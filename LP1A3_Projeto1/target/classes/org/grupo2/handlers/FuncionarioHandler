package org.grupo2.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.grupo2.modelos.Biblioteca;
import org.grupo2.modelos.Funcionario;

import java.io.IOException;
import java.io.OutputStream;

public class FuncionarioHandler implements HttpHandler {
    private static Integer proximoId = 2;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String[] pathParts = path.split("/");
        if (pathParts.length == 3 && "funcionarios".equals(pathParts[1])) {
            Integer id = Integer.parseInt(pathParts[2]);
            if ("GET".equals(requestMethod)) {
                handleGetFuncionario(exchange, id);
            } else if ("DELETE".equals(requestMethod)) {
                handleDeleteFuncionario(exchange, id);
            } else if ("PUT".equals(requestMethod)) {
                handlePutFuncionario(exchange, id);
            }
        } else if ("GET".equals(requestMethod)) {
            handleGetFuncionarios(exchange);
        } else if ("POST".equals(requestMethod)) {
            handlePostFuncionario(exchange);
        } else {
            handleBadRequest(exchange, "Requisicao invalida: " + requestMethod + " " + path);
        }
    }

    // CREATE
    private void handlePostFuncionario(HttpExchange exchange) throws IOException {
        String requestBody = new String(exchange.getRequestBody().readAllBytes());
        Funcionario funcionario = Funcionario.fromJson(requestBody);
        funcionario.setId(proximoId);
        proximoId++;
        Biblioteca.getFuncionarios().put(funcionario.getId(), funcionario);
        sendResponse(exchange, funcionario.toJson(), 201);
    }

    // READ ALL
	private void handleGetFuncionarios(HttpExchange exchange) throws IOException {
		StringBuilder response = new StringBuilder();
		response.append("[");
		for (Funcionario funcionario : Biblioteca.getFuncionarios().values()) {
			response.append(funcionario.toJson());
			response.append(",");
		}
		if (Biblioteca.getFuncionarios().size() > 0) {
			response.deleteCharAt(response.length() - 1);
		}
		response.append("]");
		sendResponse(exchange, response.toString());
	}

    // READ
    private void handleGetFuncionario(HttpExchange exchange, Integer id) throws IOException {
        Funcionario funcionario = Biblioteca.getFuncionarios().get(id);
        if (funcionario == null) {
            handleNotFound(exchange, "Funcionario não encontrado com o id " + id);
        } else {
            sendResponse(exchange, funcionario.toJson());
        }
    }

    // UPDATE
    private void handlePutFuncionario(HttpExchange exchange, Integer id) throws IOException {
        Funcionario funcionario = Biblioteca.getFuncionarios().get(id);
        if (funcionario == null) {
            handleNotFound(exchange, "Funcionario não encontrado com ID: " + id);
        } else {
            String requestBody = new String(exchange.getRequestBody().readAllBytes());
            Funcionario funcionarioAtualizado = Funcionario.fromJson(requestBody);
            funcionario.setId(funcionarioAtualizado.getId());
			funcionario.setNome(funcionarioAtualizado.getNome());
			funcionario.setCpf(funcionarioAtualizado.getCpf());
			funcionario.setEndereco(funcionarioAtualizado.getEndereco());
			funcionario.setEmail(funcionarioAtualizado.getEmail());
			funcionario.setSenha(funcionarioAtualizado.getSenha());
            sendResponse(exchange, funcionario.toJson());
        }
    }

	// DELETE
	private void handleDeleteFuncionario(HttpExchange exchange, Integer id) throws IOException {
		Funcionario funcionario = Biblioteca.getFuncionarios().remove(id);
		if (funcionario == null) {
			handleNotFound(exchange, "Funcionario não encontrado com ID: " + id);
		} else {
			try {
				System.out.println("DELETE /funcionarios/" + id + ": Funcionario deletado com sucesso.");
				sendResponse(exchange, "", 204);
			} catch (IOException e) {
				throw new IOException();
			}
		}
	}

    private void handleNotFound(HttpExchange exchange, String s) throws IOException {
        sendResponse(exchange, s, 404);
    }

	private void handleBadRequest(HttpExchange exchange, String s) throws IOException {
		sendResponse(exchange, s, 400);
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
