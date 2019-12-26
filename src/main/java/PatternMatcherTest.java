import Algorithm.AlgorithmKnuthMorrisPratt;
import Algorithm.FiniteAutomationMatcher;
import Algorithm.NativeStringMatcher;

import java.util.Arrays;

public class PatternMatcherTest {

    public static void main(String[] args) {

//        String testText = "The native keyword is applied to a method to indicate that the method is implemented in native code using JNI (Java Native Interface). ";
//        String testPattern = " ";
//        NativeStringMatcher.nativeStingMatcher(testPattern,testText);
//
//        System.out.println("\n*** Knuth-Morris-Pratt algorithm ***\n");
//        String pattern = "Script";
//        String text = "These docs assume that you are already familiar with HTML, CSS, JavaScript," +
//                " and some of the tools from the latest standards, such as classes and modules.\n" +
//                "The code samples are written using TypeScript. Most Angular code can be written with just the" +
//                " latest JavaScript, using types for dependency injection, and using decorators for metadata.";
//
//
//        AlgorithmKnuthMorrisPratt alg = new AlgorithmKnuthMorrisPratt(pattern,text);
//        alg.findPattern();
//
//        System.out.println("\n*** Algorithm.FiniteAutomationMatcher ***\n");
//
//        String pattern2 = "BABBB";
//        String text2 = "AABABBBBABBBBABABAAABBBAABBBABABBABABBAABABBBBBAABAAAAAAABABBBABBBABAABBBBAAAABB";
//        FiniteAutomationMatcher fam = new FiniteAutomationMatcher(pattern2,text2);
//

        /** Test patterns 1 **/

//        Character[] alphabet = new Character[]{'t','y','ą','['};
//        String testText1 = "tytytąąąąąątytytytytą";
//        String testPattern1[] = new String[]{"[","ą", "tyt", "ąą","ytą" ,"ąty"};
//        for (String testPattern : testPattern1) {
//          new FiniteAutomationMatcher(testPattern,testText1,alphabet);
//          System.out.println("-----------------\n");
//        }

        /** Test patterns 2 **/

        Character[] alphabet2 = new Character[]{'α','β','γ','δ'};
        String testText2 = "αβαβγβαβαβαβαβγ";
        String testPattern2[] = new String[]{"δ","γδ", "αβ", "αβαβ"};
        for (String testPattern : testPattern2) {
//            new FiniteAutomationMatcher(testPattern,testText2,alphabet2);
            new AlgorithmKnuthMorrisPratt(testPattern,testText2, Arrays.asList(alphabet2)).findPattern();

            System.out.println("-----------------\n");
        }

        /** Test patterns 3 **/

        Character[] alphabet3 = new Character[]{'ć','ą','ś','&'};
        String testText3 = "ąćąćąćąśśśćąćąćąćąćśśśćąćś";
        String testPattern3[] = new String[]{"&","&ś", "ąć", "ś","śś","ćąć"};
        for (String testPattern : testPattern3) {
//            new FiniteAutomationMatcher(testPattern,testText3,alphabet3);
            new AlgorithmKnuthMorrisPratt(testPattern,testText3, Arrays.asList(alphabet3)).findPattern();

            System.out.println("-----------------\n");
        }

        /** Test patterns 3 **/

        Character[] alphabet4 = new Character[]{'\\','|','/',' '};
        String testText4 = "\\|\\|\\/\\|/\\ \\\\\\ \\\\\\ \\/// //\\|\\|";
        String[] testPattern4 = new String[]{"|","||","\\ \\", "//","\\|\\","\\|\\|"};
        for (String testPattern : testPattern4) {
            new AlgorithmKnuthMorrisPratt(testPattern,testText4, Arrays.asList(alphabet4)).findPattern();
//            new FiniteAutomationMatcher(testPattern,testText4,alphabet4);
            System.out.println("-----------------\n");
        }

        /** Test patterns 4 **/

        Character[] alphabet5 = new Character[]{'ρ','ω','r','o','ろ'};
        String testText5 = "roωrrρrωrωrωrrωrろωorrρρo";
        String[] testPattern5 = new String[]{"ろ","ろω","ωrωr","ρo","rr","rρrρ"};
        for (String testPattern : testPattern5) {
            new AlgorithmKnuthMorrisPratt(testPattern,testText5, Arrays.asList(alphabet5)).findPattern();
            new FiniteAutomationMatcher(testPattern,testText5,alphabet5);
            System.out.println("-----------------\n");
        }

    }
}
