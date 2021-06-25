package types;

import java.util.HashMap;

public class ListaServidores {
    private static ListaServidores instance;
    private HashMap<String, Servidor> servidores;

    private ListaServidores() { }

    public static ListaServidores getInstance() {
        if (instance == null) {
            synchronized (ListaServidores.class) {
                if (instance == null) {
                    instance = new ListaServidores();
                }
            }
        }
        return instance;
    }

    public HashMap<String, Servidor> getServidores() {
        return servidores;
    }

    public void setServidores(HashMap<String, Servidor> servidores) {
        this.servidores = servidores;
    }
}
