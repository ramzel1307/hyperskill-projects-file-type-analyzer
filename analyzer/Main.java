package analyzer;

import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {        
        String file = args[0];
        HashMap<String, String> pattern = new HashMap();
        pattern.put(args[1], args[2]);
        
        try {
            byte[] allBytes = Files.readAllBytes(Paths.get(file));
            String strAllBytes = new String(allBytes, StandardCharsets.UTF_8).toLowerCase();
            
            pattern.entrySet().stream()
                .filter(p -> strAllBytes.contains(p.getKey().toLowerCase()))
                .map(p -> p.getValue())
                .findAny()
                .ifPresentOrElse(System.out::println,
                                () -> System.out.println("Unknown file type"));
        } catch (IOException ioex) {
            System.err.println(ioex.getMessage());
        }
    }
}
