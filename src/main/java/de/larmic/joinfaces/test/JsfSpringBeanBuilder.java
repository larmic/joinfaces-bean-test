package de.larmic.joinfaces.test;

import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple chaining builder to create JSF scoped bean that should be unit tested.
 */
public class JsfSpringBeanBuilder {

    private final Map<String, String> parameterMap = new HashMap<>();
    private final ApplicationContext context;

    public JsfSpringBeanBuilder(ApplicationContext context) {
        this.context = context;
    }

    public JsfSpringBeanBuilder withExternalParameter(String key, String value) {
        this.parameterMap.put(key, value);
        return this;
    }

    public <T> T build(Class<T> beanClass) {
        new FacesContextMock()
                .withExternalParameters(parameterMap)
                .replaceIn(context);

        return context.getBean(beanClass);
    }
}
