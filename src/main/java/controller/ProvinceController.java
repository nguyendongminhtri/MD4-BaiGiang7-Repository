package controller;

import model.Customer;
import model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import service.impl.CustomerServiceImpl;
import service.impl.ProvinceServiceImpl;

import java.util.List;
import java.util.Optional;

@Controller
public class ProvinceController {
    @Autowired
    private ProvinceServiceImpl provinceService;
    @Autowired
    private CustomerServiceImpl customerService;
    @GetMapping("/province")
    public ModelAndView listProvince(){
        Iterable<Province> provinces = provinceService.findAll();
        ModelAndView modelAndView = new ModelAndView("/province/list");
        modelAndView.addObject("listProvince",provinces);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showFormCreatePorvince(){
        ModelAndView modelAndView = new ModelAndView("/province/add");
        modelAndView.addObject("createProvinceForm",new Province());
        return modelAndView;
    }
    @PostMapping("/create/province")
    public String createProvince(@ModelAttribute("createProvinceForm") Province province){
        provinceService.save(province);
        return "redirect:/province";
    }
    @GetMapping("/detail/province/{id}")
    public ModelAndView detailProvince(@PathVariable Long id){
        Optional<Province> province = provinceService.findById(id);
        Iterable<Customer> customers = customerService.findAllByProvince(province.get());
        ModelAndView modelAndView = new ModelAndView("/province/detail");
        modelAndView.addObject("detailProvince",province.get());
        modelAndView.addObject("customerByProvince",customers);
        return modelAndView;
    }
}
