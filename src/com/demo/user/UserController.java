package com.demo.user;

import java.io.File;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.common.model.Blog;
import com.demo.common.model.Comment;
import com.demo.common.model.User;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.upload.UploadFile;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * userController
 * 所有  sql 与业务逻辑写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
@Before(UserInterceptor.class)
public class UserController extends Controller {
	public void index() {
		setAttr("userPage", User.me.paginate(getParaToInt(0, 1), 10));
		getData();
		loginData();
		render("user.html");
	}
	
	public void add() {
		render("user.html");
	}
	
	@Before(UserValidator.class)
	public void save() {
		getModel(User.class).save();
		redirect("/user");
	}
	
	public void edit() {
		setAttr("user", User.me.findById(getParaToInt()));
		render("user.html");
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
	public void json(){
		getResponse().addHeader("Access-Control-Allow-Origin", "*");
		renderJson(User.me.paginate(getParaToInt(0, 1), 15));
	}
	public void loginData(){
		try{

			HttpServletRequest r= getRequest();
			r.getParameter("upwd");
			if(r.getParameter("obj").equals("1")){
				
				HttpServletResponse rp = getResponse();
				r.setCharacterEncoding("UTF-8");
				List users = User.me.find("SELECT * FROM user WHERE upwd = '"+r.getParameter("upwd")+
							"' AND uname = '"+r.getParameter("uname")+"'");
				if(users.size() > 0){
				
			        //相关操作....
					PrintWriter out= rp.getWriter();
					out.write(URLEncoder.encode("ture","UTF-8"));
					out.flush();
			        out.close();
				    System.out.println("登录成功");
			    }else{
			        //相关操作....
					PrintWriter out=rp.getWriter();
					out.write(URLEncoder.encode("false","UTF-8"));
					out.flush();
			        out.close();
			        System.out.println("登录失败");
			    }
			}
		
		}
		catch(Exception ex){
			//异常处理，略
			System.out.println("异常报错");
		}
		renderText("测试");
	}

	public void getData(){
		try{


			HttpServletRequest r= getRequest();
			if(r.getParameter("obj").equals("0")){
				r.setCharacterEncoding("UTF-8");
//				String id = r.getParameter("id");
//				System.out.println(r.getParameter("busnumber"));
//				List<Bus> th = Bus.me.find("select busnumber from bus where id="+id);
//				Bus.me.findById(id).set("busnumber",r.getParameter("busnumber")).update();
			
				System.out.println(r.getParameter("uname"));
				User u = new User();
				u.set("upwd", r.getParameter("upwd"));
				u.set("uname", r.getParameter("uname"));
				u.set("uaccount", r.getParameter("uaccount"));
				u.save();
			}
	
		}
		catch(Exception ex){
			//异常处理，略
			System.out.println("异常报错");
		}
		renderText("测试");
	}

	
}