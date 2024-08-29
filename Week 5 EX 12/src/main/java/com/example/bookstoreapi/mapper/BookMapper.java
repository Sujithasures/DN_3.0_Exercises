package com.example.bookstoreapi.mapper;

import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BookMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "price", source = "price")
    BookDTO toDto(Book book);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "price", source = "price")
    Book toEntity(BookDTO bookDTO);
}
