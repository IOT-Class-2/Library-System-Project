package com.example.LIBRARY.service;

import com.example.LIBRARY.model.Book;
import com.example.LIBRARY.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Yeni kitap ekleme

    public Book addBook(Book book) {
        // Yeni bir kitabı veritabanına JPA'den gelen 'save' metodu ile kaydeder.
        return bookRepository.save(book);
    }

    // Tüm kitapları getiren metot
    public List<Book> getAllBooks() {
        // Tüm kitapları veritabanından "findAll" metodu ile getirir.
        return bookRepository.findAll();
    }

    // Barcode numarasına göre arama

    public Optional<Book> getBookByBarcode(Long barcode) {
        return bookRepository.findById(barcode);
    }

    // Yazar adına göre arama
    public List<Book> getBookByAuthorName(String authorName) {
        return bookRepository.findByAuthorName(authorName);
    }

    // Update
    public Book updateBook(Long barcode, Book bookDetails) {

        Book book = bookRepository.findById(barcode)
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı!"));

        book.setBookName(bookDetails.getBookName());
        book.setAuthorName(bookDetails.getAuthorName());
        book.setGenre(bookDetails.getGenre());
        book.setPublisher(bookDetails.getPublisher());
        return bookRepository.save(book);
    }

    // Kütüphaneden kitap silme
    public void deleteBook(Long barcode) {

        Book book = bookRepository.findById(barcode)
                .orElseThrow(() -> new RuntimeException("Book not found!"));

        bookRepository.delete(book);
    }
}
