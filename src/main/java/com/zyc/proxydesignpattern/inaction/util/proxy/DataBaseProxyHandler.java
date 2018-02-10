package com.zyc.proxydesignpattern.inaction.util.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DataBaseProxyHandler<T> implements InvocationHandler {

    //被代理对象
    private Object delegate;

    //需要去做事情的接口
    private ProxyInterface myProxyInterface;

    //需要去做事情的类
    private Object param;

    //构造器 在这里给赋值
    public DataBaseProxyHandler(Object v) {
        this.param = v;
    }

    //proxy方法，返回
    public <T> T proxy(T delegate, ProxyInterface myProxyInterface) {
        this.myProxyInterface = myProxyInterface;
        this.delegate = delegate;

        return (T) Proxy.newProxyInstance(this.delegate.getClass().getClassLoader(),
                this.delegate.getClass().getInterfaces(), this);
    }

    //就多了个几个类，其他一模一样
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {


        Object obj = null;

        if (args != null && args.length > 0){
            obj = myProxyInterface.doBegin(param,args);
        }else{
            obj = myProxyInterface.doBegin(param,null);
        }
        if (obj != null)
            return obj;

        obj = method.invoke(this.delegate, args);

        if (args != null && args.length > 0){
            myProxyInterface.doEnd(obj, param,args);
        }else{
            myProxyInterface.doEnd(obj, param,null);
        }

        return obj;
    }
}