package net.javaguides.springboot.controller;


import net.javaguides.springboot.model.Department;
import net.javaguides.springboot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/departments")
    public String viewDepartments(Model model) {
        return findPaginatedDepartments(1, "nom", "asc", model);
    }

    @GetMapping("/showNewDepartmentForm")
    public String showNewDepartmentForm(Model model) {
        Department department = new Department();
        model.addAttribute("department", department);
        return "new_department";
    }

    @PostMapping("/saveDepartment")
    public String saveDepartment(@ModelAttribute("department") Department department) {
        departmentService.saveDepartment(department);
        return "redirect:/departments";
    }

    @GetMapping("/showFormForUpdateDepartment/{id}")
    public String showFormForUpdateDepartment(@PathVariable(value = "id") int id, Model model) {
        Department department = departmentService.getDepartmentById(id);
        model.addAttribute("department", department);
        return "update_department";
    }

    @GetMapping("/deleteDepartment/{id}")
    public String deleteDepartment(@PathVariable(value = "id") int id) {
        departmentService.deleteDepartmentById(id);
        return "redirect:/departments";
    }

    @GetMapping("/department/page/{pageNo}")
    public String findPaginatedDepartments(@PathVariable(value = "pageNo") int pageNo,
                                           @RequestParam("sortField") String sortField,
                                           @RequestParam("sortDir") String sortDir,
                                           Model model) {
        int pageSize = 5;
        Page<Department> page = departmentService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Department> listDepartments = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listDepartments", listDepartments);
        return "departments";
    }
}
