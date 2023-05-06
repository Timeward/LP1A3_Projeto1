package org.grupo2;

import org.grupo2.modelos.Biblioteca;
import org.grupo2.modelos.Livro;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Livro Hp = new Livro(45,"Harry Potter e a Pedra Filosofal","J.K Rowling","Rocco",1997,12,12);
        Livro Hp2 = new Livro(46,"Harry Potter e a Camara Secreta","J.K Rowling","Rocco",1998,10,10);
        Livro Hp3 = new Livro(47,"Harry Potter e o Prisioneiro de Azkaban","J.K Rowling","Rocco",1999,13,9);
        System.out.println(Hp);
        System.out.println(Hp2);
        System.out.println(Hp3);
        Biblioteca.startServer();
    }
}