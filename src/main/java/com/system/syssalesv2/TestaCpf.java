package com.system.syssalesv2;

import com.system.syssalesv2.validatories.Validation;
import com.system.syssalesv2.validatories.Validator;

public class TestaCpf {

	@SuppressWarnings("null")
	public static void main(String[] args) {
		
		Validator vali = new Validation();
		
		
		
		//vali.valid("15357450889");
		
		//System.out.println(IsCNPJ.isValid("58577114000189"));
		
		//System.out.println(ValidCPF.isValid("12232186865"));
		
		System.out.println(vali.validCNPJ("05396440000123"));
		
		//for (Integer string : ValidCPF.naneDigits("34485861023")) {
			//System.out.println(string);
		//}
		
	}	

}