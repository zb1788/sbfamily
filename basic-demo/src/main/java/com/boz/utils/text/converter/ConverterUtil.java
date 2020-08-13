package com.boz.utils.text.converter;

import java.io.*;
import java.util.*;

/**
 * 批量文件转换gbk转为utf8
 * @author boz
 * @date 2019/11/13
 */
public class ConverterUtil {
    private static String lineSepator;
    private static final String BLACKLIST = "jpg,jpeg,gif,png,bmp,rar,zip,swf,svg,jar";//黑名单，不进行转换的文件(图片，压缩包)

    static {
        String osName = System.getProperty("os.name");
        if (osName.contains("Windows")) {
            lineSepator = "\r\n";
        } else {
            lineSepator = "\n";
        }
    }

    public static Map<String, String> parseArgs(String[] args) {
        Map<String, String> argsMap = new HashMap<String, String>();
        for (int i = 0; i < args.length; i++) {
            String key = args[i];
            if (++i >= args.length) {
                argsMap.put(key, "");
            } else {
                argsMap.put(key, args[i]);
            }
        }
        return argsMap;
    }

    public static void writeString(File file, String content, String encoding) throws Exception {
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), encoding);
        osw.write(content);
        osw.close();
    }

    public static String readString(File file, String encoding) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
        StringBuilder buff = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            if (buff.length() > 0) {
                buff.append(lineSepator);
            }
            buff.append(line);
        }
        br.close();
        return buff.toString();
    }

    public static List<File> listFiles(File dir, String ext, int maxDepth) {
        List<File> files = new ArrayList<File>();
        seekFiles(dir, ext, maxDepth, files);
        return files;
    }

    private static void seekFiles(File dir, String ext, int maxDepth, List<File> files) {
        if (maxDepth-- <= 0) {
            return;
        }
        files.addAll(Arrays.asList(dir.listFiles(f -> {
            if(f.isFile()){
                if("*".equals(ext)){
                    String suffix = f.getName().substring(f.getName().lastIndexOf(".") + 1);
                    if(BLACKLIST.indexOf(suffix)!= -1){
                        return false;
                    }else{
                        return true;
                    }
                }else{
                    return f.getName().toLowerCase().endsWith("." + ext);
                }
            }else{
                return false;
            }
        })));
        for (File _dir : dir.listFiles(f -> f.isDirectory())) {
            seekFiles(_dir, ext, maxDepth, files);
        }
    }
}

