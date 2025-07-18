class Solution {
    public int lengthOfLastWord(String s) {
        int i=0, j=s.length() - 1;
        while(j>=0 && s.charAt(j) == ' '){
            j--;
        }
        while(j>=0 && s.charAt(j) != ' '){
            i++;
            j--;
        }
        return i;
        
    }
}