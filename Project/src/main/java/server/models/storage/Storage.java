package server.models.storage;

import globalmodels.Cargo;

public class Storage implements IStorage {

    @Override
    public void put(Cargo cargo) {
        System.out.println("PUT " + cargo);

    }

    @Override
    public boolean get(Cargo cargo) {
        System.out.println("GET " + cargo);
        return true;
    }
}
