package gr.certh.hit.util;

/*
 * FolderBrowser component
 * Developed by Augustine Gyawu Adjei
 * Started: 23 March 2012
 * First Public Release: 23 March 2012
 * 
 * You may use this class in your apps for both personal and commercial use.
 * Send feeback to austinejei@gmail.com or visit http://austinejei.blogspot.com
 */

import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 * Form for browsing through folders on a mobile device
 * 
 * @author Augustine Gyawu Adjei
 */
public class MyFolderBrowser {
	
	FileSystemStorage fs;

	public static String UP_DIRECTORY = "..";
	// DEFAULT CHARACTERS
	public static String ROOT_DIRECTORY = "\\";
	public static String DIRECTORY_SEPARATOR = "\\";
	public static char SEPARATOR_CHARACTER = '\\';
	public String currentDirectory;

//	ComponentGroup gp;
//	Container con;
//	Command cmdView, cmdSelect, cmdBack;
	int previousSize = -1;
	int size = 0;

	public MyFolderBrowser(Form f, Container container, List folderList) {
//		super("My Folder Browser");
		initializeComponents(f, container, folderList);
	}

	private void initializeComponents(Form f, Container container, List folderList) {
//		this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		fs = FileSystemStorage.getInstance();

//		cmdSelect = new Command("Select");
//		cmdView = new Command("Open");
//		cmdBack = new Command("back");
//		gp = new ComponentGroup();
//		gp.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
//		con = new Container(new BoxLayout(BoxLayout.Y_AXIS));

//		addCommand(cmdView);
//		addCommand(cmdSelect);
//		addCommand(cmdBack);
//		gp.addComponent(con);
//		addComponent(gp);
//		addCommandListener(new ActionEventListeners());

		startListing(f, container, folderList);
	}

	/**
	 * Starting point of the dialog. Displays the root directories on the device.
	 */
	private void startListing(Form f, Container container, List folderList) {
		SEPARATOR_CHARACTER = fs.getFileSystemSeparator();
		DIRECTORY_SEPARATOR = String.valueOf(SEPARATOR_CHARACTER);
		ROOT_DIRECTORY = DIRECTORY_SEPARATOR;
		currentDirectory = ROOT_DIRECTORY;

		displayFolders(f, container, folderList);
	}

//	List folBrowser;

