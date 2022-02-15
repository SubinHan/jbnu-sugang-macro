package lab.square.lecturemacro.core;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BeforeLoginState implements IState {
	
	private static final String ID_INPUT = "//*[@id=\"mainframe_VFrameSet_LoginFrame_form_div_login_div_form_edt_hakbun_input\"]";
	private static final String PASSWORD_INPUT = "//*[@id=\"mainframe_VFrameSet_LoginFrame_form_div_login_div_form_edt_passwd_input\"]";
	
	private WebDriver driver;
	
	public BeforeLoginState(WebDriver driver) {
		this.driver = driver;
	}
	
	public IState perform() {
		System.out.println("BeforeLoginState performing");
		
		WebElement id = Macro.click(driver, ID_INPUT);
		id.sendKeys(System.getenv("jbnu_id"));
		WebElement password = Macro.click(driver, PASSWORD_INPUT);
		password.sendKeys(System.getenv("jbnu_pw") + Keys.ENTER);
		
		if(AlertState.alertExists(driver))
			return new AlertState(driver);
		
		return new MainMenuState(driver);
	}

}
