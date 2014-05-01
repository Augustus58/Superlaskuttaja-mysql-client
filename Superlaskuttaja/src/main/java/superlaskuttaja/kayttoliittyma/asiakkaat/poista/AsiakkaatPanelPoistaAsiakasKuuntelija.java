/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.asiakkaat.poista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.TaulukkoValintaKuuntelija;
import superlaskuttaja.kayttoliittyma.asiakkaat.AsiakkaatTaulukko;
import superlaskuttaja.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class AsiakkaatPanelPoistaAsiakasKuuntelija implements ActionListener {
    
    private final Lataaja lataaja;
    private final AsiakkaatTaulukko taulukko;
    private final TaulukkoValintaKuuntelija kuuntelija;
    private final NappulaLukko lukko;
    
    public AsiakkaatPanelPoistaAsiakasKuuntelija(Lataaja lataaja, AsiakkaatTaulukko taulukko, TaulukkoValintaKuuntelija kuuntelija, NappulaLukko lukko) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.kuuntelija = kuuntelija;
        this.lukko = lukko;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!lukko.onkoLukkoPaalla()) {
            try {
                kuuntelija.paivitaArvo();
                for (int i = 0; i < lataaja.getLadattuTietovarasto().getSuoritteet().size(); i++) {
                    if (lataaja.getLadattuTietovarasto().getSuoritteet().get(i).getAsiakas().equals(lataaja.getLadattuTietovarasto().getAsiakkaat().get(kuuntelija.getPaivitettyArvo()))) {
                        throw new IllegalStateException();
                    }
                }
                lataaja.getLadattuTietovarasto().getAsiakkaat().remove(kuuntelija.getPaivitettyArvo().intValue());
                taulukko.getModel().removeRow(kuuntelija.getPaivitettyArvo());
            } catch (IllegalStateException e) {
                AsiakkaatPanelPoistaAsiakasAsiakkaallaSuoritteitaPoikkeusIkkuna poikkeusIkkuna = new AsiakkaatPanelPoistaAsiakasAsiakkaallaSuoritteitaPoikkeusIkkuna();
                SwingUtilities.invokeLater(poikkeusIkkuna);
            } catch (Exception e) {
                AsiakkaatPanelPoistaAsiakasEiValittuAsiakastaPoikkeusIkkuna poikkeusIkkuna = new AsiakkaatPanelPoistaAsiakasEiValittuAsiakastaPoikkeusIkkuna();
                SwingUtilities.invokeLater(poikkeusIkkuna);
            }
        }
    }
}
