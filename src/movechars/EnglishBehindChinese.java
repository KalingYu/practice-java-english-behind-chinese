package movechars;

import java.io.*;

/**
 * Created by kaling on 16-8-3.
 */
public class EnglishBehindChinese {

    public static void main(String[] args) {
        String path =System.getProperty("user.dir");
        String srcPath = path + "/src.txt";
        String targetPath = path + "/target.txt";
        System.out.println("source path is :" + srcPath);
        System.out.println("targetPath is : " + targetPath);
        File srcFile = new File(srcPath);
        File targetFile = new File(targetPath);

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            if (!srcFile.exists()){
                srcFile.createNewFile();
            }
            if (!targetFile.exists()){
                targetFile.createNewFile();
            }

            reader = new BufferedReader(new FileReader(srcFile));
            writer = new BufferedWriter(new FileWriter(targetFile, true));
            String tempString = null;
            int line = 1;

            while ((tempString = reader.readLine()) != null) {
                System.out.println("开始转换第 " + line + " 行");

                //转换的主要逻辑
                StringBuilder engSB = new StringBuilder();
                StringBuilder chiSB = new StringBuilder();
                for (int i = 0; i < tempString.length(); i++) {
                    char tempChar = tempString.charAt(i);
                    if ((tempChar >= 0x4E00 && tempChar <= 0x9FA5) || (tempChar >= 0xFF00 && tempChar <= 0xFFEF)
                            || (tempChar >= 0x42E80 && tempChar <= 0x2EFF) || (tempChar >= 0x3000 && tempChar <= 0x303F)
                            || (tempChar >= 0x31C0 && tempChar <= 0x31EF)) {
                        chiSB.append(tempChar);
                    } else {
                        engSB.append(tempChar);
                    }
                }
                chiSB.append(" ").append(engSB).append("\n");
                writer.write(chiSB.toString());
                System.out.println("转换结果是：" + chiSB.toString());
                line++;
            }
            writer.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
