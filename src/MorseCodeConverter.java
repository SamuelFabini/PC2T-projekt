import java.util.HashMap;
import java.util.Map;

class MorseCodeConverter {
    private static final Map<Character, String> morseMap = new HashMap<>();
    static {
        morseMap.put('A', ".-"); morseMap.put('B', "-..."); morseMap.put('C', "-.-.");
        morseMap.put('D', "-.."); morseMap.put('E', "."); morseMap.put('F', "..-.");
        morseMap.put('G', "--."); morseMap.put('H', "...."); morseMap.put('I', "..");
        morseMap.put('J', ".---"); morseMap.put('K', "-.-"); morseMap.put('L', ".-..");
        morseMap.put('M', "--"); morseMap.put('N', "-."); morseMap.put('O', "---");
        morseMap.put('P', ".--."); morseMap.put('Q', "--.-"); morseMap.put('R', ".-.");
        morseMap.put('S', "..."); morseMap.put('T', "-"); morseMap.put('U', "..-");
        morseMap.put('V', "...-"); morseMap.put('W', ".--"); morseMap.put('X', "-..-");
        morseMap.put('Y', "-.--"); morseMap.put('Z', "--.."); morseMap.put(' ', " /");
    }

    public static String toMorse(String input) {
        StringBuilder morse = new StringBuilder();
        input = input.toUpperCase();
        for (char c : input.toCharArray()) {
            morse.append(morseMap.getOrDefault(c, ""));
            morse.append(" ");
        }
        return morse.toString();
    }
}
