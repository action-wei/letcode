package com.xiaowei.design;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典树(默认26个小写字母）
 * Created by xiaowei on 2017/6/15.
 */
public class Trie_tree {
    private final int SIZE = 26; //每个节点可以包含的子节点数
    private Node root; //字典树的根节点
    //字典树节点
    public class Node{
        private boolean isWord; // 标识该节点是否是字符串终点节点
        private int num; //标识经过该节点的字符串数。
        private Node[] child; //该节点的子节点
        public Node(){
            child = new Node[SIZE];
        }

        public boolean isWord() {
            return isWord;
        }

        public int getNum() {
            return num;
        }

        public Node getChild(int index) {
            return child[index];
        }

        public void setIsWord(boolean flag) {
            isWord = flag;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public void addChild(int index,Node node) {
            this.child[index] = node;
        }
    }

    public Trie_tree(){
        root = new Node();
    }

    /**
     * 获取根节点
     * @return
     */
    public Node getRoot(){
        return root;
    }

    /**
     * 检查字典树中是否完全包含字符串word
     * @param word
     * @return
     */
    public boolean hasStr(String word){
        Node pnode = this.getRoot();
        //逐个字符比较
        for(int i=0;i<word.length();i++) {
            int index = word.charAt(i)-'a';
            if(pnode.child[index]==null || (i==word.length()-1 && pnode.child[index].isWord==false)) return false;
            else{
                pnode = pnode.child[index];
            }
        }
        return true;
    }

    /**
     * 插入一个单词
     * @param word
     * @return
     */
    public boolean insertOneWord(String word){
        if(hasStr(word)) return false;
        int index = 0;
        Node pnode = this.getRoot();
        for(int i=0;i<word.length();i++){
            index = word.charAt(i)-'a';
            if(pnode.child[index]==null){
                Node node = new Node();
                if(i==word.length()-1){
                    //如果是单词的结束字符，设置单词标识isWrod=true
                    node.setIsWord(true);
                }
                node.setNum(node.getNum()+1);
                pnode.addChild(index,node);
                pnode = node;
            }else{
                //该字符已存在
                pnode = pnode.getChild(index);
                pnode.setNum(pnode.getNum()+1);
            }
        }
        return true;
    }

    /**
     * 获取某个前缀所包含的单词数
     * @param str ： 前缀字符
     * @return
     */
    public int getCountPreWords(String str){
        Node pnode = this.getRoot();
        for(int i=0;i<str.length()-1;i++){
            int index = str.charAt(i)-'a';
            if(pnode.getChild(index)==null) return 0;
            else  pnode = pnode.getChild(index);
        }
        return pnode.getNum();
    }

    /**
     * 获取所有单词
     * @return
     */
    public List<String> getAllWord(){
        ArrayList<String > list = new ArrayList<>();
        //todo
        return list;
    }

    public List<String> getAllPrefixWords(){
        List<String> list = new ArrayList<>();
        //todo
        return list;
    }


    /**
     * 前序搜索
     */
    public void preSearch(){
        search(getRoot());
    }

    /**
     * 层次搜索
     * @param root
     */
    private void search(Node root){
        Node pnode = root;
        for(int i=0;i<SIZE;i++){
            if(pnode.getChild(i)!=null){
                System.out.print((char)('a'+i)+"----");
                search(pnode.getChild(i));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Trie_tree trie_tree = new Trie_tree();
        trie_tree.insertOneWord("zhangwei");
        trie_tree.insertOneWord("lisumei");
        trie_tree.insertOneWord("zhong");
        if (trie_tree.hasStr("zhong")) {
            System.out.println("there is word:zhong");
        }
        trie_tree.search(trie_tree.getRoot());
    }
}
