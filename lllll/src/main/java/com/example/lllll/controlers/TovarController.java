package com.example.lllll.controlers;

import com.example.lllll.Models.Tovar;
import com.example.lllll.Repositories.TovarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
public class TovarController {
    @Autowired
    TovarRepository tovarRepository;

    @GetMapping("/tovar")
    public String tovar(Model model) {

        Iterable<Tovar> listTovar = tovarRepository.findAll();
        model.addAttribute(("list_tovar"), listTovar);
        return ("tovarPackage/index");
    }

    @GetMapping("/tovarPackage/tovarAdd")
    public String TovarAddView() {return ("tovarPackage/tovarAdd");}

    @PostMapping("/tovarPackage/tovarAdd")
    public String tovarAdd(@RequestParam String tovar,
                              @RequestParam Integer articul,
                              @RequestParam String cvet,
                              @RequestParam Integer kolvo,
                              @RequestParam Integer ves) {


        Tovar tovars = new Tovar(tovar,articul,ves,cvet,kolvo);
        tovarRepository.save(tovars);
        return ("redirect:/tovar");
    }

    @GetMapping("/tovarPackage/filterACCU")
    public String EmployeeFilterACCU(Model model,
                                     @RequestParam(name = "search") String tovar) {

        List<Tovar> tovarList = tovarRepository.findByTovar(tovar);
        model.addAttribute("searchRes", tovarList);
        return ("tovarPackage/tovarFilter");
    }

    @GetMapping("/tovarPackage/filter")
    public String EmployeeFilter(Model model,
                                 @RequestParam(name = "search") String tovar) {

        List<Tovar> tovarList = tovarRepository.findByTovarContains(tovar);
        model.addAttribute("searchRes", tovarList);
        return ("tovarPackage/tovarFilter");
    }

    @GetMapping("/tovarPackage/details/{id}")
    public String TovarDetails(Model model,
                                  @PathVariable long id) {

        Optional<Tovar> tov = tovarRepository.findById(id);
        ArrayList<Tovar> result = new ArrayList<>();

        tov.ifPresent(result::add);
        model.addAttribute("tovar", result);
        return ("/tovarPackage/tovarDetails");
    }

    @GetMapping("tovarPackage/delete/{id}")
    public String tovarDelete(@PathVariable Long id) {

        tovarRepository.deleteById(id);
        return("redirect:/tovar");
    }

    @GetMapping("tovarPackage/tovarEdit/{id}")
    public String CarEdit(Model model,
                          @PathVariable long id) {

        Tovar emp = tovarRepository.findById(id).orElseThrow();
        model.addAttribute("tovar", emp);
        return("/tovarPackage/tovarEdit");
    }
    @PostMapping("tovarPackage/tovarEdit/{id}")
    public String TovarEdit(Model model,
                               @PathVariable long id,
                               @RequestParam String tovar,
                               @RequestParam Integer articul,
                               @RequestParam Integer ves,
                               @RequestParam Integer cvet,
                               @RequestParam Integer kolvo) {

        Tovar emp = tovarRepository.findById(id).orElseThrow();
        emp.setTovar(tovar);
        emp.setArticul(articul);
        emp.setVes(ves);
        emp.setCvet(cvet);
        emp.setKolvo(kolvo);
        model.addAttribute("tovar", emp);
        tovarRepository.save(emp);
        return("/tovarPackage/tovarDetails");
    }
}
