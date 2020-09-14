package com.ygordy.stocks.controller;

import com.ygordy.stocks.service.DowJonesStockService;
import com.ygordy.stocks.util.HttpHeadersBuilder;
import com.ygordy.stocks.util.StocksAppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class DowJonesStockController {

    @Autowired
    DowJonesStockService dowJonesStockService;

    @PostMapping("/djstock/bulkupload")
    public ResponseEntity bulkStockUpload(@RequestParam("file") MultipartFile multipartFile,
                                          @RequestParam("setupByUserId") String userId) {
        try {
            dowJonesStockService.saveBulkStocks(userId, multipartFile);
            String message = "Uploaded the file successfully: " + multipartFile.getOriginalFilename();
            return ResponseEntity.created(new URI("/api/djstock/bulkupload"))
                    .headers(HttpHeadersBuilder.createEntityCreatedAlert(StocksAppConstants.DJ_ENTITY_NAME, "Uploaded the file successfully"))
                    .body(message);
        } catch (Exception e) {
            String message = "Could not upload the file: " + multipartFile.getOriginalFilename() + "!";
            return ResponseEntity.badRequest()
                    .headers(HttpHeadersBuilder.createFailureAlert(StocksAppConstants.DJ_ENTITY_NAME, "failed",
                            "Failed to save bulk records of DowJonesStock"))
                    .body(message);
        }
    }
}
