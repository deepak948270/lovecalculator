package dev.thapak.lovecalculator.lovecalculator.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoveDto {
    @NotBlank(message = "*your name is mendatory")
	private String userName;

	@NotBlank(message = "*cruch name is mendatory")
	private String crushName;

	@AssertTrue(message = " * You have to agree to use our app")
	private boolean agreedToTermsAndCondition;
	
	private String result;
	
    
}
