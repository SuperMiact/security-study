package cn.miact.digest;


import java.security.MessageDigest;

/**
 * @author : mawei
 * @Classname : DigestDemo1
 * @createDate : 2022-06-08 17:34:06
 * @Description : 消息摘要算法，为了防止篡改
 *                常见的加密算法：md5、sha1、sha256、sha512
 */
public class DigestDemo1 {
    public static void main(String[] args) throws Exception {
        // 原文
        String input = "aa";
        // 算法
        String algorithm = "MD5";
        System.out.println(getDigest(input, algorithm));
    }

    /**
     * 获取数字摘要
     * @param input 原文
     * @param algorithm 算法
     * @return
     * @throws Exception
     */
    private static String getDigest(String input, String algorithm) throws Exception {
        // 创建消息摘要对象
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        // 执行消息摘要算法
        byte[] digest1 = digest.digest(input.getBytes());

        return toHex(digest1);
    }

    private static String toHex(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        // 对密文进行迭代
        for (byte b : digest) {
            // 把密文转换成16进制
            String s = Integer.toHexString(b & 0xff);
            // 判断如果密文的长度是1，需要在高位进行补0
            if (s.length()==1){
                s = "0"+s;
            }
            sb.append(s);
        }
        return sb.toString();
    }
}
