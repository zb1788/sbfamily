package com.boz.cur.zitie;

import com.boz.utils.DaoUtil;
import com.boz.utils.FileUtils;
import com.boz.utils.GsonTools;
import com.boz.utils.HttpUtils;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

/**
 * 字帖采集
 * https://study.51titi.net/print/index.html?uid=&key=&code_version=undefined&ios_code_version=undefined&from=&fujia=undefined
 * @author boz
 * @date 2020/6/29
 */
public class Zitie {


    public static void getZitie(List<Map<String, String>> multiData,DaoUtil dao){

        for (Map map:multiData){
            System.out.println(map.get("zi"));
            String sql = "select count(1) from zitie where zi='"+map.get("zi")+"'";
            Integer countData = dao.getCountData(sql);
            if(countData == 0){
                postZi((String) map.get("zi"),dao);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void postZi(String zi,DaoUtil dao){
        String postUrl = "https://app.beisu100.com/szhtml/shengzi/PostZitie";
        String param = "zi="+zi;
        String content = HttpUtils.sendPost(postUrl, param, "utf-8");
        //System.out.println("content = " + content);
		JsonObject o = GsonTools.StringToJson(content);

		try{
            String bishun = o.getAsJsonArray("data").get(0).getAsJsonObject().get("bishun").getAsString();
            String bushou = o.getAsJsonArray("data").get(0).getAsJsonObject().get("bushou").getAsString();
            String data = o.getAsJsonArray("data").get(0).getAsJsonObject().get("data").getAsString();
            String id = o.getAsJsonArray("data").get(0).getAsJsonObject().get("id").getAsString();
            String pinyin = o.getAsJsonArray("data").get(0).getAsJsonObject().get("pinyin").getAsString();
            String zongbihua = o.getAsJsonArray("data").get(0).getAsJsonObject().get("zongbihua").getAsString();

            String sql = "insert into zitie(bid,zi,bushou,pinyin,zongbihua,bishun,data) values ('"+id+"','"+zi+"','"+bushou+"','"+pinyin+"','"+zongbihua+"','"+bishun+"','"+data+"') ";
            System.out.println(sql);
            dao.ExecSQL(sql);
        }catch (Exception e){
            String path = "D://profile\\zitie/error.txt";
            FileUtils.writeFile(zi,path,true);
        }
    }


    public static void main(String[] args) {
        DaoUtil dao=new DaoUtil();
        String sql = "select * from yw_zi";
        List<Map<String, String>> multiData = dao.getMultiData(sql);
        System.out.println("multiData = " + multiData);
        getZitie(multiData,dao);
    }
}
