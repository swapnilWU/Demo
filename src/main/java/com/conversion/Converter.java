package com.conversion;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style=Style.DOCUMENT)
public interface Converter {
	@WebMethod(operationName="getCodeCurrency")
	ArrayList<String>getCurrencyCode(@WebParam (name= "Sender")String sender, @WebParam (name ="Receiver")String receiver);
	@WebMethod(operationName="getConversionRate")
	float getConversionRate(@WebParam (name= "sender")String sender,@WebParam (name= "receiver")String receiver,@WebParam (name= "Amount")float val);
}
