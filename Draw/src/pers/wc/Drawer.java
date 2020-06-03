package pers.wc;

import java.awt.image.BufferedImage;

/**
 * @author Wang
 */
public interface Drawer {
    /**
     * 用数组data绘制一幅图像
     */
    BufferedImage getImage(double[] data, String[] dataMess);
}
