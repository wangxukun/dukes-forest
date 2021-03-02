package org.xkidea.web;

import org.xkidea.ejb.UserBean;
import org.xkidea.entity.Person;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "userController")
@SessionScoped
public class UserController implements Serializable {
    private static final String BUNDLE = "bundles.Bundle";
    private static final long serialVersionUID = 7677926122140567641L;

    Person user;
    @EJB
    private UserBean ejbFacade;
    private String username;
    private String password;
    @Inject
    CustomerController customerController;

    public UserController() {
    }
}
