package com.demo.draw;

import com.demo.common.model.Draw;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * drawController
 * 所有 sql 与业务逻辑写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
@Before(DrawInterceptor.class)
public class DrawController extends Controller {
	public void index() {
		setAttr("drawPage", Draw.me.paginate(getParaToInt(0, 1), 10));
		render("draw.html");
	}
	
	public void add() {
	}
	
	@Before(DrawValidator.class)
	public void save() {
		getModel(Draw.class).save();
		redirect("/draw");
	}
	
	public void edit() {
		setAttr("draw", Draw.me.findById(getParaToInt()));
	}
	
	@Before(DrawValidator.class)
	public void update() {
		getModel(Draw.class).update();
		redirect("/draw");
	}
	
	public void delete() {
		Draw.me.deleteById(getParaToInt());
		redirect("/draw");
	}
}


