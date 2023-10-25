package com.sh8121.springcore.archive.v0_basic.bean.v3;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;

public class BeanPostProcessorA implements DestructionAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("beanA")) {
            System.out.println("postProcessBeforeInitialization of " + beanName + " in BeanPostProcessorA");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("beanA")) {
            System.out.println("postProcessAfterInitialization of " + beanName + " in BeanPostProcessorA");
        }
        return bean;
    }

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (beanName.equals("beanA")) {
            System.out.println("postProcessBeforeDestruction of " + beanName + " in BeanPostProcessorA");
        }
    }
}
