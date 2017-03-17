import javax.xml.bind.ValidationException;
import java.io.File;

/**
 * Created by Stefan on 11/27/2015.
 */
public class Main {

    public static void main(String[] args) {
        File directory = new File(args[0]);
        File[] files = directory.listFiles();
        switch(args[1]) {
            case "help":
            case "Help":
            case "?":
                System.out.println(
                        "Available Commands:\n" +
                                "Resize [width] [height]\n" +
                                "\tResizes all image files by factor [width] in x and [height] in y"
                );
                break;
            case "resize":
            case "Resize":
                System.out.println("Resizing Files...");
                double factorW = Double.parseDouble(args[2]);
                double factorH = Double.parseDouble(args[3]);
                ImageUtil.resize(files, factorW, factorH);
                break;
            case "spritesheet": // name of spritesheet is args[2] (with file extension)
                File output = new File(args[2]);
                // grab file extension
                String format = output.getPath().substring(output.getPath().lastIndexOf(".") + 1);
                try {
                    ImageUtil.saveImageToFile(
                        ImageUtil.createSpriteSheet(
                                ImageUtil.loadImagesInDir(directory), 10
                        ), output, format);
                } catch (ValidationException e) {
                    System.out.println(e.getMessage());
                }
        }
    }
}
