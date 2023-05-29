package demo.persistence;

import demo.entities.Customer;
import demo.entities.Store;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public interface ICustomerDAO {
    List<Customer> loadAll();
    void setEm(EntityManager em);
    void persist(Customer customer);
    Customer findOne(Long id);
    List<Customer> findByStoreId(Long storeId);
    Customer update(Customer customer);
    Store getStoreById(long storeId);
}
