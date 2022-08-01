package testPackage;

import java.util.Scanner;

import org.junit.Test;

import dvdPackage.DVDCollection;
import dvdPackage.DVDConsoleUI;

public class TestDVDConsoleUI {
	
	@Test
	public void testCreateDVDConsole() {
		DVDCollection dvdlist = new DVDCollection();		
		DVDConsoleUI dvd = new DVDConsoleUI(dvdlist);
	}

}

// Each JUnit test class should test the methods and properties for a single class
// Each JUnit test should test a single operation
// Tests should be named to describe the property being tested
// The test suite should cover all of the major operations for the tested classes
// All tests should be contained in a single package named testing
// The DVD project should be contained in a separate package
// A test runner should be provided to run the test suite