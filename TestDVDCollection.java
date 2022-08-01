package testPackage;

import org.junit.Test;

import dvdPackage.DVD;
import dvdPackage.DVDCollection;

public class TestDVDCollection {


@Test
public void testCreateDVDCollection() {
	DVDCollection dvds = new DVDCollection();
	dvds.addOrModifyDVD("Top Gun", "R", "90");
	System.out.println("DVDs with rating R: " + dvds.getDVDsByRating("R"));
	System.out.println("Total running time: " + dvds.getTotalRunningTime());
	System.out.println("Number of DVDs: " + dvds.getNumDVDs());
	}
}
// Each JUnit test class should test the methods and properties for a single class
// Each JUnit test should test a single operation
// Tests should be named to describe the property being tested
// The test suite should cover all of the major operations for the tested classes
// All tests should be contained in a single package named testing
// The DVD project should be contained in a separate package
// A test runner should be provided to run the test suite