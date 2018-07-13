package com.gzxant.controller.enroll.vedio;

import com.gzxant.annotation.SLog;
import com.gzxant.base.entity.ReturnDTO;
import com.gzxant.base.vo.DataTable;
import com.gzxant.constant.Setting;
import com.gzxant.entity.enroll.vedio.EnrollVedio;
import com.gzxant.service.enroll.vedio.IEnrollVedioService;
import com.gzxant.shiro.GzxantSysUser;
import com.gzxant.util.DateUtils;
import com.gzxant.util.FileUtils;
import com.gzxant.util.PathUtils;
import com.gzxant.util.ReturnDTOUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *     视频controller
 * </p>
 * @author jadenziv
 * @since 2018-07-12
 */
@Controller
@RequestMapping("/vedio")
public class EnrollVedioController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 图片地址
     */

    @Autowired
    private IEnrollVedioService enrollVedioService;

    @GetMapping(value = "")
    @ApiOperation(value = "进入视频信息列表界面",notes="进入视频信息列表界面")
    public String list(Model model){
        return "/enroll/vedio/list";
    }


    @ApiOperation(value = "添加视频信息", notes = "添加视频信息")
    @PostMapping(value = "/insert")
    @ResponseBody
    public ReturnDTO create(EnrollVedio param) {
        enrollVedioService.insert(param);
        return ReturnDTOUtil.success();
    }


    @GetMapping("/insert")
    @ApiOperation(value="进入编辑视频信息",notes = "进入编辑视频信息")
    public String insert(Model model){
        model.addAttribute("action", "insert");
        return "enroll/vedio/insert";
    }

    @PostMapping("/list")
    @ApiOperation(value = "获取视频信息列表界面",notes="获取视频信息列表界面")
    @ResponseBody
    public DataTable<EnrollVedio> list(@RequestBody DataTable<EnrollVedio> dt){
        return enrollVedioService.pageSearch(dt);
    }

    @SLog("批量删除视频信息")
    @ApiOperation(value = "批量删除视频信息", notes = "批量删除视频信息")
    @PostMapping(value = "/delete")
    @ResponseBody
    public ReturnDTO delete(@RequestParam("ids") List<Long> ids) {
        boolean success = enrollVedioService.deleteBatchIds(ids);
        if (success) {
            return ReturnDTOUtil.success();
        }
        return ReturnDTOUtil.fail();
    }

    @ApiOperation(value = "进入视频信息", notes = "进入视频信息")
    @GetMapping(value = "/{action}/{id}")
    public String detail(@PathVariable("action") String action,@PathVariable("id") String id, Model model) {
        EnrollVedio enrollVedio = enrollVedioService.selectById(id);
        model.addAttribute("enrollVedio", enrollVedio);
        model.addAttribute("action", action);
        return "/enroll/vedio/detail";
    }

    @ApiOperation(value = "编辑参赛者信息", notes = "编辑参赛者信息")
    @PostMapping(value = "/update")
    @ResponseBody
    public ReturnDTO update(EnrollVedio param) {
        enrollVedioService.updateById(param);
        return ReturnDTOUtil.success();
    }

    @ApiOperation(value = "后台上传文件", notes = "后台上传文件")
    @PostMapping(value = "/upload/{type}")
    @ResponseBody
    public ReturnDTO uploadTransImg(@PathVariable("type") String type, @RequestParam("file") MultipartFile file,
                                    @RequestParam(value = "path", defaultValue = "") String path,
                                    HttpServletResponse response, HttpServletRequest request) throws IOException {

        response.setContentType("text/html; charset=UTF-8");

        List<Map<String, String>> rt=new ArrayList<>();

        rt.add( upload( type, path, file));

        return ReturnDTOUtil.success(rt);
    }

    private Map upload(String type, String path, MultipartFile file){
        String uuid = FileUtils.createFileName();//创建文件名称

        String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();//扩展名

        String rootPath = PathUtils.getUploadPath();
        if (StringUtils.isNotBlank(path)) {
            path = path.replace("/", File.separator);
            path = path.replace("\\", File.separator);
            rootPath = rootPath + File.separator + path;
        }

        String savePath = rootPath + File.separator + type + File.separator + GzxantSysUser.id() + File.separator + uuid + "." + fileExt;//附件路径+类型（头像、附件等）+名称+扩展名
        File localFile = FileUtils.saveFileToDisk(file, savePath); //保存到磁盘

        String thumbnailName = "";
        if (FileUtils.getImageFormat(fileExt)) {
            //创建缩略图
            thumbnailName = rootPath + File.separator + type + File.separator + GzxantSysUser.id() + File.separator + "s" + File.separator + uuid + "." + fileExt;//附件路径+类型（头像、附件等）+s(文件夹)+名称+扩展名
            FileUtils.createThumbnail(localFile, thumbnailName);
        }

        Map<String, String> rt = new HashMap<String, String>();

        rt.put("uuid", uuid);
        rt.put("path", Setting.BASEFLODER);
        rt.put("ext", fileExt);
        rt.put("url", savePath);
        rt.put("s_url", thumbnailName);
        rt.put("date", DateUtils.getCurDateTime());

        logger.info("上传的文件地址为 fileName={}", savePath);
        return rt;
    }

    private String getFileType(String filePath) {
        Path path = Paths.get(filePath);
        String contentType = null;
        try {
            contentType = Files.probeContentType(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentType;
    }

    /**
     * 视频api
     */
    @RequestMapping(value = "/api/list", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ReturnDTO apiList() {
        List<EnrollVedio> enrollVedioList = enrollVedioService.selectList(null);
        return ReturnDTOUtil.success(enrollVedioList);
    }
}
