package trainingxyz;

import org.junit.jupiter.api.Test;

import models.Product;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ApiTest2 {

	// RESPONSE BODY
	@Test
	public void getAllProductsWithBody() {
		System.out.println("//RESPONSE BODY");

		String endpoint = "http://localhost:80/api_testing/product/read.php";

		given().
		when().
			get(endpoint).
		then().
			log().body().
				assertThat().
				statusCode(200).
					body("records.size()", greaterThan(0)).
					body("records.id", everyItem(notNullValue())).
					body("records.name", everyItem(notNullValue())).
					body("records.description", everyItem(notNullValue())).
					body("records.price", everyItem(notNullValue())).
					body("records.category_id", everyItem(notNullValue())).
					body("records.category_name", everyItem(notNullValue())).
					body("records.id[0]", equalTo("24"));

		System.out.println("");
	}

	// RESPONSE HEADER
	@Test
	public void getAllProductsWithHeaders() {
		String endpoint = "http://localhost:80/api_testing/product/read.php";

		given().
		when().
			get(endpoint).
		then().log().headers().
			assertThat().
				statusCode(200).
				header("Content-Type", equalTo("application/json; charset=UTF-8")).
				body("records.size()", greaterThan(0)).
				body("records.id", everyItem(notNullValue())).
				body("records.name", everyItem(notNullValue())).
				body("records.description", everyItem(notNullValue())).
				body("records.price", everyItem(notNullValue())).
				body("records.category_id", everyItem(notNullValue())).
				body("records.category_name", everyItem(notNullValue())).
				body("records.id[0]", equalTo("24"));
	}

	// DESERIALIZING RESPONSE BODY
	@Test
	public void getDeserializedProduct() {
		System.out.println("//DESERIALIZING RESPONSE BODY");

		String endpoint = "http://localhost:80/api_testing/product/read_one.php";

		Product expectedProduct = new Product(2, "Water Bottle", "Blue water bottle. Holds 64 ounces", 15.00, 2,
				"Active Wear - Women");

		Product actualProduct = given().
									queryParam("id", "2").
								when().
									get(endpoint).
										as(Product.class);

		assertThat(actualProduct, samePropertyValuesAs(expectedProduct));

		System.out.println("");

	}

	/*
	 * @Test public void createChallengeProduct() { String endpoint =
	 * "http://localhost:80/api_testing/product/create.php"; Product product = new
	 * Product("Sweatband", "Men & Women Sweatband Headband", 6.95, 3);
	 * 
	 * given(). body(product). when(). post(endpoint). then().log().body(); }
	 */
	
	@Test
	public void getMultivitaminProductChallenge() {
		String endpoint = "http://localhost:80/api_testing/product/read_one.php";
		
		Product expectedProduct =  new Product(18, "Multi-Vitamin (90 capsules)", 
				"A daily dose of our Multi-Vitamins fulfills a day’s nutritional needs for over 12 vitamins and minerals.",
				10.00, 4, "Supplements");
		
		given().
			queryParam("id", "18").
		when().
			get(endpoint).
		then().
			assertThat().
				statusCode(200).
				header("Content-Type", equalTo("application/json")).
				body("id", equalTo(String.valueOf(expectedProduct.getId()))).
				body("name", equalTo(expectedProduct.getName())).
				body("description", equalTo(expectedProduct.getDescription())).
				body("price", equalTo(String.format("%.2f", expectedProduct.getPrice()).replace(",", "."))).
				body("category_id", equalTo(String.valueOf(expectedProduct.getCategory_id()))).
				body("category_name", equalTo(String.valueOf(expectedProduct.getCategory_name())));
	}	
}
