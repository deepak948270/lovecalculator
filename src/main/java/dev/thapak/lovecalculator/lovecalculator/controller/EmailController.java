package dev.thapak.lovecalculator.lovecalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import dev.thapak.lovecalculator.lovecalculator.dto.EmailDTO;
import dev.thapak.lovecalculator.lovecalculator.dto.LoveDto;
import dev.thapak.lovecalculator.lovecalculator.mailsender.EmailSenderService;
import jakarta.servlet.http.HttpSession;

@Controller
public class EmailController {
    private final EmailSenderService emailSenderService;

    public EmailController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @GetMapping("/sendmail")
    public String sendEmail(@ModelAttribute("emailDTO") EmailDTO emailDTO) {
        return "sendmail";
    }

    @GetMapping("/processemail")
    public String processEmail(@ModelAttribute("emailDTO") EmailDTO emailDTO, HttpSession session) {

        // getting result from the session
        LoveDto loveDto = (LoveDto) session.getAttribute("user");
        String result = loveDto.getResult();
        String userName = loveDto.getUserName();
        String crushName = loveDto.getCrushName();

        String userEmail = emailDTO.getUserEmail();
        // modifing the orginal result
        String modifiedResult = "Hello " + userName + "  and  " + crushName
                + " love calculator is calculated your result that is  -> " + result;

        // sending result to the email
        emailSenderService.sendEmail(userEmail, modifiedResult);

        return "processemail";
    }

}