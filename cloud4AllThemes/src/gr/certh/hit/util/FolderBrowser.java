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

import java.io.IOException;
import java.io.InputStream;

import com.codename1.io.FileSystemStorage;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.UIBuilder;

/**
 * Form for browsing through folders on a mobile device
 * 
 * @author Augustine Gyawu Adjei
 */
public class FolderBrowser extends Form {
//	
//	FileSystemStorage fs;
//
//	private static String UP_DIRECTORY = "..";
//	// DEFAULT CHARACTERS
//	private static String ROOT_DIRECTORY = "\\";
//	private static String DIRECTORY_SEPARATOR = "\\";
//	private static char SEPARATOR_CHARACTER = '\\';
//	private String currentDirectory;
//
//	ComponentGroup gp;
//	Container con;
//	Command cmdView, cmdSelect, cmdBack;
//
//	public FolderBrowser() {
//		super("Folder Browser");
//		initializeComponents();
//	}
//
//	private void initializeComponents() {
//		this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
//		fs = FileSystemStorage.getInstance();
//
//		cmdSelect = new Command("Select");
//		cmdView = new Command("Open");
//		cmdBack = new Command("back");
//		gp = new ComponentGroup();
//		gp.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
//		con = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//
//		addCommand(cmdView);
//		addCommand(cmdSelect);
//		addCommand(cmdBack);
//		gp.addComponent(con);
//		addComponent(gp);
//		addCommandListener(new ActionEventListeners());
//
//		startListing();
//	}
//
//	/**
//	 * Starting point of the dialog. Displays the root directories on the device.
//	 */
//	private void startListing() {
//		SEPARATOR_CHARACTER = fs.getFileSystemSeparator();
//		DIRECTORY_SEPARATOR = String.valueOf(SEPARATOR_CHARACTER);
//		ROOT_DIRECTORY = DIRECTORY_SEPARATOR;
//		currentDirectory = ROOT_DIRECTORY;
//
//		displayFolders();
//	}
//
//	List folBrowser;
//
//	/**
//	 * Displays all folders under the current directory
//	 */
//	private void displayFolders() {
//
//		String[] folderItems;
//		try {
//			if (ROOT_DIRECTORY.equals(currentDirectory)) {
//				folderItems = fs.getRoots();
//				setTitle("Drives");
//				folBrowser = new List();
//
//			}
//			else {
//				folderItems = fs.listFiles(currentDirectory);
//				setTitle(currentDirectory);
//				folBrowser = new List();
//				folBrowser.getModel().addItem(UP_DIRECTORY);
//
//			}
//
//			System.out.println("folderItems.length: " + folderItems.length);
//				for (int i = 0; i < folderItems.length; i++) {
//	
//					String fileName = folderItems[i];
//					// you may customize this part to show files...
//	//				if (fs.isDirectory(currentDirectory + fileName)) {
//						folBrowser.getModel().addItem(fileName);
//	//				}
//					
//				}
//			
//			
//			con.removeAll();
//
//			con.addComponent(folBrowser);
//			folBrowser.addActionListener(new ActionEventListeners());
//			con.repaint();
//			
//			// DIKA MOU
//			revalidate();
//			show();
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//	}
//
//	/**
//	 * Browses a directory
//	 * 
//	 * @param fileName
//	 *            Must be valid and not null
//	 */
//	private void browseDirectory(String fileName) {
//		String thename = UP_DIRECTORY + DIRECTORY_SEPARATOR;
//		if (currentDirectory.equals(ROOT_DIRECTORY)) {
//			if (fileName.equals(UP_DIRECTORY)) {
//				// we don't need to go up from root
//				return;
//			}
//			currentDirectory = fileName;
//		}
//
//		else if (fileName.equals(thename)) {
//			// Move back one directory
//
//			int i = currentDirectory.lastIndexOf(SEPARATOR_CHARACTER, currentDirectory.length() - 2);
//			if (i != -1) {
//				currentDirectory = currentDirectory.substring(0, i + 1);
//			} else {
//				currentDirectory = ROOT_DIRECTORY;
//			}
//		} else {
//			currentDirectory = currentDirectory + fileName;
//		}
//		displayFolders();
//	}
//
//	/**
//	 * Class to handle action events
//	 */
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
