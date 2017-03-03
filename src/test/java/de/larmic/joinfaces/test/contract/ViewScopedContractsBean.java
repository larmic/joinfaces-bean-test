package de.larmic.joinfaces.test.contract;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ViewScopedContractsBean implements ContractsBean {

    private String customerNumber;

    @PostConstruct
    public void init() {
        customerNumber = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("customerNumber");

    }

    public String getCustomerNumber() {
        return customerNumber;
    }
}
