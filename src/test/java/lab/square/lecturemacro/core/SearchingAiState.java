package lab.square.lecturemacro.core;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchingAiState implements IState {

	private static final String SEARCH_BUTTON = "//*[@id=\"mainframe_VFrameSet_WorkFrame_form_div_work_div_search_edt_regularChoice_input\"]";
	private static final String SECOND_BUTTON = "/html/body/div[1]/div/div/div[3]/div/div/div[1]/div/div[1]/div[2]/div/div[19]/div[1]/div[4]/div[1]/div/div[3]/div/div/div[1]/div/div/div/div/div";
	private static final String SECOND_NUMBER = "/html/body/div[1]/div/div/div[3]/div/div/div[1]/div/div[1]/div[2]/div/div[19]/div[1]/div[4]/div[1]/div/div[3]/div/div/div[8]/div";
	private static final String SECOND_CAP = "/html/body/div[1]/div/div/div[3]/div/div/div[1]/div/div[1]/div[2]/div/div[19]/div[1]/div[4]/div[1]/div/div[3]/div/div/div[10]/div";

	private WebDriver driver;

	public SearchingAiState(WebDriver driver) {
		this.driver = driver;
	}

	public IState perform() {
		System.out.println("SearchingAiSTate performing");
		
		WebElement search = Macro.click(driver, SEARCH_BUTTON);
		search.sendKeys("인공지능" + Keys.ENTER);

		try {
			if (Macro.getValue(driver, SECOND_NUMBER) < Macro.getValue(driver, SECOND_CAP)) {
				Macro.click(driver, SECOND_BUTTON);
				return new AlertState(driver);
			}
		} catch (Exception e) {
			if (AlertState.alertExists(driver))
				return new AlertState(driver);
		}

		return new ClickedSugangState(driver);
	}

}
