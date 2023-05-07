package demo.persistence;

import demo.entities.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class CustomerDAO {
    @Inject
    private EntityManager em;

    public List<Customer> loadAll() {
        return em.createNamedQuery("Customer.findAll", Customer.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Customer customer){
        this.em.persist(customer);
    }

    public Customer findOne(Integer id) {
        return em.find(Customer.class, id);
    }
    public List<Customer> findByStoreId(Long storeId) {
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findByStoreId", Customer.class);
        query.setParameter("storeId", storeId);
        return query.getResultList();
    }
}
