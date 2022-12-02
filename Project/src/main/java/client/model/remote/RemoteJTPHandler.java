package client.model.remote;

import client.model.Truck;
import globalmodels.TruckInformation;
import jtp.JTPClient;
import jtp.JTPMessage;

import java.io.IOException;

import static utils.Constants.FINISHED;
import static utils.Constants.INCOMING;

public class RemoteJTPHandler implements RemoteHandler {//отвечает за отправку и получение смс

    private Truck truck;

    private JTPClient client;

    public RemoteJTPHandler(Truck truck) throws IOException {
        this.truck = truck;
        client = new JTPClient();
        initEndpoints();
        client.start();
    }

    private void initEndpoints() {

    }

    @Override
    public String getToken() {
        return client.getToken();
    }

    @Override
    public void incomingMessage(TruckInformation ship) {
        client.sendMessage(JTPMessage.createMessage(INCOMING, ship));
    }

    @Override
    public void loadFinished(TruckInformation info) {
        client.sendMessage(JTPMessage.createMessage(FINISHED, info));
    }

}
