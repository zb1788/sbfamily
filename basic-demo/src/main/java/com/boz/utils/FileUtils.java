package com.boz.utils;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 文件处理工具类
 * 
 * @author ruoyi
 */
@Slf4j
public class FileUtils
{
    public static String FILENAME_PATTERN = "[a-zA-Z0-9_\\-\\|\\.\\u4e00-\\u9fa5]+";

    /**
     * 输出指定文件的byte数组
     * 
     * @param filePath 文件路径
     * @param os 输出流
     * @return
     */
    public static void writeBytes(String filePath, OutputStream os) throws IOException
    {
        FileInputStream fis = null;
        try
        {
            File file = new File(filePath);
            if (!file.exists())
            {
                throw new FileNotFoundException(filePath);
            }
            fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length;
            while ((length = fis.read(b)) > 0)
            {
                os.write(b, 0, length);
            }
        }
        catch (IOException e)
        {
            throw e;
        }
        finally
        {
            if (os != null)
            {
                try
                {
                    os.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
            if (fis != null)
            {
                try
                {
                    fis.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除文件
     * 
     * @param filePath 文件
     * @return
     */
    public static boolean deleteFile(String filePath)
    {
        boolean flag = false;
        File file = new File(filePath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists())
        {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 文件剪切
     * @param from
     * @param to
     * @throws Exception
     */
    public static void moveFile(String from, String to) throws Exception {
        boolean ok = copy(from, to);
        if (ok)
            deleteFile(from);
    }


    /**
     * 文件拷贝（单个）
     * 创建日期：(2002-1-24 9:52:47)
     * @param from java.lang.String
     * @param to java.lang.String
     */
    public static boolean copy(String from, String to) {
        try {
            to = to.replaceAll("\\\\", "/ <file://\\> ");
            String toPath = to.substring(0, to.lastIndexOf("/"));
            File f = new File(toPath);
            if (!f.exists())
                f.mkdirs();
            BufferedInputStream bin = new BufferedInputStream(new
                    FileInputStream(from));
            BufferedOutputStream bout = new BufferedOutputStream(new
                    FileOutputStream(to));
            int c;
            while ( (c = bin.read()) != -1)
                bout.write(c);
            bin.close();
            bout.close();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 文件名称验证
     *
     * @param filename 文件名称
     * @return true 正常 false 非法
     */
    public static boolean isValidFilename(String filename)
    {
        return filename.matches(FILENAME_PATTERN);
    }


    /**
     * 获取图片大小
     * @param path
     * @return
     */
    public static Map<String,Integer> getImageSize(String path){
        log.debug("GET IMG :"+path);
        HashMap<String,Integer> imagemap =  null;
        try {
            //InputStream is = new ByteArrayInputStream(path);
            File imgfile = new File(path);
            if(imgfile.exists()){
                imagemap = new HashMap<String,Integer>();
                BufferedImage bufImg = ImageIO.read(imgfile);
                imagemap.put("width", bufImg.getWidth());
                imagemap.put("height", bufImg.getHeight());
                //bufImg.flush();
                bufImg = null;
            }else{
                log.error(" IMG FILE "+path+" NOT EXISTS!! ");
            }
            imgfile = null;
        } catch (IOException e) {
            log.error("GET IMG WIDTH HEIGHT ERROR:"+e.getMessage(),e);
            e.printStackTrace();
        }
        return imagemap;
    }

    /**
     * CHL
     * 删除path绝对路径文件夹下的所有文件
     * @param path
     * @return
     */
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }



    /**
     * CHL
     * 删除path绝对路径文件夹,及其下文件
     * @param
     * @return
     */
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 创建文件及目录的方法
     * @param path-文件路径
     * @throws Exception
     */
    public static void createFile(String path) throws Exception {
        File dirName = new File(path);
        if (!dirName.exists()) {
            boolean success = dirName.mkdirs(); //建立文件
        }
    }



    /**
     * 获取basePath目录下所有扩展名为extname文件路径地址
     * @param basePath
     * @param extname 扩展名(不带点) 如果为空 则获取所有文件
     * @param showChild 如果为true，则递归查询子文件夹，false则只查当前文件夹
     * @param fileType 1 列出文件夹+文件 2 只列文件夹 3 只列文件
     * @return
     */
    public static String[] listFiles(String basePath, String extname,boolean showChild,Integer fileType) {
        String[] files = null;
        File file = new File(basePath);
        if (!file.exists()) {
            return new String[] {};
        }
        LinkedList l = new LinkedList();

        if(!basePath.endsWith("/")){
            basePath = basePath + "/";
        }
        if(basePath.endsWith("//")){
            basePath = basePath.substring(0,basePath.length()-1);
        }

        basePath = basePath.replaceAll("//","/");

        getFilesList(file, basePath, basePath, extname, l, showChild, fileType);
        files = new String[l.size()];
        for (int i = 0; i < l.size(); i++) {
            files[i] = (String) l.get(i);
        }
        return files;
    }


    /**
     * 递归方法 获取basePath目录下所有扩展名为extname的文件
     * @param file
     * @param basePath
     * @param parentName
     * @param extname 扩展名 例如 xml html等等 如果为空 则返回所有文件
     * @param l 存储的集合
     * @param showChild 是否递归查询
     * @param fileType 1文件夹+文件 2 文件夹 3 文件
     * @return
     */
    private static LinkedList getFilesList(File file, String basePath, String parentName, String extname, LinkedList l, boolean showChild, Integer fileType) {
        StringBuffer fileNodeStr = new StringBuffer();
        try {
            String dir = file.getPath();
            dir = dir.replaceAll("\\\\", "/");

            File TempFile[] = file.listFiles();
            String filepath = null;
            for (int i = 0; i < TempFile.length; i++) {
                File tmpFile = TempFile[i];
                if (tmpFile.isDirectory()) {
                    if(showChild){
                        getFilesList(tmpFile, basePath, tmpFile.getName(), extname, l, showChild, fileType);
                    }else{
                        filepath = tmpFile.getCanonicalPath();
                        filepath = filepath.substring(basePath.length());


                        if(fileType == 1 || fileType == 2){
                            //文件夹也加入
                            l.add(filepath);
                        }else if(fileType == 3){
                            //只列文件，跳过文件夹的名称
                            continue;
                        }
                    }
                }
                else {
                    if(fileType == 1 || fileType == 3){
                        //只列文件夹，跳过文件
                        filepath = tmpFile.getCanonicalPath();
                        filepath = filepath.substring(basePath.length());
                        if (extname == null || extname.trim().equals("")) {
                            l.add(filepath);
                        }
                        else {
                            if (tmpFile.getName().endsWith("." + extname)) {
                                l.add(filepath);
                            }
                        }
                    }else{
                        continue;
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }



    /**
     * 复制整个文件夹内容
     * @param oldPath String 原文件路径 如：c:/fqf
     * @param newPath String 复制后路径 如：f:/fqf/ff
     * @return boolean
     * @throws Exception
     */
    public static void copyFolder(String oldPath, String newPath) throws Exception {
        createFile(newPath); // 如果文件夹不存在，则建立新文件夹
        File a = new File(oldPath);
        String[] file = a.list();
        if (file == null) {
            return;
        }
        File temp = null;
        for (int i = 0; i < file.length; i++) {
            if (oldPath.endsWith(File.separator)) {
                temp = new File(oldPath + file[i]);
            } else {
                temp = new File(oldPath + File.separator + file[i]);
            }

            if (temp.isFile()) {
                FileInputStream input = new FileInputStream(temp);
                FileOutputStream output = new FileOutputStream(newPath + "/"
                        + (temp.getName()).toString());
                byte[] b = new byte[1024 * 5];
                int len;
                while ((len = input.read(b)) != -1) {
                    output.write(b, 0, len);
                }
                output.flush();
                output.close();
                input.close();
            }
            if (temp.isDirectory()) {// 如果是子文件夹
                copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
            }
        }

    }

    /**
     * 文件、文件夹修改名字。
     * @param oldName
     * @param newName
     * @return
     */
    public boolean rename(String oldName, String newName) {
        File oldFile = new File(oldName);
        if (!oldFile.exists()) {
            return false;
        }
        return oldFile.renameTo(new File(newName));
    }

    /**
     * 获取文件名
     * @param filePathName
     * @return
     */
    public static String getFileName(String filePathName){
        File f = new File(filePathName);
        return f.getName();
    }

    /**
     * 获取文件名不包含后缀
     * @param filePathName
     * @return
     */
    public static String getFileNameExcludeFix(String filePathName){
        File f = new File(filePathName);
        String fileName = f.getName();
        return fileName.substring(0,fileName.lastIndexOf(fileName));
    }




    public static void readFile(String filePath,String charSet){

    }

    /**
     * 按行读取文本内容
     * @param fileName 文件路径
     * @param charset 编码
     */
    public static StringBuffer readFileByLine(String fileName,String charset){
        try {
            File file = new File(fileName);
            InputStream is = new FileInputStream(file);
            InputStreamReader read = new InputStreamReader(is,charset);
            BufferedReader reader = null;

            log.debug("以行为单位读取文件内容，一次读一整行：");

            StringBuffer content = new StringBuffer();
            reader = new BufferedReader(read);
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                log.debug("line " + line + ": " + tempString);
                content.append(tempString).append(System.getProperty("line.separator"));
                line++;
                //获取当前行文件内容后的自定义操作
            }
            is.close();
            reader.close();
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void writeFile(String content,String filePath,Boolean append){
        try {
            FileOutputStream fos = new FileOutputStream(filePath,append);
            fos.write(content.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        if(append){
//            try {
//                FileOutputStream fos = new FileOutputStream(filePath,true);
//                fos.write(content.getBytes());
//                fos.close();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }else {
//            FileWriter writer;
//            try {
//                writer = new FileWriter(filePath);
//                writer.write("");
//                writer.flush();
//                writer.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }




    public static void main(String[] args) {
        File f = new File("D://profile\\ebook/123.txt");
        String fileName = f.getName();
        System.out.println(fileName);
        System.out.println(f.getPath());
    }


}
