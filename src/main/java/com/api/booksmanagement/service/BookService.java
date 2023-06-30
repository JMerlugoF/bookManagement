package com.api.booksmanagement.service;

import com.api.booksmanagement.model.Book;
import com.api.booksmanagement.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public Book save(Book book){
        return this.bookRepository.save(book);
    }

    public void delete(Book book){
        this.bookRepository.delete(book);
    }

    public Optional<Book> findById(UUID id){
        return this.bookRepository.findById(id);
    }

    public Page<Book> listAll(Pageable pageable){
        return this.bookRepository.findAll(pageable);
    }

}
