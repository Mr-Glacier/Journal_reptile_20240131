import com.alibaba.fastjson.JSONObject;
import org.example.Entity.author;

public class testmain {

    public static void main(String[] args) {
        author a = new author();
        a.setAuthorHome("1212");
        a.setAuthorID("5555");
        a.setAuthorName("hhhhh");


        String join = JSONObject.toJSONString(a);
        System.out.println(join);
    }

}
