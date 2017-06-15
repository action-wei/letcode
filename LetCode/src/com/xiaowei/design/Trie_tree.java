package com.xiaowei.design;

import java.util.ArrayList;
import java.util.List;

/**
 * �ֵ���(Ĭ��26��Сд��ĸ��
 * Created by xiaowei on 2017/6/15.
 */
public class Trie_tree {
    private final int SIZE = 26; //ÿ���ڵ���԰������ӽڵ���
    private Node root; //�ֵ����ĸ��ڵ�
    //�ֵ����ڵ�
    public class Node{
        private boolean isWord; // ��ʶ�ýڵ��Ƿ����ַ����յ�ڵ�
        private int num; //��ʶ�����ýڵ���ַ�������
        private Node[] child; //�ýڵ���ӽڵ�
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
     * ��ȡ���ڵ�
     * @return
     */
    public Node getRoot(){
        return root;
    }

    /**
     * ����ֵ������Ƿ���ȫ�����ַ���word
     * @param word
     * @return
     */
    public boolean hasStr(String word){
        Node pnode = this.getRoot();
        //����ַ��Ƚ�
        for(int i=0;i<word.length();i++) {
            int index = word.charAt(i)-'a';
            if(pnode.child[index]==null || (i+1==word.length()-1 && pnode.child[index].isWord==false)) return false;
            else{
                pnode = pnode.child[index];
            }
        }
        return true;
    }

    /**
     * ����һ������
     * @param word
     * @return
     */
    public boolean insertOneWord(String word){
        if(hasStr(word)) return false;
        int index = 0;
        Node pnode = this.getRoot();
        for(int i=0;i<word.length()-1;i++){
            index = word.charAt(i)-'a';
            if(pnode.child[index]==null){
                Node node = new Node();
                if(i+1==word.length()-1){
                    //����ǵ��ʵĽ����ַ�������ǰ׺�ַ����������õ��ʱ�ʶisWrod=true
                    node.setNum(pnode.getNum()+1);
                    node.setIsWord(true);
                }
                pnode.addChild(index,node);
                pnode = node;
            }else{
                //���ַ��Ѵ���
                pnode = pnode.getChild(index);
            }
        }
        return true;
    }

    /**
     * ��ȡĳ��ǰ׺�������ĵ�����
     * @param str �� ǰ׺�ַ�
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
     * ��ȡ���е���
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
     * ǰ������
     */
    public void preSearch(){
        search(getRoot());
    }

    /**
     * �������
     * @param root
     */
    private void search(Node root){
        Node pnode = root;
        for(int i=0;i<SIZE;i++){
            if(pnode.getChild(i)!=null){
                System.out.println((char)('a'+i)+"----");
                search(pnode);
            }
        }
    }
}
