package com.ygordy.stocks.service.dtoconverter;

import com.ygordy.stocks.model.DowJonesStockEntity;
import com.ygordy.stocks.service.dto.DowJonesStock;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class DowJonesConverterToDTO implements Function<DowJonesStockEntity, DowJonesStock> {
    @Override
    public DowJonesStock apply(DowJonesStockEntity dowJonesStockEntity) {
        DowJonesStock dowJonesStock = new DowJonesStock();
        if (dowJonesStockEntity.getId() != null)
            dowJonesStock.setId(dowJonesStockEntity.getId());
        if (dowJonesStockEntity.getSetupByUserId() != null)
            dowJonesStock.setSetupByUserId(dowJonesStockEntity.getSetupByUserId());
        if (dowJonesStockEntity.getModifiedDate() != null)
            dowJonesStock.setModifiedDate(dowJonesStockEntity.getModifiedDate());
        if (dowJonesStockEntity.getQuarter() != null)
            dowJonesStock.setQuarter(dowJonesStockEntity.getQuarter());
        if (dowJonesStockEntity.getStockSymbol() != null)
            dowJonesStock.setStockSymbol(dowJonesStockEntity.getStockSymbol());
        if (dowJonesStockEntity.getLastBusDate() != null)
            dowJonesStock.setLastBusDate(dowJonesStockEntity.getLastBusDate());
        if (dowJonesStockEntity.getOpenPrice() != null)
            dowJonesStock.setOpenPrice(dowJonesStockEntity.getOpenPrice());
        if (dowJonesStockEntity.getHighPrice() != null)
            dowJonesStock.setHighPrice(dowJonesStockEntity.getHighPrice());
        if (dowJonesStockEntity.getLowPrice() != null)
            dowJonesStock.setLowPrice(dowJonesStockEntity.getLowPrice());
        if (dowJonesStockEntity.getClosePrice() != null)
            dowJonesStock.setClosePrice(dowJonesStockEntity.getClosePrice());
        if (dowJonesStockEntity.getSharesNumber() != null)
            dowJonesStock.setSharesNumber(dowJonesStockEntity.getSharesNumber());
        if (dowJonesStockEntity.getPercentChangePrice() != null)
            dowJonesStock.setPercentChangePrice(dowJonesStockEntity.getPercentChangePrice());
        if (dowJonesStockEntity.getPercentChangeSharesNumber() != null)
            dowJonesStock.setPercentChangeSharesNumber(dowJonesStockEntity.getPercentChangeSharesNumber());
        if (dowJonesStockEntity.getPercentReturnNextDividend() != null)
            dowJonesStock.setPercentReturnNextDividend(dowJonesStockEntity.getPercentReturnNextDividend());
        if (dowJonesStockEntity.getNextWeekOpenPrice() != null)
            dowJonesStock.setNextWeekOpenPrice(dowJonesStockEntity.getNextWeekOpenPrice());
        if (dowJonesStockEntity.getNextWeekClosePrice() != null)
            dowJonesStock.setNextWeekClosePrice(dowJonesStockEntity.getNextWeekClosePrice());
        if (dowJonesStockEntity.getNextWeekChangePrice() != null)
            dowJonesStock.setNextWeekChangePrice(dowJonesStockEntity.getNextWeekChangePrice());
        if (dowJonesStockEntity.getDaysToNextDividend() != null)
            dowJonesStock.setDaysToNextDividend(dowJonesStockEntity.getDaysToNextDividend());
        if (dowJonesStockEntity.getPercentReturnNextDividend() != null)
            dowJonesStock.setPercentReturnNextDividend(dowJonesStockEntity.getPercentReturnNextDividend());
        return dowJonesStock;
    }
}
