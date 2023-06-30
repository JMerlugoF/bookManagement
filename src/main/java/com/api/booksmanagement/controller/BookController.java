package com.api.booksmanagement.controller;

import com.api.booksmanagement.dto.BookDTO;
import com.api.booksmanagement.model.Book;
import com.api.booksmanagement.service.BookService;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class BookController {

    final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/listall")
    public ResponseEntity<Page<Book>> listAll(@PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.ASC)Pageable pageable){
        return new ResponseEntity<>(this.bookService.listAll(pageable), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") UUID id){
        Optional<Book> bookOptional = this.bookService.findById(id);
        if (!bookOptional.isPresent()){
            return new ResponseEntity<>("Erro ao editar livro", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookOptional.get(), HttpStatus.CREATED);
    }

    @PostMapping("/save")
    public ResponseEntity<Book> save(@RequestBody BookDTO bookDTO){
        Book book = Book.builder()
                .name(bookDTO.getName())
                .edition(bookDTO.getEdition())
                .description(bookDTO.getDescription())
                .build();
        return new ResponseEntity<>(this.bookService.save(book), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@RequestBody BookDTO bookDTO, @PathVariable("id") UUID id){
        Optional<Book> bookOptional = this.bookService.findById(id);
        if (!bookOptional.isPresent()){
            return new ResponseEntity<>("Erro ao editar livro", HttpStatus.NO_CONTENT);
        }
        bookOptional.get().setDescription(bookDTO.getDescription());
        bookOptional.get().setName(bookDTO.getName());
        bookOptional.get().setEdition(bookDTO.getEdition());
        return new ResponseEntity<>(this.bookService.save(bookOptional.get()), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") UUID id){
        Optional<Book> bookOptional = this.bookService.findById(id);
        if (!bookOptional.isPresent()){
            return new ResponseEntity<>("Erro ao excluir livro", HttpStatus.NO_CONTENT);
        }
        this.bookService.delete(bookOptional.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
