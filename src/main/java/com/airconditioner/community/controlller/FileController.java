package com.airconditioner.community.controlller;

import com.airconditioner.community.dto.FileDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author AirConditioner
 * @Date 2022/9/15 11:17
 **/
@RestController
public class FileController {


    @PostMapping("/file/upload")
    public FileDTO upload(){
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setUrl("/image/0191.jpg");
        //fileDTO.setMessage("");;
        return fileDTO;
    }



}
