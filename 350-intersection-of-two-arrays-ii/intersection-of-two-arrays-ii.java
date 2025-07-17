class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
         Map<Integer, Integer> freqMap = new HashMap<>();
        
        // Step 1: Count frequencies in nums1
        for (int num : nums1) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        List<Integer> resultList = new ArrayList<>();
        
        // Step 2: Loop through nums2 and collect common elements
        for (int num : nums2) {
            if (freqMap.containsKey(num) && freqMap.get(num) > 0) {
                resultList.add(num);
                freqMap.put(num, freqMap.get(num) - 1); // Decrease frequency
            }
        }

        // Step 3: Convert list to array
        int[] result = new int[resultList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultList.get(i);
        }

        return result;
        
    }
}