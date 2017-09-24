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


    def lengthOfLongestSubstring_v2(self, s):
        """
        use of len can be slow, use a dict instead
        """
        char_to_idx = {}
        max_len = 0
        idx_start = 0  # start idx of the non-repeated substring
        for idx, c in enumerate(s):
            if c in char_to_idx and idx_start <= char_to_idx[c]:  # if found a duplication
                idx_start = char_to_idx[c] + 1  # update substring but cutting the previous part
            char_to_idx[c] = idx
            max_len = max(max_len, idx - idx_start + 1)  # cur_length = idx - idx_start + 1
        return max_len

