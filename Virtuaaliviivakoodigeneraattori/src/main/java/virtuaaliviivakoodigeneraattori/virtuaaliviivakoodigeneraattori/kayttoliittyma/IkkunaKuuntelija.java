/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Augustus58
 */
public class IkkunaKuuntelija extends WindowAdapter {
    private final NappulaLukko lukko;

    public IkkunaKuuntelija(NappulaLukko lukko) {
        this.lukko = lukko;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        lukko.avaa();
    }
}
