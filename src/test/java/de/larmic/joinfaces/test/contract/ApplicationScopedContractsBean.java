package de.larmic.joinfaces.test.contract;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "applicationScopedContractsBean")
@ApplicationScoped
public class ApplicationScopedContractsBean implements ContractsBean {

    private String customerNumber;

    @PostConstruct
    public void init() {
        customerNumber = lookupContext();
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public String lookupContext() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("customerNumber");
    }
}
