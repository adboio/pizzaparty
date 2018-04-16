package com.pizzaparty.util;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class MapsTest {

	@Test
	public void test() {
		Maps m = null;
		try {
			m = new Maps("868 Challenge Club Dr, Clinton, NC");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m.getGeocode();
	}

}
