package net.aya.angular.web;


public class BalanceNotSufficientException extends Exception {
    public BalanceNotSufficientException(String message) {
        super(message);
    }
}