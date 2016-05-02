package com.pld.recipes.core

/**
 *
 * @author zebres
 */
enum Source {
    TroisFoisParJour("3 Fois par jour"),
    Autre,
    CoupDePouce("Coup de pouce"),
    Loblaws,
    Ricardo,
    Web;
        
    String label;
    private Source() {
        this.label = this.toString();
    }
    
    private Source(String label) {
        this.label = label;
    }
}

