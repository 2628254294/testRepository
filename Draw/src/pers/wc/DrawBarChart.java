package pers.wc;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 * 绘制柱状图
 *
 * @author Wang
 */
public class DrawBarChart implements Drawer {
    @Override
    public BufferedImage getImage(double[] data, String[] dataMess) {
        double sum = 0;
        for (double item : data) {
            sum += item;
        }
        double[] copyData = Arrays.copyOf(data, data.length);
        int width = 1000;
        int height = 300;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.cyan);
        graphics.fillRect(0, 0, width, height);
        Graphics2D graphics2D = (Graphics2D) graphics;
        int startX = 10;
        // 矩形的左上角x，y坐标
        double x = startX;
        double y = height - 20;
        // 矩形的宽和高
        double w = width / data.length / 3;
        double h = 0;
        double step = w / 2;
        Rectangle2D rectangle2D = new Rectangle2D.Double(x, y, w, h);
        for (int i = 0; i < data.length; i++) {
            x = x + w + step;
            y = height - data[i] / sum * height;
            h = data[i] / sum * height;
            rectangle2D.setRect(x, y, w, h);
            if (i % 3 == 0) {
                graphics2D.setColor(Color.red);
            } else if (i % 3 == 1) {
                graphics2D.setColor(Color.blue);
            } else if (i % 3 == 2) {
                graphics2D.setColor(Color.green);
            }
            // 填充矩形
            graphics2D.fill(rectangle2D);
            graphics2D.setFont(new Font("宋体", Font.PLAIN, 16));
            graphics2D.setColor(Color.black);
            graphics2D.drawString(dataMess[i] + ":" + copyData[i], (int) x, (int) y);
        }
        Line2D line2D = new Line2D.Double(startX, 0, startX, height);
        graphics2D.draw(line2D);
        graphics2D.drawString("数组代数和：" + sum, (int) startX, 20);
        return image;
    }
}
