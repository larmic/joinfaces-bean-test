package de.larmic.joinfaces.test.contract;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class SessionScopedContractsBean implements ContractsBean, Serializable {

    private String customerNumber;

    @PostConstruct
    public void init() {
        customerNumber = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("customerNumber");

    }

    public String getCustomerNumber() {
        return customerNumber;
    }
}
