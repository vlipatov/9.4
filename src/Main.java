import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.nio.file.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("http://lenta.ru/").get();
        Elements elements = doc.select("img");
        String path = "data/images";
        elements.forEach(e -> {
            try {
                downloader(e.absUrl("src"), path);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public static void downloader(String url, String path) throws IOException {
        int index = url.lastIndexOf("/");
        /** проверка чтобы не включались две ссылки - https://mc.yandex.ru/watch/27714477 ,
         *  https://counter.rambler.ru/top100.cnt?pid=80674 */
        if (url.matches(".*(\\.[a-z]{3})$")) {
            try (InputStream in = new URL(url).openStream()) {
                Files.copy(in, Paths.get(path + url.substring(index)), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }
}



