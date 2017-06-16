package com.xiaowei.design;

/**
 * 常见hash算法
 * Created by xiaowei on 2017/6/16.
 */
public class Hash {
    /**
     * 加法Hash
     * @param key
     * @param prime:任意的质数，结果的值域为[0，prime-1]
     * @return
     */
    public static int additiveHash(String key, int prime) {
        int hash,i;
        for(hash=key.length(),i=0;i<key.length();i++) {
            hash += key.charAt(i);
        }
        return (hash % prime);
    }

    /**
     * 位运算hash
     * @param key
     * @param prime
     * @return
     */
    public static int rotatingHash(String key, int prime) {
        int hash,i;
        for(hash=key.length(),i=0;i<key.length();i++) {
            //可自由利用各种位运算
            hash = (hash << 4) ^ (hash >> 28) ^ key.charAt(i);
        }
        return (hash % prime);
    }

    /*-------------------乘法Hash---------------------------*/
    private static final long OFFSET_BASIS = 2166136261L; //32的offset basis
    public static final long PRIME = 16777619; //32的prime
    private static int M_SHIFT = 0;


    /**
     * 乘法Hash
     * @param key
     * @return
     */
    public static int MultiHash(String key) {
        int hash = 0,i;
        for(i=0;i<key.length();i++) {
            //jdk5.0String类的hashCode()方法使用了乘法hash，对应的乘数因子是31.
            //推荐的乘数还有131,1313,13131,131313等
            hash = 33 * hash + key.charAt(i);
        }
        return hash;
    }

    /**
     * 32位的FNV算法
     * 使用乘法hash方式的著名hash函数
     * @param data
     * @return
     */
    public static long FNV32Hash(byte[] data) {
        long hash = OFFSET_BASIS;
        for (byte b : data) {
            hash = (hash * PRIME) ^ b;
        }
        return hash;
    }

    /**
     * 改进的32位FNV算法1
     * @param str
     * @return
     */
    public static int FNVHash1(String str) {
        int hash = (int)OFFSET_BASIS;
        for(int i=0;i< str.length();i++) {
            hash = (hash^str.charAt(i))*(int)PRIME;
        }
        hash +=hash<<13;
        hash ^=hash>>7;
        hash +=hash<<3;
        hash ^=hash>>17;
        hash +=hash<<5;
        //如果算出来的值为负数则取绝对值
        if(hash <0 ) hash = Math.abs(hash);
        return hash;
    }

}
