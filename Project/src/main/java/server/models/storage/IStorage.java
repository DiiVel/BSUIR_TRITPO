package server.models.storage;

import globalmodels.Cargo;

public interface IStorage {

    void put(Cargo cargo);

    boolean get(Cargo cargo);
}
