package com.xiaowei.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 参考 http://blog.csdn.net/kimylrong/article/details/17022319
 * Created by zhangwei on 2017/7/17.
 */
public class HuffmanTree {
    private BinaryNode root; //哈夫曼树的根节点
    private int num; //哈夫曼节点的总数

    // huffman树节点
    private class BinaryNode implements Comparable<BinaryNode>{
        Character ch; //字符
        int frequence; //字符对应的频率
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

        //判断是否是左节点
        public boolean isLeftNode(){
            return parent!=null && this == parent.left;
        }
        //判断是否是右节点
        public boolean isRightNode() {
            return parent!=null && this == parent.right;
        }
        //判断是否是叶子节点
        public boolean isLeaf(){
            return this.ch!=null;
        }
    }

    //初始化原始节点
    public List<BinaryNode> buildOriginForest(Map<Character, Integer> map) {
        List<BinaryNode> nodeList = new ArrayList<BinaryNode>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            BinaryNode node = new BinaryNode(entry.getKey(), entry.getValue(), null, null, null);
            nodeList.add(node);
        }
        return nodeList;
    }

    //统计字符频率
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


    //构建哈夫曼树
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

    //获取字符与编码对应关系
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

    //编码
    public String  encode(String originStr){
        //先统计字符频率
        Map<Character, Integer> map = statistics(originStr);
        System.out.println(map);
        //构建原始森林
        List<BinaryNode> list = buildOriginForest(map);
        //构建哈夫曼树
        BinaryNode root = buildHuffmanTree(list);
        //获取字符和编码的对应的关系
        Map<Character,String> csMap = getCharCode(list);
        System.out.println(csMap);
        //计算原始字符的编码
        StringBuffer sb = new StringBuffer();
        for(Character ch: originStr.toCharArray()){
            sb.append(csMap.get(ch));
        }
        return sb.toString();
    }

    /**
     * 解码
     * @param str 二进制编码字符串
     * @param originStr 待编码的字符串
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
        //先统计字符频率
        Map<Character, Integer> map = statistics(originStr);
        //构建原始森林
        List<BinaryNode> list = buildOriginForest(map);
        //构建哈夫曼树
        BinaryNode root = buildHuffmanTree(list);

        //开始解码
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

        //解码
        System.out.println("test decode");
        String str = tree.decode("010110111",originalStr);
        System.out.println(str);
    }
}


