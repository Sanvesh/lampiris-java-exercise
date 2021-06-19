package com.lampiris.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Book")
public class BookDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long book_id;

	@ManyToOne
	@JoinColumn(name = "genre_id", referencedColumnName = "genre_id")
	private GenreDto genre;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String author;
}
