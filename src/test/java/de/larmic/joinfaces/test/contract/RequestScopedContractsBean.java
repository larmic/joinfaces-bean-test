package de.larmic.joinfaces.test.contract;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class RequestScopedContractsBean implements ContractsBean {

    private String customerNumber;

    @PostConstruct
    public void init() {
        customerNumber = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("customerNumber");

    }

    public String getCustomerNumber() {
        return customerNumber;
    }
}
