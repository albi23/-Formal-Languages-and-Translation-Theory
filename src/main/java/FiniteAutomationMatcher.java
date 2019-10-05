import java.util.*;

public class FiniteAutomationMatcher {

    private Map<Integer,Map<Character,Integer>> states;
    private Character[] alphabet;
    private String pattern;
    private String text;

    public FiniteAutomationMatcher(String pattern, String text, Character[] alphabet) {
        this.pattern = pattern;
        this.text = text;
        this.alphabet = alphabet;
        this.states = new HashMap<>();
        this.finiteAutomationMatcher();
    }

    public FiniteAutomationMatcher(String pattern, String text) {
        this.pattern = pattern;
        this.text = text;
        this.states = new HashMap<>();
        this.prepareAlphabet(pattern.concat(text));
        this.finiteAutomationMatcher();
    }

    private void prepareAlphabet(String str){
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < str.length()-1; i++) set.add(str.charAt(i));
        this.alphabet = set.toArray(new Character[set.size()]);
    }

    private void computeTransitionFunction(){
        int k = 0;
        int m = pattern.length();
        for (int q = 0; q < m; q++) {
            for (Character a : alphabet) {
                k = Math.min(m+1, q+2);
                while (isSuffix(pattern.substring(0,k), pattern.substring(0,q)+a)){
                    k--;
                }
                Map<Character,Integer> newState = new HashMap<>();
                newState.put(a,k);
                states.put(q,newState);
            }
        }
    }

    private boolean isSuffix(String pattern, String matcher){
        System.out.println("Pattern : "+pattern+"   matcher : "+matcher);
        for (int i = matcher.length()-1; i >0 ; i--) {
            if (matcher.charAt(i) != pattern.charAt(i)) return false;
        }
        return true;
    }

    public void finiteAutomationMatcher(){
        this.computeTransitionFunction();
        int n = text.length();
        int q =0;
        for (int i = 0; i < n; i++) {
            q = states.get(q).get(text.charAt(i));
            if (q == pattern.length()-1){
                System.out.println("Mamy wystąienie wzorca na pozycji " +(i-pattern.length()-1));
            }
        }
    }
}
