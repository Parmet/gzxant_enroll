package com.gzxant.controller.upload;

import com.gzxant.base.entity.ReturnDTO;
import com.gzxant.enums.HttpCodeEnum;
import com.gzxant.util.ReturnDTOUtil;
import com.gzxant.utils.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: Fatal
 * @date: 2018/7/14 0014 9:46
 */
@Controller
@PropertySource(value= {"classpath:picture.properties"})
public class ImageController {

    @Value("${PICTURE_PATH}")
    private String PICTURE_PATH;

    @Value("${LOCAL_PATH}")
    private String LOCAL_PATH;

    @PostMapping("/upload")
    @ResponseBody
    private ReturnDTO fileUpload(HttpServletRequest request) {

        Map<String, Object> map = new HashMap<String, Object>();
        /*CommonsMultipartResolver commonsMultipartResolver =
                new CommonsMultipartResolver(request.getSession().getServletContext());*/
        MultipartHttpServletRequest mreq = (MultipartHttpServletRequest) request;
        Iterator<String> fileNames = mreq.getFileNames();
        while (fileNames.hasNext()) {
            MultipartFile file = mreq.getFile(fileNames.next());
            if (file != null) {
                String myFileName = file.getOriginalFilename();
                System.out.println("myFileName:" + myFileName);
                if (myFileName.trim() != "") {
                    String filename = file.getOriginalFilename();

                    String fileBaseName = filename.substring(0, filename.lastIndexOf("."));
                    String fileExt = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();

                    //时间戳生产文件名
                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
                    String newFileName = format.format(new Date());
                    newFileName = newFileName + new Random().nextInt(100000000) + "." + fileExt;

//                    String filepath = request.getSession().getServletContext().getRealPath("/") + "upload\\" + newFileName;
//                    File localFile = new File(filepath);
//                    newFileName = "http://localhost:8084/gzxant/enroll/upload/" + newFileName;
                    try {
                        FileUtil.uploadFile(file.getBytes(), PICTURE_PATH, newFileName);
                    } catch (Exception e) {

                    }
                    map.put("name", fileBaseName);
                    map.put("fileName", newFileName);
                    map.put("path", LOCAL_PATH);
                    return ReturnDTOUtil.success(map);
                }
            }
        }
        return ReturnDTOUtil.fail();
    }

}
