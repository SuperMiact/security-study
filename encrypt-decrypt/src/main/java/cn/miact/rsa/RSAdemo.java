package cn.miact.rsa;


import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * @author : mawei
 * @Classname : RSAdemo
 * @createDate : 2022-06-13 09:34:53
 * @Description :
 */
public class RSAdemo {
    public static void main(String[] args) throws Exception {
        // 创建密钥对
        String algorithm = "RSA";
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
        // 打印公钥和私钥
        System.out.println(privateKeyEncodedString);
        System.out.println(publicKeyEncodedString);


    }
}
