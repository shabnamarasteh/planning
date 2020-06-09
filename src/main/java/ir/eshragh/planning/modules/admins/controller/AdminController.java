package ir.eshragh.planning.modules.admins.controller;

import ir.eshragh.planning.architecture.annotation.AdminAnnotation;
import ir.eshragh.planning.architecture.interfaces.controller.ControllerInterface;
import ir.eshragh.planning.modules.admins.entity.Admin;
import ir.eshragh.planning.modules.admins.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;

@AdminAnnotation
@Controller
@RequestMapping(value = {"/admin" , "/admin/"})
public class AdminController implements ControllerInterface {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping(value = {"/register" , "/register/"} , method = RequestMethod.GET)
    public String registerAdminForm(Model model){
        model.addAttribute("admin" , new Admin());
        return "admin/register";
    }

    @RequestMapping(value = {"/delete/{id}" , "/delete/{id}/"} , method = RequestMethod.GET)
    public String deleteAdminForm(Model model, @PathVariable("id") long id){
        this.adminService.delete(id);
        return "redirect:/admin/";
    }

    @RequestMapping(value = {"/register", "/register"} , method = RequestMethod.POST)
    public String registerAdmin(@ModelAttribute Admin admin){
        this.adminService.addNewItem(admin);

        return "redirect:/admin/";
    }

    @RequestMapping(value = {"/edit/{id}" , "/edit/{id}/"} , method = RequestMethod.GET)
    public String editAdminForm(Model model, @PathVariable("id") Long id){
        model.addAttribute("admin" , this.adminService.findById(id));
        return "admin/register";
    }

    @RequestMapping(value = {"/" , ""})
    public String allAdmins(Model model){
        model.addAttribute("admins", this.adminService.findAllAdmin());
        return "admin/allAdmins";
    }
}
