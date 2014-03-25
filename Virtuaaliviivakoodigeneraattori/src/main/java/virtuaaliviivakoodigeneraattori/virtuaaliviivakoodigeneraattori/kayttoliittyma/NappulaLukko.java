/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma;

/**
 *
 * @author Augustus58
 */

//Tällä luokalla estetään useampien ikkunoiden avaaminen ohjelmassa.
public class NappulaLukko {
    private Boolean lukko;

    public NappulaLukko() {
        this.lukko = false;
    }
    public void lukitse() {
        lukko = true;
    }
    
    public void avaa() {
        lukko = false;
    }
    
    public Boolean onkoLukkoPaalla() {
        return lukko;
    }
}
