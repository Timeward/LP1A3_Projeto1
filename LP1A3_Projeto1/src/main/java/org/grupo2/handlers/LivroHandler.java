package org.grupo2.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.grupo2.modelos.Biblioteca;
import org.grupo2.modelos.Livro;

import java.io.IOException;
import java.io.OutputStream;

public class LivroHandler implements HttpHandler {
    private static Integer proximoId = 4;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String[] pathParts = path.split("/");
        if (pathParts.length == 3 && "livros".equals(pathParts[1])) {
            Integer id = Integer.parseInt(pathParts[2]);
            if ("GET".equals(requestMethod)) {
                handleGetLivro(exchange, id);
            } else if ("DELETE".equals(requestMethod)) {
                handleDeleteLivro(exchange, id);
            } else if ("PUT".equals(requestMethod)) {
                handlePutLivro(exchange, id);
            }
        } else if ("GET".equals(requestMethod)) {
            handleGetLivros(exchange);
        } else if ("POST".equals(requestMethod)) {
            handlePostLivro(exchange);
        } else {
            handleBadRequest(exchange, "Requisicao invalida: " + requestMethod + " " + path);
        }
    }

    // CREATE
    private void handlePostLivro(HttpExchange exchange) throws IOException {
        String requestBody = new String(exchange.getRequestBody().readAllBytes());
        Livro livro = Livro.fromJson(requestBody);
        livro.setId(proximoId++);
        Biblioteca.getLivros().put(livro.getId(), livro);
        sendResponse(exchange, livro.toJson(), 201);
    }

    // READ ALL
    private void handleGetLivros(HttpExchange exchange) throws IOException {
        StringBuilder response = new StringBuilder();
        response.append("[");
        for (Livro livro : Biblioteca.getLivros().values()) {
            response.append(livro.toJson());
            response.append(",");
        }
        if (Biblioteca.getLivros().size() > 0) {
            response.deleteCharAt(response.length() - 1);
        }
        response.append("]");
        sendResponse(exchange, response.toString());
    }

    // READ
    private void handleGetLivro(HttpExchange exchange, Integer id) throws IOException {
        Livro livro = Biblioteca.getLivros().get(id);
        if (livro == null) {
            handleNotFound(exchange, "Livro não encontrado com o id " + id);
        } else {
            sendResponse(exchange, livro.toJson());
        }
    }

	// UPDATE
	private void handlePutLivro(HttpExchange exchange, Integer id) throws IOException {
		Livro livro = Biblioteca.getLivros().get(id);
		if (livro == null) {
			handleNotFound(exchange, "Livro não encontrado com ID: " + id);
		} else {
			String requestBody = new String(exchange.getRequestBody().readAllBytes());
			Livro livroAtualizado = Livro.fromJson(requestBody);
			livro.setId(livroAtualizado.getId());
			livro.setTitulo(livroAtualizado.getTitulo());
			livro.setAutor(livroAtualizado.getAutor());
			livro.setEditora(livroAtualizado.getEditora());
			livro.setAnoPublicacao(livroAtualizado.getAnoPublicacao());
			livro.setNumExemplares(livroAtualizado.getNumExemplares());
			livro.setNumExemplaresDisponiveis(livroAtualizado.getNumExemplaresDisponiveis());
			sendResponse(exchange, livro.toJson());
		}
	}

	// DELETE
	private void handleDeleteLivro(HttpExchange exchange, Integer id) throws IOException {
		Livro livro = Biblioteca.getLivros().remove(id);
		if (livro == null) {
			handleNotFound(exchange, "Livro não encontrado com ID: " + id);
		} else {
			try {
				System.out.println("DELETE /livros/" + id + ": Livro deletado com sucesso.");
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