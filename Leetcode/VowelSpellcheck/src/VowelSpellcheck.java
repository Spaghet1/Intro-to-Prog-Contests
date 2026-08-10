import java.util.LinkedList;

public class VowelSpellcheck {
    TreeNode root = new TreeNode('\0');
    public static class TreeNode {
        char ch;
        LinkedList<TreeNode> children = new LinkedList<TreeNode>();

        public TreeNode(char ch) {
            this.ch = ch;
        }

        public void addChild(TreeNode node) {
            children.add(node);
        }

        public TreeNode getChild(char ch) {
            for (TreeNode node : children) {
                if (node.ch == ch) {
                    return node;
                }
            }
            return null;
        }

        public boolean containsChild(char ch) {
            for (TreeNode node : children) {
                if (node.ch == ch) {
                    return true;
                }
            }
            return false;
        }
    }

    public void buildTrie(String[] words) {
        for (String word : words) {
            TreeNode currNode = root;
            for (char ch : word.toCharArray()) {
                TreeNode child = currNode.getChild(ch);
                if (child == null) {
                    TreeNode newNode = new TreeNode(ch);
                    currNode.addChild(newNode);
                    currNode = newNode;
                } else {
                    currNode = child;
                }
            }
        }
    }

    public boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    public String findWord(String words) {
        char[] chars = words.toCharArray();
        TreeNode currNode = root;
        for (int i = 0; i < chars.length; i++) {
            if (isVowel(chars[i])) {

            }
            currNode = findChar(chars, i, currNode);
            if (currNode == null) {
                return "";
            }
        }
        return new String(chars);
    }

    public TreeNode findChar(char[] chars, int index, TreeNode currNode) {
        char ch = chars[index];
        if (currNode.containsChild(ch)) {
            currNode = currNode.getChild(ch);
            return currNode;
        } else if (currNode.containsChild(Character.toLowerCase(ch))) {
            chars[index] = Character.toLowerCase(ch);
            currNode = currNode.getChild(chars[index]);
            return currNode;
        } else if (currNode.containsChild(Character.toUpperCase(ch))) {
            chars[index] = Character.toUpperCase(ch);
            currNode = currNode.getChild(chars[index]);
            return currNode;
        }
        return null;
    }

    public String[] spellchecker(String[] wordlist, String[] queries) {
        return null;
    }

    public static void main(String[] args) {
        VowelSpellcheck spellcheck = new VowelSpellcheck();
        String[] wordList = {"KiTe","kite","hare","Hare"};
        spellcheck.buildTrie(wordList);
        String word = "keto";
        word = spellcheck.findWord(word);
        System.out.println(word);
    }
}
