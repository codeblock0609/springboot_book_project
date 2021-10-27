package com.example.springboot_demo.service;

import com.example.springboot_demo.model.Book;


import java.util.List;

public interface BookService {

    public List<Book> retrieveListOfBooks();
    public Book retrieveBookById(int id);
    public String addBook(List<Book> book);
    public String updateBook(Book book);
    public String deleteBook(int id);
}
