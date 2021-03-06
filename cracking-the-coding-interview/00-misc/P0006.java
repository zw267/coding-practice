/* P0006
 *
 * Word Ladder
 * Given two words (start and end), and a dictionary, find the length of
 * shortest transformation sequence from start to end, such that
 *
 * 1) Olny one letter can be changed at a time.
 * 2) Each intermediate word must exist in the dictionary.
 *
 * For example,
 * 
 * Given:
 * start = "hit"
 * end = "cog"
 * dict = ["hot", "dot", "dog", "lot", "log"]
 * As one shortest transformation is
 * "hit" -> "hot" -> "lot" -> "log" -> "cog",
 * return its length 5.
 *
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * */

import java.util.HashSet;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Iterator;

public class P0006 {

  @SuppressWarnings("unchecked")
  public static int wordLadder(String start, String end, HashSet<String> dict) {
    // by default, level 0 only contains start. If there is a chain of, therei
    // will be at least one transformation.
    int res = 1;

    // use a copy of the dictionary
    dict = (HashSet<String>)dict.clone();

    Queue<String> queue = new LinkedList<String>();
    queue.offer(start);

    int[] level = new int[2];
    int i = res % 2; 
    // i = 0;
    // i is the index of the level in which we are dequeuing
    level[i] = 1;
    int j = (i + 1) % 2;
    // j = 1;
    // j is the index of the level where we are enqueuing

    while (queue.size() > 0) {
      String current = queue.poll();
      level[i]--;

      for (Iterator<String> iter = dict.iterator(); iter.hasNext(); ) {
        String cand = iter.next();
        

        if (isValidTrans(current, cand)) {
          queue.offer(cand);
          iter.remove();
          level[j]++;

          if (isValidTrans(end, cand))
            // wrong: return res++;
            return ++res;
        }
      }
      if (level[i] == 0) {
        res++;
        i = res % 2;
        j = (i + 1) % 2;
      }
    }

    return 0;
  }

  private static boolean isValidTrans(String src, String tgt) {
    int len = src.length();

    int mismatchCount = 0;
    for (int i = 0; i < len; i++) {
      if (src.charAt(i) != tgt.charAt(i))
        mismatchCount++;

      if (mismatchCount > 1)
        return false;
    }

    return mismatchCount == 1;
  }

  public static void main(String[] args) {
    String start = "hit", end = "cog";
    //String[] dictArray = new String[]{"hot", "dot", "dog", "lot", "log"};
    String[] dictArray = new String[]{"hot", "dot", "lot"};
    HashSet<String> dict = new HashSet<String>(Arrays.asList(dictArray));

    int res = P0006.wordLadder(start, end, dict);

    System.out.println(res);
  }
}
