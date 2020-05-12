package cn.eddie.live.controller;

import cn.eddie.live.config.LiveConfig;
import cn.eddie.live.model.Course;
import cn.eddie.live.model.Live;
import cn.eddie.live.model.Major;
import cn.eddie.live.model.Student;
import cn.eddie.live.service.*;
import cn.eddie.live.vm.LiveViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private LiveService liveService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private MajorService majorDao;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private LiveConfig liveConfig;
    @Autowired
    private StudentService studentService;

    @Autowired
    private LiveMaterialService liveMaterialService;


    @GetMapping("/user")
    public String toUserPage(HttpSession session, Model model) {
        String loginUserKind = session.getAttribute("loginUserKind").toString();
        String url = "redirect:/";
        switch (loginUserKind) {
            case "student":
                String studentId = session.getAttribute("loginUserId").toString();
                url = toStudentUserPage(studentId, model);
                break;
            case "teacher":
                String teacherId = session.getAttribute("loginUserId").toString();
                url = toTeacherUserPage(teacherId, model);
                break;
        }
        return url;
    }

    private String toStudentUserPage(String studentId, Model model) {
        Student student = studentService.findById(studentId);
        if (student == null) {
            return "redirect:/";
        }
        // 过滤时间和专业和年级
        Date yesterday = new Date(System.currentTimeMillis() - 86400000);
        List<Live> liveList = liveService.findByDateAfterAndMajorIdAndGrade(yesterday, student.getMajorId(), student.getGrade());
        List<LiveViewModel> liveVMList = LiveViewModel.loadFromLiveList(liveConfig.getIp(), liveList, teacherService, courseService, majorDao, liveMaterialService);
        model.addAttribute("liveVMList", liveVMList);
        model.addAttribute("active", "live-list");
        return "user-student";
    }

    private String toTeacherUserPage(String teacherId, Model model) {
        List<Live> liveList = liveService.findByTeacherId(teacherId);
        List<LiveViewModel> liveVMList = LiveViewModel.loadFromLiveList(liveConfig.getIp(), liveList, teacherService, courseService, majorDao, liveMaterialService);
        model.addAttribute("liveVMList", liveVMList);
        model.addAttribute("active", "live-list");
        return "user-teacher";
    }

    @GetMapping("/user/create-live")
    public String toCreateLivePage(Model model) {
        List<Course> courseList = courseService.findAll();
        List<Major> majorList = majorDao.findAll();
        model.addAttribute("courseList", courseList);
        model.addAttribute("majorList", majorList);
        model.addAttribute("active", "create-live");
        return "user-teacher-create-live";
    }

    @GetMapping("/user/upload-video")
    public String toUploadVideoPage(Model model) {
        List<Course> courseList = courseService.findAll();
        List<Major> majorList = majorDao.findAll();
        model.addAttribute("courseList", courseList);
        model.addAttribute("majorList", majorList);
        model.addAttribute("active", "upload-video");
        return "user-teacher-upload-video";
    }


}
