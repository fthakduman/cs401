package com.composite.modelbasedtesting1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Adapter1 {

	WebDriver mDriver;

	public Adapter1() {

		// if you didn't update the Path system variable to add the full directory path
		// to the executable as above mentioned then doing this directly through code
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\MustafaPC\\Desktop\\geckodriver\\geckodriver");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\MustafaPC\\Desktop\\geckodriver\\chromedriver.exe");
		// Now you can Initialize marionette driver to launch firefox
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability("marionette", true);

		mDriver = new ChromeDriver(capabilities);
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
		if(!pageSource.contains("averageRate")) {
			throw new Exception();
		}
	}

	public void bank1()  throws Exception{
		mDriver.get("http://localhost:8080/Composite/rest/getComposite/getRates/bank/1");
		String pageSource = mDriver.getPageSource();
		if(!pageSource.contains("averageRate")) {
			throw new Exception();
		}
	}

	public void bank2()  throws Exception{
		mDriver.get("http://localhost:8080/Composite/rest/getComposite/getRates/bank/2");
		String pageSource = mDriver.getPageSource();
		if(!pageSource.contains("averageRate")) {
			throw new Exception();
		}
	}

	public void bank3() throws Exception {
		mDriver.get("http://localhost:8080/Composite/rest/getComposite/getRates/bank/3");
		String pageSource = mDriver.getPageSource();
		if(!pageSource.contains("averageRate")) {
			throw new Exception();
		}
	}

	public void bank4() throws Exception {
		mDriver.get("http://localhost:8080/Composite/rest/getComposite/getRates/bank/4");
		String pageSource = mDriver.getPageSource();
		if(!pageSource.contains("averageRate")) {
			throw new Exception();
		}
	}
}