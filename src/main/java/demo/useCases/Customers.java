package demo.useCases;

import demo.entities.Customer;
import demo.entities.Store;
import demo.persistence.CustomerDAO;
import demo.persistence.StoreDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.List;
import java.util.Map;

@Named
@RequestScoped
public class Customers {
    @Inject
    private CustomerDAO customerDAO;

    @Resource
    private UserTransaction utx;
    @Inject
    private StoreDAO storeDAO;

    @Getter @Setter
    private Store store;

    @Getter
    @Setter
    private Customer customerToCreate = new Customer();

    @Getter
    @Setter
    private Customer customer;

    @Getter
    private List<Customer> allCustomers;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        long storeId = Long.parseLong(requestParameters.get("storeId"));
        this.store = storeDAO.findOne(storeId);
    }

//    @Transactional
//    public void createCustomer(){
//        customerToCreate.setStore(this.store);
//        this.customerDAO.persist(customerToCreate);
//    }
    public void createCustomer(){
        try {
            utx.begin();
            customerToCreate.setStore(this.store);
            this.customerDAO.persist(customerToCreate);
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }
    private void loadAllCustomers(){
        this.allCustomers = customerDAO.loadAll();
    }
}
