package com.system.syssalesv2;

import com.system.syssalesv2.services.ClientService;

public class TestaCpf {

	public static void main(String[] args) {
		ClientService cs = new ClientService();
		
		cs.findById(1L);
		
		
		System.out.println(cs.findById(1L).getName());
		
	}	

}
