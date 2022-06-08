package cn.miact.desaes;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author : mw
 * @Classname :DesDemo
 * @createDate : 2022-05-26 22:43:27
 * @Description : 对称加密
 */
public class DesDemo {
    public static void main(String[] args) throws Exception {
        // 原文  如果使用的不填充模式，那么原文必须是8个字节的整数倍
        String input = "啊这11";
        // 定义key，如果使用des加密，密钥必须是8个字节
        String key = "12345678";
        // 算法
//        String transformation = "DES";
//        String transformation = "DES/ECB/PKCS5Padding";
//        String transformation = "DES/CBC/PKCS5Padding";
        String transformation = "DES/CBC/NoPadding";
        // 加密类型
        String algorithm = "DES";
        String encode = encryptDes(input, key, transformation, algorithm);
        System.out.println("加密:"+encode);
        String decrypt = decryptDes(encode,key,transformation,algorithm);
        System.out.println("解密:"+decrypt);
    }

    /**
     * DES解密数据
     */
    private static String decryptDes(String encode, String key, String transformation, String algorithm) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(transformation);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        IvParameterSpec iv = new IvParameterSpec("12345678".getBytes());
        cipher.init(Cipher.DECRYPT_MODE,secretKeySpec,iv);
        byte[] bytes = cipher.doFinal(Base64.decode(encode));
        return new String(bytes);
    }

    /**
     * DES加密数据
     */
    private static String encryptDes(String input, String key, String transformation, String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        // 创建加密对象
        Cipher cipher = Cipher.getInstance(transformation);
        // 创建加密规则
        // 第一个参数：表示key的字节
        // 第二个参数：表示加密的类型
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        // 创建iv向量，iv向量，是使用CBC加密模式才会用到
        IvParameterSpec iv = new IvParameterSpec("12345678".getBytes());
        // 进行加密初始化
        // 第一个参数表示模式，加密模式，解密模式
        // 第二个参数表示：加密的规则
        cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec,iv);
        // 调用加密方法
        // 参数表示原文的字节数组
        byte[] bytes = cipher.doFinal(input.getBytes());

//        for (byte aByte : bytes) {
//            System.out.println(aByte);
//        }
//        // 打印密文
//        // 如果直接打印密文会出现乱码，在ascii表上找不到对应的值就会出现乱码，需要通过base64转码查看密文
//        System.out.println(new String(bytes));

        //  创建Base64对象
        String encode = Base64.encode(bytes);
        return encode;
    }
}
