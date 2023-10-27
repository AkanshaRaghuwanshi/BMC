package BMC.UIAssingment;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import BMC.Util.Commands;

public class BmcAmazon_TC {

	private Logger log = Logger.getLogger(this.getClass());
	private String tcName = "Amazon Test Case";
	
	public PageObject pageObject;
	
	public static String url;
	WebDriver driver;
	
	private Properties prop;
	
	@BeforeMethod
	public void initTestcases() throws Exception {
		System.out.println("Started : " + tcName);
		log.info("Started Test Case:" + tcName);
		
		
		prop = Commands.getPropertyValue("config");
		 url = prop.getProperty("googleURL");
		
		 
		 
		driver = Commands.openBrowser(url,driver);
		
		pageObject = PageFactory.initElements(driver, PageObject.class);
		
		System.out.println("Browser opened");
		log.info("Browser opened");
		
		
		
	}
	
	@Test(groups = "Amazon")
	public void mainTest() throws Exception {
		
		
		WebElement searchBox = pageObject.getGoogleSearchBox();
		searchBox.clear();
        searchBox.sendKeys("amazon.in");
        searchBox.submit();
        
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        List<WebElement> searchResults = pageObject.getSerachH3();
        System.out.println(searchResults.size());
        
        System.out.println("Search All Link");
        log.info("Search All Link");
        
        for (WebElement result : searchResults) {
            System.out.println(result.getText());
            log.info(result.getText());
        }
        
     // 5. Click on the link which takes you to the Amazon login page
        // 6. Log in to https://www.amazon.in/ (You need to add your login logic here)
        WebElement amazonLink = driver.findElement(By.partialLinkText("Amazon.in"));
        amazonLink.click();
        
        System.out.println("Found Amazon");
        log.info("Found Amazon");
       
        // 7. Click on all buttons on search & select Electronics
         
        Select s = new Select(driver.findElement(By.xpath("//select[@title='Search in']")));
        s.selectByVisibleText("Electronics");
        
     // 8. search dell computers
            
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("dell computers");
        driver.findElement(By.cssSelector("input[id='nav-search-submit-button']")).click();
        
     // 9. Apply the filter of range Rs 30000 to 50000 (You need to add filter logic here)
        driver.findElement(By.id("low-price")).sendKeys("30000");
        driver.findElement(By.id("high-price")).sendKeys("50000");
        driver.findElement(By.cssSelector("input.a-button-input")).click();
        
        // click dell check-box
     WebElement checkbox =driver.findElement(By.xpath("//li[@id='p_89/Dell']//i[@class='a-icon a-icon-checkbox']"));
     checkbox.click();
        
     // 10. Print all the products whose rating is 5 out of 5 (You need to add rating filter logic here)
        ArrayList<String> searchTopRateResults = new ArrayList<String>();
        
        do {
        	
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        List<WebElement> searchTopRate = driver.findElements(By.xpath("//i[contains(@class,'a-star-small-5')]//ancestor::div[@data-component-type=\"s-search-result\"]//h2//span")); 
        
        
        System.out.println(searchTopRate.size());
        
        
        System.out.println("Search All Dell Computers Results");
        log.info("Search All Dell Computers Results");
        
        for (WebElement result : searchTopRate) {
        	searchTopRateResults.add(result.getText());
        	System.out.println("");
            
        }
        
        System.out.println("Total Search Top Results " + searchTopRateResults.size());
        
        //Click on Next Button
        try {
        	driver.findElement(By.cssSelector("a.s-pagination-next")).click();
        	System.out.println("Next Page Found");
            log.info("Next Page Found");
        }catch (Exception e) {
        	System.out.println("Next Page Not Found");
            log.info("Next Page Not Found");
            break;
		}
        }while(true);
        
        //Print Product with 5 Star
        for(String result : searchTopRateResults) {
        	System.out.println(result);
            log.info(result);
        }
	}
	
	@AfterMethod
	public void close() {
	
		try{
 			driver.quit();
 			}
 			catch(Exception e){
 				System.out.println("Browser was closed");
				log.info("Browser was closed");
 			}
}

}

