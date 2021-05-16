import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author XUAN
 * @date 2021/5/16 - 21:21
 * @references
 * @purpose
 * @errors
 */
public class Main {
    public static void main(String[] args) {
        File path = new File(System.getProperty("java.class.path"));
        String title = getFileNameNoEx(path.getName());
        File dir = new File(path.getParent()+File.separator+title);
        dir.mkdir();
        File markdown = new File(dir.getAbsolutePath()+File.separator+"README.md");

        String yaml="---";
        yaml+="\ntitle: "+title;
        yaml+="\ndate: "+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        yaml+="\ntags: ";
        yaml+="\ncategories: ";
        yaml+="\n---";
        yaml+="\n";

        SaveAndReadByUTF8.save(markdown,yaml);
    }

    private static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }
}
