package com.boz.file.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author boz
 * @date 2019/8/28
 */
@RestController
public class BlobController {

    @CrossOrigin
    @RequestMapping("stream")
    public void stream(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        File file = new File("C:\\Users\\boz\\Desktop\\test\\57f22dd759e162e1ca351013c61ad5ac.mp4");
        String fileName = file.getName();
        String userAgent = req.getHeader("User-Agent").toLowerCase();
        if (userAgent.indexOf("firefox") != -1) {
            resp.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("GB2312"), "ISO-8859-1"));
        }
        else {
            resp.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        }

        //设置response编码
        resp.setCharacterEncoding("UTF-8");
        resp.addHeader("Content-Length", "" + file.length());
        //设置输出文件类型
        resp.setContentType("video/mpeg4");

        FileInputStream fis = null;
        OutputStream os = null;

        try {
            //获取response输出流
            os = resp.getOutputStream();
            fis = new FileInputStream(file);

            int tempbyte;
            while ((tempbyte = fis.read()) != -1){
                System.out.println(tempbyte);
                os.write(tempbyte);
            }

//            byte[] buffer = new byte[1024];
//            int len;
//            while ((len = fis.read(buffer)) != -1) {
//                // 输出文件
//                System.out.println(buffer);
//                os.write(buffer,0,len);
//            }
        } catch (Exception e) {
            if (null != fis) {
                fis.close();
            }

            if (null != os) {
                os.flush();
                os.close();
            }
        }

    }
}
