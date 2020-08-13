package com.boz.file.controller;

import com.boz.config.Global;
import com.boz.config.ServerConfig;
import com.boz.utils.FileUploadUtils;
import com.boz.utils.FileUtils;
import com.boz.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author boz
 * @date 2019/7/10
 */

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private ServerConfig serverConfig;


    @GetMapping("download")
    public void downloadFile(HttpServletResponse response, HttpServletRequest request) throws Exception{
        String path = "C:\\Users\\boz\\Desktop\\tmp\\face.png";
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;fileName=face.png" );
        FileUtils.writeBytes(path, response.getOutputStream());
    }

    @CrossOrigin(origins = {"http://localhost:8888", "null"})
    @PostMapping("/add")
    @ResponseBody
    public JSONResult addSave(@RequestParam("file") MultipartFile file) throws IOException
    {
        // 上传文件路径
        String filePath = Global.getProfile() + "upload1/";
        // 上传并返回新文件名称
        // file.transferTo(new File(filePath));
        String fileName = "";

        fileName = FileUploadUtils.upload(filePath,file);


        System.out.println(fileName);
//        fileInfo.setFilePath(fileName);

        return JSONResult.ok(fileName);

    }



    public void testServerCfg(){
        System.out.println(serverConfig.getUrl());
    }


}
