/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.laskut.lisaa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.asiakkaat.AsiakkaatTaulukko;
import superlaskuttaja.kayttoliittyma.laskut.LaskutTaulukko;
import superlaskuttaja.kayttoliittyma.suoritteet.SuoritteetTaulukko;
import superlaskuttaja.kayttoliittyma.yhteenveto.LaskuttajaOsioJPanel;
import superlaskuttaja.logiikka.Lasku;
import superlaskuttaja.logiikka.LaskunSumma;
import superlaskuttaja.logiikka.Lataaja;
import superlaskuttaja.logiikka.MerkkiJaMerkkijonoTarkistin;
import superlaskuttaja.logiikka.Pankkiviivakoodi;
import superlaskuttaja.logiikka.Suorite;
import superlaskuttaja.logiikka.Viite;

/**
 *
 * @author Augustus58
 */
public class LisaaLaskuIkkunaLisaaKuuntelija implements ActionListener {

    private final LisaaLaskutIkkunaComboBoxKuuntelija comboBoxkuuntelija;
    private final LisaaLaskuIkkunaSuoritteetList suoritteetLista;
    private final JTextField paivaysKentta;
    private final JTextField maksuaikaKentta;
    private final JTextField erapaivaKentta;
    private final JTextField viivastyskorkoKentta;
    private final JTextField maksuehtoKentta;
    private final JTextField lisatiedotKentta;

    private final SuoritteetTaulukko suoritteetTaulukko;
    private final AsiakkaatTaulukko asiakkaatTaulukko;
    private final LaskuttajaOsioJPanel laskuttajaOsioJPanel;
    private final Lataaja lataaja;
    private final LaskutTaulukko taulukko;
    private final JFrame frame;
    private final NappulaLukko lukko;
    private final MerkkiJaMerkkijonoTarkistin tarkistin;
    private final DateFormat pvmFormaatti;

