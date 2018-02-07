package com.pointwest.ui;

import java.util.List;

import com.pointwest.bean.Building;
import com.pointwest.bean.Employee;
import com.pointwest.bean.Project;
import com.pointwest.bean.Seat;
import com.pointwest.manager.SearchPageManager;
import com.pointwest.util.Helper;

public class SearchPage {

	public void displaySearchPage() {
		displayHeader();
		String menuChoice = inputMenu();
		String input = userInput(menuChoice);
		if (input != null) {
			SearchPageManager searchPageManager = new SearchPageManager();
			List<Employee> listOfEmployee = null;
			switch (menuChoice) {
			case "1":
				listOfEmployee = searchPageManager.retrieveListEmployeeById(input);
				break;
			case "2":
				listOfEmployee = searchPageManager.retrieveListEmployeeByName(input);
				break;
			case "3":
				listOfEmployee = searchPageManager.retrieveListEmployeeByProjectName(input);
				break;
			}
			displayEmployeeList(listOfEmployee, menuChoice);
		}
	}

	private boolean validateRetryInput(String userInput) {
		boolean invalid = false;
		if (!"Y".equalsIgnoreCase(userInput) && !"N".equalsIgnoreCase(userInput)) {
			invalid = true;
		}
		return invalid;
	}

	public boolean askSearchAgain() {
		boolean searchAgain = false;
		boolean invalidInput;
		String userInput;
		do {
			System.out.print("\t\t\t\t\tSearch again?[Y/N]:");
			userInput = Helper.getUserInput();
			invalidInput = validateRetryInput(userInput);
			if (invalidInput) {
				System.out.println("t\t\t\t\t\tPlease enter Y/N only");
			}
		} while (invalidInput);
		if ("Y".equalsIgnoreCase(userInput)) {
			searchAgain = true;
		}
		return searchAgain;

	}

