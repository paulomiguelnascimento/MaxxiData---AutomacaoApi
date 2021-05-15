package maxiData;

import static io.restassured.RestAssured.*;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;

import static org.hamcrest.Matchers.*;

import java.util.Arrays;


public class DesafioQA {

	//String value;

@Test
	public void ListaTodasAsPessoas () {
	
		Response response =
				
	given()
		.contentType("application/json")
	
    .when()
    
		.get("http://localhost:8080/api/pessoas?pagina=0&tamanho=10");
    
		response.then()
	.statusCode(200)
	.body(equalTo("({\"pagina\":\"0\",\"tamanho\": \"10\",\"numeroRegistros\":\"3\",\"registros\":,\"id\": \"1\",\"nome\":\"João da Silva\",\"dataNascimento\":\"2000-01-01\",\"\"cpf\":\"123.456.789/00\",\"ativo\":\"true\",\"meta\": \"1000\"}\"))"))
	.log().all();
		System.out.println("RETORNO =>" + response.body().asString());
}


		@Test
		public void ListaUmaPessoaEspecifica () {
	
			Response response =
			
				given()
				.contentType("application/json")
	
				.when()
				.get("http://localhost:8080/api/pessoas/1");
	
				response.then()
				.statusCode(200)
				.body(equalTo("{\"id\":\"1\",\"nome\":João da Silva,\"dataNascimento\":\"01/01/2000\",\"cpf\":\"464.589.520-66\",\"ativo\"true,\"meta\":\"1000\"}") )
				.log().all();

				System.out.println("RETORNO =>" + response.body().asString());



}

			@Test
			public void ListaPessoaNaoEncontrada () {

				Response response =
		
						given()
						.contentType("application/json")
						.when()
						.get("http://localhost:8080/api/pessoas/27");

						response.then()
						.statusCode(404)
						.body(equalTo("{\"codigoErro\":\"1\",\"mensagemErro\":O registro 27 não foi encontrado\"}") )
						.log().all();

						System.out.println("RETORNO =>" + response.body().asString());

}


	@Test
	public void CadastraUmaPessoa () {
		
		String url =  "http://localhost:8080/api/pessoas";
		
		Response response = 
		given().contentType("application/json")
		.body("{\"id\": \"1\",\"nome\":\"João da Silva\",\"dataNascimento\":\"2000-01-01\"cpf\":\"464.589.520-66\"ativo\":\"true\"\"meta\":\"1000\"}").
		when().post(url);
		response.then().body("message",containsString ("Sucesso")).statusCode(201);
		
		System.out.println("RETORNO =>" + response.body().asString());
		
	}

		
	@Test
	public void CadastraUmaPessoaComCPFInvalido () {
		
		String url =  "http://localhost:8080/api/pessoas";
		
		Response response = 
		given().contentType("application/json")
		.body("{\"id\": \"1\",\"nome\":\"João da Silva Santos\",\"dataNascimento\":\"2000-01-01\"cpf\":\"123.456.789/00\"ativo\":\"true\"\"meta\":\"1000.00\"}").
		when().post(url);
		response.then().body("message",containsString ("O cpf informado não é valido")).statusCode(400);
		
		System.out.println("RETORNO =>" + response.body().asString());
		
	}
	
	
	@Test
	public void EditaUmaPessoa () {
		
		String url =  "http://localhost:8080/api/pessoas/1";
		
		Response response = 
		given().contentType("application/json")
		.body("{\"id\": \"1\",\"nome\":\"João da Silva Santos\",\"dataNascimento\":\"2000-01-01\"cpf\":\"789.123.999/00\"ativo\":\"false\"\"meta\":\"0\"}").
		when().put(url);
		response.then().body("message",containsString ("Sucesso")).statusCode(200);
		
		System.out.println("RETORNO =>" + response.body().asString());
		
	}

	@Test
	public void RetornaUmaListadeReceita () {
	
		Response response =
				
	given()
		.contentType("application/json")
	
    .when()
    
		.get("http://localhost:8080/api/receitas?pagina=0&tamanho=10");
			response.then()
	.statusCode(200)
	.body(equalTo("({\"pagina\":\"0\",\"tamanho\": \"10\",\"numeroRegistros\":\"2\",\"registros\":,\"id\": \"1\",\"pessoaId\":\"1\",\"data\":\"2021-01-12\",\"\"valor\":\"1100.99\",\"id\":\"2\",\"pessoaId\":\"1\",\"Data\":\"2021-02-15\",\"Valor\":\"2500\"}\"))"))
	.log().all();
		System.out.println("RETORNO =>" + response.body().asString());
}

