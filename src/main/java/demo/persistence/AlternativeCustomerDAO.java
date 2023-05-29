package demo.persistence;

import demo.entities.Customer;
import demo.entities.Store;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@Alternative
@ApplicationScoped
public class AlternativeCustomerDAO implements ICustomerDAO{
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

    public Customer findOne(Long id) {
        return em.find(Customer.class, id);
    }
    public List<Customer> findByStoreId(Long storeId) {
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findCorrectByStoreId", Customer.class);
        query.setParameter("storeId", storeId);
        return query.getResultList();
    }
    public Customer update(Customer customer){
        return em.merge(customer);
    }

    @Override
    public Store getStoreById(long storeId) {
        TypedQuery<Store> query = em.createNamedQuery("Store.findByStoreId", Store.class);
        query.setParameter("storeId", storeId);
        Store store = query.getSingleResult();

        List<Customer> customers = store.getCustomers().stream()
                .filter(c -> c.getPhone() != null && !c.getPhone().isEmpty())
                .collect(Collectors.toList());

        store.setCustomers(customers);

        return store;
    }
}
