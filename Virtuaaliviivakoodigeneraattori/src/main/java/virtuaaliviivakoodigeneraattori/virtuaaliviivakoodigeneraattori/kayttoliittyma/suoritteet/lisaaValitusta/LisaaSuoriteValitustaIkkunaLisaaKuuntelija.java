/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.suoritteet.lisaaValitusta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JTextField;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.ComboBoxKuuntelija;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.NappulaLukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.suoritteet.SuoritteetTaulukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Asiakas;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Suorite;

/**
 *
 * @author Augustus58
 */
public class LisaaSuoriteValitustaIkkunaLisaaKuuntelija implements ActionListener {

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
    private final ComboBoxKuuntelija kuuntelija;

    public LisaaSuoriteValitustaIkkunaLisaaKuuntelija(ComboBoxKuuntelija kuuntelija, JTextField kuvausKentta, JTextField pvmKentta, JTextField maaraKentta, JTextField maaranYksikotKentta, JTextField aHintaKentta, JTextField alvProsKentta, Lataaja lataaja, SuoritteetTaulukko taulukko, JFrame frame, NappulaLukko lukko) {
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
        this.kuuntelija = kuuntelija;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {

            Asiakas suoritteenAsiakas = lataaja.getLadattuTietovarasto().getAsiakkaat().get(kuuntelija.getValinta());
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

//            if (!suorite.onkoTiedotOikeanlaiset()) {
//                throw new IllegalArgumentException("Jokin syöte on virheellinen.");
//            }
            lataaja.getLadattuTietovarasto().getSuoritteet().add(suorite);
            taulukko.addSuoritteetTaulukkoRivi(suorite);
            suljeIkkuna();
        } catch (Exception e) {
//            LisaaAsiakasIkkunaLisaaKuuntelijaPoikkeusIkkuna poikkeusIkkuna = new LisaaAsiakasIkkunaLisaaKuuntelijaPoikkeusIkkuna();
//            SwingUtilities.invokeLater(poikkeusIkkuna);
        }

    }

    private void suljeIkkuna() {
        frame.dispose();
//        lukko.avaa();
    }
}
