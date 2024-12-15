package hello.advanced.app.v5.v4;

import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.callback.TraceCallBack;
import hello.advanced.app.trace.callback.TraceTemplate;
import hello.advanced.app.trace.logtrace.LogTrace;
import hello.advanced.app.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV5 {

    private final TraceTemplate template;

    public OrderRepositoryV5(LogTrace trace) {
        this.template = new TraceTemplate(trace);
    }

    public void save(String itemId) {
        template.execute("OrderRepositoryV1.request()", () -> {
            if (itemId.equals("ex")) {
                throw new IllegalArgumentException("예외 발생!");
            }
            sleep(100);
            return null;
        });
    }

    public void sleep(int millis) {
        try{
            Thread.sleep(millis);
        } catch(InterruptedException e) {
            e.printStackTrace();;
        }
    }

}
