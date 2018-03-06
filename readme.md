# JSF Bean testing using JoinFaces and SpringBoot 

[![Build Status](https://travis-ci.org/larmic/joinfaces-bean-test.svg?branch=master)](https://travis-ci.org/larmic/joinfaces-bean-test) [![Coverage Status](https://coveralls.io/repos/github/larmic/joinfaces-bean-test/badge.svg?branch=master)](https://coveralls.io/github/larmic/joinfaces-bean-test?branch=master)

This is a simple demo to demonstrate how to test a jsf scoped bean in Spring Boot context using [JoinsFaces](https://github.com/joinfaces/joinfaces).

This code is inspired by some commercial project commits of @DEXRAY and updated by @larmic and @renepa

## Usage

Dependency is available in maven central repository. Just add to your pom.xml

```xml
<dependency>
   <groupId>de.larmic</groupId>
   <artifactId>joinfaces-bean-test</artifactId>
   <version>0.4</version>
   <scope>test</scope>
</dependency>
```

## Example

```java
@Named
@RequestScoped
public class ContractsBean {

    private String customerNumber;

    @PostConstruct
    public void init() {
        customerNumber = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("customerNumber");

    }

    public String getCustomerNumber() {
        return customerNumber;
    }
}
```

This simple bean reads url query parameter _customerNumber_. Using JoinFaces and SpringBoot this bean is 
not testable by default injection into a test class.

This demo allows following integration test:

```java
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ContractsBeanTest {
    
    @Autowired
    private ApplicationContext context;

    @Test
    public void testCreateRequestScopedBeanWithCustomerNumberIsSet() throws Exception {
        final ContractsBean bean = new JsfSpringBeanBuilder(context)
                .withExternalParameter("customerNumber", "unit-test-customer-number")
                .build(ContractsBean.class);

        assertThat(bean.getCustomerNumber()).isEqualTo("unit-test-customer-number");
    }

    @Test
    public void testCreateRequestScopedBeanWithCustomerNumberIsNull() throws Exception {
        final ContractsBean bean = new JsfSpringBeanBuilder(context)
                .build(ContractsBean.class);

        assertThat(bean.getCustomerNumber()).isNull();
    }
}
```

With version 0.3 you can also use injections

```java
@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = ContractsBeanTest.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ContractsBeanTest implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    
    @Autowired
    private ContractsBean contractsBean;
    
    @Override
    public void initialize(ConfigurableApplicationContext ctx) {
        new FacesContextMock()
                .withExternalParameter("customerNumber", "unit-test-customer-number")
                .replaceIn(ctx);
    }
    
    @Test
    public void testCreateRequestScopedBeanWithCustomerNumberIsNull() throws Exception {
        final ContractsBean bean = new JsfSpringBeanBuilder(context)
                .build(ContractsBean.class);

        assertThat(bean.getCustomerNumber()).isEqualTo("unit-test-customer-number");
    }
}
```

## Compatibility list

| Version | Spring Boot | JoinFaces |
|:-------:|:-----------:|:---------:|
| <= 0.3  |       1.5.x | 2.3.x
| 0.4     |      2.0.x  | 3.0.0.RC1    

