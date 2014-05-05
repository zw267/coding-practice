/* P1803
 *
 * Write a method to randomly generate a set of m integers from an array of
 * size n. Each element must have equal probability of being chosen.
 */

import java.util.Random;

public class P1803 {
    public static void selectN(int[] a, int n) {
        int len = a.length;

        if (n > len)
            return;

        Random random = new Random();
        int tmp;
        int index, last = len - 1;
        int count = len;
        for (int i = 0; i < n; i++) {
            index = random.nextInt(count);

            System.out.print(a[index] + " ");

            a[index] = a[last];

            last--;
            count--;
        }

        System.out.println();

        return;
    }

    public static void main(String args[]) {
        int[] a = {1, 2, 3, 4, 5, 6};

        int n = Integer.parseInt(args[0]);

        selectN(a, n);
    }
}
