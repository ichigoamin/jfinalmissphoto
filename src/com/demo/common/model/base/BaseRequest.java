package com.demo.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseRequest<M extends BaseRequest<M>> extends Model<M> implements IBean {

	public void setRid(java.lang.Integer rid) {
		set("rid", rid);
	}

	public java.lang.Integer getRid() {
		return get("rid");
	}

	public void setRtitle(java.lang.String rtitle) {
		set("rtitle", rtitle);
	}

	public java.lang.String getRtitle() {
		return get("rtitle");
	}

	public void setRdate(java.util.Date rdate) {
		set("rdate", rdate);
	}

	public java.util.Date getRdate() {
		return get("rdate");
	}

	public void setRdetail(java.lang.String rdetail) {
		set("rdetail", rdetail);
	}

	public java.lang.String getRdetail() {
		return get("rdetail");
	}

	public void setRuid(java.lang.Integer ruid) {
		set("ruid", ruid);
	}

	public java.lang.Integer getRuid() {
		return get("ruid");
	}

}
