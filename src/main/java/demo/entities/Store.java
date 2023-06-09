package demo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Store.findAll", query = "select b from Store as b"),
        @NamedQuery(name = "Store.findAllWithNonNullNameAndAddress", query = "SELECT s FROM Store s WHERE s.name IS NOT NULL AND s.address IS NOT NULL")
})
@Table(name = "STORE")
@Getter @Setter
public class Store implements Serializable {

    public Store()
    {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "ADDRESS")
    private String address;


    @OneToMany(mappedBy = "store")
    private List<Customer> customers;

    @ManyToMany
    @JoinTable(name = "STORE_PRODUCT",
            joinColumns = @JoinColumn(name = "STORE_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private List<Product> products;

    @Column(name = "NAME")
    private String name;

}
