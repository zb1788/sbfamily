package com.boz;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

import java.io.*;

/**
 * Hello world!
 *
 */
public class App 
{


    public static void convertSvg2Png(File svg, File png) throws IOException, TranscoderException
    {

        InputStream in = new FileInputStream(svg);
        OutputStream out = new FileOutputStream(png);
        out = new BufferedOutputStream(out);

        Transcoder transcoder = new PNGTranscoder();
        try {
            TranscoderInput input = new TranscoderInput(in);
            try {
                TranscoderOutput output = new TranscoderOutput(out);
                transcoder.transcode(input, output);
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }
    public static void main( String[] args )
    {

        File f=new File("C:\\Users\\boz\\Desktop\\1111\\2.svg");
        File destFile=new File("E:\\2.png");

        try {
            convertSvg2Png(f, destFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TranscoderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
