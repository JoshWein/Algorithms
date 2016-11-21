package dynamicProgramming;

/**
 * Created by Josh Wein on 11/20/2016.
 */
public class EditDistance {

    public static void getEditDistance(String str1, String str2) {
        int[][] resultsTable = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++) {
            for (int j = 0; j <= str2.length(); j++) {
                if (i == 0) {
                    resultsTable[i][j] = j;
                } else if (j == 0) {
                    resultsTable[i][j] = i;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) { // match
                    resultsTable[i][j] = resultsTable[i - 1][j - 1];
                } else {
                    int insert = resultsTable[i][j - 1];
                    int delete = resultsTable[i - 1][j];
                    int substitute = resultsTable[i - 1][j - 1];
                    if (insert < delete && insert < substitute) {
                        resultsTable[i][j] = insert + 1;
                    } else if (delete < insert && delete < substitute) {
                        resultsTable[i][j] = delete + 1;
                    } else {
                        resultsTable[i][j] = substitute + 1;
                    }
                }
            }
        }
        System.out.println("Cost: " + resultsTable[str1.length()][str2.length()]);
        System.out.print("Path: ");
        reconstructPath(resultsTable, str1.length(), str2.length());
        System.out.println();
    }

    private static void reconstructPath(int[][] resultsTable, int i, int j) {
        // Check if we've hit an edge of the matrix
        if (i <= 0 && j > 0) {
            reconstructPath(resultsTable, i, j - 1);
            System.out.print("I");
            return;
        }
        if (j <= 0 && i > 0) {
            reconstructPath(resultsTable, i - 1, j);
            System.out.print("D");
            return;
        }
        // Check if we're done
        if (j == 0 && i == 0) {
            return;
        }

        int cur = resultsTable[i][j];
        int diag = resultsTable[i - 1][j - 1];
        int left = resultsTable[i - 1][j];
        int up = resultsTable[i][j - 1];
        if (diag <= left && diag <= up && (cur == diag || cur - 1 == diag)) {
            if (diag == cur - 1) {
                reconstructPath(resultsTable, i - 1, j - 1);
                System.out.print("S");
            } else {
                reconstructPath(resultsTable, i - 1, j - 1);
                System.out.print("M");
            }
        } else if (left <= up && (left == cur) || left == cur - 1) {
            reconstructPath(resultsTable, i - 1, j);
            System.out.print("D");
        } else {
            reconstructPath(resultsTable, i, j - 1);
            System.out.print("I");
        }
    }

    public static void main(String[] args) {
        getEditDistance("thou-shalt-not", "you-should-not");
        getEditDistance("watch the movie raising arizona?", "watch da mets raze arizona?");
        getEditDistance("this is what happens when I type slow", "htishisth whaty havpens when ui type fasht");
        getEditDistance("leonard skiena", "lynard skynard");
    }
}
