package demo.useCases;

import demo.entities.Customer;
import demo.interceptors.LoggedInvocation;
import demo.persistence.ICustomerDAO;
import demo.persistence.IStoreDAO;
import demo.rest.contracts.CustomerDto;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.UserTransaction;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@ViewScoped
@Named
public class CustomerEdit implements Serializable {

    @Getter
    @Setter
    private CustomerDto customer;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long id = Long.parseLong(requestParameters.get("id"));

        Client client = ClientBuilder.newClient();
        customer = client.target("http://localhost:8080/demo/api/customer/" + id)
                .request(MediaType.APPLICATION_JSON)
                .get(CustomerDto.class);
    }
    public void updateCustomer(){
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8080/demo/api/customer/" + customer.getId())
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(customer, MediaType.APPLICATION_JSON));
        if (response.getStatus() != 200) {
            System.out.println("Error: " + response.getStatus());
        }
    }
}
