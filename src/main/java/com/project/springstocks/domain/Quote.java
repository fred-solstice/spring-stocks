package com.project.springstocks.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "quotes")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String symbol;

    @Column
    private Float price;

    @Column
    private int volume;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date date;

    public Quote(String symbol, Float price, int volume, Date date) {
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
        this.date = date;
    }

    protected Quote(){}

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getSymbol() { return symbol; }

    public void setSymbol(String symbol) { this.symbol = symbol; }

    public Float getPrice() { return price; }

    public void setPrice(Float price) { this.price = price; }

    public int getVolume() { return volume; }

    public void setVolume(int volume) { this.volume = volume; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }
}
