package com.company;

import javax.swing.*;

public class Controller {
    private View view;
    private Bank bank;
    private int cardNum;
    private State state;


    public void setView(View view) {
        this.view = view;
    }

    public void start() {
        bank = new Bank();
        view.create();
        setState(State.START);

    }

    public void handleAddAccountButtonClick() {
        String keyword = JOptionPane.showInputDialog("Введите кодовое слово");
        if (keyword == null) {
            return;
        }
        String str = bank.createAccount(keyword);
        view.setTextArea(str);
    }

    public void handleEnterCardButtonClick() {
        String numberCard = JOptionPane.showInputDialog("Введите номер карты");
        if (numberCard == null) {
            return;
        }
        cardNum = Integer.parseInt(numberCard);
        if (!bank.isCardExists(cardNum)) {
            view.setTextArea("Такой карты нет");
            return;
        }
        if (bank.isCardBlocked(cardNum)) {
            view.setTextArea("Карта заблокированна");
            return;
        }
        setState(State.PIN);

    }

    public void handleEnterPinButtonClick() {
        String numberPin = JOptionPane.showInputDialog("Введите Пинкод");
        if (numberPin == null) {
            return;
        }
        boolean pinCorrect = bank.isPinCorrect(cardNum, Integer.parseInt(numberPin));
        if (pinCorrect) {
            view.setTextArea("Пинкод верный");
            setState(State.MENU);
        } else {
            view.setTextArea("Пин код неверный");
            if (bank.isCardBlocked(cardNum)) {
                setState(State.START);
            }
        }
    }

    public void handleBlockCardButtonClick() {
        if (state == State.MENU) {
            bank.blockCard(cardNum);
            setState(State.START);
            return;
        }
        String answer = JOptionPane.showInputDialog("Введите номер карты");
        if (answer == null) {
            return;
        }
        int numCard = Integer.parseInt(answer);
        if (!bank.isCardExists(numCard)) {
            view.setTextArea("No card");
            return;
        }
        bank.blockCard(numCard);
        view.setTextArea("The card has been blocked");
    }

    public void handleUnblockCardButtonClick() {
        String answer = JOptionPane.showInputDialog("Введите номер карты");
        if (answer == null) {
            return;
        }
        int numCard = Integer.parseInt(answer);
        if (!bank.isCardExists(numCard)) {
            view.setTextArea("No card");
            return;
        }
        answer = JOptionPane.showInputDialog("Введите кодовое слово");
        if (answer == null) {
            return;
        }
        if (!bank.isKeywordCorrect(numCard, answer)) {
            view.setTextArea("Wrong keyword");
            return;
        }
        bank.unblockCard(numCard);
        view.setTextArea("Card unblock");
    }

    public void handleEjectCardButtonClick() {
        setState(State.START);
    }

    public void handleShowBalanceButtonClick() {
        view.setTextArea(String.valueOf(bank.getBalance(cardNum)));
    }

    public void handleAddMoneyBalanceButtonClick() {
        String addBalance = JOptionPane.showInputDialog("Пополнить баланс");
        if (addBalance == null) {
            return;
        }
        bank.addBalance(cardNum, Integer.parseInt(addBalance));
    }

    public void handleWithdrawCashButtonClick() {
        String withdraw = JOptionPane.showInputDialog("Введите сумму");
        if (withdraw == null) {
            return;
        }
        if (bank.getBalance(cardNum) >= Integer.parseInt(withdraw)) {
            bank.getCash(cardNum, Integer.parseInt(withdraw));
            view.setTextArea("Заберите деньги");
        } else {
            view.setTextArea("Нет денег на счету");
        }
    }

    public void handleTransferMoneyButtonClick() {
        String answer = JOptionPane.showInputDialog("Введите номер карты");
        if (answer == null) {
            return;
        }
        int transferCardTo = Integer.parseInt(answer);
        if (!bank.isCardExists(transferCardTo)) {
            view.setTextArea("Такой карты нет");
            return;
        }
        answer = JOptionPane.showInputDialog("Введите сумму для перевода");
        if (answer == null) {
            return;
        }
        int sum = Integer.parseInt(answer);

        if (bank.getBalance(cardNum) < sum) {
            view.setTextArea("Недостаточно средств");
            return;
        }
        bank.transferMoney(cardNum, transferCardTo, sum);
        view.setTextArea("Операция выполнена");

    }

    public void handleChangePinButtonClick() {
        String pinAnswer = JOptionPane.showInputDialog("Введите новый пинкод");
        if (pinAnswer == null) {
            return;
        }
        int newPin = Integer.parseInt(pinAnswer);
        if (!bank.isPinFormatCorrect(newPin)) {
            view.setTextArea("Пинкод неверный");
            return;
        }
        bank.setNewPin(cardNum, newPin);
        view.setTextArea("Пинкод изменен");
    }

    private void setState(State state) {
        this.state = state;
        view.setState(state);
    }
    public void handleHistoryButtonClick() {
        view.setTextArea(bank.getLog(cardNum));
    }
}
