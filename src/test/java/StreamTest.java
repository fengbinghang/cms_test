import com.fbh.bean.User;

public class StreamTest {
	public static void main(String[] args) throws Exception {
		User u1 = new User(1, "张三", "222");
		User u2 = new User(1, "张三", "222");
		System.out.println(u1.equals(u2));
		/*
		 * // 把user对象序列化到D:/user.txt中 ObjectOutputStream out = new
		 * ObjectOutputStream(new FileOutputStream(new File("D:/user.txt")));
		 * out.writeObject(u); out.flush(); out.close(); // 反序列化 ObjectInputStream in =
		 * new ObjectInputStream(new FileInputStream(new File("D:/user.txt"))); Object
		 * u2 = in.readObject(); in.close(); System.out.println(u2);
		 */
	}
}
