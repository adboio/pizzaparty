package com.pizzaparty.util;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.pizzaparty.geocoding.Geocode;
import com.pizzaparty.geocoding.Geometry;
import com.pizzaparty.model.Delivery;

public class MapsTest {
	
	private String NO_ADDRESS = "ADDRESS_NOT_FOUND";

	@Test
	public void test() {		
		ExcelReader er = new ExcelReader("input/pizzaparty-small.xlsx");
		ArrayList<Delivery> deliveries = er.readExcelFile();
		
		for (int i = 0; i < deliveries.size(); i++) {
			System.out.println(deliveries.get(i).getAddress());
		}
		
		for (int i = 0; i < deliveries.size(); i++) {
			if (!deliveries.get(i).getAddress().equals(NO_ADDRESS)) {
				double lat;
				double lng;
				Maps m = null;
				try {
					m = new Maps(deliveries.get(i).getAddress());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				List<Geocode> results = m.getGeocode().getResults();
				
				if (results.size() > 0) {
					Geometry gm = results.get(0).getGeometry();
					
					lat = gm.getLocation().getLat();
					lng = gm.getLocation().getLng();
					
					System.out.println(deliveries.get(i).getAddress() + " " + lat + ", " + lng);
				}
			}
		}
		
	}

}
