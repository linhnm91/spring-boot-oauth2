package com.codehubs.springoauth2;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.codehubs.springoauth2.dao.IUserDao;
import com.codehubs.springoauth2.helpers.AuthenticateHelper;
import com.codehubs.springoauth2.model.Role;
import com.codehubs.springoauth2.model.User;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@SpringApplicationConfiguration(classes = SpringSecurityOauth2Application.class)
public class SpringSecurityOauth2ApplicationTests {

	@Autowired
	IUserDao userDao;

	@Test
	public void setUp() throws Exception {
		Role r1 = new Role();
		r1.setId(1);
		r1.setName("USER");
		Set<Role> rls = new HashSet<>();
		rls.add(r1);
		User user = new User();
		String passwd = AuthenticateHelper.generateSHA256("abc1234567");
		user.setAccountId("0000001");
		user.setCreatedAt(new Date());
		user.setFistName("Linh");
		user.setLastName("Nguyen");
		user.setPassword(passwd);
		user.setPhone("01652017596");
		user.setUsername("linhnm");
		user.setRoles(rls);
		userDao.create(user);
		Assert.assertNotNull(user.getId());
	}

}
