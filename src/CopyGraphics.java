import java.io.File;

/**
 * Used to resize and copy android graphics to other folders
 */
public class CopyGraphics {

    public static void main(String[] args) {
        // args[0] = copy directory
        File copy_directory = new File(args[0]);
        File[] to_copy = copy_directory.listFiles();
        // args[1] = destination directory
        File dest_dir = new File(args[1]);
        // make dir if it does not exist
        if (!dest_dir.exists()) {
            dest_dir.mkdirs();
        } else if (!dest_dir.isDirectory()) {
            // crash if dest exists but isn't a directory
            System.out.println("Error: Destination must be a directory");
            System.exit(0);
        }
        // resize and copy over files from to_copy directory if they are .png
        // and if they do not already exist in dest_dir
        for (File f : to_copy) {
            File dest = new File(dest_dir, f.getName());
            if (f.getPath().substring(f.getPath().lastIndexOf('.')).equals(".png") && !dest.exists()) {
                ImageUtil.resizeImage(f, new File(dest_dir, f.getName()), 50.0 / 130, 50.0 / 130);
            }
        }
    }
}
