package trainingxyz;

import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.equalTo;

//import models.Product;

public class ApiTests {

	/*GET
	 * @Test public void getCategories() { String endpoint =
	 * "http://localhost:80/API_testing/category/read.php";
	 * 
	 * //GIVEN: Specify prerequisites //WHEN: Describe the action to take //Then:
	 * Describe the expected result
	 * 
	 * var response = given().when().get(endpoint).then();
	 * System.out.println("CATEGORIAS"); response.log().body(); }
	 * 
	 * GET
	 * @Test public void getProducts() { String endpoint =
	 * "http://localhost:80/api_testing/product/read_one.php"; var response =
	 * given(). queryParam("id", 2). when(). get(endpoint). then();
	 * System.out.println("PRODUTOS"); response.log().body(); }
	 */

	/*
	 * POST Request Body "name": "---", "description": "---", "price": 0.00,
	 * "category_id: 0"
	 */

	/* POST
	 * @Test public void createProducts() { String endpoint =
	 * "http://localhost:80/api_testing/product/create.php"; String body =
	 * "{\"name\": \"Water Bottle\", \"description\": \"Blue water bottle. Holds 64 ounces\", \"price\": 12,  \"category_id\": 3}"
	 * ;
	 * 
	 * var response = given().body(body).when().post(endpoint).then();
	 * response.log().body(); } /*
	 * 
	 * /*
	 * 
	 * @Test public void updateProducts() { String endpoint =
	 * "http://localhost:80/api_testing/product/update.php"; String body =
	 * "{\"id\": 2, \"name\": \"Water Bottle\", \"price\": 15, \"description\": \"Blue water bottle. Holds 64 ounces\", \"category_id\": 2}"
	 * ;
	 * 
	 * var response = given().body(body).when().put(endpoint).then();
	 * response.log().body().toString(); }
	 */
	/*
	 * @Test public void deleteProducts() { String endpoint =
	 * "http://localhost:80/api_testing/product/delete.php"; String body =
	 * "{\"id\": 19}"; System.out.println("DELETE"); var response =
	 * given().body(body).when().delete(endpoint).then(); response.log().body(); }
	 * 
	 * @Test public void createSerializedProduct() { String endpoint =
	 * "http://localhost:80/api_testing/product/create.php"; Product product = new
	 * Product("Water Bottle", "Blue water bottle. Holds 64 ounces", 12, 3);
	 * 
	 * var response = given().body(product).when().post(endpoint).then();
	 * response.log().body(); }
	 */

	// @Test
	// public void deleteChallengeProduct() {
	// String endpoint = "http://localhost:80/api_testing/product/delete.php";
	// String body = "{\"id\": 23}";

	// var response = given().body(body).when().delete(endpoint).then();
	// response.log().body();
	// }

	// @Test
	// public void updateChallengeProduct() {
	// String endpoint = "http://localhost:80/api_testing/product/update.php";
	// Product product = new Product(21, "Sweatband", "Men Sweatband Headband", 6.0,
	// 3);

	// var response = given().body(product).when().put(endpoint).then();
	// response.log().body();
	// }

	// @Test
	// public void createChallengeProduct() {
	// String endpoint = "http://localhost:80/api_testing/product/create.php";
	// Product product = new Product("Sweatband", "Men & Women Sweatband Headband",
	// 6.95, 3);

	// var response = given().body(product).when().post(endpoint).then();
	// response.log().body();
	// }

	@Test
	public void getChallengeProduct() {
		String endpoint = "http://localhost:80/api_testing/product/read_one.php";
		
		given().queryParam("id", 23).when().get(endpoint).then().log().body();

		given().
			queryParam("id", 23).
		when().
			get(endpoint).
		//then().log().body();
		  then().
		  	assertThat().
				statusCode(200).
				body("id", equalTo("23")).
				body("name", equalTo("Sweatband")).
				body("description", equalTo("Men &amp; Women Sweatband Headband")).
				body("price", equalTo("6.95")).
				body("category_id", equalTo("3")).
				body("category_name", equalTo("Active Wear - Unisex"));
	}
}
