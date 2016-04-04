package org.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rest.RestApplication;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RestApplication.class)
@WebAppConfiguration
public class RestApplicationTests {

	@Test
	public void contextLoads() {
	}

}