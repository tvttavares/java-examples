package com.tavares.graphqlsample.repository;

import com.tavares.graphqlsample.entity.TradeType;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TradeTypeRepository extends JpaRepository<TradeType, Long> {

    @NotNull
    Optional<TradeType> findById(@NotNull Long id);

    @NotNull
    List<TradeType> findAll();
}
