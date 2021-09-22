package eu.dedalus.analyser.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

@Data
@AllArgsConstructor
public class ResultDto {
    private HashMap<String, Integer> map;
}
