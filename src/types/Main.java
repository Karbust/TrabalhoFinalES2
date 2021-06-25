package types;

import Exceptions.IncorrectFileFormatException;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Main {
    public static void main(String[] args) throws MalformedURLException, IncorrectFileFormatException {
        URL url = new URL("https://ebooks.karbust.me/Technology/AW.Framework.Design.Guidelines.2nd.Edition.pdf");

        System.out.println(getFileSize(url));

        if(!FilenameUtils.getExtension(url.getPath()).equals("pdf") && !FilenameUtils.getExtension(url.getPath()).equals("epub")) {
            throw new IncorrectFileFormatException();
        }
    }

    private static int getFileSize(URL url) {
        URLConnection conn = null;
        try {
            conn = url.openConnection();
            if(conn instanceof HttpURLConnection) {
                ((HttpURLConnection)conn).setRequestMethod("HEAD");
            }
            conn.getInputStream();
            return conn.getContentLength();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(conn instanceof HttpURLConnection) {
                ((HttpURLConnection)conn).disconnect();
            }
        }
    }
}
