/*
 * Copyright (c) 2012, Codename One and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Codename One designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *  
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 * 
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * Please contact Codename One through http://www.codenameone.com/ if you 
 * need additional information or have any questions.
 */
package com.codename1.demos.kitchen1;

import java.util.Hashtable;

import com.codename1.components.MultiButton;
import com.codename1.contacts.Contact;
import com.codename1.contacts.ContactsModel;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.List;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.list.ListCellRenderer;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.util.EventDispatcher;
import com.codename1.ui.util.Resources;

/**
 * 
 * @author Shai Almog
 */
public class Contacts{ // extends Demo {

	int selectedIndex;

	public int getSelectedIndex() {
		return selectedIndex;
	}

	public String getDisplayName() {
		return "Contacts";
	}

	public Image getDemoIcon(Resources res) {
		return res.getImage("Thumb.png");
	}

	// private MultiButton createRendererMultiButton() {
	// MultiButton b = new MultiButton();
	// b.setIconName("icon");
	// b.setNameLine1("fname");
	// b.setNameLine2("phone");
	// b.setUIID("Label");
	// return b;
	// }

	private MultiButton createRendererMultiButton() {
		MultiButton b = new MultiButton();
		b.setIconName("icon");
		b.setNameLine1("fname");
		b.setNameLine2("phone");
		b.setUIID("Label");
		return b;
	}

	protected ListCellRenderer createListRenderer() {
		MultiButton sel = createRendererMultiButton();
		MultiButton unsel = createRendererMultiButton();
		return new GenericListCellRenderer(sel, unsel);
	}

	// protected CellRenderer createGridRenderer() {
	// MultiButton sel = createRendererMultiButton();
	// MultiButton unsel = createRendererMultiButton();
	// sel.setIconPosition(BorderLayout.NORTH);
	// unsel.setIconPosition(BorderLayout.NORTH);
	// return new GenericListCellRenderer(sel, unsel);
	// }

	// public Container createDemo(Resources res) {
	// final Container contactsDemo = new Container(new BorderLayout());
	// final Image defaultIcon = res.getImage("Thumb.png");
	//
	// // new Model(defaultIcon); Vgainei o Shai kai oi hardcoded contacts
	// //new ContactsModel(Display.getInstance().getAllContacts(true)); LEITOURGEI ALLA OTAN ALLAZV SE GRID TA LOADING... DEN SYNAXIZOUN NA ALLAZOUN
	// ListModel m = new ContactsModel(Display.getInstance().getAllContacts(true));//new Model(defaultIcon);//new ContactsModel(Display.getInstance().getAllContacts(true));
	// final List contactsList = new List(m);
	// // GridLayout grd = new GridLayout(1, 1);
	// // grd.setAutoFit(true);
	// // final ContainerList grid = new ContainerList(m);
	// // grid.setLayout(grd);
	// contactsDemo.addComponent(BorderLayout.CENTER, contactsList);
	//
	// contactsList.setRenderer(createListRenderer());
	// // grid.setRenderer(createGridRenderer());
	//
	// // final Button asGrid = new Button("As Grid");
	// // asGrid.addActionListener(new ActionListener() {
	// // public void actionPerformed(ActionEvent ev) {
	// // if(contactsList.getParent() != null) {
	// // contactsDemo.replace(contactsList, grid, CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, true, 300));
	// // asGrid.setText("As List");
	// // }
	// // else {
	// // contactsDemo.replace(grid, contactsList, CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 300));
	// // asGrid.setText("As Grid");
	// // }
	// // }
	// // });
	//
	// // ComponentGroup gp = new ComponentGroup();
	// // gp.addComponent(asGrid);
	// // contactsDemo.addComponent(BorderLayout.SOUTH, gp);
	// return contactsDemo;
	// }

	public Container createDemo(Resources res) {
		final Container contactsDemo = new Container(new BorderLayout());
		final Image defaultIcon = res.getImage("Thumb.png");

		ListModel m = new ContactsModel(Display.getInstance().getAllContacts(true)) {
			@Override
			public Object getItemAt(int index) {
//				Object con = super.getItemAt(index);
//				System.out.println(con);
				
//				if(selectedIndex == index) {
//					 Contact contact = Display.getInstance().getContactById(""+index);
//					 System.out.println(contact);
//				}
				
				return super.getItemAt(index);
			}

			@Override
			public int getSelectedIndex() {
				// System.out.println("Selected INDEX: " + super.getSelectedIndex());
				selectedIndex = super.getSelectedIndex();
				
				return super.getSelectedIndex();
			}

		};
		final List contactsList = new List(m);
		contactsDemo.addComponent(BorderLayout.CENTER, contactsList);

		contactsList.setRenderer(createListRenderer());
		return contactsDemo;
	}

	// @Override
	// protected boolean initListModelList(List cmp) {
	//
	// String[] contactsId = ContactsManager.getAllContactsWithNumbers();
	//
	// cmp.setModel(new ContactsModel(contactsId){
	// @Override
	// public Object getItemAt(int index) {
	// ht=(Hashtable)super.getItemAt(index);
	// if(getSelectedIndex()==index){
	// ht.put("selected", (Object)"true");
	// }
	// return ht;
	// }
	// });
	// return true;
	// }

	private static final String[] FNAME = { "Shai", "Chen", "John", "Bill", "Bob", "Thomas", };
	private static final String[] SNAME = { "Almog", "Fishbein", "Doe", "Richards", "Dole", "Pain" };
	private static final String[] PHONE = { "555-4444", "555-4444", "555-4444", "555-4444", "555-4444", "555-4444" };

	static class Model implements ListModel {
		private Image defaultIcon;
		private int selection;
		private EventDispatcher events = new EventDispatcher();
		private EventDispatcher selectionEvents = new EventDispatcher();

		public Model(Image defaultIcon) {
			this.defaultIcon = defaultIcon;
		}

		public Object getItemAt(int index) {
			Hashtable h = new Hashtable();
			h.put("fname", FNAME[index] + " " + SNAME[index]);
			h.put("phone", PHONE[index]);
			h.put("icon", defaultIcon);
			// System.out.println("***** getItemAt: " + FNAME[index] + "-" + SNAME[index] + "--" + PHONE[index]);
			return h;
		}

		public int getSize() {
			return FNAME.length;
		}

		public int getSelectedIndex() {
			// System.out.println("***** INDEX: " + selection);
			return selection;
		}

		public void setSelectedIndex(int index) {
			int o = selection;
			selection = index;
			if (o != selection) {
				selectionEvents.fireSelectionEvent(selection, selection);
			}
		}

		public void addDataChangedListener(DataChangedListener l) {
			events.addListener(l);
		}

		public void removeDataChangedListener(DataChangedListener l) {
			events.removeListener(l);
		}

		public void addSelectionListener(SelectionListener l) {
			selectionEvents.addListener(l);
		}

		public void removeSelectionListener(SelectionListener l) {
			selectionEvents.removeListener(l);
		}

		public void addItem(Object item) {
		}

		public void removeItem(int index) {
		}
	}
}
