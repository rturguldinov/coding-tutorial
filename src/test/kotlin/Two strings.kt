import io.kotlintest.specs.FeatureSpec
import io.kotlintest.*
import io.kotlintest.matchers.types.shouldBeTypeOf

class TestTwoStrings: FeatureSpec({
    feature("Test two strings"){
        scenario("Two Empty strings") {
            twoStrings("","") shouldBe false
        }
        scenario("First string is Empty ") {
            twoStrings("","Test") shouldBe false
        }
        scenario("Second string is Empty ") {
            twoStrings("Test","") shouldBe false
        }
        scenario("First string is Blank, second string is Empty ") {
            twoStrings(" ","") shouldBe false
        }
        scenario("Strings is Blank"){
            twoStrings("   ", "   ") shouldBe false
        }
        scenario("Strings with one letter"){
            twoStrings("a", "A") shouldBe false
        }
        scenario("Strings with 2 letters") {
            twoStrings("ab", "ba") shouldBe true
        }
        scenario("Strings with 2 letter. Second is not rotation first"){
            twoStrings("Float", "taolf") shouldBe false
        }
        scenario("Two identical strings"){
            twoStrings("Twister", "Twister") shouldBe false
        }
        scenario("Test true result") {
            twoStrings(str1 = "Ruslan", str2 = "anRusl") shouldBe true
        }
        scenario("Test of type result") {
            twoStrings("First", "Second").shouldBeTypeOf<Boolean>()
        }
        scenario("Strings with different letter case"){
            twoStrings("RUSLAN", "lanrus") shouldBe true
        }
        scenario("strings with different lengths") {
            twoStrings("asdfghq", "asdf") shouldBe false
        }
        scenario("Strings have more than one word"){
            twoStrings("Hello EVERYBODY!", "Body!Hello every") shouldBe true
        }
        scenario("Strings with identical words, but first string have more whitespace, than second string"){
            twoStrings("Hello     everybody!", "Hello everybody!") shouldBe false
        }
    }
})

fun twoStrings (str1:String, str2:String): Boolean{

    val len1 = str1.length
    val len2 = str2.length
    if (len1 != len2) // сразу проверяем на равенство длины входящих строк
    {
        println("Second string '$str2' is not rotation of  First string '$str1'")
        return false
    }

    if (len1 <= 1) {
        println("String length is less than 1 letter")
        return false
    }
    if (str1.isBlank())
    {
        println("String is Blank")
        return false
    }

    val s1 = str1.toLowerCase()
    val s2 = str2.toLowerCase()


    var newString: String
    for (i in 1 until s1.length) {
        newString = s1.takeLast(i) + s1.take(len1 - i)

        if (newString == s2) {
            println("$str2 is rotation of $str1")
            return true
        }
    }
    return false
}
