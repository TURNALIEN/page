package com.example.page;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class TestPage {
    @Autowired
    UserRepository userRepository;
    @RequestMapping("/page")
    public String testPage(Model model, @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "5") Integer pageSize,HttpSession session){

        Sort.Order order1=new Sort.Order(Sort.Direction.ASC,"id");
        //Sort.Order order2=new Sort.Order(Sort.Direction.ASC,"username");
        Sort sort=new Sort(order1);
        Pageable pageable=new PageRequest(pageNo-1,pageSize,sort);
        Page<User> page=userRepository.findAll(pageable);
        model.addAttribute("count",page.getTotalElements());//总记录数
        model.addAttribute("pageNo",page.getNumber()+1);//当前第几页
        model.addAttribute("total",page.getTotalPages());//总页数
        model.addAttribute("list",page.getContent());//当前页面的list
        model.addAttribute("number",page.getNumberOfElements());//当前页面的记录数


        return "index";
    }
}
