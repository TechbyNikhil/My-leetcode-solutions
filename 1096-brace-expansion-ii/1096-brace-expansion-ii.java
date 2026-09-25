

class Solution {
    int i = 0;
    public List<String> braceExpansionII(String expression) {
        Set<String> result = parse(expression);
        List<String> answer = new ArrayList<>(result);
        Collections.sort(answer);
        return answer;
    }

    
    private Set<String> parse(String expression) {
        Set<String> result = new HashSet<>();
        result.add("");
        while (i < expression.length()  && expression.charAt(i) != '}'   && expression.charAt(i) != ',') {
            Set<String> current = new HashSet<>();

            if (expression.charAt(i) == '{') {
                i++; 
                current = parse(expression);
                i++; 
            } else {
                current.add(String.valueOf(expression.charAt(i)));
                i++;
            }

            result = concatenate(result, current);
        }

        
        while (i < expression.length() && expression.charAt(i) == ',') {
            i++; // skip ','
            Set<String> current = parse(expression);
            result.addAll(current);
        }
        return result;
    }

    
    private Set<String> concatenate(Set<String> A, Set<String> B) {
        Set<String> result = new HashSet<>();
        for (String a : A) {
            for (String b : B) {
                result.add(a + b);
            }
        }
        return result;
    }
}