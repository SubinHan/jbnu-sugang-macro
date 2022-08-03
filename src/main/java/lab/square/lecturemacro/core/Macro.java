package lab.square.lecturemacro.core;

import java.util.concurrent.atomic.AtomicBoolean;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Macro implements Runnable, IHeartbeatable {
	
//	private static final String ID_INPUT = "//*[@id=\"mainframe_VFrameSet_LoginFrame_form_div_login_div_form_edt_hakbun_input\"]";
//	private static final String PASSWORD_INPUT = "//*[@id=\"mainframe_VFrameSet_LoginFrame_form_div_login_div_form_edt_passwd_input\"]";
//	private static final String TOPMENU_BUTTON = "//*[@id=\"mainframe_VFrameSet_TopFrame_form_div_top_mnu_topmenu_0001\"]";
//	private static final String SHOPPINGBAG_BUTTON = "//*[@id=\"mainframe_VFrameSet_WorkFrame_form_div_work_btn_rsrvCourTextBoxElement\"]/div";
//	private static final String SEARCH_BUTTON = "//*[@id=\"mainframe_VFrameSet_WorkFrame_form_div_work_div_search_edt_regularChoice_input\"]";
//	private static final String THIRD_BUTTON = "/html/body/div[1]/div/div/div[3]/div/div/div[1]/div/div[1]/div[2]/div/div[19]/div[1]/div[4]/div[1]/div/div[4]/div/div/div[1]/div/div/div/div/div";
//	private static final String SCRIPT_OK_BUTTON = "/html/body/div[4]/div/div/div[1]/div/div[1]/div/div[7]/div";
//	private static final String SCRIPT_OK_BUTTON2 = "//*[@id=\"mainframe_VFrameSet_LoginFrame_COM_ALERT_form_btn_closeTextBoxElement\"]/div";
//	private static final String AI_BUTTON = "/html/body/div[1]/div/div/div[3]/div/div/div[1]/div/div[1]/div[2]/div/div[19]/div[1]/div[4]/div[1]/div/div[3]/div/div/div[1]/div/div/div/div/div";
//	private static final String THIRD_NUMBER = "/html/body/div[1]/div/div/div[3]/div/div/div[1]/div/div[1]/div[2]/div/div[19]/div[1]/div[4]/div[1]/div/div[4]/div/div/div[8]/div";
//	private static final String FORTH_NUMBER = "/html/body/div[1]/div/div/div[3]/div/div/div[1]/div/div[1]/div[2]/div/div[19]/div[1]/div[4]/div[1]/div/div[5]/div/div/div[8]/div";
//	private static final String THIRD_CAP = "/html/body/div[1]/div/div/div[3]/div/div/div[1]/div/div[1]/div[2]/div/div[19]/div[1]/div[4]/div[1]/div/div[4]/div/div/div[10]/div";

	private static final String CHORMEDRIVER_ADDRESS = "C:/chromedriver.exe";
	private static final String TARGET_ADDRESS = "http://all.jbnu.ac.kr/jbnu/sugang/index.html";

	private static final int TIMEOUT = 5;
	
	IHeartbeatListener listener;
	WebDriver driver;
	private final AtomicBoolean running = new AtomicBoolean(false);
	
	public void run() {
		createDriverAndInit();
		Utils.sleep(500);
		
		IState state = new BeforeLoginState(driver);
		
		performMacro(state);
	}
	
	private void performMacro(IState state) {
		running.set(true);
		while(running.get()) {
			try {
			state = state.perform();
			heartbeat();
			} catch (Exception e) {
				e.printStackTrace();
				state = new AlertState(driver);
			}
		}
	}

	private void createDriverAndInit() {
		System.setProperty("webdriver.chrome.driver", CHORMEDRIVER_ADDRESS);
		driver = new ChromeDriver();
		driver.navigate().to(TARGET_ADDRESS);
		driver.manage().window().setSize(new Dimension(1920, 1080));
	}

	public static WebElement click(WebDriver driver, String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		WebElement element = driver.findElement(By.xpath(xpath));
		element.click();

		return element;
	}
	
	public static int getValue(WebDriver driver, String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		WebElement element = driver.findElement(By.xpath(xpath));
		
		return Integer.valueOf(element.getText());
	}

	public void setListener(IHeartbeatListener listener) {
		this.listener = listener;
	}
	
	private void heartbeat() {
		listener.heartbeat(this);
	}
	
	public void close() {
		running.set(false);
		driver.close();
	}

}
