package com.pizzaparty.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.pizzaparty.util.ExcelReader;

public class ExcelReaderTest {

	@Test
	public void test() {
		ExcelReader er = new ExcelReader("input/dominosdata.xlsx");
		er.readExcelFile();
	}

}
