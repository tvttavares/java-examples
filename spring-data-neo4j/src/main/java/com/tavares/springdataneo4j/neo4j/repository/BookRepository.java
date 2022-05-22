package com.tavares.springdataneo4j.neo4j.repository;

import com.tavares.springdataneo4j.neo4j.model.Book;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends Neo4jRepository<Book, Long> {

    Book findByTitle(String title);

    Book findByLanguage(String language);

    @Query("MATCH (b:Book) RETURN b")
    List<Book> getAllBooks();

    @Query("MATCH (b:Book) WHERE b.title =~ ('(?i).*'+$str+'.*') RETURN b")
    List<Book> findByTitleContaining(String str);
}