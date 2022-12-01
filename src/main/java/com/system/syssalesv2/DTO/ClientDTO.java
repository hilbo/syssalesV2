package com.system.syssalesv2.DTO;

import java.io.Serializable;

import com.system.syssalesv2.entities.City;

public class ClientDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String email;
	private String cpfOrCnpj;
	private String type;
	private String telephone1;
	private String telephone2;
	private String address;
	private String number;
	private String complement;
	private String zipCod;
	private String cityName;
	
	public ClientDTO() {
	}
	
	public ClientDTO(Long id, String name, String email, String cpfOrCnpj, String type, String telephone1, String telephone2,
			String address, String number, String complement, String zipCod, City city) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpfOrCnpj = cpfOrCnpj;
		this.type = type;
		this.telephone1 = telephone1;
		this.telephone2 = telephone2;
		this.address = address;
		this.number = number;
		this.complement = complement;
		this.zipCod = zipCod;
		this.cityName = city.getName();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOrCnpj() {
		return cpfOrCnpj;
	}

	public void setCpfOrCnpj(String cpfOrCnpj) {
		this.cpfOrCnpj = cpfOrCnpj;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTelephone1() {
		return telephone1;
	}

	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}

	public String getTelephone2() {
		return telephone2;
	}

	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getZipCod() {
		return zipCod;
	}

	public void setZipCod(String zipCod) {
		this.zipCod = zipCod;
	}

	public String getCityName() {
		return cityName;
	}
		
	public void setCity(City city) {
		this.cityName = city.getName();
	}
}
 