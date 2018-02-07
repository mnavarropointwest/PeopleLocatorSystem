package com.pointwest.main;

import org.apache.log4j.Logger;

import com.pointwest.bean.User;
import com.pointwest.ui.HomeScreenPage;
import com.pointwest.ui.LoginScreenPage;
import com.pointwest.ui.SearchPage;
import com.pointwest.ui.SeatPlanPage;

public class Main {
	static Logger myLogger = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		myLogger.trace("Trace Message!");
		LoginScreenPage loginPage = new LoginScreenPage();
		loginPage.displayLoginScreen();
		int tries = 0;
		User userLogin = null;
		do {
			tries++;
			userLogin = loginPage.displayLoginPrompt();
		} while (userLogin == null && tries < 3);
		if (userLogin != null) {
			HomeScreenPage homePageScreen = new HomeScreenPage(userLogin);
			boolean logout = false;
			do {
				homePageScreen.displayWelcomeScreen();
				String menuChoice = homePageScreen.inputMenuChoice();
				switch (menuChoice) {
				case "1":
					SearchPage searchPage = new SearchPage();
					boolean searchPageAgain = false;
					do {
						searchPage.displaySearchPage();
						searchPageAgain = searchPage.askSearchAgain();
					} while (searchPageAgain);
					break;
				case "2":
					boolean seatPlanAgain = false;
					do {
						SeatPlanPage seatPlan = new SeatPlanPage();
						seatPlan.displaySeatPlanPage();
						seatPlanAgain = seatPlan.retrySeatPlan();
					} while (seatPlanAgain);
					break;
				case "3":
					logout = true;
					break;
				}
			} while (!logout);
			homePageScreen.displayTerminateScreen();
		}

	}
}
