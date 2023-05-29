package demo.persistence;

import demo.entities.Customer;
import demo.entities.Store;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class StoreDAO implements IStoreDAO{
    @Inject
    private EntityManager em;

    public List<Store> loadAll() {
        return em.createNamedQuery("Store.findAll", Store.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Store Store){
        this.em.persist(Store);
    }

    public Store findOne(Long id) {
        return em.find(Store.class, id);
    }
}
