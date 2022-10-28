package com.example.lllll.controlers;

import com.example.lllll.Models.Tovar;
import com.example.lllll.Repositories.TovarRepository;
import com.example.lllll.Models.parametris;
import com.example.lllll.Models.Provider;
import com.example.lllll.Models.shop;
import com.example.lllll.Repositories.parametrisRepository;
import com.example.lllll.Repositories.providerRepository;
import com.example.lllll.Repositories.shopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class TovarController {
    @Autowired
    TovarRepository tovarRepository;
    @Autowired
    parametrisRepository ParametrisRepository;
    @Autowired
    shopRepository shopRepository;
    @Autowired
    providerRepository providerRepository;

    @GetMapping("/tovar")
    public String tovar(Model model) {

        Iterable<Tovar> listTovar = tovarRepository.findAll();
        model.addAttribute(("list_tovar"), listTovar);
        return ("tovarPackage/index");
    }

    @GetMapping("/tovarPackage/Add")
    public String TovarAddView(Tovar tovar, Model model) {

        Iterable<parametris> Param = ParametrisRepository.findAll();
        ArrayList<parametris> ParamArrayList = new ArrayList<>();

        for(parametris di: Param) {
            if(di.getOwner() == null) {
                ParamArrayList.add(di);
            }
        }
        Iterable<Provider> provider = providerRepository.findAll();
        model.addAttribute("parametris", ParamArrayList);
        model.addAttribute("provider", provider);
        return ("tovarPackage/tovarAdd");
    }

    @PostMapping("/tovarPackage/Add")
    public String tovarAdd(@Valid Tovar tovar,
                           BindingResult result,
                           @RequestParam String stranaProizv,
                           @RequestParam String name) {
        if(result.hasErrors())
            return ("tovarPackage/tovarAdd");
        parametris pr = ParametrisRepository.findBystranaProizv(stranaProizv);
        Provider provider = providerRepository.findByname(name);
        tovar.setParametris(pr);
        tovar.setProvider(provider);
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

    @GetMapping("/tovar/parametrisAdd")
    public String ParametrisAdd(Model model)
    {

        return ("/tovarPackage/parametrisAdd");
    }

    @PostMapping("/tovar/parametrisAdd")
    public String ParametrisAdd(
            @RequestParam String stranaProizv,
            @RequestParam String proizv
    ) {
        parametris PR = new parametris(stranaProizv,proizv);
        ParametrisRepository.save(PR);
        return ("redirect:/tovar");
    }

    @GetMapping("/tovar/providerAdd")
    public String ProviderAdd(Model model)
    {
        return ("/tovarPackage/providerAdd");
    }

    @PostMapping("/tovar/providerAdd")
    public String ProviderAdd(
            @RequestParam String name,
            @RequestParam String date
    ) {
        Provider prov = new Provider(name,date);
        providerRepository.save(prov);
        return ("redirect:/tovar");
    }

    @GetMapping("/tovar/shopAdd")
    public String tovarAdd(Model model)
    {
        return ("/tovarPackage/shopAdd");
    }
    @GetMapping("/tovar/shops")
    public String shops(Model model)
    {
        Iterable<shop> listShop = shopRepository.findAll();
        model.addAttribute("list_shop",listShop);
        return ("/tovarPackage/shops");
    }


    @PostMapping("/tovar/shopAdd")
    public String shopadd(
            @RequestParam String address,
            @RequestParam String nazvanie
    ) {
        shop shop = new shop(address,nazvanie);
        shopRepository.save(shop);
        return ("redirect:/tovar");
    }

    @GetMapping("/tovar/shop_tovarAdd")
    public String shop_tovarAdd(Model model)
    {
        Iterable<Tovar> tovars = tovarRepository.findAll();
        model.addAttribute("tovar", tovars);
        Iterable<shop> shopp = shopRepository.findAll();
        model.addAttribute("shops", shopp);

        return ("/tovarPackage/shop_tovarAdd");
    }

    @PostMapping("/tovar/shop_tovarAdd")
    public String shop_tovarAdd(
            @RequestParam String shop,
            @RequestParam String tovar
    ) {
        Tovar t = tovarRepository.findBynameTovar(Arrays.stream((tovar.split(" "))).toList().get(0)).get(0);
        shop shops = shopRepository.findByaddress(shop);
        List<shop> ss =t.getShops();
        ss.add(shops);
        t.setShops(ss);
        tovarRepository.save(t);
        return ("redirect:/tovar");
    }
}

