package dailyProgrammer;

/**
 * Today we're going to balance words on one of the letters in them. We'll use the position and letter itself to calculate the weight around the balance point. A word can be balanced if the weight on either side of the balance point is equal. Not all words can be balanced, but those that can are interesting for this challenge.
 * The formula to calculate the weight of the word is to look at the letter position in the English alphabet (so A=1, B=2, C=3 ... Z=26) as the letter weight, then multiply that by the distance from the balance point, so the first letter away is multiplied by 1, the second away by 2, etc.
 * @author Josh Wein
 */

public class BalanceWords {
    public static void main(String[] args) {
        String[] words = {"STEAD", "UNINTELLIGIBILITY", "CONSUBSTANTIATION", "WRONGHEADED", "SUPERGLUE"};
        for (String word : words) {
            balanceWord(word);
        }
    }

    static void balanceWord(String word) {
        for (int mid = 0; mid < word.length(); mid++) {
            int weight = 0;
            for (int test = 0; test < word.length(); test++) {
                if (mid != test) {
                    weight += (word.charAt(test) - 64) * (mid - test);
                }
            }
            if (weight == 0) {
                System.out.println(word.substring(0, mid) + " " + word.charAt(mid) + " " + word.substring(mid + 1, word.length()));
                return;
            }
        }
        System.out.println(word + " does not balance");
    }
}

