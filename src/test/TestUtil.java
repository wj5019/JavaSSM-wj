package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pojo.User;

public class TestUtil {
	@Test
	public void test01() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		User user = (User) context.getBean("user");
		user.sleep();
		//如果要测试销毁对象的方法需要使用Close（）方法关闭对象context
		//注意，在使用close方法时，需要修改context的类型
		context.close();
	}
	
}
