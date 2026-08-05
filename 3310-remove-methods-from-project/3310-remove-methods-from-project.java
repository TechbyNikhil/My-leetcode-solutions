class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
    
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        
        for (int[] invocation : invocations) {
            int ai = invocation[0];
            int bi = invocation[1];
            graph.get(ai).add(bi);
        }
        
        boolean[] suspicious = new boolean[n];
        dfsMarkSuspicious(graph, k, suspicious);
        
       
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) { 
                for (int neighbor : graph.get(i)) {
                    if (suspicious[neighbor]) {
                        
                        List<Integer> allMethods = new ArrayList<>();
                        for (int j = 0; j < n; j++) {
                            allMethods.add(j);
                        }
                        return allMethods; 
                    }
                }
            }
        }
        
        List<Integer> remaining = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                remaining.add(i);
            }
        }
        return remaining;
    }
    
    
    private void dfsMarkSuspicious(List<List<Integer>> graph, int node, boolean[] suspicious) {
        suspicious[node] = true;
        for (int neighbor : graph.get(node)) {
            if (!suspicious[neighbor]) {
                dfsMarkSuspicious(graph, neighbor, suspicious);
            }
        }
    }
}