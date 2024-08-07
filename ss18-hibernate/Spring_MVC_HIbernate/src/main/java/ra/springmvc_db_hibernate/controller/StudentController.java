package ra.springmvc_db_hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ra.springmvc_db_hibernate.dao.StudentDAO;
import ra.springmvc_db_hibernate.entity.Student;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentDAO studentDAO;

//    @InitBinder
//    public void initBinder(WebDataBinder binder){
//        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
//        binder.registerCustomEditor(Date.class,new CustomDateEditor(sf,false));
//    }

    @RequestMapping("/initInsertStudent")
    public String initInsertStudent(Model model){
        Student s = new Student();
        model.addAttribute("s",s);
        return "addStudent";
    }

    @RequestMapping("/insertStudent")
    public String insertStudent(@ModelAttribute("s")Student stu, Model model){
        boolean bl = studentDAO.insertStudent(stu);
        if(bl){
            return "redirect:/loadStudents"; //Gọi tiếp tới method trên controller
        }else{
            model.addAttribute("err","Insert failed!");
            model.addAttribute("s",stu);
            return "addStudent";
        }
    }


    @RequestMapping(value ={"/","/loadStudents"})
    public String loadStudents(Model model, @RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "2") int pageSize){
        List<Student> list = studentDAO.getStudents(pageNumber, pageSize);
        Long totalStudents = studentDAO.count();
        int totalPages = (int) Math.ceil((double) totalStudents / pageSize);
        model.addAttribute("list",list);
        model.addAttribute("pageNumber",pageNumber);
        model.addAttribute("totalPages", totalPages);
        return "listStudent";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(Model model,@RequestParam("id") Integer id) {
        Student student = studentDAO.getStudentById(id);
        model.addAttribute("st", student);
        return "editStudent";
    }

    @PostMapping("/updateStudent")
    public String updateStudent(@ModelAttribute("st") Student student){
        studentDAO.updateStudent(student);
        return "redirect:/loadStudents";

    }

    @GetMapping("/deleteStudent")
    public String deleteStudent(Integer id){
        studentDAO.deleteStudent(id);
        return "redirect:/loadStudents";
    }

    @GetMapping("/search")
    public String search(@RequestParam("searchName") String searchName, Model model) {
        if (searchName==null){
            return "redirect:/loadStudents";
        }else {
            List<Student> students = studentDAO.getStudentsByName(searchName);
            model.addAttribute("list", students);
            return "listStudent";
        }



    }
}
