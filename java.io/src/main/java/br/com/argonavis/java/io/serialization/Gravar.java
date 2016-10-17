package br.com.argonavis.java.io.serialization;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Gravar {

    public static void main(String[] args) throws IOException {
        System.out.println("Gravando!");
        
        String userDir = System.getProperty("user.dir");
        
        File pasta = new File(userDir,"target/arquivos");
        pasta.mkdir();
        
        File pessoa = new File(pasta, "Pessoa_1.prod");
        FileOutputStream fos = new FileOutputStream(pessoa);
        ObjectOutputStream out = new ObjectOutputStream(fos);
        
        Pessoa p = new Pessoa("Rayssa Yavanova", 22);
        Endereco e = new Endereco("Ulitsa Tverskaya", 123);
        p.setEndereco(e);
        
        Teatro.reservar(p.getNome());
        Ingresso i = Teatro.getReserva(p.getNome());
        p.setIngresso(i);
        
        System.out.println("Objeto a ser gravado: " + p);
        
        out.writeObject(p);
        out.close();

    }
}
