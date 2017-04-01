package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

/**
 * Created by Alina on 29.03.2017.
 */
public class TextEditor implements ActionListener, EditTextListener.TextChangeListener {

    private static final int EDIT_ROWS = 20;

    private static final int EDIT_COLS = 50;

    private static final int NUM_CHARS = 15;

    private JTextField searchField;

    private JTextArea textArea;

    private JButton maska;

    private Lab lab;

    TextEditor(List<String> words) {
        initUI(words);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CoordinatesForReplacing coordinatesForReplacing = new CoordinatesForReplacing();
        JButton source = (JButton) e.getSource();
        if (source.equals(maska)) {
            onMaskaClick();
        }

    }

    private List<CoordinatesForReplacing> onMaskaClick() {
        String allText = textArea.getText();
        Set<String> start =
                lab.filter(Arrays.asList(allText.split("\n")), searchField.getText());
        List<CoordinatesForReplacing> coordinatesForReplacings = new ArrayList<>();

        textArea.requestFocus();
        start.forEach(word -> {
            for (int i = 0; i < allText.length(); ) {
                int startIndex = allText.indexOf(word, i);
                if (startIndex == -1) {
                    break;
                }
                int endIndex = startIndex + word.length();
                selectText(startIndex, endIndex);
                coordinatesForReplacings.add(new CoordinatesForReplacing(word, startIndex,
                        endIndex));
                i += endIndex;
            }
        });
        return coordinatesForReplacings;
    }

    private void selectText(final int startIndex, final int endIndex) {
        Highlighter highlighter = textArea.getHighlighter();
        Highlighter.HighlightPainter painter =
                new DefaultHighlighter.DefaultHighlightPainter(Color.pink);
        try {
            highlighter.addHighlight(startIndex, endIndex, painter);
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onTextChange(final String text) {

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
        maska = initButton("Маска");
        searchPanel.add(maska);
        searchPanel.add(searchField);

        searchField.getDocument().addDocumentListener(new EditTextListener(this));
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
}
