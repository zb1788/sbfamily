package com.boz.utils;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.io.IOException;

/**
 * @author boz
 * @date 2019/8/29
 */
public class Ocr {
    public static void main(String[] args) throws IOException {

//        ClassPathResource classPathResource = new ClassPathResource("chi_sim.traineddata");
//        File f = classPathResource.getFile();
//
//        String path = classPathResource.getPath();
//
//        ITesseract instance = new Tesseract();



        File imgFile = new File("C:\\Users\\boz\\Desktop\\test\\bbb.png");

        ITesseract tesseract = new Tesseract();


        tesseract.setDatapath("C:\\Users\\boz\\Desktop\\test");

        tesseract.setLanguage("chi_sim");


        try {
            String s = tesseract.doOCR(imgFile);
            System.out.println(s);
        } catch (TesseractException e) {
            e.printStackTrace();
        }


    }
}
