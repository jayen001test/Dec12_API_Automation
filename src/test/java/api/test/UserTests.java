package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.Endpoints.UserEndPoints;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User userPayload;
	
	public Logger logger;

	@BeforeClass
	public void setupData() {

		faker=new Faker();

		userPayload=new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		logger=LogManager.getLogger(this.getClass());
	}


	@Test(priority=1)
	public void testPostUser() {
		
		logger.info("************* test_testPostUser started ******************");
		Response res=UserEndPoints.createuser(userPayload);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		
		logger.info("************* User created ******************");
	}


	@Test(priority=2)
	public void testGetUsername() {
		logger.info("************* test_getuser started ******************");
		Response res=UserEndPoints.readuser(userPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("************* get the user by username ******************");
	}
	
	@Test(priority = 3)
	public void testUpdateUsername() {
		
		logger.info("************* test_updateuser started ******************");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response res=UserEndPoints.updateuser(userPayload, userPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		
		Response responseAfterupdate=UserEndPoints.readuser(userPayload.getUsername());
		Assert.assertEquals(responseAfterupdate.getStatusCode(),200);
		logger.info("************* user got updated by the usernamee ******************");
	}
	
	@Test(priority = 4)
	public void testDeleteUser() {
		
		logger.info("************* test_delation of user started ******************");
		
		Response res=UserEndPoints.deleteuser(userPayload.getUsername());
		Assert.assertEquals(res.getStatusCode(), 200);
		
		logger.info("************* test_deleted the user ******************");
	}
	
	
}
