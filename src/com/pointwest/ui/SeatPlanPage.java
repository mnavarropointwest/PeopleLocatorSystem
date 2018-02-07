package com.pointwest.ui;

import java.util.ArrayList;
import java.util.List;

import com.pointwest.bean.Building;
import com.pointwest.bean.Employee;
import com.pointwest.bean.Seat;
import com.pointwest.manager.SeatPlanManager;
import com.pointwest.util.Helper;

public class SeatPlanPage {
	public void displayHeader() {
		System.out.println("\t\t-------------------------------==============------------------------------------");
		System.out.println("\t\t\t\t\t\tVIEW SEATPLAN");
		System.out.println("\t\t-------------------------------==============------------------------------------");
	}

	public void displayMenu() {
		System.out.println("\t\t\t\t\t[1] By Location - Floor Level");
		System.out.println("\t\t\t\t\t[2] By Quadrant");
		System.out.print("\t\t\t\t\tCHOICE:");
	}

	public void displaySeatPlanPage() {
		displayHeader();
		String menuChoice = inputMenu();
		displaySubMenu(menuChoice);
	}

	public String inputMenu() {
		String menuChoice;
		boolean invalidInput = false;
		do {
			displayMenu();
			menuChoice = Helper.getUserInput();
			invalidInput = validateInput(menuChoice);
			if (invalidInput) {
				System.out.println("\t\t\t\t\t\tPlease enter from the menu choices");
			}
		} while (invalidInput);
		return menuChoice;
	}

	private boolean validateInput(String input) {
		boolean valid = false;
		if (!"1".equalsIgnoreCase(input) && !"2".equalsIgnoreCase(input)) {
			valid = true;
		}
		return valid;
	}

	public void displaySubMenu(String menuChoice) {
		List<Employee> employeeList = null;
		String details = null;
		switch (menuChoice) {
		case "1":
			System.out.println(
					"---------------------------------------===============VIEW SEATPLAN BY LOCATION - FLOOR LEVEL===============------------------------------");
			if (checkLocations()) {
				String location = askLocation();
				List<Seat> seatFloors = displayFloor(location);
				String floor = askFloor(seatFloors);
				SeatPlanManager seatPlanManager = new SeatPlanManager();
				employeeList = seatPlanManager.retrieveSeatPlanByLocationFloor(location, floor);
				details = "LOCATION: " + location + " FLOOR: " + floor;
				displaySeatPlan(employeeList, details, null);
			}
			break;
		case "2":
			System.out.println(
					"------------------------------------------------===============VIEW SEATPLAN BY QUADRANT===============-------------------------------------");
			SeatPlanManager seatPlanManager = new SeatPlanManager();
			if (checkQuadrants()) {
				String location = askLocation();
				List<Seat> seatFloors = displayFloor(location);
				String floor = askFloor(seatFloors);
				String quadrant = askQuadrant();
				employeeList = seatPlanManager.retrieveSeatPlanByLocationFloor(location, floor);
				details = "LOCATION: " + location + " FLOOR: " + floor + "  QUADRANT: " + quadrant;
				displaySeatPlan(employeeList, details, quadrant);
			} else {
				System.out.println("\t\t\t\t\tThere are no Quadrants.");
			}
			break;
		}
	}

	public String askQuadrant() {
		String userInput;
		boolean invalidInput;
		do {
			displayQuadrants();
			System.out.print("\t\t\t\t\t\t\tCHOICE:");
			userInput = Helper.getUserInput();
			invalidInput = validateQuadrantInput(userInput);
			if (invalidInput) {
				System.out.println("\t\t\t\t\t\t Please select from the Quadrants above.");
			}
		} while (invalidInput);
		return userInput;
	}

	private boolean validateQuadrantInput(String input) {
		SeatPlanManager seatPlanManager = new SeatPlanManager();
		List<String> quadrants = seatPlanManager.retrieveAllQuadrants();
		boolean invalid = true;
		for (String quadrantName : quadrants) {
			if (quadrantName.equalsIgnoreCase(input)) {
				invalid = false;
				break;
			}
		}
		return invalid;
	}

	private boolean checkQuadrants() {
		SeatPlanManager seatPlanManager = new SeatPlanManager();
		List<String> quadrants = seatPlanManager.retrieveAllQuadrants();
		if (quadrants != null) {
			return true;
		} else {
			return false;
		}
	}

	public void displayQuadrants() {
		SeatPlanManager seatPlanManager = new SeatPlanManager();
		List<String> quadrants = seatPlanManager.retrieveAllQuadrants();
		System.out.println("\t\t\t\t\t\t\tQ U A D R A N T S");
		for (String quadrantName : quadrants) {
			System.out.println("\t\t\t\t\t\t\t\t[" + quadrantName + "]");
		}
	}

