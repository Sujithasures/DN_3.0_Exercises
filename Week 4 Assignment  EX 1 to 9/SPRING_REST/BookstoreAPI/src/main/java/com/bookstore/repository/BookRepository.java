
package com.bookstore.repository;

import com.bookstore.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

	Book save(Book book);
}
