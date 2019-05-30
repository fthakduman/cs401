package com.composite.util;

import java.util.HashMap;

public class Result {
	HashMap<Integer, OutputBank> banks;
	OutputBank selectedBank;

	public Result() {
		banks = new HashMap<Integer, OutputBank>();
	}

	public HashMap<Integer, OutputBank> getBanks() {
		return banks;
	}

	public void setBanks(HashMap<Integer, OutputBank> banks) {
		this.banks = banks;
	}

	public OutputBank getSelectedBank() {
		return selectedBank;
	}

	public void setSelectedBank(OutputBank selectedBank) {
		this.selectedBank = selectedBank;
	}

}
