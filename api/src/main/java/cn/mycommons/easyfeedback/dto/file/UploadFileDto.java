package cn.mycommons.easyfeedback.dto.file;

import lombok.Data;

@Data
public class UploadFileDto {
    private String fileName;
    private String downloadUrl;
    private String type;
    private long size;
}