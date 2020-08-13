package com.boz.excel;

import com.boz.config.ServerConfig;
import com.boz.utils.ExcelUtil;
import com.boz.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * @author boz
 * @date 2019/8/23
 */
@RequestMapping("exceldemo")
public class ExcelDemoController {

    @Autowired
    private ServerConfig serverConfig;


    @Autowired
    private CustomerMapper customerMapper;

    @GetMapping("export")
    public JSONResult exportExcelDemo(){
        //1.生成集合
        List<Customer> customers = customerMapper.selectList(null);
        //2.初始化util
        ExcelUtil<Customer> util = new ExcelUtil<Customer>(Customer.class);
        //3.调用工具类的导出方法
        util.exportExcel(customers,"用户表");
        return JSONResult.ok();
    }


    @GetMapping("import")
    public JSONResult importExcel(){
        //1.初始化util
        ExcelUtil<Customer> util = new ExcelUtil<Customer>(Customer.class);

        //2.获取文件流
        InputStream is = null;
        try {
            String url = "D:/profile/d0a17b5d-a1b2-49aa-912c-c971b9e8d687_用户表.xlsx";
            is = new FileInputStream(url);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        //导入(实体类必须有无参的构造方法)
        try {
            List<Customer> customers = util.importExcel(is);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return JSONResult.ok();
    }



}
