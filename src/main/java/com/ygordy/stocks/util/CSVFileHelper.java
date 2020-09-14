package com.ygordy.stocks.util;

import com.ygordy.stocks.model.DowJonesStockEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class CSVFileHelper {

    public List<DowJonesStockEntity> csvToDowJonesStockEntities(String userIdStr, InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            ArrayList<DowJonesStockEntity> dowJonesStockEntities = new ArrayList();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            LocalDateTime now = LocalDateTime.now();
            Long userId = Long.parseLong(userIdStr);
            csvRecords.forEach(csvRecord -> dowJonesStockEntities.add(handleCSVRecord(csvRecord, userId, now)));
            return dowJonesStockEntities;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    private DowJonesStockEntity handleCSVRecord(CSVRecord csvRecord, Long userId, LocalDateTime now) {
        String pattern = "MM/dd/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

        DowJonesStockEntity dowJonesStockEntity = new DowJonesStockEntity(userId, now);

        if (!csvRecord.get(0).equals("")) {
            short quarter = Short.parseShort(csvRecord.get(0));
            dowJonesStockEntity.setQuarter(quarter);
        }
        if (!csvRecord.get(1).equals("")) {
            String symbol = csvRecord.get(1);
            dowJonesStockEntity.setStockSymbol(symbol);
        }
        if (!csvRecord.get(2).equals("")) {
            Date date = null;
            try {
                date = dateFormat.parse(csvRecord.get(2));
                dowJonesStockEntity.setLastBusDate(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (!csvRecord.get(3).equals("")) {
            double openPrice = Double.parseDouble(csvRecord.get(3).replace("$", ""));
            dowJonesStockEntity.setOpenPrice(openPrice);
        }
        if (!csvRecord.get(4).equals("")) {
            double highPrice = Double.parseDouble(csvRecord.get(4).replace("$", ""));
            dowJonesStockEntity.setHighPrice(highPrice);
        }
        if (!csvRecord.get(5).equals("")) {
            double lowPrice = Double.parseDouble(csvRecord.get(5).replace("$", ""));
            dowJonesStockEntity.setLowPrice(lowPrice);
        }
        if (!csvRecord.get(6).equals("")) {
            double closePrice = Double.parseDouble(csvRecord.get(6).replace("$", ""));
            dowJonesStockEntity.setClosePrice(closePrice);
        }
        if (!csvRecord.get(7).equals("")) {
            double sharesNumber = Double.parseDouble(csvRecord.get(7).replace("$", ""));
            dowJonesStockEntity.setSharesNumber(sharesNumber);
        }
        if (!csvRecord.get(8).equals("")) {
            double percentChangePrice = Double.parseDouble(csvRecord.get(8).replace("$", ""));
            dowJonesStockEntity.setPercentChangePrice(percentChangePrice);
        }
        if (!csvRecord.get(9).equals("")) {
            double percentChangeSharesNumber = Double.parseDouble(csvRecord.get(9).replace("$", ""));
            dowJonesStockEntity.setPercentChangeSharesNumber(percentChangeSharesNumber);
        }
        if (!csvRecord.get(10).equals("")) {
            double previousWeeksShares = Double.parseDouble(csvRecord.get(10).replace("$", ""));
            dowJonesStockEntity.setPreviousWeeksShares(previousWeeksShares);
        }
        if (!csvRecord.get(11).equals("")) {
            double nextWeekOpenPrice = Double.parseDouble(csvRecord.get(11).replace("$", ""));
            dowJonesStockEntity.setNextWeekOpenPrice(nextWeekOpenPrice);
        }
        if (!csvRecord.get(12).equals("")) {
            double nextWeekClosePrice = Double.parseDouble(csvRecord.get(12).replace("$", ""));
            dowJonesStockEntity.setNextWeekClosePrice(nextWeekClosePrice);
        }
        if (!csvRecord.get(13).equals("")) {
            double nextWeekChangePrice = Double.parseDouble(csvRecord.get(13).replace("$", ""));
            dowJonesStockEntity.setNextWeekChangePrice(nextWeekChangePrice);
        }
        if (!csvRecord.get(14).equals("")) {
            int daysToNextDividend = Integer.parseInt(csvRecord.get(14));
            dowJonesStockEntity.setDaysToNextDividend(daysToNextDividend);
        }
        if (!csvRecord.get(15).equals("")) {
            double percentReturnNextDividend = Double.parseDouble(csvRecord.get(15));
            dowJonesStockEntity.setPercentReturnNextDividend(percentReturnNextDividend);
        }
        return dowJonesStockEntity;
    }
}
