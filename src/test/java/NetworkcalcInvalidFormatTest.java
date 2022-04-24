import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

public class NetworkcalcInvalidFormatTest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://networkcalc.com/api/";
    }

    private static List<Arguments> invalidNumber() {
        return Arrays.asList(
                Arguments.of("2", "2", "2"),
                Arguments.of("8", "8", "9"),
                Arguments.of("10", "10", "a"),
                Arguments.of("16", "16", "g")
        );
    }

    @ParameterizedTest(name = "#{index} - Use invalid number {2} for base {0}")
    @MethodSource("invalidNumber")
    void invalidDataTest(String fromBase, String toBase, String originalNumber) {
        ErrorResponse expectedResponse = new ErrorResponse()
                .setStatus("INVALID_NUMBER_FORMAT")
                .setError("INVALID_NUMBER_FORMAT");

        ErrorResponse actualResponse = RestAssured
                .given().log().all()
                .pathParam(Params.NUMBER, originalNumber)
                .queryParam(Params.FROM, fromBase)
                .queryParam(Params.TO, toBase)
                .get("binary/{number}")
                .then().log().all()
                .statusCode(400)
                .extract().body().as(ErrorResponse.class);

        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    private static List<Arguments> invalidBase() {
        return Arrays.asList(
                Arguments.of("3", "2", "INVALID_FROM_BASE"),
                Arguments.of("2", "3", "INVALID_TO_BASE")
        );
    }

    @ParameterizedTest(name = "#{index} - Use {2}")
    @MethodSource("invalidBase")
    void invalidBaseTest(String fromBase, String toBase, String errorMessage) {
        ErrorResponse expectedResponse = new ErrorResponse()
                .setStatus(errorMessage)
                .setError(errorMessage);

        ErrorResponse actualResponse = RestAssured
                .given().log().all()
                .pathParam(Params.NUMBER, "10")
                .queryParam(Params.FROM, fromBase)
                .queryParam(Params.TO, toBase)
                .get("binary/{number}")
                .then().log().all()
                .statusCode(400)
                .extract().body().as(ErrorResponse.class);

        Assertions.assertEquals(expectedResponse, actualResponse);
    }
}
