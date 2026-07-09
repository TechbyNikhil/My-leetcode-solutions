/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function(nums, target) {
    let mp = new Map();
    for(i=0; i<nums.length; i++){
let rem = target-nums[i];
if(mp.has(rem))
{
    return [mp.get(rem),i];
}
else 
mp.set(nums[i],i);
    }
};