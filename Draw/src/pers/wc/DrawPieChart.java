package pers.wc;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

/**
 * 绘制饼图
 *
 * @author Wang
 */
public class DrawPieChart implements Drawer {

    @Override
    public BufferedImage getImage(double[] data, String[] dataMess) {
        double sum = 0;
        for (double item : data) {
            sum += item;
        }
        int width = 1000;
        int height = 800;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.fillRect(0, 0, width, height);
        Graphics2D graphics2D = (Graphics2D) graphics;
        // 饼弧的开始角度
        double angSt = 0;
        // 饼弧的度数
        double angExt = 360;
        double radius = width / 4;
        int x = 20;
        int y = 20;
        Arc2D arc2D = new Arc2D.Double(x, y, 2 * radius, 2 * radius, angSt, angExt, Arc2D.PIE);
        for (int i = 0; i < data.length; i++) {
            angSt = 0;
            for (int m = 0; m < i; m++) {
                angSt = angSt + (data[m] / sum) * 360;
            }
            angExt = (data[i] / sum) * 360;
            // 设置饼弧的开始角度
            arc2D.setAngleStart(angSt);
            // 设置饼弧的度数
            arc2D.setAngleExtent(angExt);
            graphics2D.setXORMode(Color.white);
            if (i % 5 == 0) {
                graphics2D.setColor(Color.red);
            } else if (i % 5 == 1) {
                graphics2D.setColor(Color.blue);
            } else if (i % 5 == 2) {
                graphics2D.setColor(Color.green);
            } else if (i % 5 == 3) {
                graphics2D.setColor(Color.yellow);
            } else if (i % 5 == 4) {
                graphics2D.setColor(Color.cyan);
            }
            // 绘制饼图
            graphics2D.fill(arc2D);
            angSt = angSt + angExt / 2;
            double left_x = x + radius + radius * Math.cos(angSt * Math.PI / 180);
            double left_y = y + radius - radius * Math.sin(angSt * Math.PI / 180);
            graphics2D.setFont(new Font("宋体", Font.BOLD, 20));
            graphics2D.setColor(Color.black);
            String percent = String.format("%.2f", (data[i] / sum) * 100);
            if (angSt < 90 || angSt > 270) {
                Line2D line2D = new Line2D.Double(left_x, left_y, 2 * radius + x + 5, left_y);
                graphics2D.draw(line2D);
                graphics2D.drawString(dataMess[i] + "" + percent + "%（数据：" + data[i] + "）",
                        (int) (2 * radius + x + 5), (int) left_y);
            } else {
                graphics2D.drawString(dataMess[i] + "" + percent + "%（数据：" + data[i] + "）",
                        (int) left_x, (int) left_y);
            }
        }
        return image;
    }
}
