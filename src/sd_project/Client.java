package sd_project;

import java.io.*;
import java.net.*;

public class Client {
	private Socket socket;
    private BufferedReader entrada;
    private PrintWriter saida;
    
    public Client(String enderecoServidor, int portaServidor) throws IOException {
        socket = new Socket(enderecoServidor, portaServidor);
        saida = new PrintWriter(socket.getOutputStream(), true);
        entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    
    public Usuario fazerLogin(String email, String senha) throws IOException, ClassNotFoundException {
//        saida.writeObject("LOGIN");
//        saida.writeObject(email);
//        saida.writeObject(senha);
        Usuario usuario = new Usuario(0, "aaa", "aaa@aa.com", "1211212");
//        return usuario;
    	System.out.println("loginnn");
    	return usuario;
    }
    
    public boolean fazerCadastro(Usuario usuario) throws IOException, ClassNotFoundException {
//        saida.writeObject("CADASTRO");
//        saida.writeObject(usuario);
//        boolean sucesso = entrada.readBoolean();
//        return sucesso;
    	System.out.println("cadastrooo");
    	return true;
    }
    
    public void fecharConexao() throws IOException {
        saida.write("SAIR");
        entrada.close();
        saida.close();
        socket.close();
    }
}