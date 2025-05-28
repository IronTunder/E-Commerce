# Nucifora's Hub - E-Commerce Application

!![image](https://github.com/user-attachments/assets/a88a411a-8dd7-456d-b234-917f97fe6bc5)

## Descrizione del Progetto

Nucifora's Hub è un'applicazione e-commerce completa per la vendita di prodotti tecnologici, sviluppata in Java con Swing per l'interfaccia grafica. L'applicazione offre:

- Catalogo prodotti organizzato per categorie
- Sistema di autenticazione (login/registrazione)
- Carrello della spesa
- Pannelli di amministrazione per la gestione prodotti e ordini
- Visualizzazione dettagliata dei prodotti

## Funzionalità Principali

### Per gli Utenti
- **Registrazione e Login** con sistema di autenticazione sicuro
- **Navigazione per categorie** con pannello laterale dedicato
- **Visualizzazione dettagliata prodotti** con immagini e specifiche tecniche
- **Carrello della spesa** per gestire gli acquisti
- **Checkout** (da implementare)

### Per gli Amministratori
- **Gestione prodotti** (aggiunta, modifica, eliminazione)
- **Gestione ordini** (visualizzazione e modifica stato)
- **Dashboard amministrativa**

## Tecnologie Utilizzate

- **Java 8+**
- **Swing** per l'interfaccia grafica
- **Design Pattern MVC** (Model-View-Controller)
- **Serializzazione** per il salvataggio dei dati
- **Multithreading** per il caricamento delle immagini

## Struttura del Progetto

```
src/
├── controllers/       # Logica dell'applicazione
│   ├── AuthController.java 
│   ├── OrdersController.java
│   └── ProductsController.java
├── models/            # Modelli dati
│   ├── Prodotto.java 
│   ├── Utente.java
│   ├── Ordine.java
│   └── categories/   # Sottocategorie di prodotti
│       ├── AccessoriPC.java
│       ├── AccessoriSmartPhone.java
│       ├── AudioVideoGaming.java
│       ├── CasaEUfficio.java
│       ├── Cavetteria.java
│       ├── ComponentiPC.java
│       ├── ComputerDesktop.java
│       ├── Consumabili.java
│       ├── NotebookEAccessori.java
│       └── UsatoGarantito.java
├── view/              # Interfaccia grafica
│   ├── admin/         # Pannelli admin
│   │   ├── ordini/
│   │       └── OrderPanel.java
│   │   └── prodotti/  # Pannelli prodotti
│   │       ├── BottoniInserimento.java
│   │       ├── ElencoMagazzino.java
│   │       ├── Inserimento.java
│   │       └── ProductsPanel.java
│   │
│   ├── auth/          # Schermate login/registrazione
│   │   ├── InterfacciaAuth.java
│   │   ├── Login.java
│   │   └── Registrazione.java
│   ├── carrello/      # Gestione carrello
│   │   └── InterfacciaCart.java
│   ├── components/    # Componenti riutilizzabili
│   │   ├── PannelloCategoria.java
│   │   └── PannelloLaterale.java
│   ├── prodotto/      # Vista dettaglio prodotto
│   │   ├── Prodottopanel.java
│   └── HomePage.java  # Schermata principale
│App.java # Main
└──
```

## Diagramma UML 
![Diagramma UML](https://github.com/user-attachments/assets/6fb7fab2-1a39-4a33-8546-f0b9ff792665)

## Diagramma di sequenza 
![Diagramma di Sequenza](https://github.com/user-attachments/assets/d5438b8c-d6a1-405e-8adb-8de1505ae8aa)

## Requisiti di Sistema

- Java JDK 8 o superiore
- 4GB RAM (consigliati)
- 500MB spazio su disco

## Installazione e Esecuzione

1. Clonare il repository:
   ```bash
   git clone https://github.com/tuorepositorio/nucifora-hub.git
   ```

2. Compilare il progetto:
   ```bash
   cd nucifora-hub
   javac src/view/HomePage.java
   ```

3. Eseguire l'applicazione:
   ```bash
   java -cp src view.HomePage
   ```

## Credenziali di Test

**Amministratore:**
- Email: admin@nucifora.com
- Password: admin123

## Credenziali per gli admin
- Per registrare un nuovo admin è necessaria
  una parola chiave che è "pelato<3"

**Utente normale:**
- Email: user@example.com
- Password: user1234

## Contributi

I contributi sono benvenuti! Per favore aprire una issue o una pull request per suggerimenti o miglioramenti.

---

**Sviluppato da [IronTunder] e [kelskels07]**  
*Data ultimo aggiornamento: ultimo push*
