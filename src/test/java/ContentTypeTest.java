import com.fbh.enums.ContentType;

public class ContentTypeTest {
	//测试枚举序数能否注册输出
	public static void main(String[] args) {
		ContentType ct = ContentType.HTML;
		System.out.println(ct.getOrdinal());
	}
}
