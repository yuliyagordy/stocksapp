package com.ygordy.stocks.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * A Data Transfer Object for the DowJonesStock entity.
 */
@Getter
@Setter
public class DowJonesStock implements Serializable {

    private Long id;
    private Long setupByUserId;
    private LocalDateTime modifiedDate;
    private Short quarter;
    private String stockSymbol;
    private Date lastBusDate;
    private Double openPrice;
    private Double highPrice;
    private Double lowPrice;
    private Double closePrice;
    private Double sharesNumber;
    private Double percentChangePrice;
    private Double percentChangeSharesNumber;
    private Double previousWeeksShares;
    private Double nextWeekOpenPrice;
    private Double nextWeekClosePrice;
    private Double nextWeekChangePrice;
    private Integer daysToNextDividend;
    private Double percentReturnNextDividend;

    public DowJonesStock() {
    }

    public DowJonesStock(Long id, Long setupByUserId, LocalDateTime modifiedDate) {
        this.id = id;
        this.setupByUserId = setupByUserId;
        this.modifiedDate = modifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DowJonesStock dowJonesStock = (DowJonesStock) o;
        if (dowJonesStock.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dowJonesStock.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StockDTO{"
                + "id=" + getId()
                + ", quarter='" + getQuarter() + "'"
                + ", stockSymbol='" + getStockSymbol() + "'"
                + "}";
    }
}
