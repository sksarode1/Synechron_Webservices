package com.synechron.ws.wstraining.basics.response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonPathDemo {

	

	public String baseurl = "https://api.trello.com";
	public String key = "c5f676759b86029624f7dcb31ccf655e";
	public String token = "9b60bd7325defc221aa0203822765f426ecb134adcf46f7ca823c569ced3a2f3";
	
	@Test
	public void validateBodyInGet()
	{
		RestAssured.baseURI = baseurl;
				
		Response response = given().
					param("key", key).
					param("token", token).
				when().
					get("/1/boards/llt4bIbC"). 
				then(). 
					assertThat().statusCode(200).
				extract().response();
		
		String responseStr = response.asString();
		JsonPath responseBody = new JsonPath(responseStr);
		
		System.out.println("ID : " + responseBody.get("id"));
		System.out.println("NAME : " + responseBody.get("name"));
		Assert.assertEquals(responseBody.get("id"), "60ac71721dceec66fea94ff9");
		Assert.assertEquals(responseBody.get("name"), "myAutomationBoard");
	
	}
	
	
	@Test
	public void validateArrayInGet()
	{
		RestAssured.baseURI = baseurl;
				
		Response response = given().
					param("key", key).
					param("token", token).
				when().
					get("/1/boards/llt4bIbC"). 
				then(). 
					assertThat().statusCode(200).
				extract().response();
		
		String responseStr = response.asString();
		JsonPath responseBody = new JsonPath(responseStr);
		
		int size = responseBody.get("prefs.backgroundImageScaled.size()");
		
		for (int i = 0; i < size; i++) 
		{
			System.out.println("Width at index " + i + " is : " +  responseBody.get("prefs.backgroundImageScaled[" + i + "].width"));
			System.out.println("Height at index " + i + " is : " +  responseBody.get("prefs.backgroundImageScaled[" + i + "].height"));
			System.out.println("Url at index " + i + " is : " +  responseBody.get("prefs.backgroundImageScaled[" + i + "].url"));
		}
		
		
	
	}
}
