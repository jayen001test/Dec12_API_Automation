package api.Endpoints;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {

	// Created to perform  CRUD operation

	// Create, retrieve(read), update, delete


	public static  Response createuser(User payload) {

		Response response =given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)

				.when()
				.post(Routes.post_url);

		return response;

	}


	public static Response readuser(String Username) {

		Response response =given()
				.accept(ContentType.JSON)
				.pathParam("username", Username)
				.when()
				.get(Routes.get_url);

		return response;
	}


	public static  Response updateuser(User payload ,String Username ) {

		Response response =given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.pathParam("username", Username) 
				.when()
				.put(Routes.update_url);
		return response;

	}


	public static Response deleteuser(String Username) {

		Response response =given()
				.accept(ContentType.JSON)
				.pathParam("username", Username)
				.when()
				.delete(Routes.delete_url);

		return response;
	}




}
