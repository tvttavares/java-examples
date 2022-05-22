package com.tavares.graphqlsample.repository;

import com.tavares.graphqlsample.entity.Listing;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {

    @NotNull
    Optional<Listing> findById(@NotNull Long id);

    @NotNull
    List<Listing> findAll();
}
