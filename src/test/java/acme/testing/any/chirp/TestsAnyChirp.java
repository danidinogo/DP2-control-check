package acme.testing.any.chirp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class TestsAnyChirp extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/chirp.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void testAnyChirpList(final int key, final String version,final String author, final String body, 
		final String creationTime, final String email, final String title ) {

		
		super.clickOnMenu("Anonymous","Chirps");
		super.checkListingExists();
		
		super.checkColumnHasValue(key, 0, author);
		super.checkColumnHasValue(key, 1, body);
		super.checkColumnHasValue(key, 2, creationTime);
		super.checkColumnHasValue(key, 3, email);
		super.checkColumnHasValue(key, 4, title);

	}
}