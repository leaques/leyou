package com.leyou.upload.service;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UploadService {

    private static final List<String> allowTypes = Arrays.asList("image/jpeg", "image/png", "image/bmp");

    public String uploadImage(MultipartFile file) {
        try {
            log.error("开始上传");
            // 校验文件类型
            String contentType = file.getContentType();
            if (!allowTypes.contains(contentType)) {
                throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
            }
            // 校验文件的内容
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null ) {
                throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
            }
            // 目标路径
            File dest = new File("D:\\IdeaProjects\\upload", file.getOriginalFilename());
            file.transferTo(dest);

            //返回路径
            return "http://";
        } catch (IOException e) {
            //上传失败
            log.error("上传文件失败！", e);
            throw new LyException(ExceptionEnum.UPLOAD_FILE_ERROR);
        }
        // 返回路径
    }
}
