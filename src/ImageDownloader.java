import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class ImageDownloader {
    public ImageDownloader(List<String> images, String path) throws IOException {
        for (String img : images) {
            int index = img.lastIndexOf("/");
            if (!Paths.get(path).toFile().isDirectory()) { // если по пути назначения нет папки, то создаем новую
                Files.createDirectory(Paths.get(path));
            }
            if (img.contains(path.substring(path.indexOf("/")+2))) {
                try (InputStream in = new URL(img).openStream()) {
                    Files.copy(in, Paths.get(path + img.substring(index)), StandardCopyOption.REPLACE_EXISTING);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Все изображения скачаны в " + path);
    }
}
