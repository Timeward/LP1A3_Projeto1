package org.grupo2.modelos;


public class Administrador extends Usuario { // implements GerenciamentoDeUsuarios, GerenciamentoDeLivros {
    private int id;

    public Administrador(int id, String nome, String cpf, String endereco, String email, String senha) {
        super(nome, cpf, endereco, email, senha);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void cadastrarFuncionario() {

    }

    public void removerFuncionario() {
        //TODO
    }

    public String buscarFuncionario(String nome) {
        //TODO
        return null;
    }

    //    @Override
//    public void emprestarLivro(){
//
//    }
//
//    @Override
//    public void devolverLivro() {
//
//    }
//
//    @Override
//    public void reservarLivro() {
//
//    }
//
//    @Override
//    public void cancelarReserva() {
//
//    }
//
//    @Override
//    public void cadastrarLivro() {
//
//    }
//
//    @Override
//    public void atualizarLivro() {
//
//    }
//
//    @Override
//    public void removerLivro() {
//
//    }
//
//    @Override
//    public void buscarLivro() {
//
//    }
//
//    @Override
//    public void cadastrarUsuario() {
//
//    }
//
//    @Override
//    public void atualizarUsuario() {
//
//    }
//
//    @Override
//    public void removerUsuario() {
//
//    }
//
//    @Override
//    public void buscarUsuario() {
//
//    }

    public String toJson() {
        return "{\"id\": " + this.getId() + ", \"nome\": \"" + this.getNome() +
                "\", \"cpf\": \"" + this.getCpf() + "\", \"endereco\": \"" + this.getEndereco() +
                "\", \"email\": \"" + this.getEmail() + "\"}";
    }

    public static Administrador fromJson(String requestBody) {
        String requestBodyClean = requestBody.replace("{", "").replace("}","");
        String[] splitProperties = requestBodyClean.split(",");
        int jsonId = Integer.parseInt(splitProperties[0].split(":")[1].trim());
        String jsonNome = splitProperties[1].split(":")[1].trim().replace("\"","");
        String jsonCpf = splitProperties[2].split(":")[1].trim().replace("\"","");
        String jsonEndereco = splitProperties[3].split(":")[1].trim().replace("\"","");
        String jsonEmail = splitProperties[4].split(":")[1].trim().replace("\"","");
        String jsonSenha = splitProperties[5].split(":")[1].trim().replace("\"","");
        return new Administrador(jsonId, jsonNome, jsonCpf, jsonEndereco, jsonEmail, jsonSenha);
    }
}
