package com.dominosdata.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExcelReaderTest {

	@Test
	public void test() {
		ExcelReader er = new ExcelReader("input/dominosdata.xlsx");
		er.readExcelFile();
	}

}
