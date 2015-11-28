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
                BatchImageUtil.resize(files, factorW, factorH);
        }
    }
}
