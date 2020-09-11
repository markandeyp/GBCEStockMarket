package com.gbce.service.impl;

import com.gbce.dto.TradeDTO;
import com.gbce.enums.TradeType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StockServiceImplTest {
    private StockServiceImpl stockService;

    @Before
    public void setup() {
        stockService = new StockServiceImpl();
    }

    @Test
    public void testCalculateDividendYield() {
        double dividendYield = stockService.calculateDividendYield("ALE", 2);
        Assert.assertEquals(11.5, dividendYield, 0.0);
    }

    @Test
    public void testCalculatePERatio() {
        double peRatio = stockService.calculatePERatio("ALE", 5);
        Assert.assertEquals(0.22, peRatio, 0.0);
    }

    @Test
    public void testAddTrade() {
        TradeDTO tradeDTO = new TradeDTO();
        tradeDTO.setType(TradeType.Buy);
        tradeDTO.setPrice(10);
        tradeDTO.setQuantity(5);
        stockService.addTrade("ALE", tradeDTO);
        double volWeightedPrice = stockService.calculateVolumeWeightedPrice("ALE");
        Assert.assertEquals(volWeightedPrice, 2.0, 0.0);
    }

    @Test
    public void testCalculateVolumeWeightedPrice() {
        stockService.addTrade("ALE", createTrade(TradeType.Buy, 10, 5));
        stockService.addTrade("ALE", createTrade(TradeType.Buy, 10, 20));

        double volWeightedPrice = stockService.calculateVolumeWeightedPrice("ALE");
        Assert.assertEquals(volWeightedPrice, 1.04, 0.0);
    }

    @Test
    public void testCalculateGBCEAllShareIndex() {
        stockService.addTrade("ALE", createTrade(TradeType.Buy, 10, 5));
        stockService.addTrade("ALE", createTrade(TradeType.Buy, 17, 20));
        stockService.addTrade("TEA", createTrade(TradeType.Buy, 15, 30));
        stockService.addTrade("JOE", createTrade(TradeType.Sell, 20, 50));
        double gbceAllShareIndex = stockService.calculateGBCEAllShareIndex();
        Assert.assertEquals(1.16, gbceAllShareIndex, 0.0);
    }

    private TradeDTO createTrade(TradeType tradeType, double price, int quantity) {
        TradeDTO trade = new TradeDTO();
        trade.setType(tradeType);
        trade.setPrice(price);
        trade.setQuantity(quantity);
        return trade;
    }
}