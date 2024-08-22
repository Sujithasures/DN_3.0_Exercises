package com.bookstore.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import lombok.Data;

@Data
public class BookDTO {

    @NotNull
    private Long id;

    @Size(min = 1, max = 255)
    private String title;

    @NotNull
    private String author;

    @Min(0)
    private Double price;

    @NotNull
    private String isbn;

	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}
}
