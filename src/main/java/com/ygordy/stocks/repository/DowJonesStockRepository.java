package com.ygordy.stocks.repository;

import com.ygordy.stocks.model.DowJonesStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DowJonesStockRepository extends JpaRepository<DowJonesStockEntity, Long> {
}
