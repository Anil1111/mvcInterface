package com.nine.intface.common.controller;

import com.nine.intface.common.vo.Result;
import com.nine.intface.common.vo.ResultFactory;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Controller
@RequestMapping("/files")
public class FileController {

    @Value("${rubi.file.upload.folder}")
    private String UPLOAD_FOLDER;

    @GetMapping(value = "/index")
    public String index() throws Exception {
        return "my/upload";
    }
    @PostMapping("/upload")
    @ResponseBody
    public Result fileUpload(@RequestParam MultipartFile file,Model model) throws Exception {
        Result result;
        log.info("{}-->{}",file.getOriginalFilename(),file.getSize());
        File dest = new File(UPLOAD_FOLDER  + file.getOriginalFilename());
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdirs();
        }
        file.transferTo(dest); //保存文件
        result = ResultFactory.getOKRestResult();
        return result;
    }
}


