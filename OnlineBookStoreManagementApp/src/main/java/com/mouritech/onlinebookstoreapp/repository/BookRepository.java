package com.mouritech.onlinebookstoreapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mouritech.onlinebookstoreapp.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	Book findByBookName(String bookName);

}
