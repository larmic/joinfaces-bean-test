package de.larmic.joinfaces.test.contract;


import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class RequestScropedBeanUsesViewScopedContractsBean implements ContractsBean, Serializable {

    @Autowired
    private ViewScopedContractsBean viewScopedContractsBean;

    @Override
    public String getCustomerNumber() {
        return viewScopedContractsBean.getCustomerNumber();
    }
}
