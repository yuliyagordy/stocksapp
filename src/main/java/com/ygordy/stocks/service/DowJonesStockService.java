package com.ygordy.stocks.service;

import com.ygordy.stocks.model.DowJonesStockEntity;
import com.ygordy.stocks.repository.DowJonesStockRepository;
import com.ygordy.stocks.service.dtoconverter.DowJonesConverterToDTO;
import com.ygordy.stocks.service.dtoconverter.DowJonesConverterToEntity;
import com.ygordy.stocks.util.CSVFileHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class DowJonesStockService {

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
}
