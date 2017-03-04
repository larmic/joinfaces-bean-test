package de.larmic.joinfaces.test.contract;

import de.larmic.joinfaces.test.FacesContextMockApplicationContextInitializer;
import de.larmic.joinfaces.test.JsfSpringBeanBuilder;
import de.larmic.joinfaces.test.TestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = {FacesContextMockApplicationContextInitializer.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = TestApplication.class)
public class ContractsBeanTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void testCreateRequestScopedBeanWithCustomerNumberIsSet() throws Exception {
        final ContractsBean bean = new JsfSpringBeanBuilder(context)
                .withExternalParameter("customerNumber", "unit-test-customer-number")
                .build(RequestScopedContractsBean.class);

        assertThat(bean.getCustomerNumber()).isEqualTo("unit-test-customer-number");
    }

    @Test
    public void testCreateRequestScopedBeanWithCustomerNumberIsNull() throws Exception {
        final ContractsBean bean = new JsfSpringBeanBuilder(context).build(RequestScopedContractsBean.class);

        assertThat(bean.getCustomerNumber()).isNull();
    }

    @Test
    public void testCreateViewScopedBeanWithCustomerNumberIsSet() throws Exception {
        final ContractsBean bean = new JsfSpringBeanBuilder(context)
                .withExternalParameter("customerNumber", "1")
                .build(ViewScopedContractsBean.class);

        assertThat(bean.getCustomerNumber()).isEqualTo("1");
    }

    @Test
    public void testCreateViewScopedBeanWithCustomerNumberIsNull() throws Exception {
        final ContractsBean bean = new JsfSpringBeanBuilder(context)
                .build(ViewScopedContractsBean.class);

        assertThat(bean.getCustomerNumber()).isNull();
    }

    @Test
    public void testCreateSessionScopedBeanWithCustomerNumberIsSet() throws Exception {
        final ContractsBean bean = new JsfSpringBeanBuilder(context)
                .withExternalParameter("customerNumber", "3")
                .build(SessionScopedContractsBean.class);

        assertThat(bean.getCustomerNumber()).isEqualTo("3");
    }

    @Test
    public void testCreateSessionScopedBeanWithCustomerNumberIsNull() throws Exception {
        final ContractsBean bean = new JsfSpringBeanBuilder(context)
                .build(SessionScopedContractsBean.class);
        assertThat(bean.getCustomerNumber()).isNull();
    }

    @Test
    public void testCreateApplicationScopedBeanWithCustomerNumberIsNull() throws Exception {
        final ApplicationScopedContractsBean bean = new JsfSpringBeanBuilder(context)
                .withExternalParameter("customerNumber", "5")
                .build(ApplicationScopedContractsBean.class);
        assertThat(bean.getCustomerNumber()).isNull();
        assertThat(bean.lookupContext()).isEqualTo("5");
    }


    @Test
    public void testRequestScropedBeanUsesViewScopedContractsBean() throws Exception {
        final ContractsBean bean = new JsfSpringBeanBuilder(context)
                .withExternalParameter("customerNumber", "6")
                .build(RequestScropedBeanUsesViewScopedContractsBean.class);

        assertThat(bean.getCustomerNumber()).isEqualTo("6");
    }
}