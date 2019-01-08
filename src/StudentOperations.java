import com.vmware.Students;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;


public class StudentOperations {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public void create(){
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Students s = new Students(1, "Miles", "man", new Date(), "revolution");
        session.save(s);
        transaction.commit();
        session.close();
    }

    public void update(){
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Students students = session.get(Students.class, 1);
        students.setName("Monroe");
        session.update(students);
        transaction.commit();
        session.close();
    }

    public void select(){
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Students";
        Query<Students> query = session.createQuery(hql, Students.class);
        List<Students> list = query.list();
        for(Students st: list){
            System.out.println(st.getName() + ":" + st.getAddress() );
        }
        transaction.commit();
        session.close();
    }

    public void delete(){
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Students s = new Students();
        s.setSid(1);
        session.remove(s);
        transaction.commit();
        session.close();
    }

    public static void main(final String[] args) {
        StudentOperations st = new StudentOperations();
        st.create();
        st.update();
        st.select();
        st.delete();

    }
}