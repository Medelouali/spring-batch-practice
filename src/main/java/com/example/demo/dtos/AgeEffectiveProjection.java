package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@AllArgsConstructor
@NoArgsConstructor
@Data
public class AgeEffectiveProjection {
    int age;
    Long effective;

    public AgeEffectiveProjection(int age, Long effective) {
        this.age = age;
        this.effective = effective;
    }
}
