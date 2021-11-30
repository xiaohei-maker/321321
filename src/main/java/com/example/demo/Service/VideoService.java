package com.example.demo.Service;

import com.example.demo.Mapper.*;
import com.example.demo.Model.*;
import com.example.demo.dto.PaginationDTO;
import com.example.demo.dto.QuestionDTO;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {
   @Autowired
   private  VideoMapper videoMapper;

    @Autowired
    private VideoExtMapper videoExtMapper;

    @Autowired
    private VideolistMapper videolistMapper;

    @Autowired
    private VideolistExtMapper videolistExtMapper;

//    @Autowired
//    private VideolistE
    public PaginationDTO selectByTid(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();

        VideoExample videoExample=new VideoExample();
        videoExample.createCriteria().andIdIsNotNull();

        Integer totalPage;
        Integer totalCount=(int) videoMapper.countByExample(videoExample);

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage, page);

        Integer offset = size * (page - 1);
        List<Video>  videoList=videoMapper.selectByExampleWithRowbounds(videoExample,new RowBounds(offset,size));
        paginationDTO.setData(videoList);


        return  paginationDTO;
    }

    public  PaginationDTO selectByTidAndType(Integer type,Integer page,Integer size){
        PaginationDTO paginationDTO=new PaginationDTO();
        VideoExample videoExample=new VideoExample();
        videoExample.createCriteria().andTypeEqualTo(type);

        Integer totalPage;
        Integer totalCount=(int)videoMapper.countByExample(videoExample);
        if(totalCount%size==0){
            totalPage=totalCount/size;
        }else {
            totalPage=totalCount/size+1;
        }
        if(page<1){
            page=1;
        }
        if(page>totalPage){
            page=totalPage;
        }
        paginationDTO.setPagination(totalPage,page);
        Integer offset=size*(page-1);
        List<Video> videoList=videoMapper.selectByExampleWithRowbounds(videoExample,new RowBounds(offset,size));
        paginationDTO.setData(videoList);
        return  paginationDTO;
    }

    public  List<Videolist>  selectByListid(Integer ListId){
       VideolistExample videolistExample=new VideolistExample();
       videolistExample.createCriteria().andListidEqualTo(String.valueOf(ListId));
       List<Videolist> videolists=videolistMapper.selectByExample(videolistExample);
       return  videolists;
    }

    public Object selectByListidAndNumber(Integer vId, Integer pId) {
        Videolist videolist=new Videolist();
        videolist.setListid(vId.toString());
        videolist.setNumber(pId.toString());
         Videolist videolists= videolistExtMapper.selectVideolist(videolist);
         System.out.print(videolists);
        return  videolists;
    }

    public Video selectBId(Integer listId) {
        VideoExample videoExample=new VideoExample();
        videoExample.createCriteria().andIdEqualTo(listId.toString());
        List<Video> list=videoMapper.selectByExample(videoExample);
        return  list.get(0);
    }

    public PaginationDTO selectByTitle(String title, Integer page, Integer size) {
        PaginationDTO paginationDTO=new PaginationDTO();
        VideoExample videoExample=new VideoExample();
        videoExample.createCriteria().andTitleEqualTo(title);

        Integer totalPage;
        Integer totalCount=(int)videoMapper.countByExample(videoExample);
        if(totalCount%size==0){
            totalPage=totalCount/size;
        }else {
            totalPage=totalCount/size+1;
        }
        if(page<1){
            page=1;
        }
        if(page>totalPage){
            page=totalPage;
        }
        paginationDTO.setPagination(totalPage,page);
        Integer offset=size*(page-1);
        List<Video> videoList=videoMapper.selectByExampleWithRowbounds(videoExample,new RowBounds(offset,size));
        paginationDTO.setData(videoList);
        return  paginationDTO;
    }
}
