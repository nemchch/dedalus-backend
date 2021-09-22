package eu.dedalus.analyser.service;

import eu.dedalus.analyser.data.AnalyseDto;
import eu.dedalus.analyser.data.ResultDto;

public interface AnalyserService {
    ResultDto analyse(AnalyseDto analyseDto);
}
