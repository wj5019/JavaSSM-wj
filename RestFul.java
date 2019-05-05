package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RestFulController {
//自定义方法
	@RequestMapping("/login/{name}/{age}")
	public String login(@PathVariable String name,@PathVariable String age,Model model) {
		//打桩测试
		System.out.println(name);
		System.out.println(age);
		//页面跳转
		return "RestFul";
	}
}
--------------------------------------------
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RestFul风格的url路径</title>
</head>
<body>
    <a href="${pageContext.request.contextPath }/login/张三/33">resutful格式的路径</a>

</body>
</html>
