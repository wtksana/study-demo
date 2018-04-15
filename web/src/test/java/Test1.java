import com.tt.study.demo.DemoApplication;
import com.tt.study.demo.common.RespInfo;
import com.tt.study.demo.entity.User;
import com.tt.study.demo.service.MqProducer.HelloProducer;
import com.tt.study.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * date: 2018/3/19
 * author: wt
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@Slf4j
public class Test1 {
    @Autowired
    private HelloProducer helloProducer;
    @Autowired
    private UserService userService;

    @Test
    public void test1() {
        for (int i = 0; i < 100; i++) {
            helloProducer.sendToStringQueue("hello + " + i);
        }
        log.info("send ok");
    }

    @Test
    public void test2() {
        User user = new User();
        user.setUsername("haha");
        user.setPassword("123456");
        helloProducer.sendToUserQueue(user);
        helloProducer.sendToStringQueue("haha");
    }

    @Test
    public void test3() {
        helloProducer.sendToFanoutExchange("Fanout Message");
        log.info("ok");
    }

    @Test
    public void test4() {
        String msg = "topic msg ";
        helloProducer.sendWithTopicA(msg + "1");
        helloProducer.sendWithTopicB(msg + "2");
        helloProducer.sendWithTopicBC(msg + "3");
        log.info("ok");
    }

    @Test
    public void testRegister() throws Exception{
        User user = new User();
        user.setUsername("zhangsan10");
        user.setPassword("123456");
        userService.register(user);
        //等待5秒再结束
        Thread.sleep(5000);
    }

}
