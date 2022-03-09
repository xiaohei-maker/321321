package com.example.demo.Controller;

import com.example.demo.Model.Video;
import com.example.demo.Model.Videolist;
import com.example.demo.Service.VideoService;
import com.example.demo.dto.PaginationDTO;
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

    @GetMapping("/videoselect")
    public  String videoselect(HttpServletRequest request,
                        HttpServletResponse response, Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "36") Integer size){
        String title=request.getParameter("搜索");
//        System.out.print(title);
//        HttpSession session=request.getSession();
        PaginationDTO paginationDTO= videoService.selectByTitle(title,page,size);
        model.addAttribute("panelist",paginationDTO);
        model.addAttribute("title",title);
        return  "videlicet";
    }

    //试验
    @GetMapping("/vod-list-id-/{typeid}")
    public  String showlist1(HttpServletRequest request,
                        HttpServletResponse response, Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "36") Integer size,
                             @PathVariable(name = "typeid") Integer typeid){
        HttpSession session=request.getSession();
        PaginationDTO paginationDTO= videoService.selectByTidAndType(typeid,page,size);
        model.addAttribute("types",typeid);
        model.addAttribute("panelist",paginationDTO);
        return  "vod-list-id";
    }

    @RequestMapping("/show/{ListId}")
    public String sh(HttpServletRequest request,
                     HttpServletResponse response,
                     @PathVariable(name ="ListId") Integer ListId,
                     Model model){

//            Integer a=vId;
        HttpSession session=request.getSession();
        List<Videolist> videoList= videoService.selectByListid(ListId);


        Integer p=0;
        for (Videolist a:videoList)
        {
            p++;
        }

        Videolist t ;
        Videolist h;
        for (int i = 0; i < p - 1; i++){
            for (int j = 0; j < p - 1 - i; j++){
                Integer m= Integer.valueOf(videoList.get(j).getNumber());
                Integer n= Integer.valueOf(videoList.get(j+1).getNumber());
                if (m> n) {
                    t = videoList.get(j);
                    h=videoList.get(j+1);
                    videoList.set(j,h);
                    videoList.set(j+1,t);
                }
            }
        }

        Video video= videoService.selectBId(ListId);
        session.setAttribute("videoList",videoList);
        model.addAttribute("videoList",videoList);
        model.addAttribute("videos",video);
        return  "videodetail";
    }

    @RequestMapping("/show/{vId}/{pId}")
    public String sh(Model model,
                     HttpServletRequest request,
                     HttpServletResponse response,
                     @PathVariable(name ="vId") Integer vId,
                     @PathVariable(name ="pId") Integer pId){
//        s[0]="https://jiexi.dplayer.club/m3u8.php?url=https://v.youku.com/v_show/id_XNTc2NTI4MDQ0.html";

        Videolist videolist= (Videolist) videoService.selectByListidAndNumber(vId,pId);
        Video video= videoService.selectBId(vId);
        model.addAttribute("videoss",video);
        model.addAttribute("mo",videolist.getHref());
//        System.out.print(videolist.getHref());
        return  "videoAllDetail";



//        HttpSession session=request.getSession();
//        List<Video> videoList= videoService.selectByTid();
//        session.setAttribute("Violist",videoList);

       // src="https://jiexi.dplayer.club/m3u8.php?url=https://v.youku.com/v_show/"+""

    }

}
