import io.kotlintest.matchers.collections.shouldContain
import io.kotlintest.matchers.numerics.*
import io.kotlintest.shouldBe
import io.kotlintest.shouldNot
import io.kotlintest.shouldNotBe
import io.kotlintest.specs.FeatureSpec
import kotlin.math.min

class BasicsFeatureSpec : FeatureSpec({
    feature("functions") {
        scenario("sum1 and sum2 works the same") {
            val a = 1
            val b = 2
            sum1(a, b) shouldBe 3
            sum1(a, b) shouldBeLessThan 4
            sum1(0, 3) shouldBe 3
            sum1(-1, 1) shouldBe 0
            sum1(4, 10) shouldBeLessThan 15
            sum1(a, b) shouldBeInRange (1..3)
            sum1(10, b) shouldBeGreaterThanOrEqual 12
            // Add greater less checks
        }
    }

    /*
    feature("variables") {

        val readOnly = 11
        var reassignable = 3
//            scenario("val can not be reassigned") {
//                readOnly = 12
//            }

        scenario("can be reassigned") {
            readOnly != 21
            reassignable = 6
            reassignable shouldBe 6
        }
    }
*/
    feature("strings") {
        val toge = "toge"
        val ther = "ther"
        val together = "together"

        scenario("concatenation works") {
            toge + ther shouldBe together
        }

        scenario("string interpolation works") {
            "$toge$ther" shouldBe together
        }

        scenario("is not empty") {
            together.isNotBlank() shouldBe true
        }
    }

    feature("conditional expressions") {
        val max = 100
        val min = 0
        val list = listOf(1 ,3, 4, 5, -1)

        scenario("returns max") {
            maxOf(min, max) shouldBe max
        }

        scenario("return min") {
            minOf(1.0, 2.0, 3.0) shouldBe 1.0
        }
        scenario("return min of 3") {
            minOf3(1.0, 2.0, 3.0) shouldBe 1.0
        }
        scenario("return min of 4") {
            minOf4(1.0, 2.0, 3.0, 4.0) shouldBe 1.0
        }
        scenario("return min of 5") {
            minOf5(1.0, 2.0, 3.0, 4.0, 5.0) shouldBe 1.0
        }
        scenario("minoff with collection") {
            minOfList(list) shouldBe -1
        }
    }

    // Write minOff function

    feature("when expression") {
        describe(1) shouldBe "One"
        describe("hello") shouldBe "Unknown"
        describe(9122538854775) shouldBe "Long"
        describe(345) shouldNotBe "Long"
        describe("Hello") shouldBe "Greeting"
        describe(23.23) shouldBe "Not a string"
        describe(true) shouldBe "Boolean"

        // Add other checks
    }

    feature("collections") {
        val fruits = arrayListOf("Apple", "Orange", "Grapes", "Cherry")

        scenario("") {
            fruits.count() shouldBe 4
            fruits shouldContain "Apple"

            count(fruits) shouldBe 4
        }
    }
})

fun sum1(a: Int, b: Int): Int {
    return a + b
}

// fun sum2(a: Int, b: Int) = a + b

fun maxOf(a: Int, b: Int) = if (a > b) a else b

fun minOf(a: Double, b: Double): Double {
    return if (a < b) a else b
    //return Unit
}

fun minOf(a: Double, boom: Double, c: Double): Double {
    return minOf(a, minOf(boom, c))

}

fun minOf3(k: Double, m: Double, n: Double): Double {
    var min = k
    if (min > m) min = m
    if (min > n) min = n
    return min
}
fun minOf4(q: Double, w: Double, e: Double, r: Double): Double {

    val res1 = minOf(q, w)
    val res2 = minOf(e, r)
    return minOf(res1, res2)
    //  если не использовать промежуточные переменные res1  res2, то можно попробовать так: return minOf((minOf(a,b), minOf (b,c))
}
fun minOf5(z: Double, x: Double, s: Double, d: Double, v: Double): Double {
    return minOf(minOf(minOf(z, x), minOf(s,d)), v)
}

fun minOfList(list:List<Int>): Int {
    var mini = list[0]
    for (i in list){
        if (i < mini) {
            mini = i
        }
    }
    return mini
}


fun describe(obj: Any): String =
        when (obj) {
            1 -> "One"
            "Hello" -> "Greeting"
            is Boolean -> "Boolean"
            is Long -> "Long"
            !is String -> "Not a string"
            else -> "Unknown"
        }

fun count(list: ArrayList<String>): Int {
    var counter = 0
    for (i in list) {
        counter += 1
    }
    return counter
}