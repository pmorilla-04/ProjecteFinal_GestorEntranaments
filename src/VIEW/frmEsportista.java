package VIEW;

import CONTROLLER.GestiorFitxersTXT;
import CONTROLLER.Principal;
import DATA.Querys;
import DATA.Querys2;
import MODEL.Comentari;
import MODEL.Entrenament;
import MODEL.TipusEsport;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

public class frmEsportista extends JFrame {

    // COMPONENTS
    private JTable tblEntrenaments;
    private JTable tblComentaris;

    private JTextField txtId;
    private JTextField txtData;
    private JTextField txtDurada;
    private JTextField txtDistancia;

    private JComboBox<String> cbmIntensitat;
    private JComboBox<TipusEsport> cbmTipus;

    private JCheckBox chkCompletat;
    private JCheckBox chkValidat;

    private JButton btnFiltrar;
    private JButton btnCancelar;
    private JButton btnEntrenament;

    public frmEsportista() {

        setTitle("Panell Esportista");
        setSize(1450, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();

        carregarTipusEsport();
        carregarIntensitats();

        Querys.mostrarEntrenaments();
        omplirTaulaEntrenaments();

        afegirListenerTaulaEntrenament();
    }

    private void initComponents() {

        // COLORS
        Color bg = new Color(20, 20, 20);
        Color panel = new Color(35, 35, 35);
        Color text = Color.WHITE;

        JPanel principal = new JPanel(new BorderLayout());
        principal.setBackground(bg);

        // ===== HEADER =====

        JLabel lblTitol = new JLabel("PANEL ESPORTISTA");
        lblTitol.setForeground(new Color(0, 220, 120));
        lblTitol.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitol.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        principal.add(lblTitol, BorderLayout.NORTH);

        // ===== CONTENT =====

        JPanel content = new JPanel(new GridLayout(1, 2, 20, 20));
        content.setBackground(bg);
        content.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        // ==========================================
        // PANEL ESQUERRA
        // ==========================================

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(panel);
        leftPanel.setLayout(null);

        JLabel lblFiltres = new JLabel("FILTRES");
        lblFiltres.setForeground(Color.WHITE);
        lblFiltres.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblFiltres.setBounds(30, 20, 200, 30);

        leftPanel.add(lblFiltres);

        // ID
        addLabel(leftPanel, "ID", 30, 80);
        txtId = addTextField(leftPanel, 30, 105);

        // DATA
        addLabel(leftPanel, "DATA (yyyy-MM-dd)", 220, 80);
        txtData = addTextField(leftPanel, 220, 105);

        // DISTANCIA
        addLabel(leftPanel, "DISTÀNCIA", 30, 170);
        txtDistancia = addTextField(leftPanel, 30, 195);

        // DURADA
        addLabel(leftPanel, "DURADA", 220, 170);
        txtDurada = addTextField(leftPanel, 220, 195);

        // INTENSITAT
        addLabel(leftPanel, "INTENSITAT", 30, 260);

        cbmIntensitat = new JComboBox<>();
        cbmIntensitat.setBounds(30, 290, 150, 35);

        leftPanel.add(cbmIntensitat);

        // TIPUS
        addLabel(leftPanel, "TIPUS ESPORT", 220, 260);

        cbmTipus = new JComboBox<>();
        cbmTipus.setBounds(220, 290, 180, 35);

        leftPanel.add(cbmTipus);

        // CHECKS
        chkCompletat = new JCheckBox("COMPLETAT");
        chkCompletat.setBounds(30, 360, 150, 30);
        chkCompletat.setForeground(Color.WHITE);
        chkCompletat.setBackground(panel);

        leftPanel.add(chkCompletat);

        chkValidat = new JCheckBox("VALIDAT");
        chkValidat.setBounds(220, 360, 150, 30);
        chkValidat.setForeground(Color.WHITE);
        chkValidat.setBackground(panel);

        leftPanel.add(chkValidat);

        // BOTONS
        btnFiltrar = new JButton("FILTRAR");
        btnFiltrar.setBounds(30, 450, 160, 45);
        estilBoto(btnFiltrar, new Color(0, 170, 90));

        btnCancelar = new JButton("NETEJAR");
        btnCancelar.setBounds(220, 450, 160, 45);
        estilBoto(btnCancelar, new Color(220, 53, 69));

        btnEntrenament = new JButton("GESTIONAR ENTRENAMENT");
        btnEntrenament.setBounds(30, 540, 350, 50);
        estilBoto(btnEntrenament, new Color(0, 140, 255));

        leftPanel.add(btnFiltrar);
        leftPanel.add(btnCancelar);
        leftPanel.add(btnEntrenament);

        // ==========================================
        // PANEL DRETA
        // ==========================================

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(panel);
        rightPanel.setLayout(new BorderLayout(15, 15));

        JPanel topTables = new JPanel(new GridLayout(2, 1, 10, 20));
        topTables.setBackground(panel);

        // TAULA ENTRENAMENTS
        JPanel entrenamentPanel = new JPanel(new BorderLayout());
        entrenamentPanel.setBackground(panel);

        JLabel lblEntrenaments = new JLabel("ENTRENAMENTS");
        lblEntrenaments.setForeground(Color.WHITE);
        lblEntrenaments.setFont(new Font("Segoe UI", Font.BOLD, 20));

        tblEntrenaments = new JTable();

        JScrollPane sp1 = new JScrollPane(tblEntrenaments);

        entrenamentPanel.add(lblEntrenaments, BorderLayout.NORTH);
        entrenamentPanel.add(sp1, BorderLayout.CENTER);

        // TAULA COMENTARIS
        JPanel comentariPanel = new JPanel(new BorderLayout());
        comentariPanel.setBackground(panel);

        JLabel lblComentaris = new JLabel("COMENTARIS");
        lblComentaris.setForeground(Color.WHITE);
        lblComentaris.setFont(new Font("Segoe UI", Font.BOLD, 20));

        tblComentaris = new JTable();

        JScrollPane sp2 = new JScrollPane(tblComentaris);

        comentariPanel.add(lblComentaris, BorderLayout.NORTH);
        comentariPanel.add(sp2, BorderLayout.CENTER);

        topTables.add(entrenamentPanel);
        topTables.add(comentariPanel);

        rightPanel.add(topTables, BorderLayout.CENTER);

        // AFEGIR PANELS
        content.add(leftPanel);
        content.add(rightPanel);

        principal.add(content, BorderLayout.CENTER);

        add(principal);

        // EVENTS
        btnFiltrar.addActionListener(e -> filtrar());

        btnCancelar.addActionListener(e -> cancelarFiltres());

        btnEntrenament.addActionListener(e -> {
            frmEntranaments f = new frmEntranaments();
            f.setVisible(true);

            GestiorFitxersTXT.escripturaAFitxerLog(
                    "Obrint formulari entrenaments"
            );
        });
    }

    // ==========================================
    // ESTILS
    // ==========================================

    private void estilBoto(JButton boto, Color color) {

        boto.setBackground(color);
        boto.setForeground(Color.WHITE);
        boto.setFocusPainted(false);
        boto.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boto.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void addLabel(JPanel panel, String text, int x, int y) {

        JLabel lbl = new JLabel(text);

        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbl.setBounds(x, y, 180, 25);

        panel.add(lbl);
    }

    private JTextField addTextField(JPanel panel, int x, int y) {

        JTextField txt = new JTextField();

        txt.setBounds(x, y, 150, 35);

        panel.add(txt);

        return txt;
    }

    // ==========================================
    // LOGICA ORIGINAL
    // ==========================================

    private void carregarIntensitats() {

        cbmIntensitat.removeAllItems();
        cbmIntensitat.addItem(null);

        try {

            ResultSet rs = Querys.getIntensitats();

            if (rs.next()) {

                String enumValue = rs.getString(2);

                enumValue = enumValue
                        .replace("enum(", "")
                        .replace(")", "")
                        .replace("'", "");

                String[] values = enumValue.split(",");

                for (String v : values) {

                    cbmIntensitat.addItem(v.trim());

                    GestiorFitxersTXT.escripturaAFitxerLog(
                            "Carreguem intensitats"
                    );
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    private void carregarTipusEsport() {

        cbmTipus.removeAllItems();

        Principal.tipusesports.clear();

        try {

            ResultSet rs = Querys.getTipusEsport();

            while (rs.next()) {

                int id = rs.getInt("id");
                String nom = rs.getString("nom");

                TipusEsport tipus = new TipusEsport(id, nom);

                cbmTipus.addItem(tipus);

                Principal.tipusesports.add(tipus);

                GestiorFitxersTXT.escripturaAFitxerLog(
                        "Carreguem tipus esport"
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void omplirTaulaEntrenaments() {

        String[] columnes = {
                "ID",
                "DATA",
                "DURADA",
                "DISTANCIA",
                "DESCRIPCIO",
                "INTENSITAT",
                "COMPLETAT",
                "VALIDAT",
                "ID_USUARI",
                "TIPUS ESPORT"
        };

        DefaultTableModel model = new DefaultTableModel();

        model.setColumnIdentifiers(columnes);

        for (Entrenament e : Principal.entrenaments) {

            Object[] fila = {
                    e.getId(),
                    e.getData(),
                    e.getDuradaMinuts(),
                    e.getDistancia(),
                    e.getDescripcio(),
                    e.getIntensitat(),
                    e.isCompletat(),
                    e.isValidat(),
                    e.getUsuariId(),
                    obtenirNomTipusEsport(e.getTipusEsportId())
            };

            model.addRow(fila);
        }

        tblEntrenaments.setModel(model);

        GestiorFitxersTXT.escripturaAFitxerLog(
                "Omplint taula entrenaments"
        );
    }

    private String obtenirNomTipusEsport(int idTipus) {

        for (TipusEsport t : Principal.tipusesports) {

            if (t.getId() == idTipus) {

                return t.getNom();
            }
        }

        return "";
    }

    public void omplirTaulaComentaris() {

        String[] columnes = {
                "ID",
                "TEXT",
                "DATA",
                "ENTRENAMENT_ID",
                "ENTRENADOR_ID"
        };

        DefaultTableModel model = new DefaultTableModel();

        model.setColumnIdentifiers(columnes);

        for (Comentari c : Principal.comentaris) {

            Object[] fila = {
                    c.getId(),
                    c.getText(),
                    c.getData(),
                    c.getEntrenamentId(),
                    c.getEntrenadorId()
            };

            model.addRow(fila);
        }

        tblComentaris.setModel(model);
    }

    private void filtrar() {

        try {

            Integer id = txtId.getText().isEmpty()
                    ? null
                    : Integer.parseInt(txtId.getText());

            LocalDate data = txtData.getText().isEmpty()
                    ? null
                    : LocalDate.parse(txtData.getText());

            Integer durada = txtDurada.getText().isEmpty()
                    ? null
                    : Integer.parseInt(txtDurada.getText());

            Integer distancia = txtDistancia.getText().isEmpty()
                    ? null
                    : Integer.parseInt(txtDistancia.getText());

            Entrenament.Intensitat intensitat =
                    cbmIntensitat.getSelectedItem() == null
                    ? null
                    : Entrenament.Intensitat.valueOf(
                            cbmIntensitat.getSelectedItem()
                                    .toString()
                                    .toUpperCase()
                    );

            Boolean completat =
                    chkCompletat.isSelected() ? true : null;

            Boolean validat =
                    chkValidat.isSelected() ? true : null;

            Integer tipus = null;

            if (cbmTipus.getSelectedItem() != null) {

                tipus = Querys2.tornarIdTipusEsportAmbNom(
                        cbmTipus.getSelectedItem().toString()
                );
            }

            Querys.filtrarEntrenament(
                    id,
                    data,
                    durada,
                    distancia,
                    intensitat,
                    completat,
                    validat,
                    tipus
            );

            omplirTaulaEntrenaments();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, e.getMessage());

            e.printStackTrace();
        }
    }

    private void cancelarFiltres() {

        txtId.setText("");
        txtData.setText("");
        txtDurada.setText("");
        txtDistancia.setText("");

        cbmIntensitat.setSelectedIndex(-1);
        cbmTipus.setSelectedIndex(-1);

        chkCompletat.setSelected(false);
        chkValidat.setSelected(false);

        Querys.mostrarEntrenaments();

        omplirTaulaEntrenaments();

        GestiorFitxersTXT.escripturaAFitxerLog(
                "Filtratge cancel·lat"
        );
    }

    private void afegirListenerTaulaEntrenament() {

        tblEntrenaments.getSelectionModel()
                .addListSelectionListener((ListSelectionEvent e) -> {

            if (!e.getValueIsAdjusting()) {

                int fila = tblEntrenaments.getSelectedRow();

                if (fila != -1) {

                    int idEntrenament = Integer.parseInt(
                            tblEntrenaments.getValueAt(fila, 0)
                                    .toString()
                    );

                    Querys.mostrarComentari(idEntrenament);

                    omplirTaulaComentaris();
                }
            }
        });
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new frmEsportista().setVisible(true);
        });
    }
}