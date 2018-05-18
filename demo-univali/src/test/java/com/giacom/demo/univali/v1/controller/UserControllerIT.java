package com.giacom.demo.univali.v1.controller;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.giacom.demo.univali.v1.dto.UserDTO;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-it.properties")
@ContextConfiguration(classes = TestConfigurations.class)
public class UserControllerIT {

    private static String USERS_RESOURCE;

    @LocalServerPort
    private int randomPort;

    @Before
    public void setUp() {
        port = randomPort;
        USERS_RESOURCE = baseURI + ":" + port + "/api/v1/users";
    }

    @Test
    public void whenRequestGetThenOK() {
        when()
            .get(USERS_RESOURCE)
        .then()
            .statusCode(equalTo(HttpStatus.OK.value()));
    }

    @Test
    public void whenRequestOneUserById() {

        when()
            .get(USERS_RESOURCE.concat("/{id}"), "5a85ccaa-d35a-41ac-9b84-e4c99fd8d629")
        .then()
            .statusCode(equalTo(HttpStatus.OK.value()))
        .and()
            .body("id", equalTo("5a85ccaa-d35a-41ac-9b84-e4c99fd8d629"))
            .body("name", equalTo("Maria"))
            .body("lastName", equalTo("das Dores"));
    }

    @Test
    public void whenCreateUserThenReturnNewUser() {
        UserDTO userDTO = UserDtoBuilder
            .create()
            .withName("Jose")
            .withLastName("Junior")
            .withAge(40).build();

        given()
            .header("Content-Type", "application/json")
            .body(userDTO)
        .when()
            .post(USERS_RESOURCE)
        .then()
            .statusCode(equalTo(HttpStatus.CREATED.value()))
        .and()
            .body("id", is(notNullValue()))
            .body("name", is("Jose"))
            .body("lastName", is("Junior"))
            .body("age", is(40));
    }

    @Test
    public void whenCreateUserThenReturnNewUser1() {
        UserDTO userDTO = UserDtoBuilder
            .create()
            .withName("Jose")
            .withLastName("Junior")
            .withAge(39).build();

        Response response = given()
            .header("Content-Type", "application/json")
            .body(userDTO)
        .when()
            .post(USERS_RESOURCE)
        .thenReturn();

        String json = response.getBody().prettyPrint();
        UserDTO user = new JsonPath(json).getObject("$", UserDTO.class);
        assertThat(user.getName()).isEqualTo("Jose");
        assertThat(user.getLastName()).isEqualTo("Junior");
        assertThat(user.getAge()).isEqualTo(39);
        assertThat(user.getId()).isNotEmpty();
    }


    @Test
    public void whenRequestOneByIdNotFound() {
        when()
            .get(USERS_RESOURCE.concat("/{id}"), "111")
        .then()
            .statusCode(equalTo(HttpStatus.NOT_FOUND.value()));
    }

}