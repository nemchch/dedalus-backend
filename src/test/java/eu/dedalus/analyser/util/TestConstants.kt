package eu.dedalus.analyser.util

import eu.dedalus.analyser.data.AnalyseDto

val analyseDtoVowels = AnalyseDto(true, "healthcare software")
val analyseDtoConsonants = AnalyseDto(false, "healthcare software")
val analyseDtoOnlyNumbers = AnalyseDto(null, "1234569876")
val analyseDtoOnlySpecialChars = AnalyseDto(null, "   | =-=~~=|*/-    ")
val analyseDtoOnlySpecialCharsConsonants = AnalyseDto(false, "   | =-=~~=|*/-    ")
val analyseDtoOnlyConsonantsVowels = AnalyseDto(true, "qwrtyplkjhgfds")
val analyseDtoOnlyVowelsConsonants = AnalyseDto(false, "aaaaaaaaaaeoiiiiiiiiiiiiuu")
val analyseDtoUpperCaseAndLowerCase = AnalyseDto(null, "AaAapPpP")
val analyseDtoEmptyText = AnalyseDto(null, "")
val analyseDtoEmptyVowels = AnalyseDto(true, "")
val analyseDtoEmptyConsonants = AnalyseDto(false, "")