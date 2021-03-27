package br.com.farmaciasocialapi.controllers;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsInstanceOf;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import br.com.farmaciasocialapi.FarmaciaSocialApiApplication;
import br.com.farmaciasocialapi.dto.JwtResponseDTO;
import br.com.farmaciasocialapi.dto.LoginDTO;
import br.com.farmaciasocialapi.models.UserModel;
import br.com.farmaciasocialapi.service.UserService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FarmaciaSocialApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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

	@Mock
	private UserController controller;
	@Mock
	private UserService service;

	@BeforeAll
	public void setUp() {
		service = mock(UserService.class);
		controller = new UserController(service);

		// this.shouldSetTokenOnHeader();
	}

	public void shouldSetTokenOnHeader() {
		LoginDTO loginDto = new LoginDTO();
		loginDto.setEmail("kessyanesilva.97@hotmail.com");
		loginDto.setPassword("123");
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
	@DisplayName("Deve retornar uma lista de todos os usuários registrados")
	public void shouldReturnArrayWithAllUsers() {
		ResponseEntity<List<UserModel>> response = restTemplate.exchange(getUrl(), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<UserModel>>() {
				});
		List<UserModel> body = response.getBody();
		assertNotNull(body);
		assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	@Order(2)
	@DisplayName("Deve retornar um usuário buscado pelo ID")
	public void shouldReturnUserById() {
		UserModel user = restTemplate.getForObject(getUrl() + "/1", UserModel.class);
		assertNotNull(user);
		assertNotNull(user.getName());
		assertEquals(1, user.getId());
	}

	@Test
	@Order(3)
	@DisplayName("Deve retornar retornar status HTTP 201")
	public void shouldRegisterANewUser() {
		UserModel user = new UserModel();

		user.setName("Usuário Teste");
		user.setEmail("dummy@email.com");
		user.setPassword("123");
		user.setPhone("(12) 98175-5795");
		user.setRoleId(1L);
		user.setCpf("815.101.140-84");
		user.setAddress("Rua Maestro João Seppe, 303, Jardim Paraíso, São Carlos - SP");
		user.setCep("13561-180");

		ResponseEntity<UserModel> postResponse = restTemplate.postForEntity(getUrl(), user, UserModel.class);
		assertEquals(201, postResponse.getStatusCodeValue());
	}

	// @Test
	// @Order(4)
	// public void cadastraUsuario() {
	// UserModel usuario = new UserModel();
	// usuario.setName("David Kalil");
	// usuario.setEmail("david@davidkaliil.com");
	// usuario.setPhone("(11) 98277-0646");
	// usuario.setPassword("123456");
	// usuario.setCpf("111.222.333-19");
	// usuario.setCep("12711-230");
	// usuario.setAddress("oi");

	// ResponseEntity<UserModel> postResponse = restTemplate.postForEntity(getUrl(),
	// usuario, UserModel.class);
	// assertNotNull(postResponse);
	// assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
	// }

	// @Test
	// @Order(5)
	// public void deletaUsuario() {
	// UserModel usuario = restTemplate.getForObject(getUrl() + "/2",
	// UserModel.class);
	// assertNotNull(usuario);
	// System.out.println("vou deletar o:" + usuario.getName());
	// restTemplate.delete(getUrl() + "/2");
	// try {
	// usuario = restTemplate.getForObject(getUrl() + "/2", UserModel.class);
	// System.out.println("deletou o:" + usuario.getName());
	// } catch (final HttpClientErrorException e) {
	// assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
	// }
	// }

}
