/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.suoritteet.muokkaa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.ComboBoxKuuntelija;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.NappulaLukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.TaulukkoValintaKuuntelija;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.suoritteet.SuoritteetTaulukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Asiakas;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.MerkkiJaMerkkijonoTarkistin;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Suorite;

/**
 *
 * @author Augustus58
 */
public class MuokkaaValittuaIkkunaMuokkaaKuuntelija implements ActionListener {

    private final JTextField kuvausKentta;
    private final JTextField pvmKentta;
    private final JTextField maaraKentta;
    private final JTextField maaranYksikotKentta;
    private final JTextField aHintaKentta;
    private final JTextField alvProsKentta;
    private final Lataaja lataaja;
    private final SuoritteetTaulukko taulukko;
    private final JFrame frame;
    private final NappulaLukko lukko;
    private final ComboBoxKuuntelija comboBoxKuuntelija;
    private final TaulukkoValintaKuuntelija taulukkoKuuntelija;
    private MerkkiJaMerkkijonoTarkistin tarkistin;

    public MuokkaaValittuaIkkunaMuokkaaKuuntelija(JTextField kuvausKentta, JTextField pvmKentta, JTextField maaraKentta, JTextField maaranYksikotKentta, JTextField aHintaKentta, JTextField alvProsKentta, Lataaja lataaja, SuoritteetTaulukko taulukko, JFrame frame, NappulaLukko lukko, ComboBoxKuuntelija comboBoxKuuntelija, TaulukkoValintaKuuntelija taulukkoKuuntelija) {
        this.kuvausKentta = kuvausKentta;
        this.pvmKentta = pvmKentta;
        this.maaraKentta = maaraKentta;
        this.maaranYksikotKentta = maaranYksikotKentta;
        this.aHintaKentta = aHintaKentta;
        this.alvProsKentta = alvProsKentta;
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.frame = frame;
        this.lukko = lukko;
        this.comboBoxKuuntelija = comboBoxKuuntelija;
        this.taulukkoKuuntelija = taulukkoKuuntelija;
        this.tarkistin = new MerkkiJaMerkkijonoTarkistin();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {

            Asiakas suoritteenAsiakas = lataaja.getLadattuTietovarasto().getAsiakkaat().get(comboBoxKuuntelija.getValinta());

            if (!tarkistin.onkoMerkkijonoMuotoaNnPisteNnPisteNnnn(pvmKentta.getText())) {
                throw new IllegalArgumentException("Syöte päivämäärä on virheellinen.");
            }

            if (!tarkistin.onkoPvmMerkkijonoMuotoaNnPisteNnPisteNnnnValidi(pvmKentta.getText())) {
                throw new IllegalArgumentException("Syöte päivämäärä on virheellinen.");
            }

            Integer vuosi = Integer.parseInt(pvmKentta.getText().substring(6, 10));
            Integer kuukausi = Integer.parseInt(pvmKentta.getText().substring(3, 5));
            Integer paiva = Integer.parseInt(pvmKentta.getText().substring(0, 2));
            Date date = new Date(vuosi - 1900, kuukausi - 1, paiva);

            Suorite suorite = new Suorite(suoritteenAsiakas,
                    kuvausKentta.getText(),
                    date,
                    Double.parseDouble(maaraKentta.getText()),
                    maaranYksikotKentta.getText(),
                    Double.parseDouble(aHintaKentta.getText()),
                    Integer.parseInt(alvProsKentta.getText()));

            if (!suorite.onkoTiedotOikeanlaisetPaitsiPvm()) {
                throw new IllegalArgumentException("Jokin syöte on virheellinen.");
            }
            lataaja.getLadattuTietovarasto().getSuoritteet().remove(taulukkoKuuntelija.getArvoModel());
            lataaja.getLadattuTietovarasto().getSuoritteet().add(taulukkoKuuntelija.getArvoModel(), suorite);
            taulukko.getModel().insertRow(taulukkoKuuntelija.getArvoModel(), suorite.suoritteenTiedotTaulukossa());
            taulukko.getModel().removeRow(taulukkoKuuntelija.getArvoModel() + 1);
            suljeIkkuna();
        } catch (Exception e) {
            MuokkaaValittuaIkkunaMuokkaaPoikkeusIkkuna poikkeusIkkuna = new MuokkaaValittuaIkkunaMuokkaaPoikkeusIkkuna();
            SwingUtilities.invokeLater(poikkeusIkkuna);
        }

    }

    private void suljeIkkuna() {
        frame.dispose();
        lukko.avaa();
    }
}
