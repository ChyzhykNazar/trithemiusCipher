package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;

class TrithemiusChiper {
    public static String encrypt(String alphabet, String text, String type, String aKey, String bKey, String cKey) {
        boolean upperCase;
        String result = new String();
        char[] ch = alphabet.toCharArray();
        for(int i = 0; i < text.length(); i++){
            for(int j = 0; j < alphabet.length(); j++){
                upperCase = Character.isUpperCase(text.charAt(i));
                char temp = Character.toLowerCase(text.charAt(i));
                int code = temp;
                int k;
                if(ch[j] == temp){
                    try{
                        if(type == "Лінійне зміщення"){
                            k = Integer.parseInt(aKey) * i + Integer.parseInt(bKey);
                        }else{
                            k  = Integer.parseInt(aKey) * i * i + Integer.parseInt(bKey) * i + Integer.parseInt(cKey);
                        }
                        System.out.println(k);
                        int L = (j + k) % alphabet.length();
                        System.out.println("Alphabet: " + ch[j]);
                        System.out.println("Text: " + temp);
                        System.out.println("Shift: "+ ch[L]);
                        if(upperCase == true){
                            result = result + Character.toTitleCase(ch[L]);
                        }else{
                            result = result + ch[L];
                        }
                        j = alphabet.length();
                    }catch (ArrayIndexOutOfBoundsException ex){
                        ex.getMessage();
                    }
                }else if(temp == ' '){
                    result = result + temp;
                    j = alphabet.length();
                }else if(code == 45){
                    result = result + temp;
                    j = alphabet.length();
                }else if(code == 39){
                    result = result + temp;
                    j = alphabet.length();
                }
            }
        }
        return result;
    }
    public static String decipher(String alphabet, String text, String type, String aKey, String bKey, String cKey) {
        boolean upperCase;
        String result = new String();
        char[] ch = alphabet.toCharArray();
        for(int i = 0; i < text.length(); i++){
            for(int j = 0; j < alphabet.length(); j++){
                upperCase = Character.isUpperCase(text.charAt(i));
                char temp = Character.toLowerCase(text.charAt(i));
                int code = temp;
                int k;
                if(ch[j] == temp){
                    try{
                        if(type == "Лінійне зміщення"){
                            k = Integer.parseInt(aKey) * i + Integer.parseInt(bKey);
                        }else{
                            k  = Integer.parseInt(aKey) * i * i + Integer.parseInt(bKey) * i + Integer.parseInt(cKey);
                        }
                        System.out.println(k);
                        int number = j - k;
                        while (number < 0){
                            number += alphabet.length();
                        }
                        int m = number % alphabet.length();
                        System.out.println("Alphabet: " + ch[j]);
                        System.out.println("Text: " + temp);
                        System.out.println("Shift: "+ ch[m]);
                        if(upperCase == true){
                            result = result + Character.toTitleCase(ch[m]);
                        }else{
                            result = result + ch[m];
                        }
                        j = alphabet.length();
                    }catch (ArrayIndexOutOfBoundsException ex){
                        ex.getMessage();
                    }
                }else if(temp == ' '){
                    result = result + temp;
                    j = alphabet.length();
                }else if(code == 45){
                    result = result + temp;
                    j = alphabet.length();
                }else if(code == 39){
                    result = result + temp;
                    j = alphabet.length();
                }
            }
        }
        return result;
    }
}

public class Controller {

    @FXML
    private Button encrypt_btn;

    @FXML
    private TextArea input_textarea;

    @FXML
    private Label output;

    @FXML
    private Button decipher_btn;

    @FXML
    private ChoiceBox<String> shiftChoiceBox;

    @FXML
    private ChoiceBox<String> languageChoiceBox;

    @FXML
    private TextField aKey;

    @FXML
    private ChoiceBox<String> methodChoiceBox;

    @FXML
    void initialize() {
        String English = "abcdefghijklmnopqrstuvwxyz";
        String Ukrainian = "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя";
        methodChoiceBox.getItems().add("Лінійне зміщення");
        methodChoiceBox.getItems().add("Квадратичне зміщення");
        languageChoiceBox.getItems().add("Українська");
        languageChoiceBox.getItems().add("English");
        languageChoiceBox.setValue("Українська мова");

        languageChoiceBox.setOnAction(actionEvent -> {
            shiftChoiceBox.getItems().clear();
            if(languageChoiceBox.getValue() == "Українська"){
                for(int i = 0; i < 34; i++){
                    shiftChoiceBox.getItems().add("" + i + "");
                }
            }else{
                for(int i = 0; i < 27; i++){
                    shiftChoiceBox.getItems().add("" + i + "");
                }
            }
        });

        encrypt_btn.setOnAction(actionEvent -> {
            String inputText = input_textarea.getText();
            String A = String.valueOf(aKey.getText());
            String B = String.valueOf(aKey.getText());
            String C = String.valueOf(aKey.getText());
            if(languageChoiceBox.getValue() == "Українська"){
                String outputText = TrithemiusChiper.encrypt(Ukrainian,inputText,String.valueOf(methodChoiceBox.getValue()), A, B, C);
                System.out.println(outputText);
                output.setText(outputText);
            }else{
                String outputText = TrithemiusChiper.encrypt(English,inputText, String.valueOf(methodChoiceBox.getValue()), A, B, C);
                System.out.println(outputText);
                output.setText(outputText);
            }

        });

        decipher_btn.setOnAction(actionEvent -> {
            String inputText = input_textarea.getText();
            String A = String.valueOf(aKey.getText());
            String B = String.valueOf(aKey.getText());
            String C = String.valueOf(aKey.getText());
            if(languageChoiceBox.getValue() == "Українська"){
                String outputText = TrithemiusChiper.decipher(Ukrainian,inputText,String.valueOf(methodChoiceBox.getValue()), A, B, C);
                System.out.println(outputText);
                output.setText(outputText);
            }else{
                String outputText = TrithemiusChiper.decipher(English,inputText,String.valueOf(methodChoiceBox.getValue()), A, B, C);
                System.out.println(outputText);
                output.setText(outputText);
            }
        });

    }
}