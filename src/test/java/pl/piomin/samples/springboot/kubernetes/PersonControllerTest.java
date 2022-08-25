package pl.piomin.samples.springboot.kubernetes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import pl.piomin.samples.springboot.kubernetes.domain.Gender;
import pl.piomin.samples.springboot.kubernetes.domain.Person;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
public class PersonControllerTest {

	@LocalServerPort
	int port;
	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void testAdd() {
		Person person = new Person(null, "Test", "Test", 100, Gender.FEMALE);
		Person personAdded = restTemplate.postForObject("/persons", person, Person.class);
		Assertions.assertNotNull(personAdded);
		Assertions.assertNotNull(personAdded.getId());
		Assertions.assertEquals(person.getLastName(), personAdded.getLastName());
	}

}
