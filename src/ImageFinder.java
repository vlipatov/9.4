import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageFinder {
    List<String> images;

    public ImageFinder(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select("img");
        images = new ArrayList<>();
        elements.forEach(element -> images.add(element.absUrl("src")));
    }
    public List getImages() {
       return images;
    }

}
