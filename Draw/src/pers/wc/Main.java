package pers.wc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Wang
 */
public class Main {
    public static void main(String[] args) {
        Drawer drawer = new DrawPieChart();
        double[] data = {3, 12, 19, 8, 10};
        String[] dataMess = {"不及格", "及格", "中等", "良好", "优秀"};
        BufferedImage imagePie = drawer.getImage(data, dataMess);
        drawer = new DrawBarChart();
        BufferedImage imageBar = drawer.getImage(data, dataMess);
        File filePie = new File("pie.jpg");
        File fileBar = new File("bar.bmp");
        try {
            boolean boo = ImageIO.write(imagePie, "jpeg", filePie);
            boo = ImageIO.write(imageBar, "bmp", fileBar);
            if (boo) {
                System.out.println(filePie.getAbsolutePath() + "");
                System.out.println(fileBar.getAbsolutePath() + "");
                System.out.println("保存成功");
//                Runtime runtime = Runtime.getRuntime();
//                System.out.println(fileBar.getAbsolutePath());
//                runtime.exec("mspaint" + filePie.getAbsolutePath());
//                runtime.exec("mspaint" + fileBar.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
