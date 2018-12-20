package cz.muni.fi.pa165.smartphonEShop.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Roman Nahalka
 * Class represents: Claim spring mvc controller.
 * @author xnahalka
 */
@Controller
public class AccessDeniedController
{
    @RequestMapping("/403")
    public String accessDenied()
    {
        return "/403";
    }
}
