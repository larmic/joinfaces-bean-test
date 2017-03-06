package de.larmic.joinfaces.test.contract;

import de.larmic.joinfaces.test.FacesContextMock;
import de.larmic.joinfaces.test.TestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = {InjectBeansTest.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = TestApplication.class)
public class InjectBeansTest implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Autowired
    private RequestScopedContractsBean requestScopedContractsBean;

    @Autowired
    private ViewScopedContractsBean viewScopedContractsBean;

    @Autowired
    private ApplicationScopedContractsBean applicationScopedContractsBean;

    @Autowired
    private SessionScopedContractsBean sessionScopedContractsBean;


    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        new FacesContextMock()
                .withExternalParameter("customerNumber", "value")
                .replaceIn(applicationContext);
    }


    @Test
    public void testInjectedRequestScropedBean() throws Exception {
        assertThat(requestScopedContractsBean.getCustomerNumber()).isEqualTo("value");
    }

    @Test
    public void testInjectedViewScropedBean() throws Exception {
        assertThat(viewScopedContractsBean.getCustomerNumber()).isEqualTo("value");
    }

    @Test
    public void testInjectedSessionScropedBean() throws Exception {
        assertThat(sessionScopedContractsBean.getCustomerNumber()).isEqualTo("value");
    }

    @Test
    public void testInjectedApplicationScropedBean() throws Exception {
        assertThat(applicationScopedContractsBean.getCustomerNumber()).isEqualTo("value");
    }

}
