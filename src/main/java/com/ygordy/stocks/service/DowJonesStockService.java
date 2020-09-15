package com.ygordy.stocks.service;

import com.ygordy.stocks.model.DowJonesStockEntity;
import com.ygordy.stocks.repository.DowJonesStockRepository;
import com.ygordy.stocks.service.dto.DowJonesStock;
import com.ygordy.stocks.service.dtoconverter.DowJonesConverterToDTO;
import com.ygordy.stocks.service.dtoconverter.DowJonesConverterToEntity;
import com.ygordy.stocks.util.CSVFileHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DowJonesStockService {
    private static final Logger log = LoggerFactory.getLogger(DowJonesStockService.class);

    @Autowired
    private final DowJonesStockRepository dowJonesStockRepository;
    @Autowired
    private final DowJonesConverterToDTO dowJonesConverterToDTO;
    @Autowired
    private final DowJonesConverterToEntity dowJonesConverterToEntity;
    @Autowired
    private final CSVFileHelper csvFileHelper;
    @Autowired
    public DowJonesStockService(DowJonesStockRepository dowJonesStockRepository,
                                DowJonesConverterToDTO dowJonesConverterToDTO,
                                DowJonesConverterToEntity dowJonesConverterToEntity,
                                CSVFileHelper csvFileHelper) {
        this.dowJonesStockRepository = dowJonesStockRepository;
        this.dowJonesConverterToDTO = dowJonesConverterToDTO;
        this.dowJonesConverterToEntity = dowJonesConverterToEntity;
        this.csvFileHelper = csvFileHelper;
    }


    public void saveBulkStocks(String userIdStr, MultipartFile multipartFile) {
        try {
            List<DowJonesStockEntity> dowJonesStockEntities = csvFileHelper
                    .csvToDowJonesStockEntities(userIdStr, multipartFile.getInputStream());
            dowJonesStockRepository.saveAll(dowJonesStockEntities);
        } catch (Exception e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public List<DowJonesStock> findStockListBySymbol(String symbolId) {
        List<DowJonesStockEntity> stockEntityList = dowJonesStockRepository.findAllByStockSymbol(symbolId);
        List<DowJonesStock> dowJonesStockList = null;
        if (stockEntityList != null) {
            dowJonesStockList = new ArrayList();
            for (DowJonesStockEntity stockEntity : stockEntityList) {
                dowJonesStockList.add(dowJonesConverterToDTO.apply(stockEntity));
            }
        }
        return dowJonesStockList;
    }


    public DowJonesStock addDowJonesStock(String userIdStr, DowJonesStock dowJonesStock) {
        DowJonesStockEntity dowJonesStockEntity = dowJonesConverterToEntity.apply(dowJonesStock);
        LocalDateTime now = LocalDateTime.now();
        dowJonesStockEntity.setModifiedDate(now);
        Long userId = Long.parseLong(userIdStr);
        dowJonesStockEntity.setSetupByUserId(userId);
        DowJonesStockEntity newDowJonesStockEntity = dowJonesStockRepository.save(dowJonesStockEntity);
        if (newDowJonesStockEntity != null) {
            return dowJonesConverterToDTO.apply(newDowJonesStockEntity);
        }
        return null;
    }
}
