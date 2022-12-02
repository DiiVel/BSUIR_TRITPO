package utils;

import server.models.Informative;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utils {

    private Utils() {
    }

    public static Dimension scale(Dimension src, double scale) {//исходный размер берёт и масштабирует
        return new Dimension((int) (src.width * scale), (int) (src.height * scale));
    }

    public static void log(Informative arg) {//сохраняет информацию с указанием времени
        System.out.println(new SimpleDateFormat("HH:mm:ss.SSS").format(Calendar.getInstance().getTime()) + " - " + arg.getInfo());
    }


}
