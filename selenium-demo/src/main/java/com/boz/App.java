package com.boz;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {
        System.out.println( "Hello World!" );
        System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver_win32\\chromedriver.exe");// chromedriver服务地址
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.zhibo8.cc/nba/index_old.htm");

        String title = driver.getTitle();
        System.out.println(title);
        Thread.sleep(2000);

        WebElement left = driver.findElement(By.id("left"));
        List<WebElement> boxs = left.findElements(By.className("box"));
        int i = 0;
        for(WebElement box : boxs){
            i++;
            String infoTitle = box.findElement(By.className("titlebar")).findElement(By.tagName("h2")).getText();
            System.out.println(infoTitle);

            String text = box.findElement(By.className("content")).findElement(By.tagName("span")).getText();
            System.out.println(text);
            String attribute = box.findElement(By.className("content")).findElement(By.tagName("span")).getAttribute("innerHTML");
            System.out.println(attribute);
            System.out.println("-----------------------");

            String pattern = "\\|(.*?)录像";
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(attribute);
            if(m.find()){
                System.out.println(m.group(0));
                m.groupCount();
            }
            System.out.println("=============================");
            if(i>2){
                break;
            }
        }

        driver.close();

    }
}
