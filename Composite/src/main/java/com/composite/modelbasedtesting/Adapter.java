
package com.composite.modelbasedtesting;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Adapter {

	WebDriver mDriver;

	public Adapter() {

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
		mDriver.close();
		mDriver = new ChromeDriver();
		// Reset the fire database...
	}

	public void composite() {
		mDriver.get("http://localhost:8080/Composite/rest/getComposite");
	}
	
	public void ykb() {
		mDriver.get("http://localhost:8080/Mock/rest/getRates/Bank/YKB");
	}

	public void bank1() {
		mDriver.get("http://localhost:8080/Mock/rest/getRates/Bank/1");
	}

	public void bank2() {
		mDriver.get("http://localhost:8080/Mock/rest/getRates/Bank/2");
	}

	public void bank3() {
		mDriver.get("http://localhost:8080/Mock/rest/getRates/Bank/3");
	}

	public void bank4() {
		mDriver.get("http://localhost:8080/Mock/rest/getRates/Bank/4");
	}
}