package demo.useCases;

import demo.entities.Product;
import demo.entities.Store;
import demo.interceptors.LoggedInvocation;
import demo.persistence.IStoreDAO;
import demo.persistence.ProductDAO;
import demo.persistence.StoreDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.List;

@Model
public class Stores {
    @Inject
    private StoreDAO storeDAO;
    @Resource
    private UserTransaction utx;

    @Getter
    @Setter
    private Store storeToCreate = new Store();

    @Getter
    private List<Store> allStores;

    @PostConstruct
    public void init(){
        loadAllStores();
    }

//    @Transactional
//    public void createStore(){
//        this.storeDAO.persist(storeToCreate);
//    }
    @LoggedInvocation
    public void createStore(){
        try {
            utx.begin();
            this.storeDAO.persist(storeToCreate);
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
    private void loadAllStores(){
        this.allStores = storeDAO.loadAll();
    }
}
