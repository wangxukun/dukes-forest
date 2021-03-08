package org.xkidea.web;

import org.xkidea.ejb.UserBean;
import org.xkidea.entity.Customer;
import org.xkidea.entity.Person;
import org.xkidea.qualifiers.LoggedIn;
import org.xkidea.web.util.AbstractPaginationHelper;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Logger;

@Named(value = "customerController")
@SessionScoped
public class CustomerController implements Serializable {
    private static final String BUNDLE = "bundles.Bundle";
    private static final long serialVersionUID = 1909923329155404223L;

    @Inject
    @LoggedIn
    Person authenticated;
    private Customer current;
    private DataModel items = null;
    @EJB
    private UserBean ejbFacade;

    private static final Logger logger = Logger.getLogger(CustomerController.class.getCanonicalName());

    private AbstractPaginationHelper pagination;
    private int selectedItemIndex;

    public CustomerController() {
    }

    public Customer getSelected() {
        if (current == null) {
            current = new Customer();
            selectedItemIndex = -1;
        }
        return current;
    }

    private UserBean getFacade() {
        return ejbFacade;
    }

    // TODO ... ...
}
