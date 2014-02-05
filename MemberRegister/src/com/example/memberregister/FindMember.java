package com.example.memberregister;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.NativeSelect;

public class FindMember {
	
	private transient  NativeSelect find = null;

	private  String findstr = "lastname";
	
	
	public FindMember() {
		
		find = new NativeSelect("Поле поиска");
        
        find.addItem("firstname");
        find.setItemCaption("firstname", "Имя");
        find.addItem("lastname");
        find.setItemCaption("lastname", "Фамилия");
        find.addItem("email");
        find.setItemCaption("email", "Email");
     

	    find.setNullSelectionAllowed(false);
	    find.setValue("lastname");
	    find.setImmediate(true);
	    find.addValueChangeListener(new ValueChangeListener() {
	        

			/**
			 * 
			 */
			private static final long serialVersionUID = 1065024501632914360L;

			@Override
	        public void valueChange(final ValueChangeEvent event) {
	            findstr = String.valueOf(event.getProperty()
	                    .getValue());
	        }

	    	});
	}
	
	public NativeSelect getFind() {
		return find;
	}


	public void setFind(NativeSelect find) {
		this.find = find;
	}


	public String getFindstr() {
		return findstr;
	}


	public void setFindstr(String findstr) {
		this.findstr = findstr;
	}
}
