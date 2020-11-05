import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Cliente extends JFrame implements ActionListener{
    JPanel janela;
    JTextField areaTexto;
    JTextArea areaChat;
    JButton botaoEnviar;

    private DataInputStream receber;
    private DataOutputStream enviar;
    private Socket cliente;

    public Cliente() throws IOException{
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
        this.setTitle("Janela do Cliente");
        botaoEnviar.addActionListener(this);
        areaTexto.addActionListener(this);
        cliente = new Socket("127.0.0.1", 5000);

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
        new Cliente();
    }
}