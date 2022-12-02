package server.presentation;

import server.controller.MainController;
import server.models.Custom;
import utils.Window;

import javax.swing.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainWindow extends Window {
    public static final int PERIOD = 5000;
    MainController controller;
    private JPanel panel;
    private JTextArea textArea;

    public MainWindow(Custom custom) {
        super("server");
        controller = new MainController(custom);
        init(panel);
        Timer updateTimer = new Timer();
        updateTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                System.out.println("UPDATE");
                textArea.setText(custom.getInfo());
            }
        }, 0, PERIOD);

    }

    public static void main(String[] args) {
        Custom custom = null;
        String mooring = JOptionPane.showInputDialog(null, "Введите количество пунктов таможенной проверки");
        while (!mooring.matches("[1-9]\\d*")) {
            mooring = JOptionPane.showInputDialog(null, "Правильно введите количество пунктов таможенной проверки");
        }
        try {
            custom = new Custom(Integer.parseInt(mooring));
            new MainWindow(custom);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initController() {

    }

    @Override
    protected void initObservers() {

    }
}