	public void displayEmployeeList(List<Employee> employeeList, String input) {
		if (employeeList != null) {
			System.out.println("-----------------------------------------------------------------SEARCH RESULTS ("
					+ input + ")-----------------------------------------------------------------------------");
			System.out.format("%-20s%-25s%-20s%-20s%-15s%-25s%-25s", "EMPLOYEE ID", "FIRST NAME", "LAST NAME", "   SEAT",
					"LOCAL", "SHIFT", "PROJECT");
			System.out.println();
			System.out.println(
					"----------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println();
			for (Employee employee : employeeList) {
				String id = employee.getId();
				String firstName = employee.getFirstName();
				String lastName = employee.getLastName();
				Seat empSeat = employee.getSeat();
				Building empBuilding = empSeat.getBuilding();
				String buildingId = empBuilding.getId();
				String seatFloor = empSeat.getFloorNumber();
				String seatQuadrant = empSeat.getQuadrant();
				String seatRow = empSeat.getRowNumber();
				String seatColumn = empSeat.getColNumber();
				String seatLoc = empSeat.getLoc();
				String shift = employee.getShift();
				String projects = employee.getProject();
				if (shift == null) {
					shift = "NONE";
				}
				if (seatLoc == null) {
					seatLoc = "NONE";
				}
				if (projects == null) {
					projects = "NONE";
				}
				String seatLocation = buildingId + seatFloor + "F" + seatQuadrant + seatRow + "-" + seatColumn;
				System.out.format("%-20s%-25s%-20s%-20s%-15s%-25s%-25s", id, firstName, lastName, seatLocation, seatLoc,
						shift, projects);
				System.out.println();
			}
			System.out.println("-----------------------------------------------------------------------------END OF RESULTS (" + input
					+ ")------------------------------------------------------------------");
		} else {
			System.out.println("\t\t\t\t\t\tNO EMPLOYEE WAS RETRIEVED.");
		}
	}

	public void displayHeader() {
		System.out.println("\t\t-----------------------------======S E A R C H======------------------------------");
	}

	public void displayMenu() {
		System.out.println("\t\t\t\t\t\t\tM E N U:");
		System.out.println("\t\t\t\t\t\t[1] BY EMPLOYEE ID");
		System.out.println("\t\t\t\t\t\t[2] BY EMPLOYEE NAME");
		System.out.println("\t\t\t\t\t\t[3] BY EMPLOYEE PROJECT");
		System.out.print("\t\t\t\t\t\tCHOICE:");
	}

	public boolean displayProjectList() {
		List<Project> projectList = null;
		SearchPageManager searchPageManager = new SearchPageManager();
		projectList = searchPageManager.retrieveProjectChoices();
		if (projectList != null) {
			System.out.println("\t\t\t\t\tPROJECTS");
			for (Project project : projectList) {
				System.out.println("\t\t\t\t\t\t[" + project.getName() + "]");
			}
			return true;
		} else {
			System.out.println("\t\t\t\t\tThere's no project available");
			return false;
		}
	}

	public String inputMenu() {
		String subMenuChoice;
		boolean invalidInput;
		do {
			displayMenu();
			subMenuChoice = Helper.getUserInput();
			invalidInput = isInvalidInputMenu(subMenuChoice);
			if (invalidInput) {
				System.out.println("\t\t\t\t\tPlease select from the menu below/above. :)");
			}
		} while (invalidInput);
		return subMenuChoice;
	}

	private boolean isInvalidInputMenu(String subMenuChoice) {
		boolean isInvalid = false;
		if (!"1".equals(subMenuChoice) && !"2".equalsIgnoreCase(subMenuChoice)
				&& !"3".equalsIgnoreCase(subMenuChoice)) {
			isInvalid = true;
		}
		return isInvalid;
	}

	public void displaySubMenuHeader(String subMenuChoice) {
		switch (subMenuChoice) {
		case "1":
			System.out.println("\t\t----------------------======SEARCH BY EMPLOYEE ID======-------------------------");
			break;
		case "2":
			System.out.println("\t\t---------------------======SEARCH BY EMPLOYEE NAME======------------------------");
			break;
		case "3":
			System.out.println("\t\t--------------------======SEARCH BY EMPLOYEE PROJECT======----------------------");
			break;
		}
	}

	public boolean displayPrompt(String subMenuChoice) {
		boolean isPossible = true;
		switch (subMenuChoice) {
		case "1":
			System.out.println();
			System.out.print("\t\t\t\t\tEmployee ID:");
			break;
		case "2":
			System.out.print("\t\t\t\t\tEMPLOYEE LAST NAME/FIRST NAME:");
			break;
		case "3":
			if (displayProjectList()) {
				System.out.print("\t\t\t\t\tEMPLOYEE PROJECT NAME:");
			} else {
				isPossible = false;
			}
			break;
		}
		return isPossible;
	}

	public String userInput(String subMenuChoice) {
		String userInput;
		boolean invalidInput = false;
		displaySubMenuHeader(subMenuChoice);
		do {
			if (displayPrompt(subMenuChoice)) {
				userInput = Helper.getUserInput();
				if (invalidUserInput(subMenuChoice, userInput)) {
					System.out.println("Invalid input");
				}
			} else {
				userInput = null;
				break;
			}
		} while (invalidInput);
		return userInput;
	}

	private boolean invalidUserInput(String subMenuChoice, String userInput) {
		boolean isInvalid = false;

		switch (subMenuChoice) {
		case "1":
			if (userInput.length() > 7 || userInput.length() <= 0) {
				isInvalid = true;
			}
			break;
		case "2": // validation for last name first name
			break;
		case "3":
			SearchPageManager searchPageManager = new SearchPageManager();
			boolean found = false;
			List<Project> projectList = searchPageManager.retrieveProjectChoices();
			for (Project project : projectList) {
				if (project.getName().equalsIgnoreCase(userInput)) {
					found = true;
					break;
				}
			}
			if (!found) {
				isInvalid = false;
			}
			break;
		}
		return isInvalid;
	}

}
