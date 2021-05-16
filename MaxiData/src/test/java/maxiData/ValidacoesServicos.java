package maxiData;

import static io.restassured.RestAssured.*;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;

import static org.hamcrest.Matchers.*;

import java.util.Arrays;


public class ValidacoesServicos {


@Test
	public void ListaTodasAsPessoas () {
	
		Response response =
				
	given()
		.contentType("application/json")
	
    .when()
    
		.get("http://localhost:8080/ListaTodasPessoas");
		
		
    
		response.then()
	.statusCode(200)
	.body(equalTo("[{\"1\":{\"Pagina\":\"0\"}},{\"2\":{\"tamanho\":\"2\"}},{\"3\":{\"numeroRegistros\":\"3\"}},{\"4\":{\"id\":\"1\",\"nome\":\"João da Silva\",\"dataNascimento\":\"2000-01-01\",\"cpf\":\"123.456.789/00\",\"ativo\":\"true\",\"meta\":\"1000\"}},{\"5\":{\"id\":\"2\",\"nome\":\"Maria da Silva\",\"dataNascimento\":\"1998-01-01\",\"cpf\":\"987.654.321/00\",\"ativo\":\"true\",\"meta\":\"1000\"}},{\"6\":{\"id\":\"3\",\"nome\":\"José da Silva\",\"dataNascimento\":\"2010-09-27\",\"cpf\":\"null\",\"ativo\":\"false\",\"meta\":\"0.5\"}}]"))
	.log().all();
		System.out.println("RETORNO =>" + response.body().asString());
}


		
}




