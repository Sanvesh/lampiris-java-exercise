package com.lampiris.library.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.lampiris.library.dao.IBookRepository;
import com.lampiris.library.model.BookDto;
import com.lampiris.library.model.BookReponse;
import com.lampiris.library.model.BookRequest;
import com.lampiris.library.model.GenreDto;
import com.lampiris.library.service.IBookService;

@Service
public class BookServiceImpl implements IBookService {

	@Autowired
	IBookRepository bookRepository;

	@Override
	public List<BookReponse> findAll() {
		List<BookDto> list = (List<BookDto>) bookRepository.findAll();

		List<BookReponse> reponse = list.stream().map(g -> new ModelMapper().map(g, BookReponse.class))
				.collect(Collectors.toList());
		return reponse;
	}

	@Override
	public void addBook(BookRequest bookRequest) {
		try {

			BookDto bookDto = new BookDto();
			bookDto.setAuthor(bookRequest.getAuthor());
			bookDto.setTitle(bookRequest.getTitle());
			bookDto.setGenre(new GenreDto(Long.valueOf(bookRequest.getGenre_id())));
			bookRepository.save(bookDto);

		} catch (Exception e) {
			throw new RuntimeException("Could not add the record.", e);
		}

	}

	@Override
	public void deleteBook(long id) {
		try {
			bookRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new RuntimeException("No such record exist.", e);
		} catch (Exception e) {
			throw new RuntimeException("Could not delete the record.", e);
		}

	}

	@Override
	public void modifyBook(long id, BookRequest bookRequest) {
		try {
			Optional<BookDto> existingDto = bookRepository.findById(id);

			if (existingDto.isEmpty())
				throw new IllegalArgumentException("No such record exist.");

			BookDto modifiedDto = new BookDto();
			modifiedDto.setBook_id(id);
			modifiedDto.setAuthor(bookRequest.getAuthor());
			modifiedDto.setTitle(bookRequest.getTitle());
			modifiedDto.setGenre(new GenreDto(Long.valueOf(bookRequest.getGenre_id())));
			bookRepository.save(modifiedDto);

		} catch (IllegalArgumentException e) {
			throw new RuntimeException("No such record exist.", e);

		} catch (Exception e) {
			throw new RuntimeException("Could not update the record.", e);
		}

	}

}
