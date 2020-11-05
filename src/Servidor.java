import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends JFrame implements ActionListener{
    JPanel janela;
    JTextField areaTexto;
    JTextArea areaChat;
    JButton botaoEnviar;

    private DataInputStream receber;
    private DataOutputStream enviar;
    private ServerSocket servidor;
    private Socket cliente;

    public Servidor() throws IOException{
        janela = new JPanel();
        areaTexto = new JTextField();
        areaChat = new JTextArea();
        botaoEnviar = new JButton("ENVIAR");
        this.setSize(500,500);
        this.setVisible(true);
        janela.setLayout(null);
        this.add(janela);
        areaChat.setBounds(20,20,450,360);
        janela.add(areaChat);
        areaTexto.setBounds(20,400,340,30);
        janela.add(areaTexto);
        botaoEnviar.setBounds(375,400,95,30);
        janela.add(botaoEnviar);
        this.setTitle("Janela do Servidor");
        botaoEnviar.addActionListener(this);
        areaTexto.addActionListener(this);
        servidor = new ServerSocket(5000);
        cliente = servidor.accept();
        areaChat.append("Cliente Conectado...");

        while (true){
            receber = new DataInputStream(cliente.getInputStream());
            areaChat.append("\nCLIENTE: " + receber.readUTF());
        }
    }

    public void actionPerformed(ActionEvent acao) {
        try {
           enviar = new DataOutputStream(cliente.getOutputStream());
           enviar.flush();
           enviar.writeUTF(areaTexto.getText());
           areaChat.append("\nVOCE: " + areaTexto.getText());
           areaTexto.setText(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Servidor();
    }
}