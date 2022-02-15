package lab.square.lecturemacro.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertState implements IState {

	private static final String SCRIPT_OK_BUTTON = "//*[@id=\"mainframe_VFrameSet_LoginFrame_COM_ALERT_form_btn_closeTextBoxElement\"]/div";
	private static final String SCRIPT_OK_BUTTON2 = "//*[@id=\"mainframe_VFrameSet_WorkFrame_COM_ALERT_form_Static00\"]/div";

	private WebDriver driver;

	public AlertState(WebDriver driver) {
		this.driver = driver;
	}

	public IState perform() {
		System.out.println("AlertState performing");
		
		try {
			Macro.click(driver, SCRIPT_OK_BUTTON);
			driver.navigate().refresh();
			return new BeforeLoginState(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Macro.click(driver, SCRIPT_OK_BUTTON2);
			driver.navigate().refresh();
			return new BeforeLoginState(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}

		driver.navigate().refresh();
		return new BeforeLoginState(driver);	
	}

	public static boolean alertExists(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 1);
		
		boolean result = false;

		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SCRIPT_OK_BUTTON)));
			result = result || driver.findElements(By.xpath(SCRIPT_OK_BUTTON)).size() != 0;
		} catch (Exception e) {
			result = result || false;
		}
		
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SCRIPT_OK_BUTTON2)));
			result = result || driver.findElements(By.xpath(SCRIPT_OK_BUTTON2)).size() != 0;
		} catch (Exception e) {
			result = result || false;
		}
		
		return result;
	}

}
