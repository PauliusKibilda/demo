package demo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Customer.findAll", query = "select t from Customer as t"),
        @NamedQuery(name = "Customer.findByStoreId", query = "SELECT c FROM Customer c WHERE c.store.id = :storeId")
})
@Table(name = "CUSTOMER")
@Getter @Setter
public class Customer {

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

    @ManyToOne
    @JoinColumn(name="STORE_ID")
    private Store store;
}
