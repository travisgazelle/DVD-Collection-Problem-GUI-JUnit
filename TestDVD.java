package testPackage;

import static org.junit.Assert.fail;

import org.junit.Test;

import dvdPackage.DVD;

public class TestDVD {
	
	@Test
	public void testCreateDVD() {
		DVD dvd = new DVD("Up", "PG", 120);
		System.out.println(dvd.getTitle());
		System.out.println(dvd.getRating());
		System.out.println(dvd.getRunningTime());
		System.out.println(dvd.getGenre());		
	}

}



// Each JUnit test class should test the methods and properties for a single class
// Each JUnit test should test a single operation
// Tests should be named to describe the property being tested
// The test suite should cover all of the major operations for the tested classes
// All tests should be contained in a single package named testing
// The DVD project should be contained in a separate package
// A test runner should be provided to run the test suite
