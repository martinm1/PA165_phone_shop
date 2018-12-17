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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
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

    /**
     * Get all claims filtered by claim state.
     * @param filter state of claim.
     * @param model data to display.
     * @return JSP page name.
     */
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

        model.addAttribute("claims", claims);
        return "claim/list";
    }

    /**
     * Get all claims filtered by wanted solution.
     * @param filter solution.
     * @param model data to display.
     * @return JSP page name.
     */
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

        model.addAttribute("claims", claims);
        return "claim/list";
    }

    /**
     * Get all claims.
     * @param model data to display.
     * @return JSP page name.
     */
    @RequestMapping(value = "/list/all", method = RequestMethod.GET)
    public String listAll(Model model)
    {
        Collection<ClaimDTO> claims;

        claims = claimFacade.getAllClaims();
        model.addAttribute("claims", claims);
        return "claim/list";
    }

    /**
     * Get all claims of one person.
     * @param id primary key for person.
     * @param model data to display.
     * @return JSP page name.
     */
    @RequestMapping(value = "/list/byPerson", method = RequestMethod.GET)
    public String listByUserId(@RequestParam("personId") long id, Model model)
    {
        Collection<ClaimDTO> claims;

        claims = claimFacade.findClaimByUserId(id);
        model.addAttribute("claims", claims);
        return "claim/list";
    }

    /**
     * Gett all claims of one order.
     * @param id primary key for order.
     * @param model data to display.
     * @return JSP page name.
     */
    @RequestMapping(value = "/list/byOrder", method = RequestMethod.GET)
    public String listByOrderId(@RequestParam("orderId") long id, Model model)
    {
        Collection<ClaimDTO> claims;

        claims = claimFacade.findClaimByOrderId(id);
        model.addAttribute("claims", claims);
        return "claim/list";
    }

    /**
     * Prepares a empty form.
     * @param model data to display.
     * @return JSP page name.
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newClaim(Model model)
    {
        ClaimCreateDTO claimCreateDTO = new ClaimCreateDTO();
        model.addAttribute("claimCreate", claimCreateDTO);
        return "claim/new";
    }
    @ModelAttribute("claimSolution")
    public ClaimSolution[] claimSolutions()
    {
        return ClaimSolution.values();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("claimCreate") ClaimCreateDTO claim, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder)
    {
        if(bindingResult.hasErrors())
        {
            for(FieldError fe : bindingResult.getFieldErrors())
            {
                model.addAttribute(fe.getField() + "_error", true);
            }

            return "claim/new";
        }

        Long id = claimFacade.createClaim(claim);
        redirectAttributes.addFlashAttribute("alert_success", "Claim " + id + " was created");
        return "redirect:" + uriBuilder.path("/claim/list").toUriString();
    }

    /**
     * Get one claim by id.
     * @param id primary key for claim.
     * @param model data to display.
     * @return JSP page name.
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("claim", claimFacade.findClaimById(id));
        return "claim/view";
    }

    /**
     * Accept claim.
     * @param id primary key for claim.
     * @param model data to display.
     * @param builder path builder.
     * @param redirectAttributes attributes for redirection.
     * @return JSP page name.
     */
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

        return "redirect: " + builder.path("/claim/detail/{id}").buildAndExpand(id).encode().toUriString();
    }

    /**
     * Reject claim.
     * @param id primary key for claim.
     * @param model data to display.
     * @param builder path builder.
     * @param redirectAttributes attributes for redirection.
     * @return JSP page name.
     */
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
