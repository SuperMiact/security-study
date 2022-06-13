package cn.miact.digest;

import cn.miact.rsa.RSAdemo;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

/**
 * @author : mawei
 * @Classname : SignatureDemo
 * @createDate : 2022-06-13 15:11:10
 * @Description :
 */
public class SignatureDemo {
    public static void main(String[] args) throws Exception{
        String input = "啊这";

        PublicKey publicKey = RSAdemo.getPublicKey("a.pub", "RSA");
        PrivateKey privateKey = RSAdemo.getPrivateKey("a.pri", "RSA");

        // 获取数字签名
        String signaturedData = getSignature(input,"sha256withrsa",privateKey);
        System.out.println(signaturedData);
        // 验证签名
        boolean b = verifySignature(input,"sha256withrsa",publicKey,signaturedData);
        System.out.println(b);
    }

    /**
     * 验证签名
     * @param input             原文
     * @param algorithm         算法
     * @param publicKey         公钥
     * @param signaturedData    签名密文
     * @return
     */
    private static boolean verifySignature(String input, String algorithm, PublicKey publicKey,String signaturedData) throws Exception{
        // 获取签名对象
        Signature signature = Signature.getInstance(algorithm);
        // 初始化校验
        signature.initVerify(publicKey);
        // 传入原文
        signature.update(input.getBytes());
        // 校验数据
        return signature.verify(Base64.getDecoder().decode(signaturedData));
    }

    /**
     * 生成数字签名
     * @param input         原文
     * @param algorithm     算法
     * @param privateKey    私钥
     * @return
     */
    private static String getSignature(String input, String algorithm, PrivateKey privateKey) throws Exception{
        // 获取签名对象
        Signature signature = Signature.getInstance(algorithm);
        // 初始化签名
        signature.initSign(privateKey);
        // 传入原文
        signature.update(input.getBytes());
        // 开始签名
        byte[] sign = signature.sign();
        // 使用base64转码
        return Base64.getEncoder().encodeToString(sign);
    }
}
