package demo.rest;

import demo.entities.Customer;
import demo.persistence.CustomerDAO;
import demo.persistence.ICustomerDAO;
import demo.rest.contracts.CustomerDto;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/customer")
public class CustomerController {

    @Inject
    @Getter
    @Setter
    private ICustomerDAO customerDAO;
    @Resource
    private UserTransaction userTransaction;
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final  Long id) {
        Customer customer = customerDAO.findOne(id);
        if(customer == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName(customer.getName());
        customerDto.setPhone(customer.getPhone());
        customerDto.setId(customer.getId());
        customerDto.setAsync(customer.getAsync());
        return Response.ok(customerDto).build();
    }
    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(
            @PathParam("id") final Long customerId,
            CustomerDto customerData) {
        try {
            userTransaction.begin();
            Customer existingCustomer = customerDAO.findOne(customerId);
            if (existingCustomer == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingCustomer.setName(customerData.getName());
            existingCustomer.setPhone(customerData.getPhone());
            existingCustomer.setAsync(customerData.getAsync());
            Customer c = customerDAO.update(existingCustomer);
            userTransaction.commit();
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }catch (Exception ex) {
            try {
                userTransaction.rollback();
            } catch (Exception rollbackEx) {
            }
            return Response.serverError().build();
        }
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    //@Transactional
    public Response create(CustomerDto customerData) {
        try {
            Customer newCustomer = new Customer();
            newCustomer.setName(customerData.getName());
            newCustomer.setPhone(customerData.getPhone());

            customerDAO.persist(newCustomer);

            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
