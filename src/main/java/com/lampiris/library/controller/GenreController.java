package com.lampiris.library.controller;

import java.util.List;
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
import com.lampiris.library.model.GenreReponse;
import com.lampiris.library.model.GenreRequest;
import com.lampiris.library.service.IGenreService;

@RestController
@RequestMapping("api/librarymanagement/genre")
public class GenreController {

	@Autowired
	private IGenreService genreService;

	@GetMapping(path = "/findAll", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<GenreReponse>> findAll() {
		List<GenreReponse> list = genreService.findAll();

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PostMapping(path = "/add")
	public ResponseEntity<String> addGenre(@RequestBody GenreRequest genreRequest) {
		try {

			genreService.addGenre(genreRequest);
			return new ResponseEntity<>("Succefully added.", HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> deleteGenere(@PathVariable long id) {
		try {
			genreService.deleteGenere(id);
			return new ResponseEntity<>("Succefully deleted.", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<String> modifyGenre(@PathVariable long id, @RequestBody GenreRequest genreRequest) {
		try {

			genreService.modifyGenre(id, genreRequest);
			return new ResponseEntity<>("Succefully updated.", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_MODIFIED);
		}
	}

}
