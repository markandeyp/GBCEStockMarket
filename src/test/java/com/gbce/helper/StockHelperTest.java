package com.gbce.helper;

import com.gbce.dto.StockDTO;
import com.gbce.dto.TradeDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.ZonedDateTime;

public class StockHelperTest {
    StockLoader stockLoader = new StockLoader();

    @Before()
    public void setup() {
        stockLoader.loadStocks("src/main/resources/stocks.json");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateDividendYieldPriceZero() {
        double dividendYield = StockHelper.getDividendYield(stockLoader.getStocks().get("ALE"), 0);
    }

    @Test
    public void testCalculateDividendYield() {
        double dividendYield = StockHelper.getDividendYield(stockLoader.getStocks().get("ALE"), 2);
        Assert.assertEquals(11.5, dividendYield, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateDividendYieldPreferredPriceZero() {
        double dividendYield = StockHelper.getDividendYield(stockLoader.getStocks().get("JIN"), 0);
    }

    @Test
    public void testCalculateDividendYieldPreferred() {
        double dividendYield = StockHelper.getDividendYield(stockLoader.getStocks().get("JIN"), 2);
        Assert.assertEquals(0.0, dividendYield, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePERatioDividendZero() {
        double peRatio = StockHelper.calculatePERatio(0, 5);
    }

    @Test
    public void testCalculatePERatioDividend() {
        double peRatio = StockHelper.calculatePERatio(10, 5);
        Assert.assertEquals(0.5, peRatio, 0.0);
    }

    @Test
    public void testGetFormattedDouble() {
        double formattedDouble = StockHelper.getFormattedDouble(4.33245);
        Assert.assertEquals(4.33, formattedDouble, 0.0);
    }

    private StockDTO createStockWithTrades() {
        ZonedDateTime time = ZonedDateTime.now();

        TradeDTO olderTrade = new TradeDTO();
        olderTrade.setQuantity(3);
        olderTrade.setPrice(10.0);
        time = time.minusSeconds(901);
        olderTrade.setTimestamp(time);

        TradeDTO oldTrade = new TradeDTO();
        oldTrade.setQuantity(2);
        oldTrade.setPrice(10.0);
        time = time.minusSeconds(100);
        oldTrade.setTimestamp(time);

        TradeDTO onTimeTrade = new TradeDTO();
        onTimeTrade.setQuantity(4);
        onTimeTrade.setPrice(10.0);
        onTimeTrade.setTimestamp(time);

        StockDTO stockDTO = new StockDTO();
        stockDTO.addTrade(olderTrade);
        stockDTO.addTrade(oldTrade);
        stockDTO.addTrade(onTimeTrade);

        return stockDTO;
    }

    private StockDTO createStockWithTradesUnder15Minutes() {
        ZonedDateTime time = ZonedDateTime.now();

        TradeDTO olderTrade = new TradeDTO();
        olderTrade.setQuantity(3);
        olderTrade.setPrice(10.0);
        time = time.minusSeconds(200);
        olderTrade.setTimestamp(time);

        TradeDTO oldTrade = new TradeDTO();
        oldTrade.setQuantity(2);
        oldTrade.setPrice(10.0);
        time = time.minusSeconds(100);
        oldTrade.setTimestamp(time);

        TradeDTO onTimeTrade = new TradeDTO();
        onTimeTrade.setQuantity(4);
        onTimeTrade.setPrice(10.0);
        onTimeTrade.setTimestamp(time);

        StockDTO stockDTO = new StockDTO();
        stockDTO.addTrade(olderTrade);
        stockDTO.addTrade(oldTrade);
        stockDTO.addTrade(onTimeTrade);

        return stockDTO;
    }

    @Test
    public void testCalculateVolumeWeightPriceWithNoTradeUnder15Minutes() {
        double volWeightPrice = StockHelper.calculateVolumeWeightPrice(createStockWithTrades());
        Assert.assertEquals(0.0, volWeightPrice, 0.0);
    }

    @Test
    public void testCalculateVolumeWeightPrice() {
        double volWeightPrice = StockHelper.calculateVolumeWeightPrice(createStockWithTradesUnder15Minutes());
        Assert.assertEquals(3.33, volWeightPrice, 0.0);
    }
}
