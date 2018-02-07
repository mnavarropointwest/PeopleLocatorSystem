package com.pointwest.util;

import java.util.Scanner;

public class Helper {
	public static String trimSpaces(String stringToTrim) {
		return stringToTrim.trim();
	}

	public static String getUserInput() {
		Scanner scanner = new Scanner(System.in);
		return trimSpaces(scanner.next());
	}
}
