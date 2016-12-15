package com.demo.request;

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
}


