package com.mrinalshukla.crackingcoding.practice.ch1.arraystrings;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Chapter1Solutions {

    private Chapter1Solutions() {
        //Cannot instantiate; Static methods only!
    }

    public static boolean hasAllUniqueCharacters(String s) {
        /*
        Assumptions:
        1) All ascii characters in English including
            - letters (lower/upper),
            - spaces,
            - us/keyboard symbols,
            - numbers (0-9)
        2) Input string is non-null object

        Time Complexity: O(n)
        Space Complexity: O(m) -> m = 128
         */

        int[] asciiCodes = new int[128];
        char[] chars = s.toCharArray();

        for (char c : chars) {
            if (asciiCodes[c] == 0)
                asciiCodes[c]++;
            else
                return false;
        }

        return true;
    }

    public static boolean hasUniqueNoExtraMemory(String s) {
        /*
        Assumptions: Same as hasUniqueCharacters(String s)
        Time Complexity: O(n^2)
        Space Complexity: O(1)

         */
        if (s.length() < 2)
            return true;

        for (int i=0; i<s.length(); i++) {
            for (int j=i+1; j<s.length(); j++) {
                if (s.charAt(j) == s.charAt(i))
                    return false;
            }
        }
        return true;
    }

    public static boolean isPermutation(String a, String b) {
        /*
        Assumptions: Array sorting is allowed and we don't have to implement it.
        Time Complexity: (Merge sort is O(nlog(n))) => so, O(nlog(n))
        Space Complexity: O(m+p) => m: length of a, p: length of b => Linear.
         */

        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();

        Arrays.sort(aChars);
        Arrays.sort(bChars);

        String aSorted = String.valueOf(aChars);
        String bSorted = String.valueOf(bChars);

        return  aSorted.equals(bSorted);
    }

    public static String urlify(char[] s, int trueLength) {
        /*
        Assumption:
        1) 's' is never less than 3 unless it is 0 -> emptyString

        Time Complexity: O(n)
        Space Complexity: O(1)
         */

        int index = s.length;

        for (int i = trueLength - 1; i >= 0; i--) {
            if (s[i] == ' ') {
                s[index - 1] = '0';
                s[index - 2] = '2';
                s[index - 3] = '%';
                index -= 3;
            } else {
                s[index - 1] = s[i];
                index--;
            }
        }

        return String.valueOf(s);
    }

    public static boolean isPalindromePermutation(String s) {
        /*
        Assumption:
        1) 's' is not null
        2) Palindromes are not case-sensitive. See toLowercase in forLoop below.
        Time Complexity: O(n)
        Space Complexity: O(m) => m = 128
         */

        if (s.length() < 2)
            return false;

        int[] asciiCodes = new int[128];

        for (char c: s.toLowerCase().toCharArray()) {
            asciiCodes[c]++;
        }

        for (int i : asciiCodes) {
            boolean foundOdd = false;
            if (i % 2 == 1) {
                if (foundOdd) {
                    return false;
                }
                foundOdd = true;
            }
        }
        return true;
    }

    public static boolean oneAway(String a, String b) {
        /*
        TC: O(n)
        SC: O(2n)
         */

        //Check length only different by at most 1
        if (Math.abs(a.length() - b.length()) > 1)
            return false;

        boolean swap = a.length() <= b.length() ? false: true;
        String shorterString = swap ? b : a;
        String longerString = swap ? a : b;

        int shorterIndex = 0;
        int longerIndex = 0;

        boolean diffFound = false;
        while (shorterIndex < shorterString.length() && longerIndex < longerString.length()) {
            // same length; one replacement
            // different length; one added

            // check if only one replacement
            if (shorterString.charAt(shorterIndex) != longerString.charAt(longerIndex)) {
                if (diffFound)
                    return false;
                else {
                    diffFound = true;
                }

            } else {
                shorterIndex++;
            }
            longerIndex++;
        }
        return true;
    }

    public static String compressString (String s) {
        /*
        's' is not null

        TC: O(n)
        SC: O(1)
         */

        int count = 0;
        char last = s.charAt(0);

        StringBuilder sb = new StringBuilder();
        for (char curr : s.toCharArray()) {
            if (last != curr) {
                sb.append(last).append(count);
                count = 0;
            }
            count++;
            last = curr;
        }
        final String newStr = sb.append(last).append(count).toString();
        return s.length() > newStr.length() ? newStr : s;
    }

    public static int[][] rotateMatrix (int[][] matrix) {
        int temp = matrix[0][0];

        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix.length; j++) {
                temp = matrix[i][j];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }

        return matrix;
    }

    public static boolean stringRotation (String s1, String s2) {
        final String rotation = s2+s2;
        return isSubstring(s1, rotation);
    }

    private static boolean isSubstring (String subString, String string) {
        return string.contains(subString);
    }
    public static void main (String... args) {
        final String hasUniqueResults = String.format(
                "emptyString -> %s, " +
                        "singleSpace -> %s, " +
                        "'abcndjdk' -> %s, " +
                        "'y 89y kb' -> %s, " +
                        "'bb' -> %s, " +
                        "'cbc' -> %s",
                hasAllUniqueCharacters(""),
                hasAllUniqueCharacters(" "),
                hasAllUniqueCharacters("abcndjdk"),
                hasAllUniqueCharacters("y 89y kb"),
                hasAllUniqueCharacters("bb"),
                hasAllUniqueCharacters("cbc")
                );
        final String hasUniqueNoExtraMemoryResults = String.format(
                "emptyString -> %s, " +
                        "singleSpace -> %s, " +
                        "'abcndjdk' -> %s, " +
                        "'y 89y kb' -> %s, " +
                        "'bb' -> %s, " +
                        "'cbc' -> %s",
                hasUniqueNoExtraMemory(""),
                hasUniqueNoExtraMemory(" "),
                hasUniqueNoExtraMemory("abcndjdk"),
                hasUniqueNoExtraMemory("y 89y kb"),
                hasUniqueNoExtraMemory("bb"),
                hasUniqueNoExtraMemory("cbc")
        );
        System.out.println(hasUniqueResults);
        System.out.println(hasUniqueNoExtraMemoryResults);

        final String isPermutationResults = String.format(
                "emptyString, emptyString -> %s, " +
                        "singleSpace, singleSpace -> %s, " +
                        "'cat, act' -> %s, " +
                        "'y 89y kb, 89yybk<two-spaces>' -> %s, " +
                        "'bb, bb<space>' -> %s, " +
                        "'cbc, xycbc' -> %s",
                isPermutation("", ""),
                isPermutation(" ", " "),
                isPermutation("cat", "act"),
                isPermutation("y 89y kb", "89yybk  "),
                isPermutation("bb", "bb "),
                isPermutation("cbc", "xycbc")
        );
        System.out.println(isPermutationResults);

        final String urlifyResults = String.format("urlify: 'Mr John Smith    ' -> %s", urlify("Mr John Smith    ".toCharArray(), 13));
        System.out.println(urlifyResults);

        final String isPermutationPalindrome = String.format("isPermutationPalindrome: 'tactcoapapa' -> %s", isPalindromePermutation("tactcoapapa"));
        System.out.println(isPermutationPalindrome);

        final String oneAwayResults = String.format("oneAway: 'pale', 'bales' -> %s", oneAway("pale", "bales"));
        System.out.println(oneAwayResults);

        final String compressStringResults = String.format("compressString: 'aaabb' -> %s", compressString("aaabb"));
        System.out.println(compressStringResults);

        final String stringRotationResult = String.format("String Rotation: 'waterbottle' of 'erbottlewat' -> %s", stringRotation("waterbottle", "erbottlewat"));
        System.out.println(stringRotationResult);
    }


}
