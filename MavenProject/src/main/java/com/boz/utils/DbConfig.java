package com.boz.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author boz
 * @date 2019/8/30
 */
@Slf4j
public class DbConfig {

    private static Properties DbPropertyTemp = null;

    public static final String DBFILENAME = "db.properties";

    /**
     * 获取当前数据库的配置属性
     * @return
     */
    public static Properties getDbProperty(){
        if(DbPropertyTemp == null){
            DbPropertyTemp = new Properties();
            InputStream is;

            try {
                is = getConfigFile(DBFILENAME);
                DbPropertyTemp.load(is);
                is.close();
                is = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return DbPropertyTemp;
    }


    private static InputStream getConfigFile(String fileName){
        return DbConfig.class.getResourceAsStream("/" + fileName);
    }

    public static void main(String[] args) {

        Properties dbProperty = DbConfig.getDbProperty();
        Object url = dbProperty.get("url");
        System.out.println(url);


    }

}
