package com.test.conversion;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.conversion.ConverterImpl;

public class ConverterImplTest {	
	@Test
	public void testConversionInterchange()
	{
		ConverterImpl ci = new ConverterImpl();
		float actual1 = ci.getConversionRate("GBP","INR",23.50f);
		float actual2 = ci.getConversionRate("INR","GBP",actual1);
		float expected = 23.50f;
		
		assertEquals(actual2, expected,0.99999);
	}
	@Test
	public void testConversionEUR()
	{
		ConverterImpl ci = new ConverterImpl();
		float actual1 = ci.getConversionRate("GBP","INR",23.50f);
		float actual2 = ci.getConversionRate("INR","EUR",actual1);
		float actual3 = ci.getConversionRate("EUR","GBP",actual2);
		float expected = 23.50f;
		assertEquals(actual3, expected,0.99999);
	}
	@Test
	public void testCurrencyCode()
	{
		ConverterImpl ci = new ConverterImpl();
		ArrayList<String> cc = ci.getCurrencyCode("united Kingdom", "india");
		String str[] = new String[cc.size()]; 
		for (int j = 0; j < cc.size(); j++) {  
	            str[j] = cc.get(j);  
	            
	    } 
		ConverterImpl ci1 = new ConverterImpl();
		ArrayList<String> cc1 = ci1.getCurrencyCode("India", "united Kingdom");
		String str1[] = new String[cc1.size()]; 
		for (int j = 0; j < cc1.size(); j++) {  
	            str1[j] = cc1.get(j);  
	           
	    }
		assertEquals(str[0],str1[1]);
		assertEquals(str[1],str1[0]);
	}
}
