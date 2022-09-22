package com.system.syssalesv2.validatories.checkers;

import java.util.ArrayList;
import java.util.List;

public abstract class IsCNPJ {

	public static Boolean isValid(String cnpj) {
		// Verifica se o cnpj tem 14 caracteres

		if (cnpj.length() != 14) {
			return false;
		}

		//Verifica se o cnpj tem números iguais em sequencia 
		if (cnpj == "00000000000000" || cnpj == "11111111111111" || cnpj == "22222222222222" || cnpj ==
					"33333333333333" || cnpj == "44444444444444" || cnpj == "55555555555555" || cnpj ==
					"66666666666666" || cnpj == "77777777777777" || cnpj == "88888888888888" || cnpj ==
					"99999999999999" || cnpj == "01234567890123") { 
		return false;
		}
		
		// Cria lista com a string
		List<String> originList = new ArrayList<>();
		String[] listTmp = cnpj.split("");
		for (String str : listTmp) {
			originList.add(str);
		}
		// Cria lista com doze digitos
		String[] newList12d = new String[12];

		// Inseri ao array os doze primeiros dígitos do cnpj
		for (int i = 0; i < 12; i++) {
			newList12d[i] = originList.get(i);
		}

		// Calcula o primeiro dígito verificador
		int dig1 = 0;
		Integer index1 = 5;
		Integer position = 0;
		while (index1 != 1) {
			dig1 = dig1 + (Integer.parseInt(newList12d[position]) * index1);
			index1 = index1 - 1;
			position = position + 1;
		}

		index1 = 9;
		position = 4;
		while (position <= 11) {
			dig1 = dig1 + (Integer.parseInt(newList12d[position]) * index1);
			index1 = index1 - 1;
			position = position + 1;
		}
		if (dig1 % 11 == 0 || dig1 % 11 == 1) {
			dig1 = 0;
		}
		if (dig1 % 11 != 0 || dig1 % 11 != 1) {
			dig1 = 11 - dig1 % 11;
		}

		// Calcula o segundo dígito verificador
		Integer dig2 = 0;
		index1 = 6;
		position = 0;
		while (index1 != 1) {
			dig2 = dig2 + (Integer.parseInt(listTmp[position]) * index1);
			index1 = index1 - 1;
			position = position + 1;
		}

		index1 = 9;
		position = 5;
		while (position <= 12) {
			dig2 = dig2 + (Integer.parseInt(listTmp[position]) * index1);
			index1 = index1 - 1;
			position = position + 1;
		}
		if (dig2 % 11 == 0 || dig2 % 11 == 1) {
			dig2 = 0;
		}
		if (dig2 % 11 != 0 || dig2 % 11 != 1) {
			dig2 = 11 - dig2 % 11;
		}

		if (dig1 == Integer.parseInt(listTmp[12]) && dig2 == Integer.parseInt(listTmp[13])) {
			return true;
		}
		return false;
	}
}
