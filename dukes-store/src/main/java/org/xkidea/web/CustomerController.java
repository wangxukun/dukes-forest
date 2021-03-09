package org.xkidea.web;

import org.xkidea.ejb.UserBean;
import org.xkidea.entity.Customer;
import org.xkidea.entity.Person;
import org.xkidea.qualifiers.LoggedIn;
import org.xkidea.web.util.AbstractPaginationHelper;
import org.xkidea.web.util.PageNavigation;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
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

    public AbstractPaginationHelper getPagination(){
        if (pagination == null) {
            pagination = new AbstractPaginationHelper(AbstractPaginationHelper.DEFAULT_SIZE) {
                @Override
                public int getItemCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(),
                        getPageFirstItem() + getPageSize()}));
                    // return new ListDataModel(getFacade().findAll());
                }
            };
        }
        return pagination;
    }

    public PageNavigation prepareList(){
        recreateModel();
        return PageNavigation.LIST;
    }

    private void recreateModel() {
        items = null;
    }
}
