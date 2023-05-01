package sd_project;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*; 

public class Server {
	private static final int PORTA = 20011;
    private static List<Usuario> usuarios = new ArrayList<>();
    
	public static void main(String[] args) throws IOException 
	   { 
		ServerSocket servidor = new ServerSocket(PORTA);
        System.out.println("Servidor iniciado na porta " + PORTA);
        
        while (true) {
            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
            
            Thread t = new Thread(() -> {
                try {
                    BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                    PrintWriter saida = new PrintWriter(cliente.getOutputStream(), true);
                    
                    String requisicao = entrada.readLine();
                    String[] partes = requisicao.split(",");
                    // change afterward to accept json
                    
                    if (partes[0].equals("login")) { // if the operation is 1 or login
                        Usuario usuario = buscarUsuario(partes[1], partes[2]);
                        
                        if (usuario != null) {
                            saida.println("ok");
                        } else {
                            saida.println("usuario nao existe no banco");
                        }
                    } else if (partes[0].equals("cadastro")) { // if the operation is 2
                        Usuario usuario = new Usuario(0, partes[1], partes[2], partes[3]);
                        System.out.printf (usuario.getNome());
                        usuarios.add(usuario);
                        saida.println("ok");
                    }
                    
                    entrada.close();
                    saida.close();
                    cliente.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            
            t.start();
        }
    }
    
    private static Usuario buscarUsuario(String email, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        
        return null;
    }
}
