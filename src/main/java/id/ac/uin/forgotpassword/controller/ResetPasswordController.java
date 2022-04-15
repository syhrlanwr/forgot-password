package id.ac.uin.forgotpassword.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import id.ac.uin.forgotpassword.entity.PasswordReset;
import id.ac.uin.forgotpassword.entity.PasswordResetToken;
import id.ac.uin.forgotpassword.entity.User;
import id.ac.uin.forgotpassword.service.framework.PasswordResetTokenService;
import id.ac.uin.forgotpassword.service.framework.UserService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Locale;

@Controller
@RequestMapping("/reset-password")
public class ResetPasswordController {
    private final PasswordResetTokenService passwordResetTokenService;
    private final MessageSource messageSource;
    private final UserService userService;

    @Autowired
    public ResetPasswordController(PasswordResetTokenService passwordResetTokenService, MessageSource messageSource, UserService userService) {
        this.passwordResetTokenService = passwordResetTokenService;
        this.messageSource = messageSource;
        this.userService = userService;
    }

    @GetMapping
    public String resetPassword(@RequestParam(value = "token", required = false) String token, Model model) {
        PasswordResetToken passwordResetToken = passwordResetTokenService.getPasswordResetToken(token);
        if (passwordResetToken == null) {
            model.addAttribute("error", messageSource.getMessage("TOKEN_NOT_FOUND", new Object[]{}, Locale.ENGLISH));
        } else if (passwordResetToken.getExpiryDateTime().isBefore(LocalDateTime.now())) {
            model.addAttribute("error", messageSource.getMessage("TOKEN_EXPIRED", new Object[]{}, Locale.ENGLISH));
        } else {
            model.addAttribute("token", passwordResetToken.getToken());
        }
        return "reset-password";
    }

    @PostMapping
    public String savePasswordReset(@Valid @ModelAttribute("passwordReset") PasswordReset passwordReset, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("passwordReset", passwordReset);
            return "redirect:/reset-password?token=" + passwordReset.getToken();
        }
        PasswordResetToken passwordResetToken = passwordResetTokenService.getPasswordResetToken(passwordReset.getToken());
        User user = passwordResetToken.getUser();
        user.setPassword(passwordReset.getPassword());
        userService.updatePassword(user);
        return "redirect:/login";
    }

    @ModelAttribute("passwordReset")
    public PasswordReset getPasswordReset() {
        return new PasswordReset();
    }
}
