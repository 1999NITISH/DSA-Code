class Solution {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int index = -1;
    }

    TrieNode root = new TrieNode();
    String[] wordsContainer;

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {

        this.wordsContainer = wordsContainer;

        // Build Trie using reversed words
        for (int i = 0; i < wordsContainer.length; i++) {
            insert(wordsContainer[i], i);
        }

        int[] ans = new int[wordsQuery.length];

        for (int i = 0; i < wordsQuery.length; i++) {
            ans[i] = search(wordsQuery[i]);
        }

        return ans;
    }

    private void insert(String word, int idx) {

        TrieNode node = root;

        updateBestIndex(node, idx);

        for (int i = word.length() - 1; i >= 0; i--) {

            int ch = word.charAt(i) - 'a';

            if (node.children[ch] == null) {
                node.children[ch] = new TrieNode();
            }

            node = node.children[ch];

            updateBestIndex(node, idx);
        }
    }

    private void updateBestIndex(TrieNode node, int idx) {

        if (node.index == -1) {
            node.index = idx;
            return;
        }

        int currentLen = wordsContainer[node.index].length();
        int newLen = wordsContainer[idx].length();

        // Prefer smaller length
        // If equal length, smaller index remains automatically
        if (newLen < currentLen) {
            node.index = idx;
        }
    }

    private int search(String word) {

        TrieNode node = root;

        for (int i = word.length() - 1; i >= 0; i--) {

            int ch = word.charAt(i) - 'a';

            if (node.children[ch] == null) {
                break;
            }

            node = node.children[ch];
        }

        return node.index;
    }
}