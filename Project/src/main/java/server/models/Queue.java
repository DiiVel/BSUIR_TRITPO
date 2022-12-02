package server.models;

import globalmodels.TruckInformation;

import java.util.Comparator;
import java.util.LinkedList;

public class Queue implements Informative {
    private LinkedList<TruckInformation> ships = new LinkedList<>();


    public boolean isEmpty(){
        return ships.isEmpty();
    }

    private static Integer shipToInt(TruckInformation ship) {//возвращает номер статуса
        if (ship.isPriority() && !ship.isOffense()) {
            return 1;
        }
        if (ship.isPriority() && ship.isOffense()) {
            return 2;
        }
        if (!ship.isPriority() && !ship.isOffense()) {
            return 3;
        }
        return 4;
    }

    public void add(TruckInformation ship) {
        ships.addLast(ship);
        sort();
    }

    public TruckInformation pop() {
        return ships.pollFirst();
    }

    private void sort() {
        ships.sort(Comparator.comparing(Queue::shipToInt));//return shipToInt(ship1).compareTo(shipToInt(ship2));
    }


    @Override
    public String getInfo() {
        if (ships.isEmpty()) {
            return "Очередь  пуста";
        }

        var builder = new StringBuilder(String.format("Размер очереди: %d\n", ships.size()));
        for (int i = 0; i < ships.size(); i++) {
            TruckInformation ship = ships.get(i);
            builder.append(String.format("Корабаль %d: %s\n", i + 1, ship.getInfo()));
        }
        return builder.toString();
    }
}
