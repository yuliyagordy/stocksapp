package com.ygordy.stocks.util;

import org.springframework.http.HttpHeaders;

public class HttpHeadersBuilder {

    public HttpHeadersBuilder() {
    }

    public static HttpHeaders createAlert(String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-canissueApp-alert", message);
        headers.add("X-canissueApp-params", param);
        return headers;
    }

    public static HttpHeaders createEntityCreatedAlert(String entityName, String param) {
        return createAlert(StocksAppConstants.APPLICATION_NAME + "." + entityName + ".created", param);
    }

    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-canissueApp-error", "error." + errorKey);
        headers.add("X-canissueApp-params", entityName + " " + defaultMessage);
        return headers;
    }

    public static HttpHeaders createWarningAlert(String entityName, String warningKey, String defaultMessage) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-canissueApp-alert", "alert." + warningKey);
        headers.add("X-canissueApp-params", entityName + " " + defaultMessage);
        return headers;
    }

    public static HttpHeaders createGetStockListAlert(String entityName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-canissueApp-params", entityName);
        return headers;
    }
}
