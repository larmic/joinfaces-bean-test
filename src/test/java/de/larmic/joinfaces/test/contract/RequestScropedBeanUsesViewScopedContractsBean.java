package de.larmic.joinfaces.test.contract;


import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class RequestScropedBeanUsesViewScopedContractsBean implements ContractsBean, Serializable {

    @Autowired
    private ViewScopedContractsBean viewScopedContractsBean;

    @Override
    public String getCustomerNumber() {
        return viewScopedContractsBean.getCustomerNumber();
    }
}
