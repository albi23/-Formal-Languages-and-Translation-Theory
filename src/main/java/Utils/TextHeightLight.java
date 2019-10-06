package Utils;

import java.util.List;

public interface TextHeightLight {

    default void printWithHeightLight(String pattern, String text,List<Pair<Integer>> occurrences, Color color){
        System.out.println("Pattern : \""+color.makeColor(Color.RED,pattern)+"\"");
        int lastIndx = 0;
        StringBuilder sb = new StringBuilder();
        for (Pair<Integer> singleMark: occurrences) {
            if (singleMark.getValue1() < lastIndx) singleMark.setValue1(lastIndx);
            sb.append(text, lastIndx, singleMark.getValue1());
            sb.append(color.makeColor(color,text.substring(singleMark.getValue1(),singleMark.getValue2())));
            lastIndx = singleMark.getValue2();
        }
        if (occurrences.get(occurrences.size()-1).getValue2() != text.length()-1) sb.append(text, lastIndx, text.length());
        System.out.println(sb.toString());
    }
}
