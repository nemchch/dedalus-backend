package eu.dedalus.analyser.controller;

import eu.dedalus.analyser.data.AnalyseDto;
import eu.dedalus.analyser.data.ResultDto;
import eu.dedalus.analyser.service.AnalyserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AnalyserController {

    private final AnalyserService analyserService;

    @PostMapping(value = "/api/v1/analyse")
    public ResponseEntity<ResultDto> analyse(@RequestBody AnalyseDto analyseDto) {
        ResultDto resultDto = analyserService.analyse(analyseDto);
        return ResponseEntity.ok(resultDto);
    }
}
