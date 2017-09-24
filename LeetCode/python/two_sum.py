class Solution:
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """        
        # first pass get all the val to idx
        val_to_idx = {nums[idx]:idx for idx in range(len(nums))}
        # second pass:
        for cur_idx in range(len(nums)):
            rest = target - nums[cur_idx]
            if rest in val_to_idx and val_to_idx[rest] != cur_idx:
                return [cur_idx, val_to_idx[rest]]
        print('sth wrong')
        return
    def twoSum_v2(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        val_to_idx = {}
        # only one pass:
        for cur_idx in range(len(nums)):
            rest = target - nums[cur_idx]
            if rest in val_to_idx:
                return [cur_idx, val_to_idx[rest]]
            val_to_idx[nums[cur_idx]] = cur_idx
        print('sth wrong')
        return
