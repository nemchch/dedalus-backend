package eu.dedalus.analyser.service.impl;

import eu.dedalus.analyser.data.AnalyseDto;
import eu.dedalus.analyser.data.ResultDto;
import eu.dedalus.analyser.service.AnalyserService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnalyserServiceImpl implements AnalyserService {

    private static final Set<Character> ALL_VOWELS = new HashSet<>(Arrays.asList('A', 'E', 'I', 'O', 'U'));

    @Override
    public ResultDto analyse(AnalyseDto analyseDto) {
        String input = analyseDto.getText().toUpperCase();
        Map<Character, Long> analyse = getAnalyse(input);

        if (Objects.isNull(analyseDto.getVowels())) {
            return new ResultDto(getAnalyseResult(analyse));
        }

        Map<Character, Long> analyseLettersWithFilter;
        if (analyseDto.getVowels()) {
            analyseLettersWithFilter = analyse.entrySet().stream()
                    .filter(item -> ALL_VOWELS.contains(item.getKey()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        } else {
            analyseLettersWithFilter = analyse.entrySet().stream()
                    .filter(item -> !ALL_VOWELS.contains(item.getKey()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }

        return new ResultDto(getAnalyseResult(analyseLettersWithFilter));
    }

    private Map<Character, Long> getAnalyse(String input) {
        return input.toUpperCase().chars().mapToObj(ch -> (char) ch)
                .filter(Character::isAlphabetic)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }

    private List<ResultDto.ResultItem> getAnalyseResult(Map<Character, Long> frequentChars) {
        return frequentChars.entrySet().stream()
                .map(item -> new ResultDto.ResultItem(String.valueOf(item.getKey()), item.getValue()))
                .collect(Collectors.toList());
    }
}
