package com.xiaowei.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * �ο� http://blog.csdn.net/kimylrong/article/details/17022319
 * Created by zhangwei on 2017/7/17.
 */
public class HuffmanTree {
    private BinaryNode root; //���������ĸ��ڵ�
    private int num; //�������ڵ������

    // huffman���ڵ�
    private class BinaryNode implements Comparable<BinaryNode>{
        Character ch; //�ַ�
        int frequence; //�ַ���Ӧ��Ƶ��
        BinaryNode left;
        BinaryNode right;
        BinaryNode parent;

        public BinaryNode(Character ch,int frequence, BinaryNode left, BinaryNode right,BinaryNode parent) {
            this.ch = ch;
            this.frequence = frequence;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        @Override
        public int compareTo(BinaryNode o) {
            return this.frequence-o.frequence;
        }

        //�ж��Ƿ�����ڵ�
        public boolean isLeftNode(){
            return parent!=null && this == parent.left;
        }
        //�ж��Ƿ����ҽڵ�
        public boolean isRightNode() {
            return parent!=null && this == parent.right;
        }
        //�ж��Ƿ���Ҷ�ӽڵ�
        public boolean isLeaf(){
            return this.ch!=null;
        }
    }

    //��ʼ��ԭʼ�ڵ�
    public List<BinaryNode> buildOriginForest(Map<Character, Integer> map) {
        List<BinaryNode> nodeList = new ArrayList<BinaryNode>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            BinaryNode node = new BinaryNode(entry.getKey(), entry.getValue(), null, null, null);
            nodeList.add(node);
        }
        return nodeList;
    }

    //ͳ���ַ�Ƶ��
    public Map<Character, Integer> statistics(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0;i<str.length();i++) {
            Character ch = str.charAt(i);
            if (map.containsKey(ch)) {
                Integer value = map.get(ch);
                map.put(ch, value+1);
            }else{
                map.put(ch,1);
            }
        }
        return map;
    }


    //������������
    public BinaryNode buildHuffmanTree(List<BinaryNode> list){
        if (list.size() == 1) {
            return list.remove(0);
        }
        PriorityQueue<BinaryNode> queue = new PriorityQueue<>(list);
        while (queue.size() != 1) {
            BinaryNode left = queue.poll();
            BinaryNode right = queue.poll();
            BinaryNode parent = new BinaryNode(null,left.frequence + right.frequence, left, right,null);
            left.parent = parent;
            right.parent = parent;
            queue.add(parent);
        }
        return root=queue.poll();
    }

    //��ȡ�ַ�������Ӧ��ϵ
    public Map<Character,String> getCharCode(List<BinaryNode> list){
        Map<Character, String> map = new HashMap<>();
        for(BinaryNode node : list){
            BinaryNode curNode = node;
            String codeStr = "";
            while (curNode.parent!=null){
                if(curNode.isLeftNode()) {
                    codeStr = "0"+codeStr;
                }else{
                    codeStr = "1"+codeStr;
                }
                curNode = curNode.parent;
            }
            map.put(node.ch,codeStr);
        }
        return map;
    }

    //����
    public String  encode(String originStr){
        //��ͳ���ַ�Ƶ��
        Map<Character, Integer> map = statistics(originStr);
        System.out.println(map);
        //����ԭʼɭ��
        List<BinaryNode> list = buildOriginForest(map);
        //������������
        BinaryNode root = buildHuffmanTree(list);
        //��ȡ�ַ��ͱ���Ķ�Ӧ�Ĺ�ϵ
        Map<Character,String> csMap = getCharCode(list);
        System.out.println(csMap);
        //����ԭʼ�ַ��ı���
        StringBuffer sb = new StringBuffer();
        for(Character ch: originStr.toCharArray()){
            sb.append(csMap.get(ch));
        }
        return sb.toString();
    }

    /**
     * ����
     * @param str �����Ʊ����ַ���
     * @param originStr ��������ַ���
     * @return
     */
    public String decode(String str,String originStr) {
        if(str==null || str.equals("")) return "";

        char[] chars = str.toCharArray();
        int size = chars.length;
        LinkedList<Character> charList = new LinkedList<>();
        for(int i=0;i<size;i++){
            charList.add(new Character(chars[i]));
        }
        //��ͳ���ַ�Ƶ��
        Map<Character, Integer> map = statistics(originStr);
        //����ԭʼɭ��
        List<BinaryNode> list = buildOriginForest(map);
        //������������
        BinaryNode root = buildHuffmanTree(list);

        //��ʼ����
        StringBuffer sb = new StringBuffer();
        while(charList.size() > 0){
            BinaryNode node = root;
            String tmp = "";
            do{
                Character ch = charList.removeFirst();
                if(ch == '0'){
                    node = node.left;
                }else{
                    node = node.right;
                }
                if(node.ch!=null) tmp+=node.ch;
            }while(!node.isLeaf());
            sb.append(tmp);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String originalStr = "hhelloho";
        HuffmanTree tree = new HuffmanTree();
        String ret = tree.encode(originalStr);
        System.out.println("test encode");
        System.out.println(ret);

        //����
        System.out.println("test decode");
        String str = tree.decode("010110111",originalStr);
        System.out.println(str);
    }
}


