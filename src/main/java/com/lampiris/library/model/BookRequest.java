package com.lampiris.library.model;

import lombok.Data;

@Data
public class BookRequest {
	private String genre_id;
	private String title;
	private String author;
}
