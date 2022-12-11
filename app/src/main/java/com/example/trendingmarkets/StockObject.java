package com.example.trendingmarkets;

public class StockObject {
    String ticker, price, volume;

    public StockObject(String ticker, String price, String volume) {

        this.ticker = ticker;
        this.price = price;
        this.volume = volume;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String title) {
        this.ticker = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String artist) {
        this.price = artist;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String country) {this.volume = country; }
}
