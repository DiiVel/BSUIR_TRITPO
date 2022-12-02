package client.controller;

import client.model.Truck;
import client.model.SimpleTableModel;
import globalmodels.TruckInformation;

public class MainController {//обрабатывает действие пользователя
    private Truck truck;

    public MainController(Truck truck) {
        this.truck = truck;
    }

    public SimpleTableModel getSimpleTableModel() {
        return truck.getTable();
    }

    public void addButtonClicked() {
        truck.getTable().addRow();
    }

    public void portButtonClicked(String status, boolean priority) {
        truck.moveToPort(status, priority);
    }

    public void finishDownload() {
        truck.finishLoading();
    }

    public TruckInformation informationButtonClicked() {
        return truck.getInfo();
    }

}
