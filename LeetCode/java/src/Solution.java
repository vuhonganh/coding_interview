import java.lang.reflect.Array;
import java.util.*;

class Solution {
    //Definition for singly-linked list.
    public class ListNode
    {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2)
    {
        ListNode cursor1 = l1;
        ListNode cursor2 = l2;
        ListNode res = new ListNode(0);
        ListNode cursorRes = res;
        int carry = 0;
        while (cursor1 != null || cursor2 != null || carry != 0)
        {
            int cur_val = cursor1 != null ? cursor1.val : 0;
            cur_val += cursor2 != null ? cursor2.val : 0;
            cur_val += carry;
            carry = cur_val / 10;
            cur_val = cur_val % 10;
            cursorRes.next = new ListNode(cur_val);
            cursorRes = cursorRes.next;
            if (cursor1 != null) cursor1 = cursor1.next;
            if (cursor2 != null) cursor2 = cursor2.next;
        }
        return res.next;
    }
    public int lengthOfLongestSubstring(String s)
    {
        HashMap<Character, Integer> charToIdx = new HashMap<Character, Integer>();
        int startIndex = 0;
        int maxLen = 0;
        for (int i = 0; i < s.length(); ++i)
        {
            // update the substring if found a duplication
            char cur = s.charAt(i);
            if (charToIdx.containsKey(cur) && startIndex <= charToIdx.get(cur))  // duplication found
            {
                startIndex = charToIdx.get(cur) + 1;
            }
            charToIdx.put(cur, i);
            maxLen = (i + 1 - startIndex > maxLen) ? i + 1 - startIndex : maxLen;
        }
        return maxLen;
    }


    public int reverse(int x)
    {
        // check if x > 0 or x < 0
        // x > 0: -> check overflow when comparing to Integer.MAX_VALUE

        String xStr = Integer.toString(x);
        String xRev = new StringBuffer(xStr).reverse().toString();
        for (char c: xStr.toCharArray())
        {
            System.out.println(c);
        }
        return -1;
    }

    public int maxArea(int[] height)
    {
        // move from 2 ends to the middle by this strategy: if ai < aj (j > i), then move left end to i+1
        // if aj < ai then move right end to j-1
        // correctness: suppose max area is at i* and j*. Prove that this strategy will reach this point
        // by followwing this strategy, we will absolutely reach one of the 2 ends at some point: i* or j*.
        // When we reach one end, the strategy will make the other end move to the optimal -> done
        int low = 0;
        int high = height.length - 1;
        int maxArea = java.lang.Math.min(height[high], height[low]) * (high - low);
        while (high > low)
        {

            int curHeightContainer = java.lang.Math.min(height[high], height[low]);
            while (height[high] <= curHeightContainer && high > low)
            {
                high -= 1;
            }
            while (height[low] <= curHeightContainer && high > low)
            {
                low += 1;
            }

            int curArea = (high - low) * java.lang.Math.min(height[high], height[low]);
            maxArea = java.lang.Math.max(curArea, maxArea);
        }
        return maxArea;
    }

    public String longestCommonPrefix_v1(String[] strs)
    {
        // base case
        if (strs.length == 1) return strs[0];
        if (strs.length == 0) return "";

        int i = 0;  // common index (exclusive)
        int minLen = Integer.MAX_VALUE;
        for (String s: strs)
        {
            minLen = java.lang.Math.min(s.length(), minLen);
        }
        while (i < minLen)
        {
            Character c = null;
            int cnt = 0;
            for (String s: strs)
            {
                if (cnt == 0)
                {
                    c = s.charAt(i);
                }
                else
                {
                    if (c != s.charAt(i))
                    {
                        return s.substring(0, i);
                    }
                }
                cnt += 1;
            }
            i += 1;
        }
        return strs[0].substring(0, i);
    }

    public String longestCommonPrefix(String[] strs)  // faster version
    {
        // base case
        if (strs == null || strs.length == 0) return "";
        // idea is as follows: take the first string in the array as cur_prefix
        // for each other word: chop off the last character of cur_prefix until it becomes the prefix for this word
        String prefix = strs[0];
        int idxWord = 1;
        while (idxWord < strs.length)
        {
            while (strs[idxWord].indexOf(prefix) != 0)  // when it's not prefix
            {
                prefix = prefix.substring(0, prefix.length() - 1);  // chop off the last char
            }
            idxWord++;
        }
        return prefix;
    }

