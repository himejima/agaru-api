import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {

  "Application" should {

  //   "send 404 on a bad request" in new WithApplication{
  //     route(FakeRequest(GET, "/boum")) must beNone
  //   }

  //   "render the index page" in new WithApplication{
  //     val home = route(FakeRequest(GET, "/")).get

  //     status(home) must equalTo(OK)
  //     contentType(home) must beSome.which(_ == "text/html")
  //     contentAsString(home) must contain ("Your new application is ready.")
  //   }
  //   "123 = 123" in {
  //       123 must beEqualTo(123)
  //   }
        "render add page" in new WithApplication{
            val home = route(FakeRequest(GET, "/add")).get
            contentAsString(home) must contain("住所録の登録")
        }

        "send 404 on a bad request" in new WithApplication{
            route(FakeRequest(GET, "/bonum")) must beNone
        }

        "render the index page" in {
            running(FakeApplication()) {
                val home = route(FakeRequest(GET, "/")).get
                status(home) must equalTo(OK)
                contentType(home) must beSome.which(_ == "text/html")
                contentAsString(home) must contain("住所録一覧")
            }
        }
  }
}
