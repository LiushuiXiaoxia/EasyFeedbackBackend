package cn.mycommons.easyfeedback.controller;

import cn.mycommons.easyfeedback.dto.CommonResp;
import cn.mycommons.easyfeedback.dto.file.UploadFileDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@Slf4j
@RestController
@RequestMapping("/file")
public class UploadController {

    @Value("${upload.dir}")
    String uploadDir;

    @PostMapping("/upload")
    public CommonResp<UploadFileDto> upload(@RequestParam("file") MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();

            if (filename == null) {
                filename = file.getName();
            }

            // 文件名称太短
            if (filename.length() < 10) {
                filename = System.currentTimeMillis() + "_" + filename;
            }

            file.transferTo(new File(uploadDir, filename));

            UploadFileDto dto = new UploadFileDto();
            dto.setFileName(filename);
            dto.setType(file.getContentType());
            dto.setSize(file.getSize());
            dto.setDownloadUrl(downloadUrl(filename));
            return CommonResp.success(dto);
        } catch (IOException e) {
            log.error("upload file fail", e);
            return CommonResp.serverError(e.getMessage());
        }
    }

    private String downloadUrl(String name) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file/download/")
                .path(name)
                .toUriString();
    }

    @GetMapping("/download/{name}")
    public ResponseEntity<Resource> download(@PathVariable("name") String name,
                                             HttpServletRequest request) {
        File file = new File(uploadDir, name);
        if (!file.exists()) {
            throw new RuntimeException("file do not exist");
        }

        MediaType type = MediaType.APPLICATION_OCTET_STREAM;
        try {
            type = MediaType.parseMediaType(request.getServletContext().getMimeType(file.getAbsolutePath()));
        } catch (Exception ignored) {
            log.info("parseMediaType error file = {}", file);
        }

        return ResponseEntity.ok()
                .contentType(type)
                .header(CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(new FileSystemResource(file));
    }
}