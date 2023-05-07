package demo.useCases;

import demo.entities.Customer;
import demo.entities.Product;
import demo.persistence.CustomerDAO;
import demo.persistence.ProductDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Products {
    @Inject
    private ProductDAO productDAO;

    @Getter
    @Setter
    private Product productToCreate = new Product();

    @Getter
    private List<Product> allProducts;

    @PostConstruct
    public void init(){
        loadAllProducts();
    }

//    @Transactional
//    public void createCustomer(){
//        this.productDAO.persist(productToCreate);
//    }

    private void loadAllProducts(){
        this.allProducts = productDAO.loadAll();
    }
}