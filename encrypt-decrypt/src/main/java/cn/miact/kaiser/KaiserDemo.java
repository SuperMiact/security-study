package cn.miact.kaiser;

/**
 * @author : mw
 * @Classname :KaiserDemo
 * @createDate : 2022-05-24 22:14:47
 * @Description : 凯撒加密
 */
public class KaiserDemo {
    public static void main(String[] args) {
        // 定义原文
        String input = "Hello World";
        // 把原文右边移动3位
        int key = 3;
        String s = encrypt(input,key);
        System.out.println("加密===" + s);
        String s1 = decrypt(s,key);
        System.out.println("解密===" + s1);
    }

    /**
     * 解密
     * @param s 密文
     * @param key 密钥
     * @return 明文
     */
    public static String decrypt(String s, int key) {
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            int b = aChar;
            b -= key;
            char newb = (char) b;
            sb.append(newb);
        }
        return sb.toString();
    }

    /**
     * 加密
     * @param input 原文
     * @return 密文
     */
    public static String encrypt(String input,int key) {
        // 把字符串变为字节数组
        char[] chars = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            int b = aChar;
            // 往右边移动3位
            b += key;
            char newb = (char) b;
            sb.append(newb);
        }
        return sb.toString();
    }
}
