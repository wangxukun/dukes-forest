package org.xkidea.ejb;

import org.xkidea.entity.Customer;
import org.xkidea.entity.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UserBean extends AbstractFacade<Customer>{

    @PersistenceContext(unitName = "forestPU")
    private EntityManager em;

    public UserBean() {
        super(Customer.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public boolean createUser(Customer customer) {
        if (getUserByEmail(customer.getEmail()) == null) {
            super.create(customer);
            return true;
        } else {
            return false;
        }
    }

    public Person getUserByEmail(String email) {
        Query createNameQuery = getEntityManager().createNamedQuery("Person.findByEmail");

        createNameQuery.setParameter("email", email);

        if (createNameQuery.getResultList().size() > 0) {
            return (Person) createNameQuery.getResultList();
        } else {
            return null;
        }
    }
}