	/**
	 * Displays all folders under the current directory
	 */
	private void displayFolders(Form f, Container container, List folBrowser) {

		String[] folderItems;
		try {
			if (ROOT_DIRECTORY.equals(currentDirectory)) {
				folderItems = fs.getRoots();
				System.out.println("Eimaste sto ROOT - size: " + folderItems.length); //2
				System.out.println("ROOT - currentDirectory: " + currentDirectory);
				f.setTitle("Drives");
//				folBrowser = new List();
				
//				if(previousSize != size) {
//					previousSize = size;
				
				System.out.println("A1. folBrowser: " + folBrowser);
				System.out.println("A. folBrowser.getModel().getSize(): " + folBrowser.getModel().getSize()); //0
				
				for(int i=0; i<folBrowser.getModel().getSize(); i++){
					folBrowser.getModel().removeItem(0);
//					System.out.println("i = " + i);
				}
//				}

			}
			else {
				folderItems = fs.listFiles(currentDirectory);
				System.out.println("Eimaste ALLOU - size: " + folderItems.length); //112
				System.out.println("ALLOU - currentDirectory: " + currentDirectory);
				f.setTitle(currentDirectory);
//				folBrowser = new List();
				
				size = folBrowser.getModel().getSize();
				System.out.println("B. folBrowser.getModel().getSize(): " + folBrowser.getModel().getSize());
				
				if(previousSize != size) {
					previousSize = size;
					for(int i=0; i<size; i++){
						folBrowser.getModel().removeItem(0);
//						System.out.println("i = " + i);
					}
				}
				
				System.out.println("displayFolders --> currentDirectory: " + currentDirectory);
				
//				if (!currentDirectory.endsWith(MyFolderBrowser.DIRECTORY_SEPARATOR)) {
//					for(int i=0; i<size; i++){
//						folBrowser.getModel().removeItem(0);
//						System.out.println("i = " + i);
//					}
				folBrowser.getModel().addItem(UP_DIRECTORY);
			}

			if(folderItems != null) {
			for (int i = 0; i < folderItems.length; i++) {
			String fileName = folderItems[i];
			// you may customize this part to show files...
//				if (fs.isDirectory(currentDirectory + fileName)) {
				folBrowser.getModel().addItem(fileName);
//				}
			}
		}
			
//			if(folderItems != null) {
////			System.out.println("length: " + folderItems.length);
////				for (int i = 0; i < folderItems.length; i++) {
//			System.out.println("size: " + size);
//			for (int i = 0; i < size; i++) {
//					String fileName = folderItems[i];
//					// you may customize this part to show files...
//	//				if (fs.isDirectory(currentDirectory + fileName)) {
//						folBrowser.getModel().addItem(fileName);
//	//				}
//				}
//			}

			container.removeAll();

			container.addComponent(folBrowser);
//			folBrowser.addActionListener(new ActionEventListeners()); tax
			container.repaint();
			
			// DIKA MOU
			System.out.println("FORM NAME: " + f.getName());
			f.revalidate();
			f.show();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Browses a directory
	 * 
	 * @param fileName
	 *            Must be valid and not null
	 */
	public void browseDirectory(Form f, Container container, List folBrowser, String fileName) {
		String thename = UP_DIRECTORY + DIRECTORY_SEPARATOR;
		
		System.out.println("1 browserDirectory --> currentDirectory: " + currentDirectory);
		
		if (currentDirectory.equals(ROOT_DIRECTORY)) {
			if (fileName.equals(UP_DIRECTORY)) {
				// we don't need to go up from root
				return;
			}
			currentDirectory = fileName;
		}
		else if (fileName.equals(thename)) {
			// Move back one directory

			int i = currentDirectory.lastIndexOf(SEPARATOR_CHARACTER, currentDirectory.length() - 2);
			if (i != -1) {
				currentDirectory = currentDirectory.substring(0, i + 1);
			} else {
				currentDirectory = ROOT_DIRECTORY;
			}
		} else {
			currentDirectory = currentDirectory + fileName;
		}
		
		System.out.println("2 browserDirectory --> currentDirectory: " + currentDirectory);
		
		displayFolders(f, container, folBrowser);
	}

	/**
	 * Class to handle action events
	 */
//	private class ActionEventListeners implements ActionListener {
//
//		public void actionPerformed(ActionEvent evt) {
//			String currFile = folBrowser.getSelectedItem().toString();
//			
//			System.out.println("evt.getSource(): " + evt.getSource());
//			System.out.println("folBrowser.getSelectedItem().toString(): " + folBrowser.getSelectedItem().toString());
//			
//
//			if (!currFile.endsWith(DIRECTORY_SEPARATOR)) {
//				currFile = currFile + DIRECTORY_SEPARATOR;
//			}
//			if (evt.getSource().equals(cmdSelect)) {
//				Dialog.show("Great!!", "Selected Folder: " + currentDirectory + currFile, "Ok", null);
//			} else if (evt.getSource().equals(cmdBack)) {
//				showBack();
//			}
//			else if (evt.getSource().equals(cmdView)) {
//				if (currFile.equals(UP_DIRECTORY)) { //currFile.endsWith(DIRECTORY_SEPARATOR) || 
//					browseDirectory(currFile);
//				} else {
//					// you may want to show your files here....
////			    	Form f = Display.getInstance().getCurrent();
////			    	System.out.println("FORM NAME 8: " + f.getName());
//			        if(currentDirectory.indexOf(".jpg") != -1){
//			        	
//                        InputStream is = null;
//                        Image currentImage = null;
//                        String path = null;
//                        
//                        try {
//                            path = currentDirectory; //(String) evt.getSource();
//                            System.out.println("path " + path);
//                            is = com.codename1.io.FileSystemStorage.getInstance().openInputStream(path);
//                            Image i = Image.createImage(is);
//                            currentImage = i.scaledWidth(360);
//
//                        } catch (Exception ex) {
//                            ex.printStackTrace();
//                        } finally {
//                            try {
//                                is.close();
//                            } catch (IOException ex) {
//                                ex.printStackTrace();
//                            }
//                        }
//                        
//                        PhotoView view = new PhotoView(currentImage, path);
//                        view.show();
//                        
////                        removeAll();
////                        Label photoDisplayLb = new Label();
////                        addComponent(photoDisplayLb);
////                        photoDisplayLb.setIcon(currentImage);
////                        revalidate();
//			        }
////			        Image img = null;
////			        String path;
////			        ImageActionListener aiActionListener = new ImageActionListener();
////
////			        Display.getInstance().openImageGallery(aiActionListener);
////			        path = aiActionListener.GetPath();
//
////			        try {
////			          img = Image.createImage(path);
////			          findStorageLb().setIcon(img);
////			          findStorageLb().setText("");
//			        	
//			        	System.out.println("currentDirectory: " + currentDirectory);
//			        	System.out.println("currFile: " + currFile);
//			        /*	
//			        	img = Image.createImage(currentDirectory + currFile);
//			          
//						con.removeAll();
//						Label photoLb = new Label();
//						con.addComponent(photoLb);
//						photoLb.setText("");
//						photoLb.setIcon(img);
//						con.addComponent(folBrowser);
//						
//						revalidate();
//						repaint();
//			          */
//			        	
////			          f.revalidate();
////			          System.out.println("FORM NAME 9: " + f.getName());
////			        } catch (IOException e) {
////			          // TODO Auto-generated catch block
////			          e.printStackTrace();
////			        }
//				}
//
//			} else if (evt.getSource().equals(folBrowser)) {
//				if (currFile.endsWith(DIRECTORY_SEPARATOR) || currFile.equals(UP_DIRECTORY)) {
//					browseDirectory(currFile);
//				} else {
//
//				}
//			}
//		}
//
//	}

}
