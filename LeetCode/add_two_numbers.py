# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        real_res = ListNode(0)
        res = real_res  # res is the temp linkedlist that will traverse
        residu_ten = 0
        while True:
            if l1 and l2:
                res.val = l1.val + l2.val + residu_ten            
                l1 = l1.next
                l2 = l2.next
            elif l1:
                res.val = l1.val + residu_ten
                l1 = l1.next
            elif l2:
                res.val = l2.val + residu_ten
                l2 = l2.next
            elif residu_ten:  # both l1 and l2 are finished but there are residuals left
                res.val = residu_ten                
            else:  # both l1 and l2 are finished and no residual left
                break
            if res.val > 9:
                res.val -= 10
                residu_ten = 1  # for next round
            else:
                residu_ten = 0    
            if l1 or l2 or residu_ten:  # unfinish l1 or unfinish l2 or there is still residual  
                res.next = ListNode(0)
            res = res.next
        
        return real_res
