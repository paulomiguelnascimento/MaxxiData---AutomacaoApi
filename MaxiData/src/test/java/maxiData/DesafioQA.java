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
    
		.get("http://localhost:8080/ListaTodasPessoas");
    
		response.then()
	.statusCode(200)
	.body(equalTo("[{\"1\":{\"Pagina\":\"0\"}},{\"2\":{\"tamanho\":\"2\"}},{\"3\":{\"numeroRegistros\":\"3\"}},{\"4\":{\"id\":\"1\",\"nome\":\"João da Silva\",\"dataNascimento\":\"2000-01-01\",\"cpf\":\"123.456.789/00\",\"ativo\":\"true\",\"meta\":\"1000\"}},{\"5\":{\"id\":\"2\",\"nome\":\"Maria da Silva\",\"dataNascimento\":\"1998-01-01\",\"cpf\":\"987.654.321/00\",\"ativo\":\"true\",\"meta\":\"1000\"}},{\"6\":{\"id\":\"3\",\"nome\":\"José da Silva\",\"dataNascimento\":\"2010-09-27\",\"cpf\":\"null\",\"ativo\":\"false\",\"meta\":\"0.5\"}}]"))
	.log().all();
		System.out.println("RETORNO =>" + response.body().asString());
}


		@Test
		public void ListaUmaPessoaEspecifica () {
	
			Response response =
			
				given()
				.contentType("application/json")
	
				.when()
				.get("http://localhost:8080/ListaPessoaEspecifica/1");
	
				response.then()
				.statusCode(200)
				.body(equalTo("[{\"\":{\"id\":\"1\",\"nome\":\"João da Silva\",\"dataNascimento\":\"2000-01-01\",\"cpf\":\"123.456.789/00\",\"ativo\":\"true\",\"meta\":\"1000\"}}]") )
				.log().all();

				System.out.println("RETORNO =>" + response.body().asString());



}

			@Test
			public void ListaPessoaNaoEncontrada () {

				Response response =
		
						given()
						.contentType("application/json")
						.when()
						.get("http://localhost:8080/ListaPessoaEspecifica/27");

						response.then()
						.statusCode(404)
						.body(equalTo("{\"codigoErro\":1,\"mensagemErro\":\"O registro 27 não foi encontrado\"}") )
						.log().all();

						System.out.println("RETORNO =>" + response.body().asString());

}


	@Test
	public void CadastraUmaPessoa () {
		
		String url =  "http://localhost:8080/CadastraPessoa";
		
		Response response = 
		given().contentType("application/json")
		.body("{\"id\": \"1\",\"nome\":\"João da Silva\",\"dataNascimento\":\"2000-01-01\"cpf\":\"464.589.520-66\"ativo\":\"true\"\"meta\":\"1000\"}").
		when().post(url);
		response.then().body("message",containsString ("Sucesso")).statusCode(201);
		
		System.out.println("RETORNO =>" + response.body().asString());
		
	}

		
	@Test
	public void CadastraUmaPessoaComCPFInvalido () {
		
		String url =  "http://localhost:8080/CadastraPessoaCPFInvalido";
		
		Response response = 
		given().contentType("application/json")
		.body("{\"id\": \"1\",\"nome\":\"João da Silva Santos\",\"dataNascimento\":\"2000-01-01\"cpf\":\"123.456.789/00\"ativo\":\"true\"\"meta\":\"1000.00\"}").
		when().post(url);
		response.then().body("message",containsString ("O cpf informado não é valido")).statusCode(400);
		
		System.out.println("RETORNO =>" + response.body().asString());
		
	}
	
	
	@Test
	public void EditaUmaPessoa () {
		
		String url =  "http://localhost:8080/EditarPessoa/1";
		
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
    
		.get("http://localhost:8080/ListaTodasReceitas");
			response.then()
	.statusCode(200)
	.body(equalTo("[{\"1\":{\"Pagina\":\"0\"}},{\"2\":{\"tamanho\":\"10\"}},{\"3\":{\"numeroRegistros\":\"2\"}},{\"4\":{\"id\":\"1\",\"pessoaId\":\"1\",\"data\":\"2021-01-12\",\"valor\":\"1100.99\"}},{\"5\":{\"id\":\"2\",\"pessoaId\":\"2\",\"data\":\"2021-02-15\",\"valor\":\"2500\"}}]"))
	.log().all();
		System.out.println("RETORNO =>" + response.body().asString());
}

	@Test
	public void RetornaUmaReceitaEspecifica () {
	
		Response response =
				
	given()
		.contentType("application/json")
	
    .when()
    
		.get("http://localhost:8080/ListaReceitaEspecifica/1");
			response.then()
	.statusCode(200)
	.body(equalTo("[{\"1\":{\"id\":\"1\",\"pessoaId\":\"1\",\"data\":\"2021-01-12\",\"valor\":\"1100.99\"}}]"))
	.log().all();
		System.out.println("RETORNO =>" + response.body().asString());
}

	@Test
	public void RetornaReceitaNaoEncontrada () {
	
		Response response =
				
	given()
		.contentType("application/json")
	
    .when()
    
		.get("http://localhost:8080/ListaReceita/25");
			response.then()
	.statusCode(400)
	.body(equalTo("{\"codigoErro\":1,\"mensagemErro\":\"O registro 25 não foi encontrado\"}") )
	.log().all();
		System.out.println("RETORNO =>" + response.body().asString());
}

	@Test
	public void CadastraUmaReceita () {
		
		String url =  "http://localhost:8080/CadastraReceita";
		
		Response response = 
		given().contentType("application/json")
		.body("{\"pessoaId\": \"1\",\"data\":\"2021-02-15\",\"valor\":\"2500.00\"cpf\":\"123.456.789/00\"ativo\":\"true\"\"meta\":\"1000.00\"}").
		when().post(url);
		response.then().body("message",containsString ("Sucesso")).statusCode(201);
		
		System.out.println("RETORNO =>" + response.body().asString());
		
	}
	
	

	@Test
	public void CadastraUmaReceitaComValorNegativo () {
		
		String url =  "http://localhost:8080/CadastraReceitaNegativa";
		
		Response response = 
		given().contentType("application/json")
		.body("{\"pessoaId\":\"1\",\"data\":\"2021-02-15\",\"valor\":\"-270.00\"}").
		when().post(url);
		response.then().body("message",containsString ("O campo valor não pode ser negativo")).statusCode(400);
		
		System.out.println("RETORNO =>" + response.body().asString());
		
	}

	
	@Test
	public void EditaUmaReceita () {
		
		String url =  "http://localhost:8080/EditarReceita/1";
		
		Response response = 
		given().contentType("application/json")
		.body("{\"id\": \"1\",\"pessoaId\":\"1\",\"data\":\"2021-02-15\"valor\":\"2537.5\"}").
		when().put(url);
		response.then().body("message",containsString ("Sucesso")).statusCode(200);
		
		System.out.println("RETORNO =>" + response.body().asString());
		
	}
	
		@Test
	public void EditaUmaReceitaNaoEncontrada () {
		
		String url =  "http://localhost:8080/EditarReceita/NaoEncontrada";
		
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
			.delete("http://localhost:8080/deletarReceita/1")
		
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
		.delete("http://localhost:8080/receitas/15");
		
		response.then().body("message",containsString ("O registro 15 não foi encontrado")).statusCode(404);
		
		System.out.println("RETORNO =>" + response.body().asString());
		
		
	}


	@Test
	public void RetornaTotalizadores () {
	
		Response response =
				
		given()
		.contentType("application/json")
	
		.when()
		.get("http://localhost:8080/totalizadores");
		
		response.then()
		.statusCode(200)
		.body(equalTo("[{\"1\":{\"Valor\":\"2500.25\"}}]"))
		.log().all();
		System.out.println("RETORNO =>" + response.body().asString());
}
	
	
}




