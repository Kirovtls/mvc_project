package peaksoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Course;
import peaksoft.entities.Instructor;
import peaksoft.entities.Lesson;
import peaksoft.service.CompanyService;
import peaksoft.service.CourseService;
import peaksoft.service.InstructorService;
import peaksoft.service.StudentService;

@Controller
public class CourseController {
    private final CourseService courseService;
    private final CompanyService companyService;
    private final InstructorService instructorService;

    private final StudentService studentService;

    @Autowired
    public CourseController(CourseService courseService, CompanyService companyService, InstructorService instructorService, StudentService studentService) {
        this.courseService = courseService;
        this.companyService = companyService;
        this.instructorService = instructorService;
        this.studentService = studentService;
    }

    @GetMapping("/courses/{id}")
    public String getAllCourses(@PathVariable Long id, Model model,
                                @ModelAttribute("inst") Instructor instructor) {
        model.addAttribute("courses", courseService.getAllCourses(id));
        model.addAttribute("companyId",id);
        model.addAttribute("instructors", instructorService.getAllInstructors(id));
        model.addAttribute("students", studentService.getAllStudents(id));
        return "course/courses";
    }


    @PostMapping("/{companyId}/{courseId}/assignInsToCourse")
    public String assignInstructor(@PathVariable("courseId") Long courseId,
                                   @PathVariable("companyId") Long companyId,
                                   @ModelAttribute("inst") Instructor instructor){
        instructorService.assignInstructor(courseId, instructor.getId());
        return "redirect:/courses/" + companyId;
    }



    @GetMapping("/getCourses/{id}")
    public String getCourseById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("course", courseService.getCourseById(id));
        return "course/courses";
    }

    @GetMapping("/courses/{id}/addCourse")
    public String addCourse(@PathVariable Long id, Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("companyId", id);
        return "course/addCourse";
    }

    @PostMapping("/{id}/saveCourse")
    public String saveCourse(@ModelAttribute("course") Course course,
                             @PathVariable Long id) {
        courseService.addCourse(course, id);
        return "redirect:/courses/"+id;
    }

    @GetMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") Long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        model.addAttribute("companyId", course.getCompany().getId());
        return "course/updateCourse";
    }

    @PostMapping("/{companyId}/{id}/saveUpdateCourse")
    public String saveUpdateCourse(@PathVariable("companyId") Long companyId,
                                   @PathVariable("id") Long id,
                                   @ModelAttribute("course") Course course) {
        courseService.updateCourse(course,id);
        return "redirect:/courses/"+companyId;
    }

    @GetMapping("/{companyId}/{id}/deleteCourse")
    public String deleteCourse(@PathVariable("id") Long id, @PathVariable("companyId") Long companyId) {
        courseService.deleteCourse(id);
        return "redirect:/courses/"+companyId;
    }
}
