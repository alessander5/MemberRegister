package com.example.memberregister;

import java.sql.SQLException;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Reindeer;

public class Header {

	HorizontalLayout header;
	
	@PropertyId("username")
	private TextField username;
	
	@PropertyId("password")
	private PasswordField password;
	
	@PropertyId("ind")
	private String ind = "1";
	
	private String email = "qwerty@mail.ua";
	
	private FieldGroup fieldGroup;
	
	private Object itemId = null;
	
	public Header(final SQLContainer containerAuth) {
		
		header = new HorizontalLayout();
		header.setWidth("100%");
		
		Label headerLabel = new Label("Список клієнтів");
		headerLabel.addStyleName(Reindeer.LABEL_H1);
		header.addComponent(headerLabel);
		header.setComponentAlignment(headerLabel, Alignment.BOTTOM_LEFT);
		
		HorizontalLayout log = new HorizontalLayout();
		header.addComponent(log);
		header.setComponentAlignment(log, Alignment.BOTTOM_RIGHT);
		log.setSpacing(true);
		
		
		username = new TextField("Логін");
		log.addComponent(username);	
		password = new PasswordField("Пароль");
		log.addComponent(password);

		
		
		Button butEnter = new Button("Войти",new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				//Integer id = Integer.valueOf(((Object) event).getParameters());
				//item = containerAuth.getItem(new RowId(new Object[]{ id }));
			}
		});

		log.addComponent(butEnter);
		log.setComponentAlignment(butEnter, Alignment.BOTTOM_RIGHT);
		
		Button butLogin = new Button("Зарегистрироваться",new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				itemId = containerAuth.addItem();
				Item item = containerAuth.getItem(itemId);
				fieldGroup = new FieldGroup(item);
				fieldGroup.bindMemberFields(this);
				
				
				/*PropertysetItem item = new PropertysetItem();
				item.addItemProperty("username", new ObjectProperty("Zaphod"));
				item.addItemProperty("password", new ObjectProperty(42));
				item.addItemProperty("ind", new ObjectProperty(1));
				item.addItemProperty("email", new ObjectProperty("qwerty"));
				
				itemId = containerAuth.addItem();
				Item item1 = containerAuth.getItem(itemId);

				fieldGroup = new FieldGroup(item);
				fieldGroup.bindMemberFields(item);*/
				
				try {
					fieldGroup.commit();
					containerAuth.commit();
				} catch (CommitException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});

		log.addComponent(butLogin);
		log.setComponentAlignment(butLogin, Alignment.BOTTOM_RIGHT);
		
	
	}
	
	public HorizontalLayout getHeader() {
		return header;
	}

	public void setHeader(HorizontalLayout header) {
		this.header = header;
	}
	
	
}
