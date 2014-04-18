/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.laskut.muokkaa;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import superlaskuttaja.kayttoliittyma.TaulukkoValintaKuuntelija;
import superlaskuttaja.logiikka.Lataaja;
import superlaskuttaja.logiikka.Suorite;

/**
 *
 * @author Augustus58
 */
public class MuokkaaLaskuaIkkunaSuoritteetList extends JList {

    private final DefaultListModel<Suorite> model;
    private final MuokkaaLaskuaIkkunaComboBoxKuuntelija comboBoxKuuntelija;
    private final TaulukkoValintaKuuntelija kuuntelija;
    private final Lataaja lataaja;

    public MuokkaaLaskuaIkkunaSuoritteetList(MuokkaaLaskuaIkkunaComboBoxKuuntelija comboBoxKuuntelija, TaulukkoValintaKuuntelija kuuntelija, Lataaja lataaja) {
        super();
        this.comboBoxKuuntelija = comboBoxKuuntelija;
        this.kuuntelija = kuuntelija;
        this.lataaja = lataaja;
        this.model = new DefaultListModel<>();
        this.setModel(model);
    }

    public void paivitaListanSisalto() {
        model.removeAllElements();
        if (comboBoxKuuntelija.getValinta() == lataaja.getLadattuTietovarasto().getAsiakkaat().indexOf(lataaja.getLadattuTietovarasto().getLaskut().get(kuuntelija.getPaivitettyArvo()).getAsiakas())) {
            for (int i = 0; i < lataaja.getLadattuTietovarasto().asiakkaanLaskuttamattomatSuoritteetJaHalututSuoritteetArrayList(lataaja.getLadattuTietovarasto().getAsiakkaat().get(comboBoxKuuntelija.getValinta()), lataaja.getLadattuTietovarasto().getLaskut().get(kuuntelija.getPaivitettyArvo()).getSuoritteet()).size(); i++) {
                model.addElement(lataaja.getLadattuTietovarasto().asiakkaanLaskuttamattomatSuoritteetJaHalututSuoritteetArrayList(lataaja.getLadattuTietovarasto().getAsiakkaat().get(comboBoxKuuntelija.getValinta()), lataaja.getLadattuTietovarasto().getLaskut().get(kuuntelija.getPaivitettyArvo()).getSuoritteet()).get(i));
            }
            int j = 0;
            int[] indeksit;
            indeksit = new int[lataaja.getLadattuTietovarasto().getLaskut().get(kuuntelija.getPaivitettyArvo()).getSuoritteet().size()];
            for (int i = 0; i < lataaja.getLadattuTietovarasto().asiakkaanLaskuttamattomatSuoritteetJaHalututSuoritteetArrayList(lataaja.getLadattuTietovarasto().getAsiakkaat().get(comboBoxKuuntelija.getValinta()), lataaja.getLadattuTietovarasto().getLaskut().get(kuuntelija.getPaivitettyArvo()).getSuoritteet()).size(); i++) {
                if (lataaja.getLadattuTietovarasto().getLaskut().get(kuuntelija.getPaivitettyArvo()).getSuoritteet().contains(lataaja.getLadattuTietovarasto().asiakkaanLaskuttamattomatSuoritteetJaHalututSuoritteetArrayList(lataaja.getLadattuTietovarasto().getAsiakkaat().get(comboBoxKuuntelija.getValinta()), lataaja.getLadattuTietovarasto().getLaskut().get(kuuntelija.getPaivitettyArvo()).getSuoritteet()).get(i))) {
                    indeksit[j] = i;
                    j++;
                }
            }
            this.setSelectedIndices(indeksit);
        } else {
            for (int i = 0; i < lataaja.getLadattuTietovarasto().asiakkaanLaskuttamattomatSuoritteetArrayList(lataaja.getLadattuTietovarasto().getAsiakkaat().get(comboBoxKuuntelija.getValinta())).size(); i++) {
                model.addElement(lataaja.getLadattuTietovarasto().asiakkaanLaskuttamattomatSuoritteetArrayList(lataaja.getLadattuTietovarasto().getAsiakkaat().get(comboBoxKuuntelija.getValinta())).get(i));
            }
        }
    }

    @Override
    public DefaultListModel getModel() {
        return model;
    }

    public int[] valitutRivit() {
        return this.getSelectedIndices();
    }
}
