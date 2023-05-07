package org.grupo2.consumidores;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ConsumidorCliente {
    private static final String BASE_URL = "http://localhost:8080/clientes";

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();

        // POST /clientes
        String requestBody = "{\"id\": 2, \"nome\": \"Nome Cliente 2\", \"cpf\": \"100.000.000-02\", "+
                "\"endereco\": \"Endereco Cliente 2\", \"email\": \"cliente2@email.com\", \"senha\": \"senha_cliente2\"}";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("POST /clientes: " + response.body());

        // GET /clientes
        request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .GET()
                .build();
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("GET /clientes: " + response.body());

        // GET /clientes/{id}
        int id = 1;
        request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .GET()
                .build();
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("GET /clientes/" + id + ": " + response.body());

        // PUT /clientes/{id}
        requestBody = "{\"id\": " + id + ", \"nome\": \"nome 3\", \"cpf\": \"456.411.988-63\", "+
                "\"endereco\": \"endereco 3\", \"email\": \"email3@email.com\", \"senha\": \"senha 3\"}";
        request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                .build();
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("PUT /clientes/" + id + ": " + response.body());

        // DELETE /clientes/{id}
        request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .DELETE()
                .build();
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
