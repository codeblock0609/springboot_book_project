package com.example.springboot_demo.service;

import com.example.springboot_demo.model.Book;
import com.example.springboot_demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> retrieveListOfBooks() {
        return (List<Book>)bookRepository.findAll();

    }

    @Override
    public Book retrieveBookById(int id) {
        return bookRepository.findById(id).get();
    }

    @Override
    @Transactional
    public String addBook(List<Book> book) {
        bookRepository.saveAll(book);
        String message = "Saving failed";
        if (retrieveListOfBooks() !=null) {
            message = "Successfully saved in the database";
        }
        return message;
    }

    @Override
    @Transactional
    public String updateBook(Book updatedBook) {
        Book book = retrieveBookById(updatedBook.getId());
        String message ="Update failed!";
        if (book != null) {
            book.setBookTitle(updatedBook.getBookTitle());
            book.setBookAuthor(updatedBook.getBookAuthor());
            book.setYearPublished(updatedBook.getYearPublished());
            bookRepository.save(book);
            message = "Updated book information";
        }
        return message;
    }

    @Override
    @Transactional
    public String deleteBook(int id) {
        String message = "No record found";
        if (retrieveBookById(id) != null) {
            bookRepository.deleteById(id);
            message = "Delete successful";
        }
        return message;
    }
}
