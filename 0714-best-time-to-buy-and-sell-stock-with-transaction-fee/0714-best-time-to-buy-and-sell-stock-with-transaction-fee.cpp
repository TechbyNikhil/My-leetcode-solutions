class Solution {
public:
    int maxProfit(vector<int>& prices, int fee) {
        int n = prices.size();
        if (n == 0) return 0;

        // profit if we don't share any stock on day 0
        int hold_nothing = 0; 
        // profit if we buy a stock on day 0
        int hold_stock = -prices[0]; 

        for (int i = 1; i < n; i++) {
            // Maximum of doing nothing or selling the stock we hold today
            int next_hold_nothing = max(hold_nothing, hold_stock + prices[i] - fee);
            
            // Maximum of doing nothing or buying a stock today
            int next_hold_stock = max(hold_stock, hold_nothing - prices[i]);

            hold_nothing = next_hold_nothing;
            hold_stock = next_hold_stock;
        }

        return hold_nothing;
    }
};
