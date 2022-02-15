package lab.square.lecturemacro.core;

import org.openqa.selenium.WebDriver;

public class SearchingNaState implements IState {

	private static final String THIRD_BUTTON = "/html/body/div[1]/div/div/div[3]/div/div/div[1]/div/div[1]/div[2]/div/div[19]/div[1]/div[4]/div[1]/div/div[4]/div/div/div[1]/div/div/div/div/div";
	private static final String THIRD_NUMBER = "/html/body/div[1]/div/div/div[3]/div/div/div[1]/div/div[1]/div[2]/div/div[19]/div[1]/div[4]/div[1]/div/div[4]/div/div/div[8]/div";
	private static final String THIRD_CAP = "/html/body/div[1]/div/div/div[3]/div/div/div[1]/div/div[1]/div[2]/div/div[19]/div[1]/div[4]/div[1]/div/div[4]/div/div/div[10]/div";

	private WebDriver driver;

	public SearchingNaState(WebDriver driver) {
		this.driver = driver;
	}

	public IState perform() {
		System.out.println("SearchingNaState performing");
		
		try {
			if (Macro.getValue(driver, THIRD_NUMBER) < Macro.getValue(driver, THIRD_CAP)) {
				Macro.click(driver, THIRD_BUTTON);
				return new AlertState(driver);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (AlertState.alertExists(driver))
				return new AlertState(driver);
		}
		return new EnterededSugangState(driver);
	}

}
