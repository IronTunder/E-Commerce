package src.view.components;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class PannelloLaterale extends JPanel {
    private final String[] categories = {
            "Accessori PC", "Accessori SmartPhone", "Audio, Video e Gaming",
            "Casa e Ufficio", "Cavetteria", "Componenti PC",
            "Computer Desktop", "Consumabili", "Notebook e Accessori",
            "Usato Garantito"
    };

    public PannelloLaterale() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(50, 50, 50));
        setPreferredSize(new Dimension(215, 400));

        // Titolo "Categorie"
        JLabel titleLabel = new JLabel("Categorie");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Lista delle categorie
        JList<String> categoryList = creaListaCategoria();
        JScrollPane scroller = new JScrollPane(categoryList);
        scroller.setBorder(BorderFactory.createEmptyBorder());
        add(scroller, BorderLayout.CENTER);

        // Logo in fondo
        caricaLogo();
    }

    private JList<String> creaListaCategoria() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String category : categories) {
            model.addElement(category);
        }

        JList<String> categoryList = new JList<>(model);
        confLista(categoryList); // Configura bordi e colori

        // Altezza fissa per ogni elemento + spaziatura
        categoryList.setFixedCellHeight(35); // Più alto per dare spazio

        // Personalizzazione della grafica degli elementi
        categoryList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus
            ) {
                JLabel label = (JLabel) super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus
                );

                // Stile normale
                label.setBackground(new Color(50, 50, 50));
                label.setForeground(Color.WHITE);
                label.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createBevelBorder(BevelBorder.RAISED,
                                new Color(80, 80, 80),
                                new Color(30, 30, 30)),
                        BorderFactory.createEmptyBorder(5, 0, 5, 0)
                ));

                // Stile quando selezionato
                if (isSelected) {
                    label.setBackground(Color.WHITE);
                    label.setForeground(Color.BLACK);
                    label.setFont(label.getFont().deriveFont(Font.BOLD));
                    label.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createBevelBorder(BevelBorder.LOWERED,
                                    Color.DARK_GRAY,
                                    Color.GRAY),
                            BorderFactory.createEmptyBorder(5, 0, 5, 0)
                    ));
                }

                return label;
            }
        });

        return categoryList;
    }

    private void confLista(JList<String> list) {
        list.setFont(new Font("Arial", Font.PLAIN, 14));
        list.setBackground(new Color(50, 50, 50));
        list.setSelectionBackground(new Color(70, 130, 180)); // Colore di selezione
        list.setSelectionForeground(Color.WHITE);
        list.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.LOWERED,
                        new Color(80, 80, 80),
                        new Color(30, 30, 30)),
                BorderFactory.createEmptyBorder(5, 0, 5, 0)
        ));
    }

    private void caricaLogo() {
        try {
            ImageIcon originalIcon = new ImageIcon("././icon.png");
            int newWidth = 150;
            int newHeight = (int) (originalIcon.getIconHeight() *
                    ((double) newWidth / originalIcon.getIconWidth()));
            Image scaledImage = originalIcon.getImage().getScaledInstance(
                    newWidth, newHeight, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
            logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JPanel logoPanel = new JPanel(new BorderLayout());
            logoPanel.setBackground(new Color(50, 50, 50));
            logoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
            logoPanel.add(logoLabel, BorderLayout.CENTER);
            add(logoPanel, BorderLayout.SOUTH);
        } catch (Exception e) {
            System.err.println("Errore nel caricamento del logo: " + e.getMessage());
        }
    }
}