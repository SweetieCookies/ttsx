import com.zf.dao.UserDao;
import com.zf.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class test1 {
    @Autowired
    private UserDao userDao;
    @Test
    public void test1(){
        userDao.add(new User(null,"zf","123","3255@123.xom",new Date(),"123"));
    }
    @Test
    public void test2(){
        User user = userDao.findByEmail("3255193457@qq.com");
        System.out.println(user);
    }
}
