package com.mock.rest;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.json.JSONTokener;

import com.mock.util.Bank;
import com.mock.util.FileUtil;
import com.mock.util.ProcessData;

@Path("/getRates/Bank")
public class MockService {
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{param}")
	public Bank getYKB(@PathParam("param") String bankName) throws FileNotFoundException {
		if (!"YKB".equals(bankName)) {
			String bankConfig = getBankConfig(bankName);
			FileReader fileReader = null;
			try {
				fileReader = new FileReader(bankConfig);
			} catch (FileNotFoundException e) {
				throw new FileNotFoundException();
			}
			JSONTokener tokener = new JSONTokener(fileReader);
			JSONObject object = new JSONObject(tokener);

			ProcessData data = new ProcessData(object);
			if (bankName != null && bankName.startsWith("Test"))
				return data.getMockRates(bankName, false, true);
			else if ("1".equals(bankName) || "2".equals(bankName) || "3".equals(bankName) || "4".equals(bankName))
				return data.getMockRates(bankName, false, false);
			return null;
		} else {
			ProcessData data = new ProcessData();
			if ("YKB".equals(bankName))
				return data.getMockRates(bankName, true, false);
			return null;
		}
	}

	public String getBankConfig(String bankName) {
		String config = "";
		if ("1".equals(bankName)) {
			config = FileUtil.LOCATION + "bank1config.json";
		} else if ("2".equals(bankName)) {
			config = FileUtil.LOCATION + "bank2config.json";
		} else if ("3".equals(bankName)) {
			config = FileUtil.LOCATION + "bank3config.json";
		} else if ("4".equals(bankName)) {
			config = FileUtil.LOCATION + "bank4config.json";
		} else if ("Test1".equals(bankName)) {
			return FileUtil.LOCATION + "test1config.json";
		} else if ("Test2".equals(bankName)) {
			return FileUtil.LOCATION + "test2config.json";
		}
		return config;
	}
}