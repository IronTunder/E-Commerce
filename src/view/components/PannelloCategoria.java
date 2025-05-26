package src.view.components;

import src.controllers.AuthController;
import src.models.Prodotto;

import src.models.Utente;
import src.models.categories.*;
import src.view.HomePage;
import src.view.auth.InterfacciaAuth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PannelloCategoria extends JPanel{
    AuthController authController;
    HomePage homePage;
    InterfacciaAuth interfacciaAuth;
    public PannelloCategoria(String titoloCategoria, List<Prodotto> prodotti, HomePage homePage) {
        this.authController = homePage.getAuthController();
        this.interfacciaAuth = homePage.getInterfacciaAuth();
        this.homePage = homePage;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Titolo della categoria
        JLabel titolo = new JLabel(titoloCategoria);
        titolo.setFont(new Font("Arial", Font.BOLD, 18));
        titolo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        add(titolo, BorderLayout.NORTH);

        // Pannello prodotti con WRAPPER per lo scroll
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(new Color(240, 240, 240));

        JPanel prodottiPanel = new JPanel();
        prodottiPanel.setLayout(new BoxLayout(prodottiPanel, BoxLayout.X_AXIS));
        prodottiPanel.setBackground(new Color(240, 240, 240));

        // Limita a 10 prodotti per categoria per evitare overflow
        int maxProdotti = Math.min(prodotti.size(), 10);
        for (int i = 0; i < maxProdotti; i++) {
            JPanel prodottoCard = creaProdottoCard(prodotti.get(i));
            prodottiPanel.add(prodottoCard);
            prodottiPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        }

        // Aggiungi spazio flessibile a destra
        prodottiPanel.add(Box.createHorizontalGlue());

        wrapper.add(prodottiPanel, BorderLayout.CENTER);

        // Configurazione dello scroll pane
        JScrollPane scrollPane = new JScrollPane(wrapper);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16);

        // Imposta dimensioni fisse per lo scrollpane
        scrollPane.setPreferredSize(new Dimension(800, 300)); // Larghezza fissa

        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel creaProdottoCard(Prodotto prodotto) {
        JPanel card = new JPanel();

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                homePage.mostraProdotto(prodotto);
            }
        });
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(128, 128, 128)),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));
            }
        });
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(200, 200, 200)),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));
            }
        });
        card.setLayout(new BorderLayout(5, 5));
        card.setPreferredSize(new Dimension(180, 200));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Immagine placeholder
        JLabel imgLabel = new JLabel("Caricamento...", SwingConstants.CENTER);
        imgLabel.setPreferredSize(new Dimension(160, 100));

        new Thread(() -> {
            try {
                ImageIcon originalIcon = new ImageIcon(new URL(prodotto.getUrlImage()));
                Image scaledImage = originalIcon.getImage().getScaledInstance(
                        160, 100, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);

                SwingUtilities.invokeLater(() -> {
                    imgLabel.setIcon(scaledIcon);
                    imgLabel.setText("");
                    imgLabel.revalidate();
                    imgLabel.repaint();
                });
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> {
                    imgLabel.setIcon(null);
                    imgLabel.setText("Immagine non disponibile");
                });
            }
        }).start();

        // Info prodotto base
        JLabel nomeLabel = new JLabel(prodotto.getNome());
        JLabel prezzoLabel = new JLabel("€ " + String.format("%.2f", prodotto.getPrezzo()), SwingConstants.RIGHT);
        nomeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        prezzoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        prezzoLabel.setForeground(new Color(0, 100, 0));

        // Pannello per gli attributi specifici
        JPanel attributiPanel = new JPanel();
        attributiPanel.setLayout(new BoxLayout(attributiPanel, BoxLayout.Y_AXIS));

        // Aggiungi attributi in base alla categoria
        switch(prodotto.getCategoria()) {
            case "ComponentiPC":
                if(prodotto instanceof ComponentiPC) {
                    ComponentiPC componente = (ComponentiPC) prodotto;
                    attributiPanel.add(new JLabel("Tipo: " + componente.getTipoComponente()));
                    attributiPanel.add(new JLabel("Specifiche: " + componente.getSpecificaTecnica()));
                    attributiPanel.add(new JLabel("Capacità: " + componente.getCapacitaGB() + "GB"));
                    attributiPanel.add(new JLabel("Compatibilità: " + componente.getCompatibilita()));
                }
                break;

            case "ComputerDesktop":
                if(prodotto instanceof ComputerDesktop) {
                    ComputerDesktop desktop = (ComputerDesktop) prodotto;
                    attributiPanel.add(new JLabel("CPU: " + desktop.getCpu()));
                    attributiPanel.add(new JLabel("GPU: " + desktop.getGpu()));
                    attributiPanel.add(new JLabel("RAM: " + desktop.getRamGB() + "GB"));
                    attributiPanel.add(new JLabel("Memoria: " + desktop.getMemoriaGB() + "GB"));
                    attributiPanel.add(new JLabel("SO: " + desktop.getSO()));
                }
                break;

            case "NotebookeAccessori":
                if(prodotto instanceof NotebookEAccessori) {
                    NotebookEAccessori notebook = (NotebookEAccessori) prodotto;
                    attributiPanel.add(new JLabel("Display: " + notebook.getDimensioneDisplayPollici() + "\""));
                    attributiPanel.add(new JLabel("Risoluzione: " + notebook.getRisoluzione()));
                    attributiPanel.add(new JLabel(notebook.isTouchscreen() ? "Touchscreen: Sì" : "Touchscreen: No"));
                    attributiPanel.add(new JLabel("Autonomia: " + notebook.getAutonomiaOre() + " ore"));
                    attributiPanel.add(new JLabel("CPU: " + notebook.getCpu()));
                    attributiPanel.add(new JLabel("RAM: " + notebook.getRamGB() + "GB"));
                }
                break;

            case "AccessoriPC":
                if(prodotto instanceof AccessoriPC) {
                    AccessoriPC accessorioPC = (AccessoriPC) prodotto;
                    attributiPanel.add(new JLabel("Tipo: " + accessorioPC.getTipoAccessorio()));
                    attributiPanel.add(new JLabel("Connettività: " + accessorioPC.getConnettivita()));
                    attributiPanel.add(new JLabel(accessorioPC.isRgb() ? "RGB: Sì" : "RGB: No"));
                    attributiPanel.add(new JLabel("Compatibilità: " + accessorioPC.getCompatibilitaSistema()));
                    attributiPanel.add(new JLabel("Dimensioni: " + accessorioPC.getDimensioni()));
                    attributiPanel.add(new JLabel(accessorioPC.isErgonomico() ? "Ergonomico: Sì" : "Ergonomico: No"));
                }
                break;

            case "AccessoriSmartPhone":
                if(prodotto instanceof AccessoriSmartPhone) {
                    AccessoriSmartPhone accessorioPhone = (AccessoriSmartPhone) prodotto;
                    attributiPanel.add(new JLabel("Materiale: " + accessorioPhone.getTipoMateriale()));
                    if(accessorioPhone.getCapacitaPowerBank() > 0) {
                        attributiPanel.add(new JLabel("Capacità: " + accessorioPhone.getCapacitaPowerBank() + "mAh"));
                    }
                    attributiPanel.add(new JLabel(accessorioPhone.isRicaricaWireless() ? "Wireless: Sì" : "Wireless: No"));
                    attributiPanel.add(new JLabel(accessorioPhone.isMagnetico() ? "Magnetico: Sì" : "Magnetico: No"));
                }
                break;

            case "Audio,VideoeGaming":
                if(prodotto instanceof AudioVideoGaming) {
                    AudioVideoGaming av = (AudioVideoGaming) prodotto;
                    attributiPanel.add(new JLabel("Tipo: " + av.getTipoProdotto()));
                    attributiPanel.add(new JLabel("Dimensioni: " + av.getPollici() + "\""));
                    attributiPanel.add(new JLabel("Frequenza: " + av.getHz() + "Hz"));
                    attributiPanel.add(new JLabel(av.isSupportaHDR() ? "HDR: Sì" : "HDR: No"));
                    attributiPanel.add(new JLabel(av.isWireless() ? "Wireless: Sì" : "Wireless: No"));
                }
                break;

            case "CasaeUfficio":
                if(prodotto instanceof CasaEUfficio) {
                    CasaEUfficio casa = (CasaEUfficio) prodotto;
                    attributiPanel.add(new JLabel("Tecnologia: " + casa.getTecnologia()));
                    attributiPanel.add(new JLabel("Velocità: " + casa.getVelocitaStampaPPM() + " ppm"));
                    attributiPanel.add(new JLabel(casa.isFronteRetroAutomatico() ? "Fronte/retro: Sì" : "Fronte/retro: No"));
                    attributiPanel.add(new JLabel(casa.isWifiIntegrato() ? "Wi-Fi: Sì" : "Wi-Fi: No"));
                }
                break;

            case "Cavetteria":
                if(prodotto instanceof Cavetteria) {
                    Cavetteria cavo = (Cavetteria) prodotto;
                    attributiPanel.add(new JLabel("Connettori: " + cavo.getTipoConnettori()));
                    attributiPanel.add(new JLabel("Lunghezza: " + cavo.getLunghezzaMetri() + "m"));
                    attributiPanel.add(new JLabel(cavo.isSupporta4K() ? "4K: Sì" : "4K: No"));
                    attributiPanel.add(new JLabel(cavo.isRinforzato() ? "Rinforzato: Sì" : "Rinforzato: No"));
                }
                break;

            case "Consumabili":
                if(prodotto instanceof Consumabili) {
                    Consumabili consumabile = (Consumabili) prodotto;
                    attributiPanel.add(new JLabel("Quantità: " + consumabile.getQuantita() + " " + consumabile.getUnitaDiMisura()));
                }
                break;

            case "UsatoGarantito":
                if(prodotto instanceof UsatoGarantito) {
                    UsatoGarantito usato = (UsatoGarantito) prodotto;
                    attributiPanel.add(new JLabel("Stato: " + usato.getStatoUsura()));
                    attributiPanel.add(new JLabel("Anno: " + usato.getAnnoProduzione()));
                    attributiPanel.add(new JLabel("Garanzia: " + usato.getMesiGaranziaResidua() + " mesi"));
                    attributiPanel.add(new JLabel(usato.isIncludeAccessoriOriginali() ? "Accessori originali: Sì" : "Accessori originali: No"));
                }
                break;

            default:
                attributiPanel.add(new JLabel("Marca: " + prodotto.getMarca()));
                attributiPanel.add(new JLabel("Prezzo: €" + String.format("%.2f", prodotto.getPrezzo())));
                break;
        }

        JButton aggiungiAlCarrello = creaBottone("Aggiungi al carrello", "Aggiungi " + prodotto.getNome() + " al carrello");
        aggiungiAlCarrello.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(authController.isLoggedIn()){
                    ArrayList<Utente> utenti = authController.getUtenti();
                    authController.getLogin().getCarrello().add(prodotto);
                    for (int i = 0; i < utenti.size(); i++) {
                        if(authController.getLogin().equals(utenti.get(i))){
                            utenti.remove(i);
                            utenti.add(authController.getLogin());
                            break;
                        }
                    }
                    authController.salvaUtenti(utenti);
                }
                else{
                    JOptionPane.showMessageDialog(homePage, "Accedere per utilizzare il carrello", "Attenzione", JOptionPane.WARNING_MESSAGE);
                    if (interfacciaAuth == null) {
                        interfacciaAuth = new InterfacciaAuth(authController);
                        interfacciaAuth.mostraPannelloLogin();
                    }
                }

            }
        });

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(nomeLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        infoPanel.add(attributiPanel);
        infoPanel.add(prezzoLabel);
        infoPanel.add(aggiungiAlCarrello);

        card.add(imgLabel, BorderLayout.NORTH);
        card.add(infoPanel, BorderLayout.CENTER);

        return card;
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