package com.jerry.jerry.web.controller.core;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UsersControllerTest {

	@BeforeClass(groups = "InContainer")
	public void setup() {
		System.out.println("set up");
	}

	@AfterClass(groups = "InContainer")
	public void clear() {
		System.out.println("clear");
	}

	@Test(groups = "InContainer")
	public void testDemo() {
		System.out.println("Demo!!!!!");
	}
	
	@Test(groups = "OutOfContainer")
	public void testDemo1() {
		System.out.println("Demo111!!!!!");
	}
}
