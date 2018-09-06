package com.project.springstocks.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.springstocks.domain.Quote;
import com.project.springstocks.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;

@Service
public class QuoteService {

    @Autowired
    QuoteRepository quoteRepository;

    public void saveAll() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        URL json = new URL("https://bootcamp-training-files.cfapps.io/week2/week2-stocks.json");
        List<Quote> quotes = mapper.readValue(json, new TypeReference<List<Quote>>(){});

        if (quoteRepository.count() == 0) { quoteRepository.saveAll(quotes); }
    }

    public float getMax(String symbol, Date date) {
        float max = quoteRepository.getMaxPrice(symbol, date);
        return max;

    }

    public float getMin(String symbol, Date date) {
        float min = quoteRepository.getMinPrice(symbol, date);
        return min;

    }

    public float getSumVol(String symbol, Date date) {
        float sum = quoteRepository.getSumVolume(symbol, date);
        return sum;

    }

    public float getClosing(String symbol, Date date) {
        float close = quoteRepository.getClosingPrice(symbol, date);
        return close;
    }

    public float getMonthMax(String symbol, int month) {
        float summary = quoteRepository.getMonthlyMax(symbol, month);
        return summary;
    }

    public float getMonthMin(String symbol, int month) {
        float summary = quoteRepository.getMonthlyMin(symbol, month);
        return summary;
    }

    public float getMonthVol(String symbol, int month) {
        float summary = quoteRepository.getMonthlyVolume(symbol, month);
        return summary;
    }

}
