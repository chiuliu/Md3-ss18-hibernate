package ra.springmvc_db_hibernate.dao;

import ra.springmvc_db_hibernate.entity.Student;

import java.util.List;

public interface StudentDAO {
   List<Student> getStudents(Integer pageNumber, Integer pageSize);
     Student getStudentById(Integer stuId);
     boolean insertStudent(Student stu);
     boolean updateStudent(Student stu);
     boolean deleteStudent(Integer stuId);
     List<Student> getStudentsByName(String name);

  public Long count();
}
