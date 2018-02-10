package com.zyc.proxydesignpattern;

import com.zyc.proxydesignpattern.dynamicproxy.cglib.MyCglibProxy;
import com.zyc.proxydesignpattern.dynamicproxy.cglib.ProxyFactory;
import com.zyc.proxydesignpattern.dynamicproxy.jdk.MyInvocationHandler;
import com.zyc.proxydesignpattern.dynamicproxy.jdk.UserService;
import com.zyc.proxydesignpattern.dynamicproxy.jdk.UserServiceImpl;
import com.zyc.proxydesignpattern.inaction.controller.ProjectController;
import com.zyc.proxydesignpattern.staticproxy.UserServiceProxy;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProxyDesignPatternApplicationTests {

	@Autowired
	private ProjectController projectController;
	
	private UserService userService = new UserServiceImpl();

	private com.zyc.proxydesignpattern.staticproxy.UserService userServiceByStatic = new com.zyc.proxydesignpattern.staticproxy.UserServiceImpl();


	@Test
	public void inactionTest() {
		System.out.println(projectController.getProjectById("4546"));
	}
	
	
	@Test
	public void dynamicTest(){
		UserService proxy = (UserService) new MyInvocationHandler(userService).getProxy();
		proxy.login("admin","123");

	}

	@Test
	public void staticTest(){
		new UserServiceProxy(userServiceByStatic).login("admin","123");
	}

	@Test
	public void cglibTest(){
		/*这样搞不方便，可以搞一个工厂

		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(com.zyc.proxydesignpattern.dynamicproxy.cglib.UserService.class);
		enhancer.setCallback(new MyCglibProxy());
		com.zyc.proxydesignpattern.dynamicproxy.cglib.UserService userService = (com.zyc.proxydesignpattern.dynamicproxy.cglib.UserService)enhancer.create();
		userService.login("admin","4554");*/

		ProxyFactory.getUserServiceProxy(new MyCglibProxy()).login("123","3443");
	}

}
