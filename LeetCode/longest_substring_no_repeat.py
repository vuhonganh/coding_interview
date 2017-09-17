class Solution(object):
    def lengthOfLongestSubstring(self, s):
        """
        :type s: str
        :rtype: int
        """
        max_len = 0
        cur_queue = []
        
        for c in s:        
            if c in cur_queue:
                idx_dup = cur_queue.index(c)
                cur_queue = cur_queue[idx_dup + 1 :]
            cur_queue.append(c)
            max_len = max(max_len, len(cur_queue))         
        return max_len