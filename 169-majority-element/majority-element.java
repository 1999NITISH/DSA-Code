class Solution {
    public int majorityElement(int[] nums) {
        int condidate = 0;
        int count = 0;
        
        for(int num : nums){
            if(count == 0){
                condidate =num;

            }
            if(num == condidate){
                count++;
            }
            else{
                count--;
            }
        }
        return condidate;
        
    }
}