<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" 
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:util="http://www.springframework.org/schema/util" 
    xmlns:aop="http://www.springframework.org/schema/aop" 
    xmlns:tx="http://www.springframework.org/schema/tx" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xsi:schemaLocation="http://www.springframework.org/schema/beans 
	http://www.springframework.org/schema/beans/spring-beans-3.2.xsd 
	http://www.springframework.org/schema/context 
	http://www.springframework.org/schema/context/spring-context-3.2.xsd 
	http://www.springframework.org/schema/util 
	http://www.springframework.org/schema/util/spring-util-3.2.xsd
	http://www.springframework.org/schema/aop 
	http://www.springframework.org/schema/aop/spring-aop-3.2.xsd
	http://www.springframework.org/schema/tx
	http://www.springframework.org/schema/tx/spring-tx-3.2.xsd">
	
	<!--1. 开启包扫描 -->
	<context:component-scan base-package="service" />
	
	<!--2. 开启注解扫描驱动器  -->
	<context:annotation-config />
	
	<!--3. 引入外部的配置文件 jdbc.properties -->
	<context:property-placeholder location="classpath:/jdbc.properties"/>
	
	<!--4. 配置mysql的数据源信息 c3p0  -->
	<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
	    <!-- DI依赖注入：jdbc的配置信息 -->
	    <property name="driverClass" value="${jdbc.driver}" />
	    <property name="jdbcUrl" value="${jdbc.url}" />
	    <property name="user" value="${jdbc.user}" />
	    <property name="password" value="${jdbc.password}" />
	    <!-- 配置c3p0的连接池信息 -->
	    <property name="maxPoolSize" value="30" />
	    <property name="minPoolSize" value="8" />
	</bean>
	
	<!--5.配置事务管理器  -->
	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
	    <property name="dataSource" ref="dataSource" />
	</bean>
	
	<!--6.配置事务的通知  -->
	    <!-- 只有query可支持的，其他的必须要进行事务控制 -->
	<tx:advice id="txAdvice">
	    <tx:attributes>
	        <tx:method name="add*" propagation="REQUIRED"/>
	        <tx:method name="delete*" propagation="REQUIRED"/>
	        <tx:method name="update*" propagation="REQUIRED"/>
	        <tx:method name="query*" propagation="SUPPORTS"/>
	        <tx:method name="*" read-only="true"/>
	    </tx:attributes>
	</tx:advice>
	
	<!--7.配置事务的切面  -->
	<aop:config>
	    <aop:pointcut expression="execution(* service..*.*(..))" id="pc"/>
	    <aop:advisor advice-ref="txAdvice" pointcut-ref="pc"/>
	</aop:config>
	
	<!-- 17.配置mybatis框架的核心 。引入mybatis的核心api,SqlSessionFactory -->
	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
	    <!-- 18.引入dataSource数据源 -->
	    <property name="dataSource" ref="dataSource" />
	    <!-- 19.引入mybatis的核心配置文件 -->
	    <property name="configLocation" value="classpath:/sqlMapConfig.xml" />
	    <!-- 20.引入mybatis的映射文件 -->
	    <property name="mapperLocations" value="classpath:/pojo/*.xml" />
	</bean>
	
	<!-- 21.配置mybatis的mapper接口   作用：生成接口的实现类对象专门执行sql语句 -->
	<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
	    <!-- 设置mapper接口的路径 -->
	    <property name="basePackage" value="mapper"/>
	</bean>
	
	
	
</beans>


<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" version="3.0">
    <display-name>spring-springmvc-mybatis</display-name>
    
    
    <!-- web容器最终整合了ssm的核心配置文件 -->
    <!-- 8.配置spring框架核心配置文件的路径 -->
    <context-param>
        <!-- 描述applicationContext.xml文件 -->
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:/applicationContext.xml</param-value>
    </context-param>
    
    <!-- 9.配置web容器的监听器
           作用：当web服务器启动时 web容器加载spring框架的配置文件-->
    <listener>
         <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>  
    </listener>
    
    <!-- 配置springmvc的核心配置文件 -->
    <!-- 13.配置dispatcherServlet -->
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <!-- 14.配置springmvc核心文件路径 -->
        <init-param>
            <param-name>contextConfigLoaction</param-name>
            <param-value>classpath:/springmvc-servlet.xml</param-value>
        </init-param>
    </servlet>
    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
  
  
  
  
  
  
  
  
  
  
  
</web-app>




<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" 
	xmlns:context="http://www.springframework.org/schema/context" 
	xmlns:mvc="http://www.springframework.org/schema/mvc" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xsi:schemaLocation="http://www.springframework.org/schema/beans 
	http://www.springframework.org/schema/beans/spring-beans-3.2.xsd 
	http://www.springframework.org/schema/context 
	http://www.springframework.org/schema/context/spring-context-3.2.xsd 
	http://www.springframework.org/schema/mvc 
	http://www.springframework.org/schema/mvc/spring-mvc-3.2.xsd ">
	
	<!--10. 开启包扫描 -->
	<context:component-scan base-package="controller" />
	
	<!--11.开启springmvc的注解扫描  -->
	<mvc:annotation-driven />
	
	<!--12.配置视图解析器:viewResolver  -->
	<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	    <!-- 设置页面的前缀路径 -->
	    <property name="prefix" value="/WEB-INF/" />
	    <!-- 设置页面的后缀名 -->
	    <property name="suffix" value=".jsp"></property>
	</bean>

</beans>



<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <!-- 特别注意：因为dataSource信息以及mappers映射文件信息交给了spring框架去管理 -->
    <!-- 那么mybatis的核心配置文件只需要开启驼峰映射以及数据库缓存 -->
    <settings>
        <!-- 15.开启驼峰映射 -->
        <setting name="mapUnderscoreToCamelCase" value="true"/>
        <!-- 16.开启缓存：数据第一次是查的数据库，数据之后会先从缓存中查询 -->
        <setting name="cacheEnabled" value="true"/>
    </settings>
    



</configuration>





