package testPackage;

import dvdPackage.*; 
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)

@SuiteClasses({
	TestDVD.class, TestDVDCollection.class, TestDVDConsoleUI.class
})
public class AllTests {
}