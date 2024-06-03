package by.st.effectivebankingservice.utils;

import org.springframework.stereotype.Component;

@Component
public class StringUtil {
    public String capitalizeFirstLetter(String word) {
        if (word == null || word.isEmpty()) {
            return "";
        }
        char firstLetter = Character.toUpperCase(word.charAt(0));
        return firstLetter + word.substring(1);
    }
}
