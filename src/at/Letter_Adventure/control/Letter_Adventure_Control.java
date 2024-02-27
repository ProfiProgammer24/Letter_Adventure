package at.Letter_Adventure.control;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Random;

public class Letter_Adventure_Control {

    @FXML
    private Label label_score;

    @FXML
    private Label label_wort;

    @FXML
    private Pane pane_bubbles;

    private String[] englishWords = {
            "Apple", "Banana", "Cat", "Dog", "House", "Car", "Tree", "Flower", "Sun", "Moon",
            "Water", "Food", "Drink", "Book", "Pen", "School", "Teacher", "Student", "Friend", "Family",
            "Mother", "Father", "Brother", "Sister", "Baby", "Boy", "Girl", "Happy", "Sad", "Love",
            "Hate", "Good", "Bad", "Big", "Small", "Hot", "Cold", "Day", "Night", "Time", "Money",
            "Color", "Red", "Blue", "Green", "Yellow", "Orange", "Black", "White", "One", "Two", "Three",
            "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Hello", "Goodbye", "Please",
            "Thank you", "Sorry", "Yes", "No", "How are you?", "I'm fine", "What is your name?", "My name is...",
            "Where is the bathroom?", "I love you", "Happy birthday", "Good morning", "Good afternoon",
            "Good evening", "Good night", "Excuse me", "I don't understand", "Can you help me?",
            "How much does this cost?", "What time is it?", "Where is the nearest hospital?",
            "Please give me a glass of water", "I'm lost", "I need a taxi",
            "Where can I find a restaurant?", "This is delicious", "I'm allergic to peanuts",
            "Can you recommend a good movie?", "I like to travel", "What's the weather like today?",
            "I'm tired", "Are you okay?", "I'm hungry", "I'm thirsty", "It's beautiful here",
            "I'm excited", "Can you speak slower, please?", "I'm from Austria"
    };

    private String[] germanTranslations = {
            "Apfel", "Banane", "Katze", "Hund", "Haus", "Auto", "Baum", "Blume", "Sonne", "Mond",
            "Wasser", "Essen", "Trinken", "Buch", "Stift", "Schule", "Lehrer", "Schüler", "Freund", "Familie",
            "Mutter", "Vater", "Bruder", "Schwester", "Baby", "Junge", "Mädchen", "Glücklich", "Traurig", "Liebe",
            "Hass", "Gut", "Schlecht", "Groß", "Klein", "Heiß", "Kalt", "Tag", "Nacht", "Zeit", "Geld",
            "Farbe", "Rot", "Blau", "Grün", "Gelb", "Orange", "Schwarz", "Weiß", "Eins", "Zwei", "Drei",
            "Vier", "Fünf", "Sechs", "Sieben", "Acht", "Neun", "Zehn", "Hallo", "Auf Wiedersehen", "Bitte",
            "Danke", "Entschuldigung", "Ja", "Nein", "Wie geht es dir?", "Mir geht es gut", "Wie heißt du?",
            "Ich heiße...", "Wo ist die Toilette?", "Ich liebe dich", "Alles Gute zum Geburtstag", "Guten Morgen", "Guten Tag",
            "Guten Abend", "Gute Nacht", "Entschuldigen Sie", "Ich verstehe nicht", "Können Sie mir helfen?",
            "Wie viel kostet das?", "Wie spät ist es?", "Wo ist das nächste Krankenhaus?",
            "Bitte geben Sie mir ein Glas Wasser", "Ich habe mich verirrt", "Ich brauche ein Taxi",
            "Wo finde ich ein Restaurant?", "Das schmeckt köstlich", "Ich bin allergisch gegen Erdnüsse",
            "Können Sie einen guten Film empfehlen?", "Ich reise gerne", "Wie ist das Wetter heute?",
            "Ich bin müde", "Geht es dir gut?", "Ich habe Hunger", "Ich habe Durst", "Es ist wunderschön hier",
            "Ich bin aufgeregt", "Können Sie langsamer sprechen, bitte?", "Ich komme aus Österreich"
    };

    private String currentWord;
    private int correctWordIndex;
    private boolean gameInProgress = false;

    private int score = 0;

    @FXML
    public void initialize() {
        // Starte das Spiel beim Initialisieren
        startGame();
        animateBubbles();
    }

    public void startGame() {
        gameInProgress = true;
        score = 0;
        updateScore();
        generateNewWord();
    }

