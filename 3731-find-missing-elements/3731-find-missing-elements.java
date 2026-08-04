class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<n;i++){
            set.add(nums[i]);
        }
        for(int i= nums[0] ; i<= nums[n-1];i++){
            if(!set.contains(i)){
               res.add(i); 
            }
        }
        return res;
    }
}