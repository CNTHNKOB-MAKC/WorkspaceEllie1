package HomeWorkSelenium_ADV_Day_01;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AssertElements1ArrayAsList {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
@Test
	public void testCheckQuickLaunchButtons() throws Exception {
		String [] expected_footers =  {"Assign Leave", "Leave List", "Timesheets"};
		
		
		driver.get(baseUrl + "/symfony/web/index.php/auth/login");
		driver.findElement(By.id("txtUsername")).clear();
		driver.findElement(By.id("txtUsername")).sendKeys("admin");
		driver.findElement(By.id("txtPassword")).clear();
		driver.findElement(By.id("txtPassword")).sendKeys("Password");
		driver.findElement(By.id("btnLogin")).click();

		// Solution 1 //she likes best the first one
		List<WebElement> all_button_footers = driver.findElements(
				By.xpath("//div[@id='dashboard-quick-launch-panel-menu_holder']//div[@class='quickLaunge']/a/img/following-sibling::*")
		);
		
		// Check that there are exactly 3 image footers
		assertEquals(
				"The actual number of footers did not match the expected number",
				3, all_button_footers.size()); 

		for (WebElement footer: all_button_footers) {
			String actual_footer = footer.getText();
			assertTrue(Arrays.asList(expected_footers).contains(actual_footer));
		}
	
	
		
		driver.findElement(By.id("welcome")).click();
		driver.findElement(By.linkText("Logout")).click();
	}

@Before
public void setUp() throws Exception {
	driver = new FirefoxDriver();
	baseUrl = "http://hrm.seleniumminutes.com/";
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}


	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		
	}

	}

