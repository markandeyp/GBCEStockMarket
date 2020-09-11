package com.gbce.model;

import com.gbce.dto.StockDTO;
import com.gbce.helper.StockLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StockModel {
    private static StockModel modelInstance;
    private Map<String, StockDTO> stockMap;
    private StockLoader stockLoader;

    private StockModel() {
        stockLoader = new StockLoader();
        init();
    }

    public static StockModel getModelInstance() {
        if (modelInstance == null)
            modelInstance = new StockModel();
        return modelInstance;
    }

    public void init() {
        stockLoader.loadStocks("src/main/resources/stocks.json");
        stockMap = stockLoader.getStocks();
    }

    public StockDTO getStock(String stockSymbol) {
        StockDTO stockInstance = stockMap.get(stockSymbol);
        if (stockInstance == null) {
            throw new IllegalArgumentException("Invalid stock symbol");
        }
        return stockInstance;
    }

    public List<StockDTO> getAllStocks() {
        return new ArrayList<StockDTO>(stockMap.values());
    }
}
