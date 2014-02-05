package com.example.memberregister;

import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("memberregistertheme")
public class MemberregisterUI extends UI {

	SQLContainer container = null;
	SQLContainer container1 = null;
	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MemberregisterUI.class)
	public static class Servlet extends VaadinServlet {
	}

	private void createContainer(){
		try {
			JDBCConnectionPool pool = new SimpleJDBCConnectionPool(
					"com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/my", "root", "qwerty");
			TableQuery tq = new TableQuery("person", pool);
			container = new SQLContainer(tq);
			
			TableQuery tqAuth = new TableQuery("auth", pool);
			container1 = new SQLContainer(tqAuth);
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	@Override
	protected void init(VaadinRequest request) {
		setSizeFull();
		
		VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setSizeFull();
		setContent(layout);


		createContainer();
		//layout.addComponent(new Header(container1).getHeader());
		
		HorizontalLayout content = new HorizontalLayout();
		content.setSizeFull();
		layout.addComponent(content);
		layout.setExpandRatio(content, 1);
		
		
		
		Navigator navigator = new Navigator(this, content);
		navigator.addView("", new MemberListView(container));
		navigator.addView("member", new MemberView(container));
		
		navigator.navigateTo("");		
		
	}



}