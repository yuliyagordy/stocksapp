package com.ygordy.stocks.controller;

import com.ygordy.stocks.service.DowJonesStockService;
import com.ygordy.stocks.service.dto.DowJonesStock;
import com.ygordy.stocks.util.HttpHeadersBuilder;
import com.ygordy.stocks.util.StocksAppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DowJonesStockController {
    private static final Logger log = LoggerFactory.getLogger(DowJonesStockController.class);

    @Autowired
    DowJonesStockService dowJonesStockService;

    public DowJonesStockController(DowJonesStockService dowJonesStockService) {
        this.dowJonesStockService = dowJonesStockService;
    }

    @PostMapping("/djstock/bulkupload")
    public ResponseEntity bulkStockUpload(@RequestParam("file") MultipartFile multipartFile,
                                          @RequestParam("setupByUserId") String userId) {
        log.debug("bulkStockUpload() setupByUserId=["+ userId + "]");
        try {
            dowJonesStockService.saveBulkStocks(userId, multipartFile);
            String message = "Uploaded the file successfully: " + multipartFile.getOriginalFilename();
            return ResponseEntity.created(new URI("/api/djstock/bulkupload"))
                    .headers(HttpHeadersBuilder.createEntityCreatedAlert(StocksAppConstants.DJ_ENTITY_NAME,
                            "Uploaded the file successfully"))
                    .body(message);
        } catch (Exception e) {
            String message = "Could not upload the file: " + multipartFile.getOriginalFilename() + "!";
            return ResponseEntity.badRequest()
                    .headers(HttpHeadersBuilder.createFailureAlert(StocksAppConstants.DJ_ENTITY_NAME, "failed",
                            "Failed to save bulk records of DowJonesStock"))
                    .body(message);
        }
    }

    @GetMapping("/djstock/list/{symbolId}")
    public ResponseEntity<List<DowJonesStock>> getStockList(@RequestParam("setupByUserId") String userId,
                                                            @PathVariable String symbolId) throws Exception {
        log.debug("getStockList() setupByUserId=["+ userId + "]");
        List<DowJonesStock> dowJonesStockList = dowJonesStockService.findStockListBySymbol(symbolId);
        if (dowJonesStockList == null || dowJonesStockList.size() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .headers(HttpHeadersBuilder.createWarningAlert(StocksAppConstants.DJ_ENTITY_NAME, "null",
                            "List empty"))
                    .body(null);
        }
        return ResponseEntity.created(new URI("/api/djstock/list"))
                .headers(HttpHeadersBuilder.createGetStockListAlert(StocksAppConstants.DJ_ENTITY_NAME))
                .body(dowJonesStockList);
    }

    @PostMapping("/djstock/create")
    public ResponseEntity<DowJonesStock> createStock(@RequestParam("setupByUserId") String userId,
                                                     @RequestBody DowJonesStock dowJonesStock) throws Exception {
        log.debug("createStock() setupByUserId=["+ userId + "]");
        DowJonesStock result = dowJonesStockService.addDowJonesStock(userId, dowJonesStock);
        if (result == null) {
            return ResponseEntity.badRequest()
                    .headers(HttpHeadersBuilder.createFailureAlert(StocksAppConstants.DJ_ENTITY_NAME, "failed",
                            "Failed to create new DowJonesStock"))
                    .body(null);
        }
        return ResponseEntity.created(new URI("/api/stock/create" + result.getId()))
                .headers(HttpHeadersBuilder.createEntityCreatedAlert(StocksAppConstants.DJ_ENTITY_NAME,
                        result.getId().toString()))
                .body(result);
    }

}
