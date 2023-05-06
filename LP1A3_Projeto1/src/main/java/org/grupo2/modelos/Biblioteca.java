package org.grupo2.modelos;

import com.sun.net.httpserver.HttpServer;
import org.grupo2.handlers.ClienteHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.*;

public class Biblioteca {
    private final static Integer idLivros = 0;
    private final static Integer idUsuarios = 0;
    private final static Integer idEmprestimos = 0;
    private final static Integer idReservas = 0;
    private static Map<Integer, Livro> livros = new HashMap<Integer, Livro>();
    private static Map<Integer, Cliente> clientes = new HashMap<Integer, Cliente>();
    private static Map<Integer, Emprestimo> emprestimos = new HashMap<Integer, Emprestimo>();
    private static Map<Integer, Reserva> reservas = new HashMap<Integer, Reserva>();

    private static final int PORT = 8080;

    public static void startServer() throws IOException {
        clientes.put(1, new Cliente(1,"Nome Cliente 1", "123.123.123-0", "Endereco 1", "cliente1@email.com", "senha_cliente1"));
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/clientes", new ClienteHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Servidor iniciado na porta " + PORT);
    }

    public static Map<Integer, Livro> getLivros() {
        return livros;
    }

    public static Map<Integer, Cliente> getClientes() {
        return clientes;
    }

    public static Map<Integer, Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public static Map<Integer, Reserva> getReservas() {
        return reservas;
    }

    public Livro retornaLivro(String nome) {
        // TODO: 24/04/2023
        return null;
    }

    public void listarLivros() {
        // TODO: 24/04/2023
    }

    public Usuario buscarUsuarioNome(String nome) {
        // TODO: 24/04/2023
        return null;
    }

    public Usuario buscarUsuarioCpf(String cpf) {
        // TODO: 24/04/2023
        return null;
    }

    public void listarUsuarios() {
        // TODO: 24/04/2023
    }

    public void listarClientes() {
        // TODO: 24/04/2023
    }

    public void listarFuncionarios() {
        // TODO: 24/04/2023
    }

    public void listarEmprestimos() {
        // TODO: 24/04/2023
    }

    public void listarEmprestimosCliente(Cliente cliente) {
        // TODO: 24/04/2023
    }

    public void listarEmprestimosData(Date data) {
        // TODO: 24/04/2023
    }

    public void login(String cpf, String senha) {
        // TODO: 24/04/2023
    }

    public static boolean existeEmprestimoPorId(int id) {
        return Objects.nonNull(emprestimos.get(id));
    }

    public static void salvaEmprestimo(Emprestimo emprestimo) {
        emprestimos.put(Biblioteca.idEmprestimos + 1, emprestimo);
    }

    public static void salvarReserva(Reserva reserva) {
        reservas.put(Biblioteca.idReservas + 1, reserva);
    }

    public static Optional<Reserva> procurarReservaPorLivroECliente(Livro livro, Cliente cliente) {
        Optional<Reserva> reservaOptional = Optional.empty();
        for (int i = 1; i < reservas.size() + 1; i++) {
            if (reservas.get(i).getLivro().equals(livro) && reservas.get(i).getCliente().equals(cliente)) {
                reservaOptional = Optional.of(reservas.get(i));
                break;
            }
        }
        return reservaOptional;
    }

    public static Optional<Reserva> procuraReservaPorId(int id) {
        return Optional.of(reservas.get(id));
    }

    public static void cancelarReserva(Reserva reserva) {
        reservas.remove(reserva.getId());
    }
}
