package eu.dedalus.analyser.controller

import eu.dedalus.analyser.DedalusApplication
import eu.dedalus.analyser.util.*
import io.restassured.RestAssured
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort


@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [DedalusApplication::class]
)
@EnableAutoConfiguration
internal class AnalyserControllerTest {

    @LocalServerPort
    private val port = 5000

    @Test
    fun analyse_Vowels() {
        val response = RestAssured.given().log().all()
            .urlEncodingEnabled(false)
            .contentType("application/json")
            .body(analyseDtoVowels)
            .post("http://localhost:$port/api/v1/analyse")
            .prettyPeek()
            .then().assertThat()
            .statusCode(200).extract().response()

        assertThat(
            response.jsonPath().get<HashMap<String, List<HashMap<String, String>>>>("$")["list"]?.size
        ).isEqualTo(3)
        assertThat(
            response.jsonPath().get<HashMap<String, List<HashMap<String, String>>>>("$")["list"]?.get(0)?.get("letter")
        ).isEqualTo("A")
        assertThat(
            response.jsonPath().get<HashMap<String, List<HashMap<String, Int>>>>("$")["list"]?.get(0)?.get("times")
        ).isEqualTo(3)
        assertThat(
            response.jsonPath().get<HashMap<String, List<HashMap<String, String>>>>("$")["list"]?.get(1)?.get("letter")
        ).isEqualTo("E")
        assertThat(
            response.jsonPath().get<HashMap<String, List<HashMap<String, Int>>>>("$")["list"]?.get(1)?.get("times")
        ).isEqualTo(3)
        assertThat(
            response.jsonPath().get<HashMap<String, List<HashMap<String, String>>>>("$")["list"]?.get(2)?.get("letter")
        ).isEqualTo("O")
        assertThat(
            response.jsonPath().get<HashMap<String, List<HashMap<String, Int>>>>("$")["list"]?.get(2)?.get("times")
        ).isEqualTo(1)
    }

    @Test
    fun analyse_Consonants() {
        val response = RestAssured.given().log().all()
            .urlEncodingEnabled(false)
            .contentType("application/json")
            .body(analyseDtoConsonants)
            .post("http://localhost:$port/api/v1/analyse")
            .prettyPeek()
            .then().assertThat()
            .statusCode(200).extract().response()

        assertThat(
            response.jsonPath().get<HashMap<String, List<HashMap<String, String>>>>("$")["list"]?.size
        ).isEqualTo(8)
        assertThat(
            response.jsonPath().get<HashMap<String, List<HashMap<String, String>>>>("$")["list"]?.get(0)?.get("letter")
        ).isEqualTo("R")
        assertThat(
            response.jsonPath().get<HashMap<String, List<HashMap<String, Int>>>>("$")["list"]?.get(0)?.get("times")
        ).isEqualTo(2)
        assertThat(
            response.jsonPath().get<HashMap<String, List<HashMap<String, String>>>>("$")["list"]?.get(1)?.get("letter")
        ).isEqualTo("C")
        assertThat(
            response.jsonPath().get<HashMap<String, List<HashMap<String, Int>>>>("$")["list"]?.get(1)?.get("times")
        ).isEqualTo(1)
        assertThat(
            response.jsonPath().get<HashMap<String, List<HashMap<String, String>>>>("$")["list"]?.get(2)?.get("letter")
        ).isEqualTo("S")
        assertThat(
            response.jsonPath().get<HashMap<String, List<HashMap<String, Int>>>>("$")["list"]?.get(2)?.get("times")
        ).isEqualTo(1)
    }

    @Test
    fun analyse_OnlyNumbers() {
        val response = RestAssured.given().log().all()
            .urlEncodingEnabled(false)
            .contentType("application/json")
            .body(analyseDtoOnlyNumbers)
            .post("http://localhost:$port/api/v1/analyse")
            .prettyPeek()
            .then().assertThat()
            .statusCode(200).extract().response()

        assertThat(
            response.jsonPath().get<HashMap<String, List<HashMap<String, String>>>>("$")["list"]?.size
        ).isEqualTo(0)
    }

    @Test
    fun analyse_OnlySpecialChars() {
        val response = RestAssured.given().log().all()
            .urlEncodingEnabled(false)
            .contentType("application/json")
            .body(analyseDtoOnlySpecialChars)
            .post("http://localhost:$port/api/v1/analyse")
            .prettyPeek()
            .then().assertThat()
            .statusCode(200).extract().response()

        assertThat(
            response.jsonPath().get<HashMap<String, List<HashMap<String, String>>>>("$")["list"]?.size
        ).isEqualTo(0)
    }

