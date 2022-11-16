package com.system.syssalesv2;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class TestaCpf {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class<?> obj = Class.forName ("com.system.syssalesv2.entities.Client");
		obj.getConstructor().newInstance();
				
		Field fieldlist[] = obj.getDeclaredFields();
		for(Field f : fieldlist) {
			System.out.println(f.getName());
		}
		
		System.out.println("###########################################################");
		
		Method mets[] = obj.getDeclaredMethods();
		for(Method met : mets) {
			System.out.println(met.getName());
		}
		
		
	}
}
