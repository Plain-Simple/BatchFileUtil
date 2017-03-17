import javax.imageio.ImageIO;
import javax.xml.bind.ValidationException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by Stefan on 11/27/2015.
 */
public class ImageUtil {

    // finds all images in the directory and returns them in an array
    public static BufferedImage[] loadImagesInDir(File dir) {
        File[] files = dir.listFiles();
        List<BufferedImage> found_imgs = new LinkedList<>();
        for (File f : files) {
            try {
                found_imgs.add(readImageFromFile(f));
            } catch (ValidationException e) {
                // not an image: do nothing
            }
        }
        return found_imgs.toArray(new BufferedImage[found_imgs.size()]);
    }

    public static BufferedImage readImageFromFile(File file) throws ValidationException {
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            throw new ValidationException(file.getName() + " is not an image.");
        }
    }

    /*// saves all images to the specified directory
    public static boolean saveImagesToDir(BufferedImage[] imgs, File output) {

    }*/


    public static boolean saveImageToFile(BufferedImage img, File output, String format) {
        try {
            return ImageIO.write(img, format, output);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

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

    public static void resizeImage(File toResize, File destination, double factorW, double factorH) {
        BufferedImage original, resized;
        Graphics2D g;
        String extension;
       try {
            original = ImageIO.read(toResize);
            resized = new BufferedImage((int) (original.getWidth() * factorW),
                    (int) (original.getHeight() * factorH), original.getType());
            g = resized.createGraphics();
            g.drawImage(original, 0, 0, resized.getWidth(), resized.getHeight(), null);
            extension = toResize.getPath().substring(toResize.getPath().lastIndexOf(".") + 1);
            ImageIO.write(resized, extension, destination);
        } catch(IOException e) {

        }
    }

    // combines individual images in frames into a single image of a spritesheet.
    // use maxColWidth to specify the maximum number of frames per column. More
    // frames will trigger a new row to form.
    // Spritesheet is generated in the order of images in frames
    public static BufferedImage createSpriteSheet(BufferedImage[] frames, int maxColWidth) throws ValidationException {
        int frame_width = frames[0].getWidth();
        BufferedImage sprite_sheet =
                new BufferedImage(frame_width * frames.length, frames[0].getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D sheet_graphics = sprite_sheet.createGraphics();
        BufferedImage sub_img;
        for (int i = 0; i < frames.length; i++) {
            sub_img = frames[i];
            // make sure sub image has same dimensions as first frame
            if (sub_img.getWidth() != frame_width || sub_img.getHeight() != frames[0].getHeight()) {
                throw new ValidationException("Spritesheet Error: All frames must have the same dimensions");
            }
            sheet_graphics.drawImage(sub_img, i * frame_width, 0, null);
        }
        return sprite_sheet;
    }

}
