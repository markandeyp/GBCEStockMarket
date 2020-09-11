package com.gbce.service.impl;

import com.gbce.dto.StockDTO;
import com.gbce.dto.TradeDTO;
import com.gbce.helper.StockHelper;
import com.gbce.model.StockModel;
import com.gbce.service.StockService;
import org.apache.log4j.Logger;

import java.time.ZonedDateTime;
import java.util.List;

public class StockServiceImpl extends StockHelper implements StockService {

    private final static Logger logger = Logger.getLogger(StockServiceImpl.class);
    private StockModel stockModel = StockModel.getModelInstance();

    @Override
    public double calculateDividendYield(String stockSymbol, double price) throws IllegalArgumentException {
        double dividendYield = 0;
        if (price < 0) {
            logger.error("Price can't be less than zero");
            throw new IllegalArgumentException("Price can't be less than zero");
        }
        if (stockSymbol == null || stockSymbol.isEmpty()) {
            logger.error("Invalid stock symbol");
            throw new IllegalArgumentException("Invalid stock symbol");
        }
        StockDTO stock = stockModel.getStock(stockSymbol);
        dividendYield = StockHelper.getDividendYield(stock, price);
        return dividendYield;
    }

    @Override
    public double calculatePERatio(String stockSymbol, double price) throws IllegalArgumentException {
        double pERatio;
        if (price < 0) {
            logger.error("Price can't be less than zero");
            throw new IllegalArgumentException("Price can't be less than zero");
        }
        if (stockSymbol == null || stockSymbol.isEmpty()) {
            logger.error("Invalid stock symbol");
            throw new IllegalArgumentException("Invalid stock symbol");
        }
        StockDTO stock = stockModel.getStock(stockSymbol);
        pERatio = StockHelper.calculatePERatio(stock.getLastDividend(), price);
        return pERatio;
    }

    @Override
    public void addTrade(String stockSymbol, TradeDTO trade) throws IllegalArgumentException {
        if (stockSymbol == null || stockSymbol.isEmpty()) {
            logger.error("Invalid stock symbol");
            throw new IllegalArgumentException("Invalid stock symbol");
        }
        if (trade == null) {
            logger.error("Invalid trade data");
            throw new IllegalArgumentException("Invalid trade data");
        }
        trade.setTimestamp(ZonedDateTime.now());
        StockDTO stock = stockModel.getStock(stockSymbol);
        stock.addTrade(trade);
    }

    @Override
    public double calculateVolumeWeightedPrice(String stockSymbol) throws IllegalArgumentException {
        if (stockSymbol == null || stockSymbol.isEmpty()) {
            logger.error("Invalid stock symbol");
            throw new IllegalArgumentException("Invalid stock symbol");
        }
        double volWeightedPrice = 0.0;
        StockDTO stock = stockModel.getStock(stockSymbol);
        volWeightedPrice = StockHelper.calculateVolumeWeightPrice(stock);
        return volWeightedPrice;
    }

    @Override
    public double calculateGBCEAllShareIndex() {
        double gbceAllShareIndex = 0.0;
        List<StockDTO> stocks = stockModel.getAllStocks();
        gbceAllShareIndex = StockHelper.calculateGBCEAllShareIndex(stocks);
        return gbceAllShareIndex;
    }

}
