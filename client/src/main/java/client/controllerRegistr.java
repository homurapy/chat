package client;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javafx.scene.control.Label;
import network.Configure;

public class controllerRegistr {
    @FXML
    TextField loginFieldReg, passwordFieldReg, nicknameFieldReg;

    @FXML
    Label result;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    public void registrationToChat() {
            try {
                Configure configure = Configure.readConfig(Configure.DEFAULT_CONFIG);
                if (socket == null || socket.isClosed()) {
                socket = new Socket(configure.getHost(), configure.getPort());
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
                String str = "/registration " + loginFieldReg.getText() + " " + passwordFieldReg.getText() + " " + nicknameFieldReg.getText();                out.writeUTF(str);
                loginFieldReg.clear();
                passwordFieldReg.clear();
                nicknameFieldReg.clear();
                String answer = in.readUTF();
                result.setText(answer);
                if (answer.equals("Registration complied")) {
                    out.writeUTF("/end");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
   }



