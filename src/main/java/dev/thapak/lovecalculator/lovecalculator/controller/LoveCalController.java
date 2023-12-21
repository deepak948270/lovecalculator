package dev.thapak.lovecalculator.lovecalculator.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import dev.thapak.lovecalculator.lovecalculator.dto.LoveDto;
import dev.thapak.lovecalculator.lovecalculator.service.LCAppService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoveCalController {

    private final LCAppService lcAppService;

    public LoveCalController(LCAppService lcAppService) {
        this.lcAppService = lcAppService;
    }

    // show love cali form
    @GetMapping("/lovecalci")
    public String lovecalci(@ModelAttribute("loveDto") LoveDto loveDto) {
        loveDto.setUserName("king");
        loveDto.setCrushName("kratika");
        return "lovecalci";
    }

    // process the love calcutor result
    @GetMapping("/processlovecalci")
    public String processlovecalci(@Valid @ModelAttribute("loveDto") LoveDto loveDto, BindingResult result,
            HttpSession session) {
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            allErrors.forEach(error -> log.error(error.toString()));
            return "lovecalci";

        }

        // calculating the relationship b/w username and crushname
        String processresult = lcAppService.calculateLove(loveDto.getUserName(), loveDto.getCrushName());
        loveDto.setResult(processresult);

        // adding loveDto to the session
        session.setAttribute("user", loveDto);

        return "result";
    }

}
