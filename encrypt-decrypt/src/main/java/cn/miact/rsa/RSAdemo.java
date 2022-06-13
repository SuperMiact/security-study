package cn.miact.rsa;


import org.apache.commons.io.FileUtils;

import javax.crypto.Cipher;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author : mawei
 * @Classname : RSAdemo
 * @createDate : 2022-06-13 09:34:53
 * @Description :
 */
public class RSAdemo {
    public static void main(String[] args) throws Exception {
        // 原文
        String input = "啊这";
        // 创建密钥对
        String algorithm = "RSA";

        // 生成密钥对并保存在本地文件中
        generateKeyToFile(algorithm,"a.pub","a.pri");

        // 获取私钥key
        PrivateKey privateKey = getPrivateKey("a.pri", algorithm);
        // 获取公钥key
        PublicKey publicKey = getPublicKey("a.pub", algorithm);


        String encryptData = encryptRSA(algorithm, privateKey, input);
        System.out.println(encryptData);

        String decryptData = decryptRSA(algorithm, publicKey, encryptData);
        System.out.println(decryptData);

    }

    /**
     * 读取公钥
     * @param pubKeyPath 公钥的路径
     * @param algorithm  算法
     * @return
     */
    private static PublicKey getPublicKey(String pubKeyPath, String algorithm) throws Exception{
        String publicKeyString = FileUtils.readFileToString(new File(pubKeyPath), StandardCharsets.UTF_8);
        // 创建key的工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        // 创建公钥规则
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyString));
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 读取私钥
     * @param priKeyPath 私钥的路径
     * @param algorithm  算法
     * @return
     */
    private static PrivateKey getPrivateKey(String priKeyPath, String algorithm) throws Exception{
        String privateKeyString = FileUtils.readFileToString(new File("a.pri"), StandardCharsets.UTF_8);
        // 创建key的工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        // 创建私钥key的规则
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyString));
        // 返回私钥对象
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * RSA加密
     * @param algorithm 算法
     * @param priKey    密钥
     * @param input     原文
     * @return
     * @throws Exception
     */
    public static String encryptRSA(String algorithm, Key priKey,String input) throws Exception{
        // 创建加密对象
        Cipher cipher = Cipher.getInstance(algorithm);
        // 对加密进行初始化;
        // 第一个参数：加密的模式
        // 第二个参数：你想使用公钥加密还是私钥加密
        // 我想使用私钥进行加密
        cipher.init(Cipher.ENCRYPT_MODE,priKey);
        // 使用私钥进行加密
        byte[] bytes = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * RSA解密
     * @param algorithm 算法
     * @param pubKey    密钥
     * @param encode    密文
     * @return
     * @throws Exception
     */
    public static String decryptRSA(String algorithm,Key pubKey,String encode)throws Exception{
        // 创建解密对象
        Cipher cipher = Cipher.getInstance(algorithm);
        // 公钥解密
        cipher.init(Cipher.DECRYPT_MODE,pubKey);
        byte[] decode = Base64.getDecoder().decode(encode);
        byte[] bytes = cipher.doFinal(decode);
        return new String(bytes,0,bytes.length);
    }

    /**
     * 保存公钥和私钥，把公钥和私钥保存到根目录
     * @param algorithm 算法
     * @param pubFilePath 公钥路径
     * @param priFilePath 私钥路径
     */
    private static void generateKeyToFile(String algorithm, String pubFilePath, String priFilePath) throws Exception{
        KeyPairGenerator rsa = KeyPairGenerator.getInstance(algorithm);
        // 生成密钥对
        KeyPair keyPair = rsa.generateKeyPair();
        // 生成私钥
        PrivateKey privateKey = keyPair.getPrivate();
        // 生成公钥
        PublicKey publicKey = keyPair.getPublic();
        // 获取私钥的字节数组
        byte[] privateKeyEncoded = privateKey.getEncoded();
        // 获取公钥的字节数组
        byte[] publicKeyEncoded = publicKey.getEncoded();
        // 使用base64进行编码
        String privateKeyEncodedString = Base64.getEncoder().encodeToString(privateKeyEncoded);
        String publicKeyEncodedString = Base64.getEncoder().encodeToString((publicKeyEncoded));

        // 把文件保存到根目录
        FileUtils.writeStringToFile(new File(pubFilePath),publicKeyEncodedString, StandardCharsets.UTF_8);
        FileUtils.writeStringToFile(new File(priFilePath),privateKeyEncodedString, StandardCharsets.UTF_8);
    }
}
