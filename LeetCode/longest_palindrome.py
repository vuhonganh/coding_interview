def longest_common_substring(s1, s2):
	# calculate LCSsuffix(p, q) is the longest common substring that has the same end at s1[0:p] and s2[0:q]
	# memoray table dp[p, q] = LCSsuffix(s1[0:p], s2[0:q])
	# LCSsuffix(s1[0:p], s2[0:q]) = LCSsuffix(s1[0:p-1], s2[0:q-1]) + 1 if s1[p] == s2[q]
	max_len = 0
	max_str = ''
	dp = [[0 for i in range(len(s2) + 1)] for j in range(len(s1) + 1)]
	for p in range(1, len(s1) + 1):
		ip = p - 1  # index of char p-th in s1 (python is 0-indexed) 		
		for q in range(1, len(s2) + 1):
			iq = q - 1
			if s1[ip] == s2[iq]:
				dp[p][q] = dp[p-1][q-1] + 1
				if max_len < dp[p][q]:
					max_len = dp[p][q]
					max_str = s1[p - max_len : p]
				max_len = max(dp[p][q], max_len)

	return max_len, max_str


def longestPalindrome_v1(s):  # is slow
    """
    :type s: str
    :rtype: str
    """
    max_len = 0
    max_str = ''
    # if len(s):
    # 	max_str = s[0]
    # 	max_len = 1    
    
    for i in range(len(s) + 1):
    	# print(i)
        for j in range(i):
            cur_sub = s[j:i]
            if cur_sub == cur_sub[::-1]:
                if i - j + 1 > max_len:
                    max_len = i - j + 1
                    max_str = cur_sub
    return max_str


def longestPalindrome(s):
	'''
	go to 2 sides
	'''
	if len(s) < 2:
		return s
	
	max_len = 1  # max length of palindrome
	end_inclusive = 0  # current end index of palindrome (include that character)
	for i in range(1, len(s)):
		# next longer palindrome can only have 2 lengths: new_max_len = old_max_len + 1 or new_max_len = old_max_len + 2
		cur_sub_length = i + 1
		new_max_len = None
		if max_len + 1 <= cur_sub_length:
			check_length = max_len + 1
			if check_palindrome(s, i + 1 - check_length, i):  # check to i inclusive
				end_inclusive = i
				new_max_len = check_length
		if max_len + 2 <= cur_sub_length:
			check_length = max_len + 2
			if check_palindrome(s, i + 1 - check_length, i):  # check to i inclusive
				end_inclusive = i
				new_max_len = check_length
		if new_max_len is not None:
			max_len = new_max_len
	return s[end_inclusive + 1 - max_len : end_inclusive + 1]

		
def check_palindrome(s, start_idx, end_idx):	
	while(start_idx < end_idx):
		if s[start_idx] != s[end_idx]:
			return False
		start_idx += 1
		end_idx -= 1
	return True

print(longestPalindrome('cabac'))
print(check_palindrome('aaaa', 0, 3))