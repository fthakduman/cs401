package com.composite.util;

import java.util.HashMap;

public class Bank {
	private String name;
	private HashMap<String, Conversion> map;

	public Bank() {
		map = new HashMap<String, Conversion>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<String, Conversion> getMap() {
		return map;
	}

	public void setMap(HashMap<String, Conversion> map) {
		this.map = map;
	}
}
