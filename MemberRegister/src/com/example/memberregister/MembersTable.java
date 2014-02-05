package com.example.memberregister;

import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class MembersTable extends Table {

	private static final Action DELETE_ACTION = new Action("Видалити");
	private static final Action EDIT_ACTION = new Action("Редагувати");

	public MembersTable(final SQLContainer container) {
		setContainerDataSource(container);
		setColumnReorderingAllowed(true);
		setSelectable(true);
		setFooterVisible(true);
		//getConverter("rang");
		setVisibleColumns(new String[] {"surname","name","secname","birth","is35","organ","oblast","rayon","town","category","rang","posada","vyd","study","tel","email"});
		setColumnHeaders(new String[] { "Прізвище", "Ім’я","По батьковi", "Дата нар.","Є 35","Орган вик. влади", "Область","Район","Місто(село)", "Категорія","Ранг","Посада","Вид дяльностi", "Освiта", "Телефон", "Email" });
		addActionHandler(new Handler() {			
			
			@Override
			public void handleAction(Action action, Object sender, Object target) {
				if (action == EDIT_ACTION) {
					getUI().getNavigator().navigateTo("member/" + target);
				}else if(action == DELETE_ACTION) {
					getUI().addWindow(new DeleteDialog(container,target));
				}
				
			}
			
			@Override
			public Action[] getActions(Object target, Object sender) {
				return new Action[]{ EDIT_ACTION, DELETE_ACTION};
			}
		});
		
			
			addGeneratedColumn("email", new ColumnGenerator() {
				
				@Override
				public Object generateCell(Table source, Object itemId, Object columnId) {
					String email = (String) container.getContainerProperty(itemId, columnId).getValue();
					if (email == null || email.equals("")) return null;
					 return new Link(email, new ExternalResource("mailto:" + email)) ;
				}
			});
		
	}
	
}
