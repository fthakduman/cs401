package com.composite.rest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.json.JSONTokener;

import com.composite.util.Bank;
import com.composite.util.Conversion;
import com.composite.util.FileUtil;
import com.composite.util.OutputBank;
import com.composite.util.ProcessData;
import com.composite.util.Result;
import com.google.gson.Gson;

@Path("/getComposite")
public class CompositeService {
	private final String USER_AGENT = "Mozilla/5.0";
	//private final String USER_AGENT = "Mozilla/5.0";

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Result getComposite() throws Exception {

		ArrayList<Bank> normals = new ArrayList<Bank>();
		ArrayList<String> urls = new ArrayList<String>();

		
		urls.add("http://" + FileUtil.SERVER1 + ":8080/Mock/rest/getRates/Bank/1");
		urls.add("http://" + FileUtil.SERVER1 + ":8080/Mock/rest/getRates/Bank/2");
		urls.add("http://" + FileUtil.SERVER1 + ":8080/Mock/rest/getRates/Bank/3");
		urls.add("http://" + FileUtil.SERVER1 + ":8080/Mock/rest/getRates/Bank/4");

		for (String url : urls) {
			String sendGetYkb = sendGet(url);
			normals.add(parseOutput(sendGetYkb));
			System.out.println();
		}
		
		// api üzerinden ykb'ye istek atma
		ProcessData data = new ProcessData();
		Bank ykb = data.getMockRates("YKB", true, false);
		normals.add(ykb);

		HashMap<Integer, OutputBank> map = new HashMap<Integer, OutputBank>();
		double minDiff = 1000.0;
		int index = 0;

		OutputBank bestBank = new OutputBank();

		for (Bank normal : normals) {
			for (Conversion conversion : normal.getMap().values()) {
				if ("TL".equals(conversion.getMinorCurrency()) && "USD".equals(conversion.getMajorCurrency())) {
					double sellRate = Double.parseDouble(conversion.getSellRate());
					double buyRate = Double.parseDouble(conversion.getBuyRate());
					double diffBetweenSellAndBuy = sellRate - buyRate;
					if (minDiff > diffBetweenSellAndBuy) {
						minDiff = diffBetweenSellAndBuy;
						bestBank.setBankName("MOST RELAIBLE BANK NAME IS: " + normal.getName());
						bestBank.setSellRate(conversion.getSellRate());
						bestBank.setBuyRate(conversion.getBuyRate());
						bestBank.setDiff(minDiff + "");
					}
					index++;
					OutputBank outputBank = new OutputBank();
					outputBank.setBankName(normal.getName());
					outputBank.setSellRate(conversion.getSellRate());
					outputBank.setBuyRate(conversion.getBuyRate());
					outputBank.setDiff(diffBetweenSellAndBuy + "");
					map.put(index, outputBank);
				}
			}
		}

		Result result = new Result();
		result.setBanks(map);
		result.setSelectedBank(bestBank);

		return result;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/getRates/bank/{param}")
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


	// HTTP GET request
	private String sendGet(String url) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("Sending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		return response.toString();
	}

	private Bank parseOutput(String sendGet) {
		Gson g = new Gson();
		Bank value = g.fromJson(sendGet, Bank.class);
		return value;
	}
}