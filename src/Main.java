import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String url = "https://lenta.ru/";
        ImageFinder finder = new ImageFinder(url);
        ImageDownloader downloader = new ImageDownloader(finder.getImages(),"data/images");
    }

}



