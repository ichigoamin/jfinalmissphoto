package com.demo.draw;

import com.demo.common.model.Draw;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * drawValidator.
 */
public class DrawValidator extends Validator {
	
	protected void validate(Controller controller) {
		validateRequiredString("draw.dlike", "dlikeMsg", "请输入点赞数!");
		validateRequiredString("draw.durl", "durlMsg", "请输入画作地址!");
		validateRequiredString("draw.udid", "udidMsg", "请输入画作作者!");
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(Draw.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/draw/save"))
			controller.render("add.html");
		else if (actionKey.equals("/draw/update"))
			controller.render("edit.html");
	}
}
