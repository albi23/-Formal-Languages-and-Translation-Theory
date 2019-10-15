package Algorithm;

import Utils.Color;
import Utils.Pair;
import Utils.TextHeightLight;
import java.util.*;

public class FiniteAutomationMatcher implements TextHeightLight {

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

    public void finiteAutomationMatcher() {
        List<Pair<Integer>> occurrences = new ArrayList<>();

        this.computeTransitionFunction();
        int q = 0;
        for (int i = 0; i < text.length(); i++) {
            q = states.get(q).get(text.charAt(i));
            if (q == pattern.length()) {
                System.out.println(String.format("Match at position [%d, %d)", (i + 1) - pattern.length(), i + 1));
                occurrences.add(new Pair<>((i + 1) - pattern.length(), (i + 1)));

            }
        }

        printWithHeightLight(pattern, text, occurrences, Color.BLUE);
    }

    private void computeTransitionFunction(){
        this.setInitialState();
        int stateValue, m = pattern.length();

        for (int q = 1; q <= m; q++) {
            Map<Character,Integer> newState = new HashMap<>();
            for (Character a : alphabet) {
                stateValue = Math.min(m, q+1);
                while (!isSuffix(pattern.substring(0,q)+a,pattern.substring(0,stateValue))) stateValue--;

                newState.put(a,stateValue);
                states.put(q,newState);
            }
        }

//        states.forEach((k1,v1) ->{
//            System.out.println("K1 : "+k1);
//            v1.forEach((k2,v2)->{
//                System.out.print("{\""+k2+"\" -> \""+v2+"\"}, ");
//            });
//            System.out.println();
//        });
    }

    private void setInitialState(){
        Map<Character,Integer> initialState = new HashMap<>();
        for (Character c: alphabet) initialState.put(c,0);
        if (this.pattern.length() > 0) initialState.put(this.pattern.charAt(0),1);
        this.states.put(0,initialState);
    }

    private boolean isSuffix(String Pq, String Pk){
        for (int k = Pk.length()-1, q = Pq.length() - 1;  k > -1 && q > -1 ; k--, q--) {
            if (Pk.charAt(k) != Pq.charAt(q)) return false;
        }
        return true;
    }

}
