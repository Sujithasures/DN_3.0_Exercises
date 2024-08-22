package com.bookstore.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import lombok.Data;

@Data
public class CustomerDTO {

    @NotNull
    private Long id;

    @Size(min = 1, max = 100)
    private String name;

    @Email
    private String email;

    @Size(min = 10, max = 15)
    private String phoneNumber;

	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}
}
