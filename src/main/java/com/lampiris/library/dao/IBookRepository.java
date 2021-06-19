package com.lampiris.library.dao;

import org.springframework.data.repository.CrudRepository;
import com.lampiris.library.model.BookDto;

public interface IBookRepository extends CrudRepository<BookDto, Long> {

}
