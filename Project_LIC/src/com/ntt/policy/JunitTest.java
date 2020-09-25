package com.ntt.policy;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JunitTest {

	@Before
	public void setUp() throws Exception {
		System.out.println("Setting up.....!!!");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Finishing off.....!!!");
	}

	@Test
	public void testNewPolicy() {
		PolicyServices service=new PolicyServices();
		PolicyPojo obj=new PolicyPojo("Whole Life Insurance","Deepika","2020-01-24",8000,"Half-Yearly");
		
		int result = 0;
		result = service.newPolicy(obj);
		assertEquals(1,result);		
	}

}
