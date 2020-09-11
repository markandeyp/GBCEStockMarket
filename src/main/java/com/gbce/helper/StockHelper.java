package com.gbce.helper;

import com.gbce.dto.StockDTO;
import com.gbce.dto.TradeDTO;
import com.gbce.enums.StockType;
import org.apache.log4j.Logger;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class StockHelper {
    private final static Logger logger = Logger.getLogger(StockHelper.class);

    public static double getDividendYield(StockDTO stock, double price) throws IllegalArgumentException {
        if (price == 0.0) {
            throw new IllegalArgumentException("Dividend Yield Calculation Error. Price can't be zero");
        }
        double dividendYield = (stock.getType() == StockType.Common)
                ? stock.getLastDividend() / price
                : (stock.getFixedDividend() * stock.getParValue()) / price;
        dividendYield = getFormattedDouble(dividendYield);

        return dividendYield;
    }

    public static double calculatePERatio(int dividend, double price) throws IllegalArgumentException {
        if (dividend == 0) {
            throw new IllegalArgumentException("PE Ratio Calculation Error. Dividend can't be zero");
        }
        double pERatio = price / dividend;
        pERatio = getFormattedDouble(pERatio);
        return pERatio;
    }


    public static double calculateVolumeWeightPrice(StockDTO stock) {
        double volWeightPrice = 0.0;
        ZonedDateTime currentTime = ZonedDateTime.now();
        List<TradeDTO> tradeDTOList = stock.getTradeList().
                stream()
                .filter(tradeDTO -> getTimeDiff(tradeDTO.getTimestamp(), currentTime) < 900)
                .collect(Collectors.toList());

        int totalTradeQuantity = tradeDTOList.stream().mapToInt(tradeDTO -> tradeDTO.getQuantity()).sum();
        double totalTradePrice = tradeDTOList.stream().mapToDouble(tradeDTO -> tradeDTO.getPrice()).sum();

        if (totalTradeQuantity != 0) {
            volWeightPrice = totalTradePrice / totalTradeQuantity;
        }
        volWeightPrice = getFormattedDouble(volWeightPrice);
        return volWeightPrice;
    }


    public static double calculateGBCEAllShareIndex(List<StockDTO> stocks) {
        double volWeightPriceTotal = 0;
        volWeightPriceTotal = stocks.stream()
                .mapToDouble(stockDTO -> calculateVolumeWeightPrice(stockDTO))
                .sum();
        double gbceAllShareIndex = Math.pow(volWeightPriceTotal, 1.0 / stocks.size());
        return StockHelper.getFormattedDouble(gbceAllShareIndex);
    }


    public static double getFormattedDouble(double value) {
        double roundedOffVal = Math.round(value * 100);
        roundedOffVal = roundedOffVal / 100;
        return roundedOffVal;
    }

    private static long getTimeDiff(ZonedDateTime time1, ZonedDateTime time2) {
        ChronoUnit unit = ChronoUnit.SECONDS;
        return unit.between(time1, time2);
    }

}
