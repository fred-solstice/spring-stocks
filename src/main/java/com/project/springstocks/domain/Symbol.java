package com.project.springstocks.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Symbol {

    private String symbol;

    public Symbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
