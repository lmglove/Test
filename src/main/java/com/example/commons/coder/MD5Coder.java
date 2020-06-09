package com.example.commons.coder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** MD5加密
 * 
 * @date 2012-9-14 */
public class MD5Coder {
    private static final Logger log = LoggerFactory.getLogger(MD5Coder.class);

    private static MD5Coder md5 = null;

    private MD5Coder() {
    }

    public static synchronized MD5Coder getInstance() {
        if (md5 == null) {
            md5 = new MD5Coder();
        }
        return md5;
    }

    /** 将字符串加密
     * 
     * @param string
     * @return */
    public static String encrypt(String string) {
        if (string != null && !"".equals(string)) {
            try {
				return getInstance().getMD5ByBytes(string.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "";
			}
        } else {
            return string;
        }
    }

    private String getMD5ByBytes(byte[] source) {
        String s = null;
        // 用来将字节转换成 16 进制表示的字符
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source);
            byte tmp[] = md.digest(); // MD5 的计算结果是一个128位的长整数， 用字节表示就是16个字节
            char str[] = new char[16 * 2]; // 每个字节用16进制表示的话，使用两个字符，所以表示成16进制需要32个字符
            int k = 0; // 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5的每一个字节，转换成16进制字符的转换
                byte byte0 = tmp[i]; // 取第i个字节
                str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高4位的数字转换,>>>为逻辑右移，将符号位一起右移
                str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低4位的数字转换
            }
            s = new String(str); // 换后的结果转换为字符串
        } catch (Exception e) {
            log.error("MD5 加密 [error]", e);
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println(MD5Coder.encrypt("LiveByTouch_woyue_damamaicai").toUpperCase());
    }
}
