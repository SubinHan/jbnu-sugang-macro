package lab.square.lecturemacro.core;

import org.openqa.selenium.WebDriver;

public class MainMenuState implements IState {

	private static final String TOPMENU_BUTTON = "//*[@id=\"mainframe_VFrameSet_TopFrame_form_div_top_mnu_topmenu_0001\"]";
	
	private WebDriver driver;

	public MainMenuState(WebDriver driver) {
		this.driver = driver;
	}

	public IState perform() {
		System.out.println("MainMenuState performing");
		
		Macro.click(driver, TOPMENU_BUTTON);
		
		return new EnterededSugangState(driver);
	}
	
	

}
