/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.asiakkaat.poista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.SwingUtilities;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.TaulukkoValintaKuuntelija;
import superlaskuttaja.kayttoliittyma.asiakkaat.AsiakkaatTaulukko;
import superlaskuttaja.logiikka.DataDeliver;

/**
 *
 * @author Augustus58
 */
public class AsiakkaatPanelPoistaAsiakasKuuntelija implements ActionListener {

    private final DataDeliver lataaja;
    private final AsiakkaatTaulukko taulukko;
    private final TaulukkoValintaKuuntelija kuuntelija;
    private final NappulaLukko lukko;

    public AsiakkaatPanelPoistaAsiakasKuuntelija(DataDeliver lataaja, AsiakkaatTaulukko taulukko, TaulukkoValintaKuuntelija kuuntelija, NappulaLukko lukko) {
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
                //Tarkistetaan onko poistettavalla asiakkaalla suoritteita. Jos on, niin poistaminen estetään.
                //Ensin poistettavan asiakkaan asiakasnumero.

                String valitunAsiakasnumero = taulukko.getModel().getValueAt(kuuntelija.getPaivitettyArvo(), 5).toString();

                ResultSet rs = lataaja.getDbc().executeQuery("select distinct suoritteenNumero\n"
                        + "from Suorite S, Asiakas A\n"
                        + "where\n"
                        + "((S.tilaaja = A.asiakasnumero\n"
                        + "and S.tilaajanVersio = A.versio)\n"
                        + "or\n"
                        + "(S.vastaanottaja = A.asiakasnumero\n"
                        + "and S.vastaanottajanVersio = A.versio))\n"
                        + "and A.asiakasnumero = " + valitunAsiakasnumero + "\n"
                        + "");

                if (rs.first()) {
                    throw new IllegalStateException();
                }
                
                //Jos tähän kohtaan päästään, niin ei pitäisi olla suoritteita olemassa tälle asiakkaalle.
                lataaja.getDbc().executeUpdate("delete from Asiakas\n"
                        + "where asiakasnumero = " + valitunAsiakasnumero + "\n"
                        + "");

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
