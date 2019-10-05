import java.util.List;

public interface TextHeightLight {

    default void printWithHeightLight(String pattern, String text,List<Entry<Integer>> occurrences){
        System.out.println("Pattern : \""+pattern+"\"");
        int lastIndx = 0;
        StringBuilder sb = new StringBuilder();
        for (Entry<Integer> singleMark: occurrences) {
            if (singleMark.getValue1() < lastIndx) singleMark.setValue1(lastIndx);
            sb.append(text, lastIndx, singleMark.getValue1());
            sb.append("\u001b[41m").append(text, singleMark.getValue1(), singleMark.getValue2()).append("\u001b[0m");
            lastIndx = singleMark.getValue2();
        }
        if (occurrences.get(occurrences.size()-1).getValue2() != text.length()-1) sb.append(text, lastIndx, text.length());
        System.out.println(sb.toString());
    }
}
