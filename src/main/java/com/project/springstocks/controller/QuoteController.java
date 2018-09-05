package com.project.springstocks.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.springstocks.domain.AggregateData;
import com.project.springstocks.domain.Quote;
import com.project.springstocks.repository.QuoteRepository;
import com.project.springstocks.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@RestController
public class QuoteController {

    @Autowired
    QuoteService quoteService;

    @PostMapping(path = "/load", consumes = "application/json")
    public void insertQuotes() throws IOException {
        quoteService.saveAll();
    }

    @GetMapping("/{symbol}/{date}")
    public AggregateData getDataForSymbolOnDate(@PathVariable(value = "symbol") String symbol,
                                       @PathVariable(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {

        float max = quoteService.getMax(symbol, date);
        float min = quoteService.getMin(symbol, date);
        float sum = quoteService.getSumVol(symbol, date);
        float close = quoteService.getClosing(symbol, date);

        List<Float> data = Arrays.asList(max, min, sum, close);

        AggregateData daily = new AggregateData(max, min, sum, close);

        return daily;
    }

}

