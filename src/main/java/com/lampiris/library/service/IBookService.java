package com.lampiris.library.service;

import java.util.List;
import com.lampiris.library.model.BookReponse;
import com.lampiris.library.model.BookRequest;

public interface IBookService {

	List<BookReponse> findAll();

	void addBook(BookRequest bookRequest);

	void deleteBook(long id);

	void modifyBook(long id, BookRequest bookRequest);
}
