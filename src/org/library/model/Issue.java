package org.library.model;

import java.sql.Date;

public class Issue {
	private String callno, id, name;
	private long phone;
	private Date issueddate;
	private String returnstatus;
	public String getCallno() {
		return callno;
	}
	public void setCallno(String callno) {
		this.callno = callno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public Date getIssueddate() {
		return issueddate;
	}
	public void setIssueddate(Date issueddate) {
		this.issueddate = issueddate;
	}
	public String getReturnstatus() {
		return returnstatus;
	}
	public void setReturnstatus(String returnstatus) {
		this.returnstatus = returnstatus;
	}
	
}
