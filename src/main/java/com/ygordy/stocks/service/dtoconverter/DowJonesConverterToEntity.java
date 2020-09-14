package com.ygordy.stocks.service.dtoconverter;

import com.ygordy.stocks.model.DowJonesStockEntity;
import com.ygordy.stocks.service.dto.DowJonesStock;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class DowJonesConverterToEntity implements Function<DowJonesStock, DowJonesStockEntity> {
    @Override
    public DowJonesStockEntity apply(DowJonesStock dowJonesStock) {
        DowJonesStockEntity dowJonesStockEntity = new DowJonesStockEntity();
        if (dowJonesStock.getId() != null)
            dowJonesStockEntity.setId(dowJonesStock.getId());
        if (dowJonesStock.getSetupByUserId() != null)
            dowJonesStockEntity.setSetupByUserId(dowJonesStock.getSetupByUserId());
        if (dowJonesStock.getModifiedDate() != null)
            dowJonesStockEntity.setModifiedDate(dowJonesStock.getModifiedDate());
        if (dowJonesStock.getQuarter() != null)
            dowJonesStockEntity.setQuarter(dowJonesStock.getQuarter());
        if (dowJonesStock.getStockSymbol() != null)
            dowJonesStockEntity.setStockSymbol(dowJonesStock.getStockSymbol());
        if (dowJonesStock.getLastBusDate() != null)
            dowJonesStockEntity.setLastBusDate(dowJonesStock.getLastBusDate());
        if (dowJonesStock.getOpenPrice() != null)
            dowJonesStockEntity.setOpenPrice(dowJonesStock.getOpenPrice());
        if (dowJonesStock.getHighPrice() != null)
            dowJonesStockEntity.setHighPrice(dowJonesStock.getHighPrice());
        if (dowJonesStock.getLowPrice() != null)
            dowJonesStockEntity.setLowPrice(dowJonesStock.getLowPrice());
        if (dowJonesStock.getClosePrice() != null)
            dowJonesStockEntity.setClosePrice(dowJonesStock.getClosePrice());
        if (dowJonesStock.getSharesNumber() != null)
            dowJonesStockEntity.setSharesNumber(dowJonesStock.getSharesNumber());
        if (dowJonesStock.getPercentChangePrice() != null)
            dowJonesStockEntity.setPercentChangePrice(dowJonesStock.getPercentChangePrice());
        if (dowJonesStock.getPercentChangeSharesNumber() != null)
            dowJonesStockEntity.setPercentChangeSharesNumber(dowJonesStock.getPercentChangeSharesNumber());
        if (dowJonesStock.getPercentReturnNextDividend() != null)
            dowJonesStockEntity.setPercentReturnNextDividend(dowJonesStock.getPercentReturnNextDividend());
        if (dowJonesStock.getNextWeekOpenPrice() != null)
            dowJonesStockEntity.setNextWeekOpenPrice(dowJonesStock.getNextWeekOpenPrice());
        if (dowJonesStock.getNextWeekClosePrice() != null)
            dowJonesStockEntity.setNextWeekClosePrice(dowJonesStock.getNextWeekClosePrice());
        if (dowJonesStock.getNextWeekChangePrice() != null)
            dowJonesStockEntity.setNextWeekChangePrice(dowJonesStock.getNextWeekChangePrice());
        if (dowJonesStock.getDaysToNextDividend() != null)
            dowJonesStockEntity.setDaysToNextDividend(dowJonesStock.getDaysToNextDividend());
        if (dowJonesStock.getPercentReturnNextDividend() != null)
            dowJonesStockEntity.setPercentReturnNextDividend(dowJonesStock.getPercentReturnNextDividend());
        return dowJonesStockEntity;
    }
}
