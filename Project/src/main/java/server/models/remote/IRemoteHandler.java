package server.models.remote;

import globalmodels.TruckInformation;

public interface IRemoteHandler {

    void onIncomingTruck(TruckInformation ship);

    void onLoadingFinished(TruckInformation info);
}
