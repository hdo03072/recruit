package com.toy.recruit.domain.notice;

import com.toy.recruit.core.domain.BaseEntity;
import com.toy.recruit.domain.admin.Admin;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.toy.recruit.core.code.Constants.DB_MAX_NUM;
import static com.toy.recruit.core.code.Constants.TEXT;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "notice")
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @Column(length = DB_MAX_NUM, name = "title", nullable = false)
    private String title;

    @Column(name = "content", columnDefinition = TEXT)
    private String content;

    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NoticeFile> files = new ArrayList<>();
}
