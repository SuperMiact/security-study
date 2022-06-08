package cn.miact;

import java.util.Base64;

/**
 * @author : mawei
 * @Classname :TestBase64
 * @createDate : 2022-06-08 14:58:24
 * @Description : 当字节不够时，需要使用 = 进行补齐
 */
public class TestBase64 {
    public static void main(String[] args) {
        // 一个字节，两个等号
        System.out.println(Base64.getEncoder().encodeToString("1".getBytes()));
        // 两个字节，一个等号
        System.out.println(Base64.getEncoder().encodeToString("12".getBytes()));
        // 三个字节，就不需要补了
        System.out.println(Base64.getEncoder().encodeToString("123".getBytes()));
        // 一个中文三个字节，刚好被整除，所以就没有等号
        System.out.println(Base64.getEncoder().encodeToString("啊".getBytes()));
    }
}