    public List<List<Integer>> threeSum(int[] nums)
    {
        Arrays.sort(nums);  // note that java sorts in-place
        // for each entry, do 2sum on the rest of the sorted array
        List<List<Integer>> res = new LinkedList<>();
        for (int j = 0; j < nums.length - 2; ++j)
        {
            if (j == 0 || (j > 0 && nums[j] != nums[j - 1]))
            {
                int cur = nums[j];
                if (cur > 0) break;
                int lo = j + 1;
                int hi = nums.length - 1;
                while (hi > lo)
                {
                    int highNum = nums[hi];
                    int lowNum = nums[lo];
                    if (highNum + lowNum + cur < 0) lo++;
                    else if (highNum + lowNum + cur > 0) hi--;
                    else
                    {
                        System.out.println("asdf");
                        res.add(Arrays.asList(cur, lowNum, highNum));
                        // THIS IS CRUCIAL: ALWAYS UPDATE BEFORE MOVING ON
                        lo++;
                        hi--;
                        // DONE UPDATING, NOW SKIP DUPLICATES
                        while (hi > lo && nums[hi] == nums[hi + 1]) hi--;
                        while (hi > lo && nums[lo] == nums[lo - 1]) lo++;
                    }
                }
            }
        }
        return res;
    }

    public int threeSumClosest_v1(int[] nums, int target)  // slow because of binary search
    {
        Arrays.sort(nums);
        int sumClosest = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; ++i)  // duplicate is ok since we are finding closest value
        {
            for (int j = i + 1; j < nums.length - 1; ++j)
                {
                    int toSearch = target - nums[i] - nums[j];
                    int iC = Arrays.binarySearch(nums, toSearch);  // return idx of the first elem >= toSearch if found
                    iC = (iC < 0) ? 0 - (iC + 1) : iC;  // when not found, return -(insertion_point + 1)
//                    System.out.println(toSearch);
//                    System.out.println(iC);
                    if (iC < j + 2)  // already cover this or the diff will always increase -> opt was found
                    {
                        iC = j + 1;
                        int curSum = nums[i] + nums[j] + nums[iC];
                        int curDiff = Math.abs(curSum - target);
                        int optDiff = Math.abs(sumClosest - target);
                        sumClosest = (curDiff < optDiff) ? curSum : sumClosest;
                        // if sumClosest > target, continue only makin it further to the target
                        if (sumClosest > target) break;
                    }
                    else
                    {
                        // check current and below one index
                        if (iC < nums.length)
                        {
                            int curSum = nums[i] + nums[j] + nums[iC];
                            int curDiff = Math.abs(curSum - target);
                            int optDiff = Math.abs(sumClosest - target);
                            sumClosest = (curDiff < optDiff) ? curSum : sumClosest;
                        }
                        int curSum = nums[i] + nums[j] + nums[iC - 1];
                        int curDiff = Math.abs(curSum - target);
                        int optDiff = Math.abs(sumClosest - target);
                        sumClosest = (curDiff < optDiff) ? curSum : sumClosest;
                    }
                }
        }
        return sumClosest;
    }

    public List<List<Integer>> fourSum(int[] nums, int target)
    {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if (len < 4) return res;
        for (int i = 0; i < len - 3; ++i)
        {
            if (nums[i] + nums[len - 3] + nums[len - 2] + nums[len - 1] < target) continue;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < len - 2; ++j)
            {
                if (nums[i] + nums[j] + nums[len - 2] + nums[len - 1] < target) continue;
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int twoFirstSum = nums[i] + nums[j];
                int idLo = j + 1;
                int idHi = len - 1;
                while (idLo < idHi)
                {
                    int curSum = twoFirstSum + nums[idLo] + nums[idHi];
                    if (curSum == target)
                    {
                        res.add(Arrays.asList(nums[i], nums[j], nums[idLo], nums[idHi]));
                        idLo++;
                        idHi--;
                        while (idLo < idHi && nums[idLo] == nums[idLo - 1])
                        {
                            idLo++;
                        }
                        while (idLo < idHi && nums[idHi] == nums[idHi + 1])
                        {
                            idHi--;
                        }
                    }
                    else if (curSum < target)
                    {
                        idLo++;
                        while (idLo < idHi && nums[idLo] == nums[idLo - 1])
                        {
                            idLo++;
                        }
                    }
                    else
                    {
                        idHi--;
                        while (idLo < idHi && nums[idHi] == nums[idHi + 1])
                        {
                            idHi--;
                        }
                    }
                }
            }
        }
        return res;
    }

    public int threeSumClosest(int[] nums, int target)
    {
        Arrays.sort(nums);
        int sumClosest = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; ++i)
        {
            int idLo = i + 1;
            int idHi = nums.length - 1;
            while(idLo < idHi)
            {
                // calculate and update this time first
                int curSum = nums[i] + nums[idLo] + nums[idHi];
                int curDiff = Math.abs(curSum - target);
                int optDiff = Math.abs(sumClosest - target);
                sumClosest = (curDiff < optDiff) ? curSum : sumClosest;

                // now compute next calculation
                if (curSum == target)  // early stopping
                {
                    return target;
                }
                else if (curSum < target)
                {
                    idLo++;
                    while(idLo < idHi && nums[idLo] == nums[idLo - 1]) idLo++;  // stop at the first different
                }
                else
                {
                    idHi--;
                    while(idLo < idHi && nums[idHi] == nums[idHi + 1]) idHi--;  // stop at the first different
                }
            }
        }
        return sumClosest;
    }

    public ListNode removeNthFromEnd(ListNode head, int n)
    {
        // remove the nth node from the end of linked list and return its head.
        // find length
        int len = 0;
        ListNode cur = head;
        ListNode cur2 = head;
        while (cur != null)
        {
            cur = cur.next;
            len++;
        }
        if (len == 1 || n == len) return head.next;


        for (int i = 0; i < len - n - 1; ++i)
        {
            cur2 = cur2.next;
        }

        cur2.next = cur2.next.next;
        return head;
    }

    public boolean isValid(String s)
    {
        if (s.length() % 2 == 1) return false;
        Stack<Character> q = new Stack<>();
        for (char c : s.toCharArray())
        {
            if (c == '[' || c == '(' || c == '{')
            {
                q.push(c);
            }
            else
            {
                if (q.empty()) return false;
                switch (c)
                {
                    case ')':
                        if (q.peek() != '(') return false;
                        break;
                    case ']':
                        if (q.peek() != '[') return false;
                        break;
                    case '}':
                        if (q.peek() != '{') return false;
                        break;
                    default:
                        return false;
                }
                q.pop();
            }
        }
        return q.empty();
    }

    public ListNode mergeTwoLists_v1(ListNode l1, ListNode l2)  // can be done faster by pointing directly rather than updating val
    {
        if (l1 == null && l2 == null) return null;  // deal with base case
        ListNode res = new ListNode(0);
        ListNode cursorRes = res;
        while ((l1 != null) || (l2 != null))
        {
            if (l1 != null && l2 != null)
            {
                if (l1.val < l2.val)
                {
                    cursorRes.val = l1.val;
                    l1 = l1.next;
                }
                else
                {
                    cursorRes.val = l2.val;
                    l2 = l2.next;
                }
            }
            else if (l1 != null)
            {
                cursorRes.val = l1.val;
                l1 = l1.next;
            }
            else
            {
                cursorRes.val = l2.val;
                l2 = l2.next;
            }

            if (l1 != null || l2 != null) cursorRes.next = new ListNode(0);
            cursorRes = cursorRes.next;
        }
        return res;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2)  // faster version
    {
        ListNode res = new ListNode(0);
        ListNode cursorRes = res;
        while (l1 != null && l2 != null)
        {
            if (l1.val < l2.val)
            {
                cursorRes.next = l1;
                l1 = l1.next;
            }
            else
            {
                cursorRes.next = l2;
                l2 = l2.next;
            }
            cursorRes = cursorRes.next;
        }
        if (l1 != null)
        {
            cursorRes.next = l1;
        }
        else
        {
            cursorRes.next = l2;
        }
        return res.next;
    }

    public static void main(String[] args)
    {
        Solution s = new Solution();
        String[] strs = {"a", "ab"};
//        System.out.println(s.longestCommonPrefix(strs));
        System.out.println("abc".indexOf(""));
        System.out.println("hello");
        int[] test = {-1,0,1,2,-1,-4};
        System.out.println(s.threeSum(test));
        int[] testThreeSumClosest = {43,75,-90,47,-49,72,17,-31,-68,-22,-21,-30,65,88,-75,23,97,-61,53,87,-3,33,20,51,-79,43,80,-9,34,-89,-7,93,43,55,-94,29,-32,-49,25,72,-6,35,53,63,6,-62,-96,-83,-73,66,-11,96,-90,-27,78,-51,79,35,-63,85,-82,-15,100,-82,1,-4,-41,-21,11,12,12,72,-82,-22,37,47,-18,61,60,55,22,-6,26,-60,-42,-92,68,45,-1,-26,5,-56,-1,73,92,-55,-20,-43,-56,-15,7,52,35,-90,63,41,-55,-58,46,-84,-92,17,-66,-23,96,-19,-44,77,67,-47,-48,99,51,-25,19,0,-13,-88,-10,-67,14,7,89,-69,-83,86,-70,-66,-38,-50,66,0,-67,-91,-65,83,42,70,-6,52,-21,-86,-87,-44,8,49,-76,86,-3,87,-32,81,-58,37,-55,19,-26,66,-89,-70,-69,37,0,19,-65,38,7,3,1,-96,96,-65,-52,66,5,-3,-87,-16,-96,57,-74,91,46,-79,0,-69,55,49,-96,80,83,73,56,22,58,-44,-40,-45,95,99,-97,-22,-33,-92,-51,62,20,70,90};
        System.out.println(s.threeSumClosest(testThreeSumClosest, 284));

    }

}