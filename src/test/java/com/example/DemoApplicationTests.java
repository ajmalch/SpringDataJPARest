package com.example;

import com.example.model.Person;
import com.example.repository.PersonRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private PersonRepository repo;

	@Test
	public void saveTest(){

		Person p1 = repo.save(new Person( "cliebntIdTest", LocalDate.of(2013, 01, 01),
				10L,"test",LocalDate.of(2020,12,31),
				"Maliha", "Cholasery",LocalDate.of(1985, 01, 24),
				Person.SEX.FEMALE));
		Person p2 = repo.findByLastname("Cholasery",Person.class);
		Assert.assertNotNull(p1.getNameid());
//		Assert.assertEquals("Save Success", java.util.Optional.of(10L), java.util.Optional.ofNullable(p2.getAuditid()));
	}

}
