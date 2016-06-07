package demo

class TestController {

	def testService

    def index() { }

    def testAction(){
    	def input = params.input
    	def output = testService.emphasize(input) 
    	render output
    }
}
