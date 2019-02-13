import com.sun.istack.internal.Nullable
import com.sun.xml.internal.fastinfoset.stax.events.EmptyIterator
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException
import io.kotlintest.*
import io.kotlintest.matchers.collections.containNull
import io.kotlintest.matchers.collections.shouldContain
import io.kotlintest.matchers.file.beExecutable
import io.kotlintest.matchers.haveKey
import io.kotlintest.matchers.haveValue
import io.kotlintest.matchers.maps.shouldContainValue
import io.kotlintest.matchers.maps.shouldNotContain
import io.kotlintest.matchers.numerics.shouldBeGreaterThanOrEqual
import io.kotlintest.matchers.numerics.shouldBeInRange
import io.kotlintest.matchers.numerics.shouldBeLessThan
import io.kotlintest.matchers.numerics.shouldNotBeGreaterThan
import io.kotlintest.matchers.types.shouldBeTypeOf
import io.kotlintest.matchers.types.shouldNotBeTypeOf
import io.kotlintest.properties.propertyTestFailureMessage
import io.kotlintest.specs.FeatureSpec
import kotlin.Exception
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NotNullTypeParameter
import kotlin.system.exitProcess

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


    feature ("Test of list with map"){
        val list2: List<String> = listOf("Apple", "Orange", "Grapes", "Apple", "Orange", "Apple", "", "", "Cherry", "Watermelon", "Grapes", "Peach")

        scenario("print result") {
            println("\nHome work 2\n Количество фруктов в магазине:\n" + listToMap(list2))

        }
        scenario("Have key Apple"){
            listToMap(list2) shouldHave (haveKey("Apple"))
            println(" \nScenario #1: Map have key = Apple")
        }

        scenario("Have value"){
            listToMap(list2) shouldBe (haveValue(3))
            println(" \nScenario #2: Have value = 3")

        }

        scenario("Should not be boolean"){
            listToMap1(list2) shouldNotBe Boolean
            println(" \nScenario #3: Map should not be boolean")

        }
        scenario("No banana in map"){
            listToMap(list2) shouldNot (haveKey("Banana"))
            println(" \nScenario #4: No banana in map")

        }
        scenario("Contains orange true"){
            listToMap(list2).containsKey("Orange") shouldBe true
            println(" \nScenario #5: Map contains orange")

        }
        scenario("Not Empty"){
            listToMap(list2).isEmpty() shouldBe false
            println(" \nScenario #6: Not Empty")

        }
        scenario("contain value 3"){
            listToMap(list2).shouldContainValue(3)
            println(" \nScenario #7: contain value 3")

        }
        scenario("not contain Potato = 5") {
            listToMap1(list2).shouldNotContain("Potato", 5)
            println(" \nScenario #8: not contain Potato = 5")

        }
            scenario("test 9") {
                println("\n Попытка №2. Список №2:\n" + listToMap1(list2))

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
        scenario("minOf with collection") {
            minOfList(list) shouldBe -1
        }
    }

    //test of Home work #3, task 1
    feature("Test minOf with vararg") {

        scenario("3.1 expected type testing"){
            minOfVarArg(0, 1, 2, 0).shouldBeTypeOf<Int>()
        }

        //Скорее всего в данном случае этот тест избыточный, так как тип Int,
        // и kotlin самостоятельно берет только целую часть числа
        scenario("3.1 list with not an integer element"){
            minOfVarArg(9/2, 5, 10) shouldBe 4
        }
        scenario("Empty list"){
            shouldThrow<Exception> { minOfVarArg() }
        }
        scenario("3.1 random values") {
            minOfVarArg(1, 8, -11234, 173, -4, 0, -3, 11234, -4, 34, 99) shouldBe (-11234)
        }
        scenario("3.1 test with two equal elements: + and - ") {
            minOfVarArg(1, -1) shouldBe -1
        }
        scenario("3.1 with one element in list") {
            minOfVarArg(1) shouldBe 1
        }
        scenario("3.1 test") {
            minOfVarArg(0) shouldBe 0
        }
    }

    //test Home work 3, task 2
    feature("Test of day to realise"){
        val featureForRealise = arrayListOf("registration", "Redesign", "add Button", "add New File", "Delete post", "Add comment")
        val featureForRealise2 = arrayListOf("registration")

        //57 задач для релиза
        val featureCountOverMax = arrayListOf("registration", "Redesign", "add Button", "add New File", "Delete post", "Add comment", "Edit profile", "registration", "Redesign", "add Button", "add New File", "Delete post", "Add comment", "Edit profile","registration", "Redesign", "add Button", "add New File", "Delete post", "Add comment", "Edit profile","registration", "Redesign", "add Button", "add New File", "Delete post", "Add comment", "Edit profile","registration", "Redesign", "add Button", "add New File", "Delete post", "Add comment", "Edit profile","registration", "Redesign", "add Button", "add New File", "Delete post", "Add comment", "Edit profile","registration", "Redesign", "add Button", "add New File", "Delete post", "Add comment", "Edit profile","registration", "Redesign", "add Button", "add New File", "Delete post", "Add comment", "Edit profile", " LAST ELEMENT")

        //56 задач для релиза
        val maxCountOfTaskForRealise = arrayListOf("registration", "Redesign", "add Button", "add New File", "Delete post", "Add comment", "Edit profile", "registration", "Redesign", "add Button", "add New File", "Delete post", "Add comment", "Edit profile","registration", "Redesign", "add Button", "add New File", "Delete post", "Add comment", "Edit profile","registration", "Redesign", "add Button", "add New File", "Delete post", "Add comment", "Edit profile","registration", "Redesign", "add Button", "add New File", "Delete post", "Add comment", "Edit profile","registration", "Redesign", "add Button", "add New File", "Delete post", "Add comment", "Edit profile","registration", "Redesign", "add Button", "add New File", "Delete post", "Add comment", "Edit profile","registration", "Redesign", "add Button", "add New File", "Delete post", "Add comment", "Edit profile")

        val qa = QA("Ruslan", "QA department")
        scenario("Type of result"){
            qa.realiseTesting(18, featureForRealise).shouldBeTypeOf<Boolean>()
        }
        scenario("1 day to realise"){
            qa.realiseTesting(1, featureForRealise) shouldBe false
            println(qa.toString())
        }
        scenario("test days = count of features for realise"){
            qa.realiseTesting(6, featureForRealise) shouldBe true
        }
        scenario("1 day and 1 feature to realise"){
            qa.realiseTesting(1, featureForRealise2) shouldBe true
        }
        scenario("0 days to realise"){
            qa.realiseTesting(0,featureForRealise2) shouldBe false
        }
        scenario("negative value"){
            qa.realiseTesting(-10,featureForRealise) shouldBe false
        }
        scenario("Test in range 1..28") {
            qa.realiseTesting(28, featureForRealise) shouldBe true
        }
        scenario("Test in range 1..30") {
            shouldThrow<IllegalArgumentException> { qa.realiseTesting(29, featureForRealise) }
        }
        scenario("over max tasks for realise") {
            qa.realiseTesting(28, featureCountOverMax) shouldBe false
        }
        scenario("Max count of tasks for Realise") {
            qa.realiseTesting(28, maxCountOfTaskForRealise) shouldBe true
        }
        scenario("Println of params"){
            println("Тесты разработал специалист из " + qa.department +" - "+ qa.name)
        }
    }

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
//Home work 1
fun minOfList(list:List<Int>): Int {
    var mini = list[0]
    for (i in list) {
         if (i < mini) {
             mini = i
         }
    }
    return mini
}

//Home work 2
fun listToMap(fruits: List<String>): Map<String, Int> {
    return fruits.groupingBy{it}.eachCount().filterKeys{it.isNotBlank() }
}

// Home work 2. try 2
fun listToMap1(fruits: List<String>): Map<String, Int> {
    val anotherMap = mutableMapOf<String, Int>()
    for (i in fruits) {
        if (i == "") continue

        if (anotherMap.contains(i)){  //anotherMap.containsKey(i)
            anotherMap[i] = anotherMap.getValue(i) + 1
        }
        else anotherMap[i] = 1
    }
    return anotherMap
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

//Home work 3, function for task 1
@Throws(IllegalArgumentException::class)
fun minOfVarArg(vararg list:Int):Int  {
    if (list.isEmpty()) {
        throw IllegalArgumentException("Нет минимального элемента, так как лист путой")
    }
    var mini = list[0]
    for (i in list) {
        if (i < mini) {
            mini = i
        }
    }
    return mini
}


// Home work 3, classes for task 2
open class Engineer (val name: String, val department: String)

class QA(name: String, department: String):Engineer(name, department){
    override fun toString(): String {
        return "My name is: " + this.name + ", im from department: " + this.department
    }

    @Throws(Exception::class)
    fun realiseTesting(dayToRelease: Int, newFunc: ArrayList<String>): Boolean {
        val tasksInDay = 2
        val countTasks = newFunc.size
        // Условие, что длительность спринта должна быть не более 28 дней
        if (dayToRelease > 28) {
            throw IllegalArgumentException("Длина спринта должна быть меньше 28 дней")
        }
        val tasksCompletedBeforeRelease = tasksInDay * dayToRelease
        return countTasks <= tasksCompletedBeforeRelease
    }
}
