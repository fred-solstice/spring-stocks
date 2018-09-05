package com.project.springstocks.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.springstocks.domain.AggregateData;
import com.project.springstocks.domain.Quote;
import com.project.springstocks.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;


@RestController
public class QuoteController {

    @Autowired
    QuoteRepository quoteRepository;

    @PostMapping(path = "/load", consumes = "application/json")
    public void insertQuotes() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File json = new File("/Users/freddymarquez/Code/spring-stocks/src/main/resources/stocks.json");
        List<Quote> quotes = mapper.readValue(json, new TypeReference<List<Quote>>() {
        });

        if (quoteRepository.count() == 0) {
            quoteRepository.saveAll(quotes);

        } else {
            quoteRepository.deleteAllInBatch();
            quoteRepository.saveAll(quotes);
        }
    }

    @GetMapping("/{symbol}/{date}")
    public AggregateData getAggregatedDataForSymbolOnDate(@PathVariable(value = "symbol") String symbol,
                                                          @PathVariable(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {

        return quoteRepository.findBySymbolAndDate(symbol, date);

    }

}

