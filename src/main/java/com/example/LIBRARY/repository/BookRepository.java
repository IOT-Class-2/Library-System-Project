package com.example.LIBRARY.repository;

import com.example.LIBRARY.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository  extends JpaRepository<Book, Long> {

    //JPARepository kütüphanesinde yazar adına göre getir metotu olmadığı için metotu kendimiz oluşturuyoruz.

    List<Book> findByAuthorName(String authorName); // Yazar adına göre kitabı getiren metot.
}
