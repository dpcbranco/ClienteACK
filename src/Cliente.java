import java.io.File;
import java.io.IOException;

import conexao.Conexao;

public class Cliente {

	public static void main(String[] args) {
		try {
			File f = new File("C:\\Users\\Nerds\\Documents\\RPG\\Paelias Liadon - Background.docx");
			Conexao c = new Conexao();
			if (c.enviarArquivo(f)) {
				System.out.println("Arquivo enviado com sucesso");
			}
			
			else {
				System.out.println("Falha no envio do arquivo");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
