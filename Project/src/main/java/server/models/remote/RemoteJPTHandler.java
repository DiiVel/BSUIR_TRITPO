package server.models.remote;

import globalmodels.TruckInformation;
import jtp.JTPServer;
import server.models.Custom;

import java.io.IOException;

import static utils.Constants.FINISHED;
import static utils.Constants.INCOMING;

public class RemoteJPTHandler implements IRemoteHandler {

    private Custom custom;

    private JTPServer server;

    public RemoteJPTHandler(Custom custom) throws IOException {
        this.custom = custom;
        server = new JTPServer();
        initEndPoints();
        server.start();
    }

    private void initEndPoints() {//выставляет действия по некоторым событиям
        server.registerInputEndPoint(INCOMING, message ->
                onIncomingTruck(message.getDBody(TruckInformation.class)));
        server.registerInputEndPoint(FINISHED , message -> {
            onLoadingFinished(message.getDBody(TruckInformation.class));
        });
    }

    @Override
    public void onIncomingTruck(TruckInformation ship) {
        custom.incomingShip(ship);
    }

    @Override
    public void onLoadingFinished(TruckInformation info) {
        custom.loadedFinished(info);

    }
}
