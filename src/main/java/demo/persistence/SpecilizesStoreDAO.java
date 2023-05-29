package demo.persistence;

import demo.entities.Store;

import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Specializes
public class SpecilizesStoreDAO extends StoreDAO {

    @Inject
    public EntityManager em;
    @Override
    public List<Store> loadAll() {
        return em.createNamedQuery("Store.findAllWithNonNullNameAndAddress", Store.class).getResultList();
    }
}
