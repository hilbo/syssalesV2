package com.system.syssalesv2;

import com.system.syssalesv2.validatories.Validator;
import com.system.syssalesv2.validatories.implementations.Validation;

public class TestaCpf {
	
	public static void main(String[] args) {
		
		Validator vali = new Validation();
		
		
		
		//vali.valid("15357450889");
		
		//System.out.println(IsCNPJ.isValid("58577114000189"));
		
		//System.out.println(ValidCPF.isValid("12232186865"));
		
		System.out.println(vali.validCNPJ("05396440000126"));
		
		//for (Integer string : ValidCPF.naneDigits("34485861023")) {
			//System.out.println(string);
		//}
		
	}	

}
