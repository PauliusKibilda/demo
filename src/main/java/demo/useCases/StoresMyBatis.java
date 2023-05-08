package demo.useCases;

import demo.entities.Product;
import demo.mybatis.model.Store;
import demo.mybatis.dao.StoreMapper;
import demo.persistence.ProductDAO;
import demo.persistence.StoreDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.List;

@Model
public class StoresMyBatis {

    @Inject
    public StoresMyBatis(Instance<Object> instance) {
        this.storeMapper = (StoreMapper) instance.select(StoreMapper.class).get();
    }

    private StoreMapper storeMapper;

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

    public String createStore(){
        try {
            utx.begin();
            this.storeMapper.insert(storeToCreate);
            utx.commit();
            loadAllStores();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return "/myBatis/stores?faces-redirect=true";
    }

    private void loadAllStores(){
        this.allStores = storeMapper.selectAll();
    }
}
