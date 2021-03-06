package com.example.demo.Controller;

import com.example.demo.Service.CommentService;
import com.example.demo.Service.QuestionService;
import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.QuestionDTO;
import com.example.demo.enums.CommentTypeEnum;
import com.example.demo.exception.CustomizeErrorCode;
import com.example.demo.exception.CustomizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by codedrinker on 2019/5/21.
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id, Model model) {



        Long questionId = null;
        try {
            questionId = Long.parseLong(String.valueOf(id));
        } catch (NumberFormatException ex) {
            throw new CustomizeException(CustomizeErrorCode.INVALID_INPUT);
        }
        QuestionDTO questionDTO = questionService.getById(questionId);
        List<QuestionDTO> relatedQuestions = questionService.selectRelated(questionDTO);
        List<CommentDTO> comments = commentService.listByTargetId(questionId, CommentTypeEnum.QUESTION);
        //累加阅读数
        questionService.incView(questionId);
        //累加喜欢数

//        commentService.incLikeCount(likecount,listid);

        model.addAttribute("question", questionDTO);
        model.addAttribute("comments", comments);
        for (CommentDTO x:comments){
            Integer a=x.getLikeCount();
        }
        model.addAttribute("relatedQuestions", relatedQuestions);
        return "question";
    }
    @RequestMapping("/ll")
    @ResponseBody
    public  String showa(Model model,
                         HttpServletRequest request,
                         HttpServletResponse response,
                         Integer ins,
                         String ins1){
        Integer likecount = ins;
        String listid = ins1;
        commentService.incLikeCount(likecount,listid);
        return "question";
    }
}
