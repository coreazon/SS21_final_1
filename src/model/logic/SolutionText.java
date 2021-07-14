package model.logic;

/**
 * This class models a solution text
 *
 * @author urliz
 * @version 1.0
 */
public class SolutionText {

    private final String text;

    /**
     * Instantiates a solution text
     *
     * @param text the text
     */
    public SolutionText(String text) {
        this.text = text;
    }

    /**
     * function to search the longest common substring and return it as a String
     *
     * @param solutionText the solution text from a student
     * @return the longest common substring
     */
    public String getLongestCommonSubstring(SolutionText solutionText) {
        //length of the texts
        int lengthFirstText = text.length();
        var secondText = solutionText.toString();
        int lengthSecondText = secondText.length();

        var maxLength = 0;

        var longestCommonSubstring = new int[lengthFirstText][lengthSecondText];
        int endIndex = -1;
        //find the LCS
        for (var i = 0; i < lengthFirstText; i++) {
            for (var j = 0; j < lengthSecondText; j++) {
                //no match -> don't need to for subsequence
                if (text.charAt(i) != secondText.charAt(j)) continue;

                if (i == 0 || j == 0) {
                    longestCommonSubstring[i][j] = 1;
                } else {
                    longestCommonSubstring[i][j] = longestCommonSubstring[i - 1][j - 1] + 1;
                }

                if (maxLength < longestCommonSubstring[i][j]) {
                    maxLength = longestCommonSubstring[i][j];
                    endIndex = i;
                }

            }
        }
        return text.substring(endIndex - maxLength + 1, endIndex + 1);
    }

    /**
     * String representation of a text
     *
     * @return String representation
     */
    @Override
    public String toString() {
        return text;
    }
}
