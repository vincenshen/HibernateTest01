import com.vmware.Students;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class StudentsTest {

    private static SessionFactory sessionFactory;
    private static Session session;
    private static Transaction transaction;

    @BeforeAll
    public static void init(){

        // 创建配置对象
        Configuration configuration = new Configuration().configure();

        // 创建会话工厂对象
        sessionFactory = configuration.buildSessionFactory();

        // 打开会话对象
        session = sessionFactory.openSession();

        // 开启事务
        transaction = session.beginTransaction();
    }

    @AfterAll
    public static void destroy(){

        // 提交事务
        transaction.commit();

        // 关闭会话
        session.close();

        // 关闭会话工厂

        sessionFactory.close();
    }


    @Test
    // @RepeatedTest(value = 20)
    public void testSaveStudents(){
        // 生成学生对象
        Students s = new Students(1, "Miles", "man", new Date(), "revolution");

        // 保存对象进入数据库
        session.save(s);

    }
}