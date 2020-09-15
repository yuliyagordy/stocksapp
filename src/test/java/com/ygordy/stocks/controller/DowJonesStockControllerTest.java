package com.ygordy.stocks.controller;

import com.ygordy.stocks.model.DowJonesStockEntity;
import com.ygordy.stocks.repository.DowJonesStockRepository;
import com.ygordy.stocks.service.DowJonesStockService;
import com.ygordy.stocks.service.dto.DowJonesStock;
import com.ygordy.stocks.service.dtoconverter.DowJonesConverterToDTO;
import com.ygordy.stocks.service.dtoconverter.DowJonesConverterToEntity;
import com.ygordy.stocks.util.CSVFileHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DowJonesStockControllerTest {

    private static DowJonesStockRepository dowJonesStockRepository;
    private static DowJonesStockService dowJonesStockService;
    private static DowJonesStockController dowJonesStockController;
    private static DowJonesConverterToEntity dowJonesConverterToEntity;
    private static DowJonesConverterToDTO dowJonesConverterToDTO;
    private static CSVFileHelper csvFileHelper;

    public DowJonesStockControllerTest() {
        dowJonesStockRepository = mock(DowJonesStockRepository.class);
        dowJonesConverterToEntity = new DowJonesConverterToEntity();
        dowJonesConverterToDTO = new DowJonesConverterToDTO();
        csvFileHelper = new CSVFileHelper();
        dowJonesStockService = new DowJonesStockService(dowJonesStockRepository,
                dowJonesConverterToDTO,
                dowJonesConverterToEntity,
                csvFileHelper);
        dowJonesStockController = new DowJonesStockController(dowJonesStockService);
    }

    @BeforeAll
    public static void setUpClass() {
        System.out.println("BeforeAll");
    }

    @AfterAll
    public static void tearDownClass() {
        System.out.println("AfterAll");
    }

    @BeforeEach
    public void setUp() {
        System.out.println("BeforeEach");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("AfterEach");
    }

    /**
     * Test of createStock() method, of class DowJonesStockController.
     */
    @Test
    public void testCreateStock() throws Exception {
        System.out.println("running test_CreateStock() ");
        DowJonesStock dowJonesStock = new DowJonesStock();
        dowJonesStock.setSetupByUserId(1L);
        dowJonesStock.setQuarter((short) 1);
        dowJonesStock.setStockSymbol("WOW");

        DowJonesStockEntity dowJonesStockEntity = dowJonesConverterToEntity.apply(dowJonesStock);
        dowJonesStockEntity.setId(2L);
        LocalDate ld = LocalDate.of(2020, 10, 10);
        LocalDateTime ldt = ld.atTime(1, 10);
        dowJonesStockEntity.setModifiedDate(ldt);

        when(dowJonesStockRepository.save(any())).thenReturn(dowJonesStockEntity);
        ResponseEntity<DowJonesStock> result = dowJonesStockController.createStock("1", dowJonesStock);
        DowJonesStock actualDowJonesStock = result.getBody();
        DowJonesStock expectedDowJonesStock = dowJonesConverterToDTO.apply(dowJonesStockEntity);
        assertEquals(expectedDowJonesStock, actualDowJonesStock);
    }

    /**
     * Test of getStockList() method, of class DowJonesStockController.
     */
    @Test
    void testGetStockList() throws Exception {
        System.out.println("running test_GetStockList() ");
        DowJonesStock djs1 = new DowJonesStock();
        djs1.setId(1L);
        djs1.setSetupByUserId(1L);
        djs1.setQuarter((short) 1);
        djs1.setStockSymbol("WOW");
        DowJonesStock djs2 = new DowJonesStock();
        djs2.setId(1L);
        djs2.setSetupByUserId(1L);
        djs2.setQuarter((short) 1);
        djs2.setStockSymbol("WOW");
        List<DowJonesStock> dowJonesStockList = new ArrayList<>();
        dowJonesStockList.add(djs1);
        dowJonesStockList.add(djs2);

        DowJonesStockEntity djsEntity1 = dowJonesConverterToEntity.apply(djs1);
        DowJonesStockEntity djsEntity2 = dowJonesConverterToEntity.apply(djs2);
        List<DowJonesStockEntity> stockEntityList = new ArrayList<>();
        stockEntityList.add(djsEntity1);
        stockEntityList.add(djsEntity2);

        when(dowJonesStockRepository.findAllByStockSymbol("WOW")).thenReturn(stockEntityList);

        ResponseEntity<List<DowJonesStock>> result = dowJonesStockController.getStockList("1", "WOW");
        List<DowJonesStock> actualDowJonesStockList = result.getBody();
        List<DowJonesStock> expectedDowJonesStockList = new ArrayList<>();
        stockEntityList.forEach(stockEntity -> expectedDowJonesStockList.add(dowJonesConverterToDTO.apply(stockEntity)));

        assertTrue(expectedDowJonesStockList.equals(actualDowJonesStockList));

    }
}
