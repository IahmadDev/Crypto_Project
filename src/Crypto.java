import java.util.Scanner;

public class Crypto {
    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        System.out.print("Enter your text to encrypt: ");
        String text = input.nextLine();
        System.out.print("Enter number to encrypt: ");
        int key = input.nextInt();
        System.out.print("Enter the number to group encrypted text: ");
        int gKey = input.nextInt();

        System.out.println(encryptedText(text,key,gKey));

    }

    public static String normalizeText(String text)
    {
        //This method--> to remove all spaces, , then any (.,:;!?)
        //turn all lower case to upper case
        // return the normalizeText String
        String newText = text.replaceAll("[~!`@#$%^&*()_+={}:;',./<>?]","");
        String nText = newText.replace(" ","");
        String upperText = nText.toUpperCase();
        return upperText;
    }

    public static String obify(String text)
    {
        String obifiedText = "";
        //String vowels = "AEIOU";
        for(int i =0; i<text.length();i++) {
            char charText = text.charAt(i);
            if (charText == 'A' || charText == 'E' || charText == 'I' || charText == 'O' || charText == 'U' || charText == 'Y') {
                obifiedText = obifiedText +"OB"+ charText;
            }else{
                obifiedText += "" + charText;
            }
        }
        return obifiedText;
    }


    public static String shiftAlphabet(int shift) {
        int start = 0;
        if (shift < 0) {
            start = (int) 'Z' + shift + 1;
        } else {
            start = 'A' + shift;
        }
        String result = "";
        char currChar = (char) start;
        for(; currChar <= 'Z'; ++currChar) {
            result = result + currChar;
        }
        if(result.length() < 26) {
            for(currChar = 'A'; result.length() < 26; ++currChar) {
                result = result + currChar;
            }
        }
        return result;
    }

    public static String ceasarify(String text, int key)
    {
        String newAlphabet = shiftAlphabet(key);
        String ceasarifiedText = "";
        int currChar ;
        for (int i = 0; i<text.length();i++){
            currChar = text.charAt(i)-65;
            ceasarifiedText = ceasarifiedText + newAlphabet.charAt(currChar);
            
        }
        return ceasarifiedText;
    }

    public static String groupify(String text, int key)
    { 
        String groupifiedText = "";
        for (int i = 0 ; i<text.length();i++) {
            if ((i + 1) % key != 0) {
                groupifiedText = groupifiedText + text.substring(i, (i + 1));

            } else {
                groupifiedText = groupifiedText + text.substring(i, (i + 1)) + " ";
            }
        }
            //to add remaining x at the end of groups
            int length = text.length();
            if(length % key != 0){
                int remChar = key - length % key;
                for (int j = 0; j<remChar;j++){
                    groupifiedText += "x";
                }
            }
    return groupifiedText;
    }

    public static String encryptedText(String text,int key,int gkey)
    {
        String normalText = normalizeText(text);
        String obText = obify(normalText);
        String encryptedText = ceasarify(obText,key);
        String groupedEncryptedText = groupify(encryptedText,gkey);
        return groupedEncryptedText;
    }
}
