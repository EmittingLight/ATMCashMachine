package com.company;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private String keyword;
    private int pinCode;
    private int numberCard;
    private int balance;
    private boolean isBlocked = false;
    private int attemptCount = 0;
    private List<String> log = new ArrayList<>();

    public BankAccount(String keyword, int pinCode, int numberCard, int balance) {
        this.keyword = keyword;
        this.pinCode = pinCode;
        this.numberCard = numberCard;
        this.balance = balance;
    }
    public String getLog() {
        String text = "";
        for (String s : log) {
            text += s + "\n";

        }
        return text;
    }

    public void addLog(String text) {
        log.add(text);
    }

    public int getBalance() {
        return balance;
    }

    public int getPinCode() {
        return pinCode;
    }

    public int getNumberCard() {
        return numberCard;
    }

    @Override
    public String toString() {
        return "Номер карты: " + numberCard + "\nПинкод: " + pinCode + "\nБаланс: " + balance + "\nКодовое слово: " + keyword;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public void setBlocked(boolean b) {
        isBlocked = b;
    }

    public String getKeyword() {
        return keyword;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void increaseAttempts() {
        attemptCount++;
    }

    public void resetAttempts() {
        attemptCount = 0;
    }

    public int getAttemptCount() {
        return attemptCount;
    }
}
