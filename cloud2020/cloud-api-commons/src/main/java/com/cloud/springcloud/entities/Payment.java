package com.cloud.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Payment implements Serializable {

    private Long id;
    private String serial;

}
