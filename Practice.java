import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Practice {

    public static void main(String[] args) throws Exception {
        int[] arr = { 3, 30, 34, 5, 9 };

    }

    public static int findMaxConsecutiveOnes(int[] nums) {
        int index = 0, maxCount = 0;

        while (index < nums.length) {
            int tempCount = 0;
            if (nums[index] == 1) {
                tempCount++;
                index++;
                while (index < nums.length && nums[index] == 1) {
                    tempCount++;
                    index++;
                }
                maxCount = Math.max(maxCount, tempCount);
            }
            index++;
        }

        return maxCount;
    }

    public static int thirdMax(int[] nums) {
        Set<Integer> max = new HashSet<>();
        for (int a : nums) {
            max.add(a);
            if (max.size() > 3) {
                max.remove(Collections.min(max));
            }
        }
        return max.size() == 3 ? Collections.min(max) : Collections.max(max);
    }

    public static int maximum69Number(int num) {
        if (num % 10 == num) {
            return 9;
        }
        boolean switched = false;
        String tempString = String.valueOf(num);
        StringBuilder maxInt = new StringBuilder();

        for (int i = 0; i < tempString.length(); i++) {
            if (tempString.charAt(i) == '6' && !switched) {
                switched = true;
                maxInt.append(9);
            } else {
                maxInt.append(tempString.charAt(i));
            }
        }

        return Integer.parseInt(maxInt.toString());
    }

    public static List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(arr);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] < minDiff) {
                minDiff = arr[i] - arr[i - 1];
            }
        }

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] == minDiff) {
                List<Integer> tempList = new ArrayList<>();
                tempList.add(arr[i - 1]);
                tempList.add(arr[i]);
                res.add(tempList);
            }
        }
        return res;
    }

    public static String reverseVowels(String s) {
        Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        char[] arr = s.toCharArray();
        int left = 0, right = arr.length - 1;
        while (left < right) {
            if (!set.contains(arr[left])) {
                left++;
            } else if (!set.contains(arr[right])) {
                right--;
            } else {
                char tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
                left++;
                right--;
            }
        }
        return new String(arr);
    }

    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> list = new ArrayList<>();
        int p1 = 0, p2 = 0, p3 = 0;

        while (p1 < arr1.length && p2 < arr2.length && p3 < arr3.length) {
            if (arr1[p1] == arr2[p2] && arr1[p1] == arr3[p3]) {
                list.add(arr1[p1]);
                p1++;
                p2++;
                p3++;
            } else if (arr1[p1] < arr2[p2]) {
                p1++;
            } else if (arr2[p2] < arr3[p3]) {
                p2++;
            } else {
                p3++;
            }

        }
        return list;
    }

    public static int largestUniqueNumber(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int largest = -1;
        for (Integer a : map.keySet()) {
            if (a > largest && map.get(a) == 1) {
                largest = a;
            }
        }
        return largest;
    }

    public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        int prev = lower - 1;
        for (int i = 0; i <= nums.length; i++) {
            int curr = (i < nums.length) ? nums[i] : upper + 1;
            if (prev + 1 <= curr - 1) {
                result.add(formatRange(prev + 1, curr - 1));
            }
            prev = curr;
        }
        return result;
    }

    private static String formatRange(int lower, int upper) {
        if (lower == upper) {
            return String.valueOf(lower);
        }
        return lower + "->" + upper;
    }

    public static int maxLengthBetweenEqualCharacters(String s) {
        int maxLength = Integer.MIN_VALUE;
        HashMap<Character, int[]> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            if (map.containsKey(a)) {
                int[] tempArr = map.get(a);
                tempArr[1] = i;
                map.put(a, tempArr);
            } else {
                int[] newArr = new int[2];
                newArr[0] = i;
                map.put(a, newArr);
            }
        }

        for (Character key : map.keySet()) {
            if (map.get(key)[1] - map.get(key)[0] - 1 > maxLength) {
                maxLength = map.get(key)[1] - map.get(key)[0] - 1;
            }
        }
        return maxLength;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] arr = new int[2];
        arr[0] = searchInsert(nums, target);
        arr[1] = searchRightMost(nums, target);
        return arr;
    }

    public static int searchLeftMost(int[] nums, int target) {
        int index = -1;
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] >= target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
            if (nums[middle] == target) {
                index = middle;
            }
        }
        return index;
    }

    public static int searchRightMost(int[] nums, int target) {
        int index = -1;
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] <= target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
            if (nums[middle] == target) {
                index = middle;
            }
        }
        return index;
    }

    public static List<String> commonChars(String[] words) {
        List<String> commonList = new ArrayList<>();
        int[] minFrequencies = new int[26];
        Arrays.fill(minFrequencies, Integer.MAX_VALUE);

        for (String aString : words) {
            int[] charFrequencies = new int[26];
            for (char a : aString.toCharArray()) {
                charFrequencies[a - 'a']++;
            }

            for (int i = 0; i < charFrequencies.length; i++) {

                if (charFrequencies[i] >= 0) {
                    minFrequencies[i] = Math.min(minFrequencies[i], charFrequencies[i]);

                }

            }

        }

        for (int i = 0; i < minFrequencies.length; i++) {
            while (minFrequencies[i] > 0) {
                commonList.add(String.valueOf((char) (i + 'a')));
                minFrequencies[i]--;
            }
        }

        return commonList;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode slow = head, fast = head.next;
        while (slow.next != null) {
            ListNode tempNode = fast.next;
            fast.next = slow;
            slow.next = tempNode;
            dummy.next = fast;
            dummy = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return dummy.next;
    }

    public static int[] anagramMappings(int[] nums1, int[] nums2) {
        if (nums1.length == 1)
            return nums1;
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] arr = new int[nums1.length];
        for (int i = 0; i < nums2.length; i++) {
            map.putIfAbsent(nums2[i], i);
        }

        for (int i = 0; i < nums1.length; i++) {
            arr[i] = map.get(nums1[i]);
        }
        return arr;
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<Integer, List<String>> map = new HashMap<>();
        int hashCode;

        for (int i = 0; i < strs.length; i++) {
            hashCode = computeHashCode(strs[i]);
            System.out.println("Hashcode : " + hashCode);
            map.putIfAbsent(hashCode, new ArrayList<>());
            map.get(hashCode).add(strs[i]);
        }
        return new ArrayList<>(map.values());
    }

    private static int computeHashCode(String s) {
        byte[] count = new byte[26];
        for (byte i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        return Arrays.hashCode(count);
    }

    public static List<List<String>> groupAnagrams2(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        byte[] charFreq = new byte[26];
        for (int i = 0; i < strs.length; i++) {
            Arrays.fill(charFreq, (byte) 0);
            for (int j = 0; j < strs[i].length(); j++) {
                charFreq[strs[i].charAt(j) - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int k = 0; k < 26; k++) {
                sb.append("#");
                sb.append(charFreq[k]);
            }
            String key = sb.toString();
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(strs[i]);

        }
        return new ArrayList<>(map.values());
    }

    public static boolean wordPattern(String pattern, String s) {
        HashMap<Character, String> charMap = new HashMap<>();
        HashMap<String, Character> wordMap = new HashMap<>();
        String[] words = s.split(" ");
        if (words.length != pattern.length())
            return false;

        for (int i = 0; i < words.length; i++) {
            char c = pattern.charAt(i);
            String w = words[i];
            if (!charMap.containsKey(c)) {
                if (wordMap.containsKey(w)) {
                    return false;
                } else {
                    charMap.put(c, w);
                    wordMap.put(w, c);
                }
            } else {
                if (!charMap.get(c).equals(w)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static String removeDuplicateLetters(String s) {
        if (s.length() == 1)
            return s;
        int[] charArr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            if (charArr[s.charAt(i) - 'a'] != 1) {
                charArr[s.charAt(i) - 'a']++;
            }
        }
        System.out.println(Arrays.toString(charArr));
        StringBuilder tempString = new StringBuilder();
        for (int i = 0; i < charArr.length; i++) {
            if (charArr[i] != 0) {
                tempString.append((char) (i + 97));
            }
        }
        return tempString.toString();
    }

    public static boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : arr) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        Set<Integer> set = new HashSet<>(map.values());
        return set.size() == map.size();
    }

    public static void sortColors(int[] nums) {
        if (nums.length <= 1)
            return;
        int index = 0, start = 0, end = nums.length - 1;

        while (index <= end && start < end) {
            if (nums[index] == 0) {
                nums[index] = nums[start];
                nums[start] = 0;
                start++;
                index++;
            } else if (nums[index] == 2) {
                nums[index] = nums[end];
                nums[end] = 2;
                end--;
            } else {
                index++;
            }
        }
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levelList = new ArrayList<>();
        if (root == null)
            return levelList;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.remove();
                level.add(current.val);
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
            levelList.add(level);
        }
        return levelList;
    }

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode() {
        };

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

    }

    public static String removeDuplicates2(String S) {
        StringBuilder sb = new StringBuilder();
        int sbLength = 0;
        for (char character : S.toCharArray()) {
            if (sbLength != 0 && character == sb.charAt(sbLength - 1))
                sb.deleteCharAt(sbLength-- - 1);
            else {
                sb.append(character);
                sbLength++;
            }
        }
        return sb.toString();
    }

    public static String removeDuplicates(String s) {
        if (s.length() == 1)
            return s;
        Stack<Character> stack = new Stack<>();
        int index = 0;
        while (index < s.length()) {
            if (!stack.isEmpty() && stack.peek() == s.charAt(index)) {
                stack.pop();
            } else {
                stack.push(s.charAt(index));
            }
            index++;
        }
        StringBuilder sb = new StringBuilder();
        for (char ch : stack) {
            sb.append(ch);
        }
        return sb.toString();
    }

    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length() || s2.length() == 0)
            return false;
        int[] arr1 = new int[26], arr2 = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            arr1[s1.charAt(i) - 'a']++;
            arr2[s2.charAt(i) - 'a']++;
        }

        for (int i = s1.length(); i < s2.length(); i++) {
            if (Arrays.equals(arr1, arr2))
                return true;
            arr2[s2.charAt(i) - 'a']++;
            arr2[s2.charAt(i - s1.length()) - 'a']--;
        }
        return Arrays.equals(arr1, arr2);
    }

    public boolean checkInclusion2(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;
        int[] map = new int[26];
        for (char c : s1.toCharArray())
            map[c - 'a']++;
        int j = 0, i = 0;
        int count_chars = s1.length();
        while (j < s2.length()) {
            if (map[s2.charAt(j++) - 'a']-- > 0)
                count_chars--;
            if (count_chars == 0)
                return true;
            if (j - i == s1.length() && map[s2.charAt(i++) - 'a']++ >= 0)
                count_chars++;
        }
        return false;
    }

    public static String addStrings(String num1, String num2) {
        String newString = "";
        int carry = 0;
        int sum;
        int p1 = num1.length() - 1, p2 = num2.length() - 1;
        while (p1 > 0 || p2 > 0) {
            sum = Character.getNumericValue(num1.charAt(p1)) +
                    Character.getNumericValue(num2.charAt(p2)) + carry;
            carry = sum / 10;
            newString = (sum % 10) + newString;
            p1--;
            p2--;
        }
        if (carry > 0)
            newString = (carry) + newString;
        return newString;
    }

    public static void reverseWords(char[] s) {
        reverse(s, 0, s.length - 1);
        reverseEachWord(s);
        System.out.println(Arrays.toString(s));
    }

    public static void reverseEachWord(char[] s) {
        int start = 0;
        int end = 0;
        while (start < s.length) {
            while (end < s.length && s[end] != ' ')
                end++;
            reverse(s, start, end - 1);
            start = end + 1;
            end++;
        }
    }

    public static void reverse(char[] words, int l, int r) {
        char temp;
        while (l < r) {
            temp = words[l];
            words[l++] = words[r];
            words[r--] = temp;
        }
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || strs == null)
            return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
            }
        }
        return prefix;
    }

    class MyQueue {

        private Stack<Integer> stack1, stack2;

        public MyQueue() {
            this.stack1 = new Stack<>();
            this.stack2 = new Stack<>();
        }

        public void push(int x) {
            stack1.push(x);
        }

        public int pop() {
            shift();
            return stack2.pop();
        }

        public int peek() {
            shift();
            return stack2.peek();
        }

        public void shift() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
        }

        public boolean empty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }

    }

    public boolean isValidSudoku(char[][] board) {
        int[] rows = new int[9], cols = new int[9], squares = new int[9];
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] != '.') {
                    int value = (1 << (board[r][c] - '1'));
                    if ((value & rows[r]) > 0 || (value & cols[c]) > 0 || (value & squares[3 * (r / 3) + c / 3]) > 0)
                        return false;
                    rows[r] |= value;
                    cols[c] |= value;
                    squares[3 * (r / 3) + c / 3] |= value;
                }
            }
        }
        return true;
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() < 2)
            return s.length();
        int p1 = 0, p2 = 0;
        int max = 0;
        HashSet<Character> newSet = new HashSet<>();
        while (p2 < s.length()) {
            if (!newSet.contains(s.charAt(p2))) {
                newSet.add(s.charAt(p2));
                p2++;
                max = Math.max(newSet.size(), max);
            } else {
                newSet.remove(s.charAt(p1));
                p1++;
            }
        }
        return max;
    }

    public static List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        result.get(0).add(1);

        for (int i = 1; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> prevRow = new ArrayList<>(result.get(i - 1));
            row.add(1);
            for (int j = 1; j < prevRow.size(); j++) {
                row.add(prevRow.get(j - 1) + prevRow.get(j));
            }
            row.add(1);
            result.add(row);
        }
        return result;
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> row = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            row.add(0, 1);
            for (int j = 1; j < row.size() - 1; j++) {
                row.set(j, row.get(j) + row.get(j + 1));
            }
            result.add(new ArrayList<>(row));
        }
        return result;
    }

    public static boolean canConstruct(String ransomNote, String magazine) {
        if (magazine.length() < ransomNote.length())
            return false;
        int[] charArr = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            charArr[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            if (charArr[ransomNote.charAt(i) - 'a'] < 1)
                return false;
            charArr[ransomNote.charAt(i) - 'a']--;
        }
        return true;
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        int[] charArr = new int[26];

        for (int i = 0; i < s.length(); i++) {
            charArr[s.charAt(i) - 'a']++;
            charArr[t.charAt(i) - 'a']--;
        }
        for (int a : charArr) {
            if (a != 0)
                return false;
        }
        return true;
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0)
            return false;
        int rows = matrix.length;
        int columns = matrix[0].length;
        int left = 0;
        int right = rows * columns - 1;
        int midPoint, midElement;
        while (left <= right) {
            midPoint = left + (right - left) / 2;
            midElement = matrix[midPoint / columns][midPoint % columns];
            if (midElement == target) {
                return true;
            } else if (midElement > target) {
                right = midPoint - 1;
            } else {
                left = midPoint + 1;
            }
        }
        return false;
    }

    public static String reverseWords(String s) {
        char[] tempArr = s.toCharArray();
        int slow = 0, fast = 0;
        while (fast < tempArr.length) {
            if (tempArr[fast] == ' ') {
                reverseWord(tempArr, slow, fast - 1);
                slow = fast + 1;
            }
            fast++;
        }
        reverseWord(tempArr, slow, fast - 1);
        return new String(tempArr);
    }

    public static void reverseWord(char[] c, int i, int j) {
        char temp;
        while (i < j) {
            temp = c[i];
            c[i++] = c[j];
            c[j--] = temp;
        }
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        HashMap<Integer, Integer> newMap = new HashMap<>();
        for (int a : nums1) {
            newMap.put(a, newMap.getOrDefault(a, 0) + 1);
        }

        int k = 0;
        for (int a : nums2) {
            if (newMap.containsKey(a) && newMap.get(a) > 0) {
                nums1[k++] = a;
                newMap.put(a, newMap.get(a) - 1);
            }
        }

        return Arrays.copyOfRange(nums1, 0, k);
    }

    public static int[] twoSum(int[] numbers, int target) {
        int high = numbers.length - 1;
        int low = 0;
        int sum = 0;
        while (low < high) {
            sum = numbers[low] + numbers[high];
            if (sum == target) {
                return new int[] { low + 1, high + 1 };
            } else if (sum > target) {
                high--;
            } else {
                low++;
            }
        }
        return new int[2];
    }

    public static void moveZeroes2(int[] nums) {
        for (int i = 0, j = 0; j < nums.length; j++) {
            if (nums[j] != 0) {
                int temp = nums[i];
                nums[i++] = nums[j];
                nums[j] = temp;
            }
        }
    }

    public static void moveZeroes(int[] nums) {
        int[] newArr = new int[nums.length];
        int p1 = 0;
        int p2 = nums.length - 1;
        int index = 0;
        while (p1 <= p2) {
            if (nums[index] == 0) {
                newArr[p2] = nums[index];
                p2--;
            } else {
                newArr[p1] = nums[index];
                p1++;
            }
            index++;
        }
        System.out.println(Arrays.toString(newArr));
        nums = newArr;
    }

    public static int shortestDistance(String[] wordsDict, String word1, String word2) {
        int distance = 0;
        String firstWord = "";
        for (int i = 0; i < wordsDict.length; i++) {
            if (wordsDict[i].equals(word1) || wordsDict[i].equals(word2)) {
                if (!firstWord.equals("") && !wordsDict[i].equals(firstWord)) {
                    return distance;
                }
                firstWord = wordsDict[i];
            } else if (!firstWord.equals("")) {
                distance++;
            }
        }
        return distance;
    }

    public static int[] sortedSquares(int[] nums) {
        int[] newArr = new int[nums.length];
        int p1 = 0, p2 = nums.length - 1;
        int index = nums.length - 1;

        while (p1 <= p2 && index > -1) {
            if (Math.abs(nums[p1]) > nums[p2]) {
                newArr[index] = nums[p1] * nums[p1];
                p1++;
            } else {
                newArr[index] = nums[p2] * nums[p2];
                p2--;
            }
            index--;
        }
        return newArr;
    }

    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        for (int i = m + n - 1; i > -1; i--) {
            if (p2 < 0 || (p1 > -1 && nums1[p1] > nums2[p2])) {
                nums1[i] = nums1[p1--];
            } else {
                nums1[i] = nums2[p2--];
            }
        }
    }

    public static void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static int maxSubArray(int[] nums) {
        int maxCurrent = nums[0], maxGlobal = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxCurrent = Math.max(nums[i], nums[i] + maxCurrent);
            if (maxCurrent > maxGlobal)
                maxGlobal = maxCurrent;
        }
        return maxGlobal;
    }

    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> newSet = new HashSet<>();
        for (int a : nums) {
            if (newSet.contains(a))
                return true;
            else
                newSet.add(a);
        }

        return false;
    }

    public static int searchInsert(int[] nums, int target) {
        int lowerIndex = 0, upperIndex = nums.length - 1, middleIndex;
        while (lowerIndex <= upperIndex) {
            middleIndex = (lowerIndex + upperIndex) / 2;
            if (nums[middleIndex] == target) {
                return middleIndex;
            } else if (target > nums[middleIndex]) {
                lowerIndex = middleIndex + 1;
            } else {
                upperIndex = middleIndex - 1;
            }
        }
        return lowerIndex;
    }

    /*
     * public static int firstBadVersion(int n) {
     * int lowerIndex = 1;
     * int upperIndex = n;
     * while (lowerIndex <= upperIndex) {
     * n = lowerIndex + (upperIndex - lowerIndex) / 2;
     * if (isBadVersion(n)) {
     * upperIndex = n - 1;
     * } else {
     * lowerIndex = n + 1;
     * }
     * }
     * return lowerIndex;
     * }
     */

    public static int binarySearch(int[] arr, int k) {
        int lowerIndex = 0;
        int upperIndex = arr.length - 1;
        int middleIndex;
        while (lowerIndex <= upperIndex) {
            middleIndex = (lowerIndex + upperIndex) / 2;
            if (arr[middleIndex] == k) {
                return middleIndex;
            } else if (k > arr[middleIndex]) {
                lowerIndex = middleIndex + 1;
            } else {
                upperIndex = middleIndex - 1;
            }
        }
        return -1;
    }

    public static String[] uncommonFromSentence(String s1, String s2) {
        HashMap<String, Integer> newMap = new HashMap<>();
        ArrayList<String> newList = new ArrayList<>();
        for (String s : s1.split(" "))
            newMap.put(s, newMap.getOrDefault(s, 0) + 1);
        for (String s : s2.split(" "))
            newMap.put(s, newMap.getOrDefault(s, 0) + 1);
        for (String a : newMap.keySet()) {
            if (newMap.get(a) == 1) {
                newList.add(a);
            }
        }
        return newList.toArray(new String[0]);
    }

    public static int missingNumber(int[] arr) {
        int increment = (arr[arr.length - 1] - arr[0]) / arr.length;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] + increment != arr[i + 1]) {
                return arr[i] + increment;
            }
        }
        return arr[0];
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Problem is not finished
        if (list1 == null && list2 == null) {
            return null;
        }
        ListNode tempNode = null;
        ListNode mergedList = list1;
        while (mergedList != null) {
            if (list2 != null && (list2.val == mergedList.val || list2.val == mergedList.val + 1)) {
                tempNode = mergedList.next;
                mergedList.next = list2;
                mergedList = list2;
                mergedList.next = tempNode;
            } else {
                mergedList = mergedList.next;
            }
        }
        return list1;
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode firstHalf = head;
        ListNode secondHalf = reverseList(slow.next);

        while (firstHalf != null && secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                return false;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        return true;
    }

    public ListNode reverse(ListNode head) {
        ListNode previous = null;
        ListNode temp = null;
        while (head != null) {
            temp = head.next;
            head.next = previous;
            previous = head;
            head = temp;
        }
        return previous;
    }

    public ListNode reverseList(ListNode head) {
        ListNode previous = null;
        ListNode current = head;
        ListNode temp = null;
        while (current != null) {
            temp = current.next;
            current.next = previous;
            previous = current;
            current = temp;
        }
        return previous;
    }

    public static int lengthOfLastWord(String s) {
        int length = 0;
        int index = s.length() - 1;
        while (index > -1 && !Character.isLetter(s.charAt(index))) {
            index--;
        }
        while (index > -1 && Character.isLetter(s.charAt(index))) {
            length++;
            index--;
        }
        return length;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return null;
        ListNode tempNode = head;
        while (tempNode.next != null) {
            if (tempNode.val == tempNode.next.val) {
                tempNode.next = tempNode.next.next;
            } else {
                tempNode = tempNode.next;
            }
        }
        return head;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head, fast = head;

        while (n-- > 0) {
            fast = fast.next;
        }

        if (fast == null)
            return null;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        return head;
    }

    public ListNode deleteMiddle(ListNode head) {
        if (head.next == null || head == null)
            return null;
        ListNode slow, fast, prev;
        slow = fast = prev = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = slow.next;
        return head;
    }

    public static ListNode swapNodes(ListNode head, int k) {
        k--;
        ListNode tempNode = head;
        while (k > 0) {
            tempNode = tempNode.next;
            k--;
        }

        ListNode fast = tempNode;
        ListNode slow = head;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        int temp = tempNode.val;
        tempNode.val = slow.val;
        slow.val = temp;

        return head;
    }

    public static void deleteNode(ListNode node) {
        while (node.next.next != null) {
            node.val = node.next.val;
            node = node.next;
        }
        node.next = null;
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> newMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (newMap.containsKey(nums[i]) && i - newMap.get(nums[i]) <= k) {
                return true;
            } else {
                newMap.put(nums[i], i);
            }
        }
        return false;
    }

    public static char findTheDifference(String s, String t) {
        int sum = 0;
        int i = 0;

        for (; i < s.length(); i++) {
            sum -= s.charAt(i);
            sum += t.charAt(i);
        }
        sum += t.charAt(i);
        return (char) (sum);
    }

    public static boolean containsDuplicates(int[] nums) {
        Set<Integer> newSet = new HashSet<>();
        for (int a : nums) {
            if (newSet.contains(a))
                return true;
            newSet.add(a);
        }
        return false;
    }

    public static int removeDuplicates(int[] nums) {
        int unique = 0;
        for (int i = 0; i < nums.length; i++) {
            if (unique == 0 || nums[i] > nums[i - 1]) {
                nums[unique] = nums[i];
                unique++;
            }
        }
        return unique;
    }

    public static boolean isPalindrome2(String s) {
        int leftIndex = 0;
        int rightIndex = s.length() - 1;
        while (leftIndex < rightIndex) {
            if (!Character.isLetterOrDigit(s.charAt(leftIndex)))
                leftIndex++;
            else if (!Character.isLetterOrDigit(s.charAt(rightIndex)))
                rightIndex--;
            else if (s.toLowerCase().charAt(leftIndex) != s.toLowerCase().charAt(rightIndex))
                return false;
            else {
                leftIndex++;
                rightIndex--;
            }
        }
        return true;
    }

    public static List<String> fizzBuzz(int n) {
        String[] tempArr = new String[n];
        for (int i = 0; i < tempArr.length; i++) {
            if ((i + 1) % 3 == 0 && (i + 1) % 5 == 0) {
                tempArr[i] = "FizzBuzz";
            } else if ((i + 1) % 3 == 0) {
                tempArr[i] = "Fizz";
            } else if ((i + 1) % 5 == 0) {
                tempArr[i] = "Buzz";
            } else {
                tempArr[i] = String.valueOf(i + 1);
            }
        }
        return Arrays.asList(tempArr);
    }

    public static boolean isValid(String s) {
        Stack<Character> newStack = new Stack<>();
        for (char a : s.toCharArray()) {
            if (a == '(' || a == '{' || a == '[') {
                newStack.push(a);
            } else if (a == ')' && (newStack.isEmpty() || newStack.pop() != '(')) {
                return false;
            } else if (a == '}' && (newStack.isEmpty() || newStack.pop() != '{')) {
                return false;
            } else if (a == ']' && (newStack.isEmpty() || newStack.pop() != '[')) {
                return false;
            }
        }
        return newStack.isEmpty();
    }

    public static String getSmallestAndLargest2(String s, int k) {
        String smallest = s.substring(0, k);
        String largest = s.substring(0, k);

        for (int i = 0; i < s.length() - k + 1; i++) {
            if (s.substring(i, i + k).compareTo(smallest) < 0)
                smallest = s.substring(i, i + k);
            else if (s.substring(i, i + k).compareTo(largest) > 0)
                largest = s.substring(i, i + k);
        }
        return smallest + "\n" + largest;

    }

    public static String getSmallestAndLargest(String s, int k) {
        String smallest = "";
        String largest = "";
        List<String> tempList = new ArrayList<>();
        String tempString;
        int tempCount;
        for (int i = 0; i < s.length() - k + 1; i++) {
            tempString = "";
            tempCount = 0;
            for (int j = i; j < s.length(); j++) {
                tempString += s.charAt(j);
                tempCount++;
                if (tempCount == k)
                    break;
            }
            tempList.add(tempString);
        }
        String[] subStrings = tempList.toArray(new String[0]);
        Arrays.sort(subStrings);
        smallest = subStrings[0];
        largest = subStrings[subStrings.length - 1];
        return smallest + "\n" + largest;
    }

    static class Prime {
        void checkPrime(int... intArgs) {
            for (int a : intArgs) {
                if (isPrime(a)) {
                    System.out.print(a + " ");
                }
            }
            System.out.println();
        }

        boolean isPrime(int n) {
            if (n == 2)
                return true;
            if (n < 2 || n % 2 == 0)
                return false;
            for (int i = 3; i <= (int) Math.sqrt(n); i += 2) {
                if (n % i == 0)
                    return false;
            }
            return true;
        }
    }

    public static boolean isValidIPAddress2(String ip) {
        if (ip.charAt(0) == '.' || ip.charAt(ip.length() - 1) == '.')
            return false;
        String[] tempArr = ip.split("\\.");
        if (tempArr.length != 4)
            return false;
        for (int i = 0; i < tempArr.length; i++) {
            if (String.valueOf(tempArr[i]).length() > 3)
                return false;
            else {
                try {
                    int tempInt = Integer.parseInt(tempArr[i]);
                    if (tempInt < 0 || tempInt > 255)
                        return false;
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isValidIPAddress(String ip) {
        if (ip == null)
            return false;
        String zeroTo255 = "(\\d{1,2}|(0|1)\\" + "d{2}|2[0-4]\\d|25[0-5])";
        String regex = zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255;
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(ip);
        return m.matches();
    }

    static class Add {
        public void add(int... intArgs) {
            int sum = 0;
            String separator = "";
            for (int a : intArgs) {
                sum += a;
                System.out.print(separator + a);
                separator = "+";
            }
            System.out.println("=" + sum);
        }
    }

    public static boolean stackProblem(String s) {
        Stack<Character> newStack = new Stack<>();
        for (char a : s.toCharArray()) {
            if (a == '(' || a == '[' || a == '{')
                newStack.push(a);
            else if (a == ')' && (newStack.isEmpty() || newStack.pop() != '('))
                return false;
            else if (a == '}' && (newStack.isEmpty() || newStack.pop() != '{'))
                return false;
            else if (a == ']' && (newStack.isEmpty() || newStack.pop() != '['))
                return false;
        }
        return newStack.isEmpty();
    }

    public static boolean isUndulating(int n) {
        if (String.valueOf(n).length() < 3)
            return false;
        Set<Character> tempSet = new HashSet<>();
        for (char a : String.valueOf(n).toCharArray())
            tempSet.add(a);
        if (tempSet.size() != 2)
            return false;
        char[] tempArr = String.valueOf(n).toCharArray();
        for (int i = 0; i < tempArr.length - 1; i++) {
            if (tempArr[i] == tempArr[i + 1])
                return false;
        }
        return true;
    }

    public static int nextNumber(int num) {
        char temp;
        int swapIndex;
        char[] tempArr = String.valueOf(num).toCharArray();
        int index = tempArr.length - 1;
        try {
            while (tempArr[index] <= tempArr[index - 1])
                index--;
        } catch (Exception e) {
            return num;
        }
        swapIndex = index - 1;
        temp = tempArr[swapIndex];
        index = tempArr.length - 1;
        while (tempArr[index] < temp)
            index--;
        tempArr[swapIndex] = tempArr[index];
        tempArr[index] = temp;
        String tempString = new StringBuilder(String.valueOf(tempArr).substring(swapIndex + 1)).reverse().toString();
        String firstPart = String.valueOf(tempArr).substring(0, swapIndex + 1);
        return Integer.parseInt(firstPart + tempString);

    }

    public static String replace_nth(String txt, int nth, String old_char, String new_char) {
        if (nth < 1)
            return txt;
        int occurrenceCount = 0;
        char[] tempArr = txt.toCharArray();
        for (int i = 0; i < tempArr.length; i++) {
            if (tempArr[i] == old_char.charAt(0)) {
                occurrenceCount++;
            }
            if (tempArr[i] == old_char.charAt(0) && (occurrenceCount % nth == 0)) {
                tempArr[i] = new_char.charAt(0);
            }
        }
        return String.valueOf(tempArr);
    }

    public static Object[] sortIt(Object[] arr) {
        int tempNum1;
        int tempNum2;
        boolean swapped;
        Object temp;
        for (int i = 0; i < arr.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].getClass().isArray()) {
                    tempNum1 = (int) Array.get(arr[j], 0);
                } else {
                    tempNum1 = (int) arr[j];
                }
                if (arr[j + 1].getClass().isArray()) {
                    tempNum2 = (int) Array.get(arr[j + 1], 0);
                } else {
                    tempNum2 = (int) arr[j + 1];
                }
                if (tempNum1 > tempNum2) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
        return arr;
    }

    public static int countLoneOnes(long n) {
        char[] charArr = String.valueOf(n).toCharArray();
        int oneCount = 0;
        if (charArr.length == 1 && charArr[0] == '1')
            return 1;
        for (int i = 1; i < charArr.length - 1; i++) {
            if (charArr[i] == '1' && charArr[i] != charArr[i + 1] && charArr[i] != charArr[i - 1]) {
                oneCount++;
            }
        }
        if (charArr[charArr.length - 1] == '1' && charArr[charArr.length - 2] != '1')
            oneCount++;
        if (charArr[0] == '1' && charArr[1] != '1')
            oneCount++;
        return oneCount;
    }

    public static int lcmOfArray(int[] arr) {
        int lcmInt = LCM(arr[0], arr[1]);
        for (int i = 0; i < arr.length; i++) {

        }

        return lcmInt;
    }

    public static int[] primeFactorization(int n) {
        List<Integer> factorList = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                factorList.add(i);
                n /= i;
            }
        }
        return factorList.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int LCM(int a, int b) {
        return (a * b) / GCF(a, b);
    }

    public static int GCF(int a, int b) {
        return b == 0 ? a : (GCF(b, a % b));
    }

    public static int lcmOfThree(int[] arr) {
        return LCM(LCM(arr[0], arr[1]), arr[2]);
    }

    public static int countUniqueBooks(String str, char bookend) {
        Set<Character> newSet = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == bookend) {
                i++;
                while (str.charAt(i) != bookend) {
                    newSet.add(str.charAt(i));
                    i++;
                }
            }
        }
        return newSet.size();
    }

    public static int countUniqueBooks2(String str, char bookend) {
        int bookCount = 0;
        int index = 0;
        while (str.indexOf(bookend, index) > -1) {
            System.out.println(str.indexOf(bookend, index));
            index = str.indexOf(bookend, index) + 1;
        }

        return bookCount;
    }

    public static boolean bestFriend(String s, String a, String b) {
        String[] tempArr = s.split(" ");
        for (int i = 0; i < tempArr.length; i++) {
            for (int j = 0; j < tempArr[i].length() - 1; j++) {
                if (tempArr[i].charAt(tempArr[i].length() - 1) == a.charAt(0)) {
                    return false;
                } else if (tempArr[i].charAt(j) == a.charAt(0) && tempArr[i].charAt(j + 1) != b.charAt(0)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static String extendVowels(String w, int n) {
        if (!Character.isDigit(String.valueOf(n).charAt(0)))
            return "invalid";
        String tempString = "";
        for (int i = 0; i < w.length(); i++) {
            if (w.substring(i, i + 1).matches("[AEIOUaeiou]")) {
                for (int j = 0; j < n + 1; j++) {
                    tempString += w.substring(i, i + 1);
                }
            } else {
                tempString += w.substring(i, i + 1);
            }
        }
        return tempString;
    }

    public static int[] reversibleInclusiveList(int start, int end) {
        int[] newArr = new int[Math.abs(end - start) + 1];
        int index = 0;
        while (index < newArr.length) {
            if (start < end) {
                newArr[index] = start;
                start++;
            } else {
                newArr[index] = start;
                start--;
            }
            index++;
        }
        return newArr;
    }

    public static String replaceThe(String str) {
        String[] tempArr = str.split(" ");
        for (int i = 0; i < tempArr.length - 1; i++) {
            char ch = tempArr[i + 1].charAt(0);
            if (tempArr[i].equals("the")) {
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    tempArr[i] = "an";
                } else {
                    tempArr[i] = "a";
                }
            }
        }
        return String.join(" ", tempArr);
    }

    public static int dayOfYear(String date) {
        String[] tempArr = date.split("/");
        int month = Integer.valueOf(tempArr[0]);
        int day = Integer.valueOf(tempArr[1]);
        int year = Integer.valueOf(tempArr[2]);
        int dayVar = 0;
        int[] daysArr = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        for (int i = 0; i < month - 1; i++)
            dayVar += daysArr[i];
        dayVar += day;
        return (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
                && (month > 2) ? dayVar + 1 : dayVar;
    }

    public static long numbersNeedFriendsToo(int n) {
        String tempString = "";
        String[] tempArr = String.valueOf(n).split("");
        if (!tempArr[0].equals(tempArr[1])) {
            tempString += tempArr[0] + tempArr[0] + tempArr[0];
        } else {
            tempString += tempArr[0];
        }
        for (int i = 1; i < tempArr.length - 1; i++) {
            if (tempArr[i].equals(tempArr[i + 1]) || tempArr[i].equals(tempArr[i - 1])) {
                tempString += tempArr[i];
            } else {
                tempString += tempArr[i] + tempArr[i] + tempArr[i];
            }

        }
        if (!tempArr[tempArr.length - 1].equals(tempArr[tempArr.length - 2])) {
            tempString += tempArr[tempArr.length - 1] + tempArr[tempArr.length - 1]
                    + tempArr[tempArr.length - 1];
        } else {
            tempString += tempArr[tempArr.length - 1];
        }
        return Long.valueOf(tempString);
    }

    public static int WeeklySalary(int[] hours) {
        int totalSalary = 0;
        for (int i = 0; i < hours.length; i++) {
            int remainder = hours[i] % 8;
            if (hours[i] < 9) {
                if (i < 5) {
                    totalSalary += (hours[i] * 10);
                } else {
                    totalSalary += (hours[i] * 20);
                }
            } else {
                if (i < 5) {
                    totalSalary += (remainder * 15) + ((hours[i] - remainder) * 10);
                } else {
                    totalSalary += (remainder * 30) + ((hours[i] - remainder) * 20);
                }
            }
        }
        return totalSalary;
    }

    public static String invert(String s) {
        char[] tempArr = s.toCharArray();
        for (int i = 0; i < tempArr.length / 2; i++) {
            // tempArr[i] = reverseCase(tempArr[i]);

            char temp = reverseCase(tempArr[i]);
            tempArr[i] = reverseCase(tempArr[tempArr.length - 1 - i]);
            tempArr[tempArr.length - 1 - i] = temp;

        }
        System.out.println(Arrays.toString(tempArr));
        if (s.length() % 2 != 0) {
            tempArr[tempArr.length / 2] = reverseCase(tempArr[tempArr.length / 2]);
        }
        return String.valueOf(tempArr);
    }

    public static char reverseCase(char a) {
        return Character.isUpperCase(a) ? Character.toLowerCase(a) : Character.toUpperCase(a);
    }

    public static boolean canBuild(String str1, String str2) {
        if (str1.length() < str2.length())
            return false;
        else if (str2.isEmpty())
            return true;
        List<Character> newList1 = new ArrayList<>();
        List<Character> newList2 = new ArrayList<>();

        for (char a : str1.toCharArray())
            newList1.add(a);
        for (char a : str2.toCharArray())
            newList2.add(a);

        newList1.retainAll(newList2);

        return newList1.size() >= newList2.size();
    }

    public static String percentageChanged(String oldPrice, String newPrice) {
        double oldInt = Integer.valueOf(oldPrice.replaceAll("[^0-9.]", ""));
        double newInt = Integer.valueOf(newPrice.replaceAll("[^0-9.]", ""));
        if (oldInt < newInt) {
            double tempInt = ((newInt - oldInt) / oldInt) * 100;
            return (int) tempInt + "% increase";
        } else {
            double tempInt = ((oldInt - newInt) / oldInt) * 100;
            return (int) tempInt + "% decrease";
        }
    }

    public static String hoursPassed(String t1, String t2) {
        if (t1.equals(t2))
            return "No time has passed.";
        else if (t1.substring(t1.length() - 2).equals(t2.substring(t2.length() - 2))) {
            int tempInt = Integer.valueOf(t2.split(":")[0]) - Integer.valueOf(t1.split(":")[0]);
            return tempInt > 1 ? String.valueOf(tempInt) + " hours" : "1 hour";
        } else {
            int tempInt = (12 - Integer.valueOf(t1.split(":")[0])) + Integer.valueOf(t2.split(":")[0]);
            if (Integer.valueOf(t1.split(":")[0]) == 12)
                tempInt += 12;
            return String.valueOf(tempInt) + " hours";
        }
    }

    public static String dolladollaBills(double money) {
        int placeCount = 0;
        double checkMoney = money;
        money = Math.abs(Math.round(money * 100.0) / 100.0);
        String[] tempArr = String.format("%.2f", money).split("\\.");
        String newString = "";

        for (int i = tempArr[0].length() - 1; i > -1; i--) {
            placeCount++;
            newString += tempArr[0].charAt(i);
            if (placeCount % 3 == 0 && placeCount < tempArr[0].length()) {
                newString += ",";
            }

        }
        newString = "$" + new StringBuilder(newString).reverse().toString() + "." + tempArr[1];
        if (checkMoney < 0)
            newString = "-" + newString;
        return newString;
    }

    public static int spinAround(String[] turns) {
        // Left = +90 : Right = -90
        int degreeCount = 0;
        for (String a : turns) {
            if (a.equals("left")) {
                degreeCount += 90;
            } else {
                degreeCount -= 90;
            }
        }
        return Math.abs(degreeCount / 360);

    }

    public static String sentenceSearcher(String str, int n) {
        String tempString = "";
        String[] sentenceSplit = str.split("\\.");
        String[] wordSplit = str.split(" ");
        int wordCount = wordSplit.length;
        int tempSum = 0;
        if (Math.abs(n) > wordCount)
            n = (n % wordCount);
        if (n < 0)
            n = (wordCount - 1) + (n + 1);
        for (int i = 0; i < sentenceSplit.length; i++) {
            tempSum += sentenceSplit[i].split(" ").length;
            if (sentenceSplit[i].split(" ")[0].equals(""))
                tempSum--;
            if (tempSum > n) {
                tempString = sentenceSplit[i] + ".";
                break;
            }
        }
        return tempString.trim();
    }

    public static BigInteger lookAndSay(long n) {
        if (String.valueOf(n).length() % 2 != 0)
            return new BigInteger("-1");
        String[] tempArr = String.valueOf(n).split("");
        String tempString = "";

        for (int i = 0; i < tempArr.length; i++) {
            if (i % 2 != 0) {
                for (int j = 0; j < Integer.valueOf(tempArr[i - 1]); j++) {
                    tempString += tempArr[i];
                }
            }
        }
        return new BigInteger(tempString);
    }

    public static String rps(String s1, String s2) {
        // P = 1 S = 2 R = 3
        int intS1 = 0;
        int intS2 = 0;
        String[] tempArr = { "paper", "scissors", "rock" };
        for (int i = 0; i < tempArr.length; i++) {
            if (tempArr[i].equals(s1))
                intS1 = i + 1;
            if (tempArr[i].equals(s2))
                intS2 = i + 1;
        }

        int rspResult = intS1 - intS2;
        if (rspResult == 0)
            return "TIE";
        else if (rspResult == 1)
            return "Player 1 wins";
        else if (rspResult == -1)
            return "Player 2 wins";
        else if (rspResult == 2)
            return "Player 2 wins";
        else
            return "Player 1 wins";
    }

    public static String convertTime(String s) {
        String periodString = s.substring(s.length() - 2, s.length());
        String[] tempArr = s.split(":");
        tempArr[2] = tempArr[2].substring(0, tempArr[2].length() - 2);

        if (periodString.equals("PM") && Integer.valueOf(tempArr[0]) < 12) {
            tempArr[0] = String.valueOf(Integer.valueOf(tempArr[0]) + 12);
        } else if (periodString.equals("AM") && Integer.valueOf(tempArr[0]) == 12) {
            tempArr[0] = "00";
        }

        return String.join(":", tempArr);
    }

    public static String expandedForm(double n) {
        String[] whole = String.valueOf(n).split("\\.")[0].split("");
        String[] fractional = String.valueOf(n).split("\\.")[1].split("");
        String expandedString = "";

        for (int i = 0; i < whole.length; i++) {
            if (whole[i].equals("0"))
                continue;

            expandedString += String.valueOf(Math.round(Integer.valueOf(whole[i]) * Math.pow(10, whole.length - 1 - i)))
                    + " + ";
        }
        for (int j = 0; j < fractional.length; j++) {
            if (fractional[j].equals("0"))
                continue;
            expandedString += fractional[j] + "/" + String.valueOf(Math.round(Math.pow(10, j + 1))) + " + ";
        }
        return expandedString.substring(0, expandedString.length() - 2).trim();
    }

    public static boolean overlap(String str1, String str2) {
        String shorterString = "";
        String longerString = "";

        if (str1.length() >= str2.length()) {
            shorterString = str2.toLowerCase();
            longerString = str1.toLowerCase();
        } else {
            shorterString = str1.toLowerCase();
            longerString = str2.toLowerCase();

        }
        if ((longerString.length() - shorterString.length() == longerString.lastIndexOf(shorterString))) {
            return true;

        } else {
            String tempString = longerString.substring(longerString.length() - shorterString.length());
            for (int i = 0, j = 0; i < tempString.length() && j < shorterString.length(); i++, j++) {
                if (tempString.charAt(i) != shorterString.charAt(j)) {
                    if (tempString.charAt(i) != '*' && shorterString.charAt(j) != '*') {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public static String erase(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char a : str.toCharArray()) {
            if (a == '#') {
                if (stringBuilder.length() > 0)
                    stringBuilder.setLength(stringBuilder.length() - 1);
            } else {
                stringBuilder.append(a);
            }
        }
        return stringBuilder.toString();
    }

    public static String actualMemorySize(String ms) {
        String memorySize = "";
        String memoryType = "";

        for (int i = 0; i < ms.length(); i++) {
            if (Character.isDigit(ms.charAt(i))) {
                memorySize += ms.charAt(i);
            } else {
                memoryType += ms.charAt(i);
            }
        }

        double newSize = Integer.parseInt(memorySize) - Integer.parseInt(memorySize) * .07;
        newSize = Math.round(newSize * 100.0) / 100.0;
        System.out.println(newSize);

        return memoryType.equals("GB") ? String.valueOf(newSize) + "GB" : String.valueOf(newSize) + "MB";
    }

    public static String nearestChapter(Chapter[] chapter, int page) {
        String nearestChapter = "";
        int nearestDistance = Integer.MAX_VALUE;
        int nearestPage = 0;
        for (int i = 0; i < chapter.length; i++) {
            int tempDistance = Math.abs(page - chapter[i].getPage());
            if (tempDistance < nearestDistance) {
                nearestPage = chapter[i].getPage();
                nearestDistance = tempDistance;
                nearestChapter = chapter[i].getName();
            } else if (tempDistance == nearestDistance) {
                if (chapter[i].getPage() > nearestPage) {
                    nearestChapter = chapter[i].getName();
                    nearestPage = chapter[i].getPage();
                }
            }
        }

        return nearestChapter;
    }

    class Chapter {
        private String name;
        private int page;

        public Chapter(String name, int page) {
            this.name = name;
            this.page = page;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }
    }

    public static int findFulcrum(int[] arr) {

        int totalSum = Arrays.stream(arr).sum();
        int currIndex = 0;
        int leftSum = 0;

        while (currIndex < arr.length - 1) {
            currIndex++;
            leftSum += arr[currIndex - 1];
            if (leftSum == totalSum - leftSum - arr[currIndex])
                return arr[currIndex];

        }

        return -1;
    }

    public static boolean trouble(long num1, long num2) {

        Map<Long, Integer> newMap = new HashMap<>();
        while (num1 != 0) {
            if (newMap.containsKey(num1 % 10)) {
                newMap.put(num1 % 10, newMap.get(num1 % 10) + 1);
            } else {
                newMap.put(num1 % 10, 1);
            }
            num1 /= 10;
        }

        String[] num2Arr = String.valueOf(num2).split("");

        for (Map.Entry<Long, Integer> entry : newMap.entrySet()) {
            // System.out.println("Number : " + entry.getKey() + "Occurences : " +
            // entry.getValue());
            if (entry.getValue() >= 3) {
                for (int i = 0; i < num2Arr.length - 1; i++) {
                    if (Integer.parseInt(num2Arr[i]) == entry.getKey()
                            && Integer.parseInt(num2Arr[i + 1]) == entry.getKey()) {
                        return true;
                    }
                }
            }
        }

        return false;

    }

    public static boolean isPalindrome(String wrd) {
        if (wrd.length() <= 1)
            return true;
        else if (wrd.charAt(0) != wrd.charAt(wrd.length() - 1))
            return false;
        else
            return isPalindrome(wrd.substring(1, wrd.length() - 1));
    }

    public static boolean sameLength(String str) {
        if (str.length() == 0)
            return true;
        else if (str.equals(str.replaceAll("10", "")))
            return false;
        else
            return sameLength(str.replaceAll("10", ""));
    }

    public static int[] selReverse(int[] lst, int length) {
        if (length < 2)
            return lst;
        List<Integer> tempList = new ArrayList<>();
        int[] newArr = new int[lst.length];
        int index = 0;
        int tempCount = 0;
        for (int i = 0; i < lst.length; i++) {
            if (tempCount == length) {
                tempCount = 0;
                Collections.reverse(tempList);
                for (int a : tempList) {
                    newArr[index] = a;
                    index++;
                }
                tempList.clear();
            }
            tempList.add(lst[i]);
            tempCount++;

        }
        if (!tempList.isEmpty()) {
            Collections.reverse(tempList);
            for (int b : tempList) {
                newArr[index] = b;
                index++;
            }
        }
        return newArr;
    }

    public static String wordedMath(String expr) {
        expr = expr.toLowerCase();
        String[] tempArr = expr.split(" ");

        Map<String, Integer> newMap = new HashMap<>();
        newMap.put("zero", 0);
        newMap.put("one", 1);
        newMap.put("two", 2);
        newMap.put("three", 3);
        newMap.put("four", 4);
        int tempSum = 0;
        String wordedAnswer = "";

        if (tempArr[1].equals("plus")) {

            tempSum = newMap.get(tempArr[0]) + newMap.get(tempArr[2]);
        } else {
            tempSum = newMap.get(tempArr[0]) - newMap.get(tempArr[2]);
        }

        for (Map.Entry<String, Integer> entry : newMap.entrySet()) {

            if (entry.getValue() == tempSum) {
                wordedAnswer = entry.getKey();
            }
        }

        return wordedAnswer.substring(0, 1).toUpperCase() + wordedAnswer.substring(1);

    }

    public static String highestPair(String[] arr) {
        int highestValue = 0;
        String highestString = "";
        Map<String, Integer> newMap = new HashMap<>();
        for (String a : arr) {
            if (newMap.containsKey(a)) {
                newMap.put(a, newMap.get(a) + 1);
            } else {
                newMap.put(a, 1);
            }
        }

        for (Map.Entry<String, Integer> entry : newMap.entrySet()) {
            if (entry.getValue() > 1) {
                if (Character.isDigit(entry.getKey().charAt(0)) && entry.getValue() > highestValue) {
                    highestString = entry.getKey();
                } else if (entry.getKey().equals("A")) {
                    return "A";
                } else if (entry.getValue() > highestValue) {
                    highestString = entry.getKey();
                }

            }

        }

        return highestString;

    }

    public static String cupSwapping(String[] cups) {
        String tempString = "B";
        boolean firstDetection = false;

        for (int i = 0; i < cups.length; i++) {
            if (!firstDetection) {
                if (cups[i].contains("B")) {
                    tempString = String.valueOf(cups[i].charAt(1 - cups[i].indexOf("B")));
                }
                firstDetection = true;
            } else if (firstDetection) {
                if (cups[i].contains(tempString)) {
                    tempString = String.valueOf(cups[i].charAt(1 - cups[i].indexOf(tempString)));
                }
            }
        }

        return tempString;

    }

    public static String expand(long num) {

        String expandString = "";
        int count = 0;

        while (num != 0) {
            long tempDigit = num % 10;
            num /= 10;
            if (tempDigit != 0) {
                expandString = " + " + String.valueOf(Math.round(tempDigit * Math.pow(10, count))) + expandString;
            }
            count++;
        }

        return expandString.substring(3);

    }

    public static String[] pluralize(String[] s) {
        Map<String, Integer> newMap = new LinkedHashMap<>();
        for (int i = 0; i < s.length; i++) {
            if (newMap.containsKey(s[i])) {
                newMap.put(s[i], newMap.get(s[i]) + 1);
            } else {
                newMap.put(s[i], 1);
            }
        }
        List<String> newList = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : newMap.entrySet()) {
            if (entry.getValue() > 1) {
                newList.add(entry.getKey() + "s");
            } else {
                newList.add(entry.getKey());
            }
        }

        // System.out.print(Arrays.toString(newList.toArray(new
        // String[newList.size()])));

        return newList.toArray(new String[newList.size()]);

    }

    public static int memeSum(int a, int b) {
        if (a == 0 && b == 0)
            return 0;
        String memeString = "";
        while (a != 0 || b != 0) {
            memeString = String.valueOf(a % 10 + b % 10) + memeString;
            a /= 10;
            b /= 10;
        }
        return Integer.valueOf(memeString);
    }

    public static boolean validateCard(long num) {
        long checkDigit = num % 10;
        int checkSum = 0;
        String tempString = new StringBuilder(String.valueOf(num).substring(0, String.valueOf(num).length() - 1))
                .reverse().toString();

        String[] tempArr = tempString.split("");

        // System.out.println(Arrays.toString(tempArr));

        for (int i = 0; i < tempArr.length; i++) {

            if (i % 2 == 0) {

                int tempSum = Integer.parseInt(tempArr[i]) * 2;
                if (tempSum > 9) {
                    tempSum = tempSum % 10 + tempSum / 10;
                }
                tempArr[i] = String.valueOf(tempSum);
            }

            checkSum += Integer.parseInt(tempArr[i]);

        }
        /*
         * System.out.println(Arrays.toString(tempArr));
         * System.out.println("Check Digit = " + checkDigit);
         * System.out.println("Check Sum  = " + checkSum);
         */
        return 10 - (checkSum % 10) == checkDigit;
    }

    public static Object[] charAtPos(Object[] arr, String par) {
        List<Object> newList = new LinkedList<>();

        if (par == "even") {
            for (int i = 1; i < arr.length; i += 2)
                newList.add(arr[i]);
        } else {
            for (int i = 0; i < arr.length; i += 2)
                newList.add(arr[i]);
        }

        return newList.toArray(new Object[0]);

    }

    public static boolean canPartition(int[] arr) {
        int index = 0;

        while (index < arr.length) {
            int innerIndex = 0;
            int tempProduct = 1;
            while (innerIndex < arr.length) {
                if (index != innerIndex)
                    tempProduct *= arr[innerIndex];
                innerIndex++;

            }
            if (tempProduct == arr[index])
                return true;

            index++;
        }
        return false;
    }

    public static boolean productOfPrimes(int num) {
        boolean[] isPrime = new boolean[num + 1];
        for (int i = 0; i <= num; i++)
            isPrime[i] = true;

        for (int i = 2; i * i <= num; i++) {
            if (isPrime[i]) {
                for (int j = i; i * j <= num; j++) {
                    isPrime[i * j] = false;
                }
            }
        }
        List<Integer> primeNumbers = new LinkedList<>();
        for (int i = 2; i <= num; i++) {
            if (isPrime[i])
                primeNumbers.add(i);
        }

        HashSet<Integer> newSet = new HashSet<>();
        for (int a : primeNumbers) {
            if (num >= a && num % a == 0) {
                newSet.add(a);
                if (newSet.contains(num / a))
                    return true;
            }

        }
        return false;
    }

    public static String getMissing(String strLetters) {
        String missingLetters = "";

        Set<Character> missingSet = new HashSet<>();
        Set<Character> fullSet = new HashSet<>();
        Set<Character> difference = new HashSet<>();

        fullSet.addAll(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));

        for (char a : strLetters.toCharArray())
            missingSet.add(a);

        difference.addAll(fullSet);
        difference.removeAll(missingSet);

        for (char a : difference)
            missingLetters += a;

        return missingLetters;
    }

    public static int[] numSplit(int n) {
        int originalN = n;

        if (n < 0)
            n *= -1;
        StringBuilder tempString = new StringBuilder(String.valueOf(n)).reverse();
        int tempInt = Integer.parseInt(tempString.toString());
        int[] newArr = new int[String.valueOf(n).length()];

        String multString = String.valueOf((int) Math.pow(10, String.valueOf(n).length() - 1));

        for (int i = 0; i < newArr.length; i++) {
            int digit = (tempInt % 10) * Integer.parseInt(multString);
            if (originalN < 0)
                newArr[i] = digit * -1;
            else
                newArr[i] = digit;
            tempInt /= 10;
            multString = multString.substring(0, multString.length() - 1);
        }

        return newArr;

    }

    public static int maxProduct(int[] r) {
        Arrays.sort(r);
        int maxOne = r[r.length - 1] * r[r.length - 2] * r[r.length - 3];
        int maxTwo = r[0] * r[1] * r[r.length - 1];
        return Math.max(maxOne, maxTwo);

    }

    public static int minProduct(int[] r) {
        Arrays.sort(r);

        if (r[0] > 0)
            return r[0] * r[1] * r[2];

        int minOne = 0;
        int minTwo = 0;

        minOne = r[0] * r[1] * r[2];
        minTwo = r[0] * r[r.length - 2] * r[r.length - 1];

        return Math.min(minOne, minTwo);
    }

    public static String longestSubstring(String digits) {
        ArrayList<String> arrayList = new ArrayList<>();
        digits += "0";
        int index = 0;
        String tempString = "";
        while (index < digits.length() - 1) {
            int parityCheck = 1;

            parityCheck = Math.abs(Integer.valueOf(digits.charAt(index)) - Integer.valueOf(digits.charAt(index + 1)));
            tempString += digits.charAt(index);

            if (parityCheck % 2 == 0) {
                arrayList.add(tempString);
                tempString = "";
            }
            index++;
        }

        return Collections.max(arrayList, Comparator.comparing(String::length));
    }

    public static int minimumSwaps(int[] arr) {
        int[] tempArr = arr.clone();
        Arrays.sort(tempArr);
        int swapCount = 0;
        int tempArrIndex = 0;
        int arrIndex = 0;
        while (true && arrIndex < arr.length) {
            if (arr == tempArr) {
                break;
            }
            if (arr[arrIndex] != tempArr[tempArrIndex]) {
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] == tempArr[tempArrIndex]) {
                        int temp = arr[arrIndex];
                        arr[arrIndex] = arr[i];
                        arr[i] = temp;
                        swapCount++;
                    }
                }
            }
            tempArrIndex++;
            arrIndex++;
        }

        return swapCount;
    }

    public static int sockMerchant(int n, List<Integer> ar) {
        int numberOfPairs = 0;
        HashMap<Integer, Integer> sockMap = new HashMap<>();
        for (Integer a : ar) {
            if (sockMap.containsKey(a)) {
                sockMap.put(a, sockMap.get(a) + 1);
            } else {
                sockMap.put(a, 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : sockMap.entrySet()) {
            numberOfPairs += entry.getValue() / 2;
        }

        return numberOfPairs;
    }

    public static int countingValleys(int steps, String path) {
        char[] newArr = path.toCharArray();
        int positionTracker = 0;
        int numberOfValleys = 0;

        for (int i = 0; i < newArr.length; i++) {

            if (newArr[i] == 'U') {
                positionTracker++;
            } else {
                positionTracker--;
            }

            if (positionTracker == 0 && newArr[i] == 'U') {
                numberOfValleys++;
            }

        }

        return numberOfValleys;

    }

    public static String isBalanced(String s) {
        Stack<Character> bStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '{' || s.charAt(i) == '(' || s.charAt(i) == '[') {
                bStack.push(s.charAt(i));
            } else if (s.charAt(i) == '}') {
                if (bStack.isEmpty() || bStack.pop() != '{')
                    return "NO";
            } else if (s.charAt(i) == ']') {
                if (bStack.isEmpty() || bStack.pop() != '[')
                    return "NO";
            } else if (s.charAt(i) == ')') {
                if (bStack.isEmpty() || bStack.pop() != '(')
                    return "NO";

            }
        }

        return bStack.isEmpty() ? "YES" : "NO";

    }

    public static int sumOfVowels(String str) {
        int vowelSum = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.substring(i, i + 1).equalsIgnoreCase("A"))
                vowelSum += 4;
            else if (str.substring(i, i + 1).equalsIgnoreCase("E"))
                vowelSum += 3;
            else if (str.substring(i, i + 1).equalsIgnoreCase("I"))
                vowelSum += 1;
        }

        return vowelSum;
    }

    public static Map<String, String> mapping(String[] letters) {
        HashMap<String, String> letterMap = new HashMap<>();

        for (String a : letters)
            letterMap.put(a, a.toUpperCase());

        return letterMap;
    }

    public static int closingSum(long num) {
        int sum = 0;
        String numValue = String.valueOf(num);

        for (int i = 0; i < numValue.length() / 2; i++) {

            String tempString = String.valueOf(numValue.charAt(i))
                    + String.valueOf(numValue.charAt(numValue.length() - 1 - i));

            sum += Integer.valueOf(tempString);

        }

        if (numValue.length() % 2 != 0)
            sum += Character.getNumericValue(numValue.charAt(numValue.length() / 2));

        return sum;
    }

    public static String rearrange(String s) {

        String[] newArr = s.split(" ");

        String[] rearrangedArr = new String[newArr.length];

        for (int i = 0; i < newArr.length; i++) {
            for (int j = 0; j < newArr[i].length(); j++) {
                if (Character.isDigit(newArr[i].charAt(j))) {
                    rearrangedArr[Character.getNumericValue(newArr[i].charAt(j)) - 1] = newArr[i].substring(0, j)
                            + newArr[i].substring(j + 1);
                }
            }

        }

        return String.join(" ", rearrangedArr);

    }

    public static int[] returnInts(Object[] arr) {
        ArrayList<Integer> intList = new ArrayList<>();

        for (Object a : arr) {
            if (a instanceof Integer) {
                intList.add((int) a);
            }
        }
        return intList.stream().mapToInt(Integer::intValue).toArray();
    }

    public static boolean winRound(int[] you, int[] opp) {

        Arrays.sort(you);
        Arrays.sort(opp);

        String tempString1 = String.valueOf(you[you.length - 1]) + String.valueOf(you[you.length - 2]);
        String tempString2 = String.valueOf(opp[opp.length - 1]) + String.valueOf(opp[opp.length - 2]);

        return Integer.valueOf(tempString1) > Integer.valueOf(tempString2);

    }

    public static int pickingNumbers(int[] newArr) {
        // int[] newArr = a.stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(newArr);
        System.out.println(Arrays.toString(newArr));

        String tempString = "";
        int longestSubArr = 0;

        for (int i = 0; i < newArr.length - 1; i++) {
            if (newArr[i + 1] - newArr[i] <= 1 && Character.getNumericValue(tempString.charAt(tempString.length() - 1))
                    - Character.getNumericValue(tempString.charAt(0)) <= 1) {
                tempString += String.valueOf(newArr[i]);
            }

        }

        return longestSubArr;

    }

    public static String timeConversion(String s) {
        String[] timeArr = s.split(":");

        if (timeArr[2].charAt(2) == 'P' && Integer.valueOf(timeArr[0]) != 12) {
            timeArr[0] = String.valueOf(Integer.valueOf(Integer.valueOf(timeArr[0]) + 12));
        } else if (timeArr[2].charAt(2) == 'A' && Integer.valueOf(timeArr[0]) == 12) {
            timeArr[0] = "00";
        }

        timeArr[2] = timeArr[2].substring(0, 2);
        return String.join(":", timeArr);
    }

}
