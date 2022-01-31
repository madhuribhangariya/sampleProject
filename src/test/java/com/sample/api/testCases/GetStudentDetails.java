package com.sample.api.testCases;

import org.testng.annotations.Test;

import com.sample.api.util.CommonConstants;
import com.sample.ui.baseLibrary.DriverFunctions;
import com.sample.ui.util.AssertionUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetStudentDetails {

	public DriverFunctions functions = new DriverFunctions();

	@Test
	public void getStudentDetails() {
		String studentId="548476";
		String url = functions.getUrl();
		RequestSpecification request = RestAssured.given();
		request.relaxedHTTPSValidation();
		request.baseUri(url);
		System.out.println("the request url is: "+url+String.format(CommonConstants.getStudentDetailsUrl, studentId));
		Response response = request.get(String.format(CommonConstants.getStudentDetailsUrl, studentId));
		System.out.println("request body is displayed as : "+response.getBody().asString());
		if (response.getStatusCode() == CommonConstants.HTTPOK) {
			AssertionUtil.addVerificationLogger(
					"status code has matched and is displayed as " + response.getStatusCode(), "pass");

		}
		else
		{
			AssertionUtil.addVerificationLogger(
					"status code has not matched and is displayed as " + response.getStatusCode(), "fail");

		}
		
		if(response.getBody().asString().contains(studentId))
		{
			AssertionUtil.addVerificationLogger(
					"request body contains id: " + studentId, "pass");

		}
		else
		{
			AssertionUtil.addVerificationLogger(
					"request body does not contains id: " + studentId, "fail");

		}

	}

}
