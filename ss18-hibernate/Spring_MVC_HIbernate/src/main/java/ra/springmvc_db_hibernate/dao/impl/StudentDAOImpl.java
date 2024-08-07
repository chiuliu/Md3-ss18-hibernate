package ra.springmvc_db_hibernate.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.springmvc_db_hibernate.dao.StudentDAO;
import ra.springmvc_db_hibernate.entity.Student;
import org.hibernate.query.Query;
import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {
    @Autowired
    private SessionFactory sessionFactory;

//    @Override
//    public List<Student> getStudents() {
//        Session session = sessionFactory.openSession();
//        try{
//            List list = session.createQuery("from Student").list();
//            return list;
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }finally {
//            session.close();
//        }
//        return null;
//    }

    @Override
    public List<Student> getStudents(Integer pageNumber, Integer pageSize) {
        Session session = sessionFactory.openSession();
        try{
            Query query = session.createQuery("from Student");
            query.setFirstResult((pageNumber-1) * pageSize);
            query.setMaxResults(pageSize);
            List<Student> list = query.list();
            return list;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public Student getStudentById(Integer stuId) {
        Session session = sessionFactory.openSession();
        try{
            Student student = session.get(Student.class, stuId);
            return student;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean insertStudent(Student stu) {
        Session session = sessionFactory.openSession();
        try{
           session.beginTransaction();
           session.save(stu);
           session.getTransaction().commit(); //Lưu dữ liệu vào ổ đĩa vật lý
           return true;
        }catch (Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback(); //Hồi phục lai dữ liệu trước khi bị lỗi
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean updateStudent(Student stu) {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.update(stu);
            session.getTransaction().commit(); //Lưu dữ liệu vào ổ đĩa vật lý
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback(); //Hồi phục lai dữ liệu trước khi bị lỗi
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean deleteStudent(Integer stuId) {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.delete(getStudentById(stuId)); //Xóa theo object
//            int i = session.createQuery("delete from Student where stuId = :stuId").setParameter("stuId", stuId).executeUpdate();
            session.getTransaction().commit(); //Lưu dữ liệu vào ổ đĩa vật lý
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback(); //Hồi phục lai dữ liệu trước khi bị lỗi
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public List<Student> getStudentsByName(String name) {
        Session session = sessionFactory.openSession();
        try{
            if(name==null || name.length()==0)
                name = "%";
            else
                name = "%"+name+"%";
            List list = session.createQuery("from Student where stuName like :stuName")
                    .setParameter("stuName",name)
                    .list();
            return list;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public Long count() {
        Session session = sessionFactory.openSession();
        try{
            Long count = (Long) session.createQuery("select count(*) from Student").uniqueResult();
            return count;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

}
