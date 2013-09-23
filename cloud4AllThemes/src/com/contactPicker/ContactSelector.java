/*
 * Copyright (c) 2010, 2011 Itiner.pl. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Itiner designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Itiner in the LICENSE.txt file that accompanied this code.
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
 */
package com.contactPicker;

import java.util.ArrayList;
import java.util.Hashtable;

import userclasses.StateMachine;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.contacts.ContactsManager;
import com.codename1.contacts.ContactsModel;
import com.codename1.ui.Button;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.list.CellRenderer;
import com.codename1.ui.list.ContainerList;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.list.ListCellRenderer;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 * 
 * @author Ayoola Ajebeku
 */
public class ContactSelector extends Container implements ActionListener {

	private List contactList;
	private ContactsModel conModel;
	/*
	 * Hashtable is used for the contacts so that we can easily update which contact is selected or removed from the ContactModel because the contact model return a hashtable
	 * containing so many things including the contact object itself - which cannot just strip out because we will still need the hashtable to know which of the contacts in the
	 * hashtable was removed.
	 */
	private Hashtable tempContacts = new Hashtable();
	private Hashtable contacts = new Hashtable();
	private Button cancelButton;
	private Button doneButton;
	private ArrayList<ContactListener> listeners; // final (original)

	private Resources theResources;
	private String theTheme, theLanguage, theFont;

	private StateMachine theMachine;

	// List contactsList;
	ContainerList contactsList;
	ContainerList grid;

	// boolean isContactsGrid;

	public ContactSelector(StateMachine machine) {
		theMachine = machine;
		listeners = new ArrayList<ContactListener>();
		initializeDesign();
	}

	public void setStateMachine(StateMachine machine) {
		theMachine = machine;
	}

	public ContactSelector(Resources r, StateMachine machine, String theme, String language, String font) {
		theMachine = machine;
		theResources = r;
		theTheme = theme;
		theLanguage = language;
		theFont = font;

		listeners = new ArrayList<ContactListener>();
		initializeDesign();
	}

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

	protected CellRenderer createListRenderer2() {
		MultiButton sel = createRendererMultiButton();
		MultiButton unsel = createRendererMultiButton();
		return new GenericListCellRenderer(sel, unsel);
	}

	protected CellRenderer createGridRenderer() {
		MultiButton sel = createRendererMultiButton();
		MultiButton unsel = createRendererMultiButton();
		sel.setIconPosition(BorderLayout.NORTH);
		unsel.setIconPosition(BorderLayout.NORTH);
		return new GenericListCellRenderer(sel, unsel);
	}

	private void initializeDesign() {
		this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

		final BorderLayout absoluteCenteredLayout = new BorderLayout();
		absoluteCenteredLayout.setCenterBehavior(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE);

		createListCellRendererModel();
		Container myContainer = createContactsContainer();
		this.addComponent(myContainer);
	}

	public Container createContactsContainer() {
		final Container contactsDemo = new Container(new BorderLayout());
		final Image defaultIcon = theResources.getImage("Arrow.png");

		ListModel m = conModel;

		contactsList = new ContainerList(m);

		GridLayout grd = new GridLayout(2, 2);
		grd.setAutoFit(false);
		grid = new ContainerList(m);
		grid.setLayout(grd);

		BoxLayout box = new BoxLayout(BoxLayout.Y_AXIS);
		contactsList.setLayout(box);

		contactsList.setRenderer(createListRenderer2());
		grid.setRenderer(createGridRenderer());

		contactsList.addActionListener(this);
		grid.addActionListener(this);

		if (!StateMachine.isGrid) {
			contactsDemo.addComponent(BorderLayout.CENTER, contactsList);
		} else {
			contactsDemo.addComponent(BorderLayout.CENTER, grid);
		}

		final Button asGridOrAsListBtn = new Button("1");

		if (!StateMachine.isGrid) {
			asGridOrAsListBtn.setText("as Grid");
		} else {
			asGridOrAsListBtn.setText("as List");
		}

		asGridOrAsListBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if (contactsList.getParent() != null) {
					contactsDemo.replace(contactsList, grid, CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, true, 300));
					asGridOrAsListBtn.setText("as List");
					StateMachine.isGrid = true;
				} else {
					contactsDemo.replace(grid, contactsList, CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 300));
					asGridOrAsListBtn.setText("as Grid");
					StateMachine.isGrid = false;
				}
			}
		});

		contactsDemo.addComponent(BorderLayout.NORTH, asGridOrAsListBtn);
		return contactsDemo;
	}

//	private Container createRendererContainer() {
//		Container c = new Container(new BorderLayout());
//		c.setUIID("ListRenderer");
//
//		Label displayName = new Label();
//		displayName.setFocusable(true);
//		final String name = isDisplayNameEmpty() ? "fname" : "lname";
//		displayName.setName(name);
//		c.addComponent(BorderLayout.CENTER, displayName);
//
//		return c;
//	}

	/*
	 * Check whether the contact display name is empty or not. It can be used to set a name the label field to show, either displayName or fname
	 */
