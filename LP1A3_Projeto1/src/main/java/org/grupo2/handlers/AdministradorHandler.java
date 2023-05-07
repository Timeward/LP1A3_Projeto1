package org.grupo2.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.grupo2.modelos.Administrador;
import org.grupo2.modelos.Biblioteca;
import org.grupo2.modelos.Cliente;

import java.io.IOException;
import java.io.OutputStream;

public class AdministradorHandler implements HttpHandler {
    private static Integer proximoId = 2;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String[] pathParts = path.split("/");
        if (pathParts.length == 3 && "administradores".equals(pathParts[1])) {
            Integer id = Integer.parseInt(pathParts[2]);
            if ("GET".equals(requestMethod)) {
                handleGetAdministrador(exchange, id);
            } else if ("DELETE".equals(requestMethod)) {
                handleDeleteAdministrador(exchange, id);
            } else if ("PUT".equals(requestMethod)) {
                handlePutAdministrador(exchange, id);
            }
        } else if ("GET".equals(requestMethod)) {
            handleGetAdministradores(exchange);
        } else if ("POST".equals(requestMethod)) {
            handlePostAdministrador(exchange);
        } else {
            handleBadRequest(exchange, "Requisicao invalida: " + requestMethod + " " + path);
        }
    }

	// CREATE
    private void handlePostAdministrador(HttpExchange exchange) throws IOException {
        String requestBody = new String(exchange.getRequestBody().readAllBytes());
        Administrador administrador = Administrador.fromJson(requestBody);
        administrador.setId(proximoId++);
        Biblioteca.getAdministradores().put(administrador.getId(), administrador);
        sendResponse(exchange, administrador.toJson(), 201);
    }

	// READ ALL
    private void handleGetAdministradores(HttpExchange exchange) throws IOException {
        StringBuilder response = new StringBuilder();
        response.append("[");
        for (Administrador administrador : Biblioteca.getAdministradores().values()) {
            response.append(administrador.toJson());
            response.append(",");
        }
        if (Biblioteca.getAdministradores().size() > 0) {
            response.deleteCharAt(response.length() - 1);
        }
        response.append("]");
        sendResponse(exchange, response.toString());
    }

    // READ
    private void handleGetAdministrador(HttpExchange exchange, Integer id) throws IOException {
        Administrador administrador = Biblioteca.getAdministradores().get(id);
        if (administrador == null) {
            handleNotFound(exchange, "Administrador não encontrado com o id " + id);
        } else {
            sendResponse(exchange, administrador.toJson());
        }
    }

    // UPDATE
    private void handlePutAdministrador(HttpExchange exchange, Integer id) throws IOException {
        Administrador administrador = Biblioteca.getAdministradores().get(id);
        if (administrador == null) {
            handleNotFound(exchange, "Cliente não encontrado com ID: " + id);
        } else {
            String requestBody = new String(exchange.getRequestBody().readAllBytes());
            Administrador administradorAtualizado = Administrador.fromJson(requestBody);
            administrador.setId(administradorAtualizado.getId());
            administrador.setNome(administradorAtualizado.getNome());
            administrador.setCpf(administradorAtualizado.getCpf());
            administrador.setEndereco(administradorAtualizado.getEndereco());
            administrador.setEmail(administradorAtualizado.getEmail());
            administrador.setSenha(administradorAtualizado.getSenha());
            sendResponse(exchange, administrador.toJson());
        }
    }

    // DELETE
    private void handleDeleteAdministrador(HttpExchange exchange, Integer id) throws IOException {
        Administrador administrador = Biblioteca.getAdministradores().remove(id);
        if (administrador == null) {
            handleNotFound(exchange, "Administrador não encontrado com ID: " + id);
        } else {
            try {
                System.out.println("DELETE /administradores/" + id + ": Administrador deletado com sucesso.");
                sendResponse(exchange, "", 204);
            } catch (IOException e) {
                throw new IOException();
            }
        }
    }

    private void handleNotFound(HttpExchange exchange, String s) throws IOException {
        sendResponse(exchange, s, 404);
    }

    private void handleBadRequest(HttpExchange exchange, String s) {
    }

    private void sendResponse(HttpExchange exchange, String response, int rCode) throws IOException {
        long headerLength = (rCode == 404 || rCode == 400) ? response.length() : response.getBytes().length;
        if (rCode == 204) {
            headerLength = -1L;
        }
        exchange.sendResponseHeaders(rCode, headerLength);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private void sendResponse(HttpExchange exchange, String response) throws IOException {
        sendResponse(exchange, response, 200);
    }
}
