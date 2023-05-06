package org.grupo2.interfaces;

import org.grupo2.modelos.Livro;

public interface GerenciamentoDeLivros{
    public void cadastrarLivro(int id, String titulo, String autor, String editora, int anoPublicacao, int numExemplares, int numExemplaresDisponiveis);

    public void atualizarLivro(Livro livro);

    public void removerLivro(Livro livro);

    public void buscarLivro(Livro livro);
}
