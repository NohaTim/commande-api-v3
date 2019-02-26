/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sir.commandeapiv3.rest;

import com.sir.commandeapiv3.bean.Commande;
import com.sir.commandeapiv3.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author YOUNES
 */
@RestController()
@RequestMapping({"/commande-api-v3/commandes"})
public class CommandeRest {

    @Autowired
    private CommandeService commandeService;

    @PostMapping("/")
    public int creer(@RequestBody Commande commande) {
        return commandeService.creer(commande);
    }

    @GetMapping("/reference/{reference}")
    public Commande findByReference(@PathVariable String reference) {
        return commandeService.findByReference(reference);
    }

    @PutMapping("/reference/{reference}/montant/{montant}")
    public int payer(@PathVariable String reference, @PathVariable Double montant) {
        return commandeService.payer(reference, montant);
    }

    public CommandeService getCommandeService() {
        return commandeService;
    }

    public void setCommandeService(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

}
