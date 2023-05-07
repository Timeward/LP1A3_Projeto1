package org.grupo2.consumidores;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ConsumidorLivro {
	private static final String BASE_URL = "http://localhost:8080/livros";

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();

        // POST /livros
        String requestBody = "{\"id\": 4, \"titulo\": \"Titulo Livro 4\", \"autor\": \"Autor Livro 4\", "+
                "\"editora\": \"Editora Livro 4\", \"ano_publicacao\": 2023, \"num_exemplares\": 4, \"num_exemplares_disponiveis\": 4}";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("POST /livros: " + response.body());

        // GET /livros
        request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .GET()
                .build();
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("GET /livros: " + response.body());

        // GET /livros/{id}
        int id = 1;
        request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .GET()
                .build();
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("GET /livros/" + id + ": " + response.body());

        // PUT /livros/{id}
        requestBody = "{\"id\": 5, \"titulo\": \"Titulo Livro 5\", \"autor\": \"Autor Livro 5\", "+
                "\"editora\": \"Editora Livro 5\", \"ano_publicacao\": 2023, \"num_exemplares\": 5, \"num_exemplares_disponiveis\": 5}";
        request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                .build();
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("PUT /livros/" + id + ": " + response.body());

        // DELETE /livros/{id}
        request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .DELETE()
                .build();
        httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
