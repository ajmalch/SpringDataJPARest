package com.example;

import com.example.model.Organization;
import com.example.model.Person;
import com.example.repository.OrganizationRepository;
import com.example.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	@Profile(value = "dev")
	public CommandLineRunner demoPerson(PersonRepository repo){
		return (s)->{

			repo.save(new Person( "cliebntId1", LocalDate.of(2012, 01, 01),
					10L,"ajmal",LocalDate.of(2020,12,31),
					"Ajmal", "Cholassery",LocalDate.of(1985, 01, 24),
					Person.SEX.MALE));
			repo.save(new Person( "cliebntId2", LocalDate.of(2013, 01, 01),
					10L,"frank",LocalDate.of(2020,10,31),
					"Frank", "Lyons",LocalDate.of(1955, 01, 24),
					Person.SEX.MALE));

//			repo.save(new Person("Ajmal", "Cholassery",new Date(), Person.SEX.MALE));
//            repo.save(new Person("Frank", "Lyons",new Date(),Person.SEX.MALE));
		};
	}

    @Bean
	@Profile(value = "dev")
    public CommandLineRunner demoOrganization(OrganizationRepository repo){
        return (s)->{
            repo.save(new Organization("cliebntId3", LocalDate.of(2016, 04, 01),
					10L,"fis",LocalDate.of(2025,12,31),
            		"FIS","FIS Global"));
			repo.save(new Organization("cliebntId4", LocalDate.of(2011, 12, 01),
					10L,"sis",null,
					"SIS","Sungard Insurance System"));
			repo.save(new Organization("cliebntId5", LocalDate.of(2017, 01, 11),
					10L,"fb",null,
					"FB","Face Book"));
//            repo.save(new Organization("FIS","FIS Global"));
//            repo.save(new Organization("SIS","Sungard Insurance System"));
        };
    }
}
