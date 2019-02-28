import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.moshi.responseObject

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import io.kotlintest.matchers.numerics.shouldBeGreaterThan
import io.kotlintest.shouldBe
import io.kotlintest.specs.FeatureSpec
import models.Comments
import models.Post

class TestComment: FeatureSpec({
    feature("Comments"){
        FuelManager.instance.basePath = "http://localhost:3000"

        val commentsCount = 102
        val newComments = Comments("New comment", 1)

        scenario("adds new comment"){
            val (_, response, result) = "/comments"
                    .httpPost(listOf("body" to newComments.body, "postId" to newComments.postId))
                    .responseObject<Comments>()
            response.statusCode shouldBe 201
            result.component2() shouldBe newComments
        }

        scenario("gets all comments"){
            val (request, response, result) = Fuel.get("/comments")
                    .responseObject<List<Comments>>()
            response.statusCode shouldBe 200
            result.component1()?.count() shouldBe commentsCount
        }
    }
})
