package controller;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
	//自定义方法：用于页面的显示
	@RequestMapping(value="/toFile")
	public String toJSP() {
		//页面跳转
		return "FileUpload";
	}
	
	//专门用于文件解析的上传的代码
	@RequestMapping("/fileupload")
	public String fileupload(Model model,MultipartFile files) throws IOException {//文件上传
		//变量名对应着页面的name="变量名"
		
		//参数1：file指的是上传文件后存放的路径
		//参数2：data指的是上传文件生成的流
		FileUtils.writeByteArrayToFile(
				new File("E:\\test\\"+files.getOriginalFilename()), 
				files.getBytes());
		
		//数据的封装
		model.addAttribute("msg", "上传成功");
		
		//页面跳转
		return "FileUpload";
	}

}
-------------------------------------
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>文件上传</title>
</head>
<body>
    <form action="${pageContext.request.contextPath }/fileupload" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>文件上传：</td>
                <td><input type="file" name="files" /></td>
            </tr>
            <tr>
                <td><input type="submit" value="上传提交" /></td>
            </tr>
        </table>
    </form>
    <h1>${msg }</h1>
</body>
</html>
