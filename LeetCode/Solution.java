import java.util.HashMap;

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
    public static void main(String[] args)
    {
        int[] height = {1, 1};
        Solution s = new Solution();
        int a = s.maxArea(height);
        System.out.println(a);
    }

}