import io.kotlintest.specs.FeatureSpec
import io.kotlintest.*
import io.kotlintest.matchers.types.shouldBeTypeOf

class Test: FeatureSpec({
    feature("Test of 3 classes") {
        val c = Coder()
        val d = Developer()
        val q = QaEngineer()

        //Test Coder
        scenario("Test of count lines in Coder") {
            c.writeCode(0) shouldBe 0
        }
        scenario("Test of class Coder. Hour < 0"){
            shouldThrow<IllegalArgumentException> {c.writeCode(-5)}
        }
        scenario("Type of result"){
            c.writeCode(10).shouldBeTypeOf<Int>()
        }
        scenario("Hour = countLinesOfCodePerHour"){
            c.writeCode(100) shouldBe 10000
        }

        //Test Developer
        scenario("Hour = 0") {
            d.writeCode(0) shouldBe 0
        }
        scenario("Hour < 0"){
            shouldThrow<IllegalArgumentException> { d.writeCode(-8) }
        }
        scenario("Type of result in Developer"){
            d.writeCode(7).shouldBeTypeOf<Int>()
        }
        scenario("Hour = makeBugsPer100Line"){
            d.writeCode(7) shouldBe 49
        }

        //Testing QaEngineer
        scenario("Type of result Qa") {
            q.work(10).shouldBeTypeOf<Int>()
        }
        scenario("countLinesOfCode < 0") {
            shouldThrow<IllegalArgumentException> { q.work(-15)}
        }
        scenario("countLinesOfCode = 0") {
            shouldThrow<IllegalArgumentException> { q.work(0)}
        }
        scenario("Test when countLinesOfCode = foundBugsPer100Line") {
            q.work(15) shouldBe 2
        }
        scenario("Test qa"){
            q.work(100) shouldBe 15
        }
    }
})
