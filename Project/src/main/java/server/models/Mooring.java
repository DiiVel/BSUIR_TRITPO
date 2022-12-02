package server.models;

import globalmodels.TruckInformation;

public class Mooring implements Informative {

    private TruckInformation truckInformation;

    public boolean isEmpty(){
        return truckInformation ==null;
    }

    public TruckInformation free(){
        var info = truckInformation;
        truckInformation = null;
        return info;
    }

    public TruckInformation getTruckInformation() {
        return truckInformation;
    }

    public void setTruckInformation(TruckInformation truckInformation) {
        this.truckInformation = truckInformation;
    }

    @Override
    public String getInfo() {
        if(isEmpty()){
            return "Причал пуст\n";
        }
        return String.format("Причал занят: %s\n", truckInformation.getInfo());
    }
}
