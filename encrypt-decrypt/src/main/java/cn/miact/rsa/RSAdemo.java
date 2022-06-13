package cn.miact.rsa;


import javax.crypto.Cipher;
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
        // 原文
        String input = "啊这";
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
//        System.out.println(privateKeyEncodedString);
//        System.out.println(publicKeyEncodedString);

        // 创建加密对象
        Cipher cipher = Cipher.getInstance(algorithm);
        // 对加密进行初始化;
        // 第一个参数：加密的模式
        // 第二个参数：你想使用公钥加密还是私钥加密
        // 我想使用私钥进行加密
        cipher.init(Cipher.ENCRYPT_MODE,privateKey);
        // 使用私钥进行加密
        byte[] bytes = cipher.doFinal(input.getBytes());
        System.out.println(Base64.getEncoder().encodeToString(bytes));

        // 公钥解密
        cipher.init(Cipher.DECRYPT_MODE,publicKey);
        byte[] bytes1 = cipher.doFinal(bytes);
        System.out.println(new String(bytes1,0,bytes1.length));
    }
}
