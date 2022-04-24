/*
 * FavouriteLinkTest.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing.administrator.dashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;


public class AdministratorDashboardTest extends TestHarness {
	
	private final String adminPath = "/administrator/dashboard/show";
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@Test
	@Order(0)
	public void anonymousAccess() {
		System.out.println("asdasdasd");
		System.out.println(this.adminPath);
		
		this.checkXpathNotExists("//*[@id=\"mainMenu\"]/ul[1]/li[2]/a[text() = 'Administrator']");
		
		this.navigate(this.adminPath);
		this.checkPanicExists();
	}
	
	@Test
	@Order(1)
	public void checkDashboard() {
		this.signIn("administrator", "administrator");
		this.navigate(this.adminPath);
		this.checkNotPanicExists();
		this.checkXpathExists("//*[@id=\"totals\"]/div[1]/div[1]/div/div/label/input");
		
		this.checkXpathExists("//*[@id=\"mainMenu\"]/ul[1]/li[2]/a[text() = 'Administrator']");
		
		this.checkXpathNotExists("//*[@id=\"totalss\"]/div[1]/div[1]/div/div/label/input");
	}
	
	// Ancillary methods ------------------------------------------------------ 
	
}
