package com.demo.user;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.common.model.User;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

import com.jfinal.upload.UploadFile;
import javax.servlet.http.HttpServlet;   
import javax.servlet.http.HttpServletRequest;   
import javax.servlet.http.HttpServletResponse;   
import com.oreilly.servlet.MultipartRequest;  
/**
 * userController
 * 所有  sql 与业务逻辑写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
@Before(UserInterceptor.class)
public class UserController extends Controller {
	List<User> name;
	String webPath="http://127.0.0.1/upload/";
	public void index() {
		setAttr("userPage", User.me.paginate(getParaToInt(0, 1), 10));
		render("user.html");
	}
	
	public void add() {
	}
	
	
	public void save() {
		final int permitedSize = 314572800;
		Map<String,String> textMap=new HashMap<String,String>();
		Map<String,String> fileMap=new HashMap<String,String>();
		
		String type = ""; 
		String name="";
		String fileName="";
		String originalFilename = ""; 
		
		
		try {
			
			//上传目录
			String strDirectory="upload";
			String uploadPath = getRequest().getRealPath(strDirectory+"//");
			
			//获取句柄（MultipartRequest上传文件）
				//第一个参数为传过来的请求HttpServletRequest，   
	    		//第二个参数为上传文件要存储在服务器端的目录名称   
	    		//第三个参数是用来限制用户上传文件大小   
	    		//第四个参数可以设定用何种编码方式来上传文件名称，可以解决中文问题  
			MultipartRequest multipartRequest = null;
			
			multipartRequest = new MultipartRequest(
										getRequest(),
										uploadPath,   
										permitedSize,  
										"UTF-8");
			
			//获取文件
			Enumeration files = multipartRequest.getFileNames(); 					  
			// 取得文件详细信息   
		    while (files.hasMoreElements()) {   
		           name = (String)files.nextElement();  
		           type = multipartRequest.getContentType(name);   
		           fileName = multipartRequest.getFilesystemName(name);   
		           originalFilename = multipartRequest.getOriginalFileName(name);            
		           File currentFile = multipartRequest.getFile(name); 
		           fileMap.put("fileName", fileName); 
		    }  
		    //获取其它非文件字段
		    Enumeration params =multipartRequest.getParameterNames();
		    int i=0;
		    while(params.hasMoreElements()){
		    	String aname=(String)params.nextElement();
		    	String value =multipartRequest.getParameter(aname);
		    	textMap.put(aname, value);
		    }
		    new User().set("uid", textMap.get("User.uid"))
		    	.set("uname", textMap.get("user.uname"))
		    	.set("upwd", textMap.get("user.upwd"))
		    	.set("uaccount", textMap.get("user.uaccount"))
		    	.set("uimage",webPath+fileMap.get("fileName")).save();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		getModel(User.class).save();
		redirect("/user");
	}
	
	public void edit() {
		setAttr("user", User.me.findById(getParaToInt()));
	}
	
	@Before(UserValidator.class)
	public void update() {
		getModel(User.class).update();
		redirect("/user");
	}
	
	public void delete() {
		User.me.deleteById(getParaToInt());
		redirect("/user");
	}
	
	public void checkLogin(){//检查登陆状态,finished
		HttpServletRequest r = getRequest();
		String una = r.getParameter("uname");
		String upw = r.getParameter("upwd");
		List<User> user = User.me.find("select * from user where uname="+"'"+una+"'"+" and upwd="+"'"+upw+"'");
		if(user!=null){
			renderText("1");
		}else{
			renderText("0");
		}	
	}
	
	public void checkAccount(){//检测用户名是否存在,finished
		HttpServletRequest r = getRequest();
		String una = r.getParameter("uname");
		List<User> na = User.me.find("select * from user where uname="+"'"+una+"'");
		if(na.size()==0){
			renderText("1");
		}else{
			renderText("0");
		}	
	}
	
	public void createUser(){//创建新用户,finished
		HttpServletRequest r = getRequest();
		String una = r.getParameter("uname");
		String upw = r.getParameter("upwd");
		new User().set("uname", una).set("upwd", upw).save();
		renderText("1");
	}
	
	public void setImg(){//设置头像
		HttpServletRequest r = getRequest();
		UploadFile files =getFile("uploadfile","D:/android/jfinal_demo/WebRoot/img/");
		files.getFileName();
		String uid = r.getParameter("uid");
		List<User> id = User.me.find("select uid from user where uid="+"'"+uid+"'");
		User.me.findByIdLoadColumns(id.get(0).getUid(),"uimage")
			   .set("uimage",webPath)
			   .update();
		renderText("1");
	}
	
	public void findName(){//finish
		HttpServletRequest r = getRequest();
		String uname = r.getParameter("uname");
		System.out.println(uname);
		name = User.me.find("select name from user where uname="+"'"+uname+"'");
		System.out.println(name);
		renderText(name.get(0).getUname());
	}
	public void setPassword(){//finish
		HttpServletRequest r = getRequest();
		String upw = r.getParameter("upwd");
		String uid = r.getParameter("uid");
		List<User> id = User.me.find("select uid from user where uid="+"'"+uid+"'");
		User.me.findByIdLoadColumns(id.get(0).getUid(),"upwd")
			   .set("upwd",upw)
			   .update();
		renderText("1");
	}
	
	
	
	
	
}


