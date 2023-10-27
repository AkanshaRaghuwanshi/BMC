package BMC.Util;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;


public class Commands {

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Commands.class);
	static Properties props;
	static FileInputStream fis; // to read the config file
	
	public static Properties getPropertyValue(String fileName) throws Exception {
		if (props == null)
			props = new Properties();
		fis = new FileInputStream(
				new java.io.File(".").getCanonicalPath() + "\\properties\\" + fileName + ".properties");
		props.load(fis);
		
		return props;
	}
	
	public static WebDriver openBrowser(String url, WebDriver driver) throws Exception {
		try {
			
			
			
			String proDir = System.getProperty("user.dir");
			
			
			System.setProperty("webdriver.chrome.driver", proDir + props.getProperty("webDeriverPath"));
			
			
			
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.get(url);
		} catch (WebDriverException e) {
			log.info("Unable to open the browser :" + e);
			throw new Exception("Unable to open the browser :" + e);
		}
		return driver;

	}


}
