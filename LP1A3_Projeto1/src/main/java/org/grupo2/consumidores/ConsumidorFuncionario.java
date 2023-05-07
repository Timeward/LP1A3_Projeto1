package org.grupo2.consumidores;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ConsumidorFuncionario {
	private static final String BASE_URL = "http://localhost:8080/funcionarios";

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();

        // POST /funcionarios
        String requestBody = "{\"id\": 2, \"nome\": \"Nome Funcionario 2\", \"cpf\": \"200.000.000-02\", "+
                "\"endereco\": \"Endereco Funcionario 2\", \"email\": \"funcionario2@email.com\", \"senha\": \"senha_funcionario2\"}";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("POST /funcionarios: " + response.body());

        // GET /funcionarios
        request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .GET()
                .build();
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("GET /funcionarios: " + response.body());

        // GET /funcionarios/{id}
        int id = 1;
        request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .GET()
                .build();
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("GET /funcionarios/" + id + ": " + response.body());

        // PUT /funcionarios/{id}
        requestBody = "{\"id\": 3, \"nome\": \"Nome Funcionario 3\", \"cpf\": \"200.000.000-03\", "+
                "\"endereco\": \"Endereco Funcionario 3\", \"email\": \"funcionario3@email.com\", \"senha\": \"senha_funcionario3\"}";
        request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                .build();
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("PUT /funcionarios/" + id + ": " + response.body());

        // DELETE /funcionarios/{id}
        request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .DELETE()
                .build();
        httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
