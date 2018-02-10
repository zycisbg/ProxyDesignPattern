package com.zyc.proxydesignpattern.dynamicproxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
//看好包名  一定要用 cglib下的MethodInterceptor
public class MyCglibProxy implements MethodInterceptor {

    //实现 MethodInterceptor 中的 intercept 接口
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("---前置---");
        //实际方法运行的地方
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("---后置---");
        return result;
    }

}
