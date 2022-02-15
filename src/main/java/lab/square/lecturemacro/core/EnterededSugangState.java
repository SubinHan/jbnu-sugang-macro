package lab.square.lecturemacro.core;

import org.openqa.selenium.WebDriver;

public class EnterededSugangState implements IState {

	private static final String SHOPPINGBAG_BUTTON = "//*[@id=\"mainframe_VFrameSet_WorkFrame_form_div_work_btn_rsrvCourTextBoxElement\"]/div";

	private WebDriver driver;

	public EnterededSugangState(WebDriver driver) {
		this.driver = driver;
	}

	public IState perform() {
		System.out.println("EnteredSugangState performing");
		
		try {
			Macro.click(driver, SHOPPINGBAG_BUTTON);
		} catch (Exception e) {
			e.printStackTrace();
			if (AlertState.alertExists(driver))
				return new AlertState(driver);
		}

		return new SearchingNaState(driver);
	}

}
