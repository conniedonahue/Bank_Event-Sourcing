package dev.conniedonahue.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ping {
    private final String serverTime;

    // Constructor
    public Ping() {
        this.serverTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    // Getter
    public String getServerTime() {
        return serverTime;
    }

}