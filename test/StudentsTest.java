import com.vmware.Students;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
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
        session = sessionFactory.getCurrentSession();

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


//    @Test
//    @RepeatedTest(value = 20)
//    public void testSaveStudents(){
//        // 生成学生对象
//        Students s = new Students();
//        s.setName("Vincent");
//        s.setGender("man");
//        s.setBirthday(new Date());
//        s.setAddress("revolution");
//        // 保存对象进入数据库
//        session.save(s);
//
//    }

    @Test
    public void testWriteBlob() throws IOException {
        Students s = new Students(1, "Miles", "man", new Date(), "revolution");
        // 获得照片文件
        File f = new File("/Users/shongbing/Downloads/aa.jpg");
        // 获得照片文件的输入流
        InputStream inputStream = new FileInputStream(f);
        // 创建一个Blob对象
        Blob image = Hibernate.getLobCreator(session).createBlob(inputStream, inputStream.available());
        s.setPicture(image);
        session.save(s);
    }

    @Test
    public void testReadBlob() throws SQLException, IOException {
        Students s = session.get(Students.class, 1);
        // 如果使用同一个Session去创建对象和读取对象，需要重置对象状态，否则会抛错
        session.refresh(s, LockMode.UPGRADE_NOWAIT);
        // 获得Blob对象
        Blob image = s.getPicture();
        // 获得照片的输入流
        InputStream inputStream = image.getBinaryStream();
        // 创建输出流
        File f = new File("/Users/shongbing/Downloads/bb.jpg");
        // 获得输出流
        OutputStream outputStream = new FileOutputStream(f);
        // 创建缓冲区
        byte[] buff = new byte[inputStream.available()];
        inputStream.read(buff);
        outputStream.write(buff);
        inputStream.close();
        outputStream.close();


    }
}