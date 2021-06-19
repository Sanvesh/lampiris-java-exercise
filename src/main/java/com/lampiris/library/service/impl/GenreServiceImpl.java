package com.lampiris.library.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.lampiris.library.dao.IGenreRepository;
import com.lampiris.library.model.GenreDto;
import com.lampiris.library.model.GenreReponse;
import com.lampiris.library.model.GenreRequest;
import com.lampiris.library.service.IGenreService;

@Service
public class GenreServiceImpl implements IGenreService {

	@Autowired
	IGenreRepository genreRepository;

	@Override
	public List<GenreReponse> findAll() {
		List<GenreDto> list = (List<GenreDto>) genreRepository.findAll();

		List<GenreReponse> reponse = list.stream().map(g -> new ModelMapper().map(g, GenreReponse.class))
				.collect(Collectors.toList());
		return reponse;
	}

	@Override
	public void addGenre(GenreRequest genreRequest) {
		try {

			genreRepository.save(new ModelMapper().map(genreRequest, GenreDto.class));

		} catch (Exception e) {
			throw new RuntimeException("Could not add the record.", e);
		}

	}

	@Override
	public void deleteGenere(long id) {
		try {
			genreRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new RuntimeException("No such record exist.", e);
		} catch (Exception e) {
			throw new RuntimeException("Could not delete the record.", e);
		}
	}

	@Override
	public void modifyGenre(long id, GenreRequest genreRequest) {
		try {
			Optional<GenreDto> existingDto = genreRepository.findById(id);

			if (existingDto.isEmpty())
				throw new IllegalArgumentException("No such record exist.");

			GenreDto modifiedDto = new ModelMapper().map(genreRequest, GenreDto.class);
			modifiedDto.setGenre_id(id);
			genreRepository.save(modifiedDto);

		} catch (IllegalArgumentException e) {
			throw new RuntimeException("No such record exist.", e);

		} catch (Exception e) {
			throw new RuntimeException("Could not update the record.", e);
		}

	}

}
