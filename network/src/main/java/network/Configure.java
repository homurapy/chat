package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Configure {
    private Integer port;
    private String host;
    public static final String DEFAULT_CONFIG = "Config.txt";

    public Configure() { }

    public int getPort() {
        return port; }

    public String getHost() {
        return host;
    }

    public static Configure readConfig(String config) throws IOException {
        Configure connect = new Configure();
        InputStream is = Configure.class.getClassLoader().getResourceAsStream(config);
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
        BufferedReader file = new BufferedReader(isr);
        String line;
        while ((line = file.readLine()) != null) {
            String[] parameter = line.split(" - ");
            if (parameter[0].equals("port") && parameter[1] != null && connect.port == null) {
                connect.port = Integer.valueOf(parameter[1]);
            }
            if (parameter[0].equals("host") && parameter[1] != null && connect.host == null) {
                connect.host = parameter[1];
            }
        }
        file.close(); isr.close(); is.close();
        if (connect.port != null && connect.host != null) {
            return connect;
        } else {
            return null;
        }
    }
}
