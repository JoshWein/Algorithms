import java.util.*;
public class WordSnake {

    public static void main(String[] args) {
        String sampleinput1 = "SHENANIGANS SALTY YOUNGSTER ROUND DOUBLET TERABYTE ESSENCE";
        snake(sampleinput1);
    }

    public static void snake(String words) {
        // If the random algorithm fails to find a path to layout all of the words it resets and tries again.
        while (snakeMain(words) == 0) {
        }
    }

    public static int snakeMain(String words) {
        Random random = new Random();
        String[] input = words.split(" ");
        int length = 0;
        for (String test : input) {
            length += test.length();
        }
        char[][] canvas = new char[length/2][length/2];
        for (char[] canv : canvas) {
            Arrays.fill(canv, ' ');
        }
        int pointerX = random.nextInt(length/2),
            pointerY = random.nextInt(length/2),
            prevDir = -1, direction;
        for (String test : input) {
            do {
                direction = random.nextInt(4);
            } while (direction == prevDir);
            int attempts = 0; // Finds a valid direction to point the word.
            while (!checkForSpace(canvas, pointerX, pointerY, test.length(), direction)) {
                do {
                    direction = random.nextInt(4);
                } while (direction == prevDir);
                if (attempts == 10) {
                    return 0;
                }
                attempts++;
            }
            switch (direction) {
                case 0:
                    for (int i = 0; i < test.length(); i++, pointerY--) {
                        canvas[pointerY][pointerX] = test.charAt(i);
                    }
                    pointerY++;
                    break;
                case 1:
                    for (int i = 0; i < test.length(); i++, pointerX++) {
                        canvas[pointerY][pointerX] = test.charAt(i);
                    }
                    pointerX--;
                    break;
                case 2:
                    for (int i = 0; i < test.length(); i++, pointerY++) {
                        canvas[pointerY][pointerX] = test.charAt(i);
                    }
                    pointerY--;
                    break;
                case 3:
                    for (int i = 0; i < test.length(); i++, pointerX--) {
                        canvas[pointerY][pointerX] = test.charAt(i);
                    }
                    pointerX++;
                    break;
            }
            prevDir = direction;
        }    
        for (int i = 0; i < canvas.length; i++) { // Prints Array
            for (int j = 0; j < canvas.length; j++) {
                System.out.print(canvas[j][i]);
            }
            System.out.println();
        }
        return 1;
    }

    private static boolean checkForSpace(char[][] canvas, int posX, int posY, int length, int dir) {
        length--;
        switch (dir) {
            case 0:
                posY--;
                for (; length > 0; length--, posY--) {
                    if (posY < 0 || canvas[posY][posX] != ' ') {
                        return false;
                    }
                }
                break;
            case 1:
                posX++;
                for (; length > 0; length--, posX++) {
                    if (posX > canvas.length - 1 || canvas[posY][posX] != ' ') {
                        return false;
                    }
                }
                break;
            case 2:
                posY++;
                for (; length > 0; length--, posY++) {
                    if (posY > canvas.length - 1 || canvas[posY][posX] != ' ') {
                        return false;
                    }
                }
                break;
            case 3:
                posX--;
                for (; length > 0; length--, posX--) {
                    if (posX < 0 || canvas[posY][posX] != ' ') {
                        return false;
                    }
                }
                break;
        }
        return true;
    }
}