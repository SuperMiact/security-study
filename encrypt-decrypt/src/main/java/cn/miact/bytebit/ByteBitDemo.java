package cn.miact.bytebit;

import java.io.UnsupportedEncodingException;

/**
 * @author : mw
 * @Classname :ByteBitDemo
 * @createDate : 2022-05-26 22:05:22
 * @Description :
 */
public class ByteBitDemo {
    /**
     * 根据编码的格式不一样，对应的字节也不一样
     * 如果是UTF-8：一个中文对应的是3个字节
     * 如果是GBK，一个中文对应2个字节
     *
     * 如果是英文，就无所谓编码格式
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
//        String a = "六";
        String a = "s";
        byte[] bytes = a.getBytes("GBK");
        for (byte aByte : bytes) {
            System.out.println(aByte);
            String s = Integer.toBinaryString(aByte);
            System.out.println(s);
        }
    }
}
