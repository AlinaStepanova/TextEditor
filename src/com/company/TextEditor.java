package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.util.List;

/**
 * Created by Alina on 29.03.2017.
 */
public class TextEditor implements ActionListener {

    private static final int EDIT_ROWS = 20;
    private static final int EDIT_COLS = 50;
    private static final int NUM_CHARS = 15;

    private JTextField searchField;

    private JTextArea textArea;

    TextEditor(List<String> words) {
        initUI(words);
    }

    private void initUI(List<String> words) {
        textArea = initTextArea(words);
        JScrollPane editorScroller = new JScrollPane(textArea);
        editorScroller.setBorder(BorderFactory.createTitledBorder("Поле редагування"));

        JFrame frame = new JFrame();
        Container contentPane = frame.getContentPane();
        contentPane.add(editorScroller, BorderLayout.CENTER);

        searchField = new JTextField(NUM_CHARS);
        JPanel searchPanel = initSearchPanel();
        JPanel replacePanel = initReplacePanel();

        JPanel bottomPanel = initBottomPanel(searchPanel, replacePanel);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    private JPanel initSearchPanel() {
        JCheckBox searchFromBeginningBox = new JCheckBox("Спочатку", true);
        JCheckBox searchFromCursorPositionBox = new JCheckBox("Від курсора", false);
        JPanel searchPanel = new JPanel();
        searchPanel.add(initButton("Маска"));
        searchPanel.add(searchField);
        searchField.getDocument().addDocumentListener();
        searchPanel.add(searchFromBeginningBox);
        searchPanel.add(searchFromCursorPositionBox);
        return searchPanel;
    }

    private JPanel initBottomPanel(JPanel searchPanel, JPanel replacePanel) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));
        buttonPanel.add(searchPanel);
        buttonPanel.add(replacePanel);
        return buttonPanel;
    }

    private JPanel initReplacePanel() {
        JButton replaceAllButton = initButton("Замінити все");
        JButton replaceSelectionButton = initButton("Замінити");
        JButton skipSelectionButton = initButton("Далі");
        JTextField replaceField = new JTextField(NUM_CHARS);
        JPanel replacePanel = new JPanel();

        replacePanel.add(replaceSelectionButton);
        replacePanel.add(replaceField);
        replacePanel.add(replaceAllButton);
        replacePanel.add(skipSelectionButton);
        return replacePanel;
    }

    private JButton initButton(String маска) {
        JButton searchByMaskaButton = new JButton(маска);
        searchByMaskaButton.addActionListener(this);
        return searchByMaskaButton;
    }

    private JTextArea initTextArea(List<String> words) {
        JTextArea editor = new JTextArea(EDIT_ROWS, EDIT_COLS);
        words.forEach((str) -> editor.append(str + '\n'));
        return editor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String text = textArea.getText();
        textArea.requestFocus();
        textArea.select(1, 3);
    }
}
