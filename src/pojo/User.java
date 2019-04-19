package pojo;

//用于测试user对象的生命周期
public class User {
	//对象的实例化方法：创建对象
	public User() {
		System.out.println("user对象的创建");
	}
	
	//对象的初始化方法：设置对象的属性
	public void init() {
		System.out.println("user对象的初始化");
	}
	
	//对象的自定义方法
	public void sleep() {
		System.out.println("user对象的行为方法");
	}
	
	//对象的销毁方法：当用户不再使用时，进行对象消除
	public void destroy() {
		System.out.println("user对象被销毁了");
	}

}
