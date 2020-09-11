package com.gbce.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gbce.dto.StockDTO;
import org.apache.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class StockLoader {
    private final static Logger logger = Logger.getLogger(StockLoader.class);
    private Map<String, StockDTO> stockDTOMap;
    private ObjectMapper objectMapper;

    public StockLoader() {
        stockDTOMap = new HashMap<>();
        objectMapper = new ObjectMapper();
    }

    public void loadStocks(String jsonPath) {
        try {
            String jsonContent = Files.readString(Path.of(jsonPath));
            StockDTO[] stocks = objectMapper.readValue(jsonContent, StockDTO[].class);

            for (StockDTO stock : stocks) {
                stockDTOMap.put(stock.getSymbol(), stock);
            }
        } catch (Exception exception) {
            logger.error("Unable to load stocks from " + jsonPath);
        }
    }

    public Map<String, StockDTO> getStocks() {
        return this.stockDTOMap;
    }
}