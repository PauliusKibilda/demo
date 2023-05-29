package demo.persistence;
import demo.entities.Store;

import java.util.List;

public interface IStoreDAO {
    List<Store> loadAll();
    void persist(Store store);
    Store findOne(Long id);
}
