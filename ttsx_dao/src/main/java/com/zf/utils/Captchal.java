package com.zf.utils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Captchal {
    public static void generateCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedImage bi = new BufferedImage(68, 22, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        Color c = new Color(200, 150, 255);
        g.setColor(c);
        g.fillRect(0, 0, 68, 22);

        char[] op = "+-".toCharArray();
        Random r = new Random();
        int index, len1 = op.length;
        int result = 0, firstNum = 0, secondNum = 0;
        char operation = '0';
        String ex="";
        for (int i = 0; i < 4; i++) {// 四次循环，最后生成  【数字1 运算符 数字2 等号】四个部分，如【1 + 2 =】
            if (i != 1) index = r.nextInt(100);
            else index = r.nextInt(len1);

            g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
            if (i == 0) {
                //g.drawString(index+"", (i*15)+3, 18);
                ex+=index+" ";
                firstNum = index;
            }
            else if (i == 2) {
                //g.drawString(index+"", (i*15)+3, 18);
                ex+=index+" ";
                secondNum = index;
            }
            else if (i == 1) {
                //g.drawString(op[index]+"", (i*15)+3, 18);
                ex+=op[index]+" ";
                operation = op[index];
            }
            else {
                //g.drawString("=", (i*15)+3, 18);
                ex+="=";
            }
        }
        // 绘制算术表达式：ex
        g.drawString(ex,3,18);
        // 计算结果
        if (operation == '+') result = firstNum+secondNum;
        else if (operation == '-') result = firstNum-secondNum;
        else if (operation == '*') result = firstNum*secondNum;
        // 结果存入session
        request.getSession().setAttribute("captcha", result);
        // 写出验证码（ 响应请求 ）
        response.setDateHeader("Expires", 0L);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        // 指向客户端的输出流
        ServletOutputStream out = response.getOutputStream();
        // 写出验证码图片，响应
        ImageIO.write(bi, "JPG", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }
}
