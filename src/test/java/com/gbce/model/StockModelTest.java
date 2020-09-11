package com.gbce.model;

import com.gbce.dto.StockDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StockModelTest {
    private StockModel stockModel;

    @Before()
    public void setup() {
        stockModel = StockModel.getModelInstance();
    }

    @Test
    public void testGetModelInstance() {
        StockModel localStockModel = StockModel.getModelInstance();
        assertEquals(stockModel, localStockModel);
    }

    @Test
    public void testInit() {
        stockModel.init();
        assertEquals(5, stockModel.getAllStocks().size());
    }

    @Test
    public void testGetStock() {
        StockDTO stock = stockModel.getStock("TEA");
        assertNotNull(stock);
        assertEquals("TEA", stock.getSymbol());
    }

    @Test
    public void testGetAllStocks() {
        assertEquals(5, stockModel.getAllStocks().size());
    }
}