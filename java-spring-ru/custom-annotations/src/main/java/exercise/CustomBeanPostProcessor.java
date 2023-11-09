package exercise;

import java.lang.reflect.Proxy;

import exercise.calculator.Calculator;
import exercise.calculator.CalculatorImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// BEGIN
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {
    public final Logger LOGGER = LoggerFactory.getLogger(BeanPostProcessor.class);
    Map<String, String> beans = new HashMap<>();
    Map<String, Class> annotatedBeans = new HashMap<>();
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)throws BeansException {
        if(bean.getClass().isAnnotationPresent(Inspect.class)) {
            String level = bean.getClass().getAnnotation(Inspect.class).level();
            beans.put(beanName, level);
            annotatedBeans.put(beanName, bean.getClass());
            System.out.println("detected inspect annotation");
        }
        return bean;
    }
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(!annotatedBeans.containsKey(beanName)) {
            return bean;
        }

        Object proxyInstance = Proxy.newProxyInstance(
                annotatedBeans.get(beanName).getClassLoader(),
                annotatedBeans.get(beanName).getInterfaces(),
                (proxy, method, args) -> {
                    LOGGER.info("Was called method: " + method.getName() + "() with arguments: " + Arrays.toString(args));
                    return method.invoke(bean, args);
                }
        );
        return proxyInstance;
    }
}
// END
