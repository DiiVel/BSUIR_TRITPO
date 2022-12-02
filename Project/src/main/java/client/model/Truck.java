package client.model;

import client.model.remote.RemoteHandler;
import client.model.remote.RemoteJTPHandler;
import globalmodels.Cargo;
import globalmodels.TruckInformation;
import utils.Event;

import java.io.IOException;

import static utils.Constants.ON_ROUTE_STATUS;
import static utils.Constants.PROCEEDING_STATUS;

public class Truck {

    public Event updateInfo = new Event();
    public Event noCargoes = new Event();
    public Event successfulToPort = new Event();
    private SimpleTableModel table = new SimpleTableModel();
    private RemoteHandler remote;
    private TruckInformation info = new TruckInformation();

    public Truck() throws IOException {
        remote = new RemoteJTPHandler(this);
        info.setToken(remote.getToken());
    }

    public TruckInformation getInfo() {
        info.setCargoes(table.getItems());
        return info;
    }

    public SimpleTableModel getTable() {
        return table;
    }

    public void moveToPort(String status, boolean priority) {//действия по кнопке
        if (table.getRowCount() == 0) {
            noCargoes.notifyObservers();
            return;
        }
        for (Cargo item : table.getItems()) {
            if (item.getWeight() == null || item.getType() == null) {
                noCargoes.notifyObservers();
                return;
            }
        }
        info.setPriority(priority);
        info.setCargoes(table.getItems());
        table.lock();
        info.setStatus(status);
        remote.incomingMessage(info);
        info.setStatus(PROCEEDING_STATUS);
        updateInfo.notifyObservers(info);
        successfulToPort.notifyObservers();
    }

    public void finishLoading() {
        remote.loadFinished(info);
        table.clearTable();
        info.clearCargoes();
        info.setStatus(ON_ROUTE_STATUS);
        info.setPriority(false);
        table.unlock();
        updateInfo.notifyObservers(info);
    }

    public RemoteHandler getRemote() {
        return remote;
    }
}
