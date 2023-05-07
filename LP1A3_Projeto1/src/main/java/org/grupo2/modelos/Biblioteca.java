package org.grupo2.modelos;

import com.sun.net.httpserver.HttpServer;
import org.grupo2.consumidores.ConsumidorAdministrador;
import org.grupo2.consumidores.ConsumidorCliente;
import org.grupo2.consumidores.ConsumidorFuncionario;
import org.grupo2.consumidores.ConsumidorLivro;
import org.grupo2.handlers.AdministradorHandler;
import org.grupo2.handlers.ClienteHandler;
import org.grupo2.handlers.FuncionarioHandler;
import org.grupo2.handlers.LivroHandler;

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
    private static Map<Integer, Funcionario> funcionarios = new HashMap<Integer, Funcionario>();
    private static Map<Integer, Administrador> administradores = new HashMap<Integer, Administrador>();
    private static Map<Integer, Emprestimo> emprestimos = new HashMap<Integer, Emprestimo>();
    private static Map<Integer, Reserva> reservas = new HashMap<Integer, Reserva>();

    private static final int PORT = 8080;

    public static void startServer() throws IOException, InterruptedException {
        // clientes
        clientes.put(1, new Cliente(1,"Nome Cliente 1", "100.000.000-01", "Endereco Cliente 1", "cliente1@email.com", "senha_cliente1"));
        funcionarios.put(1, new Funcionario(1,"Nome Funcionario 1", "200.000.000-01", "Endereco Funcionario 1", "funcionario1@email.com", "senha_funcionario1"));
        administradores.put(1, new Administrador(1,"Nome Administrador 1", "300.000.000-01", "Endereco Administrador 1", "administrador1@email.com", "senha_administrador1"));
        livros.put(1,  new Livro(1,"Harry Potter e a Pedra Filosofal","J.K Rowling","Rocco",1997,12,12));
        livros.put(2,  new Livro(2,"Harry Potter e a Camara Secreta","J.K Rowling","Rocco",1998,10,10));
        livros.put(3,  new Livro(3,"Harry Potter e o Prisioneiro de Azkaban","J.K Rowling","Rocco",1999,13,9));

        // start server
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/clientes", new ClienteHandler());
        server.createContext("/funcionarios", new FuncionarioHandler());
        server.createContext("/administradores", new AdministradorHandler());
        server.createContext("/livros", new LivroHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("\nServidor iniciado na porta " + PORT + ".");

        // Chamando Consumidores
        System.out.println("\n*** ACTIONS DO MODELO Cliente ***");
        ConsumidorCliente.main(null);
        System.out.println("\n*** ACTIONS DO MODELO Funcionario ***");
        ConsumidorFuncionario.main(null);
        System.out.println("\n*** ACTIONS DO MODELO Funcionario ***");
        ConsumidorAdministrador.main(null);
        System.out.println("\n*** ACTIONS DO MODELO Administrador ***");
        ConsumidorLivro.main(null);

        // stop server
        server.stop(0);
        System.out.println("\nServidor na porta " + PORT + " foi  finalizado.");
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

    public static Map<Integer, Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public static Map<Integer, Administrador> getAdministradores() {
        return administradores;
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

    public static void salvarEmprestimo(Emprestimo emprestimo) {
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
