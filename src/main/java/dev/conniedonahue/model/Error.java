package dev.conniedonahue.model;

public class Error {
    private String message;
    private String code;

    // Default no-arg constructor for Jackson deserialization
    public Error() {}

    // Constructor
    public Error(String message) {
        this.message = message;
    }
    // Constructor with both message and code since OpenAPI spec sheet only listed `message` as required.
    public Error(String message, String code) {
        this.message = message;
        this.code = code;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
