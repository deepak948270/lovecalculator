package dev.thapak.lovecalculator.lovecalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PasswordDto {
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
    
}
