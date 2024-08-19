package com.myProject.ECommerce.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ApiResponse {
    private String message;
    private boolean status;
}
