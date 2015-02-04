/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.laskut.poista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.SwingUtilities;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.TaulukkoValintaKuuntelija;
import superlaskuttaja.kayttoliittyma.laskut.LaskutTaulukko;
import superlaskuttaja.kayttoliittyma.suoritteet.SuoritteetTaulukko;
import superlaskuttaja.logiikka.DataDeliver;

/**
 *
 * @author Augustus58
 */
public class LaskutPanelPoistaLaskuKuuntelija implements ActionListener {

    private final DataDeliver lataaja;
    private final LaskutTaulukko taulukko;
    private final TaulukkoValintaKuuntelija kuuntelija;
    private final NappulaLukko lukko;
    private final SuoritteetTaulukko suoritteetTaulukko;

    public LaskutPanelPoistaLaskuKuuntelija(DataDeliver lataaja, LaskutTaulukko taulukko, TaulukkoValintaKuuntelija kuuntelija, NappulaLukko lukko, SuoritteetTaulukko suoritteetTaulukko) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.kuuntelija = kuuntelija;
        this.lukko = lukko;
        this.suoritteetTaulukko = suoritteetTaulukko;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!lukko.onkoLukkoPaalla()) {
            try {
                kuuntelija.paivitaArvo();

                int laskunNumero = (Integer) taulukko.getModel().getValueAt(kuuntelija.getPaivitettyArvo(), 3);

                HashMap<Integer, Integer> hm = new HashMap<>();

                for (int i = 0; i < suoritteetTaulukko.getModel().getRowCount(); i++) {
                    hm.put((Integer) suoritteetTaulukko.getModel().getValueAt(i, 8), i);
                }

                //Valitun laskun suoritteet.
                ResultSet rs1 = lataaja.getDbc().executeQuery("select distinct suoritteenNumero\n"
                        + "from Suorite\n"
                        + "where lasku = " + laskunNumero + "\n"
                        + "");

                ArrayList<String> ar = new ArrayList<>();

                while (rs1.next()) {
                    ar.add(Integer.toString(rs1.getInt(1)));
                }
                
                for (int i = 0; i < ar.size(); i++) {
                    lataaja.getDbc().executeUpdate("update Suorite\n"
                            + "set lasku = NULL\n"
                            + "where suoritteenNumero = " + ar.get(i) + "\n"
                            + "");
                }
                
                ResultSet rs2;

                for (int i = 0; i < ar.size(); i++) {
                    rs2 = lataaja.getDbc().executeQuery("select distinct nimi, tilaaja, kuvaus, alkuaika, maara, maaranYksikot,\n"
                            + "((1+0.01*alvProsentti)*aHintaVeroton*maara) as yht, !isnull(lasku) as onkoLaskutettu, suoritteenNumero\n"
                            + "from Suorite, Asiakas\n"
                            + "where tilaaja = asiakasnumero\n"
                            + "and tilaajanVersio = versio\n"
                            + "and suoritteenNumero = " + ar.get(i) + "\n"
                            + "");

                    suoritteetTaulukko.addSuoritteetTaulukkoRiviKohtaan(hm.get(Integer.parseInt(ar.get(i))), rs2);
                    suoritteetTaulukko.getModel().removeRow(hm.get(Integer.parseInt(ar.get(i))) + 1);
                }

                //Otetaan pankkiviivakoodi talteen.
                rs2 = lataaja.getDbc().executeQuery("select distinct pankkiviivakoodi\n"
                        + "from Lasku\n"
                        + "where\n"
                        + "laskunNumero = " + laskunNumero + "\n"
                        + "");

                rs2.first();

                String pankkiviivakoodi = rs2.getString(1);

                lataaja.getDbc().executeUpdate("delete from Lasku\n"
                        + "where laskunNumero = " + laskunNumero + "\n"
                        + "");

                lataaja.getDbc().executeUpdate("delete from Pankkiviivakoodi\n"
                        + "where pankkiviivakoodi = " + pankkiviivakoodi + "\n"
                        + "");

                taulukko.getModel().removeRow(kuuntelija.getPaivitettyArvo());
            } catch (Exception e) {
                e.printStackTrace();
                LaskutPanelPoistaLaskuPoikkeusIkkuna poikkeusIkkuna = new LaskutPanelPoistaLaskuPoikkeusIkkuna();
                SwingUtilities.invokeLater(poikkeusIkkuna);
            }
        }
    }
}
