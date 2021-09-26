package eu.dedalus.analyser.service

import eu.dedalus.analyser.data.ResultDto
import eu.dedalus.analyser.util.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AnalyserServiceTest {
    @Autowired
    lateinit var analyserService: AnalyserService

    @Test
    fun analyse_Vowels() {
        val resultDto = analyserService.analyse(analyseDtoVowels)
        assertThat(resultDto.list).hasSize(3)
        assertThat(resultDto.list).hasSameElementsAs(
            listOf(
                ResultDto.ResultItem("A", 3),
                ResultDto.ResultItem("E", 3),
                ResultDto.ResultItem("O", 1)
            )
        )
    }

    @Test
    fun analyse_Consonants() {
        val resultDto = analyserService.analyse(analyseDtoConsonants)
        assertThat(resultDto.list).hasSize(8)
        assertThat(resultDto.list).hasSameElementsAs(
            listOf(
                ResultDto.ResultItem("R", 2),
                ResultDto.ResultItem("C", 1),
                ResultDto.ResultItem("S", 1),
                ResultDto.ResultItem("T", 2),
                ResultDto.ResultItem("F", 1),
                ResultDto.ResultItem("W", 1),
                ResultDto.ResultItem("H", 2),
                ResultDto.ResultItem("L", 1),
            )
        )
    }

    @Test
    fun analyse_OnlyNumbers() {
        val resultDto = analyserService.analyse(analyseDtoOnlyNumbers)
        assertThat(resultDto.list).hasSize(0)
    }

    @Test
    fun analyse_OnlySpecialChars() {
        val resultDto = analyserService.analyse(analyseDtoOnlySpecialChars)
        assertThat(resultDto.list).hasSize(0)
    }

    @Test
    fun analyse_OnlySpecialChars_Consonants() {
        val resultDto = analyserService.analyse(analyseDtoOnlySpecialCharsConsonants)
        assertThat(resultDto.list).hasSize(0)
    }

    @Test
    fun analyse_OnlyConsonants_Vowels() {
        val resultDto = analyserService.analyse(analyseDtoOnlyConsonantsVowels)
        assertThat(resultDto.list).hasSize(0)
    }

    @Test
    fun analyse_OnlyVowels_Consonants() {
        val resultDto = analyserService.analyse(analyseDtoOnlyVowelsConsonants)
        assertThat(resultDto.list).hasSize(0)
    }

    @Test
    fun analyse_UpperCaseAndLowerCase() {
        val resultDto = analyserService.analyse(analyseDtoUpperCaseAndLowerCase)
        assertThat(resultDto.list).hasSize(2)
        assertThat(resultDto.list).hasSameElementsAs(
            listOf(
                ResultDto.ResultItem("A", 4),
                ResultDto.ResultItem("P", 4)
            )
        )
    }

    @Test
    fun analyse_EmptyText() {
        val resultDto = analyserService.analyse(analyseDtoEmptyText)
        assertThat(resultDto.list).hasSize(0)
    }

    @Test
    fun analyse_EmptyText_Vowels() {
        val resultDto = analyserService.analyse(analyseDtoEmptyVowels)
        assertThat(resultDto.list).hasSize(0)
    }

    @Test
    fun analyse_EmptyText_Consonants() {
        val resultDto = analyserService.analyse(analyseDtoEmptyConsonants)
        assertThat(resultDto.list).hasSize(0)
    }
}