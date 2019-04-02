package com.conversion;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Currency;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.jws.WebService;

@WebService(endpointInterface="com.conversion.Converter")
public class ConverterImpl implements Converter {

	public ArrayList<String> getCurrencyCode(String sender,String receiver) {
		return getAvailableCurrencies(sender,receiver);
	}
	private ArrayList<String> getAvailableCurrencies(String c,String r) {
		
		int senderFlag = 0;
		int receiverflag = 0;
		ArrayList<String> myCode = new ArrayList<>();	
		Locale[] locales = Locale.getAvailableLocales();
        Map<String, String> currencies = new LinkedHashMap<>();
        for (Locale locale : locales) {	
            	if (locale.getDisplayCountry().equalsIgnoreCase(c))
            	{
            		senderFlag = 1;
            		currencies.put(locale.getDisplayCountry(),Currency.getInstance(locale).getCurrencyCode());
            		break;
            	}
        } 
        for (Locale locale : locales) {	
        	if (locale.getDisplayCountry().equalsIgnoreCase(r))
        	{
        		receiverflag = 1;
        		currencies.put(locale.getDisplayCountry(),Currency.getInstance(locale).getCurrencyCode());
        		
        		break;
        	}	
        }
        if(senderFlag == 0)
		{
			myCode.add("sender not found");
			return myCode;
		}
        if(receiverflag == 0){
		    myCode.add("receiver not found");
		    return myCode;
        }
        for(Entry<String, String> country : currencies.entrySet()) 
        {
            String currencyCode = currencies.get(country.getValue());
            myCode.add(currencyCode);
        }
        return myCode;
    }
	
	
	public float getConversionRate(String sender, String receiver, float val) {
		float output = getUrlContents("https://api.exchangeratesapi.io/latest?base="+sender+"&symbols="+receiver);
		return val*output;
	}
	private static float getUrlContents(String theUrl)
	{
	    StringBuilder content = new StringBuilder();
	    try
	    {
	      URL url = new URL(theUrl);
	      URLConnection urlConnection = url.openConnection();
	      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
	      String line;
	      while ((line = bufferedReader.readLine()) != null)
	      {
	        content.append(line + "\n");
	      }
	      bufferedReader.close();
	    }
	    catch(Exception e)
	    {
	      e.printStackTrace();
	    }
	    String[] arrs = content.toString().split(",");
	    String[] as  = arrs[1].split(":");
	    String assVal = as[2].replaceAll("\\}", "");
	    return Float.parseFloat(assVal);
	}
	
}