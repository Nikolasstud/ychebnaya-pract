package com.example.lllll.controlers;

import com.example.lllll.Models.Tovar;
import com.example.lllll.Repositories.TovarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
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

    @GetMapping("/tovarPackage/Add")
    public String TovarAddView(Tovar tovar) {
        return ("tovarPackage/tovarAdd");
    }

    @PostMapping("/tovarPackage/Add")
    public String tovarAdd(@Valid Tovar tovar,
                           BindingResult result) {
        if(result.hasErrors())
            return ("tovarPackage/tovarAdd");

        tovarRepository.save(tovar);
        return ("redirect:/tovar");
    }

    @GetMapping("/tovarPackage/filterACCU")
    public String EmployeeFilterACCU(Model model,
                                     @RequestParam(name = "search") String nameTovar) {

        List<Tovar> tovarList = tovarRepository.findBynameTovar(nameTovar);
        model.addAttribute("searchRes", tovarList);
        return ("tovarPackage/tovarFilter");
    }

    @GetMapping("/tovarPackage/filter")
    public String TovarFilter(Model model,
                                 @RequestParam(name = "search") String nameTovar) {

        List<Tovar> tovarList = tovarRepository.findBynameTovarContains(nameTovar);
        model.addAttribute("searchRes", tovarList);
        return ("tovarPackage/tovarFilter");
    }

    @GetMapping("/tovarPackage/details/{id}")
    public String TovarDetails(Model model,
                                  @PathVariable long id) {

        Optional<Tovar> t = tovarRepository.findById(id);
        ArrayList<Tovar> result = new ArrayList<>();

        t.ifPresent(result::add);
        model.addAttribute("tovar", result);
        return ("/tovarPackage/tovarDetails");
    }

    @GetMapping("tovarPackage/delete/{id}")
    public String tovarDelete(@PathVariable Long id) {

        tovarRepository.deleteById(id);
        return("redirect:/tovar");
    }


    @GetMapping("tovarPackage/tovarEdit/{id}")
    public String TovarEdit(Model model,
                                @PathVariable long id) {

        Tovar emp = tovarRepository.findById(id).orElseThrow();
        model.addAttribute("tovar", emp);
        return("tovarPackage/tovarEdit");
    }

    @PostMapping("tovarPackage/tovarEdit/{id}")
    public String TovarEdit(@Valid Tovar tovar,
                                BindingResult bindingResult
    ){
        if(bindingResult.hasErrors())
            return("tovarPackage/tovarEdit");

        tovarRepository.save(tovar);

        return("redirect:/tovarPackage/details/" + tovar.getId());
    }
}
