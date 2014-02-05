package com.example.memberregister;

import java.sql.SQLException;

import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.sqlcontainer.RowId;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class MemberView extends CustomComponent implements View {
	private TextField name;
	private TextField surname;
	private TextField secname;
	private TextField birth;
	private String is35;
	private TextField organ;
	private TextField oblast;
	private TextField rayon;
	private TextField town;
	private TextField posada;
	private NativeSelect category;
	private NativeSelect rang;
	private TextField study;
	private TextField tel;
	private TextField vyd;
	private TextField email;
	/*find = new NativeSelect("Поле поиска");
    
    find.addItem("firstname");
    find.setItemCaption("firstname", "Имя");
    find.addItem("lastname");
    find.setItemCaption("lastname", "Фамилия");
    find.addItem("email");
    find.setItemCaption("email", "Email");*/
	private SQLContainer container;

	private FieldGroup fieldGroup;
	
	private Object itemId = null;
	
	public MemberView(final SQLContainer container) {
		this.container=container;
		FormLayout layout = new FormLayout();
		setCompositionRoot(layout);

		surname = new TextField("Прізвище");
		name = new TextField("І’мя");
		secname = new TextField("По батькові");
		birth = new TextField("Число, місяць, рік народження");
		CheckBox check35 = new CheckBox("Є 35 років");
		organ = new TextField("Орган виконавчої влади,\n орган місцевого самоврядування");
		organ.addStyleName("multiline");
		oblast = new TextField("Область");
		rayon = new TextField("Район");
		town = new TextField("Місто(село)");
		posada = new TextField("Посада");
		category = new NativeSelect("Категорія");
		rang = new NativeSelect("Ранг");
		study = new TextField("Освіта, спеціальність (по диплому)");
		tel = new TextField("Телефон");
		vyd = new TextField("Вид навчання");
		email = new TextField("Email");
		name.setNullRepresentation("");
		surname.setNullRepresentation("");
		secname.setNullRepresentation("");
		birth.setNullRepresentation("");
		organ.setNullRepresentation("");
		oblast.setNullRepresentation("");
		rayon.setNullRepresentation("");
		town.setNullRepresentation("");
		posada.setNullRepresentation("");
		study.setNullRepresentation("");
		tel.setNullRepresentation("");
		vyd.setNullRepresentation("");
		email.setNullRepresentation("");
		email.addValidator(new EmailValidator("Ошибка записи почты"));
		layout.addComponent(surname);
		layout.addComponent(name);
		layout.addComponent(secname);
		layout.addComponent(birth);
		layout.addComponent(check35);
		layout.addComponent(organ);
		layout.addComponent(oblast);
		layout.addComponent(rayon);
		layout.addComponent(town);
		layout.addComponent(posada);
		layout.addComponent(category);
		layout.addComponent(rang);
		layout.addComponent(study);
		layout.addComponent(tel);
		layout.addComponent(vyd);
		layout.addComponent(email);
		layout.setMargin(true);
		layout.setSpacing(true);

		 check35.addValueChangeListener(new ValueChangeListener() {
	            @Override
	            public void valueChange(final ValueChangeEvent event) {
	                is35 = ((Boolean) event.getProperty().getValue()) ? "+" : "-";
	            }

	        });
		
		HorizontalLayout buttonsLayout = new HorizontalLayout();
		layout.addComponent(buttonsLayout);
		buttonsLayout.setSpacing(true);
		buttonsLayout.setMargin(true);
		buttonsLayout.addComponent(new Button("Сохранить",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					fieldGroup.commit();
					container.commit();
				} catch (CommitException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}));
		
		buttonsLayout.addComponent(new Button("Сбросить",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				fieldGroup.discard();
			}
		}));
		
		buttonsLayout.addComponent(new Button("Закрыть",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo("");
				container.removeItem(itemId);
			}
		}));
		
		
	}

	@Override
	public void enter(ViewChangeEvent event) {
		Item item = null;
		if ("new".equals(event.getParameters())) {
			//Notification.show(event.getParameters());
			itemId = container.addItem();
			item = container.getItem(itemId);
		
		}else {
			//Notification.show(event.getParameters());
			Integer id = Integer.valueOf(event.getParameters());
			item = container.getItem(new RowId(new Object[]{ id }));
		}
		fieldGroup = new FieldGroup(item);
		fieldGroup.bindMemberFields(this);
	}

}
