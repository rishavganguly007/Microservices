package com.rg.user.service.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hotel {
    private String id;
    private String name;
    private String location;
    private String about;
}
