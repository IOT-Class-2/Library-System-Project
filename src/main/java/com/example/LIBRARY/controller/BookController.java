package com.example.LIBRARY.controller;

import com.example.LIBRARY.model.Book;
import com.example.LIBRARY.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Bu anotasyon, bu sınıfın bir control sınıfı/RESTful web servisi olduğunu belirtir.
@RequestMapping("/api/books") // Temel URL'ini belirtir.
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add") // Bu anotasyon ile işaretlenen bu metot, bir kitabı eklemek için kullanılır.
    @Operation(summary = "Yeni kitap kaydı yapar." )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Kitap başarıyla eklendi."),
            @ApiResponse(responseCode = "400", description = "Hatalı istek")
    })
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @GetMapping /* Çalışanları getirmek için service sınfımızdan oluşturduğumuz "getAllEmployees"
     metotu çağırarak döndürüyoruz.*/
    @Operation(summary = "Tüm kitapları getirip listeler." )
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{barcode}")
    @Operation(summary = "Belirli bir kitabı Barcode numarasına göre getirir." )
    public Optional<Book> getBookByBarcode(@PathVariable Long barcode) {
        return  bookService.getBookByBarcode(barcode);
    }

    @GetMapping("/authorName/{authorName}")
    @Operation(summary = "Belirli bir kitabı Yazar adına göre getirir." )
    public List<Book> getBookByAuthorName(@PathVariable String authorName) {
        return bookService.getBookByAuthorName(authorName);
    }

    @PutMapping("/{barcode}") /* Belitilen barcode'a ait kitabın bilgilerini bu metot
    ile update/güncelliyoruz.*/
    @Operation(summary = "Bir kitabın bilgilerini günceller." )
    public Book updateEmployee(@PathVariable Long barcode, @RequestBody Book bookDetails){
        return bookService.updateBook(barcode, bookDetails);
    }

    @DeleteMapping("/delete/{barcode}")
    @Operation(summary = "Kütüphanedeki bir kitabı siler." )
    public void deleteEmployees(@PathVariable Long barcode) {
        bookService.deleteBook(barcode);
    }
}