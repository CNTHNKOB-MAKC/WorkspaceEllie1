package Day_10;

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;
import static org.apache.commons.lang3.StringUtils.join;

public class AlphaOrderHW8WDBFormat {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://hrm.tehportal.net/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testAlphaOrderHW8() throws Exception {
		selenium.open("/login.php");
		assertEquals("OrangeHRM - New Level of HR Management",
				selenium.getTitle());
		selenium.type("name=txtUserName", "admin");
		selenium.type("name=txtPassword", "password");
		selenium.click("name=Submit");
		selenium.waitForPageToLoad("45000");
		assertEquals("OrangeHRM", selenium.getTitle());
		selenium.selectFrame("rightMenu");
		selenium.click("link=Employee Name");
		selenium.waitForPageToLoad("45000");

		Number total_rows = selenium.getXpathCount("//tbody/tr");

		for (int i = 1; i < total_rows.intValue(); i++) {

			String top_name = selenium.getText("//tbody/tr[" + i + "]/td[3]/a");
			String bottom_name = selenium.getText("//tbody/tr[" + (i+1) + "]/td[3]/a");
			// top_name.compareTo(bottom_name);

			assertTrue(top_name.compareToIgnoreCase(bottom_name) <= 0);
			// OR
			int result = top_name.compareToIgnoreCase(bottom_name);
			assertFalse("The names are not in the expected alpha order.",
					result > 0);

		}

		selenium.selectWindow("title=OrangeHRM");
		// String[] all_ids = selenium.getAllWindowIds();
		String[] all_titles = selenium.getAllWindowTitles();
		selenium.click("link=Logout");
		// Thread.sleep(3000);
		selenium.waitForPageToLoad("45000");
		assertEquals("OrangeHRM - New Level of HR Management",
				selenium.getTitle());
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
