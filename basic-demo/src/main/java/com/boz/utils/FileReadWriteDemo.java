package com.boz.utils;

import java.io.*;

/**
 * @author boz
 * @date 2019/8/23
 */
public class FileReadWriteDemo {

    public static void main(String[] args) {
        String fileName = "D:/profile/a.txt";
        readFileByLine(fileName,"GBK");
    }


    /**
     * 按行读取文本内容
     * @param fileName
     */
    public static void readFileByLine(String fileName){
        readFileByLine(fileName,"UTF-8");
    }

    /**
     * 按行读取文本内容
     * @param fileName 文件路径
     * @param charset 编码
     */
    public static void readFileByLine(String fileName,String charset){
        try {
            File file = new File(fileName);
            InputStream is = new FileInputStream(file);
            InputStreamReader read = new InputStreamReader(is,charset);
            BufferedReader reader = null;

            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(read);
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                line++;
                //获取当前行文件内容后的自定义操作
            }
            is.close();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
     */
    public static void readFileByBytes(String fileName){
        File file = new File(fileName);
        InputStream in = null;
        System.out.println("以字节为单位读取文件内容，一次读一个字节：");
        //一次读一个字节
        try {
            in = new FileInputStream(file);
            int tempbyte;
            while ((tempbyte = in.read()) != -1){
                System.out.println(tempbyte);
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("以字节为单位读取文件内容，一次读多个字节：");
        //一次读多个字节
        byte[] tempbytes = new byte[100];
        int byteread = 0;
        try {
            in = new FileInputStream(fileName);
            System.out.println("当前字节输入流中的字节数为:" + in.available());
            // 读入多个字节到字节数组中，byteread为一次读入的字节数
            while ((byteread = in.read(tempbytes)) != -1) {
                System.out.write(tempbytes, 0, byteread);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    /**
     * 以字符为单位读取文件，常用于读文本，数字等类型的文件
     */
    public static void readFileByChars(String fileName){
        File file = new File(fileName);
        Reader reader = null;
        System.out.println("以字符为单位读取文件内容，一次读一个字节：");
        //一次读一个字节
        try {
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1){
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
                if((char) tempchar != '\r'){
                    System.out.println((char) tempchar);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("以字符为单位读取文件内容，一次读多个字节：");
        char[] tempchars = new char[30];
        int charread = 0;
        try {
            reader = new InputStreamReader(new FileInputStream(fileName));
            //读入多个字符到字符数组中，charread为一次读取字符串数
            while ((charread = reader.read(tempchars)) != -1){
                if((charread == tempchars.length) && (tempchars[tempchars.length-1]) != '\r'){
                    System.out.println(tempchars);
                }else{
                    for(int i=0; i< charread; i++){
                        if(tempchars[i] == '\r'){
                            continue;
                        }else{
                            System.out.println(tempchars[i]);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
