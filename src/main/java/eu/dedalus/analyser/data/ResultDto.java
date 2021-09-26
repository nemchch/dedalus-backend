package eu.dedalus.analyser.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResultDto {
    private List<ResultItem> list;

    @Data
    @AllArgsConstructor
    public static class ResultItem {
        private String letter;
        private Long times;
    }
}
