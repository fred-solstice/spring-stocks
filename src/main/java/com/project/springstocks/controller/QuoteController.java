package com.project.springstocks.controller;

import com.project.springstocks.domain.AggregateData;
import com.project.springstocks.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;


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

        float max   = quoteService.getMax(symbol, date);
        float min   = quoteService.getMin(symbol, date);
        float sum   = quoteService.getSumVol(symbol, date);
        float close = quoteService.getClosing(symbol, date);

        AggregateData daily = new AggregateData(max, min, sum, close);
        return daily;
    }

    @GetMapping("/{symbol}/month/{month}")
    public AggregateData getDataForSymbolOnMonth(@PathVariable(value = "symbol") String symbol,
                                        @PathVariable(value = "month")  int month) {

        float month_min = quoteService.getMonthMin(symbol, month);
        float month_max = quoteService.getMonthMax(symbol, month);
        float month_vol = quoteService.getMonthVol(symbol, month);

        AggregateData monthly = new AggregateData(month_max, month_min, month_vol);
        return monthly;
    }
}

