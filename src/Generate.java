
/*  Author: Professor Kristen Gardner
 */
public class Generate {

    public static int[] generateRandomInput(int n) {
        if(n <= 0) return null;
        int[] array = new int[n];
        array[0] = (int)(Math.random()*100000);
        for(int i = 1; i < array.length; i++) {
            array[i] = (int)(Math.random()*100000);
        }
        return array;
    }

    public static int[] generatePartiallySortedInput(int n) {
        if(n <= 0) return null;
        int[] array = new int[n];
        array[0] = (int)(Math.random()*100);
        for(int i = 1; i < array.length; i++) {
            array[i] = array[i-1] + (int)(Math.random()*200)-95;
        }
        return array;
    }

    public static int[] generateMostlySortedInput(int n) {
        if(n <= 0) return null;
        int[] array = new int[n];
        array[0] = (int)(Math.random()*100);
        for(int i = 1; i < array.length; i++) {
            array[i] = array[i-1] + (int)(Math.random()*200)-60;
        }
        return array;
    }

}
