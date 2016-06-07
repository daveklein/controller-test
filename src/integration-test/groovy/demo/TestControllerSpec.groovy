package demo


import grails.test.mixin.integration.Integration
import grails.transaction.*
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.request.RequestContextHolder
import spock.lang.*
import org.springframework.beans.factory.annotation.Autowired


@Integration
@Rollback
class TestControllerSpec extends Specification {

	@Autowired
	TestController controller

    @Autowired
    TestService testService

    @Autowired
    WebApplicationContext ctx

    def request
    def setup() {
        request = grails.util.GrailsWebMockUtil.bindMockWebRequest(ctx)
        println request.getClass().name
        controller.testService = testService
        //controller.request = request
    }

    def cleanup() {
        RequestContextHolder.resetRequestAttributes()
    }

    void "test testAction"() {
        when:
        	request.params.input = "hello"
            controller.testAction()
        then:
            request.response.status == 200
            request.response.contentAsString == "HELLO"
    }
}
