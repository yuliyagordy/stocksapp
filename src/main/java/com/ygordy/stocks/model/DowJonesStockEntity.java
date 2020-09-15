package com.ygordy.stocks.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * Entity Class
 */
@Getter
@Setter
@Entity
@Table(name = "DowJonesStock")
public class DowJonesStockEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "setup_by_user_id")
    private Long setupByUserId;

    @Column(name = "modified_date", nullable = false)
    private LocalDateTime modifiedDate;

    @Column(name = "quarter", nullable = false)
    private Short quarter;

    @Column(name = "stock_symbol", nullable = false)
    private String stockSymbol;

    @Column(name = "last_bus_date")
    private Date lastBusDate;

    @Column(name = "open_price")
    private Double openPrice;

    @Column(name = "high_price")
    private Double highPrice;

    @Column(name = "low_price")
    private Double lowPrice;

    @Column(name = "close_price")
    private Double closePrice;

    @Column(name = "shares_number")
    private Double sharesNumber;

    @Column(name = "percent_change_price")
    private Double percentChangePrice;

    @Column(name = "percent_chagne_volume_over_last_wek")
    private Double percentChangeSharesNumber;

    @Column(name = "previous_weeks_volume")
    private Double previousWeeksShares;

    @Column(name = "next_weeks_open_price")
    private Double nextWeekOpenPrice;

    @Column(name = "next_weeks_close_price")
    private Double nextWeekClosePrice;

    @Column(name = "percent_change_next_weeks_price")
    private Double nextWeekChangePrice;

    @Column(name = "days_to_next_dividend")
    private Integer daysToNextDividend;

    @Column(name = "percent_return_next_dividend")
    private Double percentReturnNextDividend;

    public DowJonesStockEntity() {
    }

    public DowJonesStockEntity(Long setupByUserId, LocalDateTime modifiedDate) {
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
        DowJonesStockEntity dowJonesStock = (DowJonesStockEntity) o;
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
        return "DowJonesStockEntity {"
                + "id=" + getId()
                + ", quarter='" + getQuarter() + "'"
                + ", stockSymbol='" + getStockSymbol() + "'"
                + "}";
    }
}
