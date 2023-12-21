package dev.thapak.lovecalculator.lovecalculator.service.impl;

import org.springframework.stereotype.Service;

import dev.thapak.lovecalculator.lovecalculator.service.LCAppService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LCAppServiceImpl implements LCAppService {

	public static final String LC_APP_FORMULA = "FLAME";
	
	
	@Override
	public String calculateLove(String userName, String crushName) {
		
		String name = userName + crushName;
		int charCount = name.length();
		int formulaCount = LC_APP_FORMULA.length();
		
		int rem = charCount % (formulaCount);
		
		char resultChar = LC_APP_FORMULA.charAt(rem);
		
		return whatsBetweenUs(resultChar);
	}


	@Override
	public String whatsBetweenUs(char resultChar) {
		
		String relation = null;
		
		switch (resultChar) {
		case 'F':
			relation =  "FRIEND";
			break;
			
		case 'L':
			relation = "LOVE";
			break;
			
		case 'A':
			relation = "AFFECTION";
			break;
			
		case 'M':
			relation = "MARRIAGE";
			break;
			
		case 'E':
			relation = "ENEMY";
			break;

		default:
			log.error("-> something went wrong please contact us !");
			break;
		}
		
		return relation;
	}
}