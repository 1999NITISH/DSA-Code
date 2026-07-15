class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {

        List<String> result = new ArrayList<>();
        int i = 0;

        while (i < words.length) {

            int j = i;
            int lineLength = 0;

            // Find words for current line
            while (j < words.length &&
                    lineLength + words[j].length() + (j - i) <= maxWidth) {
                lineLength += words[j].length();
                j++;
            }

            int gaps = j - i - 1;
            StringBuilder sb = new StringBuilder();

            // Last line or single word
            if (j == words.length || gaps == 0) {

                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k != j - 1)
                        sb.append(" ");
                }

                while (sb.length() < maxWidth)
                    sb.append(" ");

            } else {

                int totalSpaces = maxWidth - lineLength;
                int eachSpace = totalSpaces / gaps;
                int extraSpace = totalSpaces % gaps;

                for (int k = i; k < j; k++) {

                    sb.append(words[k]);

                    if (k != j - 1) {

                        for (int s = 0; s < eachSpace; s++)
                            sb.append(" ");

                        if (extraSpace > 0) {
                            sb.append(" ");
                            extraSpace--;
                        }
                    }
                }
            }

            result.add(sb.toString());
            i = j;
        }

        return result;
    }
}