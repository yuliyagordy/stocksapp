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
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        System.out.println("DowJonesStockControllerTest setup");
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of createStock method, of class DowJonesStockController.
     */
    @Test
    public void testCreateStock() throws Exception {
        System.out.println("createStock() ");
        DowJonesStock dowJonesStock = new DowJonesStock();
        dowJonesStock.setSetupByUserId(1L);
        dowJonesStock.setQuarter((short)1);
        LocalDate ld = LocalDate.of(2020, 10, 10);
        Date date = Date.from(ld.atStartOfDay().toInstant(ZoneOffset.UTC));
        dowJonesStock.setLastBusDate(date);
        dowJonesStock.setStockSymbol("WOW");


        DowJonesStockEntity dowJonesStockEntity = dowJonesConverterToEntity.apply(dowJonesStock);
        dowJonesStockEntity.setId(1L);
        LocalDateTime ldt = ld.atTime(1, 10);
        dowJonesStockEntity.setModifiedDate(ldt);

        when(dowJonesStockRepository.save(any())).thenReturn(dowJonesStockEntity);

        ResponseEntity<DowJonesStock> result = dowJonesStockController.createStock("1", dowJonesStock);
        DowJonesStock dtoResult = result.getBody();
        DowJonesStock expectedDTO = dowJonesConverterToDTO.apply(dowJonesStockEntity);
        assertEquals(dtoResult, expectedDTO);
    }

}
