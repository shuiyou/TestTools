package com.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;


public class RSAUtil {

    private String privateKey;
    private static final int MAX_ENCRYPT_BLOCK = 117;
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
    public static final String KEY_ALGORITHM ="RSA";


    private PrivateKey privateKey() throws Exception {
        byte[] keyBytes = new byte[Base64.decodeBase64(privateKey).length];
        DataInputStream privateKeyFile = new DataInputStream(
                new ByteArrayInputStream(Base64.decodeBase64(privateKey)));
        privateKeyFile.readFully(keyBytes);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    public String sign(String value) throws Exception {
        Signature signature = Signature.getInstance("SHA1withRSA");

        signature.initSign(privateKey());
        // 处理request字符串
        value = RSAUtil.removeNull(value);
        byte[] message = value.getBytes("UTF-8");
        signature.update(message);
        byte[] sigBytes = signature.sign();

        return (Base64.encodeBase64String(sigBytes));
    }


    public static String removeNull(String value) {
        value = value + "&";
        StringBuffer valueBuffer = new StringBuffer();
        int startIndex = 0;
        int tempIndex, equalIndex;
        while (true) {
            equalIndex = value.indexOf("=", startIndex + 1);
            tempIndex = value.indexOf("&", startIndex + 1);
            if (equalIndex + 1 != tempIndex && tempIndex > 0) {
                if (!value.substring(equalIndex + 1, tempIndex).toUpperCase()
                        .equals("NULL")) {
                    valueBuffer.append(value.substring(startIndex, tempIndex));
                    valueBuffer.append("&");
                }
            }
            if (tempIndex == value.length() - 1) {
                valueBuffer.deleteCharAt(valueBuffer.length() - 1);
                break;
            }
            startIndex = tempIndex + 1;
        }
        return valueBuffer.toString();
    }


    /**
     * 取得新浪支付RSA公文加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public String encrypt(String data, String key) throws Exception {
        byte[] keyBytes = decryptBASE64(key);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = data.getBytes("UTF-8");
        int inputLen = bytes.length;
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(bytes, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(bytes, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return Base64.encodeBase64String(encryptedData);

    }

    public String dencrypt(String data, String key) throws Exception {
        byte[] keyBytes = decryptBASE64(key);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(Base64.decodeBase64(data)), "UTF-8");

    }

    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }



    public String getMD5(String str, String encode) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes(encode));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(
                        Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }
        return md5StrBuff.toString();
    }



}
