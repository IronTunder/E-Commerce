package src.view.components;

import javax.swing.*;
import java.awt.*;

public class PannelloLaterale extends JPanel {
    private String[] categories = {
            "Accessori PC", "Accessori SmartPhone", "Audio, Video e Gaming",
            "Casa e Ufficio", "Cavetteria", "Componenti PC",
            "Computer Desktop", "Consumabili", "Notebook e Accessori",
            "Usato Garantito"
    };

    public PannelloLaterale() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(50, 50, 50));
        setPreferredSize(new Dimension(200, 400));

        JLabel titleLabel = new JLabel("Categorie");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        DefaultListModel<String> model = new DefaultListModel<>();
        for (String category : categories) {
            model.addElement(category);
        }

        JList<String> categoryList = new JList<>(model);
        confLista(categoryList);

        JScrollPane scroller = new JScrollPane(categoryList);
        scroller.setBorder(BorderFactory.createEmptyBorder());
        add(scroller, BorderLayout.CENTER);
        caricaLogo();
    }

    private void confLista(JList<String> list) {
        list.setFont(new Font("Arial", Font.PLAIN, 14));
        list.setBackground(new Color(50, 50, 50));
        list.setForeground(Color.WHITE);
        list.setSelectionBackground(Color.WHITE);
        list.setSelectionForeground(Color.BLACK);
        list.setBorder(BorderFactory.createBevelBorder(1,Color.DARK_GRAY,Color.BLACK));
    }

    private void caricaLogo() {
        try {
            JLabel logoLabel = getJLabel();
            // Pannello contenitore
            JPanel logoPanel = new JPanel(new BorderLayout());
            logoPanel.setBackground(new Color(50, 50, 50));
            logoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
            logoPanel.add(logoLabel, BorderLayout.CENTER);

            add(logoPanel, BorderLayout.SOUTH);
        } catch (Exception e) {
            System.err.println("Errore nel caricamento dell'immagine: " + e.getMessage());
        }
    }

    private static JLabel getJLabel() {
        ImageIcon originalIcon = new ImageIcon("././icon.png");
        Image originalImage = originalIcon.getImage();

        // Ridimensiona l'immagine se necessario (150px di larghezza)
        int newWidth = 150;
        int newHeight = (int) (originalIcon.getIconHeight() *
                ((double) newWidth / originalIcon.getIconWidth()));
        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Crea l'etichetta con l'immagine
        JLabel logoLabel = new JLabel(scaledIcon);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        return logoLabel;
    }
}