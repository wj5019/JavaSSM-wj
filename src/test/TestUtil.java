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
		//���Ҫ�������ٶ���ķ�����Ҫʹ��Close���������رն���context
		//ע�⣬��ʹ��close����ʱ����Ҫ�޸�context������
		context.close();
	}
	
}
