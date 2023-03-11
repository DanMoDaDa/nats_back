package org.danmo.controller;

import org.danmo.domain.AjaxResult;
import org.danmo.utils.FastDfsUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class FileController {

    @PostMapping("/file/upload")
    public AjaxResult upload(MultipartFile file) {
        try {
            String url = FastDfsUtil.uploadFile(file);
            return AjaxResult.success(url);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    @PostMapping("/file/delete")
    public AjaxResult delete(String url) {
        try {
            FastDfsUtil.deleteFile(url);
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }
}