    @Test
    fun analyse_OnlySpecialChars_Consonants() {
        val response = RestAssured.given().log().all()
            .urlEncodingEnabled(false)
            .contentType("application/json")
            .body(analyseDtoOnlySpecialCharsConsonants)
            .post("http://localhost:$port/api/v1/analyse")
            .prettyPeek()
            .then().assertThat()
            .statusCode(200).extract().response()

        assertThat(
            response.jsonPath().get<HashMap<String, List<HashMap<String, String>>>>("$")["list"]?.size
        ).isEqualTo(0)
    }

    @Test
    fun analyse_OnlyConsonants_Vowels() {
        val response = RestAssured.given().log().all()
            .urlEncodingEnabled(false)
            .contentType("application/json")
            .body(analyseDtoOnlyConsonantsVowels)
            .post("http://localhost:$port/api/v1/analyse")
            .prettyPeek()
            .then().assertThat()
            .statusCode(200).extract().response()

        assertThat(
            response.jsonPath().get<HashMap<String, List<HashMap<String, String>>>>("$")["list"]?.size
        ).isEqualTo(0)
    }

    @Test
    fun analyse_OnlyVowels_Consonants() {
        val response = RestAssured.given().log().all()
            .urlEncodingEnabled(false)
            .contentType("application/json")
            .body(analyseDtoOnlyVowelsConsonants)
            .post("http://localhost:$port/api/v1/analyse")
            .prettyPeek()
            .then().assertThat()
            .statusCode(200).extract().response()

        assertThat(
            response.jsonPath().get<HashMap<String, List<HashMap<String, String>>>>("$")["list"]?.size
        ).isEqualTo(0)
    }

    @Test
    fun analyse_UpperCaseAndLowerCase() {
        val response = RestAssured.given().log().all()
            .urlEncodingEnabled(false)
            .contentType("application/json")
            .body(analyseDtoUpperCaseAndLowerCase)
            .post("http://localhost:$port/api/v1/analyse")
            .prettyPeek()
            .then().assertThat()
            .statusCode(200).extract().response()

        assertThat(
            response.jsonPath().get<HashMap<String, List<HashMap<String, String>>>>("$")["list"]?.size
        ).isEqualTo(2)
        assertThat(
            response.jsonPath().get<HashMap<String, List<HashMap<String, String>>>>("$")["list"]?.get(0)?.get("letter")
        ).isEqualTo("P")
        assertThat(
            response.jsonPath().get<HashMap<String, List<HashMap<String, Int>>>>("$")["list"]?.get(0)?.get("times")
        ).isEqualTo(4)
        assertThat(
            response.jsonPath().get<HashMap<String, List<HashMap<String, String>>>>("$")["list"]?.get(1)?.get("letter")
        ).isEqualTo("A")
        assertThat(
            response.jsonPath().get<HashMap<String, List<HashMap<String, Int>>>>("$")["list"]?.get(1)?.get("times")
        ).isEqualTo(4)
    }

    @Test
    fun analyse_EmptyText() {
        val response = RestAssured.given().log().all()
            .urlEncodingEnabled(false)
            .contentType("application/json")
            .body(analyseDtoEmptyText)
            .post("http://localhost:$port/api/v1/analyse")
            .prettyPeek()
            .then().assertThat()
            .statusCode(200).extract().response()

        assertThat(
            response.jsonPath().get<HashMap<String, List<HashMap<String, String>>>>("$")["list"]?.size
        ).isEqualTo(0)
    }

    @Test
    fun analyse_EmptyText_Vowels() {
        val response = RestAssured.given().log().all()
            .urlEncodingEnabled(false)
            .contentType("application/json")
            .body(analyseDtoEmptyVowels)
            .post("http://localhost:$port/api/v1/analyse")
            .prettyPeek()
            .then().assertThat()
            .statusCode(200).extract().response()

        assertThat(
            response.jsonPath().get<HashMap<String, List<HashMap<String, String>>>>("$")["list"]?.size
        ).isEqualTo(0)
    }

    @Test
    fun analyse_EmptyText_Consonants() {
        val response = RestAssured.given().log().all()
            .urlEncodingEnabled(false)
            .contentType("application/json")
            .body(analyseDtoEmptyConsonants)
            .post("http://localhost:$port/api/v1/analyse")
            .prettyPeek()
            .then().assertThat()
            .statusCode(200).extract().response()

        assertThat(
            response.jsonPath().get<HashMap<String, List<HashMap<String, String>>>>("$")["list"]?.size
        ).isEqualTo(0)
    }
}