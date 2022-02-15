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

	private static final int TIMEOUT = 5;
	
	IHeartbeatListener listener;
	WebDriver driver;
	private final AtomicBoolean running = new AtomicBoolean(false);
	
	public void run() {
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
		driver = new ChromeDriver();
		driver.navigate().to("http://all.jbnu.ac.kr/jbnu/sugang/index.html");
		driver.manage().window().setSize(new Dimension(1920, 1080));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		IState state = new BeforeLoginState(driver);
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
		
		
//		while (true) {
//			if (driver.findElements(By.xpath(ID_INPUT)).size() != 0)
//				try {
//					login(driver);
//				} catch (Exception e) {
//					;
//				}
//
////		Point point = click(driver, NUMERICAL_BUTTON).getLocation();
////
////		Actions moveActions = new Actions(driver);
////		moveActions.moveByOffset(point.x + 10, point.y + 10).build().perform();
////		Actions clickActions = new Actions(driver).click();
////		Action click = clickActions.build();
//
//			try {
//				click(driver, SHOPPINGBAG_BUTTON);
//				if(getValue(driver, THIRD_NUMBER) < getValue(driver, THIRD_CAP)) {
//					click(driver, THIRD_BUTTON);
//					click(driver, SCRIPT_OK_BUTTON2);
//					break;
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//				driver.navigate().refresh();
//				if (driver.findElements(By.xpath(ID_INPUT)).size() != 0)
//					login(driver);
//			}
////
////			WebElement search = click(driver, SEARCH_BUTTON);
////			search.sendKeys("인공지능" + Keys.ENTER);
////
////			click(driver, AI_BUTTON);
////			click(driver, SCRIPT_OK_BUTTON);
//
//		}

//		WebElement element2 = click(driver, SCRIPT_OK_BUTTON);
//		click.perform();

//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FOR_CHECK)));
//		List<WebElement> list = driver.findElements(By.xpath(LIST_DIV));
//		System.out.println(list.size());
	}
//
//	private static void login(WebDriver driver) {
//		WebElement id = click(driver, ID_INPUT);
//		id.sendKeys("201710585");
//		WebElement password = click(driver, PASSWORD_INPUT);
//		password.sendKeys("hansoobin1*" + Keys.ENTER);
//
//		click(driver, TOPMENU_BUTTON);
//		click(driver, SHOPPINGBAG_BUTTON);
//	}

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
