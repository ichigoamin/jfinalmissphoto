package com.demo.request;

import javax.servlet.http.HttpServletRequest;

import com.demo.common.model.Request;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * requestController
 * 所有 sql 与业务逻辑写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
@Before(RequestInterceptor.class)
public class RequestController extends Controller {
	public void index() {
		setAttr("requestPage", Request.me.paginate(getParaToInt(0, 1), 10));
		setData();
		render("request.html");
	}

	public void add() {
	}
	
	@Before(RequestValidator.class)
	public void save() {
		getModel(Request.class).save();
		redirect("/request");
	}
	
	public void edit() {
		setAttr("request", Request.me.findById(getParaToInt()));
		render("request.html");
	}
	
	@Before(RequestValidator.class)
	public void update() {
		getModel(Request.class).update();
		redirect("/request");
	}
	
	public void delete() {
		Request.me.deleteById(getParaToInt());
		redirect("/request");
	}
	
	public void setData(){
		try{

			HttpServletRequest r = getRequest();
			
			if(r.getParameter("obj").equals("4") ){
				r.setCharacterEncoding("UTF-8");
//				String id = r.getParameter("id");
//				System.out.println(r.getParameter("busnumber"));
//				List<Bus> th = Bus.me.find("select busnumber from bus where id="+id);
//				Bus.me.findById(id).set("busnumber",r.getParameter("busnumber")).update();
				System.out.println(r.getParameter("rdetail"));
				System.out.println(r.getParameter("rdetail"));

				Request request = new Request();
				request.set("rdate", r.getParameter("rdate"));
				request.set("rdetail", r.getParameter("rdetail"));
				request.set("ruid", r.getParameter("ruid"));
				request.save();
				
			}
				
	
		}
		catch(Exception ex){
			//异常处理，略
			System.out.println("异常报错");
		}
	}
}