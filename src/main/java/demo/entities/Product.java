package demo.entities;

import demo.persistence.ProductDAO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Product.findAll", query = "select a from Product as a")
})
@Table(name = "PRODUCT")
@Getter @Setter
public class Product {
    public Product()
    {

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "products")
    private List<Store> stores;

}