	@Test
	public void RetornaUmaReceitaEspecifica () {
	
		Response response =
				
	given()
		.contentType("application/json")
	
    .when()
    
		.get("http://localhost:8080/api/receitas/1");
			response.then()
	.statusCode(200)
	.body(equalTo("({\"Id\":\"1\",\"pessoaId\":\"1\",\"Data\":\"2021-01-12\",\"Valor\":,\"1100.99\"}\"))"))
	.log().all();
		System.out.println("RETORNO =>" + response.body().asString());
}

	@Test
	public void RetornaReceitaNaoEncontrada () {
	
		Response response =
				
	given()
		.contentType("application/json")
	
    .when()
    
		.get("http://localhost:8080/api/receitas/15");
			response.then()
	.statusCode(404)
	.body(equalTo("{\"codigoErro\":\"1\",\"mensagemErro\":O registro 15 não foi encontrado\"}") )
	.log().all();
		System.out.println("RETORNO =>" + response.body().asString());
}

	@Test
	public void CadastraUmaReceita () {
		
		String url =  "http://localhost:8080/api/receitas";
		
		Response response = 
		given().contentType("application/json")
		.body("{\"pessoaId\": \"1\",\"data\":\"2021-02-15\",\"valor\":\"2500.00\"cpf\":\"123.456.789/00\"ativo\":\"true\"\"meta\":\"1000.00\"}").
		when().post(url);
		response.then().body("message",containsString ("Sucesso")).statusCode(201);
		
		System.out.println("RETORNO =>" + response.body().asString());
		
	}
	
	

	@Test
	public void CadastraUmaReceitaComValorNegativo () {
		
		String url =  "http://localhost:8080/api/receitas";
		
		Response response = 
		given().contentType("application/json")
		.body("{\"pessoaId\":\"1\",\"data\":\"2021-02-15\",\"valor\":\"-270.00\"}").
		when().post(url);
		response.then().body("message",containsString ("O campo valor não pode ser negativo")).statusCode(400);
		
		System.out.println("RETORNO =>" + response.body().asString());
		
	}

	
	@Test
	public void EditaUmaReceita () {
		
		String url =  "http://localhost:8080/api/receitas/1";
		
		Response response = 
		given().contentType("application/json")
		.body("{\"id\": \"1\",\"pessoaId\":\"1\",\"data\":\"2021-02-15\"valor\":\"2537.5\"}").
		when().put(url);
		response.then().body("message",containsString ("Sucesso")).statusCode(200);
		
		System.out.println("RETORNO =>" + response.body().asString());
		
	}
	
		@Test
	public void EditaUmaReceitaNaoEncontrada () {
		
		String url =  "http://localhost:8080/api/receitas/15";
		
		Response response = 
		given().contentType("application/json")
		.body("{\"pessoaId\": \"1\",\"data\":\"2021-02-15\",\"valor\":\"2500.50\"}").
		when().put(url);
		response.then().body("message",containsString ("O registro 15 não foi encontrado")).statusCode(404);
		
		System.out.println("RETORNO =>" + response.body().asString());
		
	}
	
		
	@Test
	public void DeletaUmaReceita () {
		
		given()
		.contentType("application/json")
	
		.when()
			.delete("http://localhost:8080/api/receitas/1")
		
		.then()
		.statusCode(200)
			
		.log().all();
		
	}

	
	@Test
	public void DeletaUmaReceitaNaoEncontrada () {
		
		Response response =
		
		given()
	    .contentType("application/json")
	
		.when()
		.delete("http://localhost:8080/api/receitas/15");
		
		response.then().body("message",containsString ("O registro 15 não foi encontrado")).statusCode(404);
		
		System.out.println("RETORNO =>" + response.body().asString());
		
		
	}


	@Test
	public void RetornaTotalizadores () {
	
		Response response =
				
		given()
		.contentType("application/json")
	
		.when()
		.get("http://localhost:8080/api/totalizadores");
		
		response.then()
		.statusCode(200)
		.body(equalTo("2500.25"))
		.log().all();
		System.out.println("RETORNO =>" + response.body().asString());
}
	
	
}




