package com.company;

import javax.swing.*;

public class View {
    private Controller controller;
    private JTextArea screenArea;
    private JButton addAccountButton;
    private JButton showBalanceButton;
    private JButton withdrawCashButton;
    private JButton changePinButton;
    private JButton blockCardButton;
    private JButton enterCardButton;
    private JButton enterPinButton;
    private JButton addMoneyButton;
    private JButton transferMoneyButton;
    private JButton unblockCardButton;
    private JButton ejectCardButton;
    private JButton historyCardButton;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void create() {
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        addAccountButton = new JButton("Создать счет");
        addAccountButton.setBounds(5, 150, 200, 30);
        addAccountButton.addActionListener(e -> controller.handleAddAccountButtonClick());
        frame.add(addAccountButton);

        showBalanceButton = new JButton("Показать баланс");
        showBalanceButton.setBounds(5, 180, 200, 30);
        showBalanceButton.addActionListener(e -> controller.handleShowBalanceButtonClick());
        frame.add(showBalanceButton);

        withdrawCashButton = new JButton("Выдать наличные");
        withdrawCashButton.setBounds(5, 210, 200, 30);
        withdrawCashButton.addActionListener(e -> controller.handleWithdrawCashButtonClick());
        frame.add(withdrawCashButton);

        changePinButton = new JButton("Изменить ПинКод");
        changePinButton.setBounds(5, 240, 200, 30);
        changePinButton.addActionListener(e -> controller.handleChangePinButtonClick());
        frame.add(changePinButton);

        blockCardButton = new JButton("Заблокировать карту");
        blockCardButton.setBounds(5, 270, 200, 30);
        blockCardButton.addActionListener(e -> controller.handleBlockCardButtonClick());
        frame.add(blockCardButton);

        enterCardButton = new JButton("Введите номер карты");
        enterCardButton.setBounds(205, 150, 175, 30);
        enterCardButton.addActionListener(e -> controller.handleEnterCardButtonClick());
        frame.add(enterCardButton);

        enterPinButton = new JButton("Введите Пинкод");
        enterPinButton.setBounds(205, 180, 175, 30);
        enterPinButton.addActionListener(e -> controller.handleEnterPinButtonClick());
        frame.add(enterPinButton);

        addMoneyButton = new JButton("Пополнить баланс");
        addMoneyButton.setBounds(205, 210, 175, 30);
        addMoneyButton.addActionListener(e -> controller.handleAddMoneyBalanceButtonClick());
        frame.add(addMoneyButton);

        transferMoneyButton = new JButton("Перевести деньги");
        transferMoneyButton.setBounds(205, 240, 175, 30);
        transferMoneyButton.addActionListener(e -> controller.handleTransferMoneyButtonClick());
        frame.add(transferMoneyButton);

        unblockCardButton = new JButton("Разблокировать карту");
        unblockCardButton.setBounds(205, 270, 175, 30);
        unblockCardButton.addActionListener(e -> controller.handleUnblockCardButtonClick());
        frame.add(unblockCardButton);

        ejectCardButton = new JButton("Закончить операции с картой");
        ejectCardButton.setBounds(5, 300, 375, 30);
        ejectCardButton.addActionListener(e -> controller.handleEjectCardButtonClick());
        frame.add(ejectCardButton);

        historyCardButton = new JButton("История операций с картой");
        historyCardButton.setBounds(5, 330, 375, 30);
        historyCardButton.addActionListener(e -> controller.handleHistoryButtonClick());
        frame.add(historyCardButton);

        screenArea = new JTextArea();
        screenArea.setBounds(5, 5, 375, 145);
        frame.add(screenArea);

        frame.setVisible(true);
    }

    // метод для установки текста в окно
    public void setTextArea(String string) {
        screenArea.setText(string);
    }

    // Устанавливаю состояние кнопок.
    public void setState(State state) {
        addAccountButton.setEnabled(false);
        showBalanceButton.setEnabled(false);
        withdrawCashButton.setEnabled(false);
        changePinButton.setEnabled(false);
        blockCardButton.setEnabled(false);
        enterCardButton.setEnabled(false);
        ejectCardButton.setEnabled(false);
        enterPinButton.setEnabled(false);
        transferMoneyButton.setEnabled(false);
        unblockCardButton.setEnabled(false);
        addMoneyButton.setEnabled(false);
        historyCardButton.setEnabled(false);

        if (state == State.START) {
            enableButtons(addAccountButton, enterCardButton, blockCardButton, unblockCardButton);
            setTextArea("ДОБРО ПОЖАЛОВАТЬ!");
        }
        if (state == State.PIN) {
            enableButtons(enterPinButton, ejectCardButton);
            setTextArea("Введите пинкод");
        }
        if (state == State.MENU) {
            enableButtons(showBalanceButton, withdrawCashButton, changePinButton, transferMoneyButton, addMoneyButton, blockCardButton, ejectCardButton, historyCardButton);
            setTextArea("Выберите операцию");
        }
    }

    // метод с любым кол-вом кнопок.
    private void enableButtons(JButton... buttons) {
        for (JButton button : buttons) {
            button.setEnabled(true);
        }
    }
}
