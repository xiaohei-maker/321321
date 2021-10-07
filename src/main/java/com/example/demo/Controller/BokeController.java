package com.example.demo.Controller;

import com.example.demo.Model.Boke;
import com.example.demo.Service.BokeService;
import com.example.demo.dto.PaginationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BokeController {
        @Autowired
        private BokeService bokeService;


        @RequestMapping("/boke")
         public  String show(HttpServletRequest request,
                        HttpServletResponse response, Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "12") Integer size){
                    HttpSession session=request.getSession();
                    PaginationDTO paginationDTO= bokeService.selectById(page,size);

                    model.addAttribute("botanist",paginationDTO);
                    return  "boke";
    }
         @RequestMapping("/show")
         public  String showall(Model model,
                                HttpServletRequest request,
                                HttpServletResponse response){

            return  "boke";
        }

    @RequestMapping("/l")
    @ResponseBody
    public  String showa(Model model,
                           HttpServletRequest request,
                           HttpServletResponse response,
                         String ins,
                         String ins1){
        String likecount = ins;
        String listid = ins1;
        String a=bokeService.updata(likecount,listid);
        return  "boke";
    }

}
