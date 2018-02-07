package com.pointwest.ui;

import com.pointwest.bean.Employee;
import com.pointwest.bean.User;
import com.pointwest.manager.HomePageManager;
import com.pointwest.util.Helper;

public class HomeScreenPage {
	private Employee employeeLogged;

	public HomeScreenPage(User userLogged) {
		HomePageManager homePageManager = new HomePageManager();
		this.employeeLogged = homePageManager.retrieveEmployeeDetail(userLogged);
	}

	public void displayWelcomeScreen() {
		System.out.format("%20s",
				"\t\t-------------------------------=====HOME=====------------------------------------\n");
		System.out.println("\t\t\t\t\tWelcome " + employeeLogged.getFirstName() + " " + employeeLogged.getLastName()
				+ " [" + employeeLogged.getAppRole() + "]!");
		System.out.format("%20s",
				"\t\t-------------------------------==============------------------------------------\n");
	}

	public void displayMenu() {
		System.out.format("%50s", "\t\t\tM A I N  M E N U \n");
		System.out.format("%50s", "\t\t[1] SEARCH\n");
		System.out.format("%50s", "\t\t\t[2] VIEW SEATPLAN\n");
		System.out.format("%50s", "\t\t[3] LOGOUT\n");
		System.out.format("%50s", "\tCHOICE:");
	}

	public String inputMenuChoice() {
		String menuChoice;
		boolean invalidChoice = false;
		do {
			displayMenu();
			menuChoice = Helper.getUserInput();
			invalidChoice = isInvalidMenuChoice(menuChoice);
			if (invalidChoice) {
				System.out.println("\t \t \t \t \t \tPlease try again :)");
			}
		} while (invalidChoice);
		return menuChoice;
	}

	public boolean isInvalidMenuChoice(String userInput) {
		boolean isInvalid = false;
		if (userInput.length() < 0 || (!"1".equalsIgnoreCase(userInput) && !"2".equalsIgnoreCase(userInput)
				&& !"3".equalsIgnoreCase(userInput))) {
			isInvalid = true;
		}
		return isInvalid;
	}

	public void displayTerminateScreen() {
		System.out.println(
				"\t\t--------------------======TERMINATING PROGRAM========-----------------------------\n");

	}

}