//	private boolean isDisplayNameEmpty() {
////		 String[] conts = ContactsManager.getAllContactsWithNumbers();
//		 if (ContactsManager.getContactById(conts[0]).getDisplayName().equals("")) {
//		 return true;
//		 } else {
//		return false;
//		 }
//	}

	private ContactsModel createListCellRendererModel() {
		conModel = new ContactsModel(Display.getInstance().getAllContacts(true));//new ContactsModel(ContactsManager.getAllContacts());
		return conModel;
	}

	public Hashtable getSelectedContacts() {
		return contacts;
	}

	public void addListener(ContactListener toAdd) {
		listeners.add(toAdd);
	}

	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource().equals(cancelButton)) {
			tempContacts = new Hashtable();
			cancelContactSelection();
		} else if (evt.getSource().equals(doneButton)) {
			contacts = tempContacts;
			doneContactSelection();
		} else if (evt.getSource().equals(contactsList)) { // (contactList)) { APO EDW KAI KATW!
			final int index = contactsList.getSelectedIndex();
			Hashtable h = (Hashtable) conModel.getItemAt(contactsList.getSelectedIndex());
			final String phoneNumber, firstName, lastName, displayName;

			int newIndex = index + 1; // WORKS FOR DEVICE
			// int newIndex = Math.abs((index+1)-contactList.size())+1; // WORKS FOR SIMULATOR

			try {
				// Set language
				Hashtable table = theResources.getL10N("cloud4AllThemes", theLanguage);
				UIManager.getInstance().setResourceBundle(table);

				// Set font
				if (theFont.equals("huge")) {
					UIManager.getInstance().addThemeProps(theResources.getTheme("0"));
					System.out.println("--> 0");
				} else if (theFont.equals("veryLarge")) {
					UIManager.getInstance().addThemeProps(theResources.getTheme("1"));
					System.out.println("--> 1");
				} else if (theFont.equals("large")) {
					UIManager.getInstance().addThemeProps(theResources.getTheme("2"));
					System.out.println("--> 2");
				} else if (theFont.equals("medium")) {
					UIManager.getInstance().addThemeProps(theResources.getTheme("3"));
					System.out.println("--> 3");
				} else if (theFont.equals("small")) {
					UIManager.getInstance().addThemeProps(theResources.getTheme("4"));
					System.out.println("--> 4");
				}

				System.out.println("THE MACHINE: " + theMachine);

				// Set selectedFont
				theMachine.setSelectedFont(theFont);

				// Set selectedLanguage
				theMachine.setSelectedLanguage(theLanguage);

				// Set selectedTheme
				theMachine.setSelectedTheme(theTheme);

				// Set theme
				UIManager.getInstance().setThemeProps(theResources.getTheme(theTheme));
				theMachine.getFontFromName(theFont);
				Display.getInstance().getCurrent().refreshTheme();

				theMachine.setDisplayContact(Display.getInstance().getContactById("" + newIndex));
				theMachine.showForm("Contact Form", null);
			} catch (NullPointerException n) {
				n.printStackTrace();
			}
		} else if (evt.getSource().equals(grid)) {
			final int index = grid.getSelectedIndex();
			Hashtable h = (Hashtable) conModel.getItemAt(grid.getSelectedIndex());
			final String phoneNumber, firstName, lastName, displayName;

			int newIndex = index + 1; // WORKS FOR DEVICE
			// int newIndex = Math.abs((index+1)-contactList.size())+1; // WORKS FOR SIMULATOR

			try {
				// Set language
				Hashtable table = theResources.getL10N("cloud4AllThemes", theLanguage);
				UIManager.getInstance().setResourceBundle(table);

				// Set font
				if (theFont.equals("huge")) {
					UIManager.getInstance().addThemeProps(theResources.getTheme("0"));
					System.out.println("--> 0");
				} else if (theFont.equals("veryLarge")) {
					UIManager.getInstance().addThemeProps(theResources.getTheme("1"));
					System.out.println("--> 1");
				} else if (theFont.equals("large")) {
					UIManager.getInstance().addThemeProps(theResources.getTheme("2"));
					System.out.println("--> 2");
				} else if (theFont.equals("medium")) {
					UIManager.getInstance().addThemeProps(theResources.getTheme("3"));
					System.out.println("--> 3");
				} else if (theFont.equals("small")) {
					UIManager.getInstance().addThemeProps(theResources.getTheme("4"));
					System.out.println("--> 4");
				}

				System.out.println("THE MACHINE: " + theMachine);

				// Set selectedFont
				theMachine.setSelectedFont(theFont);

				// Set selectedLanguage
				theMachine.setSelectedLanguage(theLanguage);

				// Set selectedTheme
				theMachine.setSelectedTheme(theTheme);

				// Set theme
				UIManager.getInstance().setThemeProps(theResources.getTheme(theTheme));
				theMachine.getFontFromName(theFont);
				Display.getInstance().getCurrent().refreshTheme();

				theMachine.setDisplayContact(Display.getInstance().getContactById("" + newIndex));
				theMachine.showForm("Contact Form", null);
			} catch (NullPointerException n) {
				n.printStackTrace();
			}
		}
	}

	public void doneContactSelection() {
		// Notify everybody that may be interested.
		for (ContactListener hl : listeners) {
			hl.doneContactSelection(contacts);
		}
	}

	public void cancelContactSelection() {
		// Notify everybody that may be interested.
		for (ContactListener hl : listeners) {
			hl.cancelContactSelection();
		}
	}
}
