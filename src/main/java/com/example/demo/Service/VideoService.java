package com.example.demo.Service;

import com.example.demo.Mapper.UserMapper;
import com.example.demo.Mapper.VideoExtMapper;
import com.example.demo.Mapper.VideoMapper;
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

}
