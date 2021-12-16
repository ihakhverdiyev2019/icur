package az.elixir.icurriculum.controllers;


import az.elixir.icurriculum.dtos.AddBadgeDTO;
import az.elixir.icurriculum.dtos.AddProgramDTO;
import az.elixir.icurriculum.dtos.AddUserDTO;
import az.elixir.icurriculum.dtos.AssignProgramDTO;
import az.elixir.icurriculum.models.BadgesModel;
import az.elixir.icurriculum.models.UsersModel;
import az.elixir.icurriculum.services.BadgeService;
import az.elixir.icurriculum.services.ProgramService;
import az.elixir.icurriculum.services.UsersService;
import az.elixir.icurriculum.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class DashboardController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private BadgeService badgeService;

    @Autowired
    private ProgramService programService;


    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public String addUser(@Validated @ModelAttribute("user") AddUserDTO addUserDTO, @RequestParam("image") MultipartFile multipartFile,HttpSession httpSession, RedirectAttributes redirectAttributes) throws IOException {
        UsersModel enteranceUser = (UsersModel) httpSession.getAttribute("user");
        if (enteranceUser != null && enteranceUser.getUserCategoryModel().getCategory().equals("Administrator")) {

            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

            addUserDTO.setPhoto(fileName);
            String uploadDir = "src/main/resources/static/images/user-profile-photo/";

            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

            usersService.saveUser(addUserDTO);
            return "redirect:/view/user-list";


        }
        return "redirect:/dashboard";
    }

    @RequestMapping(value = "/add-program", method = RequestMethod.POST)
    public String addBadge(@Validated @ModelAttribute("program") AddProgramDTO addProgramDTO, HttpSession httpSession, RedirectAttributes redirectAttributes) {
        UsersModel enteranceUser = (UsersModel) httpSession.getAttribute("user");
        if (enteranceUser != null && (enteranceUser.getUserCategoryModel().getCategory().equals("Administrator")
                ||enteranceUser.getUserCategoryModel().getCategory().equals("Instructor")) ) {

            programService.saveProgram(addProgramDTO);
            return "redirect:/view/program-list";


        }
        return "redirect:/dashboard";
    }


    @RequestMapping(value = "/add-badge", method = RequestMethod.POST)
    public String addUser(@Validated @ModelAttribute("badge") AddBadgeDTO addBadgeDTO, HttpSession httpSession, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        UsersModel enteranceUser = (UsersModel) httpSession.getAttribute("user");
        if (enteranceUser != null && (enteranceUser.getUserCategoryModel().getCategory().equals("Administrator")
                ||enteranceUser.getUserCategoryModel().getCategory().equals("Instructor")) ) {
            BadgesModel badgesModel = new BadgesModel();
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            badgesModel.setPhotos(fileName);
            badgesModel.setBadgeName(addBadgeDTO.getBadgeName());

            BadgesModel savedBadge = badgeService.saveBadge(badgesModel);

            String uploadDir = "src/main/resources/static/images/badge-photo/";

            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            return "redirect:/view/badge-list";


        }
        return "redirect:/dashboard";
    }

    @RequestMapping(value = "/remove-badge/{id}", method = RequestMethod.GET)
    public String removeBadge(@PathVariable("id") String id, HttpSession httpSession) throws IOException {
        UsersModel enteranceUser = (UsersModel) httpSession.getAttribute("user");
        if (enteranceUser != null && enteranceUser.getUserCategoryModel().getCategory().equals("Administrator")) {

            badgeService.removeBadge(Integer.parseInt(id));

            return "redirect:/view/badge-list";

        }


        return "redirect:/dashboard";


    }




    @RequestMapping(value = "/assign-program", method = RequestMethod.POST)
    public String addUser(@Validated @ModelAttribute("assign") AssignProgramDTO assignProgramDTO, HttpSession httpSession){
        UsersModel enteranceUser = (UsersModel) httpSession.getAttribute("user");
        if (enteranceUser != null && enteranceUser.getUserCategoryModel().getCategory().equals("Administrator")) {

            programService.assignProgram(assignProgramDTO);

            return "redirect:/view/user-list";


        }
        return "redirect:/dashboard";
    }


    @RequestMapping(value = "/enable-collect-badge/{pid}/{sid}", method = RequestMethod.GET)
    public String enableCollectBadgeByStudent(@PathVariable("sid") String sid, @PathVariable("pid") String pid, HttpSession httpSession){
        UsersModel enteranceUser = (UsersModel) httpSession.getAttribute("user");
        if (enteranceUser != null && enteranceUser.getUserCategoryModel().getCategory().equals("Administrator")) {

            badgeService.enableTheCollection(sid,pid);

            return "redirect:/view/program-list";


        }
        return "redirect:/dashboard";
    }


    @RequestMapping(value = "/enable-all-collect-badge/{pid}", method = RequestMethod.GET)
    public String enableAllCollectBadgeByStudent(@PathVariable("pid") String pid, HttpSession httpSession){
        UsersModel enteranceUser = (UsersModel) httpSession.getAttribute("user");
        if (enteranceUser != null && enteranceUser.getUserCategoryModel().getCategory().equals("Administrator")) {

            badgeService.enableAllCollection(pid);

            return "redirect:/view/program-list";


        }
        return "redirect:/dashboard";
    }

    @RequestMapping(value = "/disable-all-collect-badge/{pid}", method = RequestMethod.GET)
    public String disableAllCollectBadgeByStudent(@PathVariable("pid") String pid, HttpSession httpSession){
        UsersModel enteranceUser = (UsersModel) httpSession.getAttribute("user");
        if (enteranceUser != null && enteranceUser.getUserCategoryModel().getCategory().equals("Administrator")) {

            badgeService.disableAllCollection(pid);

            return "redirect:/view/program-list";


        }
        return "redirect:/dashboard";
    }


    @RequestMapping(value = "/disable-collect-badge/{pid}/{sid}", method = RequestMethod.GET)
    public String disableCollectBadgeByStudent(@PathVariable("sid") String sid, @PathVariable("pid") String pid, HttpSession httpSession){
        UsersModel enteranceUser = (UsersModel) httpSession.getAttribute("user");
        if (enteranceUser != null && enteranceUser.getUserCategoryModel().getCategory().equals("Administrator")) {

            badgeService.disableTheCollection(sid,pid);

            return "redirect:/view/program-list";


        }
        return "redirect:/dashboard";
    }




    @RequestMapping(value = "/student-unassign/{pid}/{sid}", method = RequestMethod.GET)
    public String unassignStudent(@PathVariable("sid") String sid, @PathVariable("pid") String pid, HttpSession httpSession){
        UsersModel enteranceUser = (UsersModel) httpSession.getAttribute("user");
        if (enteranceUser != null && enteranceUser.getUserCategoryModel().getCategory().equals("Administrator")) {

            programService.unassignStudent(sid,pid);

            return "redirect:/view/program-list";


        }
        return "redirect:/dashboard";
    }



    @RequestMapping(value = "/instructor-unassign/{pid}/{sid}", method = RequestMethod.GET)
    public String unassignInstructor(@PathVariable("sid") String sid, @PathVariable("pid") String pid, HttpSession httpSession){
        UsersModel enteranceUser = (UsersModel) httpSession.getAttribute("user");
        if (enteranceUser != null && enteranceUser.getUserCategoryModel().getCategory().equals("Administrator")) {

            programService.unassignInstructor(sid,pid);

            return "redirect:/view/program-list";


        }
        return "redirect:/dashboard";
    }



    @RequestMapping(value = "/student-badge-list/{sid}", method = RequestMethod.GET)
    public String listOfBadgeStudent(@PathVariable("sid") String sid, HttpSession httpSession){
        UsersModel enteranceUser = (UsersModel) httpSession.getAttribute("user");
        if (enteranceUser != null && enteranceUser.getUserCategoryModel().getCategory().equals("Administrator")) {


            return "redirect:/view/program-list";


        }
        return "redirect:/dashboard";
    }
}
