package com.ygordy.stocks.repository;

import com.ygordy.stocks.model.DowJonesStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DowJonesStockRepository extends JpaRepository<DowJonesStockEntity, Long> {

    List<DowJonesStockEntity> findAllByStockSymbol(String stockSymbol);

}
