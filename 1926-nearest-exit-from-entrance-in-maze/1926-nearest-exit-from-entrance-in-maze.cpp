class Solution {
public:
    int nearestExit(vector<vector<char>>& maze, vector<int>& entry) {

        int n = maze.size();

        int m = maze[0].size();

        vector<int> dx = {-1, 0, 1, 0};

        vector<int> dy = {0, 1, 0, -1};


        queue<pair<int, int>> q;

        q.push({entry[0], entry[1]});

        int time = 0;

        maze[entry[0]][entry[1]] = '+';

        while(!q.empty())
        {
            int size = q.size();

            while(size--)
            {
                auto curr = q.front();

                q.pop();

                int i = curr.first;

                int j = curr.second;

                // if exit cell is found return time

                if(time!=0 && (i == 0 || j == 0 || i == n - 1 || j == m - 1))
                {
                    return time;
                }

                

                for(int k = 0; k < 4; k++)
                {
                    int x = i + dx[k];

                    int y = j + dy[k];

                    if(x >= 0 && x < n && y >= 0 && y < m && maze[x][y] != '+')
                    {
                        maze[x][y] = '+'; //visited mark

                        q.push({x, y});
                    }
                }
            }

            // increment time

            time++;
        }

        return -1;
    }
};