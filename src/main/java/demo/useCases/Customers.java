package demo.useCases;

import demo.entities.Customer;
import demo.entities.Store;
import demo.interceptors.LoggedInvocation;
import demo.persistence.ICustomerDAO;
import demo.persistence.IStoreDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.UserTransaction;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Named
@RequestScoped
public class Customers {
    @Inject
    private ICustomerDAO customerDAO;

    @Resource
    private UserTransaction utx;
    @Inject
    private IStoreDAO storeDAO;

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
        this.store = customerDAO.getStoreById(storeId);
    }

//    @Transactional
//    public void createCustomer(){
//        customerToCreate.setStore(this.store);
//        this.customerDAO.persist(customerToCreate);
//    }
    @LoggedInvocation
    public void createCustomer(){
        //asyn.asyncMethod();
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
