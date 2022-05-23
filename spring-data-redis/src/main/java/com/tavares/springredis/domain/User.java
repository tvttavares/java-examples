package com.tavares.springredis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
//No @Entity concept here
public class User  implements Serializable {

    private static final long serialVersionUID = -8867388402023340615L;

    private int userId;
    private String name;
}
