package eu.dedalus.analyser.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnalyseDto {
    private Boolean vowels;
    private String text;
}
