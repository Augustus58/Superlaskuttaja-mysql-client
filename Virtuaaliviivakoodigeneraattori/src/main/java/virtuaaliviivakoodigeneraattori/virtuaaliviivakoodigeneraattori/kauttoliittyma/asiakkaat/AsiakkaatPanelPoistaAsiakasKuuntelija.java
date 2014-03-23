/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat;

import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.NappulaLukko;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Asiakas;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class AsiakkaatPanelPoistaAsiakasKuuntelija implements ActionListener {
    private final Lataaja lataaja;
    private final AsiakkaatTaulukko taulukko;
    private final AsiakkaatTaulukkoValintaKuuntelija kuuntelija;
    private final NappulaLukko lukko;

    public AsiakkaatPanelPoistaAsiakasKuuntelija(Lataaja lataaja, AsiakkaatTaulukko taulukko, AsiakkaatTaulukkoValintaKuuntelija kuuntelija, NappulaLukko lukko) {
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
                Asiakas poistettavaAsiakas = new Asiakas(taulukko.getValueString(kuuntelija.getArvoModel(), 4),
                        taulukko.getValueString(kuuntelija.getArvoModel(), 0),
                        taulukko.getValueString(kuuntelija.getArvoModel(), 1),
                        taulukko.getValueString(kuuntelija.getArvoModel(), 2),
                        taulukko.getValueString(kuuntelija.getArvoModel(), 3),
                        Integer.parseInt(taulukko.getValueString(kuuntelija.getArvoModel(), 5)));
                taulukko.getModel().removeRow(kuuntelija.getArvoModel());
                lataaja.getLadattuTietovarasto().poistaAsiakas(poistettavaAsiakas);
            } catch (Exception e) {
                AsiakkaatPanelPoistaAsiakasPoikkeusIkkuna poikkeusIkkuna = new AsiakkaatPanelPoistaAsiakasPoikkeusIkkuna();
                SwingUtilities.invokeLater(poikkeusIkkuna);
            }
        }
    }
}
