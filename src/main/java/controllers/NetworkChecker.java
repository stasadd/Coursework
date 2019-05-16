package controllers;

import java.net.URL;
import java.net.URLConnection;

public abstract class NetworkChecker {

    public static boolean isNetAvailable() {
        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
