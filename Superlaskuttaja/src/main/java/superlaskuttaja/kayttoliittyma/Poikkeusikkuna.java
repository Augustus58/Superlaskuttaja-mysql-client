/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
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
public class Poikkeusikkuna implements Runnable {

    private JDialog dialog;
    private final String viesti;

    public Poikkeusikkuna(String viesti) {
        this.viesti = viesti;
    }
    
    @Override
    public void run() {
        dialog = new JDialog();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        dialog.setLocation(160, 120);
        
        dialog.setResizable(false);

        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.setAlwaysOnTop(true);        

        luoKomponentit(dialog.getContentPane());

        dialog.pack();
        
        Dimension frameSize = dialog.getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        dialog.setLocation(x, y);
        
        dialog.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        JPanel panel = new JPanel();
        
        GridLayout layout = new GridLayout(0, 1);
        layout.setVgap(10);
        layout.setHgap(10);
        
        panel.setLayout(layout);
        
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JLabel teksti1 = new JLabel(viesti);
        
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new PoikkeusikkunaKuuntelija(dialog));
        
        panel.add(teksti1);
        panel.add(okButton);
        
        container.add(panel);
        
    }

    public JDialog getDialog() {
        return dialog;
    }

}
