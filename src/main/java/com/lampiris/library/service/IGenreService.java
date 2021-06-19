package com.lampiris.library.service;

import java.util.List;
import com.lampiris.library.model.GenreReponse;
import com.lampiris.library.model.GenreRequest;

public interface IGenreService {

	List<GenreReponse> findAll();

	void addGenre(GenreRequest genreRequest);

	void deleteGenere(long id);

	void modifyGenre(long id, GenreRequest genreRequest);
}
