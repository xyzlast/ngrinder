import static net.grinder.script.Grinder.grinder
import static org.junit.Assert.*
import static org.hamcrest.Matchers.*
import net.grinder.plugin.http.HTTPRequest
import net.grinder.script.GTest
import net.grinder.script.Grinder
import net.grinder.scriptengine.groovy.junit.GrinderRunner
import net.grinder.scriptengine.groovy.junit.annotation.BeforeThread
import net.grinder.scriptengine.groovy.junit.annotation.BeforeProcess

import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith

import HTTPClient.HTTPResponse

/**
 * A simple example using the HTTP plugin that shows the retrieval of a
 * single page via HTTP.
 *
 * This script is auto generated by ngrinder.
 *
 * @author ${userName}
 */
@RunWith(GrinderRunner)
class Test1 {

    public static GTest test;
    public static HTTPRequest request;

    // This method is executed once per a process.
    @BeforeProcess
    public static void beforeClass() {
        java.lang.System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        test = new GTest(1, 'AssetsStatusTest');
        request = new HTTPRequest();
        test.record(request);
        grinder.logger.info("before process.");
    }

    // This method is executed once per a thread.
    @BeforeThread
    public void beforeThread() {
        java.lang.System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        grinder.statistics.delayReports=true;
        grinder.logger.info("before thread.");
    }

    // This method is continuously executed until you stop the test
    @Test
    public void test(){
        HTTPResponse result = request.GET("https://www.pennygold.kr/v3/b2b/skp/assets/state?userKey=MT_f8ca85cf4e2f94bdf249dd74a9d2d6ae1e646f9e82cb34698213c3c930a25f0c");
        if (result.statusCode == 301 || result.statusCode == 302) {
            grinder.logger.warn("Warning. The response may not be correct. The response code was {}.", result.statusCode);
        } else {
            assertThat(result.statusCode, is(200));
        }
    }
}
