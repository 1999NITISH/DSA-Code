class Solution {
   public boolean isValid(String s) {
    if(s.length()%2!=0) return false;
    Stack<Character> st=new Stack<Character>();
    for(int i=0;i<s.length();i++){
        if(s.charAt(i)==')' || s.charAt(i)=='}' || s.charAt(i)==']'){
            char c='(';
            if(s.charAt(i)=='}') c='{';
            if(s.charAt(i)==']')c='[';
            if(st.isEmpty() || st.pop()!=c ) return false;
        }
        else st.push(s.charAt(i));
    }
    return st.isEmpty();
}
}