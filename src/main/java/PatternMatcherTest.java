public class PatternMatcherTest {

    public static void main(String[] args) {

        String testText = "The native keyword is applied to a method to indicate that the method is implemented in native code using JNI (Java Native Interface). ";
        String testPattern = " ";
        NativeStringMatcher.nativeStingMatcher(testPattern,testText);


        System.out.println("\n** Knuth-Morris-Pratt algorithm ***");
        String pattern = "Script";
        String text = "These docs assume that you are already familiar with HTML, CSS, JavaScript," +
                " and some of the tools from the latest standards, such as classes and modules.\n" +
                "The code samples are written using TypeScript. Most Angular code can be written with just the" +
                " latest JavaScript, using types for dependency injection, and using decorators for metadata.";

        AlgorithmKnuthMorrisPratt alg = new AlgorithmKnuthMorrisPratt(pattern,text);
        alg.findPattern();

        FiniteAutomationMatcher fam = new FiniteAutomationMatcher(pattern,text);

    }
}
