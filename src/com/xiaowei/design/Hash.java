package com.xiaowei.design;

/**
 * ����hash�㷨
 * Created by xiaowei on 2017/6/16.
 */
public class Hash {
    /**
     * �ӷ�Hash
     * @param key
     * @param prime:����������������ֵ��Ϊ[0��prime-1]
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
     * λ����hash
     * @param key
     * @param prime
     * @return
     */
    public static int rotatingHash(String key, int prime) {
        int hash,i;
        for(hash=key.length(),i=0;i<key.length();i++) {
            //���������ø���λ����
            hash = (hash << 4) ^ (hash >> 28) ^ key.charAt(i);
        }
        return (hash % prime);
    }

    /*-------------------�˷�Hash---------------------------*/
    private static final long OFFSET_BASIS = 2166136261L; //32��offset basis
    public static final long PRIME = 16777619; //32��prime
    private static int M_SHIFT = 0;


    /**
     * �˷�Hash
     * @param key
     * @return
     */
    public static int MultiHash(String key) {
        int hash = 0,i;
        for(i=0;i<key.length();i++) {
            //jdk5.0String���hashCode()����ʹ���˳˷�hash����Ӧ�ĳ���������31.
            //�Ƽ��ĳ�������131,1313,13131,131313��
            hash = 33 * hash + key.charAt(i);
        }
        return hash;
    }

    /**
     * 32λ��FNV�㷨
     * ʹ�ó˷�hash��ʽ������hash����
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
     * �Ľ���32λFNV�㷨1
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
        //����������ֵΪ������ȡ����ֵ
        if(hash <0 ) hash = Math.abs(hash);
        return hash;
    }

}
