package com.sh8121.springioccontainer.v0_basic.bean.v3;

import jakarta.annotation.PostConstruct;

public class BeanB {

    public BeanB() {
        System.out.println("BeanB Created");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("BeanB.postConstruct()");
    }
}
