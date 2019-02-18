class QaEngineer: Coder() {
    private val foundBugsPer100Line = 15   // количество багов на 100 строчек кода

    @Throws
    fun work( countLinesOfCode: Int): Int {
        if (countLinesOfCode <= 0) {
            throw IllegalArgumentException("Количество строк кода должно быть больше 0!")
        }
        return foundBugsPer100Line * countLinesOfCode / 100
    }
}
