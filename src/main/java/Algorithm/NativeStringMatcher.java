package Algorithm;

import Utils.Color;
import Utils.Pair;
import Utils.TextHeightLight;

import java.util.ArrayList;
import java.util.List;

public class NativeStringMatcher implements TextHeightLight {


    public static void nativeStingMatcher(String pattern, String text){
        List<Pair<Integer>> occurrences = new ArrayList<>();


        char[] mainPattern = pattern.toCharArray();
        for (int i = 0; i < text.length() - pattern.length(); i++) {
            if (compareLetters(mainPattern, text.substring(i,i+pattern.length()).toCharArray())){
                System.out.println(String.format("Match at position [%d, %d]",i, i+pattern.length()));
                occurrences.add(new Pair<>(i, i+pattern.length()));

            };
        }
        (new NativeStringMatcher()).printWithHeightLight(pattern,text,occurrences, Color.PINK);
    }

    private static boolean compareLetters(char[] mainPattern, char[] subText){
        for (int i = 0; i < mainPattern.length; i++) {
            if (mainPattern[i] != subText[i]) return false;
        }
        return true;
    }
}
