package com.Expense;

import java.sql.Date;

public class Expense
{	private int id;
	private int userid;
	private String title;
	private Double amount;
	private Date date;

	public Expense(int id ,int userid,String title,Double amount,Date date){
		this.id = id;
		this.userid  = userid;
		this.title = title;
		this.amount = amount;
		this.date = date;
	}

	public int getid() {
		return id;
	}
	public int getUserid() {
		return userid;
	}
	public String getTitle() {
		return title;
	}
	public Double getAmount() {
		return amount;
	}
	public Date getDate() {
		return date;
	}
}
