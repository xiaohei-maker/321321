package com.example.demo.Service;

import com.example.demo.Mapper.BokeMapper;
import com.example.demo.Model.*;
import com.example.demo.dto.PaginationDTO;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BokeService {
    @Autowired
    private BokeMapper bokeMapper;
    public PaginationDTO selectById(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();

        BokeExample bokeExample=new BokeExample();
        bokeExample.createCriteria().andIdIsNotNull();

        Integer totalPage;
        Integer totalCount=(int) bokeMapper.countByExample(bokeExample);

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
        List<Boke> bokeList=bokeMapper.selectByExampleWithRowbounds(bokeExample,new RowBounds(offset,size));
        paginationDTO.setData(bokeList);
        return  paginationDTO;
    }


    public String updata(String likecount, String listid) {
        BokeExample bokeExample = new BokeExample();
//        bokeExample.createCriteria().
//                andDescriptionEqualTo(someone);
        bokeExample.createCriteria().
                andIdEqualTo(Integer.valueOf(listid));
        List<Boke> bokes=bokeMapper.selectByExample(bokeExample);
        if (bokes.size() != 0) {
            // 插入
            Boke updateUser = new Boke();
            updateUser.setLikecount(Integer.valueOf(likecount));
            bokeMapper.updateByExampleSelective(updateUser, bokeExample);
        } else {

        }
        return  null;
    }
}
