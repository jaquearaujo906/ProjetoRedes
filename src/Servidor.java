import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class Servidor extends JFrame implements ActionListener{
    JPanel janela;
    JTextArea areaChat;
    JTextField areaTexto;
    JButton botaoEnviar;

    private final Socket cliente;

    public Servidor() throws IOException{
        janela = new JPanel();
        areaTexto = new JTextField();
        areaChat = new JTextArea();
        botaoEnviar = new JButton("ENVIAR");
        this.setSize(400,340);
        this.setVisible(true);
        janela.setLayout(null);
        this.add(janela);
        areaChat.setBounds(20,20,340,180);
        janela.add(areaChat);
        areaTexto.setBounds(20,220,340,30);
        janela.add(areaTexto);
        botaoEnviar.setBounds(145,260,95,30);
        janela.add(botaoEnviar);
        this.setTitle("Janela do Servidor");
        botaoEnviar.addActionListener(this);
        areaTexto.addActionListener(this);

        ServerSocket servidor = new ServerSocket(5000);
        cliente = servidor.accept();
        areaChat.append("Cliente Conectado com Sucesso!");

        while (true){
            DataInputStream receber = new DataInputStream(cliente.getInputStream());
            areaChat.append("\nCLIENTE: " + receber.readUTF());
        }
    }

    public void actionPerformed(ActionEvent acao) {
        try {
           DataOutputStream enviar = new DataOutputStream(cliente.getOutputStream());
           enviar.writeUTF(areaTexto.getText());
           areaChat.append("\nVOCÊ: " + areaTexto.getText());
           areaTexto.setText(" ");
        } catch (IOException erro) {
            erro.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Servidor();
    }
}
