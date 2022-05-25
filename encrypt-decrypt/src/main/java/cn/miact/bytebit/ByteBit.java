package cn.miact.bytebit;

/**
 * @author : mw
 * @Classname :ByteBit
 * @createDate : 2022-05-25 23:09:58
 * @Description :
 */
public class ByteBit {
    public static void main(String[] args) {
        String a = "a";
        byte[] bytes = a.getBytes();
        for (byte aByte : bytes) {
            int c = aByte;
            System.out.println(c);
            // byte 字节，对应的 bit 是多少
            String s = Integer.toBinaryString(c);
            System.out.println(c);
        }
    }
}
