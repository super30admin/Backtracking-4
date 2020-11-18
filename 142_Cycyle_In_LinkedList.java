    /*  Explanation
    # Leetcode problem link : https://leetcode.com/problems/linked-list-cycle-ii/
    Time Complexity for operators : o(n) 
    Extra Space Complexity for operators : o(1) n = length of string
    Did this code successfully run on Leetcode : NA
    Any problem you faced while coding this : No
# Your code here along with comments explaining your approach
        # Basic approach : 
        # Optimized approach: 
                              
            # 1. Backtracking
                    A) 
    */ 



/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            
            if(slow == fast){
                ListNode dummyHead = head;
                while(dummyHead != slow){
                    dummyHead = dummyHead.next;
                    slow = slow.next;
                }
                
                return slow;
            }
        }
        
        return null;
    }
}