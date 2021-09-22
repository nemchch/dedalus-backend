package eu.dedalus.analyser.service.impl;

import eu.dedalus.analyser.data.AnalyseDto;
import eu.dedalus.analyser.data.ResultDto;
import eu.dedalus.analyser.service.AnalyserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AnalyserServiceImpl implements AnalyserService {

    @Override
    public ResultDto analyse(AnalyseDto analyseDto) {
        String input = analyseDto.getText();
        HashMap<String, Integer> result = new HashMap<>();
        int numA = 0;
        int numE = 0;
        int numI = 0;
        int numO = 0;
        int numU = 0;
        if (analyseDto.getVowels()) {
            char[] chars = input.toCharArray();
            for (char aChar : chars) {
                System.out.println(aChar);
                if (aChar == 'a' || aChar == 'A')
                    numA++;
                if (aChar == 'e' || aChar == 'E')
                    numE++;
                if (aChar == 'i' || aChar == 'I')
                    numI++;
                if (aChar == 'o' || aChar == 'O')
                    numO++;
                if (aChar == 'u' || aChar == 'U')
                    numU++;
            }
            result.put("A", numA);
            result.put("E", numE);
            result.put("I", numI);
            result.put("O", numO);
            result.put("U", numU);
        } else {
            HashMap<String, Integer> consonants = new HashMap<>();
            char[] chars = input.toCharArray();
            for (char aChar : chars) {
                if (aChar != 'a'
                        && aChar != 'A'
                        && aChar != 'e'
                        && aChar != 'E'
                        && aChar != 'i'
                        && aChar != 'I'
                        && aChar != 'o'
                        && aChar != 'O'
                        && aChar != 'u'
                        && aChar != 'U'
                ) {
                    String stringCharacter = String.valueOf(aChar).toUpperCase();
                    if (consonants.containsKey(stringCharacter)) {
                        Integer num = consonants.get(stringCharacter);
                        num++;
                        consonants.put(stringCharacter, num);
                    } else {
                        consonants.put(stringCharacter, 1);
                    }
                }
            }
            result.putAll(consonants);
        }
        return new ResultDto(result);
    }
}
