package com.gbce.dto;

import com.gbce.enums.StockType;

import java.util.ArrayList;
import java.util.List;

public class StockDTO {

    private String symbol;
    private StockType type;
    private int lastDividend;
    private int fixedDividend;
    private int parValue;

    private List<TradeDTO> tradeList;

    public StockDTO() {
        this.tradeList = new ArrayList<>();
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public StockType getType() {
        return type;
    }

    public void setType(StockType type) {
        this.type = type;
    }

    public int getLastDividend() {
        return lastDividend;
    }

    public void setLastDividend(int lastDividend) {
        this.lastDividend = lastDividend;
    }

    public int getFixedDividend() {
        return fixedDividend;
    }

    public void setFixedDividend(int fixedDividend) {
        if (fixedDividend < 0) {
            throw new IllegalArgumentException("Fixed dividend can't be a negative value");
        }
        this.fixedDividend = fixedDividend;
    }

    public int getParValue() {
        return parValue;
    }

    public void setParValue(int parValue) {
        if (parValue < 0) {
            throw new IllegalArgumentException("Par value can not be negative value");
        }
        this.parValue = parValue;
    }

    public void addTrade(TradeDTO tradeDTO) {
        tradeList.add(tradeDTO);
    }

    public List<TradeDTO> getTradeList() {
        return this.tradeList;
    }
}
