package net.aya.angular.services;

public class CustumorNotFoundException extends RuntimeException {
    public CustumorNotFoundException(String message) {
        super(message);
    }
}
