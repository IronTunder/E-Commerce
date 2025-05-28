package src.view.prodotto;

import src.models.Prodotto;
import src.view.HomePage;
import src.models.categories.*;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ProdottoPanel extends JPanel {
    private final HomePage homePage;
    private final Prodotto prodotto;

    public ProdottoPanel(HomePage homePage, Prodotto prodotto) {
        this.homePage = homePage;
        this.prodotto = prodotto;
        setLayout(new BorderLayout());
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(240, 240, 240));


        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(new Color(240, 240, 240));

        JButton backButton = creaBottone("Torna alla Home","<- Torna indietro");
        backButton.addActionListener(e -> homePage.mostraHomePage());
        backButton.setPreferredSize(new Dimension(150,30));
        topPanel.add(backButton);

        add(topPanel, BorderLayout.NORTH);


        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(Color.WHITE);
        imagePanel.setPreferredSize(new Dimension(400, 400));

        JLabel imageLabel = new JLabel("Caricamento immagine...", JLabel.CENTER);
        imageLabel.setFont(new Font("Arial", Font.PLAIN, 14));


        new Thread(() -> {
            try {
                ImageIcon originalIcon = new ImageIcon(new URL(prodotto.getUrlImage()));
                ImageIcon scaledIcon = new ImageIcon(getImage(originalIcon));

                SwingUtilities.invokeLater(() -> {
                    imageLabel.setIcon(scaledIcon);
                    imageLabel.setText("");
                });
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> {
                    imageLabel.setIcon(null);
                    imageLabel.setText("Immagine non disponibile");
                });
            }
        }).start();

        imagePanel.add(imageLabel, BorderLayout.CENTER);
        mainPanel.add(imagePanel, BorderLayout.WEST);


        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        detailsPanel.setMaximumSize(new Dimension(400, Integer.MAX_VALUE));



        JLabel nameLabel = new JLabel(prodotto.getNome());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailsPanel.add(nameLabel);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 15)));


        JLabel priceLabel = new JLabel(String.format("Prezzo: €%.2f", prodotto.getPrezzo()));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        priceLabel.setForeground(new Color(200, 0, 0));
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailsPanel.add(priceLabel);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 20)));


        JLabel categoryLabel = new JLabel("Categoria: " + prodotto.getCategoria());
        categoryLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        categoryLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailsPanel.add(categoryLabel);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 20)));


        JTextArea descriptionArea = new JTextArea(prodotto.getDescrizione());
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 14));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(Color.WHITE);
        descriptionArea.setAlignmentX(Component.LEFT_ALIGNMENT);

        JScrollPane descriptionScroll = new JScrollPane(descriptionArea);
        descriptionScroll.setPreferredSize(new Dimension(300, 150));
        descriptionScroll.setBorder(BorderFactory.createTitledBorder("Descrizione"));
        detailsPanel.add(descriptionScroll);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel attributiPanel = new JPanel();
        attributiPanel.setLayout(new BoxLayout(attributiPanel, BoxLayout.Y_AXIS));
        attributiPanel.setBackground(Color.WHITE);
        attributiPanel.setBorder(BorderFactory.createTitledBorder("Specifiche Tecniche"));


        switch(prodotto.getCategoria()) {
            case "ComponentiPC":
                if(prodotto instanceof ComponentiPC) {
                    ComponentiPC componente = (ComponentiPC) prodotto;
                    attributiPanel.add(creaLabelSpecifica("Tipo:", componente.getTipoComponente()));
                    attributiPanel.add(creaLabelSpecifica("Specifiche:", componente.getSpecificaTecnica()));
                    attributiPanel.add(creaLabelSpecifica("Capacità:", componente.getCapacitaGB() + "GB"));
                    attributiPanel.add(creaLabelSpecifica("Compatibilità:", componente.getCompatibilita()));
                }
                break;

            case "ComputerDesktop":
                if(prodotto instanceof ComputerDesktop) {
                    ComputerDesktop desktop = (ComputerDesktop) prodotto;
                    attributiPanel.add(creaLabelSpecifica("CPU:", desktop.getCpu()));
                    attributiPanel.add(creaLabelSpecifica("GPU:", desktop.getGpu()));
                    attributiPanel.add(creaLabelSpecifica("RAM:", desktop.getRamGB() + "GB"));
                    attributiPanel.add(creaLabelSpecifica("Memoria:", desktop.getMemoriaGB() + "GB"));
                    attributiPanel.add(creaLabelSpecifica("Sistema Operativo:", desktop.getSO()));
                }
                break;

            case "NotebookEAccessori":
                if(prodotto instanceof NotebookEAccessori) {
                    NotebookEAccessori notebook = (NotebookEAccessori) prodotto;
                    attributiPanel.add(creaLabelSpecifica("Display:", notebook.getDimensioneDisplayPollici() + "\""));
                    attributiPanel.add(creaLabelSpecifica("Risoluzione:", notebook.getRisoluzione()));
                    attributiPanel.add(creaLabelSpecifica("Touchscreen:", notebook.isTouchscreen() ? "Sì" : "No"));
                    attributiPanel.add(creaLabelSpecifica("Autonomia:", notebook.getAutonomiaOre() + " ore"));
                    attributiPanel.add(creaLabelSpecifica("CPU:", notebook.getCpu()));
                    attributiPanel.add(creaLabelSpecifica("RAM:", notebook.getRamGB() + "GB"));
                }
                break;

            case "AccessoriPC":
                if(prodotto instanceof AccessoriPC) {
                    AccessoriPC accessorioPC = (AccessoriPC) prodotto;
                    attributiPanel.add(creaLabelSpecifica("Tipo:", accessorioPC.getTipoAccessorio()));
                    attributiPanel.add(creaLabelSpecifica("Connettività:", accessorioPC.getConnettivita()));
                    attributiPanel.add(creaLabelSpecifica("RGB:", accessorioPC.isRgb() ? "Sì" : "No"));
                    attributiPanel.add(creaLabelSpecifica("Compatibilità:", accessorioPC.getCompatibilitaSistema()));
                    attributiPanel.add(creaLabelSpecifica("Dimensioni:", accessorioPC.getDimensioni()));
                    attributiPanel.add(creaLabelSpecifica("Ergonomico:", accessorioPC.isErgonomico() ? "Sì" : "No"));
                }
                break;

            case "AccessoriSmartPhone":
                if(prodotto instanceof AccessoriSmartPhone) {
                    AccessoriSmartPhone accessorioPhone = (AccessoriSmartPhone) prodotto;
                    attributiPanel.add(creaLabelSpecifica("Materiale:", accessorioPhone.getTipoMateriale()));
                    if(accessorioPhone.getCapacitaPowerBank() > 0) {
                        attributiPanel.add(creaLabelSpecifica("Capacità:", accessorioPhone.getCapacitaPowerBank() + "mAh"));
                    }
                    attributiPanel.add(creaLabelSpecifica("Wireless:", accessorioPhone.isRicaricaWireless() ? "Sì" : "No"));
                    attributiPanel.add(creaLabelSpecifica("Magnetico:", accessorioPhone.isMagnetico() ? "Sì" : "No"));
                }
                break;

            case "AudioVideoGaming":
                if(prodotto instanceof AudioVideoGaming) {
                    AudioVideoGaming av = (AudioVideoGaming) prodotto;
                    attributiPanel.add(creaLabelSpecifica("Tipo:", av.getTipoProdotto()));
                    attributiPanel.add(creaLabelSpecifica("Dimensioni:", av.getPollici() + "\""));
                    attributiPanel.add(creaLabelSpecifica("Frequenza:", av.getHz() + "Hz"));
                    attributiPanel.add(creaLabelSpecifica("HDR:", av.isSupportaHDR() ? "Sì" : "No"));
                    attributiPanel.add(creaLabelSpecifica("Wireless:", av.isWireless() ? "Sì" : "No"));
                }
                break;

            case "CasaEUfficio":
                if(prodotto instanceof CasaEUfficio) {
                    CasaEUfficio casa = (CasaEUfficio) prodotto;
                    attributiPanel.add(creaLabelSpecifica("Tecnologia:", casa.getTecnologia()));
                    attributiPanel.add(creaLabelSpecifica("Velocità:", casa.getVelocitaStampaPPM() + " ppm"));
                    attributiPanel.add(creaLabelSpecifica("Fronte/retro:", casa.isFronteRetroAutomatico() ? "Sì" : "No"));
                    attributiPanel.add(creaLabelSpecifica("Wi-Fi:", casa.isWifiIntegrato() ? "Sì" : "No"));
                }
                break;

            case "Cavetteria":
                if(prodotto instanceof Cavetteria) {
                    Cavetteria cavo = (Cavetteria) prodotto;
                    attributiPanel.add(creaLabelSpecifica("Connettori:", cavo.getTipoConnettori()));
                    attributiPanel.add(creaLabelSpecifica("Lunghezza:", cavo.getLunghezzaMetri() + "m"));
                    attributiPanel.add(creaLabelSpecifica("4K:", cavo.isSupporta4K() ? "Sì" : "No"));
                    attributiPanel.add(creaLabelSpecifica("Rinforzato:", cavo.isRinforzato() ? "Sì" : "No"));
                }
                break;

            case "Consumabili":
                if(prodotto instanceof Consumabili) {
                    Consumabili consumabile = (Consumabili) prodotto;
                    attributiPanel.add(creaLabelSpecifica("Quantità:", consumabile.getQuantita() + " " + consumabile.getUnitaDiMisura()));
                }
                break;

            case "UsatoGarantito":
                if(prodotto instanceof UsatoGarantito) {
                    UsatoGarantito usato = (UsatoGarantito) prodotto;
                    attributiPanel.add(creaLabelSpecifica("Stato:", usato.getStatoUsura()));
                    attributiPanel.add(creaLabelSpecifica("Anno:", ""+usato.getAnnoProduzione()));
                    attributiPanel.add(creaLabelSpecifica("Garanzia:", usato.getMesiGaranziaResidua() + " mesi"));
                    attributiPanel.add(creaLabelSpecifica("Accessori originali:", usato.isIncludeAccessoriOriginali() ? "Sì" : "No"));
                }
                break;

            default:
                attributiPanel.add(creaLabelSpecifica("Marca:", prodotto.getMarca()));
                break;
        }


        detailsPanel.add(attributiPanel);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 20)));


        JButton addToCartButton = creaBottone("Aggiungi al carrello","Aggiungi " + prodotto.getNome() + " al carrello");
        addToCartButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        addToCartButton.setPreferredSize(new Dimension(200, 40));
        addToCartButton.addActionListener(e -> {
            if (homePage.getAuthController().isLoggedIn()) {
                homePage.getAuthController().getLogin().getCarrello().add(prodotto);
                JOptionPane.showMessageDialog(this,
                        "Prodotto aggiunto al carrello!",
                        "Successo",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Devi effettuare il login per aggiungere prodotti al carrello",
                        "Attenzione",
                        JOptionPane.WARNING_MESSAGE);
            }
        });
        detailsPanel.add(addToCartButton);

        mainPanel.add(detailsPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
    }

    private static Image getImage(ImageIcon originalIcon) {
        Image originalImage = originalIcon.getImage();
        int originalWidth = originalIcon.getIconWidth();
        int originalHeight = originalIcon.getIconHeight();

        int boxWidth = 400;
        int boxHeight = 400;

        float widthRatio = (float) boxWidth / originalWidth;
        float heightRatio = (float) boxHeight / originalHeight;

        float scale = Math.min(widthRatio, heightRatio);

        int newWidth = Math.round(originalWidth * scale);
        int newHeight = Math.round(originalHeight * scale);

        Image scaledIcon = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        return scaledIcon;
    }

    private JPanel creaLabelSpecifica(String nome, String valore) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel nomeLabel = new JLabel(nome);
        nomeLabel.setFont(new Font("Arial", Font.BOLD, 12));
        nomeLabel.setPreferredSize(new Dimension(120, 20));

        JLabel valoreLabel = new JLabel(valore);
        valoreLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        panel.add(nomeLabel, BorderLayout.WEST);
        panel.add(valoreLabel, BorderLayout.CENTER);

        return panel;
    }

    private JButton creaBottone(String text, String tooltip) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(100, 30));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(100, 100, 100));
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        button.setFocusable(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setToolTipText(tooltip);
        button.setActionCommand(text);
        return button;
    }
}