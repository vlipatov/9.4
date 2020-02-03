import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("http://lenta.ru/").get();
        Elements elements = doc.select("img");

        elements.forEach(e -> {
            try {
                downloader(e.absUrl("src"), "/data/pics");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }


    public static void downloader(String url, String path) throws IOException {
        URL realURL = new URL(url);
        BufferedImage img = ImageIO.read(realURL);
        Path destDir = Paths.get(path);
        if (!destDir.toFile().isDirectory()) { // если по пути назначения нет папки, то создаем новую
            Files.createDirectory(destDir);
        }
        ImageIO.write(img, "jpg", (OutputStream) destDir);
    }
}



