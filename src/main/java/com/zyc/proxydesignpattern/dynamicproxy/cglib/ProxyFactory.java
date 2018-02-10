package com.zyc.proxydesignpattern.dynamicproxy.cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Method;

public class ProxyFactory {
    /**
     * 防止在外边创建
     */
    private ProxyFactory(){}

    //代理工程方法。
    //这几天有时间的话 在写个工厂设计模式
    public static UserService getUserServiceProxy(MyCglibProxy myCglibProxy){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(com.zyc.proxydesignpattern.dynamicproxy.cglib.UserService.class);
        enhancer.setCallback(new MyCglibProxy());
        //这里就不详细写了，cglib还是很强大的。
        //setCallbacks可是设置多个代理，然后根据 setCallbackFilter 的 accept 方法 看哪个方法走哪个代理。
//        enhancer.setCallbacks(new Callback[]{new MyCglibProxy()});
//        enhancer.setCallbackFilter((Method method)-> {
//            //在这就不详细写了,cglib
//            return 0;
//        });
        return (com.zyc.proxydesignpattern.dynamicproxy.cglib.UserService)enhancer.create();
    }
}