    public LisaaLaskuIkkunaLisaaKuuntelija(LisaaLaskutIkkunaComboBoxKuuntelija comboBoxkuuntelija, LisaaLaskuIkkunaSuoritteetList suoritteetLista, JTextField paivaysKentta, JTextField maksuaikaKentta, JTextField erapaivaKentta, JTextField viivastyskorkoKentta, JTextField maksuehtoKentta, JTextField lisatiedotKentta, SuoritteetTaulukko suoritteetTaulukko, AsiakkaatTaulukko asiakkaatTaulukko, LaskuttajaOsioJPanel laskuttajaOsioJPanel, Lataaja lataaja, LaskutTaulukko taulukko, JFrame frame, NappulaLukko lukko, DateFormat pvmFormaatti) {
        this.comboBoxkuuntelija = comboBoxkuuntelija;
        this.suoritteetLista = suoritteetLista;
        this.paivaysKentta = paivaysKentta;
        this.maksuaikaKentta = maksuaikaKentta;
        this.erapaivaKentta = erapaivaKentta;
        this.viivastyskorkoKentta = viivastyskorkoKentta;
        this.maksuehtoKentta = maksuehtoKentta;
        this.lisatiedotKentta = lisatiedotKentta;
        this.suoritteetTaulukko = suoritteetTaulukko;
        this.asiakkaatTaulukko = asiakkaatTaulukko;
        this.laskuttajaOsioJPanel = laskuttajaOsioJPanel;
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.frame = frame;
        this.lukko = lukko;
        this.tarkistin = new MerkkiJaMerkkijonoTarkistin();
        this.pvmFormaatti = pvmFormaatti;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if (!tarkistin.onkoMerkkijonoMuotoaNnPisteNnPisteNnnn(paivaysKentta.getText())) {
                throw new IllegalArgumentException("Syöte päiväys on virheellinen.");
            }
            if (!tarkistin.onkoPvmMerkkijonoMuotoaNnPisteNnPisteNnnnValidi(paivaysKentta.getText())) {
                throw new IllegalArgumentException("Syöte päiväys on virheellinen.");
            }
            GregorianCalendar paivays = new GregorianCalendar();
            paivays.setTime(pvmFormaatti.parse(paivaysKentta.getText()));

            Integer maksuaika = Integer.parseInt(maksuaikaKentta.getText());

            if (maksuaika < 0 || maksuaika > 365) {
                throw new IllegalArgumentException("Syöte maksuaika on virheellinen.");
            }

            if (!tarkistin.onkoMerkkijonoMuotoaNnPisteNnPisteNnnn(erapaivaKentta.getText())) {
                throw new IllegalArgumentException("Syöte eräpäivä on virheellinen.");
            }
            if (!tarkistin.onkoPvmMerkkijonoMuotoaNnPisteNnPisteNnnnValidi(erapaivaKentta.getText())) {
                throw new IllegalArgumentException("Syöte eräpäivä virheellinen.");
            }
            GregorianCalendar erapaiva = new GregorianCalendar();
            erapaiva.setTime(pvmFormaatti.parse(erapaivaKentta.getText()));

            Viite viite = new Viite(lataaja.getLadattuTietovarasto().getAsiakkaat().get(comboBoxkuuntelija.getValinta()).getAsiakasnumero() + lataaja.getLadattuTietovarasto().getAsiakkaat().get(comboBoxkuuntelija.getValinta()).annaLaskujaLahetettyPlusYksi());

            ArrayList<Suorite> suoritteet = new ArrayList<>();
            for (int i = 0; i < suoritteetLista.valitutRivit().length; i++) {
                suoritteet.add(lataaja.getLadattuTietovarasto().asiakkaanLaskuttamattomatSuoritteetArrayList(lataaja.getLadattuTietovarasto().getAsiakkaat().get(comboBoxkuuntelija.getValinta())).get(suoritteetLista.valitutRivit()[i]));
            }
            if (suoritteet.isEmpty()) {
                throw new IllegalArgumentException("Ei valittuja suoritteita.");
            }

            double s = 0.0;
            for (int i = 0; i < suoritteet.size(); i++) {
                s = s + suoritteet.get(i).getYht();
            }
            Double summa = s;
            Integer eurot = summa.intValue();
            if (!LaskunSumma.tarkistaEurot(eurot)) {
                throw new IllegalArgumentException("Laskun summa on epäkelpo.");
            }
            Integer sentit = (int) Math.round((summa - summa.intValue()) * 100.0);
            if (!LaskunSumma.tarkistaSentit(sentit)) {
                throw new IllegalArgumentException("Laskun summa on epäkelpo.");
            }
            LaskunSumma laskunSumma = new LaskunSumma(eurot, sentit);

            Pankkiviivakoodi pankkiviivakoodi = new Pankkiviivakoodi(lataaja.getLadattuTietovarasto().getLaskuttaja().getTilinumero(), laskunSumma, viite, erapaiva);

            Lasku lasku = new Lasku(lataaja.getLadattuTietovarasto().getLaskuttaja(),
                    lataaja.getLadattuTietovarasto().getAsiakkaat().get(comboBoxkuuntelija.getValinta()),
                    paivays,
                    lataaja.getLadattuTietovarasto().getLaskuttaja().annaUusiLaskunNumero(),
                    erapaiva,
                    Integer.parseInt(viivastyskorkoKentta.getText()),
                    viite,
                    maksuehtoKentta.getText(),
                    suoritteet,
                    lisatiedotKentta.getText(),
                    laskunSumma,
                    pankkiviivakoodi);

            if (!lasku.onkoViivastyskorkoOikeanlainen()) {
                throw new IllegalArgumentException("Syöte viivästyskorko on virheellinen.");
            }
            if (!lasku.onkoMaksuehtoOikeanlainen()) {
                throw new IllegalArgumentException("Syöte maksuehto on virheellinen.");
            }

            lasku.setMaksuaika(maksuaika);

            for (int i = 0; i < suoritteet.size(); i++) {
                suoritteet.get(i).setLasku(lasku);
            }

            lataaja.getLadattuTietovarasto().getLaskuttaja().kasvataLahetettyjenLaskujenMaaraaYhdella();
            lataaja.getLadattuTietovarasto().getAsiakkaat().get(comboBoxkuuntelija.getValinta()).kasvataLahetettyjenLaskujenMaaraaYhdella();
            lataaja.getLadattuTietovarasto().getLaskut().add(lasku);
            taulukko.lisaaLaskutTaulukkoRivi(lasku);
            
            laskuttajaOsioJPanel.paivitaSisalto();

            asiakkaatTaulukko.getModel().insertRow(comboBoxkuuntelija.getValinta(), lataaja.getLadattuTietovarasto().getAsiakkaat().get(comboBoxkuuntelija.getValinta()).asiakkaanTiedotTaulukossa());
            asiakkaatTaulukko.getModel().removeRow(comboBoxkuuntelija.getValinta() + 1);

            for (int i = 0; i < suoritteet.size(); i++) {
                suoritteetTaulukko.getModel().insertRow(lataaja.getLadattuTietovarasto().getSuoritteet().indexOf(suoritteet.get(i)), suoritteet.get(i).suoritteenTiedotTaulukossa());
                suoritteetTaulukko.getModel().removeRow(lataaja.getLadattuTietovarasto().getSuoritteet().indexOf(suoritteet.get(i)) + 1);
            }

            suljeIkkuna();
        } catch (Exception e) {
            LisaaLaskuIkkunaLisaaLaskuPoikkeusIkkuna poikkeusIkkuna = new LisaaLaskuIkkunaLisaaLaskuPoikkeusIkkuna();
            SwingUtilities.invokeLater(poikkeusIkkuna);
        }
    }

    private void suljeIkkuna() {
        frame.dispose();
        lukko.avaa();
    }
}
