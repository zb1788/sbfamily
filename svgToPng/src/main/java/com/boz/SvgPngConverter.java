package com.boz;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author boz
 * @date 2020/6/30
 */
public class SvgPngConverter {


    /**
     * 将svg字符串转换为png
     *
     *  @param svgCode svg代码
     * @param pngFilePath 保存的路径
     * @throws TranscoderException svg代码异常
     * @throws IOException io错误
     */
    public static void convertToPng(String svgCode, String pngFilePath) throws IOException,
            TranscoderException {

        File file = new File(pngFilePath);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            convertToPng(svgCode, outputStream);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 将svgCode转换成png文件，直接输出到流中
     *
     * @param svgCode svg代码
     * @param outputStream 输出流
     * @throws TranscoderException 异常
     * @throws IOException io异常
     */
    public static void convertToPng(String svgCode, OutputStream outputStream)
            throws TranscoderException, IOException {
        try {
            byte[] bytes = svgCode.getBytes("utf-8");
            PNGTranscoder t = new PNGTranscoder();
            TranscoderInput input = new TranscoderInput(new ByteArrayInputStream(bytes));
            TranscoderOutput output = new TranscoderOutput(outputStream);
            t.transcode(input, output);
            outputStream.flush();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        StringBuffer svgCode = new  StringBuffer();
        //svgCode.append("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"473\" height=\"430\" viewBox=\"0 0 1024 1024\"> <g transform=\"scale(1, -1) translate(0,-900)\"><path d=\"M 520 688 Q 758 710 827 705 Q 920 702 927 705 Q 927 706 929 707 Q 936 722 923 735 Q 848 801 781 778 Q 673 757 477 733 Q 173 697 130 695 Q 123 696 116 695 Q 101 695 100 683 Q 99 670 120 654 Q 138 641 172 629 Q 184 625 203 633 Q 219 640 294 653 Q 376 674 478 684 L 520 688 ZM 544 524 Q 544 642 547 654 Q 551 667 541 675 Q 534 682 520 688 C 494 703 470 713 478 684 Q 499 648 485 268 Q 473 39 480 22 Q 486 6 498 -8 Q 511 -24 523 -5 Q 553 53 546 104 Q 540 161 544 492 L 544 524 ZM 544 492 Q 620 446 712 378 Q 733 360 749 359 Q 759 359 765 371 Q 775 387 753 430 Q 723 493 544 524 C 514 529 518 508 544 492 Z\" /></g></svg>");

        List<String> svglist = new ArrayList<>();
        svglist.add("<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 1024 1024\"><g transform=\"scale(1, -1) translate(0,-900)\"><path ng-repeat=\"stoke in pathData\" class=\"ng-scope\" d=\"M 520 688 Q 758 710 827 705 Q 920 702 927 705 Q 927 706 929 707 Q 936 722 923 735 Q 848 801 781 778 Q 673 757 477 733 Q 173 697 130 695 Q 123 696 116 695 Q 101 695 100 683 Q 99 670 120 654 Q 138 641 172 629 Q 184 625 203 633 Q 219 640 294 653 Q 376 674 478 684 L 520 688 Z\"></path></g></svg>");
        svglist.add("<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 1024 1024\"><g transform=\"scale(1, -1) translate(0,-900)\"><path ng-repeat=\"stoke in pathData\" class=\"ng-scope\" d=\"M 520 688 Q 758 710 827 705 Q 920 702 927 705 Q 927 706 929 707 Q 936 722 923 735 Q 848 801 781 778 Q 673 757 477 733 Q 173 697 130 695 Q 123 696 116 695 Q 101 695 100 683 Q 99 670 120 654 Q 138 641 172 629 Q 184 625 203 633 Q 219 640 294 653 Q 376 674 478 684 L 520 688 ZM 544 524 Q 544 642 547 654 Q 551 667 541 675 Q 534 682 520 688 C 494 703 470 713 478 684 Q 499 648 485 268 Q 473 39 480 22 Q 486 6 498 -8 Q 511 -24 523 -5 Q 553 53 546 104 Q 540 161 544 492 L 544 524 Z\"></path></g></svg>");
        svglist.add("<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 1024 1024\"><g transform=\"scale(1, -1) translate(0,-900)\"><path ng-repeat=\"stoke in pathData\" class=\"ng-scope\" d=\"M 520 688 Q 758 710 827 705 Q 920 702 927 705 Q 927 706 929 707 Q 936 722 923 735 Q 848 801 781 778 Q 673 757 477 733 Q 173 697 130 695 Q 123 696 116 695 Q 101 695 100 683 Q 99 670 120 654 Q 138 641 172 629 Q 184 625 203 633 Q 219 640 294 653 Q 376 674 478 684 L 520 688 ZM 544 524 Q 544 642 547 654 Q 551 667 541 675 Q 534 682 520 688 C 494 703 470 713 478 684 Q 499 648 485 268 Q 473 39 480 22 Q 486 6 498 -8 Q 511 -24 523 -5 Q 553 53 546 104 Q 540 161 544 492 L 544 524 ZM 544 492 Q 620 446 712 378 Q 733 360 749 359 Q 759 359 765 371 Q 775 387 753 430 Q 723 493 544 524 C 514 529 518 508 544 492 Z\" ></path></g></svg>");

        svgCode.append("<svg version='1.1' xmlns='http://www.w3.org/2000/svg' viewBox='0 0 1024 1024'><g transform='scale(1, -1) translate(0,-900)'><path ng-repeat='stoke in pathData' class='ng-scope' d='M 520 688 Q 758 710 827 705 Q 920 702 927 705 Q 927 706 929 707 Q 936 722 923 735 Q 848 801 781 778 Q 673 757 477 733 Q 173 697 130 695 Q 123 696 116 695 Q 101 695 100 683 Q 99 670 120 654 Q 138 641 172 629 Q 184 625 203 633 Q 219 640 294 653 Q 376 674 478 684 L 520 688 ZM 544 524 Q 544 642 547 654 Q 551 667 541 675 Q 534 682 520 688 C 494 703 470 713 478 684 Q 499 648 485 268 Q 473 39 480 22 Q 486 6 498 -8 Q 511 -24 523 -5 Q 553 53 546 104 Q 540 161 544 492 L 544 524 ZM 544 492 Q 620 446 712 378 Q 733 360 749 359 Q 759 359 765 371 Q 775 387 753 430 Q 723 493 544 524 C 514 529 518 508 544 492 Z' ></path></g></svg>");

        String pngFilePath = "E:\\3.png";
        try {
            convertToPng(svgCode.toString(), pngFilePath);
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (TranscoderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
