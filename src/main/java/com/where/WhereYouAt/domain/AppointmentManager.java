package com.where.WhereYouAt.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"appointment","user"})
public class AppointmentManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JsonBackReference
    private User user;

    @NotNull
    @ManyToOne
    @JsonBackReference
    private Appointment appointment;


}
