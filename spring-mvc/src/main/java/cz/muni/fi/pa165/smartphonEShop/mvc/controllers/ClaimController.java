package cz.muni.fi.pa165.smartphonEShop.mvc.controllers;

import cz.muni.fi.pa165.smartphonEShop.dto.ClaimCreateDTO;
import cz.muni.fi.pa165.smartphonEShop.exceptions.EshopServiceException;
import cz.muni.fi.pa165.smartphonEShop.facade.ClaimFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by Roman Nahalka
 * Class represents: Claim spring mvc controller.
 * @author xnahalka
 */
@Controller
@RequestMapping("/claim")
public class ClaimController
{
    @Autowired
    private ClaimFacade claimFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model)
    {
        model.addAttribute("claims", claimFacade.getAllClaims());
        return "claim/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newClaim(Model model)
    {
        ClaimCreateDTO claimCreateDTO = new ClaimCreateDTO();
        model.addAttribute("claimCreate", claimCreateDTO);
        return "claim/new";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("claim", claimFacade.findClaimById(id));
        return "claim/view";
    }

    @RequestMapping(value = "/accept/{id}")
    public String accept(@PathVariable("id") long id, Model model, UriComponentsBuilder builder,
                         RedirectAttributes redirectAttributes)
    {
        try
        {
            claimFacade.acceptClaim(id);
            redirectAttributes.addFlashAttribute("alert_success", "Claim number" + id + " was accepted.");
        }

        catch (EshopServiceException ex)
        {
            redirectAttributes.addFlashAttribute("alert_danger", "Claim number" + id + " was not accepted " + ex.getMessage());
        }

        return "redirect: " +builder.path("/claim/detail/{id}").buildAndExpand(id).encode().toUriString();
    }

    @RequestMapping(value = "/reject/{id}")
    public String reject(@PathVariable("id") long id, Model model, UriComponentsBuilder builder,
                         RedirectAttributes redirectAttributes)
    {
        try
        {
            claimFacade.rejectClaim(id);
            redirectAttributes.addFlashAttribute("alert_success", "Claim number" + id + " was rejected.");
        }

        catch (EshopServiceException ex)
        {
            redirectAttributes.addFlashAttribute("alert_danger", "Claim number" + id + " was not rejected " + ex.getMessage());
        }

        return "redirect: " +builder.path("/claim/detail/{id}").buildAndExpand(id).encode().toUriString();
    }
}
