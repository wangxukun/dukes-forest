package org.xkidea.web;

import org.xkidea.ejb.UserBean;
import org.xkidea.entity.Groups;
import org.xkidea.entity.Person;
import org.xkidea.web.util.JsfUtil;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

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


    Person getAuthenticatedUser(){
        return user;
    }

    /**
     * Login method based on HttpServletRequest and security realm
     * @return path
     */
    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String result;

        try {
            request.login(this.getUsername(), this.getPassword());

            JsfUtil.addSuccessMessage(JsfUtil.getStringFromBundle(BUNDLE, "Login_Success"));

            this.user = ejbFacade.getUserByEmail(getUsername());
            this.getAuthenticatedUser();

            if (isAdmin()) {
                result = "/admin/index";
            } else {
                result = "/index";
            }
        } catch (ServletException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE,null,ex);
            JsfUtil.addErrorMessage(JsfUtil.getStringFromBundle(BUNDLE,"Login_Failed"));

            result = "login";
        }

        return result;
    }

    public String logout(){

        try {

        } catch (ServletException ex) {

        } finally {
            return "/index";
        }
    }

    private boolean isAdmin() {
        for (Groups g : user.getGroupsList()) {
            if (g.getName().equals("ADMINS")) {
                return true;
            }
        }
        return false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
