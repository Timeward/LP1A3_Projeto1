import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Biblioteca {
    private List<Livro> livros;
    private List<Usuario> usuarios;
    private List<Emprestimo> emprestimos;
    
    public Livro buscarLivro(){
        //Inicio da função para encontrar o livro pelo pelo t[itulo, que não estava presente antes
        Scanner scanner = new Scanner(System.in);
        System.out.print("Está buscando por qual livro?");
        String livroBuscado = scanner.nextLine();
        return null;
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
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do usuário");
        String usuarioBuscado = scanner.nextLine();
        return null;
    }

    public Usuario buscarUsuarioCpf(String cpf) {
        // TODO: 24/04/2023
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o CPF do usuário");
        String cpfBuscado = scanner.nextLine();
    }

    public void listarUsuarios() {
        // TODO: 24/04/2023
        //Inicio da função listar usuarios usando a funçao forEach
        usuarios.forEach(Usuario -> System.out.println(Usuario));
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
}
