import kotlin.Exception

open class Coder{
    var linesOfCode = 0
    open val countLinesOfCodePerHour = 100 //количество строчек кода за час
    @Throws
    open fun writeCode (hour: Int): Int{
        if (hour < 0) {
            throw IllegalArgumentException("Количество выделенных часов должно быть больше или равно 0!")
        }
        linesOfCode = countLinesOfCodePerHour * hour
        return this.linesOfCode
    }
}
