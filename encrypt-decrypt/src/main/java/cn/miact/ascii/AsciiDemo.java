package cn.miact.ascii;

/**
 * @author : mw
 * @Classname :AsciiDemo
 * @createDate : 2022-05-24 22:11:45
 * @Description :
 * @use :
 */
public class AsciiDemo {
    public static void main(String[] args) {
//        char a = 'a';
//        int b = a;
        // 定义字符串
        String a = "AaZ";
        char[] chars = a.toCharArray();
        for (char aChar : chars) {
            int assciiCode = aChar;
            System.out.println(assciiCode);
        }
    }
}
