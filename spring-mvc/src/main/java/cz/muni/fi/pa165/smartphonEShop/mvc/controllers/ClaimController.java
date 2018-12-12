package cz.muni.fi.pa165.smartphonEShop.mvc.controllers;

import cz.muni.fi.pa165.smartphonEShop.dto.ClaimCreateDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.ClaimDTO;
import cz.muni.fi.pa165.smartphonEShop.enums.ClaimSolution;
import cz.muni.fi.pa165.smartphonEShop.enums.ClaimState;
import cz.muni.fi.pa165.smartphonEShop.exceptions.EshopServiceException;
import cz.muni.fi.pa165.smartphonEShop.facade.ClaimFacade;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Roman Nahalka
 * Class represents: Claim spring mvc controller.
 * @author xnahalka
 */
@Controller
@RequestMapping("/claim")
@Setter
public class ClaimController
{
    @Autowired
    private ClaimFacade claimFacade;

    @RequestMapping(value = "/list/state/{filter}", method = RequestMethod.GET)
    public String listByState(@PathVariable String filter, Model model)
    {
        Collection<ClaimDTO> claims;

        switch (filter)
        {
            case "created":
                claims = claimFacade.findClaimByClaimState(ClaimState.CREATED);
                break;

            case "accepted":
                claims = claimFacade.findClaimByClaimState(ClaimState.ACCEPTED);
                break;

            case "rejected":
                claims = claimFacade.findClaimByClaimState(ClaimState.REJECTED);
                break;

            default:
                claims = new ArrayList<>();
                model.addAttribute("alert_danger", "Unkown filter " + filter);
        }

        model.addAttribute("claimsByState", claims);
        return "claim/list";
    }

    @RequestMapping(value = "/list/solution/{filter}", method = RequestMethod.GET)
    public String listBySolution(@PathVariable String filter, Model model)
    {
        Collection<ClaimDTO> claims;

        switch (filter)
        {
            case "money":
                claims = claimFacade.findClaimByClaimSolution(ClaimSolution.MONEY);
                break;

            case "repair":
                claims = claimFacade.findClaimByClaimSolution(ClaimSolution.REPAIR);
                break;

            default:
                claims = new ArrayList<>();
                model.addAttribute("alert_danger", "Unkown filter " + filter);
        }

        model.addAttribute("claimsBySolution", claims);
        return "claim/list";
    }

    @RequestMapping(value = "/list/all", method = RequestMethod.GET)
    public String listAll(Model model)
    {
        Collection<ClaimDTO> claims;

        claims = claimFacade.getAllClaims();
        model.addAttribute("allClaims", claims);
        return "claim/list";
    }

    @RequestMapping(value = "/list/byPerson", method = RequestMethod.GET)
    public String listByUserId(@RequestParam("personId") long id, Model model)
    {
        Collection<ClaimDTO> claims;

        claims = claimFacade.findClaimByUserId(id);
        model.addAttribute("claimsByPerson", claims);
        return "claim/list";
    }

    @RequestMapping(value = "/list/byOrder", method = RequestMethod.GET)
    public String listByOrderId(@RequestParam("orderId") long id, Model model)
    {
        Collection<ClaimDTO> claims;

        claims = claimFacade.findClaimByOrderId(id);
        model.addAttribute("claimsByOrder", claims);
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

        return "redirect: " + builder.path("/claim/detail/{id}").buildAndExpand(id).encode().toUriString();
    }
}
