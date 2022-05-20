package com.tavares.graphqlsample.repository;

import com.tavares.graphqlsample.entity.TradeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TradeTypeRepository extends JpaRepository<TradeType, Long> {

    Optional<TradeType> findById(Long id);

    @Query(value = "SELECT * FROM public.trade_type ORDER BY created_at OFFSET ?1*?2 LIMIT ?2", nativeQuery = true)
    List<TradeType> get(Integer skip, Integer take);
}
