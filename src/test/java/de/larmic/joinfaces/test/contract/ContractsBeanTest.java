package de.larmic.joinfaces.test.contract;

import de.larmic.joinfaces.test.JsfSpringBeanBuilder;
import de.larmic.joinfaces.test.TestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
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
                .withExternalParameter("customerNumber", "unit-test-customer-number")
                .build(ViewScopedContractsBean.class);

        assertThat(bean.getCustomerNumber()).isEqualTo("unit-test-customer-number");
    }

    @Test
    public void testCreateViewScopedBeanWithCustomerNumberIsNull() throws Exception {
        final ContractsBean bean = new JsfSpringBeanBuilder(context).build(ViewScopedContractsBean.class);

        assertThat(bean.getCustomerNumber()).isNull();
    }

    @Test
    public void testCreateSessionScopedBeanWithCustomerNumberIsSet() throws Exception {
        final ContractsBean bean = new JsfSpringBeanBuilder(context)
                .withExternalParameter("customerNumber", "unit-test-customer-number")
                .build(SessionScopedContractsBean.class);

        assertThat(bean.getCustomerNumber()).isEqualTo("unit-test-customer-number");
    }

    @Test
    public void testCreateSessionScopedBeanWithCustomerNumberIsNull() throws Exception {
        final ContractsBean bean = new JsfSpringBeanBuilder(context).build(SessionScopedContractsBean.class);

        assertThat(bean.getCustomerNumber()).isNull();
    }
}