package cn.miact.kaiser;

/**
 * @author : mw
 * @Classname :KaiserDemo
 * @createDate : 2022-05-24 22:14:47
 * @Description : 凯撒加密
 * @use :
 */
public class KaiserDemo {
    public static void main(String[] args) {
        // 定义原文
        String input = "Hello World";
        // 把原文右边移动3位
        int key = 3;
        // 把字符串变为字节数组
        char[] chars = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            int b = aChar;
            // 往右边移动3位
            b += 3;
            char newb = (char) b;
            sb.append(newb);
        }
        System.out.println("密文==="+sb);
    }
}
