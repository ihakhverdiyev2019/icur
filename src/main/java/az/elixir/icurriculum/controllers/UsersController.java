package az.elixir.icurriculum.controllers;


import az.elixir.icurriculum.dtos.LoginDTO;
import az.elixir.icurriculum.models.UsersModel;
import az.elixir.icurriculum.services.BadgeService;
import az.elixir.icurriculum.services.ProgramService;
import az.elixir.icurriculum.services.UserCategoryService;
import az.elixir.icurriculum.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;


    @Autowired
    private ProgramService programService;

    @Autowired
    private BadgeService badgeService;

    @Autowired
    private UserCategoryService userCategoryService;


    @RequestMapping(value = "/signin",method = RequestMethod.POST)
    public String signin(@Validated @ModelAttribute("login") LoginDTO loginDTO, RedirectAttributes redirectAttributes, HttpSession httpSession) {

        UsersModel usersModel = usersService.findUserbyUsernameAndPass(loginDTO.getUsername(),loginDTO.getPassword());



        if (usersModel!=null) {
            httpSession.setAttribute("user",usersModel);

            return "redirect:/dashboard";
        }

        redirectAttributes.addFlashAttribute("errorLogin", "User not found at database...");

        return "redirect:/";


    }


    @RequestMapping(value = "/dashboard",method = RequestMethod.GET)
    public String dashboard(Model model, HttpSession httpSession, RedirectAttributes redirectAttributes) {

        UsersModel enteranceUser = (UsersModel) httpSession.getAttribute("user");


        model.addAttribute("instructors",usersService.getAllUserByCategory(userCategoryService.getCategoryByCategoryName("Instructor"))==null ? 0 : usersService.getAllUserByCategory(userCategoryService.getCategoryByCategoryName("Instructor")).size());


        model.addAttribute("students",usersService.getAllUserByCategory(userCategoryService.getCategoryByCategoryName("Student"))==null ? 0 : usersService.getAllUserByCategory(userCategoryService.getCategoryByCategoryName("Student")).size());


        model.addAttribute("badges",badgeService.getAllBadge() == null ? 0 : badgeService.getAllBadge().size());


        model.addAttribute("programs",programService.getAllProgram() == null ? 0 : programService.getAllProgram().size());
        model.addAttribute("enteranceUser", enteranceUser);

        if(enteranceUser==null){
            redirectAttributes.addFlashAttribute("error6","Please, enter system first...");
            return "redirect:/";
        }

        return "dashboard";


    }


    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpSession httpSession) {

        httpSession.invalidate();

        return "redirect:/";


    }


    }
