package com.nine.intface.common.controller;

import com.nine.intface.common.vo.Result;
import com.nine.intface.common.vo.ResultFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author : Rubi
 * @version : 2018-10-26 14:47
 */

@Controller
@RequestMapping("/files")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Value("${rubi.file.upload.folder}")
    private String UPLOAD_FOLDER;

    @GetMapping(value = "/index")
    public String index() throws Exception {
        return "my/upload";
    }
    @PostMapping("/upload")
    public String fileUpload(@RequestParam MultipartFile file,Model model) throws Exception {
        Result result;
        logger.info("{}-->{}",file.getOriginalFilename(),file.getSize());
        File dest = new File(UPLOAD_FOLDER  + file.getOriginalFilename());
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        file.transferTo(dest); //保存文件
        result = ResultFactory.getOKRestResult();
        model.addAttribute("result",result);
        return "redirect:/files/index";
    }
}


