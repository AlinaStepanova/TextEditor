package com.company;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

/**
 * Created by Alina on 01.04.2017.
 */
public class EditTextListener implements DocumentListener {

    public interface TextChangeListener {
        void onTextChange(String text);
    }

    private final TextChangeListener onTextChangeListener;

    public EditTextListener(final TextChangeListener oTextChangeListener) {
        this.onTextChangeListener = oTextChangeListener;
    }

    @Override
    public void insertUpdate(DocumentEvent documentEvent) {
        updateEvent(documentEvent);
    }

    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        updateEvent(documentEvent);
    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
        updateEvent(documentEvent);
    }

    private void updateEvent(DocumentEvent documentEvent) {
        try {
            onTextChangeListener.onTextChange(documentEvent.getDocument()
                    .getText(0, documentEvent.getDocument().getLength()));
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        }
    }
}
