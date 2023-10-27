package BMC.UIAssingment;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageObject {

	
	
	@FindBy(name = "q")
	private WebElement googleSearchBox;

	public WebElement getGoogleSearchBox() {
		return googleSearchBox;
	}
	
	@FindBy(xpath = "//h3")
	private List<WebElement> serachH3;

	public List<WebElement> getSerachH3() {
		return serachH3;
	}


}
