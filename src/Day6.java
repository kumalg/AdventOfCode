import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day6 {
    public static void main(String[] args) throws IOException{
        List<String> input = Files.lines(Paths.get("AdventOfCode/description/day6/input.txt")).collect(Collectors.toList());

        System.out.println(correctText(input, entry -> entry.getValue()));
        System.out.println(correctText(input, entry -> -entry.getValue()));
    }

    private static String correctText(List<String> input,
                                      Function<Map.Entry<Character, Integer>, Integer> cmp){
        Map<Character, Integer> allCharactersByColumn = new HashMap<>();

        String output = "";

        for (int col = 0; col < input.get(0).length(); col++){
            for (int row = 0; row < input.size(); row++){
                char actualChar = input.get(row).charAt(col);
                allCharactersByColumn.put(actualChar, allCharactersByColumn.getOrDefault(actualChar, 0) + 1);
            }

           char correctChar2 = allCharactersByColumn
                   .entrySet()
                   .stream()
                   .min( Comparator.comparing(cmp) )
                   .get()
                   .getKey();

            output += correctChar2;

            allCharactersByColumn.clear();
        }
        return output;
    }
}