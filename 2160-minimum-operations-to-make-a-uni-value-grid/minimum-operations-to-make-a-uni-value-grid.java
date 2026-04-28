class Solution {
    public int minOperations(int[][] grid, int x) {
        
        List<Integer> list = new ArrayList<>();
        
        // Flatten grid into list
        for (int[] row : grid) {
            for (int num : row) {
                list.add(num);
            }
        }
        
        // Check if possible
        int rem = list.get(0) % x;
        
        for (int num : list) {
            if (num % x != rem) {
                return -1;
            }
        }
        
        // Sort to find median
        Collections.sort(list);
        
        int median = list.get(list.size() / 2);
        
        int operations = 0;
        
        // Count operations
        for (int num : list) {
            operations += Math.abs(num - median) / x;
        }
        
        return operations;
    }
}