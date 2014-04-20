/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.yhteenveto.muokkaa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.yhteenveto.LaskuttajaOsioJPanel;
import superlaskuttaja.kayttoliittyma.yhteenveto.lisaa.LisaaLaskuttajanTiedotIkkunaLisaaPoikkeusIkkuna;
import superlaskuttaja.logiikka.Laskuttaja;
import superlaskuttaja.logiikka.Lataaja;
import superlaskuttaja.logiikka.Tilinumero;

/**
 *
 * @author Augustus58
 */
public class MuokkaaLaskuttajanTietojaIkkunaMuokkaaKuuntelija implements ActionListener {

    private final JTextField nimiKentta;
    private final JTextField katuosoiteKentta;
    private final JTextField postinumeroKentta;
    private final JTextField kaupunkiKentta;
    private final JTextField yritykseNimiTeksti;
    private final JTextField alvTunnisteKentta;
    private final JTextField tilinumeroKentta;
    private final JTextField tilinumeronPankkiKentta;
    private final JTextField tilinumeronSwiftBicKentta;
    private final JTextField puhelinnumeroKentta;
    private final JTextField sahkopostiKentta;
    private final JTextField laskujaLahetettyKentta;
    private final Lataaja lataaja;
    private final JFrame frame;
    private final NappulaLukko lukko;
    private final LaskuttajaOsioJPanel panel;

    public MuokkaaLaskuttajanTietojaIkkunaMuokkaaKuuntelija(JTextField nimiKentta, JTextField katuosoiteKentta, JTextField postinumeroKentta, JTextField kaupunkiKentta, JTextField yritykseNimiTeksti, JTextField alvTunnisteKentta, JTextField tilinumeroKentta, JTextField tilinumeronPankkiKentta, JTextField tilinumeronSwiftBicKentta, JTextField puhelinnumeroKentta, JTextField sahkopostiKentta, JTextField laskujaLahetettyKentta, Lataaja lataaja, JFrame frame, NappulaLukko lukko, LaskuttajaOsioJPanel panel) {
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
        try {
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
        } catch (Exception e) {
            MuokkaaLaskuttajanTietojaIkkunaMuokkaaPoikkeusIkkuna poikkeusIkkuna = new MuokkaaLaskuttajanTietojaIkkunaMuokkaaPoikkeusIkkuna();
            SwingUtilities.invokeLater(poikkeusIkkuna);
        }
    }

    private void suljeIkkuna() {
        lukko.avaa();
        frame.dispose();
    }
}
