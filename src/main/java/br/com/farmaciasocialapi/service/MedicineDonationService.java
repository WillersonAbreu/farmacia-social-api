package br.com.farmaciasocialapi.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.farmaciasocialapi.models.MedicineDonationModel;
import br.com.farmaciasocialapi.repository.MedicineDonationRepository;
import br.com.farmaciasocialapi.resources.BaseService;

@Service
public class MedicineDonationService extends BaseService<MedicineDonationModel, MedicineDonationRepository> {

	// esse cara fica com a regra de negocio toda a logica, o controller so entrega

	@Autowired
	private MedicineDonationRepository medicineDonationRepository;

	@Autowired
	private UserService userService;

	// Buscar todas as doações com filtro
	public Page<MedicineDonationModel> getAllPageable(MedicineDonationModel filter, Pageable pageable) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		// filter.setStatusId(1l);
		Example<MedicineDonationModel> example = Example.of(filter, matcher);

		return medicineDonationRepository.findAll(example, pageable);
	}

	// Buscar todos as doações
	public List<MedicineDonationModel> getAll() {
		List<MedicineDonationModel> donations = medicineDonationRepository.findAll();
		return donations;
	}

	// Buscar todos as doações
	public List<MedicineDonationModel> getAllByUserId(Long id) {
		List<MedicineDonationModel> donations = medicineDonationRepository.getAllByUserId(id);
		return donations;
	}

	// Buscar todos as doações
	public List<MedicineDonationModel> findAllDoneDonationsByPharmacyId(Long id) {
		List<MedicineDonationModel> donations = medicineDonationRepository.findAllDoneDonationsByPharmacyId(id);
		return donations;
	}

	// Cadastrar novo anuncio
	public MedicineDonationModel save(MedicineDonationModel medicineDonation) {

		if (medicineDonation.getPictureFile().indexOf("data:image") != -1) {
			String urlDaImagemFrente = this.saveBase64(medicineDonation.getPictureFile());
			medicineDonation.setPictureFile(urlDaImagemFrente);
		}
		if (medicineDonation.getPictureFileBack().indexOf("data:image") != -1) {
			String urlDaImagemTras = this.saveBase64(medicineDonation.getPictureFileBack());
			medicineDonation.setPictureFileBack(urlDaImagemTras);
		}

		if (medicineDonation.getUserId() == userService.getUser().getId()) {
			medicineDonation.setUserId(userService.getUser().getId());
		}

		if (medicineDonation.getStatusId() < 1L) {
			medicineDonation.setStatusId(1l);
		}

		return medicineDonationRepository.save(medicineDonation);
	}

	// servico pra armazenar imagem

	private String saveBase64(String base64Str) {
		String path = Paths.get("src/main/resources/images").toString();
		String fileName = UUID.randomUUID().toString().replaceAll("-", "");
		if (base64Str == null) {
			return null;

		} else if (base64Str.indexOf("data:image/png;") != -1) {
			base64Str = base64Str.replace("data:image/png;base64,", "");
			fileName += ".png";
		} else if (base64Str.indexOf("data:image/jpeg;") != -1) {
			base64Str = base64Str.replace("data:image/jpeg;base64,", "");
			fileName += ".jpeg";
		} else if (base64Str.indexOf("data:image/jpg;") != -1) {
			base64Str = base64Str.replace("data:image/jpg;base64,", "");
			fileName += ".jpg";
		}
		File file = new File(path, fileName);
		byte[] fileBytes = Base64.getDecoder().decode(base64Str);
		try {
			FileUtils.writeByteArrayToFile(file, fileBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// http://localhost:8080/images/12312.png
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(fileName)
				.toUriString();
		return fileDownloadUri;
	}

	// procurar um anuncio pelo id do anuncio
	public MedicineDonationModel getOne(Long id) {
		Optional<MedicineDonationModel> entity = medicineDonationRepository.findById(id);

		if (!entity.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doação não encontrada!");
		}
		MedicineDonationModel donation = entity.get();
		return donation;

	}

	// trazer todos os anuncios de um usuário
	public List<MedicineDonationModel> getAll(Long userId) {
		return medicineDonationRepository.findAllByUserId(userId);
	}

	// modificar um anuncio
	public MedicineDonationModel update(Long id, MedicineDonationModel medicineDonation) {
		MedicineDonationModel currentDonation = this.getOne(id);
		medicineDonation.setId(id);
		medicineDonation.setCreatedAt(currentDonation.getCreatedAt());
		medicineDonation.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		MedicineDonationModel donationUpdated = this.save(medicineDonation);
		return donationUpdated;
	}

	// Deletar um anúncio
	public void delete(Long id) {
		MedicineDonationModel donation = this.getOne(id);
		medicineDonationRepository.delete(donation);
	}

}
