import java.util.Arrays
import io.kotlintest.specs.FeatureSpec
import io.kotlintest.*
import io.kotlintest.matchers.string.containADigit
import io.kotlintest.matchers.string.containOnlyWhitespace
import io.kotlintest.matchers.types.shouldBeTypeOf

class TestOfHomeWork: FeatureSpec({
    feature("test string"){
        scenario("String with one letter") {
            uniqueLetters("A") shouldBe true
        }
        scenario("positive test"){
            uniqueLetters("Human") shouldBe true
            println()
        }
        scenario("Type of result") {
            uniqueLetters("Human").shouldBeTypeOf<Boolean>()
            println()

        }
        scenario("Test with two words"){
            uniqueLetters("Hi, guys!") shouldBe true
            println()
        }
        scenario("Test for false result"){
            uniqueLetters("Hello!") shouldBe false
            println()
        }
        scenario("Empty string"){
            uniqueLetters("") shouldBe false
            println()
        }
        scenario("String is Blank"){
            uniqueLetters(" ") shouldBe false
            println()
        }
        scenario("Two words with different case"){
            uniqueLetters("Two tesla") shouldBe false
            println()
        }
        scenario("String with few whitespace") {
            uniqueLetters("asd fgh jkl zxc") shouldBe true
            println()
        }
        scenario("String with same punctuation marks"){
            uniqueLetters("!\"#\$%&'()*+,-./:;<=>?@[\\]^_`{|}~!\"#\$%&'()*+,-./:;<=>?@[\\]^_`{|}~") shouldBe true
            println()
        }//
        scenario("String have equals digits") {
            uniqueLetters("33 and 223") shouldBe true
            println()
        }

    }
})

fun uniqueLetters (s:String):Boolean {
    val str = s.toLowerCase() /* Таким образом все буквы привожу в нижний регистр, для поиска одинаковых */
    val len = str.length
    val punctuations = "!\"#\$%&'()*+,-./:;<=>?@[\\]^_`{|}~"


    if (str.isEmpty()) {
        println("String is empty")
        return false
    }
    if (str.isBlank()) {
        println("String is blank")
        return false
    }

    var x = 0 // счетчик внутри цикла, для вывода в консоле
    for (i in 0 until len - 1) {
        for (j in i + 1 until len) {
            x++
            println("Итерация№$x. i = ${str[i]}  сравнить с j = ${str[j]}") // для наглядности выводим

            if (str[i] == str[j]) { //сравниваем между собой все сиволы, пока не встретим повторяющиеся
                if (str[j].isWhitespace()) {
                    println("Пробелы не считаем за одинаковые буквы")
                    continue // если встречаем несколько пробелов, то не считаем их за совпадение
                }
                if (punctuations.contains(str[j])) {
                    println("Знаки препинания не считаем за совпадения букв")
                    continue // если встречаем несколько одинаковых знаков препинания, то не считаем их за одинаковые буквы
                }
                if (str[i].isDigit()){
                    println("Цифры не считаем за одинаковые буквы")
                    continue // сли встречаем несколько равных цифр, то не считаем их за одинаковые буквы
                }
                return false
            }
        }
    }
    return true
}
