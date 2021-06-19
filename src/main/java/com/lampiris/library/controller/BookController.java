package com.lampiris.library.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lampiris.library.model.BookReponse;
import com.lampiris.library.model.BookRequest;
import com.lampiris.library.service.IBookService;
import com.lampiris.library.util.ExcelExporterUtils;

@RestController
@RequestMapping("api/librarymanagement/book")
public class BookController {

	@Autowired
	IBookService bookService;

	@GetMapping(path = "/findAll", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<BookReponse>> findAll() {
		List<BookReponse> list = bookService.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PostMapping(path = "/add")
	public ResponseEntity<String> addBook(@RequestBody BookRequest bookRequest) {
		try {
			bookService.addBook(bookRequest);
			return new ResponseEntity<>("Successfully added.", HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable long id) {
		try {
			bookService.deleteBook(id);
			return new ResponseEntity<>("Successfully deleted.", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<String> modifyBook(@PathVariable long id, @RequestBody BookRequest bookRequest) {
		try {

			bookService.modifyBook(id, bookRequest);
			return new ResponseEntity<>("Successfully updated.", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_MODIFIED);
		}
	}

	@GetMapping("/export")
	public void exportToExcel(HttpServletResponse response) throws IOException {

		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headervalue = "attachment; filename=book_records.xlsx";

		response.setHeader(headerKey, headervalue);
		List<BookReponse> list = bookService.findAll();
		ExcelExporterUtils exp = new ExcelExporterUtils(list);
		exp.export(response);

	}

}
