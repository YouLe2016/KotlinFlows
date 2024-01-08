package com.wyl.util;

import net.ifok.png.compress.PngCompressor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

public class ImageCompress {

    public static void compressPic(String sourceImagePath, String compressedImagePath) {
        File sourceImageFile = new File(sourceImagePath);
        File compressedImageFile = new File(compressedImagePath);
        compressPic(sourceImageFile, compressedImageFile, 0.6f);
    }

    public static void compressPic(File sourceImageFile, File compressedImageFile) {
        compressPic(sourceImageFile, compressedImageFile, 0.6f);
    }

    /**
     * 图片压缩
     *
     * @param sourceImagePath
     * @param compressedImagePath
     * @param compressionQuality  jpg压缩质量，范围为0.0到1.0，1.0表示无损压缩，推荐0.6-0.8
     */
    public static void compressPic(String sourceImagePath, String compressedImagePath, float compressionQuality) {
        File sourceImageFile = new File(sourceImagePath);
        File compressedImageFile = new File(compressedImagePath);
        compressPic(sourceImageFile, compressedImageFile, compressionQuality);
    }

    /**
     * 图片压缩
     *
     * @param sourceImageFile
     * @param compressedImageFile
     * @param compressionQuality  jpg压缩质量，范围为0.0到1.0，1.0表示无损压缩，推荐0.6-0.8
     */
    public static void compressPic(File sourceImageFile, File compressedImageFile, float compressionQuality) {
        try {
            BufferedImage sourceImage = ImageIO.read(sourceImageFile);
            ImageOutputStream imageOutputStream = new FileImageOutputStream(compressedImageFile);
            String formatName = getFileType(compressedImageFile);
            if (!"png".equals(formatName) && !"jpg".equals(formatName) && !"jpeg".equals(formatName)) {
                System.out.println("图片格式不正确！");
                return;
            }
            if ("png".equals(formatName)) {
                PngCompressor.compress(sourceImageFile, compressedImageFile);
            } else {
                ImageWriter imageWriter = ImageIO.getImageWritersByFormatName("jpg").next();
                imageWriter.setOutput(imageOutputStream);

                ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
                imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                imageWriteParam.setCompressionQuality(compressionQuality);

                imageWriter.write(null, new IIOImage(sourceImage, null, null), imageWriteParam);

                imageOutputStream.close();
                imageWriter.dispose();
            }
            System.out.println("sourceImageFile: " + sourceImageFile.getAbsolutePath());
            System.out.println("compressedImageFile: " + compressedImageFile.getAbsolutePath());
            System.out.println("图片压缩成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取File的文件类型
     *
     * @param file
     * @return 如：jpg、png、gif等
     */
    private static String getFileType(File file) {
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

}