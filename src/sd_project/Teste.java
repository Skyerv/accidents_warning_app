package sd_project;

import java.io.IOException;

public class Teste {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		String localhost = new String ("127.0.0.1");
	    final int port = 20032;
	    
		Server servidor = new Server(port);
        Client cliente = new Client(localhost, port);

        // register new user
        Usuario usuario = new Usuario(0, "Fulano", "fulano@teste.com", "123456");
        boolean cadastroSucesso = cliente.fazerCadastro(usuario);
        System.out.println("Cadastro do usuário " + usuario.getNome() + ": " + (cadastroSucesso ? "sucesso" : "falhou"));

        // login user
        Usuario usuarioLogado = cliente.fazerLogin("fulano@teste.com", "123456");

        // login user that doesn't exist
        usuarioLogado = cliente.fazerLogin("ciclano@teste.com", "654321");

        cliente.fecharConexao();
        servidor.fecharConexao();
	}
}
