package com.boz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author boz
 * @date 2020/7/29
 */
public class JoinMoreImage {

    /**
     * @param fileUrl
     *            文件绝对路径或相对路径
     * @return 读取到的缓存图像
     * @throws IOException
     *             路径错误或者不存在该文件时抛出IO异常
     */
    public static BufferedImage getBufferedImage(String fileUrl) throws IOException {
        File f = new File(fileUrl);
        return ImageIO.read(f);
    }

    /**
     * @param savedImg
     *            待保存的图像
     * @param saveDir
     *            保存的目录
     * @param fileName
     *            保存的文件名，必须带后缀，比如 "beauty.jpg"
     * @param format
     *            文件格式：jpg、png或者bmp
     * @return
     */
    public static boolean saveImage(BufferedImage savedImg, String saveDir, String fileName, String format) {
        boolean flag = false;

        // 先检查保存的图片格式是否正确
        String[] legalFormats = { "jpg", "JPG", "png", "PNG", "bmp", "BMP" };
        int i = 0;
        for (i = 0; i < legalFormats.length; i++) {
            if (format.equals(legalFormats[i])) {
                break;
            }
        }
        if (i == legalFormats.length) { // 图片格式不支持
            System.out.println("不是保存所支持的图片格式!");
            return false;
        }

        // 再检查文件后缀和保存的格式是否一致
        String postfix = fileName.substring(fileName.lastIndexOf('.') + 1);
        if (!postfix.equalsIgnoreCase(format)) {
            System.out.println("待保存文件后缀和保存的格式不一致!");
            return false;
        }

        String fileUrl = saveDir + fileName;
        File file = new File(fileUrl);
        try {
            flag = ImageIO.write(savedImg, format, file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return flag;
    }

    /**
     * 待合并的两张图必须满足这样的前提，如果水平方向合并，则高度必须相等；如果是垂直方向合并，宽度必须相等。
     * mergeImage方法不做判断，自己判断。
     *
     * @param img1
     *            待合并的第一张图
     * @param img2
     *            带合并的第二张图
     * @param isHorizontal
     *            为true时表示水平方向合并，为false时表示垂直方向合并
     * @return 返回合并后的BufferedImage对象
     * @throws IOException
     */
    public static BufferedImage mergeImage(boolean isHorizontal, BufferedImage img1, BufferedImage img2)
            throws IOException {
        int w1 = img1.getWidth();
        int h1 = img1.getHeight();
        int w2 = img2.getWidth();
        int h2 = img2.getHeight();

        // 从图片中读取RGB
        int[] ImageArrayOne = new int[w1 * h1];
        ImageArrayOne = img1.getRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // 逐行扫描图像中各个像素的RGB到数组中
        int[] ImageArrayTwo = new int[w2 * h2];
        ImageArrayTwo = img2.getRGB(0, 0, w2, h2, ImageArrayTwo, 0, w2);

        // 生成新图片
        BufferedImage DestImage = null;
        if (isHorizontal) { // 水平方向合并
            DestImage = new BufferedImage(w1 + w2, h1, BufferedImage.TYPE_INT_RGB);
            DestImage.setRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // 设置上半部分或左半部分的RGB
            DestImage.setRGB(w1, 0, w2, h2, ImageArrayTwo, 0, w2);
        } else { // 垂直方向合并
            DestImage = new BufferedImage(w1, h1 + h2, BufferedImage.TYPE_INT_RGB);
            DestImage.setRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // 设置上半部分或左半部分的RGB
            DestImage.setRGB(0, h1, w2, h2, ImageArrayTwo, 0, w2); // 设置下半部分的RGB
        }

        return DestImage;
    }

    /**合并任数量的图片成一张图片
     * @param isHorizontal true代表水平合并，fasle代表垂直合并
     * @param imgs 欲合并的图片数组
     * @return
     * @throws IOException
     */
    public static BufferedImage mergeImage(boolean isHorizontal, BufferedImage... imgs) throws IOException {
        // 生成新图片
        BufferedImage DestImage = null;

        // 计算新图片的长和高
        int allw = 0, allh = 0, allwMax = 0, allhMax = 0;
        for (BufferedImage img : imgs) {
            allw += img.getWidth();
            allh += img.getHeight();
            if (img.getWidth() > allwMax) {
                allwMax = img.getWidth();
            }
            ;
            if (img.getHeight() > allhMax) {
                allhMax = img.getHeight();
            }
            ;
        }
        // 创建新图片
        if (isHorizontal) {
            DestImage = new BufferedImage(allw, allhMax, BufferedImage.TYPE_INT_RGB);
        } else {
            DestImage = new BufferedImage(allwMax, allh, BufferedImage.TYPE_INT_RGB);
        }

        // 合并所有子图片到新图片
        int wx = 0, wy = 0;
        for (int i = 0; i < imgs.length; i++) {
            BufferedImage img = imgs[i];
            int w1 = img.getWidth();
            int h1 = img.getHeight();
            // 从图片中读取RGB
            int[] ImageArrayOne = new int[w1 * h1];
            ImageArrayOne = img.getRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // 逐行扫描图像中各个像素的RGB到数组中
            if (isHorizontal) { // 水平方向合并
                DestImage.setRGB(wx, 0, w1, h1, ImageArrayOne, 0, w1); // 设置上半部分或左半部分的RGB
            } else { // 垂直方向合并
                DestImage.setRGB(0, wy, w1, h1, ImageArrayOne, 0, w1); // 设置上半部分或左半部分的RGB
            }
            wx += w1;
            wy += h1;
        }
        return DestImage;
    }

    //测试类:注意图片的长宽高不一致可能会报错
    public static void main(String[] args) {

        // 读取待合并的文件
        BufferedImage bi1 = null;
        BufferedImage bi2 = null;
        BufferedImage bi3 = null;
        // 调用mergeImage方法获得合并后的图像
        BufferedImage destImg = null;

        try {
            bi1 = getBufferedImage("E:/1.png");
            bi2 = getBufferedImage("E:/2.png");
            bi3 = getBufferedImage("E:/3.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 调用mergeImage方法获得合并后的图像
        try {
            destImg = mergeImage(true, bi1, bi2, bi3);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 保存图像
        saveImage(destImg, "E:/", "allh.png", "png");
        System.out.println("水平合并完毕!");
    }


}
