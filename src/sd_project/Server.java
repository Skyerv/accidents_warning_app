package sd_project;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*; 

public class Server {
	private ServerSocket servidor;
    private List<Usuario> usuarios;
    
    public Server(int porta) throws IOException {
        servidor = new ServerSocket(porta);
        usuarios = new ArrayList<>();
    }
    
    public void iniciar() throws IOException, ClassNotFoundException {
            System.out.println("Aguardando conexão...");
            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
            
            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
            ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
            
            String operacao = (String) entrada.readObject();
            
            if (operacao.equals("LOGIN")) {
                String email = (String) entrada.readObject();
                String senha = (String) entrada.readObject();
                
                Usuario usuario = null;
                for (Usuario u : usuarios) {
                    if (u.getEmail().equals(email) && u.getSenha().equals(senha)) {
                        usuario = u;
                        break;
                    }
                }
                saida.writeObject(usuario);
                
            } else if (operacao.equals("CADASTRO")) {
                Usuario usuario = (Usuario) entrada.readObject();
                boolean sucesso = cadastrar(usuario);
                saida.writeBoolean(sucesso);
                
            } else if (operacao.equals("SAIR")) {
                entrada.close();
                saida.close();
                cliente.close();
            }
    }
    
    private boolean cadastrar(Usuario usuario) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(usuario.getEmail())) {
                return false;
            }
        }
        usuarios.add(usuario);
        return true;
    }
    
    public void fecharConexao() throws IOException {
        servidor.close();
        System.out.println("Conexão com o servidor encerrada");
    }
}
