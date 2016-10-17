package br.com.argonavis.java.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.logging.Logger;

public class CopyFile {
	public static final int EOF = -1;

	public static void main(String[] args) {
		File pastaOrigem = new File( System.getProperty("user.dir"), "/src/main/resources");
		File arquivoOrigem = new File(pastaOrigem, "crocodile.jpg");
		if(arquivoOrigem.exists()) {
			System.out.println("Tamanho: " + arquivoOrigem.length() + " bytes");
			
			File pastaDestino = new File( System.getProperty("user.dir") + "/target/copiados");
			pastaDestino.mkdir();
			
			try {
				System.out.println("Copiando para pasta: " + pastaDestino);
				copiar(arquivoOrigem, pastaDestino);
				
				System.out.println("Conteúdo da pasta: ");
				String[] conteudo = pastaDestino.list();
				for(String nome : conteudo) {
					File arquivo = new File(pastaDestino, nome);
					System.out.println(nome + ", " + arquivo.length()+ " bytes, modificado em " + new Date(arquivo.lastModified()));
				}
				
			} catch (FileNotFoundException e) {
				Logger.getLogger("br.com.argonavis.java.io.CopyFile").info("Arquivo não encontrado!");
				e.printStackTrace();
			} catch (IOException e) {
				Logger.getLogger("br.com.argonavis.java.io.CopyFile").info("Erro de leitura ou gravação!");
				e.printStackTrace();
			}
		} else {
			System.out.println("Arquivo: " + arquivoOrigem + " não existe!");
		}
	}

	private static void copiar(File arquivoOrigem, File pastaDestino) throws IOException {

		File arquivoDestino = new File(pastaDestino, arquivoOrigem.getName());

		if (!arquivoDestino.exists()) {

			FileInputStream in = new FileInputStream(arquivoOrigem);
			FileOutputStream out = new FileOutputStream(arquivoDestino);
			
			byte[] buffer = new byte[8192];
			int lidos = in.read(buffer);
			while(lidos != EOF) {
				out.write(buffer, 0, lidos); // le apenas bytes do array que contem informacoes lidas
				out.flush(); // esvazia o buffer no arquivo
				lidos = in.read(buffer); // lê mais um buffer de bytes
			}
			out.close(); // fecha o fluxo de saída (arquivo liberado)
			in.close();  // fecha fluxo de entrada
		} else {
			System.out.println("Arquivo já existe no destino. Cópia não foi feita.");
		}
	}

}
