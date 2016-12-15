package com.demo.request;

import com.demo.common.model.Request;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * requestValidator.
 */
public class RequestValidator extends Validator {
	
	protected void validate(Controller controller) {
		validateRequiredString("request.rtitle", "rtitleMsg", "请输入需求标题!");
		validateRequiredString("request.rdate", "rdateMsg", "请输入需求截止日期!");
		validateRequiredString("request.rdetail", "rdetailMsg", "请输入需求内容!");
		validateRequiredString("request.ruid", "ruidMsg", "请输入需求用户编号!");
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(Request.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/request/save"))
			controller.render("add.html");
		else if (actionKey.equals("/request/update"))
			controller.render("edit.html");
	}
}
