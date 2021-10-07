package com.example.demo.Controller;

import com.example.demo.Model.Video;
import com.example.demo.Service.UserService;
import com.example.demo.Service.VideoService;
import com.example.demo.dto.PaginationDTO;
import com.example.uitils.Base64Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class VideoController {
    @Autowired
    private VideoService videoService;
    @GetMapping("/shiping")
    public  String show(HttpServletRequest request,
                        HttpServletResponse response, Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "36") Integer size){
        HttpSession session=request.getSession();
        PaginationDTO paginationDTO= videoService.selectByTid(page,size);
        model.addAttribute("panelist",paginationDTO);
        return  "video";
    }

    @RequestMapping("/show/{vId}")
    public String sh(HttpServletRequest request,
                     HttpServletResponse response,
                     @PathVariable(name ="vId") Integer vId){

            Integer a=vId;
//        HttpSession session=request.getSession();
//        List<Video> videoList= videoService.selectByTid();
//        session.setAttribute("Violist",videoList);
        return  "videodetail";
    }

    @RequestMapping("/show/{vId}/{pId}")
    public String sh(Model model,
                     HttpServletRequest request,
                     HttpServletResponse response,
                     @PathVariable(name ="vId") Integer vId,
                     @PathVariable(name ="pId") Integer pId){

        Integer a=vId;
        Integer b=pId;

        String  s[]=new String[27];
        s[0]="https://jiexi.dplayer.club/m3u8.php?url=https://v.youku.com/v_show/id_XNTc2NTI4MDQ0.html";
        s[1]="https://jiexi.dplayer.club/m3u8.php?url=https://v.youku.com/v_show/id_XNTc2NTg3MjY4.html";
        s[2]="https://jiexi.dplayer.club/m3u8.php?url=https://v.youku.com/v_show/id_XNTc2NTg5NjY4.html";
        s[3]="https://jiexi.dplayer.club/m3u8.php?url=https://v.youku.com/v_show/id_XNTc2NTkyMTMy.html";
        s[4]="https://jiexi.dplayer.club/m3u8.php?url=https://v.youku.com/v_show/id_XNTc2NTk0MTQ0.html";
        s[5]="https://jiexi.dplayer.club/m3u8.php?url=https://v.youku.com/v_show/id_XNTc2NTk0NjQ4.html";
        s[6]="https://jiexi.dplayer.club/m3u8.php?url=https://v.youku.com/v_show/id_XNTc2NTk1MDQ0.html";
        s[7]="https://jiexi.dplayer.club/m3u8.php?url=https://v.youku.com/v_show/id_XNTc2NTk5ODUy.html";
        s[8]="https://jiexi.dplayer.club/m3u8.php?url=https://v.youku.com/v_show/id_XNTc2NjU4NzI0.html";
        s[9]="https://jiexi.dplayer.club/m3u8.php?url=https://v.youku.com/v_show/id_XNTc2NjU5ODEy.html";
        s[10]="https://jiexi.dplayer.club/m3u8.php?url=https://v.youku.com/v_show/id_XNTc2NjYwMzQ4.html";
        s[11]="https://jiexi.dplayer.club/m3u8.php?url=https://v.youku.com/v_show/id_XNTc2NjYxMDQw.html";
        s[12]="https://jiexi.dplayer.club/m3u8.php?url=https://v.youku.com/v_show/id_XNTc2NjYxNTA0.html";
        s[13]="https://jiexi.dplayer.club/m3u8.php?url=https://v.youku.com/v_show/id_XNTc2NjYyMTU2.html";
        s[14]="https://jiexi.dplayer.club/m3u8.php?url=https://v.youku.com/v_show/id_XNTc2NjYyODE2.html";
        s[15]="https://jiexi.dplayer.club/m3u8.php?url=https://v.youku.com/v_show/id_XNTc2NjY0NDA0.html";
        s[16]="https://jiexi.dplayer.club/m3u8.php?url=https://v.youku.com/v_show/id_XNTc2NjY1Mjky.html";
        s[17]="https://jiexi.dplayer.club/m3u8.php?url=https://v.youku.com/v_show/id_XNTc2NjY2MDgw.html";
        s[18]="https://jiexi.dplayer.club/m3u8.php?url=https://v.youku.com/v_show/id_XNTc2NjY3MjYw.html";
        s[19]="https://jiexi.dplayer.club/m3u8.php?url=https://v.youku.com/v_show/id_XNTc2NjY4MDg4.html";
        s[20]="https://jiexi.dplayer.club/m3u8.php?url=https://v.youku.com/v_show/id_XNTc2NjY4NjEy.html";
        s[21]="https://jiexi.dplayer.club/m3u8.php?url=https://v.youku.com/v_show/id_XNTc2NjY4ODQ0.html";
        s[22]="https://jiexi.dplayer.club/m3u8.php?url=https://v.youku.com/v_show/id_XNjQ5NzUyOTc2.html";
        s[23]="https://jiexi.dplayer.club/m3u8.php?url=https://v.youku.com/v_show/id_XNjQ5NzUzNTQw.html";
        s[24]="https://jiexi.dplayer.club/m3u8.php?url=https://v.youku.com/v_show/id_XNTc2NjY5MjAw.html";
        s[25]="https://jiexi.dplayer.club/m3u8.php?url=https://v.youku.com/v_show/id_XNTc2NjY5NDk2.html";
        String mo=s[pId-1];




//        HttpSession session=request.getSession();
//        List<Video> videoList= videoService.selectByTid();
//        session.setAttribute("Violist",videoList);

       // src="https://jiexi.dplayer.club/m3u8.php?url=https://v.youku.com/v_show/"+""
        model.addAttribute("mo",mo);
        return  "videoAllDetail";
    }

}
