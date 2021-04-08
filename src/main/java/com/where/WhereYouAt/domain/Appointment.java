package com.where.WhereYouAt.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.where.WhereYouAt.domain.dto.Destination;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String memo;

    private LocalDateTime date;

    @Valid
    @Embedded
    private Destination destination;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "appointment")
    @JsonManagedReference
    private List<AppointmentManager> appointmentList;
}
