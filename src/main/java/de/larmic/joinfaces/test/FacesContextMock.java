package de.larmic.joinfaces.test;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FacesContextMock {

   private Map<String, String> parameterMap = new HashMap<>();

   private Map<String, Object> viewMap = new HashMap();

   public FacesContextMock withExternalParameter(Map<String, String> parameters) {
      this.parameterMap.putAll(parameters);
      return this;
   }

   public FacesContextMock replaceIn(ApplicationContext applicationContext) {
      final FacesContext facesContext;
      final ConfigurableListableBeanFactory beanFactory = ((ConfigurableApplicationContext) applicationContext).getBeanFactory();
      if (!beanFactory.containsBean("facesContext")) {
         facesContext = mock(FacesContext.class);
         beanFactory.registerSingleton("facesContext", facesContext);
      } else {
         facesContext = (FacesContext) beanFactory.getBean("facesContext");
      }
      final ExternalContext externalContext = mock(ExternalContext.class);
      final UIViewRoot uiViewRoot = mock(UIViewRoot.class);
      when(facesContext.getExternalContext()).thenReturn(externalContext);
      when(externalContext.getRequestParameterMap()).thenReturn(parameterMap);
      when(facesContext.getViewRoot()).thenReturn(uiViewRoot);
      when(uiViewRoot.getViewMap()).thenReturn(viewMap);
      final Field field;
      try {
         field = FacesContext.class.getDeclaredField("instance");
         field.setAccessible(true);
         final ThreadLocal<FacesContext> threadLocal = (ThreadLocal<FacesContext>) field.get(null);
         threadLocal.set(facesContext);
      } catch (Exception e) {
         throw new RuntimeException(e);
      }

      return this;
   }
}
