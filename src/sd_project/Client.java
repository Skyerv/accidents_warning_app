package sd_project;

import java.io.*;
import java.net.*;

public class Client {
	private static String HOST = new String ("127.0.0.1");
    private static final int PORT = 20011;
    
	public static void main(String[] args) throws IOException {
        if (args.length > 0)
        	HOST = args[0];
        System.out.println ("Attemping to connect to host " +
        		HOST + " on port 10007.");

        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            echoSocket = new Socket(HOST, PORT);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                                        echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + HOST);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                               + "the connection to: " + HOST);
            System.exit(1);
        }

	BufferedReader stdIn = new BufferedReader(
                                   new InputStreamReader(System.in));

	String userInput;
    System.out.print ("input: ");
    while ((userInput = stdIn.readLine()) != null) {
    out.println(userInput);
        System.out.print ("input: ");
    }
	
	String resposta = in.readLine();
    
    if (resposta.equals("ok")) {
        System.out.println("Login realizado com sucesso!");
    } else {
        System.out.println("Erro ao fazer login.");
    }

	out.close();
	in.close();
	stdIn.close();
	echoSocket.close();
    }
}
