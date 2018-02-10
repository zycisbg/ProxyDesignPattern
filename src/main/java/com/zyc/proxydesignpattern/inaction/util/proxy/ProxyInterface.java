package com.zyc.proxydesignpattern.inaction.util.proxy;

public interface ProxyInterface<T,V> {
    /**
     * T 参数代表需要操作对象的工具类
     * V 参数实体对象
     * @param
     * @return
     */
    Object doBegin(T t, Object[] param);

    /**
     * T 参数代表需要操作对象的工具类
     * returnObj invok 后返回的参数
     *
     * @param returnObj
     * @param t
     * @return
     */
     Object doEnd(V returnObj, T t,Object[] param);
}
