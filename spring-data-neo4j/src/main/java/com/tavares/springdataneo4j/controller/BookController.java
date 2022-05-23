package com.tavares.springdataneo4j.controller;

import com.tavares.springdataneo4j.neo4j.model.Book;
import com.tavares.springdataneo4j.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public Book addBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @GetMapping("/{title}")
    public Book getBookByTitle(@PathVariable String title) {
        return bookService.getBookByTitle(title);
    }

    @GetMapping("/title/{str}")
    public List<Book> getBookByTitleContaining(@PathVariable String str) {
        return bookService.getBookByTitleContaining(str);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/count")
    public Long getCountOfBooks() {
        return bookService.getCountOfBooks();
    }

    @DeleteMapping("/{id}")
    public String deleteBookById(@PathVariable String id) {
        bookService.deleteBook(Long.parseLong(id));
        return "Book deleted successfully";
    }

    @DeleteMapping
    public String deleteAllBooks() {
        bookService.deleteAllBooks();
        return "All Books deleted successfully";
    }

}