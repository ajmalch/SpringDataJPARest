package com.example;

import com.example.model.Address;
import com.example.model.Organization;
import com.example.model.Person;
import com.example.repository.AddresssRepository;
import com.example.repository.OrganizationRepository;
import com.example.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.time.LocalDate;


@SpringBootApplication
@EnableCaching
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	@Profile(value = "dev")
	public CommandLineRunner demoPerson(PersonRepository personRepository,
                                        AddresssRepository addresssRepository){
		return (s)->{

		    Person person = Person.builder()
                    .clientId("clientId1")
                    .dateOfBirth(LocalDate.of(1985, 01, 24))
                    .firstName("Ajmal")
                    .lastName("Cholassery")
                    .effectiveDate(LocalDate.of(2017, 01, 01))
                    .sex(Person.SEX.MALE)
                    .searchkey("ajmal")
                    .build();
			personRepository.save(person);

			Address address1 = Address.builder()
                    .AddressId("1234")
                    .city("Boston")
                    .line1("1 Washington Street")
                    .state("MA")
                    .country("USA")
                    .person(person)
                    .build();
			addresssRepository.save(address1);

            Address address2 = Address.builder()
                            .AddressId("5234")
                            .city("NewYork")
                            .line1("1 Stuart Street")
                            .state("NY")
                            .country("USA")
                            .person(person)
                            .build();

            addresssRepository.save(address2);

			personRepository.save(Person.builder()
					.clientId("clientId2")
					.dateOfBirth(LocalDate.of(1965, 12, 24))
					.firstName("Frank")
					.lastName("Lyons")
					.effectiveDate(LocalDate.of(2016, 01, 01))
					.sex(Person.SEX.MALE)
					.searchkey("frank")
					.build());

		};
	}

    @Bean
	@Profile(value = "dev")
    public CommandLineRunner demoOrganization(OrganizationRepository repo){
        return (s)->{

            repo.save(Organization.builder()
					.clientId("clientId3")
					.effectiveDate(LocalDate.of(2011, 12, 01))
					.shortName("SIS")
					.searchkey("sis")
					.name("Sungard Insurance System")
					.build());
			repo.save(Organization.builder()
					.clientId("clientId4")
					.effectiveDate(LocalDate.of(2012, 12, 23))
					.shortName("FIS")
					.searchkey("fis")
					.name("Fidelity Information Services")
					.build());
			repo.save(Organization.builder()
					.clientId("clientId5")
					.effectiveDate(LocalDate.of(2017, 01, 11))
					.shortName("FB")
					.searchkey("fb")
					.name("Facebook")
					.build());

        };
    }

    @Bean
	SpelAwareProxyProjectionFactory projectionFactory(){
    	return  new SpelAwareProxyProjectionFactory();
	}
}
