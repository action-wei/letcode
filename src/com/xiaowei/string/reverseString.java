package com.xiaowei.string;

/**
 * ��ת�ַ���
 * Created by xiaowei on 2017/6/14.
 */
public class reverseString {
    /**
     * ��ת�ַ���
     * @param str
     * @return
     */
    public static String reverse(String str){
        if(null==str || str.isEmpty() || str.length()==1) return str;
        StringBuffer sb = new StringBuffer(str);
        return reverse(sb,0,sb.length());
    }

    public static String reverse(StringBuffer sb,int begin,int end){
        for(int i=begin,j=end-1;i<j;i++,j--){
            char tmp = sb.charAt(i);
            sb.setCharAt(i,sb.charAt(j));
            sb.setCharAt(j,tmp);
        }
        return sb.toString();
    }

    /**
     * ��תһ�����ӣ������ɱ��Ϳո�ָ�
     *  ���磺 hello  world  --->  world  hello
     * @param str
     * @return
     */
    public static String reverse_sen(String str) {
        if(null==str|| str.equals("")|| str.length()==1) return str;
        int len = str.length();
        StringBuffer sb = new StringBuffer(str);
        // ��һ�������������ӷ�ת
        reverse(sb,0,sb.length());
        //��ת�����е�ÿ������
        int begin = 0, end = 0;
        while(begin < len){
            if(sb.charAt(begin)==' '){
                begin++;
                end++;
            }else if(end<len && sb.charAt(end)==' '){
                reverse(sb,begin,end);
                begin = end;
            }else{
                end++;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = " hello world!";
        String ret = reverse(str);
        System.out.println(ret);
        System.out.println(" reverse sentence :");
        String sen = " world!  hello";
        String ret_sen = reverse_sen(sen);
        System.out.println(ret_sen);

        System.out.println("ʹ����򽻻��ַ�");
        char a ='b';
        char b='a';
        a = (char)(a^b);
        b = (char)(b^a);
        a = (char)(b^a);
        System.out.println(a);
        System.out.println(b);
    }
}
