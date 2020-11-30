package br.com.farmaciasocialapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.farmaciasocialapi.dto.JwtResponseDTO;
import br.com.farmaciasocialapi.dto.LoginDTO;
import br.com.farmaciasocialapi.models.UserModel;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FarmaciaSocialApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class UserControllerTests {

	@Autowired
	private TestRestTemplate restTemplateAux;
	@LocalServerPort
	private int port;

	private String getUrl() {
		return "http://localhost:" + port + "/api/users";
	}

	private RestTemplate restTemplate;

	@BeforeAll
	public void setUp() {
		LoginDTO loginDto = new LoginDTO();
		loginDto.setEmail("admin@teste.com");
		loginDto.setPassword("123123");
		ResponseEntity<JwtResponseDTO> postResponse = restTemplateAux
				.postForEntity("http://localhost:" + port + "/api/login", loginDto, JwtResponseDTO.class);
		assertNotNull(postResponse);
		this.restTemplate = new RestTemplateBuilder(rt -> rt.getInterceptors().add((request, body, execution) -> {
			request.getHeaders().add("Authorization", "Bearer " + postResponse.getBody().getToken());
			return execution.execute(request, body);
		})).build();
	}

	@Test
	@Order(1)
	public void listaTodosUsuarios() {
		ResponseEntity<List<UserModel>> response = restTemplate.exchange(getUrl(), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<UserModel>>() {
				});
		List<UserModel> body = response.getBody();
		assertNotNull(body);
		assertEquals(200, response.getStatusCodeValue());
	}

	@SuppressWarnings("deprecation")
	@Test
	@Order(2)
	public void buscaUsuarioPorId() {
		UserModel usuario = restTemplate.getForObject(getUrl() + "/1", UserModel.class);
		System.out.println(usuario.getName());
		assertNotNull(usuario);
		assertNotNull(usuario.getName());
		assertEquals(new Long(1), usuario.getId());
	}

	@Test
	@Order(3)
	public void verificaCadastroUsuario() {
		UserModel usuario = new UserModel();
		usuario.setName("Top dos Tops");
		try {
			ResponseEntity<UserModel> postResponse = restTemplate.postForEntity(getUrl(), usuario, UserModel.class);
		} catch (HttpClientErrorException exc) {
			assertEquals(exc.getStatusCode(), HttpStatus.BAD_REQUEST);
			System.out.println(exc.getMessage());
		}
	}

	@Test
	@Order(4)
	public void cadastraUsuario() {
		UserModel usuario = new UserModel();
		usuario.setName("David Kalil");
		usuario.setEmail("david@davidkaliil.com");
		usuario.setPhone("(11) 98277-0646");
		usuario.setPassword("123456");
		usuario.setCpf("111.222.333-19");
		usuario.setCep("12711-230");
		usuario.setAddress("oi");

		ResponseEntity<UserModel> postResponse = restTemplate.postForEntity(getUrl(), usuario, UserModel.class);
		assertNotNull(postResponse);
		assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
	}

	@Test
	@Order(5)
	public void deletaUsuario() {
		UserModel usuario = restTemplate.getForObject(getUrl() + "/2", UserModel.class);
		assertNotNull(usuario);
		System.out.println("vou deletar o:" + usuario.getName());
		restTemplate.delete(getUrl() + "/2");
		try {
			usuario = restTemplate.getForObject(getUrl() + "/2", UserModel.class);
			System.out.println("deletou o:" + usuario.getName());
		} catch (final HttpClientErrorException e) {
			assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
		}
	}

}
