package com.pointwest.ui;

import com.pointwest.bean.User;
import com.pointwest.exception.LocatorException;
import com.pointwest.manager.LoginScreenManager;
import com.pointwest.util.Helper;

public class LoginScreenPage {
	public void displayLoginScreen() {
		System.out.format("%20s", "\t\t----------------------======PEOPLE LOCATOR SYSTEM======-------------------------");
		System.out.println();
		System.out.println();
		System.out.format("%-20s", "\t\t-----------------------------======L O G I N======------------------------------");
		System.out.println();
	}

	public User displayLoginPrompt() {
		// boolean loggedIn = false;
		LoginScreenManager logManager = new LoginScreenManager();
		User user = null;
		try {
			user = logManager.retrieveUserByCred(inputUserName(), inputPassword());
			if (user == null) {
				System.out.println();
				System.out.println("\t\t\t\tIncorrect Username or Password");
			}
		} catch (LocatorException e) {
			System.out.println(e.getMessage());
		}
		return user;
		// return loggedIn;
	}

	private String inputPassword() {
		displayPasswordPrompt();
		return Helper.getUserInput();
	}

	public void displayPasswordPrompt() {
		System.out.print("\t\t\t\tPassword:");
	}

	private String inputUserName() {
		String userName;
		boolean invalidInput = false;
		do {
			displayUsernamePrompt();
			userName = Helper.getUserInput();
			invalidInput = isInvalidUserName(userName);
			if (invalidInput) {
				displayInvalidUserName();
			}
		} while (invalidInput);
		return userName;
	}

	public void displayInvalidUserName() {
		System.out.println("Username is invalid");
	}

	public void displayUsernamePrompt() {
		System.out.print("\t\t\t\tUsername:");
	}

	private boolean isInvalidUserName(String userInput) {
		boolean isInvalid = false;
		if (userInput.length() <= 0) {
			isInvalid = true;
		}
		return isInvalid;
	}

}
