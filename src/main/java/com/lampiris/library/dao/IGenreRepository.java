package com.lampiris.library.dao;

import org.springframework.data.repository.CrudRepository;
import com.lampiris.library.model.GenreDto;

public interface IGenreRepository extends CrudRepository<GenreDto, Long> {

}
