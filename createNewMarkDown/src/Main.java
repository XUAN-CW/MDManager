

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author XUAN
 * @date 2021/4/13 - 15:17
 * @references
 * @purpose
 * @errors
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("java.class.path");
        File jar = new File(path);
        //创建 Article 对象
        Article article = new Article();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d=new Date();
        String f = sd.format(d);
        article.setDate(f);
        List<String> stringList= new ArrayList<>();
        for (File tempFile = jar.getParentFile(); null!=tempFile&& !tempFile.getName().equals("core"); tempFile=tempFile.getParentFile()){
            stringList.add(tempFile.getName());
        }
        article.setTitle(jar.getName().substring(0,jar.getName().length()-".jar".length()));
        article.setCategories(stringList);
        System.out.println(article);


        //写入文件
        File MDdir = new File(jar.getParent()+File.separator+article.title);
        MDdir.mkdir();
        String a = "---\n"+article.toYAML()+"---";
        File md = new File(MDdir.getAbsolutePath()+File.separator+"README.md");
        SaveAndRead.save(md.getAbsolutePath(),a);

        java.awt.Desktop.getDesktop().open(md);
        jar.deleteOnExit();
        //Windows 命令
//        String command = "explorer /e,/select, " + "\"" +md.getAbsolutePath()+ "\"";
//        SaveAndRead.save("command"+new Date().getTime(),command);
//        File commandInWindows = new File("commandInWindows"+new Date().getTime()+".bat");

//        System.out.println(command);
//        Runtime.getRuntime().exec(command);
//        jar.deleteOnExit();
        //File commandInWindows = new File("commandInWindows"+new Date().getTime()+".bat");
//        File commandInWindows2 = new File("commandInWindows2"+new Date().getTime()+".bat");
//        String command="\nrundll32 url.dll,FileProtocolHandler " + md.getAbsolutePath();
////        command += "\ntimeout /t 10 ";//打开刚才创建好的文件
//        command += "\ndel " + "\"" +jar.getPath()+ "\"";//删除 jar
//        command += "\ndel "+ "\"" + commandInWindows.getName()+ "\""; //删除 commandInWindows
//        command += "\ndel "+ "\"" + commandInWindows2.getName()+ "\""; //删除 commandInWindows
//
//        command += "\ndel " + "\"" +jar.getPath()+ "\"";//删除 jar
//        SaveAndRead.save(commandInWindows.getName(),command);
//        SaveAndRead.save(commandInWindows2.getAbsolutePath(),commandInWindows.getName());
//        Runtime.getRuntime().exec(commandInWindows2.getName());
    }
}