	public void displaySeatPlan(List<Employee> employeeList, String details, String quadrant) {
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t\t\tVIEW SEATPLAN");
		System.out.println("\t\t\t\t\t\t\t" + details);
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		List<String> seatInfo = new ArrayList<String>();
		List<String> empInfo = new ArrayList<String>();
		List<String> localPhone = new ArrayList<String>();
		for (Employee employee : employeeList) {
			String fullName = "";
			String firstName = employee.getFirstName();
			String lastName = employee.getLastName();
			if (firstName != null && lastName != null) {
				fullName = firstName + "," + lastName;
			}
			Seat empSeat = employee.getSeat();
			Building seatBuilding = empSeat.getBuilding();
			String buildingId = seatBuilding.getId();
			String seatFloor = empSeat.getFloorNumber();
			String seatQuadrant = empSeat.getQuadrant();
			String seatRow = empSeat.getRowNumber();
			String seatColumn = empSeat.getColNumber();
			String seatLoc = "";
			if (empSeat.getLoc().length() > 0) {
				seatLoc = "loc." + empSeat.getLoc();
			} else {
				seatLoc = "NONE";
			}
			String seatLocation = buildingId + seatFloor + "F" + seatQuadrant + seatRow + "-" + seatColumn;
			if (quadrant != null) {
				if (seatQuadrant.equalsIgnoreCase(quadrant)) {
					seatInfo.add(seatLocation);
					empInfo.add(fullName);
					localPhone.add(seatLoc);
				} else {
					seatInfo.add("X");
					empInfo.add("X");
					localPhone.add("X");
				}
			} else {
				seatInfo.add(seatLocation);
				empInfo.add(fullName);
				localPhone.add(seatLoc);
			}

		}
		int seatCounter = 0;
		int empCounter = 0;
		int localCounter = 0;
		int index = 0;
		while (index < employeeList.size()) {
			while (seatCounter < seatInfo.size()) {
				System.out.format("%30s%5s", seatInfo.get(seatCounter), "|");
				seatCounter++;
				if (seatCounter % 5 == 0 && seatCounter != 0) {
					break;
				}
			}
			System.out.println();
			while (empCounter < empInfo.size()) {
				System.out.format("%30s%5s", empInfo.get(empCounter), "|");
				empCounter++;
				if (empCounter % 5 == 0 && empCounter != 0) {

					break;
				}
			}
			System.out.println();
			while (localCounter < localPhone.size()) {
				System.out.format("%30s%5s", localPhone.get(localCounter), "|");
				localCounter++;
				if (localCounter % 5 == 0 && localCounter != 0) {
					break;
				}
			}
			System.out.println();
			System.out.println(
					"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			index = seatCounter;
			if (index % 5 == 0 && index != 0) {
				System.out.println();
			}
		}
		System.out.println("\t\t\t\t\t\t\tEND  OF  RESULT");

	}

	public String askFloor(List<Seat> seatFloors) {
		boolean invalidInput = false;
		String floorInput;
		do {

			System.out.print("\t\t\t\t\t\t\t\tCHOICE:");
			floorInput = Helper.getUserInput();
			invalidInput = validateSeatFloorInput(floorInput, seatFloors);
			if (invalidInput) {
				System.out.println("\t\t\t\t\t\t\tPlease select from the choices.");
			}
		} while (invalidInput);
		return floorInput;
	}

	private boolean validateSeatFloorInput(String floorInput, List<Seat> seatFloors) {
		boolean invalid = false;
		boolean found = false;
		for (Seat seat : seatFloors) {
			if (seat.getFloorNumber().equalsIgnoreCase(floorInput)) {
				found = true;
				break;
			}
		}
		if (!found) {
			invalid = true;
		}
		return invalid;
	}

	private boolean checkLocations() {
		SeatPlanManager seatPlanManager = new SeatPlanManager();
		List<Building> locations = seatPlanManager.retrieveListOfLocation();
		if (locations != null) {
			return true;
		} else {
			System.out.println("No Locations");
			return false;
		}
	}

	public List<Seat> displayFloor(String location) {
		SeatPlanManager seatPlanManager = new SeatPlanManager();
		List<Seat> seatFloors = seatPlanManager.retrieveFloorsByLocation(location);
		System.out.println("\t\t\t\t\t\t\tF L O O R S");
		for (Seat seat : seatFloors) {
			System.out.println("\t\t\t\t\t\t\t\t[" + seat.getFloorNumber() + "]");
		}
		return seatFloors;
	}

	public String askLocation() {
		boolean invalidInput = false;
		String locationInput;
		do {
			displayLocation();
			System.out.print("\t\t\t\t\t\t\t\tCHOICE:");
			locationInput = Helper.getUserInput();
			invalidInput = validateLocationInput(locationInput);
			if (invalidInput) {
				System.out.println("\t\t\t\t\tPlease select only from the Choices");
			}
		} while (invalidInput);
		return locationInput;
	}

	private boolean validateLocationInput(String input) {
		boolean invalid = true;
		SeatPlanManager seatPlanManager = new SeatPlanManager();
		List<Building> locations = seatPlanManager.retrieveListOfLocation();
		boolean found = false;
		for (Building location : locations) {
			if (location.getId().equalsIgnoreCase(input)) {
				found = true;
				break;
			}
		}
		if (found) {
			invalid = false;
		}
		return invalid;
	}

	public void displayLocation() {
		SeatPlanManager seatPlanManager = new SeatPlanManager();
		List<Building> locations = seatPlanManager.retrieveListOfLocation();
		System.out.println("\t\t\t\t\t\t\tL O C A T I O N S");
		for (Building location : locations) {
			System.out.println("\t\t\t\t\t\t\t\t[" + location.getId() + "]");
		}
	}

	public boolean retrySeatPlan() {
		boolean retryAgain = false;
		String userInput;
		boolean invalidInput = false;
		do {
			System.out.print("\t\t\t\t\t\tACTIONS: [1] View Seatplan again. [2] Home: ");
			userInput = Helper.getUserInput();
			invalidInput = validateNextActionInput(userInput);
			if (invalidInput) {
				System.out.println("\t\t\t\t\t\tPlease enter from the choices");
			}
		} while (invalidInput);
		if ("1".equalsIgnoreCase(userInput)) {
			retryAgain = true;
		}
		return retryAgain;
	}

	private boolean validateNextActionInput(String input) {
		boolean invalid = false;
		if (!"1".equalsIgnoreCase(input) && !"2".equalsIgnoreCase(input)) {
			invalid = true;
		}
		return invalid;
	}
}
