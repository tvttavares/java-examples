package com.tavares.springdataneo4j.neo4j.repository;

import com.tavares.springdataneo4j.neo4j.model.Author;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends Neo4jRepository<Author, Long> {

    @Query("MATCH (au:Author)<-[a:AUTHORED]-(b:Book) RETURN au,a,b")
    List<Author> getAllAuthors();
}