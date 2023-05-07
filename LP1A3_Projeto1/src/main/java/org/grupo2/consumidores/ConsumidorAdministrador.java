package org.grupo2.consumidores;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ConsumidorAdministrador {
    private static final String BASE_URL = "http://localhost:8080/administradores";

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();

        // POST /administradores
        String requestBody = "{\"id\": 2, \"nome\": \"Nome Administrador 2\", \"cpf\": \"300.000.000-02\", " +
                "\"endereco\": \"Endereco Administrador 2\", \"email\": \"administrador2@email.com\", \"senha\": \"senha_administrador2\"}";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("POST /administradores: " + response.body());

        // GET /administradores
        request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .GET()
                .build();
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("GET /administradores: " + response.body());

        // GET /administradores/{id}
        int id = 1;
        request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .GET()
                .build();
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("GET /administradores/" + id + ": " + response.body());

        // PUT /administradores/{id}
        requestBody = "{\"id\": 3, \"nome\": \"Nome Administrador 3\", \"cpf\": \"300.000.000-03\", " +
                "\"endereco\": \"Endereco Administrador 3\", \"email\": \"administrador3@email.com\", \"senha\": \"senha_administrador3\"}";
        request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                .build();
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("PUT /administradores/" + id + ": " + response.body());

        // DELETE /clientes/{id}
        request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .DELETE()
                .build();
       httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
