package com.dominosdata.model;

public class Delivery {
	private String name;
	private String phone;
	private String address;
	private double price;
	private double tip;
	
	public Delivery(String name, String phone, String address, double price) {
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.price = price;
	}
	
	public Delivery(String name, String phone, String address, double price, double tip) {
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.price = price;
		this.tip = tip;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public double getTip() {
		return this.tip;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public String toString() {
		return "" + name + " " + address + " " + price;
	}
}
