import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Kamil on 02.12.2016.
 */
public class day2 {

    private ArrayList<String> pin1to9Template = new ArrayList<>(Arrays.asList(
            "123",
            "456",
            "789"
    ));

    private ArrayList<String> pin1toDTemplate = new ArrayList<>(Arrays.asList(
            "  1  ",
            " 234 ",
            "56789",
            " ABC ",
            "  D  "
    ));

    private ArrayList<String> numsString = new ArrayList<>();
    private Number num1to9 = new Number(pin1to9Template, '5');
    private Number num1toD = new Number(pin1toDTemplate, '5');
    private ArrayList<Character> pin1to9 = new ArrayList<>();
    private ArrayList<Character> pin1toD = new ArrayList<>();

    private day2() throws Exception{

        BufferedReader in = new BufferedReader(new FileReader("AdventOfCode/describtion/day2/input.txt"));

        String line;
        while((line = in.readLine()) != null) {
            numsString.add(line);
        }
        in.close();

        for (int i = 0; i < numsString.size(); i++){
            for (char c : numsString.get(i).toCharArray()){
                num1to9.move(c);
                num1toD.move(c);
            }
            pin1to9.add(i,num1to9.getActualChar());
            pin1toD.add(i,num1toD.getActualChar());
            num1to9 = new Number(pin1to9Template, pin1to9.get(i));
            num1toD = new Number(pin1toDTemplate, pin1toD.get(i));
        }

        for (char char1to9 : pin1to9){
            System.out.print(char1to9);
        }

        System.out.print("\n");

        for (char char1toD : pin1toD){
            System.out.print(char1toD);
        }
    }

    public static void main(String[] args) throws Exception{
        new day2();
    }
}

class Number {

    private ArrayList<String> pinTemplate = new ArrayList<>();
    private int xPos = 0, yPos = 0;

    public char getActualChar() throws Exception{
        try {
            return pinTemplate.get(yPos).charAt(xPos);
        } catch (IndexOutOfBoundsException e){
            throw new Exception("Kłopociki :<");
        }
    }

    Number(ArrayList<String> pinTemplate, char startChar){

        for(int i = 0 ; i < pinTemplate.size() ; i++){
            if (pinTemplate.get(i).contains(Character.toString(startChar))){
                yPos = i;
                for (int j = 0; j < pinTemplate.get(i).length() ; j++){
                    if (pinTemplate.get(i).charAt(j) == startChar) {
                        xPos = j;
                        break;
                    }
                }
            }
        }

        this.pinTemplate = pinTemplate;
    }

    public void move(char direction){
        int newXPos = xPos, newYPos = yPos;
        char newChar = ' ';

        switch (direction){
            case 'U' : {
                newYPos--;
                break;
            }
            case 'D' : {
                newYPos++;
                break;
            }
            case 'L' : {
                newXPos--;
                break;
            }
            case 'R' : {
                newXPos++;
                break;
            }
        }

        if (newYPos>= 0 && newYPos < pinTemplate.size()){
            if (newXPos >= 0 && newXPos < pinTemplate.get(newYPos).length()){
                newChar = pinTemplate.get(newYPos).charAt(newXPos);
            }
        }

        if (newChar != ' '){
            xPos = newXPos;
            yPos = newYPos;
        }
    }
}