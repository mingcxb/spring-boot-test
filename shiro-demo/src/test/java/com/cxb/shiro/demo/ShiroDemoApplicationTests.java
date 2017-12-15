package com.cxb.shiro.demo;

import com.cxb.shiro.demo.service.UserService;
import com.cxb.shiro.demo.utils.ApplicationContextProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroDemoApplicationTests {

	@Test
	public void contextLoads() {

		UserService userService = ApplicationContextProvider.getBean(UserService.class);

		Assert.assertNotNull(userService);
	}

}
