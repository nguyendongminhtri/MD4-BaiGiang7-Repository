package controller;

import model.Customer;
import model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import service.impl.CustomerServiceImpl;
import service.impl.ProvinceServiceImpl;

@Controller
public class CustomerController {
    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    private ProvinceServiceImpl provinceService;
    @ModelAttribute("listProvince")
    public Iterable<Province> listProvince(){
        return provinceService.findAll();
    }
    @GetMapping("/")
    public ModelAndView listCustomer(){
        Iterable<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("listCustomer",customers);
        return modelAndView;
    }
    @GetMapping("/create-customer")
    public ModelAndView showFormCreateCustomer(){
        ModelAndView modelAndView = new ModelAndView("/customer/add");
        modelAndView.addObject("creatCustomerForm", new Customer());
        return modelAndView;
    }
    @PostMapping("/create/customer")
    public String createCustomer(@ModelAttribute("creatCustomerForm") Customer customer){
        customerService.save(customer);
        return "redirect:/";
    }
}
