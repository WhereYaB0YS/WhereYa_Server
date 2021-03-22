package com.where.WhereYouAt.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.where.WhereYouAt.domain.dto.Birthday;
import com.where.WhereYouAt.enumclass.GenderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "deleted = false")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String userId;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String nickname;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GenderStatus gender;

    @Valid
    @Embedded
    private Birthday birthday;

    @Column(columnDefinition =  "TEXT")
    private String profileImg;

    @ColumnDefault("0") // 이 값이 true가 되면 삭제가 되었다 간주하고 repository에서 삭제됨
    private boolean deleted;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonManagedReference
    private List<Friend> friends;

}
