package demo.persistence;

import demo.entities.Customer;
import demo.entities.Store;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.util.List;

@Decorator
public abstract class StoreDAODecorator implements IStoreDAO {

    @Inject
    @Delegate
    private StoreDAO dao;

    @Override
    public List<Store> loadAll() {
        System.out.println("Loading all stores!");
        return dao.loadAll();
    }

    // Override other methods as necessary
}
