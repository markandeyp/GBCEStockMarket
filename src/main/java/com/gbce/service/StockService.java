package com.gbce.service;

import com.gbce.dto.TradeDTO;

public interface StockService {

    public double calculateDividendYield(String stockSymbol, double price) throws IllegalArgumentException;

    public double calculatePERatio(String stockSymbol, double price) throws IllegalArgumentException;

    public void addTrade(String stockSymbol, TradeDTO tradeData) throws IllegalArgumentException;

    public double calculateVolumeWeightedPrice(String stockSymbol) throws IllegalArgumentException;

    public double calculateGBCEAllShareIndex();

}
