import java.io.*;

public class SaveAndReadByUTF8 {

	public static void save(File file,String content){
		try{
			if(file.exists()){
				file.createNewFile();
				System.out.println("文件已创建");
			}
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			osw.write(content);
			osw.flush();
			osw.close();
		}catch (IOException ioException){
			ioException.printStackTrace();
		}
	}

	public static String read(File file) {
		String content="";
		try{
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				content += line;
				content += "\n"; // 补上换行符
			}
		}catch (IOException ioException){
			ioException.printStackTrace();
		}
		return content;
	}



	public static String read(String file) {
		return read(new File(file));
	}

	public static void save(String file,String content){
		save(new File(file),content);
	}
}
