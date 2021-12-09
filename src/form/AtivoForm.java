package form;


import controller.AtivoController;
import model.Ativo;
import net.miginfocom.swing.MigLayout;
import table.AtivoTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class AtivoForm extends JFrame {
    private JLabel lbTicker, lbAlocacao, lbPrecoEntrada;
    private JTextField txtTicker, txtAlocacao, txtPrecoEntrada;
    private JPanel panelAdd, panelTable, panelButtons;
    private JButton btnNew, btnSave, btnUpdate, btnRemove, btnCancel;
    private JTable table;
    private JScrollPane scrollPane;

    private List<Ativo> ativoList;
    private Long idAtivo;

    public AtivoForm() throws HeadlessException {
        super("Cadastro de Ativos");
        setContentPane(new JPanel());
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelAdd = new JPanel(new MigLayout());
        panelAdd.setBorder(BorderFactory.createTitledBorder("Adicionar Ativo"));
        panelAdd.setBounds(5, 0, 480, 130);

        lbTicker = new JLabel("Ticker: ");
        lbAlocacao = new JLabel("Alocação: ");
        lbPrecoEntrada = new JLabel("Preço de Entrada: ");

        txtTicker = new JTextField(50);
        txtAlocacao = new JTextField(50);
        txtPrecoEntrada = new JTextField(15);

        panelAdd.add(lbTicker);
        panelAdd.add(txtTicker, "span, growx");

        panelAdd.add(lbAlocacao);
        panelAdd.add(txtAlocacao, "right,span, growx");

        panelAdd.add(lbPrecoEntrada);
        panelAdd.add(txtPrecoEntrada, "wrap para");

        panelButtons = new JPanel(new MigLayout());
        panelButtons.setBorder(BorderFactory.createEtchedBorder());
        panelButtons.setBounds(5, 130, 480, 50);

        btnNew = new JButton();
        btnNew.setIcon(new ImageIcon(AtivoForm.class.getResource("/img/add.png")));
        btnSave = new JButton();
        btnSave.setIcon(new ImageIcon(AtivoForm.class.getResource("/img/save.png")));
        btnCancel = new JButton();
        btnCancel.setIcon(new ImageIcon(AtivoForm.class.getResource("/img/cancel.png")));
        btnRemove = new JButton();
        btnRemove.setIcon(new ImageIcon(AtivoForm.class.getResource("/img/trash.png")));
        btnUpdate = new JButton();
        btnUpdate.setIcon(new ImageIcon(AtivoForm.class.getResource("/img/edit.png")));

        panelButtons.add(btnNew, "gapleft 90");
        panelButtons.add(btnCancel);
        panelButtons.add(btnSave, "gap unrelated");
        panelButtons.add(btnUpdate, "gap unrelated");
        panelButtons.add(btnRemove);

        panelTable = new JPanel(new MigLayout());
        panelTable.setBorder(BorderFactory.createTitledBorder("Lista de Ativos"));
        panelTable.setBounds(5, 180, 480, 240);

        table = new JTable();
        scrollPane = new JScrollPane(table);
        panelTable.add(scrollPane);
        refreshTable();
        enableFields(false);


        add(panelAdd);
        add(panelButtons);
        add(panelTable);
        setMinimumSize(new Dimension(550, 480));
        setVisible(true);

        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSaveAtivo();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancelar();
            }
        });

        btnNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onNewAtivo();
            }
        });

        btnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onRemoveAtivo();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onUpdateAtivo();
            }
        });

    }

    private void onUpdateAtivo() {
        int rowIndex = table.getSelectedRow();
        if (rowIndex == -1){
            JOptionPane.showMessageDialog(this,"Selecione o ativo a ser alterado !" );
            return;
        }
        Ativo ativo = new AtivoTableModel(ativoList).get(rowIndex);
        idAtivo = ativo.getId();
        txtTicker.setText(ativo.getTicker());
        txtAlocacao.setText(ativo.getAlocacao());
        txtPrecoEntrada.setText(ativo.getPrecoEntrada());
        enableFields(true);
    }

    private void onRemoveAtivo() {
        int rowIndex = table.getSelectedRow();
        if (rowIndex == -1){
            JOptionPane.showMessageDialog(this,"Selecione o ativo para remover !" );
            return;
        }
        Ativo ativo = new AtivoTableModel(ativoList).get(rowIndex);
        int confirm = JOptionPane.showConfirmDialog(this, "Deseja excluir ?", "Excluir Ativo", JOptionPane.YES_NO_OPTION);
        if(confirm != 0){
            return;
        }
        int result = new AtivoController().excluirAtivo(ativo.getId());
        if (result == 1) {
            JOptionPane.showMessageDialog(this, "Valor removido com sucesso");
            refreshTable();

        } else {
            JOptionPane.showMessageDialog(this, "Tente Novamente !");
        }
    }

    private void onNewAtivo() {
        enableFields(true);
    }

    private void onSaveAtivo() {
        Ativo ativo = new Ativo();
        if (txtTicker.getText().length() > 0 && txtAlocacao.getText().length() > 0 && txtPrecoEntrada.getText().length() > 0) {

            ativo.setTicker(txtTicker.getText());
            ativo.setAlocacao(txtAlocacao.getText());
            ativo.setPrecoEntrada(txtPrecoEntrada.getText());

        } else {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios");
            return;
        }

        int result = 0;
        if (idAtivo == null) {
            result = new AtivoController().addAtivo(ativo);

        } else {
            ativo.setId(idAtivo);
            result = new AtivoController().alterarAtivo(ativo);
            idAtivo = null;

        }
        if (result == 1) {
            JOptionPane.showMessageDialog(this, "Valor inserido com sucesso");
            enableFields(false);
            onCancelar();
            refreshTable();

        } else {
            JOptionPane.showMessageDialog(this, "Tente Novamente !");
        }

    }

    private void refreshTable() {
        ativoList = new AtivoController().findAtivos();
        if (ativoList != null) {
            table.setModel(new AtivoTableModel(ativoList));

        }

    }

    private void onCancelar() {
        txtTicker.setText("");
        txtAlocacao.setText("");
        txtPrecoEntrada.setText("");
        enableFields(false);
    }

    private void enableFields(boolean b){
        txtTicker.setEnabled(b);
        txtAlocacao.setEnabled(b);
        txtPrecoEntrada.setEnabled(b);
    }
}
