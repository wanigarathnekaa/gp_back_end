package com.example.gp_back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormStats {
    private int visits;
    private int submissions;
    private double submissionRate;
    private double bounceRate;
}

