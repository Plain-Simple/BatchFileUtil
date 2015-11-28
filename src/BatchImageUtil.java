import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Stefan on 11/27/2015.
 */
public class BatchImageUtil {

    // attempts to read in each file as bufferedimage and scale by factorW in x
    // and factorH in y
    public static void resize(File[] images, double factorW, double factorH) {
        BufferedImage original, resized;
        Graphics2D g;
        String extension;
        for(File i : images) {
            try {
                original = ImageIO.read(i);
                resized = new BufferedImage((int) (original.getWidth() * factorW),
                        (int) (original.getHeight() * factorH), original.getType());
                g = resized.createGraphics();
                g.drawImage(original, 0, 0, resized.getWidth(), resized.getHeight(), null);
                extension = i.getPath().substring(i.getPath().lastIndexOf(".") + 1);
                ImageIO.write(resized, extension, i);
            } catch(IOException e) {

            }
        }
    }

}
