package com.example.spring;

import org.junit.jupiter.api.Test;
import java.util.Stack;

public class TestGenerateTree {


    class TreeNode {
        char val;
        TreeNode left, right;

        public TreeNode(char val) {
            this.val = val;
        }
    }

    public TreeNode generateFromString(String treeStr) {
        char[] chars = treeStr.toCharArray();
        TreeNode node = new TreeNode(chars[0]);
        TreeNode root = node;
        Stack<TreeNode> stack = new Stack<>();
        boolean left = true;
        for (int i = 1; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar == '(') {
                stack.push(node);
                left = true;
            } else if (aChar == ',') {
                left = false;
            } else if (aChar == ')') {
                stack.pop();
            } else {
                node = new TreeNode(aChar);
                if (left) {
                    stack.peek().left = node;
                } else {
                    stack.peek().right = node;
                }
            }
        }
        return root;
    }

    @Test
    public void testGenerate(){
        String treeStr = "A(B(D(,),),C(,F(,)))";
        TreeNode treeNode = generateFromString(treeStr);
    }

}
