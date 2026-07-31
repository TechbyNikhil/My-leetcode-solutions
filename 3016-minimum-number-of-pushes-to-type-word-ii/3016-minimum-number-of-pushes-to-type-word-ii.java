class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        Map<Character,Integer> map = new HashMap<>();
        for(char c : word.toCharArray()){
            map.put(c, map.getOrDefault(c,0)+1);
        }
        int result = 0;
        List<Map.Entry<Character,Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((entry1,entry2)->entry2.getValue().compareTo(entry1.getValue()));
        int count = 0;
        int freq = 1;
        for(int i =0;i<list.size();i++){
            result = result + freq * list.get(i).getValue();
            count++;
            if(count % 8 == 0){
                freq++;
            }
        }
        return result;
    }
}