package com.example.memberregister;

import java.sql.SQLException;

import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class DeleteDialog extends Window {

	/**
	 * Test
	 */
	private static final long serialVersionUID = -5556764609662186643L;

	public DeleteDialog(final SQLContainer container, final Object itemId) {
		VerticalLayout layout = new VerticalLayout();
		setContent(layout);
		setModal(true);
		setResizable(false);
		layout.setMargin(true);
		setCaption("Видалення");
		
		layout.addComponent(new Label("Ви дійсно хочете видалити цього клієнта?"));
		HorizontalLayout buttonslayout = new HorizontalLayout();
		buttonslayout.setSpacing(true);
		layout.addComponent(buttonslayout);
		
		buttonslayout.addComponent(new Button("Так", new ClickListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 2837766129033881019L;

			@Override
			public void buttonClick(ClickEvent event) {
				container.removeItem(itemId);
				
				
				try {
					container.commit();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				getUI().removeWindow(DeleteDialog.this);
			}
		} ));
		buttonslayout.addComponent(new Button("Ні", new ClickListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = -3981473403010972190L;

			@Override
			public void buttonClick(ClickEvent event) {
				getUI().removeWindow(DeleteDialog.this);
				
			}
		} ));
		
	}
	
}
