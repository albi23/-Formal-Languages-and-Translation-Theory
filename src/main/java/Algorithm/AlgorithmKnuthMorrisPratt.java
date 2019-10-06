package Algorithm;

import Utils.Color;
import Utils.Pair;
import Utils.TextHeightLight;

import java.util.ArrayList;
import java.util.List;


public class AlgorithmKnuthMorrisPratt implements TextHeightLight {

    private String pattern;
    private String text;
    private List<Pair<Integer>> occurrences;
    private int[] KMPtab;

    public AlgorithmKnuthMorrisPratt(String pattern, String text) {
        this.pattern = pattern;
        this.text = text;
        occurrences = new ArrayList<>();
        KMPtab = new int[this.pattern.length() + 1];
    }

    private void computePrefix() {
        int indx = -1;
        this.KMPtab[0] = indx;

        for (int i = 1; i <= this.pattern.length(); i++) {
            while (indx > -1 && (this.pattern.charAt(indx) != this.pattern.charAt(i - 1))) indx = this.KMPtab[indx];
            ++indx;
            this.KMPtab[i] = (i == this.pattern.length() || this.pattern.charAt(i) != this.pattern.charAt(indx)) ? indx : this.KMPtab[indx];
        }
    }


    public void findPattern() {

        this.computePrefix();
        int indx = 0;
        if (this.pattern.length() < 1) return;
        for (int i = 0; i < text.length(); i++) {
            while ((indx > -1) && (this.pattern.charAt(indx) != this.text.charAt(i))) indx = this.KMPtab[indx];
            if (++indx == this.pattern.length()) {
                occurrences.add(new Pair<>((i + 1) - pattern.length(), (i + 1)));
                System.out.println(String.format("Match at position [%d, %d)", (i + 1) - pattern.length(), i + 1));
                indx = KMPtab[indx];
            }
        }
        printWithHeightLight(this.pattern, this.text, occurrences, Color.GREEN);
    }

}
