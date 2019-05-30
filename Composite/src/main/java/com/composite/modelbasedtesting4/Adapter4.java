package com.composite.modelbasedtesting4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Adapter4 {

	private final static String USER_AGENT = "Mozilla/5.0";
	WebDriver mDriver;

	public Adapter4() {

		// if you didn't update the Path system variable to add the full directory path
		// to the executable as above mentioned then doing this directly through code
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\MustafaPC\\Desktop\\geckodriver\\chromedriver.exe");

		// Now you can Initialize marionette driver to launch firefox
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability("marionette", true);

		mDriver = new ChromeDriver();
		mDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

	public void reset() {
		mDriver.quit();
		mDriver = new ChromeDriver();
		// Reset the fire database...
	}

	public void ykb() throws Exception {
		mDriver.get("http://localhost:8080/Composite/rest/getComposite/getRates/bank/YKB");
		String pageSource = mDriver.getPageSource();
		if (!pageSource.contains("averageRate")) {
			throw new Exception();
		}
	}

	public void bank1() throws Exception {
		sendGet("http://localhost:8080/Composite/rest/getComposite/getRates/bank/Test1");
	}

	public void bank2() throws Exception {
		mDriver.get("http://localhost:8080/Composite/rest/getComposite/getRates/bank/2");
		String pageSource = mDriver.getPageSource();
		if (!pageSource.contains("averageRate")) {
			throw new Exception();
		}
	}

	public void bank3() throws Exception {
		mDriver.get("http://localhost:8080/Composite/rest/getComposite/getRates/bank/3");
		String pageSource = mDriver.getPageSource();
		if (!pageSource.contains("averageRate")) {
			throw new Exception();
		}
	}

	public void bank4() throws Exception {
		mDriver.get("http://localhost:8080/Composite/rest/getComposite/getRates/bank/4");
		String pageSource = mDriver.getPageSource();
		if (!pageSource.contains("averageRate")) {
			throw new Exception();
		}
	}

	// HTTP GET request
	private static String sendGet(String url) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		// int responseCode = con.getResponseCode();
		// System.out.println("Response Code : " + responseCode);

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
}