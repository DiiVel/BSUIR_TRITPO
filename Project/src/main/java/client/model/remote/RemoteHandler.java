package client.model.remote;

import globalmodels.TruckInformation;

public interface RemoteHandler {

    void incomingMessage(TruckInformation ship);

    void loadFinished(TruckInformation ship);

    String getToken();

}
