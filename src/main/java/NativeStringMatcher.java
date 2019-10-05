public class NativeStringMatcher  {


    public static void nativeStingMatcher(String pattern, String text){

        char[] mainPattern = pattern.toCharArray();
        for (int i = 0; i < text.length() - pattern.length(); i++) {
            if (compareLetters(mainPattern, text.substring(i,i+pattern.length()).toCharArray())){
                System.out.println(String.format("Match at position [%d, %d]",i, i+pattern.length()));
            };
        }
    }

    private static boolean compareLetters(char[] mainPattern, char[] subText){
        for (int i = 0; i < mainPattern.length; i++) {
            if (mainPattern[i] != subText[i]) return false;
        }
        return true;
    }
}
