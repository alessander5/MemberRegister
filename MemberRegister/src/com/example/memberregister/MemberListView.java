package com.example.memberregister;

import com.vaadin.data.Container.Filterable;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MemberListView extends CustomComponent implements
		View {
	
	@SuppressWarnings("deprecation")
	public MemberListView( final SQLContainer container) {
		setSizeFull();
		VerticalLayout vlayout = new VerticalLayout();
		setCompositionRoot(vlayout);
		vlayout.setSizeFull();
		vlayout.setSpacing(true);
			
		HorizontalLayout hLayout = new HorizontalLayout();
		//final  FindMember findmember = new FindMember();
		//hLayout.addComponent(findmember.getFind());
		final TextField tf = new TextField();
		tf.focus();
		hLayout.addComponent(tf);
		hLayout.setComponentAlignment(tf, Alignment.BOTTOM_LEFT);
		hLayout.setSpacing(true);
		vlayout.addComponent(hLayout);
		
		final Table table = new MembersTable(container);
		table.setSizeFull();
		table.setColumnCollapsingAllowed(true);
		vlayout.addComponent(table);
		vlayout.setExpandRatio(table,1);
		
		/*tf.addListener(new TextChangeListener() {
			SimpleStringFilter filter = null;
		    public void textChange(final TextChangeEvent event) {
		    	Filterable f = (Filterable)
		        table.getContainerDataSource();
		        if (filter != null)
		            f.removeContainerFilter(filter);
		        
				filter = new SimpleStringFilter (findmember.getFindstr(),event.getText(),true, false);
		        f.addContainerFilter(filter);
		    }
		});	*/

			
			vlayout.addComponent(new Button("Добавить нового", new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					getUI().getNavigator().navigateTo("member/new");
				}
			}));
		

	}
	
	
	@Override
	public void enter(ViewChangeEvent event) {
		//Notification.show(event.getParameters());
	}

}
