package com.demo.user;

import com.demo.common.model.User;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * UserValidator.
 */
public class UserValidator extends Validator {
	
	protected void validate(Controller controller) {
		validateRequiredString("user.upwd", "upwdMsg", "请输入密码!");
		validateRequiredString("user.uname", "unameMsg", "请输入昵称!");
		validateRequiredString("user.uaccount", "uaccountMsg", "请输入注册账户!");
		validateRequiredString("user.uimage", "uimageMsg", "请输入头像地址!");
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(User.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/user/save"))
			controller.render("add.html");
		else if (actionKey.equals("/user/update"))
			controller.render("edit.html");
	}
}
