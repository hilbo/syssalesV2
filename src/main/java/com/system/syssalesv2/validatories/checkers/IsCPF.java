package com.system.syssalesv2.validatories.checkers;

import java.util.ArrayList;
import java.util.List;

public abstract class IsCPF{
	
	public static Boolean isValid(String cpf) {
		// Verifica se o cpf tem 11 caracteres
		if (cpf.length() != 11) {
			return false;
		}
		
		//Verifica se o cpf tem números iguais em sequencia
		if (cpf == "00000000000" || cpf == "11111111111" || cpf == "22222222222"
			|| cpf == "33333333333" || cpf == "44444444444" || cpf == "55555555555"
			|| cpf == "66666666666" || cpf == "77777777777" || cpf == "88888888888"
			|| cpf == "99999999999" || cpf == "01234567890")
		{
			return false;
		}

		// Cria lista com a string
		List<String> originList = new ArrayList<>();
		String[] listTmp = cpf.split("");
		for (String str : listTmp) {
			originList.add(str);
		}
		// Cria lista com nove digitos
		String[] newList9d = new String[9];
		// Valor atribuido ao primeiro dígito verificador
		int dig1 = 0;

		// Inseri ao array os nove primeiros dígitos do cpf
		for (int i = 0; i < 9; i++) {
			newList9d[i] = originList.get(i);
		}

		// Calcula o primeiro dígito verificador
		Integer index1 = 10;
		for (String str : newList9d) {
			dig1 = dig1 + (Integer.parseInt(str) * index1);
			index1 = index1 - 1;
		}
		if (dig1 % 11 == 0 || dig1 % 11 == 1) {
			dig1 = 0;
		}
		if (dig1 % 11 != 0 || dig1 % 11 != 1) {
			dig1 = 11 - dig1 % 11;
		}

		// Calcula o segundo dígito verificador
		int dig2 = 0;
		Integer index2 = 11;
		List<String> newList11d = new ArrayList<>();
		for (String strTmp : newList9d) {
			newList11d.add(strTmp);
		}
		newList11d.add(String.valueOf(dig1));

		for (String str : newList11d) {
			dig2 = dig2 + (Integer.parseInt(str) * index2);
			index2 = index2 - 1;
		}
		if (dig2 % 11 == 0 || dig2 % 11 == 1) {
			dig1 = 0;
		}
		if (dig2 % 11 != 0 || dig2 % 11 != 1) {
			dig2 = 11 - dig2 % 11;
		}
		
		//Verifica se os dígitos verificadores são válidos.
		if (dig1 == Integer.parseInt(originList.get(9)) 
			&& dig2 == Integer.parseInt(originList.get(10))) {
			return true;
		}
		return false;
	}
}
