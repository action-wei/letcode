package com.xiaowei.design;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * ��¡������--�ӿ��ж�һ��Ԫ���Ƿ��ڼ����г��ֵķ���
 * Created by xiaowei on 2017/6/16.
 */
public class BloomFilter {
    public static final int NUM_SLOTS = 1024*1024*8;
    public static final int NUM_HASH = 8;
    private BigInteger bitmap = new BigInteger("0");

    public static void main(String[] args) {
        //���Դ���
        BloomFilter bf = new BloomFilter();
        ArrayList<String> contents = new ArrayList<>();
        contents.add("sldkjelsjf");
        contents.add("fdslfjlga");
        contents.add("fjdlasfjds");
        contents.add("kfioasnfds");

        for(int i=0;i<contents.size();i++) {
            bf.addElement(contents.get(i));
        }
        System.out.println(bf.check("fdslfjlga"));
        System.out.println(bf.check("fdjsafjl"));
    }

    /**
     * ����hashֵ
     * @param message
     * @param n
     * @return
     */
    private int getHash(String message, int n) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            message = message + String.valueOf(n);
            byte[] bytes = message.getBytes();
            md5.update(bytes);
            BigInteger bi = new BigInteger(md5.digest());

            return Math.abs(bi.intValue()) % NUM_SLOTS;
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getStackTrace());
        }
        return -1;
    }

    /**
     * ����ַ���
     * @param message
     */
    public void addElement(String message) {
        for(int i=0;i<NUM_HASH;i++) {
            int hashcode = getHash(message, i);
            if (!bitmap.testBit(hashcode)) {
                bitmap = bitmap.or(new BigInteger("1").shiftLeft(hashcode));
            }
        }
    }

    /**
     * ����ַ����Ƿ����
     * @param message
     * @return
     */
    public boolean check(String message) {
        for(int i=0;i<NUM_HASH;i++) {
            int hashcode = getHash(message, i);
            if (!bitmap.testBit(hashcode)) {
                return false;
            }
        }
        return true;
    }
}
