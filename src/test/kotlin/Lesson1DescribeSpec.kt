import io.kotlintest.matchers.collections.shouldContain
import io.kotlintest.matchers.numerics.shouldBeGreaterThan
import io.kotlintest.matchers.numerics.shouldBeLessThan
import io.kotlintest.matchers.numerics.shouldNotBeInRange
import io.kotlintest.matchers.string.shouldBeLowerCase
import io.kotlintest.matchers.string.shouldContainADigit
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import io.kotlintest.specs.DescribeSpec
import kotlin.math.min

class BasicsDescribeSpec : DescribeSpec({
    describe("Checks on Kotlin basics implementations") {
        context("functions") {
            it("sum1 and sum2 works the same") {
                val a = 1
                val b = 2
                sum1(a, b) shouldBe 3
                sum1(a, b) shouldBeLessThan 4
                sum1(0, 3) shouldBe 3
                sum1(-1, 1) shouldBe 0
                sum1(a, b) shouldBeGreaterThan 0
                sum1(20, 34) shouldNotBe 52
                sum1(a, b) shouldNotBeInRange (4..7)
                // Add greater less checks
            }
        }
/*
        context("variables") {

            val readOnly = 11
            var reassignable = 3
//            it("val can not be reassigned") {
//                readOnly = 12
//            }

            it("can be reassigned") {
                reassignable = 6
                reassignable shouldBe 6
            }
        }
*/
        context("strings") {
            val toge = "toge"
            val ther = "ther5"
            val together = "together5"

            it("concatenation works") {
                toge + ther shouldBe together
            }

            it("string interpolation works") {
                "$toge$ther" shouldBe together
            }

            it("is not empty") {
                together.isNotBlank() shouldBe true
            }

            it("the string is all in lower case\n") {
                together.shouldBeLowerCase()
            }

            it("no digit") {
                together.shouldContainADigit()
            }

        }

        /*

         */
        context("conditional expressions") {
            val max = 100
            val min = 0

            it("returns max") {
                maxOf(min, max) shouldBe max
            }

            it("return min") {
                minOf(1, 2)
            }
        }

        // Write minOff function

        context("when expression") {
            describe(1) shouldBe "One"
            describe("hello") shouldBe "Unknown"
            describe(34) shouldBe "Not a string"
            // Add other checks
        }

        context("collections") {
            val fruits = arrayListOf("Apple", "Orange", "Grapes", "Cherry")

            it("test shop") {
                fruits.count() shouldBe 4
                fruits shouldContain "Apple"
                fruits.contains("Orange")
                fruits[3] shouldBe "Cherry"

                count(fruits) shouldBe 4
            }
        }
    }
})
