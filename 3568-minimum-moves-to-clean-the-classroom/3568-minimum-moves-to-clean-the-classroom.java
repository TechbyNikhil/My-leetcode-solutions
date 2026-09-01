class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int[][] litterId = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(litterId[i], -1);
        }
        int startR = 0, startC = 0;
        int litterCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);
                if (ch == 'S') {
                    startR = i;
                    startC = j;
                } else if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }
        int targetMask = (1 << litterCount) - 1;
        
        Queue<int[]> queue = new LinkedList<>();
        
        boolean[][][][] visited =new boolean[m][n][1 << litterCount][energy + 1];
        queue.offer(new int[]{startR, startC, energy, 0});
        visited[startR][startC][0][energy] = true;
        int[][] directions = { {1, 0},  {-1, 0}, {0, 1},{0, -1} };

        int moves = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] state = queue.poll();
                int r = state[0];
                int c = state[1];
                int currEnergy = state[2];
                int mask = state[3];
                
                if (mask == targetMask) {
                    return moves;
                }
                for (int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }
                    
                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }
                    
                    if (currEnergy == 0) {
                        continue;
                    }
                    int newEnergy = currEnergy - 1;
                    int newMask = mask;
                    
                    if (litterId[nr][nc] != -1) {
                        newMask |= (1 << litterId[nr][nc]);
                    }
                    
                    if (classroom[nr].charAt(nc) == 'R') {
                        newEnergy = energy;
                    }
                    if (!visited[nr][nc][newMask][newEnergy]) {
                        visited[nr][nc][newMask][newEnergy] = true;
                        queue.offer(new int[]{ nr, nc, newEnergy, newMask  });
                    }
                }
            }
            moves++;
        }
        return -1;
    }
}
