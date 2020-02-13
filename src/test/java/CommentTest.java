import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fbh.bean.Article;
import com.fbh.bean.Comment;
import com.fbh.bean.User;
import com.fbh.service.CommentService;
import com.fbh.utils.RandomUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class CommentTest {

	@Autowired
	private CommentService cs;

	@Test
	public void test1() {
		Comment c = new Comment();
		User u = new User();
		Article a = new Article();
		for (int i = 0; i < 1000; i++) {
			u.setId(RandomUtil.getRandomNum(1, 6));
			c.setU(u);
			a.setId(RandomUtil.getRandomNum(1, 5));
			c.setA(a);
			c.setContent(RandomUtil.getRandomChineseString(100));
			c.setCreated(new Date(RandomUtil.getRandomNum(0, 10000) * 1000 * 60 * 60 * 24));
			cs.insertComment(c);
		}
	}
}
