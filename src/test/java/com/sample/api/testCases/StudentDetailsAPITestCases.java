package com.sample.api.testCases;

import org.testng.annotations.Test;

import com.sample.api.util.CommonConstants;
import com.sample.ui.baseLibrary.DriverFunctions;
import com.sample.ui.util.AssertionUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StudentDetailsAPITestCases {

	public DriverFunctions functions = new DriverFunctions();
	public static int studentId;
	public static String firstName;
	public static String middleName;
	public static String lastName;
	public static String dateOfBirth;

	@Test
	public void addNewStudent() {
		String timeStamp = String.valueOf(System.currentTimeMillis());
		String body = "{\r\n" + "  \"id\": 0,\r\n" + "  \"first_name\": \"" + "fname_" + timeStamp + "\" ,\r\n"
				+ "  \"middle_name\": \"" + "mname_" + timeStamp + "\",\r\n" + "  \"last_name\": \"" + "lname_"
				+ timeStamp + "\",\r\n" + "  \"date_of_birth\": \"10-Jun-1990\"\r\n" + "}";
		String url = functions.getUrl();
		RequestSpecification request = RestAssured.given();
		request.relaxedHTTPSValidation();
		request.header("Content-Type", "application/json");
		request.baseUri(url);
		request.body(body);
		System.out.println("the request url is: " + url + CommonConstants.addNewStudentDetails);
		System.out.println("the request body is: " + body);
		Response response = request.post(CommonConstants.addNewStudentDetails);
		System.out.println("response body is displayed as : " + response.getBody().asString());
		if (response.getStatusCode() == CommonConstants.HTTPACCEPTED) {
			AssertionUtil.addVerificationLogger(
					"status code has matched and is displayed as " + response.getStatusCode(), "pass");
			studentId = response.getBody().jsonPath().get("id");
			firstName = response.getBody().jsonPath().get("first_name");
			middleName = response.getBody().jsonPath().get("middle_name");
			lastName = response.getBody().jsonPath().get("last_name");
			dateOfBirth = response.getBody().jsonPath().get("date_of_birth");
			System.out.println("student Id is set as : " + studentId);

		} else {
			AssertionUtil.addVerificationLogger(
					"status code has not matched and is displayed as " + response.getStatusCode(), "fail");

		}

	}

	@Test(dependsOnMethods = { "addNewStudent" })
	public void getStudentDetails() {
		String url = functions.getUrl();
		RequestSpecification request = RestAssured.given();
		request.relaxedHTTPSValidation();
		request.baseUri(url);
		System.out
				.println("the request url is: " + url + String.format(CommonConstants.getStudentDetails, studentId));
		Response response = request.get(String.format(CommonConstants.getStudentDetails, studentId));
		System.out.println("response body is displayed as : " + response.getBody().asString());
		if (response.getStatusCode() == CommonConstants.HTTPOK) {
			if (response.getBody().asString().contains(String.valueOf(studentId))
					&& response.getBody().asString().contains(String.valueOf(firstName))
					&& response.getBody().asString().contains(String.valueOf(middleName))
					&& response.getBody().asString().contains(String.valueOf(lastName))
					&& response.getBody().asString().contains(String.valueOf(dateOfBirth))) {
				AssertionUtil.addVerificationLogger("response body contains id: " + studentId, "pass");
				AssertionUtil.addVerificationLogger("response body contains fist name: " + firstName, "pass");
				AssertionUtil.addVerificationLogger("response body contains middle name: " + middleName, "pass");
				AssertionUtil.addVerificationLogger("response body contains last name: " + lastName, "pass");
				AssertionUtil.addVerificationLogger("response body contains date of birth: " + dateOfBirth, "pass");

			} else {
				AssertionUtil.addVerificationLogger("response body does not contains id: " + studentId, "fail");

			}

		}
	}
	
	@Test(dependsOnMethods = { "getStudentDetails" })
	public void updateStudent() {
		String timeStamp = String.valueOf(System.currentTimeMillis());
		String body = "{\r\n" + "  \"id\": \""+studentId+ "\",\r\n" + "  \"first_name\": \"" + "fname_" + timeStamp + "\" ,\r\n"
				+ "  \"middle_name\": \"" + "mname_" + timeStamp + "\",\r\n" + "  \"last_name\": \"" + "lname_"
				+ timeStamp + "\",\r\n" + "  \"date_of_birth\": \"10-Jun-1990\"\r\n" + "}";
		String url = functions.getUrl();
		RequestSpecification request = RestAssured.given();
		request.relaxedHTTPSValidation();
		request.header("Content-Type", "application/json");
		request.baseUri(url);
		request.body(body);
		System.out.println("the request url is: " + url + String.format(CommonConstants.updateStudentDetails, studentId));
		System.out.println("the request body is: " + body);
		Response response = request.put(String.format(CommonConstants.updateStudentDetails, studentId));
		System.out.println("response body is displayed as : " + response.getBody().asString());
		if (response.getStatusCode() == CommonConstants.HTTPOK) {
			AssertionUtil.addVerificationLogger(
					"status code has matched and is displayed as " + response.getStatusCode(), "pass");

		} else {
			System.out.print("body is displayed as : " +response.getBody().asString());
			AssertionUtil.addVerificationLogger(
					"status code has not matched and is displayed as " + response.getStatusCode(), "fail");
			

		}

	}
	
	@Test(dependsOnMethods = { "updateStudent" })
	public void DeleteStudent() {
		String url = functions.getUrl();
		RequestSpecification request = RestAssured.given();
		request.relaxedHTTPSValidation();
		request.header("Content-Type", "application/json");
		request.baseUri(url);
		System.out.println("the request url is: " + url + String.format(CommonConstants.deleteStudentDetails, studentId));
		Response response = request.delete(String.format(CommonConstants.deleteStudentDetails, studentId));
		System.out.println("response body is displayed as : " + response.getBody().asString());
		if (response.getStatusCode() == CommonConstants.HTTPOK) {
			AssertionUtil.addVerificationLogger(
					"status code has matched and is displayed as " + response.getStatusCode(), "pass");

		} else {
			System.out.print("body is displayed as : " +response.getBody().asString());
			AssertionUtil.addVerificationLogger(
					"status code has not matched and is displayed as " + response.getStatusCode(), "fail");
			

		}

	}

}
