package acme.testing.inventor.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class DeleteItemServiceTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/componentPositivo2.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String name, final String code,
		final String technology, final String description, final String retailPrice, final String info) {

		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Inventor", "List Own Tools");
		super.checkListingExists();
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, description);
		super.checkColumnHasValue(recordIndex, 2, retailPrice);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("name",name);
		super.checkInputBoxHasValue("technology",technology);
		super.checkInputBoxHasValue("description",description);
		super.checkInputBoxHasValue("retailPrice",retailPrice);
		super.checkInputBoxHasValue("info", info);
	
		super.clickOnSubmit("Delete");

		super.clickOnMenu("Inventor", "List Own Tools");
		super.checkNotErrorsExist();
		super.signOut();

	}
	
	/*@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/componentNegativo.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String name, final String code,
		final String technology, final String description, final String retailPrice, final String info) {

		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Inventor", "List Own Tools");
		super.checkListingExists();
		super.clickOnListingRecord(recordIndex);
		super.clickOnButton("Update");
		super.fillInputBoxIn("name",name);
		super.fillInputBoxIn("technology",technology);
		super.fillInputBoxIn("description",description);
		super.fillInputBoxIn("retailPrice",retailPrice);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmit("Update");
		
		super.checkErrorsExist();
		super.signOut();
	
	}*/	


}
