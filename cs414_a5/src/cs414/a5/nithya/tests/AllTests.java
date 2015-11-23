package cs414.a5.nithya.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CustomerTest.class, EntryKioskTest.class, ExitKioskTest.class,
		GarageTest.class, PaymentTest.class, RegisterTest.class })
public class AllTests {

}
