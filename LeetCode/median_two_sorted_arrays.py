class Solution(object):
    def findMedianSortedArrays(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: float
        """                
        idx = 0
        i1 = 0
        i2 = 0
        last = 0
        last_of_last = 0
        
        total_len = len(nums1) + len(nums2) 
        med_idx = total_len / 2

        while idx <= med_idx:
            last_of_last = last
            if i1 == len(nums1):
                last = nums2[i2]
                i2 += 1
            elif i2 == len(nums2):
                last = nums1[i1]
                i1 += 1
            elif nums1[i1] < nums2[i2]:
                last = nums1[i1]
                i1 += 1
            else:
                last = nums2[i2]
                i2 += 1
            idx += 1
            
        if total_len % 2 == 0:                        
            return 0.5 * (last + last_of_last)
        else:
            return last