class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {

        int ans = Integer.MAX_VALUE;

        // Minimum possible finish time if we do land first
        int bestLandFinish = Integer.MAX_VALUE;
        for (int i = 0; i < landStartTime.length; i++) {
            bestLandFinish = Math.min(bestLandFinish,
                    landStartTime[i] + landDuration[i]);
        }

        // Minimum possible finish time if we do water first
        int bestWaterFinish = Integer.MAX_VALUE;
        for (int j = 0; j < waterStartTime.length; j++) {
            bestWaterFinish = Math.min(bestWaterFinish,
                    waterStartTime[j] + waterDuration[j]);
        }

        // Try land -> water
        for (int j = 0; j < waterStartTime.length; j++) {

            int startWater = Math.max(bestLandFinish, waterStartTime[j]);

            ans = Math.min(ans,
                    startWater + waterDuration[j]);
        }

        // Try water -> land
        for (int i = 0; i < landStartTime.length; i++) {

            int startLand = Math.max(bestWaterFinish, landStartTime[i]);

            ans = Math.min(ans,
                    startLand + landDuration[i]);
        }

        return ans;
    }
}