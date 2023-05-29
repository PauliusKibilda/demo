package demo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "Customer.findAll", query = "select t from Customer as t"),
        @NamedQuery(name = "Customer.findByStoreId", query = "SELECT c FROM Customer c WHERE c.store.id = :storeId"),
        @NamedQuery(name = "Customer.findCorrectByStoreId",
                query = "SELECT c FROM Customer c WHERE c.store.id = :storeId AND c.phone IS NOT NULL AND c.phone <> '' AND c.phone <> ' '"),
        @NamedQuery(name = "Store.findByStoreId", query = "SELECT s FROM Store s WHERE s.id = :storeId")
})
@Table(name = "CUSTOMER")
@Getter @Setter
public class Customer implements Serializable {

    public Customer()
    {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "ASYNC")
    private Integer async;

    @ManyToOne
    @JoinColumn(name="STORE_ID")
    private Store store;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;
}
