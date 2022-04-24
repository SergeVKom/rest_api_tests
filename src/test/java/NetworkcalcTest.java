import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

public class NetworkcalcTest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://networkcalc.com/api/";
    }

    private static List<Arguments> testData() {
        return Arrays.asList(
                Arguments.of("2", "2", "1010", "1010"),
                Arguments.of("2", "8", "1010", "12"),
                Arguments.of("2", "10", "1010", "10"),
                Arguments.of("2", "16", "1010", "a"),
                Arguments.of("8", "2", "12", "1010"),
                Arguments.of("8", "8", "12", "12"),
                Arguments.of("8", "10", "12", "10"),
                Arguments.of("8", "16", "12", "a"),
                Arguments.of("10", "2", "10", "1010"),
                Arguments.of("10", "8", "10", "12"),
                Arguments.of("10", "10", "10", "10"),
                Arguments.of("10", "16", "10", "a"),
                Arguments.of("16", "2", "a", "1010"),
                Arguments.of("16", "8", "a", "12"),
                Arguments.of("16", "10", "a", "10"),
                Arguments.of("16", "16", "a", "a")
        );
    }

    @ParameterizedTest(name = "#{index} - Convert number {2} from {0} to {1}")
    @MethodSource("testData")
    void positiveTest(String fromBase, String toBase, String originalNumber, String convertedNumber) {
        Response expectedResponse = new Response()
                .setStatus("OK")
                .setOriginal(originalNumber)
                .setConverted(convertedNumber)
                .setFrom(fromBase)
                .setTo(toBase);

        Response actualResponse = RestAssured
                .given().log().all()
                .pathParam(Params.NUMBER, originalNumber)
                .queryParam(Params.FROM, fromBase)
                .queryParam(Params.TO, toBase)
                .get("binary/{number}")
                .then().log().all()
                .statusCode(200)
                .extract().body().as(Response.class);

        Assertions.assertEquals(expectedResponse, actualResponse);
    }
}
