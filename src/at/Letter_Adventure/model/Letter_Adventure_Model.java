package at.Letter_Adventure.model;

import java.util.ArrayList;
import java.util.Random;

public class Letter_Adventure_Model {
    private ArrayList<String> englishWords = new ArrayList<>();
    private String currentWord;

    public Letter_Adventure_Model() {
        // Initialisiere die Liste der englischen Wörter
        initializeEnglishWords();
    }

    private void initializeEnglishWords() {
        // Füge Wörter zur Liste hinzu (nur Beispielwörter, du kannst weitere hinzufügen)
        englishWords.add("Dog");
        englishWords.add("Tree");
        englishWords.add("Car");
        englishWords.add("Cat");
        englishWords.add("Apple");
        englishWords.add("Orange");
        englishWords.add("Book");
        englishWords.add("Sun");
        englishWords.add("Moon");
        englishWords.add("Star");
    }

    public void addEnglishWord(String word) {
        // Füge ein neues englisches Wort zur Liste hinzu
        englishWords.add(word);
    }

    public String getRandomEnglishWord() {
        // Wähle ein zufälliges englisches Wort aus der Liste aus
        if (englishWords.isEmpty()) {
            return null; // Wenn die Liste leer ist
        }
        Random random = new Random();
        int randomIndex = random.nextInt(englishWords.size());
        return englishWords.get(randomIndex);
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(String word) {
        currentWord = word;
    }
}
