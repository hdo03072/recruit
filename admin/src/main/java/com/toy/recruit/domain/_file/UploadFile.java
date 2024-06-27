package com.toy.recruit.domain._file;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UploadFile {

    private String originFileName;

    private String storeFileName;

    private String path;

    private String contentType;

    private String extension;

    private Long fileSize;
}
