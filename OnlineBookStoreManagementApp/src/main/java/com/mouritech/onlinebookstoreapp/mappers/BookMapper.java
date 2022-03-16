package com.mouritech.onlinebookstoreapp.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mouritech.onlinebookstoreapp.DTO.BookDto;
import com.mouritech.onlinebookstoreapp.entity.Book;

@Component
public class BookMapper {
	
	@Autowired
	ModelMapper mappers;
	
	public BookDto bookToBookDto(Book book) {
		return mappers.map(book, BookDto.class);
	}

	public Book bookDtoToBook(BookDto bookdto) {
		return mappers.map(bookdto, Book.class);
	}
}
