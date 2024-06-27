package com.toy.recruit.domain._file;

import com.toy.recruit.core.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@MappedSuperclass
public class File extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    protected String originFileName;

    @Column(nullable = false)
    protected String storeFileName;

    @Column(nullable = false)
    protected String path;

    protected String contentType;

    protected String extension;

    protected Long fileSize;
}
