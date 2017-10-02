package conexao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Conexao {
	Socket servidor;

	public Conexao() throws IOException {
		servidor = new Socket(InetAddress.getLocalHost(), 1234);
	}


	public boolean enviarArquivo(File f) {
		
		boolean teste = false;

		if (f != null && servidor.isConnected()) {
			try {
				FileInputStream leitura = new FileInputStream(f);
				OutputStream envio_arq = servidor.getOutputStream();
				String resposta;
				DataInputStream ack_nak = new DataInputStream(servidor.getInputStream());
				DataOutputStream envio_info = new DataOutputStream(servidor.getOutputStream());
				
				byte [] arq_bt = new byte [(int)f.length()];
				
				leitura.read(arq_bt, 0, arq_bt.length);
				
				envio_info.writeInt(arq_bt.length);
				System.out.println("Enviando arquivo...");
				
				//envio.write(arq_bt,0,arq_bt.length);
				for (int i = 0; i < arq_bt.length; i++) {
					envio_arq.write(arq_bt[i]);
					envio_arq.flush();
					
					resposta = ack_nak.readUTF();			
					System.out.println(resposta);
					
					while (!resposta.equals("ACK")) {
						envio_arq.write(arq_bt[i]);
						System.out.println(resposta);
						envio_arq.flush();
						resposta = ack_nak.readUTF();
					}
					
				}
				
				leitura.close();				
				envio_arq.close();
				ack_nak.close();
				
				servidor.close();
				
				teste = true;
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else {
			System.out.println("Arquivo não inicializado");
		}
		
		return teste;
	}

}
