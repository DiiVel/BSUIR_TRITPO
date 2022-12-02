package utils;

import javax.swing.*;
import java.awt.*;

public abstract class Window extends JFrame {

    public Window(String name) {
        super(name);
    }

    protected void init(JPanel root) {
        var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Utils.scale(screenSize, 0.4));
        setContentPane(root);
        initView();
        initController();
        initObservers();
        setVisible(true);
    }


    /**
     * Используется для настройки элементов формы: выпадающих списков, рендеры таблиц...
     */
    protected abstract void initView();

    /**
     * Используется для настройки событий: нажатие кнопок, жестов
     */
    protected abstract void initController();

    /**
     * Используется для подписи на события модели
     */
    protected abstract void initObservers();


}
