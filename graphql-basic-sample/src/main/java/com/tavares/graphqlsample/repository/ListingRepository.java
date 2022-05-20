package com.tavares.graphqlsample.repository;

import com.tavares.graphqlsample.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {

    Optional<Listing> findById(Long id);

    @Query(value = "SELECT * FROM public.listing ORDER BY created_at OFFSET ?1*?2 LIMIT ?2", nativeQuery = true)
    List<Listing> get(Integer skip, Integer take);
}
