package com.demo.comment;

import com.demo.common.model.Comment;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * CommentValidator.
 */
public class CommentValidator extends Validator {
	
	protected void validate(Controller controller) {
		validateRequiredString("comment.cuid", "cuidMsg", "请输入评论用户id!");
		validateRequiredString("comment.comment", "commentMsg", "请输入评论内容!");
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(Comment.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/comment/save"))
			controller.render("add.html");
		else if (actionKey.equals("/comment/update"))
			controller.render("edit.html");
	}
}
