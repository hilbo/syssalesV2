package com.system.syssalesv2.validatories.checkers;

public abstract class IsTelephone {

	public static boolean isValid(String value) {

		String[] strs = value.split("");
		for (String str : strs) {
			try {
				Integer.parseInt(str);
			} catch (NumberFormatException e) {
				return false;
			}
		}
		
		if (strs.length < 11) {
			return false;
		}
		return true;
	}
}
