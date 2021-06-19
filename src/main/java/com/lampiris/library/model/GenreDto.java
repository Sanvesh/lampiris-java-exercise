package com.lampiris.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Genre")
public class GenreDto {

	public GenreDto(long genre_id) {
		this.genre_id = genre_id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long genre_id;

	@Column(nullable = false, unique = true)
	private String genre;

	private String description;

}
