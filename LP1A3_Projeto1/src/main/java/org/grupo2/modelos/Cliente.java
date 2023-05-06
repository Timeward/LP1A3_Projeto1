package org.grupo2.modelos;

public class Cliente extends Usuario {
    private int id;

    public Cliente() {
    }

    public Cliente(int id, String nome, String cpf, String endereco, String email, String senha) {
        super(nome, cpf, endereco, email, senha);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public void reservarLivro(int id, Livro livro, Cliente cliente) throws Exception {
        super.reservarLivro(id, livro, cliente);
    }

    @Override
    public void cancelarReserva(Livro livro, Cliente cliente) throws Exception {
        super.cancelarReserva(livro, cliente);
    }

    public String toJson() {
        return "{\"id\": " + this.getId() + ", \"nome\": \"" + this.getNome() +
                ", \"cpf\": \"" + this.getCpf() + ", \"endereco\": \"" + this.getEndereco() +
                ", \"email\": \"" + this.getEmail() + "\"}";
    }

    public static Cliente fromJson(String requestBody) {
        String requestBodyClean = requestBody.replace("{", "").replace("}","");
        String[] splitProperties = requestBodyClean.split(",");
        int jsonId = Integer.parseInt(splitProperties[0].split(":")[1]);
        String jsonNome = splitProperties[1].split(":")[1];
        String jsonCpf = splitProperties[2].split(":")[1];
        String jsonEndereco = splitProperties[3].split(":")[1];
        String jsonEmail = splitProperties[4].split(":")[1];
        String jsonSenha = splitProperties[5].split(":")[1];
        return new Cliente(jsonId, jsonNome, jsonCpf, jsonEndereco, jsonEmail, jsonSenha);
    }


}
