package com.toy.recruit.web.dto.notice;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NoticeUpdate {

    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    List<Long> savedFileIds = new ArrayList<>();

    List<MultipartFile> files = new ArrayList<>();
}
