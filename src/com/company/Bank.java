package com.company;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<Integer, BankAccount> accounts = new HashMap<>();


    public String createAccount(String keyword) {
        BankAccount bankAccount = new BankAccount(keyword, generateNumber(), createUniqueCardNumber(), 0);
        accounts.put(bankAccount.getNumberCard(), bankAccount);
        return bankAccount.toString();
    }

    private int createUniqueCardNumber() {
        int cardNum;
        do {
            cardNum = generateNumber();
        } while (accounts.containsKey(cardNum)); // containsKey: проверяет наличие ключа в МАР.
        return cardNum;
    }

    public String getLog(int numCard) {
        return accounts.get(numCard).getLog();
    }

    private int generateNumber() {
        return (int) (Math.random() * 9000) + 1000;
    }

    public int getBalance(int numCard) {
        return accounts.get(numCard).getBalance();

    }

    public boolean isCardExists(int numCard) {
        return accounts.containsKey(numCard);
    }

    public boolean isPinCorrect(int numCard, int pin) {
        BankAccount account = accounts.get(numCard);
        boolean isPinCorrect = account.getPinCode() == pin;
        if (isPinCorrect) {
            account.resetAttempts();
        } else {
            account.increaseAttempts();
            if (account.getAttemptCount() == 3) {
                blockCard(numCard);
            }
        }
        return isPinCorrect;
    }

    public void addBalance(int numCard, int sum) {
        changeBalance(numCard, sum);
        accounts.get(numCard).addLog("Внесение наличных: " + sum);
    }

    public void getCash(int numCard, int sum) {
        changeBalance(numCard, -sum);
        accounts.get(numCard).addLog("Снятие наличных: " + sum);
    }

    private void changeBalance(int numCard, int delta) {
        int newSum = getBalance(numCard) + delta;
        accounts.get(numCard).setBalance(newSum);
    }

    public void transferMoney(int numCardFrom, int numCardTo, int sum) {
        changeBalance(numCardFrom, -sum);
        accounts.get(numCardFrom).addLog("Выполнен перевод суммы " + sum + " на карту " + numCardTo);
        changeBalance(numCardTo, sum);
        accounts.get(numCardTo).addLog("Поступила сумма " + sum + " с карты " + numCardFrom);
    }

    public void setNewPin(int numCard, int newPin) {
        accounts.get(numCard).setPinCode(newPin);
    }

    public boolean isPinFormatCorrect(int pin) {
        return pin >= 1000 && pin <= 9999;
    }

    public void blockCard(int numCard) {
        accounts.get(numCard).setBlocked(true);
    }

    public void unblockCard(int numCard) {
        accounts.get(numCard).setBlocked(false);
    }

    public boolean isKeywordCorrect(int numCard, String keyword) {
        return accounts.get(numCard).getKeyword().equals(keyword);
    }

    public boolean isCardBlocked(int numCard) {
        return accounts.get(numCard).isBlocked();
    }
}
