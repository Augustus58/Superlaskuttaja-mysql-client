/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.suoritteet.poista;

import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Augustus58
 */
public class SuoritteetPanelPoistaSuoritePoikkeusIkkuna implements Runnable {

    private JDialog dialog;

    @Override
    public void run() {
        dialog = new JDialog();
        dialog.setLocation(160, 120);
        
        dialog.setResizable(false);

        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.setAlwaysOnTop(true);

        luoKomponentit(dialog.getContentPane());

        dialog.pack();
        dialog.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        JPanel panel = new JPanel();
        
        GridLayout layout = new GridLayout(0, 1);
        layout.setVgap(10);
        layout.setHgap(10);
        
        panel.setLayout(layout);
        
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JLabel teksti1 = new JLabel("Yhtään suoritetta ei ole valittuna.");
        
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new SuoritteetPanelPoistaSuoritePoikkeusIkkunaOkKuuntelija(dialog));
        
        panel.add(teksti1);
        panel.add(okButton);
        
        container.add(panel);
        
    }

    public JDialog getDialog() {
        return dialog;
    }

}
