package br.com.renner.consulta;


import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static io.restassured.RestAssured.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ConsultaCepTest {

    @BeforeAll
    public void setup(){
        //Configurar o acesso a API
        baseURI = "https://viacep.com.br";
        basePath = "/ws";

    }

    @Test
    public void testConsultaCepValido(){

        String cepValido = "07173130";

        given()
                .pathParam("cep", cepValido)
                .accept(ContentType.JSON)
                .when()
                .get("/{cep}/json")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);

}

@Test
    public void testConsultaCepInvalido(){

    String cepInvalido = "071730000";

    given()
            .pathParam("cep", cepInvalido)
            .accept(ContentType.JSON)
            .when()
            .get("/{cep}/json")
            .then()
            .log().all()
            .assertThat()
            .statusCode(400);

}

}
