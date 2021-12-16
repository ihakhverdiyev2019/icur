package az.elixir.icurriculum.controllers;

import az.elixir.icurriculum.models.*;
import az.elixir.icurriculum.services.BadgeService;
import az.elixir.icurriculum.services.ProgramService;
import az.elixir.icurriculum.services.UserCategoryService;
import az.elixir.icurriculum.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RouteController {

    @Autowired
    private UserCategoryService userCategoryService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ProgramService programService;

    @Autowired
    private BadgeService badgeService;


    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String loginPage(){
        return "login";
    }


    @RequestMapping(value = "/view/add-user",method = RequestMethod.GET)
    public String addUserPage(Model model, HttpSession httpSession){
        UsersModel enteranceUser = (UsersModel) httpSession.getAttribute("user");
        if(enteranceUser!=null && enteranceUser.getUserCategoryModel().getCategory().equals("Administrator")) {
            UsersModel usersModel = new UsersModel();
            List<UserCategoryModel> userCategoryModel = userCategoryService.getAllUserCategory();

            model.addAttribute("user", usersModel);

            model.addAttribute("userCategory", userCategoryModel);
            model.addAttribute("enteranceUser", enteranceUser);


            return "add-user";
        }

        return "redirect:/dashboard";
    }

    @RequestMapping(value = "/view/user-list", method = RequestMethod.GET)
    public String listUser( HttpSession httpSession, Model model,RedirectAttributes redirectAttributes){
        UsersModel enteranceUser = (UsersModel) httpSession.getAttribute("user");
        if(enteranceUser!=null && enteranceUser.getUserCategoryModel().getCategory().equals("Administrator")) {

            List<ProgramsModel> programsModels = programService.getAllProgram();
            model.addAttribute("programs", programsModels);

            List<UsersModel> usersModels = usersService.getAllUser();
            model.addAttribute("listUser", usersModels);

            model.addAttribute("enteranceUser", enteranceUser);


            return "user-list";


        }
        return "redirect:/dashboard";
    }


    @RequestMapping(value = "/view/add-program",method = RequestMethod.GET)
    public String addProgramPage(Model model, HttpSession httpSession){
        UsersModel enteranceUser = (UsersModel) httpSession.getAttribute("user");
        if(enteranceUser!=null && enteranceUser.getUserCategoryModel().getCategory().equals("Administrator")) {
            UsersModel usersModel = new UsersModel();

            model.addAttribute("user", usersModel);

            UserCategoryModel userCategoryModelInstructor = userCategoryService.getCategoryByCategoryName("Instructor");
            List<UsersModel> instructors = usersService.getAllUserByCategory(userCategoryModelInstructor);
            model.addAttribute("instructors", instructors);


            UserCategoryModel userCategoryModelStudent = userCategoryService.getCategoryByCategoryName("Student");
            List<UsersModel> students = usersService.getAllUserByCategory(userCategoryModelStudent);
            model.addAttribute("students", students);

            List<BadgesModel> badgesModels = badgeService.getAllBadge();
            model.addAttribute("badges", badgesModels);
            model.addAttribute("enteranceUser", enteranceUser);


            return "add-program";
        }

        return "redirect:/dashboard";
    }


    @RequestMapping(value = "/view/program-list", method = RequestMethod.GET)
    public String listProgram( HttpSession httpSession, Model model,RedirectAttributes redirectAttributes){
        UsersModel enteranceUser = (UsersModel) httpSession.getAttribute("user");
        if(enteranceUser!=null && enteranceUser.getUserCategoryModel().getCategory().equals("Administrator")) {

            List<ProgramsModel> programsModels = programService.getAllProgram();
            model.addAttribute("programs", programsModels);


            model.addAttribute("enteranceUser", enteranceUser);

            return "program-list";


        }
        return "redirect:/dashboard";
    }




    @RequestMapping(value = "/view/add-badge",method = RequestMethod.GET)
    public String addBadgePage(Model model, HttpSession httpSession){
        UsersModel enteranceUser = (UsersModel) httpSession.getAttribute("user");
        if(enteranceUser!=null && enteranceUser.getUserCategoryModel().getCategory().equals("Administrator")) {
            BadgesModel badgesModel = new BadgesModel();

            model.addAttribute("badge", badgesModel);

            model.addAttribute("enteranceUser", enteranceUser);


            return "add-badge";
        }

        return "redirect:/dashboard";
    }


    @RequestMapping(value = "/view/badge-list", method = RequestMethod.GET)
    public String listBadge( HttpSession httpSession, Model model,RedirectAttributes redirectAttributes){
        UsersModel enteranceUser = (UsersModel) httpSession.getAttribute("user");
        if(enteranceUser!=null && enteranceUser.getUserCategoryModel().getCategory().equals("Administrator")) {

            List<BadgesModel> badgesModels = badgeService.getAllBadge();
            model.addAttribute("badges", badgesModels);
            model.addAttribute("enteranceUser", enteranceUser);

            return "badge-list";


        }
        return "redirect:/dashboard";
    }


    @RequestMapping(value = "/view/student-badge-list/{sid}", method = RequestMethod.GET)
    public String listBadgeOfStudent(@PathVariable("sid") String sid, HttpSession httpSession, Model model, RedirectAttributes redirectAttributes){
        UsersModel enteranceUser = (UsersModel) httpSession.getAttribute("user");
        if(enteranceUser!=null && enteranceUser.getUserCategoryModel().getCategory().equals("Administrator")) {

            List<BadgesModel> collections = badgeService.getAllBadgeOfStudents(sid);
            model.addAttribute("stuBadges", collections);
            model.addAttribute("stu", usersService.getById(sid));
            model.addAttribute("enteranceUser", enteranceUser);

            return "student-badge-list";


        }
        return "redirect:/dashboard";
    }



    @RequestMapping(value = "/view/student-form/{sid}/{bid}", method = RequestMethod.GET)
    public String studentForm(@PathVariable("sid") String sid,@PathVariable("bid") String bid, HttpSession httpSession, Model model, RedirectAttributes redirectAttributes){
        UsersModel enteranceUser = (UsersModel) httpSession.getAttribute("user");
        if(enteranceUser!=null && enteranceUser.getUserCategoryModel().getCategory().equals("Administrator")) {

            List<BadgesModel> collections = badgeService.getAllBadgeOfStudents(sid);
            model.addAttribute("stuBadges", collections);
            model.addAttribute("badge", badgeService.getAllBadgeOfStudentsAndBadge(sid,bid));
            model.addAttribute("stu", usersService.getById(sid));
            model.addAttribute("enteranceUser", enteranceUser);

            return "form";


        }
        return "redirect:/dashboard";
    }

}
