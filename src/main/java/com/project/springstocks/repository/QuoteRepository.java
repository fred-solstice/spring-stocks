package com.project.springstocks.repository;

import com.project.springstocks.domain.AggregateData;
import com.project.springstocks.domain.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;


public interface QuoteRepository extends JpaRepository<Quote, Integer> {

    @Query("SELECT NEW com.project.springstocks.domain.AggregateData(MAX(price), MIN(price), SUM(volume)) FROM Quote q WHERE q.symbol = ?1 AND DATE(q.date) = ?2")
    AggregateData findBySymbolAndDate(@Param("symbol") String symbol, @Param("date") Date date);
}