    public void handleBubbleClick(javafx.scene.input.MouseEvent event) {
        if (!gameInProgress) {
            return; // Das Spiel ist beendet, keine Aktion erforderlich
        }

        Label clickedBubble = (Label) event.getSource();
        String selectedWord = clickedBubble.getText();

        if (checkAnswer(selectedWord)) {
            // Richtige Antwort
            score++; // Erhöhe den Punktestand
            updateScore();
            generateNewWord();
        } else {
            // Falsche Antwort (könnte eine Animation oder Feedback hinzufügen)
        }
    }

    private boolean checkAnswer(String selectedWord) {
        // Überprüfe, ob das ausgewählte deutsche Wort die richtige Übersetzung ist
        return selectedWord.equals(germanTranslations[correctWordIndex]);
    }

    private void generateNewWord() {
        // Zufällige Auswahl eines neuen Wortes
        Random random = new Random();
        correctWordIndex = random.nextInt(englishWords.length);
        currentWord = englishWords[correctWordIndex];
        label_wort.setText(currentWord); // Anzeige des englischen Wortes oben

        // Erstelle Bubbles für die Auswahlmöglichkeiten
        pane_bubbles.getChildren().clear();
        String[] options = generateWordOptions();
        for (String option : options) {
            Label bubble = createBubble(option);
            pane_bubbles.getChildren().add(bubble);
        }
    }

    private String[] generateWordOptions() {
        // Erstelle eine zufällige Liste von deutschen Übersetzungen (einschließlich der richtigen Antwort)
        String[] options = new String[3];
        options[0] = germanTranslations[correctWordIndex]; // Die deutsche Übersetzung des aktuellen englischen Wortes

        // Wähle zufällige deutsche Übersetzungen für die anderen Optionen
        for (int i = 1; i < options.length; i++) {
            options[i] = getRandomGermanTranslation();
        }
        shuffleArray(options);
        return options;
    }

    private String getRandomGermanTranslation() {
        // Hier kannst du die Logik einfügen, um zufällige deutsche Übersetzungen zu generieren.
        // Zum Beispiel könntest du ein Array von deutschen Übersetzungen für die englischen Wörter haben
        // und dann zufällig eine davon auswählen.

        Random random = new Random();
        return germanTranslations[random.nextInt(germanTranslations.length)];
    }

    private Label createBubble(String word) {
        Label bubble = new Label(word);
        bubble.getStyleClass().add("bubble"); // CSS-Klasse für die Bubble-Stile
        bubble.setOnMouseClicked(this::handleBubbleClick); // Klick-Handler hinzufügen
        return bubble;
    }

    private void updateScore() {
        label_score.setText(String.valueOf(score));
    }

    private void shuffleArray(String[] array) {
        // Mische die Elemente im Array (Fisher-Yates-Mischalgorithmus)
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            String temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }



    private void animateBubbles() {
        // Erstelle eine Timeline für die Bubble-Animation
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE); // Endlose Wiederholung

        // Füge Keyframes hinzu, um die Bubbles hin und her zu bewegen
        KeyFrame moveBubbles = new KeyFrame(Duration.seconds(0.1), event -> {
            // Bewege die Bubbles nach links oder rechts
            for (Node bubble : pane_bubbles.getChildren()) {
                // Hole die aktuelle Position der Bubble
                double currentX = bubble.getLayoutX();
                double currentY = bubble.getLayoutY();

                // Berechne die neue Position basierend auf der aktuellen Position und der Bewegungsgeschwindigkeit
                double newX = currentX + (Math.random() - 0.5) * 20; // Zufällige Bewegung nach links oder rechts
                double newY = currentY + (Math.random() - 0.5) * 20; // Zufällige Bewegung nach oben oder unten

                // Stelle sicher, dass die neue Position innerhalb der Grenzen des Pane liegt
                newX = Math.max(0, Math.min(pane_bubbles.getWidth() - bubble.getBoundsInLocal().getWidth(), newX));
                newY = Math.max(0, Math.min(pane_bubbles.getHeight() - bubble.getBoundsInLocal().getHeight(), newY));

                // Setze die neue Position der Bubble
                bubble.setLayoutX(newX);
                bubble.setLayoutY(newY);
            }
        });

        timeline.getKeyFrames().add(moveBubbles);

        // Starte die Animation
        timeline.play();
    }


}


