package com.project.springstocks.repository;

import com.project.springstocks.domain.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface QuoteRepository extends JpaRepository<Quote, Integer> {

    @Query("SELECT MAX(q.price) FROM Quote q WHERE q.symbol = :symbol AND DATE(q.date) = :date")
    float getMaxPrice(@Param("symbol") String symbol, @Param("date") Date date);

    @Query("SELECT MIN(q.price) FROM Quote q WHERE q.symbol = :symbol AND DATE(q.date) = :date")
    float getMinPrice(@Param("symbol") String symbol, @Param("date") Date date);

    @Query("SELECT SUM(q.volume) FROM Quote q WHERE q.symbol = :symbol AND DATE(q.date) = :date")
    float getSumVolume(@Param("symbol") String symbol, @Param("date") Date date);

    @Query(value = "SELECT price FROM quotes q WHERE q.symbol = :symbol AND DATE(q.date) = :date ORDER BY date DESC LIMIT 1", nativeQuery = true)
    float getClosingPrice(@Param("symbol") String symbol, @Param("date") Date date);

    @Query("SELECT MAX(q.price) FROM Quote q WHERE q.symbol = :symbol AND MONTH(q.date) = :month")
    float getMonthlyMax(@Param("symbol") String symbol, @Param("month") int month);

    @Query("SELECT MIN(q.price) FROM Quote q WHERE q.symbol = :symbol AND MONTH(q.date) = :month")
    float getMonthlyMin(@Param("symbol") String symbol, @Param("month") int month);

    @Query("SELECT SUM(q.volume) FROM Quote q WHERE q.symbol = :symbol AND MONTH(q.date) = :month")
    float getMonthlyVolume(@Param("symbol") String symbol, @Param("month") int month);
}
