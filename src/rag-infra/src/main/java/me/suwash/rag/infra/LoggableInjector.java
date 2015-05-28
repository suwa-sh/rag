package me.suwash.rag.infra;

import java.lang.reflect.Field;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

/**
 * ロガーのインジェクタ。
 */
@Named
public class LoggableInjector implements BeanPostProcessor {

    /**
     * TODO メソッドの説明。
     *
     * @param bean xxx
     * @param beanName xxx
     * @return xxx
     * @throws BeansException xxx
     */
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * TODO メソッドの説明。
     *
     * @param bean xxx
     * @param beanName xxx
     * @return xxx
     * @throws BeansException xxx
     */
    public Object postProcessBeforeInitialization(final Object bean, String beanName) throws BeansException {
        ReflectionUtils.doWithFields(bean.getClass(), new FieldCallback() {
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                // make the field accessible if defined private
                ReflectionUtils.makeAccessible(field);
                if (field.getAnnotation(Loggable.class) != null) {
                    Logger log = LoggerFactory.getLogger(bean.getClass());
                    field.set(bean, log);
                }
            }
        });
        return bean;
    }
}
