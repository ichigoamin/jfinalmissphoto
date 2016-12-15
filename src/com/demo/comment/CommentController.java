package com.demo.comment;

import com.demo.common.model.Comment;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * BlogController
 * 所有 sql 与业务逻辑写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
@Before(CommentInterceptor.class)
public class CommentController extends Controller {
	public void index() {
		setAttr("commentPage", Comment.me.paginate(getParaToInt(0, 1), 10));
		render("comment.html");
	}
	
	public void add() {
	}
	
	public void save() {
		getModel(Comment.class).save();
		redirect("/comment");
	}
	
	public void edit() {
		setAttr("comment", Comment.me.findById(getParaToInt()));
	}
	
	@Before(CommentValidator.class)
	public void update() {
		getModel(Comment.class).update();
		redirect("/comment");
	}
	
	public void delete() {
		Comment.me.deleteById(getParaToInt());
		redirect("/comment");
	}
}


