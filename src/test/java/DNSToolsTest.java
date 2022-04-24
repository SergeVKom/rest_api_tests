import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class DNSToolsTest {

    private static final String ID = "id";
    private static String token;

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://networkcalc.com/api/";
        token = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(Map.of(
                        "client_id", "rxF1k6kL2OXz2Nq1",
                        "client_secret", "i3Qcb529hDRu8TxV8JRwGrw9rF4eMCovG73878nKJhnphAZ2iX7i7C3hXPSTwyBA",
                        "grant_type", "client_credentials")
                )
                .post("auth/token")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("token.access_token");
    }

    @Test
    void deleteUnexistingDNS() {
        RestAssured
                .given().log().all()
                .header("Authorization", "Bearer " + token)
                .pathParam(ID, "123456")
                .delete("dns/saved/{id}")
                .then().log().all()
                .statusCode(400)
                .body("status", equalTo("NOT_FOUND"));
    }
}
