package com.dominosdata.util;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import com.dominosdata.model.Delivery;

public class ExcelReader {
	private static final int ORDER_NUM_LENGTH = 6;
	
	private String filename;
		
	public ExcelReader(String filename) {
		this.filename = filename;
	}
	
	public ArrayList<Delivery> readExcelFile() {
		ArrayList<Delivery> deliveries = new ArrayList<>();
		
		XSSFWorkbook book = null;
		try {
			book = new XSSFWorkbook(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		XSSFSheet pizza = book.getSheetAt(0);
		
		int i = 0;
		int delCount = 0;
		while (i < pizza.getPhysicalNumberOfRows()) {
			Row current = pizza.getRow(i);
			String firstCell = current.getCell(0).toString();
			
			try {
				// attempt to get int from first cell
				double orderNumDouble = Double.parseDouble(firstCell);
				int orderNum = (int) orderNumDouble;
				
				// if the first cell is an order number
				if (String.valueOf(orderNum).length() == ORDER_NUM_LENGTH && current.getPhysicalNumberOfCells() >= 6) {
					// get cost and name/address cells
					double cost = Double.parseDouble(current.getCell(5).toString());
					String nameAndAddress = current.getCell(2).toString();
					
					// split name/address cell by spaces
					String[] words = nameAndAddress.split(" ");
					
					// boolean for finding int to separate name and address
					int idx = 0;
					
					// find name by adding strings from words until a number is found
					StringBuilder nameBuilder = new StringBuilder();
					while (idx < words.length) {
						if (isNumeric(words[idx])) {
							break;
						}
						
						if (!words[idx].isEmpty()) {
							if (words[idx].charAt(0) == '(') {
								break;
							}
						}
						
						nameBuilder.append(words[idx]);
						nameBuilder.append(" ");
						
						idx++;
					}
					String name = nameBuilder.toString().trim();
					
					// the rest of the cell will be the address
					StringBuilder addressBuilder = new StringBuilder();
					for (int j = idx; j < words.length; j++) {
						addressBuilder.append(words[j]);
						addressBuilder.append(" ");
					}
					String addressAndPhone = addressBuilder.toString().trim();
					String address = "";
					String phone = "";
					
					if (!addressAndPhone.equals("")) {
						phone = addressAndPhone.substring(addressAndPhone.indexOf("("));
						address = addressAndPhone.substring(0, addressAndPhone.indexOf("("));
					}
					
					if (phone.equals("")) {
						phone = "PHONE_NOT_FOUND";
					}
					
					if (address.equals("")) {
						address = "ADDRESS_NOT_FOUND";
					}
															
					// create new delivery object and add to list
					Delivery d = new Delivery(name, phone, address, cost);
					
					deliveries.add(d);
					
					delCount++;
					
					System.out.println("Delivery [");
					System.out.println("   " + d.getName());
					System.out.println("   " + d.getPhone());
					System.out.println("   " + d.getAddress());
					System.out.println("   " + d.getPrice());
					System.out.println("]\n");
					System.out.println("count: " + delCount);
				}
				
			} catch (NullPointerException npe) {
				// skip this row
			} catch (NumberFormatException nfe) {
				// skip this row
			}
			
			i++;
		}
		
		System.out.println("TOTAL DELIVERIES FOUND: " + deliveries.size());
		return deliveries;
	}
	
	private static boolean isNumeric(String s) {
		try {
			Double d = Double.parseDouble(s);
		} catch (NumberFormatException e) {
			return false;
		}
		
		return true;
	}
	
	public Delivery parseDelivery() {
		return null;
	}
}
