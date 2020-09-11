package com.gbce.helper;

import org.junit.Assert;
import org.junit.Test;

public class StockLoaderTest {

    StockLoader stockLoader = new StockLoader();

    @Test
    public void testLoadStockInvalid() {
        String invalidConfigPath = "config.json";
        stockLoader.loadStocks(invalidConfigPath);
        Assert.assertEquals(0, stockLoader.getStocks().size());
    }

    @Test
    public void testLoadStockValid() {
        String validConfigPath = "src/main/resources/stocks.json";
        stockLoader.loadStocks(validConfigPath);
        Assert.assertEquals(5, stockLoader.getStocks().size());
        Assert.assertEquals(true, stockLoader.getStocks().containsKey("TEA"));
    }
}
