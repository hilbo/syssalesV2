package com.system.syssalesv2.validatories.checkers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class IsEmail {

	public static boolean isValid(String value) {

		List<String> strs = new ArrayList<>();
		String[] str = value.split("");
		for (int i = 0; i < str.length; i++) {
			strs.add(str[i]);
		}
		// Verifica se tem caracteres inválidos
		List<String> caracteries = new ArrayList<>();
		caracteries.addAll(Arrays.asList("(", ")", "[", "]", "{", "}", ",", ";"));
		for (String strTmp : strs) {
			if (caracteries.contains(strTmp)) {
				return false;
			}
		}
		// Verifica se tem pelo menos 3 caracteres
		if (value.length() < 4) {
			return false;
		}
		// Verifica se tem pelo menos um "@"
		if (!strs.contains("@")) {
			return false;
		}
		// Verifica se tem mais que 1 "@"
		Integer count = 0;
		for (String strTmp : strs) {
			if (strTmp.contains("@")) {
				count = count + 1;
			}
		}
		if (count > 1) {
			return false;
		}
		// Verifica se tem espaços em branco
		if (value.contains(" ")) {
			return false;
		}
		// Verifica se tem pelo menos 1 "."
		if (!value.contains(".")) {
			return false;
		}
		// Define a posição do "@"
		Integer positionDivider = strs.indexOf("@");

		// Cria uma lista de caracteres até o "@"
		List<String> user = new ArrayList<>();
		for (int i = 0; i < positionDivider; i++) {
			user.add(strs.get(i));
		}
		// Verifica se há ponto no início ou fim da lista
		Integer size = user.size() - 1;
		if (user.get(0).equals(".") || user.get(size).equals(".")) {
			return false;
		}
		
		// Cria uma lista de caracteres após o "@"
		List<String> listDomain = new ArrayList<>();
		for (int i = positionDivider + 1; i < strs.size(); i++) {
			listDomain.add(strs.get(i));
		}
		// Verifica se há ponto no início ou fim da lista
		Integer size2 = listDomain.size() - 1;
		if (listDomain.get(0).equals(".") || listDomain.get(size2).equals(".")) {
			return false;
		}
		// Verirfica se tem pelo menos 2 caracteres após o "@"
		if (listDomain.size() < 2) {
			return false;
		}
		// Verifica se tem pelo menos 1 "." após o "@"
		if (!listDomain.contains(".")) {
			return false;
		}
	return true;

	}

}
