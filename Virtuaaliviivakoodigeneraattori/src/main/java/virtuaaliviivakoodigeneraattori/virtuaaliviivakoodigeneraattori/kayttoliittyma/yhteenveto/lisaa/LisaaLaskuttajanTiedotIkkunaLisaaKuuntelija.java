/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.yhteenveto.lisaa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.NappulaLukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.yhteenveto.LaskuttajaOsioJPanel;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Laskuttaja;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Tilinumero;

/**
 *
 * @author Augustus58
 */
public class LisaaLaskuttajanTiedotIkkunaLisaaKuuntelija implements ActionListener {

    private JTextField nimiKentta;
    private JTextField katuosoiteKentta;
    private JTextField postinumeroKentta;
    private JTextField kaupunkiKentta;
    private JTextField yritykseNimiTeksti;
    private JTextField alvTunnisteKentta;
    private JTextField tilinumeroKentta;
    private JTextField tilinumeronPankkiKentta;
    private JTextField tilinumeronSwiftBicKentta;
    private JTextField puhelinnumeroKentta;
    private JTextField sahkopostiKentta;
    private JTextField laskujaLahetettyKentta;
    private final Lataaja lataaja;
    private final JFrame frame;
    private final NappulaLukko lukko;
    private final LaskuttajaOsioJPanel panel;

    public LisaaLaskuttajanTiedotIkkunaLisaaKuuntelija(JTextField nimiKentta, JTextField katuosoiteKentta, JTextField postinumeroKentta, JTextField kaupunkiKentta, JTextField yritykseNimiTeksti, JTextField alvTunnisteKentta, JTextField tilinumeroKentta, JTextField tilinumeronPankkiKentta, JTextField tilinumeronSwiftBicKentta, JTextField puhelinnumeroKentta, JTextField sahkopostiKentta, JTextField laskujaLahetettyKentta, Lataaja lataaja, JFrame frame, NappulaLukko lukko, LaskuttajaOsioJPanel panel) {
        this.nimiKentta = nimiKentta;
        this.katuosoiteKentta = katuosoiteKentta;
        this.postinumeroKentta = postinumeroKentta;
        this.kaupunkiKentta = kaupunkiKentta;
        this.yritykseNimiTeksti = yritykseNimiTeksti;
        this.alvTunnisteKentta = alvTunnisteKentta;
        this.tilinumeroKentta = tilinumeroKentta;
        this.tilinumeronPankkiKentta = tilinumeronPankkiKentta;
        this.tilinumeronSwiftBicKentta = tilinumeronSwiftBicKentta;
        this.puhelinnumeroKentta = puhelinnumeroKentta;
        this.sahkopostiKentta = sahkopostiKentta;
        this.laskujaLahetettyKentta = laskujaLahetettyKentta;
        this.lataaja = lataaja;
        this.frame = frame;
        this.lukko = lukko;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Tilinumero tilinumero = new Tilinumero(tilinumeroKentta.getText(), tilinumeronPankkiKentta.getText(), tilinumeronSwiftBicKentta.getText());
        if (!tilinumero.tarkistaTilinumero(tilinumero.getTilinumero())) {
            throw new IllegalArgumentException("Tilinumero on virheellinen.");
        }

        Laskuttaja laskuttaja = new Laskuttaja(nimiKentta.getText(),
                katuosoiteKentta.getText(),
                postinumeroKentta.getText(),
                kaupunkiKentta.getText(),
                yritykseNimiTeksti.getText(),
                alvTunnisteKentta.getText(),
                tilinumero,
                puhelinnumeroKentta.getText(),
                sahkopostiKentta.getText(),
                Integer.parseInt(laskujaLahetettyKentta.getText()));

        if (laskuttaja.onkoTiedotOikeanlaiset()) {
            lataaja.getLadattuTietovarasto().setLaskuttaja(laskuttaja);
        } else {
            throw new IllegalArgumentException("Jokin syöte on virheellinen.");
        }
        panel.setLaskuttajaOlemassa(true);
        panel.paivitaSisalto();
        suljeIkkuna();
    }

    private void suljeIkkuna() {
        lukko.avaa();
        frame.dispose();
    }
}
