package server.models;

import globalmodels.TruckInformation;
import server.models.remote.IRemoteHandler;
import server.models.remote.RemoteJPTHandler;
import server.models.storage.Storage;
import utils.Utils;

import java.io.IOException;

public class Custom implements Informative {

    private Mooring[] moorings;

    private Storage storage = new Storage();

    private Queue queue = new Queue();

    private IRemoteHandler remote;

    public Custom(int moorings) throws IOException {
        remote = new RemoteJPTHandler(this);
        this.moorings = new Mooring[moorings];
        for (int i = 0; i < moorings; i++) {
            this.moorings[i] = new Mooring();
        }
    }

    public void incomingShip(TruckInformation ship) {
        Utils.log(ship);
        for (Mooring mooring : moorings) {
            if (mooring.isEmpty()) {
                mooring.setTruckInformation(ship);
                for (int i = 0; i < ship.getCargoes().size(); i++) {
                    storage.put(ship.getCargoes().get(i));
                }

                return;
            }
        }
        queue.add(ship);
    }


    public void loadedFinished(TruckInformation ship) {
        Utils.log(ship);
        for (Mooring mooring : moorings) {
            if (mooring.getTruckInformation().getToken().equals(ship.getToken())) {
                mooring.free();
                //TODO("ship is free")

                if (!queue.isEmpty()) {
                    mooring.setTruckInformation(queue.pop());
                }
                //прочая логика
                return;
            }
        }
        System.out.println("ERROR");
    }

    @Override
    public String getInfo() {
        var builder = new StringBuilder("Порт\nПричалы:\n");
        for (int i = 0; i < moorings.length; i++) {
            Mooring mooring = moorings[i];
            builder.append(String.format("Причал %d: %s", i + 1, mooring.getInfo()));
        }
        builder.append(String.format("Очередь: %s", queue.getInfo()));
        return builder.toString();
    }
}
