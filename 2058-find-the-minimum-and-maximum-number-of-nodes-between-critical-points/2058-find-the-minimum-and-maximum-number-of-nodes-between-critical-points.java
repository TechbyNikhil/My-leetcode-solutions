/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int[] result = new int[2];
        List<Integer> criticalPoints = new ArrayList<>();
        int i = 1;  // Position counter
        
        ListNode prev = null;
        ListNode current = head;
        ListNode next = head.next;
        
        while (next != null && next.next != null) {
            prev = current;
            current = next;
            next = next.next;
            i++;
            
            // Check if current node is a critical point
            if ((current.val > prev.val && current.val > next.val) || (current.val < prev.val && current.val < next.val)) {
                criticalPoints.add(i);
            }
        }
        
        if (criticalPoints.size() < 2) {
            result[0] = -1;
            result[1] = -1;
            return result;
        }
        
        int minDistance = Integer.MAX_VALUE;
        int maxDistance = criticalPoints.get(criticalPoints.size() - 1) - criticalPoints.get(0);
        
        for (int j = 1; j < criticalPoints.size(); j++) {
            int distance = criticalPoints.get(j) - criticalPoints.get(j - 1);
            minDistance = Math.min(minDistance, distance);
        }
        
        result[0] = minDistance;
        result[1] = maxDistance;
        return result;
    }
}