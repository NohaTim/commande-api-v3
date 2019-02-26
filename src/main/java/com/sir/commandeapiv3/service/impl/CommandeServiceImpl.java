/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sir.commandeapiv3.service.impl;

import com.sir.commandeapiv3.bean.Commande;
import com.sir.commandeapiv3.dao.CommandeDao;
import com.sir.commandeapiv3.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author YOUNES
 */
@Service
public class CommandeServiceImpl  implements  CommandeService{

    @Autowired
    private CommandeDao commandeDao;
    @Override
    public Commande findByReference(String reference) {
        return commandeDao.findByReference(reference);
    }

    @Override
    public int payer(String reference, double montant) {
        Commande commande = findByReference(reference);
        if(commande==null){
            return -1;
        }else if(commande.getTotalPaiement()+montant>commande.getTotal()){
            return -2;
        }else{
            double nvPaiement = commande.getTotalPaiement()+montant;
            commande.setTotalPaiement(nvPaiement);
            commandeDao.save(commande);
            return 1;
        }
    }

    public CommandeDao getCommandeDao() {
        return commandeDao;
    }

    public void setCommandeDao(CommandeDao commandeDao) {
        this.commandeDao = commandeDao;
    }

    @Override
    public int creer(Commande commande) {
        Commande c= findByReference(commande.getReference());
        if(c!=null){
            return -1;
        }else{
            commande.setTotalPaiement(0);
            commandeDao.save(commande);
            return 1;
        }
    }
    
    
}
