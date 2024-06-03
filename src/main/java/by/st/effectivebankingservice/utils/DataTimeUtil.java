package by.st.effectivebankingservice.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Component
public class DataTimeUtil {
   public String localDateTimeFormatter(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd. MM. yyyy HH:mm:ss");
        return localDateTime.format(formatter);
    }
}
