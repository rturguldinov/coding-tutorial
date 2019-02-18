
open class Developer: Coder() {
    private val makeBugsPer100Line = 7   // количество багов на 100 строчек кода
    @Throws
    override fun writeCode(hour: Int): Int {
        if (hour < 0) {
            throw IllegalArgumentException("Количество выделенных часов должно быть больше или равно 0!")
        }
        linesOfCode = countLinesOfCodePerHour * hour
        val bugsCountInCode = linesOfCode * makeBugsPer100Line / 100
        return bugsCountInCode
    }
}
