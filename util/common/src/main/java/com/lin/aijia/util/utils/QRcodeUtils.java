package com.lin.aijia.util.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

/**
 * 二维码生成工具
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public class QRcodeUtils {

    /**
     * CHARSET
     */
    private static final String CHARSET = "utf-8";
    /**
     * FORMAT_NAME
     */
    private static final String FORMAT_NAME = "JPG";
    /**
     * 二维码尺寸
     */
    private static final int QRCODE_SIZE = 300;
    /**
     * LOGO宽度
     */
    private static final int WIDTH = 60;
    /**
     * LOGO高度
     */
    private static final int HEIGHT = 60;


    /**
     * 生成带logo二维码.
     *
     * @param content       the 内容
     * @param logoImagePath the logo图片路径
     * @param path          the 保存路径
     * @return the file
     * @throws IOException     the io exception
     * @throws WriterException the writer exception
     * @author : yangjunqing / 2019-02-21
     */
    public static File generate(String content, String logoImagePath, String path) throws Exception {
        return generate(content, logoImagePath, path, true);
    }

    /**
     * 生成二维码.
     *
     * @param content the 内容
     * @param path    the 保存路径
     * @return the file
     * @throws IOException     the io exception
     * @throws WriterException the writer exception
     * @author : yangjunqing / 2019-02-21
     */
    public static File generate(String content, String path) throws Exception {
        return generate(content, null, path, true);
    }

    /**
     * 生成带logo二维码.
     *
     * @param content       the 内容
     * @param logoImagePath the logo图片路径
     * @param path          the 保存路径
     * @param compress      the 是否需要压缩logo图片
     * @return the file
     * @throws IOException     the io exception
     * @throws WriterException the writer exception
     * @author : yangjunqing / 2019-02-21
     */
    public static File generate(String content, String logoImagePath, String path, boolean compress) throws Exception {
        BufferedImage image = createImage(content, logoImagePath, compress);
        mkdirs(path);
        String fileName = ToolUtils.getRandomName() + "." + FORMAT_NAME.toLowerCase();
        File imgFile = new File(path + "/" + fileName);
        ImageIO.write(image, FORMAT_NAME, imgFile);
        return imgFile;
    }

    /**
     * 创建目录.
     *
     * @param destPath the dest path
     * @author : yangjunqing / 2019-02-21
     */
    private static void mkdirs(String destPath){
        File file = new File(destPath);
        if (!file.exists() && !file.isDirectory()){
            file.mkdirs();
        }
    }

    /**
     * 生成二维码.
     *
     * @param content  the content
     * @param imgPath  the img path
     * @param compress the compress
     * @return the buffered image
     * @throws WriterException the writer exception
     * @throws IOException     the io exception
     * @author : yangjunqing / 2019-02-21
     */
    private static BufferedImage createImage(String content, String imgPath, boolean compress) throws WriterException, IOException {
        Hashtable hits = new Hashtable();
        hits.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hits.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hits.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE,
                QRCODE_SIZE, hits);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (StringUtils.isEmpty(imgPath)){
            return image;
        }

        //插入图片
        insertImage(image, imgPath, compress);
        return image;
    }


    /**
     * 在生成的二维码中插入图片.
     *
     * @param source   the source
     * @param imgPath  the img path
     * @param compress the compress
     * @throws IOException the io exception
     * @author : yangjunqing / 2019-02-21
     */
    private static void insertImage(BufferedImage source, String imgPath, boolean compress) throws IOException {
//        //Jar包下只能以流的方式访问文件
//        File file = new File(imgPath);
//        if (!file.exists()){
//            return;
//        }
//        Image src = ImageIO.read(new File(imgPath));

        Resource resource = new ClassPathResource("static" + imgPath);
        Image src = ImageIO.read(resource.getInputStream());

        int width = src.getWidth(null);
        int height = src.getHeight(null);
        //压缩
        if (compress){
            if (width > WIDTH){
                width = WIDTH;
            }
            if (height > HEIGHT){
                height = HEIGHT;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics graphics = tag.getGraphics();
            graphics.drawImage(image, 0, 0, null);
            graphics.dispose();
            src = image;
        }
        //插入LOGO
        Graphics2D graphics2D = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graphics2D.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graphics2D.setStroke(new BasicStroke(3f));
        graphics2D.draw(shape);
        graphics2D.dispose();
    }
}
