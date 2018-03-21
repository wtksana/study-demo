import com.tt.study.demo.DemoApplication;
import com.tt.study.demo.common.RespInfo;
import com.tt.study.demo.entity.User;
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
    private UserService userService;

    @Test
    public void test1() {
        RespInfo respInfo = userService.getUser(1L);
    }
}
