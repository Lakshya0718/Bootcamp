package assessment;

//Q4. Calculate the number & Percentage Of Lowercase Letters,Uppercase Letters, Digits And Other Special Characters In A String
import java.nio.charset.CharsetEncoder;

public class q4 {

    public static void main(String[] args) {
        int uCount = 0;
        int lCount = 0;
        int digitCount = 0;
        int specialCount = 0;

        String str = "Hello Sir @Raj12#, How can 3 i help you!?";
        for(int i = 0 ; i < str.length() ; i++){
            char ch = str.charAt(i);
            if(Character.isUpperCase(ch)){
                uCount++;
            }
            else if (Character.isLowerCase(ch)){
                lCount++;
            }
            else if (Character.isDigit(ch)){
                digitCount++;
            }
            else{
                specialCount++;
            }
        }
        double length = str.length();
        double upperPercent = (uCount/length)*100;
        double lowerPercent = (lCount/length)*100;
        double otherPercent = (digitCount/length)*100;
        double specialPercent = (specialCount/length)*100;
        System.out.println("upper count = "+ uCount);
        System.out.println("upper percentage = " + upperPercent);
        System.out.println("Lower count = "+ lCount);
        System.out.println("Lower percentage = " + lowerPercent);
        System.out.println("digit count = "+ digitCount);
        System.out.println("digit percentage = " + otherPercent);
        System.out.println("special count = "+ specialCount);
        System.out.println("special percentage = " + specialPercent);
    }
}
