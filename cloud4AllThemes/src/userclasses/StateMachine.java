/**
 * Your application code goes here
 */

package userclasses;

import generated.StateMachineBase;
import gr.certh.hit.app.MyPhotos;
import gr.certh.hit.app.map.GeocodingPoi;
import gr.certh.hit.app.map.GeocodingService;
import gr.certh.hit.app.map.ProximityService;
import gr.certh.hit.flowManager.FlowManagerService;
import gr.certh.hit.util.MyFolderBrowser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Vector;

import com.codename1.capture.Capture;
import com.codename1.codescan.CodeScanner;
import com.codename1.codescan.ScanResult;
import com.codename1.components.FileTree;
import com.codename1.components.FileTreeModel;
import com.codename1.components.InfiniteProgress;
import com.codename1.contacts.Contact;
import com.codename1.contacts.ContactsManager;
import com.codename1.contacts.ContactsModel;
import com.codename1.demos.kitchen1.Contacts;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.location.Location;
import com.codename1.location.LocationListener;
import com.codename1.location.LocationManager;
import com.codename1.maps.Coord;
import com.codename1.maps.MapComponent;
import com.codename1.maps.layers.PointLayer;
import com.codename1.maps.layers.PointsLayer;
import com.codename1.maps.providers.GoogleMapsProvider;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.RadioButton;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UITimer;

/**
 * 
 * @author Tax
 */
public class StateMachine extends StateMachineBase {

	private Resources r;
	private String selectedTheme, selectedFont, selectedLanguage;

	private String flag;
	private String text;

	private String[] phoneContacts;
	private String text2;
	Media media;
	Image currentImage;
	Image fileBrowserViewImage;
	EncodedImage currentImage2;

	String photoPath, audioPath;
	Contact displayContact;

	MyFolderBrowser browser;
	boolean firstTime = true;
	String currFile;

	Image i;
	InputStream isGlobal;

	MyPhotos photos;

	// MediaPlayer mp2 = new MediaPlayer();
	Media videoMedia;
	Media audioMedia;

	String url = "";
	DefaultListModel m;

	int volume;
	boolean isInitialised;
	Media volumeMedia;

	public static boolean isGrid;

	LocationManager manager;
	MapComponent mc;

	Coord lastLocation;
	Location globalLocation;
	LocationManager l;
	String manualPostalAddress;

	Label latitudeLb, longitudeLb;
	String latitudeStr, longitudeStr;

	boolean timerIsRunning, offVibration, shortVibration, longVibration;
	
	int vibrationTime, vibrationMode;
	
	// DEBUG variables
	String homePathDebug, outputDebug, photoNameDebug, audioNameDebug;
	char sepDebug;
	int stepDebug;
	
//	String dummyJsonResponse = "{\"info.cloud4all.JME\": {\"theme\": \"Yellow-Black\", \"fontSize\": \"veryLarge\", \"language\": \"English\", \"volume\": 50, \"vibrationMode\": 2}";
//	String dummyJsonResponse2 = "{\"info.cloud4all.JME\": {\"fontSize\": \"large\", \"language\": \"Greek\", \"volume\": 0, \"vibrationMode\": 1}";
//	String dummyJsonResponse3 = "{\"info.cloud4all.JME\": {\"language\": \"English\", \"volume\": 0, \"vibrationMode\": 1}";
	private Hashtable ht;
	private boolean isError;

	public StateMachine(String resFile) {
		super(resFile);
		// do not modify, write code in initVars and initialize class members there,
		// the constructor might be invoked too late due to race conditions that might occur

		// APO EDW KAI KATW METAFORA STO initVars()
		try {
			r = Resources.open(resFile + ".res");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Unable to open resource file!");
		}

//		setDefaultValues();
		
		photos = new MyPhotos();
		volume = 100;
		isInitialised = false;
		timerIsRunning = false;
		
		offVibration = false;
		shortVibration = true;
		longVibration = false;
		
		isError = false;

		// initVolumeAudio();
	}
	
	public Hashtable getJsonResponseHashtable(String ip, String username) {
		FlowManagerService fms = new FlowManagerService();
		
		if(ip.equalsIgnoreCase("")){
			ip = "160.40.60.183";
		}
		
		InputStream is = fms.requestNeedsAndPreferences2(ip, username);
		try {
			ht = fms.parseJsonNeedsAndPreferencesResponse(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ht;
	}
	
	// Set default values
	private void setDefaultValues(){
    	selectedTheme = "mitsosWhite";
    	selectedFont = "large";
    	selectedLanguage = "en";
    	volume = 100;
    	vibrationMode = 1;

		Hashtable table = r.getL10N("cloud4AllThemes", selectedLanguage);
		UIManager.getInstance().setResourceBundle(table);

		UIManager.getInstance().setThemeProps(r.getTheme(selectedTheme));

		UIManager.getInstance().addThemeProps(r.getTheme("2"));
		Display.getInstance().getCurrent().refreshTheme();
	}
	
//	private Hashtable getResponseHashtable() {
//		return ht;
//	}

	public void initVolumeAudio() {
		InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/volumeSound.wav");
		try {
			volumeMedia = MediaManager.createMedia(is, "audio/wav", null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getSelectedLanguage() {
		return selectedLanguage;
	}

	public void setSelectedLanguage(String selectedLanguage) {
		this.selectedLanguage = selectedLanguage;
	}

	public void setSelectedTheme(String selectedTheme) {
		this.selectedTheme = selectedTheme;
	}

	public void setSelectedFont(String selectedFont) {
		this.selectedFont = selectedFont;
	}

	/**
	 * this method should be used to initialize variables instead of the constructor/class scope to avoid race conditions
	 */
	protected void initVars() { // TODO: TO BE COMMENTED IN

		// selectedTheme = "mitsosWhite";
		// selectedFont = "medium";
		// selectedLanguage = "en";

		// UIManager.getInstance().setThemeProps(r.getTheme("mitsosNative"));
		// selectedTheme = "Initial";
		// UIManager.getInstance().setThemeProps(r.getTheme("Initial"));
		// Display.getInstance().getCurrent().refreshTheme();

		Util.register("MyPhotos", MyPhotos.class); // Used for storing photos
	}

	protected boolean onMainExit() {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		boolean val = super.onMainExit();
		Display.getInstance().vibrate(vibrationTime);
		return val;
	}

	protected void onSettings_BlackRadioButtonAction(Component c, ActionEvent event) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.onSettings_BlackRadioButtonAction(c, event);

		Display.getInstance().vibrate(vibrationTime);
		UIManager.getInstance().setThemeProps(r.getTheme("mitsosBlack"));
		selectedTheme = "mitsosBlack";

		findVolumeSlider().setThumbImage(UIManager.getInstance().getThemeImageConstant("volumeImage"));

		if (selectedFont.equals("huge")) {
			UIManager.getInstance().addThemeProps(r.getTheme("0"));
			System.out.println("--> 0");
		} else if (selectedFont.equals("veryLarge")) {
			UIManager.getInstance().addThemeProps(r.getTheme("1"));
			System.out.println("--> 1");
		} else if (selectedFont.equals("large")) {
			UIManager.getInstance().addThemeProps(r.getTheme("2"));
			System.out.println("--> 2");
		} else if (selectedFont.equals("medium")) {
			UIManager.getInstance().addThemeProps(r.getTheme("3"));
			System.out.println("--> 3");
		} else if (selectedFont.equals("small")) {
			UIManager.getInstance().addThemeProps(r.getTheme("4"));
			System.out.println("--> 4");
		}

		Display.getInstance().getCurrent().refreshTheme();
	}

	protected boolean allowBackTo(String formName) {
		return false;
	}

	protected void onSettings_WhiteRadioButtonAction(Component c, ActionEvent event) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.onSettings_WhiteRadioButtonAction(c, event);

		Display.getInstance().vibrate(vibrationTime);
		UIManager.getInstance().setThemeProps(r.getTheme("mitsosWhite"));
		selectedTheme = "mitsosWhite";

		findVolumeSlider().setThumbImage(UIManager.getInstance().getThemeImageConstant("volumeImage"));

		if (selectedFont.equals("huge")) {
			UIManager.getInstance().addThemeProps(r.getTheme("0"));
			System.out.println("--> 0");
		} else if (selectedFont.equals("veryLarge")) {
			UIManager.getInstance().addThemeProps(r.getTheme("1"));
			System.out.println("--> 1");
		} else if (selectedFont.equals("large")) {
			UIManager.getInstance().addThemeProps(r.getTheme("2"));
			System.out.println("--> 2");
		} else if (selectedFont.equals("medium")) {
			UIManager.getInstance().addThemeProps(r.getTheme("3"));
			System.out.println("--> 3");
		} else if (selectedFont.equals("small")) {
			UIManager.getInstance().addThemeProps(r.getTheme("4"));
			System.out.println("--> 4");
		}

		Display.getInstance().getCurrent().refreshTheme();
	}

	protected void onSettings_YellowRadioButtonAction(Component c, ActionEvent event) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.onSettings_YellowRadioButtonAction(c, event);

		Display.getInstance().vibrate(vibrationTime);
		UIManager.getInstance().setThemeProps(r.getTheme("mitsosYellow"));
		selectedTheme = "mitsosYellow";

		findVolumeSlider().setThumbImage(UIManager.getInstance().getThemeImageConstant("volumeImage"));

		if (selectedFont.equals("huge")) {
			UIManager.getInstance().addThemeProps(r.getTheme("0"));
			System.out.println("--> 0");
		} else if (selectedFont.equals("veryLarge")) {
			UIManager.getInstance().addThemeProps(r.getTheme("1"));
			System.out.println("--> 1");
		} else if (selectedFont.equals("large")) {
			UIManager.getInstance().addThemeProps(r.getTheme("2"));
			System.out.println("--> 2");
		} else if (selectedFont.equals("medium")) {
			UIManager.getInstance().addThemeProps(r.getTheme("3"));
			System.out.println("--> 3");
		} else if (selectedFont.equals("small")) {
			UIManager.getInstance().addThemeProps(r.getTheme("4"));
			System.out.println("--> 4");
		}

		Display.getInstance().getCurrent().refreshTheme();
	}

	protected void onSettings_LeatherRadioButtonAction(Component c, ActionEvent event) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.onSettings_LeatherRadioButtonAction(c, event);

		Display.getInstance().vibrate(vibrationTime);
		UIManager.getInstance().setThemeProps(r.getTheme("Leather"));
		selectedTheme = "Leather";

		findVolumeSlider().setThumbImage(UIManager.getInstance().getThemeImageConstant("volumeImage"));

		// findBlackRadioButton().setSelected(false);
		// findWhiteRadioButton().setSelected(false);
		// findYellowRadioButton().setSelected(false);

		if (selectedFont.equals("huge")) {
			UIManager.getInstance().addThemeProps(r.getTheme("0"));
			System.out.println("--> 0");
		} else if (selectedFont.equals("veryLarge")) {
			UIManager.getInstance().addThemeProps(r.getTheme("1"));
			System.out.println("--> 1");
		} else if (selectedFont.equals("large")) {
			UIManager.getInstance().addThemeProps(r.getTheme("2"));
			System.out.println("--> 2");
		} else if (selectedFont.equals("medium")) {
			UIManager.getInstance().addThemeProps(r.getTheme("3"));
			System.out.println("--> 3");
		} else if (selectedFont.equals("small")) {
			UIManager.getInstance().addThemeProps(r.getTheme("4"));
			System.out.println("--> 4");
		}

		Display.getInstance().getCurrent().refreshTheme();
	}

	public String getSelectedFont() {
		return selectedFont;
	}

	public String getSelectedTheme() {
		return selectedTheme;
	}

	public Resources getResources() {
		return r;
	}

	protected boolean onMainSettings() {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		boolean val = super.onMainSettings();
		Display.getInstance().vibrate(vibrationTime);
		return val;
	}

	private void setAppropriateRadioButtonsSelected(final Form f) {

		// Initially sound is set to 100%
		findVolumeSlider().setThumbImage(UIManager.getInstance().getThemeImageConstant("volumeImage"));

		final UITimer timer = new UITimer(new Runnable() {
			public void run() {
				// timerIsRunning = true;
				timerIsRunning = false;

				initVolumeAudio();
				volumeMedia.setVolume(volume);
				volumeMedia.play();
			}
		});

		// Theme
		System.out.println("Display.getInstance().getCurrent().getName(): " + Display.getInstance().getCurrent().getName());
		System.out.println("UIManager.getInstance().getThemeName(): " + UIManager.getInstance().getThemeName());

		if (UIManager.getInstance().getThemeName().equals("mitsosBlack")) { // mitsosBlack
			findBlackRadioButton().setSelected(true);
			findBlackRadioButton().requestFocus();
			// findBlackRadioButton().setFocus(true);
			System.out.println("mitsosBlack");
			selectedTheme = "mitsosBlack";
		} else if (UIManager.getInstance().getThemeName().equals("mitsosWhite")) { // mitsosWhite
			findWhiteRadioButton().setSelected(true);
			findWhiteRadioButton().requestFocus();
			// findWhiteRadioButton().setFocus(true);
			System.out.println("mitsosWhite");
			selectedTheme = "mitsosWhite";
		} else if (UIManager.getInstance().getThemeName().equals("mitsosYellow")) { // mitsosYellow
			findYellowRadioButton().setSelected(true);
			findYellowRadioButton().requestFocus();
			// findYellowRadioButton().setFocus(true);
			System.out.println("mitsosYellow");
			selectedTheme = "mitsosYellow";
		} else if (UIManager.getInstance().getThemeName().equals("Leather")) { // Leather
			findBlackRadioButton().setSelected(false);
			findWhiteRadioButton().setSelected(false);
			findYellowRadioButton().setSelected(false);
			findLeatherRadioButton().setSelected(true);
			findLeatherRadioButton().requestFocus();
			// findLeatherRadioButton().setFocus(true);
			System.out.println("Leather");
			selectedTheme = "Leather";
		} else {
			if (selectedTheme.equals("mitsosBlack")) {
				findBlackRadioButton().setSelected(true);
				findBlackRadioButton().requestFocus();
				// findBlackRadioButton().setFocus(true);
			} else if (selectedTheme.equals("mitsosWhite")) {
				findWhiteRadioButton().setSelected(true);
				findWhiteRadioButton().requestFocus();
				// findWhiteRadioButton().setFocus(true);
			} else if (selectedTheme.equals("mitsosYellow")) {
				findYellowRadioButton().setSelected(true);
				findYellowRadioButton().requestFocus();
				// findYellowRadioButton().setFocus(true);
			} else if (selectedTheme.equals("Leather")) {
				findLeatherRadioButton().setSelected(true);
				findLeatherRadioButton().requestFocus();
				// findLeatherRadioButton().setFocus(true);
			}
		}

		// Font
		System.out.println("FONT: " + UIManager.getInstance().getComponentStyle("Label").getFont().getHeight());

		if (UIManager.getInstance().getComponentStyle("Label").getFont().getHeight() == 40) {
			findHugeRadioButton().setSelected(true);
		} else if (UIManager.getInstance().getComponentStyle("Label").getFont().getHeight() == 37) {
			findVeryLargeRadioButton().setSelected(true);
		} else if (UIManager.getInstance().getComponentStyle("Label").getFont().getHeight() == 33) {
			findLargeRadioButton().setSelected(true);
		} else if (UIManager.getInstance().getComponentStyle("Label").getFont().getHeight() == 28) {
			findMediumRadioButton().setSelected(true);
		} else if (UIManager.getInstance().getComponentStyle("Label").getFont().getHeight() == 26) {
			findSmallRadioButton().setSelected(true);
		}

		System.out.println("BLACK: " + findBlackRadioButton().isSelected());
		System.out.println("WHITE: " + findWhiteRadioButton().isSelected());
		System.out.println("YELLOW: " + findYellowRadioButton().isSelected());
		System.out.println("LEATHER: " + findLeatherRadioButton().isSelected());

		System.out.println("Language: " + selectedLanguage);

		if (selectedLanguage.equals("en")) {
			findEnglishRadioButton().setSelected(true);
		} else if (selectedLanguage.equals("gr")) {
			findGreekRadioButton().setSelected(true);
		} else if (selectedLanguage.equals("es")) {
			findSpanishRadioButton().setSelected(true);
		}

		findBlackRadioButton().setFocus(false);
		findWhiteRadioButton().setFocus(false);
		findYellowRadioButton().setFocus(false);
		findLeatherRadioButton().setFocus(false);

		// if (!isInitialised) {
		// findVolumeSlider().addDataChangedListener(new DataChangedListener() {
		// public void dataChanged(int type, int index) {
		// System.out.println("Volume Changed!");
		// System.out.println("type: " + type);
		// System.out.println("index: " + index);
		// volume = index;
		// findSoundLabel().setText("" + volume);
		//
		// if (timerIsRunning) {
		// timer.cancel();
		// }
		//
		// timer.schedule(1500, false, f);
		// timerIsRunning = true;
		// }
		// });
		//
		// findSoundYesRadioButton().setSelected(true);
		// findVolumeSlider().setVisible(true);
		// findVolumeSlider().setProgress(volume);
		// isInitialised = true;
		// } else {
		// if (findSoundYesRadioButton().isSelected()) {
		// findVolumeSlider().setProgress(volume);
		// } else {
		// findVolumeSlider().setProgress(volume);
		// findVolumeSlider().setVisible(false);
		// }
		// }

		if (findVolumeSlider() != null) {
			if (findVolumeSlider().isVisible()) {

				findVolumeSlider().addDataChangedListener(new DataChangedListener() {
					public void dataChanged(int type, int index) {
						System.out.println("Volume Changed!");
						System.out.println("type: " + type);
						System.out.println("index: " + index);
						volume = index;
						findSoundLabel().setText("" + volume);

						if (timerIsRunning) {
							timer.cancel();
						}

						timer.schedule(1500, false, f);
						timerIsRunning = true;
					}
				});

				findSoundYesRadioButton().setSelected(true);

				findVolumeSlider().setVisible(true);
				findVolumeSlider().setProgress(volume);
				// findVolumeSlider().getComponentForm().revalidate();
			}
		}
		
		setVibrationLevels();
		
		String testLanguage = "";
		String testLocale = ""; 
		
		// Getting system's properties
//		String defaultLocale = System.getProperty("microedition.locale");
		if(Display.getInstance() != null){
			testLanguage = Display.getInstance().getLocalizationManager().getLanguage();
			testLocale = Display.getInstance().getLocalizationManager().getLocale();
		}
		
		findTest1Label().setText("properties");
		findTest2Label().setText("language: " + testLanguage);
		findTest3Label().setText("locale: " + testLocale);

		Display.getInstance().getCurrent().refreshTheme();
	}
	
	private void setVibrationLevels() {
		if (offVibration) {
			findOffVibrationButton().setSelected(true);
			offVibration = true;
			shortVibration = false;
			longVibration = false;
			vibrationTime = 0;
		} else if(shortVibration) {
			findShortVibrationButton().setSelected(true);
			offVibration = false;
			shortVibration = true;
			longVibration = false;
			vibrationTime = 1000;
		} else if(longVibration){
			findLongVibrationButton().setSelected(true);
			offVibration = false;
			shortVibration = false;
			longVibration = true;
			vibrationTime = 2000;
		}
	}

	protected void onSettings_SmallRadioButtonAction(Component c, ActionEvent event) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.onSettings_SmallRadioButtonAction(c, event);

		Display.getInstance().vibrate(vibrationTime);
		UIManager.getInstance().addThemeProps(r.getTheme("4"));
		selectedFont = "small";
		Display.getInstance().getCurrent().refreshTheme();
	}

	protected void onSettings_MediumRadioButtonAction(Component c, ActionEvent event) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.onSettings_MediumRadioButtonAction(c, event);

		Display.getInstance().vibrate(vibrationTime);
		UIManager.getInstance().addThemeProps(r.getTheme("3"));
		selectedFont = "medium";
		Display.getInstance().getCurrent().refreshTheme();
	}

	protected void onSettings_LargeRadioButtonAction(Component c, ActionEvent event) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.onSettings_LargeRadioButtonAction(c, event);

		Display.getInstance().vibrate(vibrationTime);
		UIManager.getInstance().addThemeProps(r.getTheme("2"));
		selectedFont = "large";
		Display.getInstance().getCurrent().refreshTheme();
	}

	protected void onSettings_VeryLargeRadioButtonAction(Component c, ActionEvent event) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.onSettings_VeryLargeRadioButtonAction(c, event);

		Display.getInstance().vibrate(vibrationTime);
		UIManager.getInstance().addThemeProps(r.getTheme("1"));
		selectedFont = "veryLarge";
		Display.getInstance().getCurrent().refreshTheme();
	}

	protected void onSettings_HugeRadioButtonAction(Component c, ActionEvent event) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.onSettings_HugeRadioButtonAction(c, event);

		Display.getInstance().vibrate(vibrationTime);
		UIManager.getInstance().addThemeProps(r.getTheme("0"));
		selectedFont = "huge";
		Display.getInstance().getCurrent().refreshTheme();
	}

	protected void beforeSettings(Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.beforeSettings(f);

		setAppropriateRadioButtonsSelected(f);
		// findVolumeSlider().addDataChangedListener((DataChangedListener)findVolumeSlider().getComponentForm());
	}

	protected void onSettings_EnglishRadioButtonAction(Component c, ActionEvent event) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.onSettings_EnglishRadioButtonAction(c, event);

		Display.getInstance().vibrate(vibrationTime);
		UIManager.getInstance().setResourceBundle(r.getL10N("cloud4AllThemes", "en"));
		selectedLanguage = "en";
	}

	protected void onSettings_GreekRadioButtonAction(Component c, ActionEvent event) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.onSettings_GreekRadioButtonAction(c, event);

		Display.getInstance().vibrate(vibrationTime);
		Hashtable table = r.getL10N("cloud4AllThemes", "gr");
		UIManager.getInstance().setResourceBundle(table);
		selectedLanguage = "gr";
	}
	
    protected void onSettings_SpanishRadioButtonAction(Component c, ActionEvent event) {
        // If the resource file changes the names of components this call will break notifying you that you should fix the code
        super.onSettings_SpanishRadioButtonAction(c, event);
    
        Display.getInstance().vibrate(vibrationTime);
        Hashtable table = r.getL10N("cloud4AllThemes", "es");
        UIManager.getInstance().setResourceBundle(table);
        selectedLanguage = "es";
    }

	protected void onLogin_LoginBtnAction(Component c, ActionEvent event) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.onLogin_LoginBtnAction(c, event);

		String username = findUsernameTA().getText();
		String ip = findIpTA().getText();

		if (username.equals("1")) {
			selectedLanguage = "gr";
			selectedFont = "huge";
			// selectedTheme = "mitsosYellow";

			// Set Greek
			Hashtable table = r.getL10N("cloud4AllThemes", "gr");
			UIManager.getInstance().setResourceBundle(table);

			// Set mitsosWhite
			UIManager.getInstance().setThemeProps(r.getTheme("mitsosWhite"));
			selectedTheme = "mitsosWhite";

			// Set huge
			UIManager.getInstance().addThemeProps(r.getTheme("0"));

			Display.getInstance().getCurrent().refreshTheme();

			showForm("Main", null);

			/*
			 * The simplest thing is to set the button as a Command and just select the destination form in the action (notice this will effectively disable the action event).
			 * Alternatively you can call: showForm("formName", null); from code. To add elements to an existing form you need to decide where they should be. E.g. the destination
			 * form has a Container where you want to place the TextArea? Just name that Container in the GUI builder as "MyDestContainer". Now when you want to add the text area
			 * you can get a pointer to the Container by using something like: Container dest = findMyDestContainer(Display.getInstance().getCurrent());
			 */

		} else if (username.equals("2")) {
			selectedLanguage = "en";
			selectedFont = "medium";
			selectedTheme = "mitsosYellow";

			// Set English
			Hashtable table = r.getL10N("cloud4AllThemes", "en");
			UIManager.getInstance().setResourceBundle(table);

			// Set small
			UIManager.getInstance().addThemeProps(r.getTheme("3"));

			// Set mitsosWhite
			UIManager.getInstance().setThemeProps(r.getTheme("mitsosYellow"));
			selectedTheme = "mitsosYellow";
			Display.getInstance().getCurrent().refreshTheme();

			showForm("Main", null);
		} else if (username.equals("3")) {
			// Default Theme
//			selectedTheme = "mitsosNative";
//			UIManager.getInstance().setThemeProps(r.getTheme("mitsosNative"));
//			Display.getInstance().getCurrent().refreshTheme();
			
			// Testing the applyServerSettings method
//	    	applyServerSettings("Yellow-Black", "huge", "Greek", 50, 0);
			// Testing the applyServerSettings method and parseJSONResponseWithCodenameOneParser
			

	    	selectedTheme = "mitsosWhite";
	    	selectedFont = "large";
	    	selectedLanguage = "en";
	    	volume = 100;
	    	vibrationMode = 1;

			Hashtable table = r.getL10N("cloud4AllThemes", selectedLanguage);
			UIManager.getInstance().setResourceBundle(table);

			UIManager.getInstance().setThemeProps(r.getTheme(selectedTheme));

			UIManager.getInstance().addThemeProps(r.getTheme("2"));
			Display.getInstance().getCurrent().refreshTheme();
			
			
//			applyServerSettings(getResponseHashtable());
			
			showForm("Main", null);
		} else {
//			// DEFAULT PROFILE (currently 1) IS LOADED
//			selectedLanguage = "gr";
//			selectedFont = "huge";
//			// selectedTheme = "mitsosYellow";
//
//			// Set Greek
//			Hashtable table = r.getL10N("cloud4AllThemes", "gr");
//			UIManager.getInstance().setResourceBundle(table);
//
//			// Set mitsosWhite
//			UIManager.getInstance().setThemeProps(r.getTheme("mitsosWhite"));
//			selectedTheme = "mitsosWhite";
//
//			// Set huge
//			UIManager.getInstance().addThemeProps(r.getTheme("0"));
//
//			Display.getInstance().getCurrent().refreshTheme();
			
			// CHANGES MAY TAKE PLACE ACCORDING TO THE SERVICE RESPONSE (os_jme user is currently hardcoded)
			Hashtable tab = getJsonResponseHashtable(ip, username);
			applyServerSettings(tab);

			if(!isError) {
				showForm("Main", null);
			} else {
				showForm("Login", null);
			}
		}
	}

	public Form findQRLoginForm(Component root) {
		return (Form) findByName("QR Login", root);
	}

	protected void beforeQRLogin(Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.beforeQRLogin(f);

		if (CodeScanner.getInstance() != null) {
			final Button qrCode = new Button("Scan QR");
			// findQRLogin().addComponent(qrCode);
			// Form currentForm = Display.getInstance().getCurrent();
			// System.out.println("Form: " + currentForm.getName());
			findQRLoginForm(f).addComponent(qrCode);

			qrCode.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					CodeScanner.getInstance().scanQRCode(new ScanResult() {
						public void scanCompleted(String contents, String formatName, byte[] rawBytes) {

							// text = text + "Complete!";
							// qrCode.setText(text);
							//
							// showForm("Main", null);

							if (contents.equals("1")) {
								flag = "1";
							} else if (contents.equals("2")) {
								flag = "2";
							}
						}

						public void scanCanceled() {
							// text = text + "Cancel";
							// qrCode.setText(text);
							// showForm("Main", null);
							if (flag != null) {
								if (flag.equals("1")) {
									// text = text + "1";
									// qrCode.setText(text);

									selectedLanguage = "en";
									selectedFont = "huge";
									selectedTheme = "mitsosYellow";

									// Set Greek
									Hashtable table = r.getL10N("cloud4AllThemes", "en");
									UIManager.getInstance().setResourceBundle(table);

									// Set huge
									UIManager.getInstance().addThemeProps(r.getTheme("0"));

									// Set mitsosYellow
									UIManager.getInstance().setThemeProps(r.getTheme("mitsosYellow"));
									selectedTheme = "mitsosYellow";
									Display.getInstance().getCurrent().refreshTheme();

									String currentFormName = Display.getInstance().getCurrent().getName();

									text = text + currentFormName;

									showForm("Main", null);
								} else if (flag.equals("2")) {
									// text = text + "2";
									// qrCode.setText(text);

									selectedLanguage = "en";
									selectedFont = "huge";
									selectedTheme = "mitsosWhite";

									// Set English
									Hashtable table = r.getL10N("cloud4AllThemes", "en");
									UIManager.getInstance().setResourceBundle(table);

									// Set small
									UIManager.getInstance().addThemeProps(r.getTheme("0"));

									// Set mitsosWhite
									UIManager.getInstance().setThemeProps(r.getTheme("mitsosWhite"));
									selectedTheme = "mitsosWhite";
									Display.getInstance().getCurrent().refreshTheme();

									showForm("Main", null);
								}
							}
						}

						public void scanError(int errorCode, String message) {
							// text = text + "Error";
							// qrCode.setText(text);

							if (flag != null) {
								if (flag.equals("1")) {
									// text = text + "1";
									// qrCode.setText(text);

									selectedLanguage = "en";
									selectedFont = "huge";
									selectedTheme = "mitsosYellow";

									// Set Greek
									Hashtable table = r.getL10N("cloud4AllThemes", "en");
									UIManager.getInstance().setResourceBundle(table);

									// Set huge
									UIManager.getInstance().addThemeProps(r.getTheme("0"));

									// Set mitsosWhite
									UIManager.getInstance().setThemeProps(r.getTheme("mitsosYellow"));
									selectedTheme = "mitsosYellow";
									Display.getInstance().getCurrent().refreshTheme();

									String currentFormName = Display.getInstance().getCurrent().getName();

									text = text + currentFormName;

									showForm("Main", null);
								} else if (flag.equals("2")) {
									// text = text + "2";
									// qrCode.setText(text);

									selectedLanguage = "en";
									selectedFont = "huge";
									selectedTheme = "mitsosWhite";

									// Set English
									Hashtable table = r.getL10N("cloud4AllThemes", "en");
									UIManager.getInstance().setResourceBundle(table);

									// Set small
									UIManager.getInstance().addThemeProps(r.getTheme("0"));

									// Set mitsosWhite
									UIManager.getInstance().setThemeProps(r.getTheme("mitsosWhite"));
									selectedTheme = "mitsosWhite";
									Display.getInstance().getCurrent().refreshTheme();

									showForm("Main", null);
								}
							}

						}
					});
				}
			});

			final Button barCode = new Button("Scan Barcode");
			// findQRLogin().addComponent(barCode);

			// Form currentForm2 = Display.getInstance().getCurrent();
			// System.out.println("Form: " + currentForm2.getName());
			findQRLoginForm(f).addComponent(barCode);

			barCode.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					CodeScanner.getInstance().scanBarCode(new ScanResult() {
						public void scanCompleted(String contents, String formatName, byte[] rawBytes) {
							// barCode.setText("Bar: " + contents);

							if (contents.equals("1")) {
								flag = "1";
							} else if (contents.equals("2")) {
								flag = "2";
							}
						}

						public void scanCanceled() {
							if (flag != null) {
								if (flag.equals("1")) {
									// text = text + "1";
									// qrCode.setText(text);

									selectedLanguage = "gr";
									selectedFont = "huge";
									selectedTheme = "mitsosYellow";

									// Set Greek
									Hashtable table = r.getL10N("cloud4AllThemes", "gr");
									UIManager.getInstance().setResourceBundle(table);

									// Set huge
									UIManager.getInstance().addThemeProps(r.getTheme("0"));

									// Set mitsosWhite
									UIManager.getInstance().setThemeProps(r.getTheme("mitsosWhite"));
									selectedTheme = "mitsosWhite";
									Display.getInstance().getCurrent().refreshTheme();

									String currentFormName = Display.getInstance().getCurrent().getName();

									text = text + currentFormName;

									showForm("Main", null);
								} else if (flag.equals("2")) {
									// text = text + "2";
									// qrCode.setText(text);

									selectedLanguage = "en";
									selectedFont = "small";
									selectedTheme = "Leather";

									// Set English
									Hashtable table = r.getL10N("cloud4AllThemes", "en");
									UIManager.getInstance().setResourceBundle(table);

									// Set small
									UIManager.getInstance().addThemeProps(r.getTheme("4"));

									// Set mitsosWhite
									UIManager.getInstance().setThemeProps(r.getTheme("Leather"));
									selectedTheme = "Leather";
									Display.getInstance().getCurrent().refreshTheme();

									showForm("Main", null);
								}
							}
						}

						public void scanError(int errorCode, String message) {
							if (flag != null) {
								if (flag.equals("1")) {
									// text = text + "1";
									// qrCode.setText(text);

									selectedLanguage = "gr";
									selectedFont = "huge";
									selectedTheme = "mitsosYellow";

									// Set Greek
									Hashtable table = r.getL10N("cloud4AllThemes", "gr");
									UIManager.getInstance().setResourceBundle(table);

									// Set huge
									UIManager.getInstance().addThemeProps(r.getTheme("0"));

									// Set mitsosWhite
									UIManager.getInstance().setThemeProps(r.getTheme("mitsosWhite"));
									selectedTheme = "mitsosWhite";
									Display.getInstance().getCurrent().refreshTheme();

									String currentFormName = Display.getInstance().getCurrent().getName();

									text = text + currentFormName;

									showForm("Main", null);
								} else if (flag.equals("2")) {
									// text = text + "2";
									// qrCode.setText(text);

									selectedLanguage = "en";
									selectedFont = "small";
									selectedTheme = "Leather";

									// Set English
									Hashtable table = r.getL10N("cloud4AllThemes", "en");
									UIManager.getInstance().setResourceBundle(table);

									// Set small
									UIManager.getInstance().addThemeProps(r.getTheme("4"));

									// Set mitsosWhite
									UIManager.getInstance().setThemeProps(r.getTheme("Leather"));
									selectedTheme = "Leather";
									Display.getInstance().getCurrent().refreshTheme();

									showForm("Main", null);
								}
							}
						}
					});
				}
			});
		}
	}

	public void getFontFromName(String font) {
		// String returnStr = "";
		if (font.equals("huge")) {
			UIManager.getInstance().addThemeProps(r.getTheme("0"));
		} else if (font.equals("veryLarge")) {
			UIManager.getInstance().addThemeProps(r.getTheme("1"));
		} else if (font.equals("large")) {
			UIManager.getInstance().addThemeProps(r.getTheme("2"));
		} else if (font.equals("medium")) {
			UIManager.getInstance().addThemeProps(r.getTheme("3"));
		} else if (font.equals("small")) {
			UIManager.getInstance().addThemeProps(r.getTheme("4"));
		}
		// return returnStr;
	}

	protected void postQRLogin(Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.postQRLogin(f);

		if (flag != null) {
			if (flag.equals("1")) {
				showForm("Main", null);
			}
		}
	}

	protected void onMain_MultiListAction(Component c, ActionEvent event) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.onMain_MultiListAction(c, event);

		System.out.println("findMultiList().getSelectedItem(): " + findMultiList().getSelectedItem());
		System.out.println("findMultiList().getSelectedIndex():" + findMultiList().getSelectedIndex());
		int selectedIndex = findMultiList().getSelectedIndex();

		if (selectedIndex == 0) {
			// showForm("Contacts", null); // First choice
			Display.getInstance().vibrate(vibrationTime);
			// showForm("MyContacts Form", null);
			showForm("Contacts", null);
		} else if (selectedIndex == 1) {
			Display.getInstance().vibrate(vibrationTime);
			showForm("Share", null);
			// showForm("Video View", null);
		} else if (selectedIndex == 2) {
			Display.getInstance().vibrate(vibrationTime);
			// showForm("ContactsTest", null);
			showForm("My Multimedia Form", null);
		} else if (selectedIndex == 3) {
			Display.getInstance().vibrate(vibrationTime);
			// showForm("Maps Form", null);
			showForm("Find Your Position Form", null);
		} else if (selectedIndex == 4) {
			Display.getInstance().vibrate(vibrationTime);
			showForm("Web Browser Form", null);
			// showForm("Contacts", null);
		}
		// else if (selectedIndex == 4) {
		// Display.getInstance().vibrate(vibrationTime);
		// // showForm("Camera Form", null);
		// showForm("Storage Form", null);
		// }
		// else if (selectedIndex == 5) {
		// Display.getInstance().vibrate(vibrationTime);
		// showForm("File Browser", null);
		// // FolderBrowser fb = new FolderBrowser();
		// // fb.show();
		// }
		// else if (selectedIndex == 6) {
		// // Display.getInstance().vibrate(vibrationTime);
		// // showForm("My Audio Form", null);
		// // FolderBrowser fb = new FolderBrowser();
		// // fb.show();
		// }
	}

	// private Form showContacts() {
	// Form testForm = new Form();
	//
	// // ContactSelector contactSelector = new ContactSelector(); // original
	//
	// ContactSelector contactSelector = new ContactSelector(this); //new ContactSelector(r, selectedTheme, selectedLanguage, selectedFont); // TAX
	// testForm.addComponent(contactSelector);
	// // testForm.show();
	// /*
	// * After creating the contactselector object and add it to your form, you will then add a ContactListener to your object which will listen for the done and cancel
	// * event(done event occurs after a user has finished selecting the contact(s)). The doneContactSelection method in the ContactListener is the corresponding done function
	// * which is called after a user clicks the done button in the ContactSelector container. It gives you an hashtable of selected contacts which you can loop through to get
	// * each contact. Sample code is available below.
	// *
	// * The cancelContactSelection method is called when a user press the cancel button - meaning no contact was selected.
	// */
	// contactSelector.addListener(new ContactListener() {
	// public void doneContactSelection(Hashtable h) {
	// final Enumeration outter = h.keys();
	//
	// // Loops through the hashtable and gives you the selected Contact object.
	// while (outter.hasMoreElements()) {
	// // Here is one of the selected contact, do whatever you want with it.
	// final Contact con = (Contact) h.get(outter.nextElement());
	// // System.out.println(con.getBirthday()+ "," + con.getDisplayName() + con.getFamilyName() + con.getFirstName() + con.getId() + con.getId() + con.getNote() +
	// // con.getPrimaryEmail() + con.getPrimaryPhoneNumber() + con.getAddresses() + con.getEmails() + con.getPhoneNumbers() +con.getPhoto() + con.getUrls());
	//
	// }
	// }
	//
	// public void cancelContactSelection() {
	// // Perform any action you wish to. Eg switch to another screen.
	// }
	// });
	//
	// return testForm;
	// }

	protected boolean initListModelAllContactsMultiList(final List cmp) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.initListModelAllContactsMultiList(cmp);

		// Vector contactVec = new Vector();
		//
		// try {
		// // phoneContacts = Display.getInstance().getAllContacts(true);
		// // System.out.println("ARXH");
		// phoneContacts = ContactsManager.getAllContacts();
		// // System.out.println("TELOS");
		// System.out.println("phoneContacts.length: " + phoneContacts.length);
		//
		// text2 = "a" + phoneContacts.length;
		// cmp.getComponentForm().setTitle(text2);
		// cmp.getComponentForm().revalidate();
		//
		// for (int i = 0; i < phoneContacts.length; i++) {
		// Contact contact = Display.getInstance().getContactById(phoneContacts[i]);
		//
		// text2 = text2 + i;
		//
		// Hashtable h = new Hashtable();
		// h.put("Line1", i + "." + contact.getDisplayName()); // contact.getFirstName() + " " + contact.getFamilyName()
		// // h.put("Line2", contact.getId());
		// // h.put("Line3", contact.getId());
		// contactVec.add(h);
		// }
		// } catch (Exception xcp) {
		// xcp.printStackTrace();
		// System.out.println(xcp.getMessage());
		// }
		//

		final String[] allContacts = ContactsManager.getAllContactsWithNumbers();// ContactsManager.getAllContacts();

		for (int i = 0; i < allContacts.length; i++) {
			System.out.println(i + ":" + allContacts[i]);
		}

		Display.getInstance().callSerially(new Runnable() {
			public void run() {
				ListModel model = new ContactsModel(allContacts);// DefaultListModel(items);
				cmp.setModel(model);
			}
		});

		return true;
	}

	protected void onMapsForm_MapsMultiListAction(Component c, ActionEvent event) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.onMapsForm_MapsMultiListAction(c, event);

		int selectedIndex = findMapsMultiList().getSelectedIndex();

		if (selectedIndex == 0) {
			Display.getInstance().vibrate(vibrationTime);
			showForm("Maps Providers", null);
		} else if (selectedIndex == 1) {
			Display.getInstance().vibrate(vibrationTime);
			showForm("Proximity Providers Form", null);
		}
	}

	protected void exitMediaPlayerForm(Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.exitMediaPlayerForm(f);

		Display.getInstance().flashBacklight(2000);
	}

	protected void beforeMediaPlayerForm(Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.beforeMediaPlayerForm(f);

		// System.out.println("UIManager.getInstance().getThemeName(): " + UIManager.getInstance().getThemeName());
		// InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/cowboy.mp4");
		//
		// try {
		// media = MediaManager.createMedia(is, "video/mpeg4", null);
		// // volume = media.getVolume();
		// media.play();
		//
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

	}

	protected void beforeMain(Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.beforeMain(f);

		if (media != null) {
			if (media.isPlaying()) {
				media.pause();
				media.cleanup();
			}
		}
	}

	protected boolean onMediaPlayerFormBack() {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		boolean val = super.onMediaPlayerFormBack();

		showForm("Main", null);
		return val;
	}

	// protected void postAyoolaContacts(Form f) {
	// // If the resource file changes the names of components this call will break notifying you that you should fix the code
	// super.postAyoolaContacts(f);
	//
	// // ContactSelector selector = new ContactSelector(); // original
	// ContactSelector selector = new ContactSelector(r, selectedTheme, selectedLanguage, selectedFont); // diko mou
	// f.addComponent(selector);
	// }

	// // ATTENTION: With the translated items
	// protected void postCameraForm(final Form f) {
	// // If the resource file changes the names of components this call will break notifying you that you should fix the code
	// super.postCameraForm(f);
	//
	// // Button b = new Button("capture");
	// final Label l = findCameraLabel(f);//new Label();
	// l.getStyle().setAlignment(Component.CENTER);
	//
	// // f.addComponent(BorderLayout.CENTER, l);
	//
	// // b.addActionListener(new ActionListener() {
	//
	// // public void actionPerformed(ActionEvent evt) {
	// Image im = l.getStyle().getBgImage();
	// if(im != null){
	// im.dispose();
	// }
	// l.getStyle().setBgImage(null);
	//
	// Capture.capturePhoto(new ActionListener() {
	//
	// public void actionPerformed(ActionEvent evt) {
	// InputStream is = null;
	// try {
	// String path = (String) evt.getSource();
	// System.out.println("path " + path);
	// // findStoragePathLb().setText("Path2: " + path);
	// is = com.codename1.io.FileSystemStorage.getInstance().openInputStream(path);
	// Image i = Image.createImage(is);
	// currentImage = i.scaledWidth(360);
	// l.setIcon(currentImage);
	// f.revalidate();
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// } finally {
	// try {
	// is.close();
	// } catch (IOException ex) {
	// ex.printStackTrace();
	// }
	// }
	// }
	// });
	// // }
	// // });
	// // f.addComponent(BorderLayout.SOUTH, b);
	// f.show();
	// }

	protected boolean onStorageFormSave() {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		boolean val = super.onStorageFormSave();

		// IN CASE WE USE Storage
		// Vector<String> vec = new Vector<String>();
		// String test1 = "Pame gia treles stis Seyxeles oo";
		// String test2 = "Na kanoume push-ups";
		//
		// vec.add(test1);
		// vec.add(test2);
		// Storage.getInstance().writeObject("Vector", vec);

		// 2
		// Vector<Image> vec = new Vector<Image>();
		// Image image1 = currentImage;

		if (currentImage2 instanceof EncodedImage) {
			photos.addPhoto((EncodedImage) currentImage2);
			System.out.println("photos.getPhotos().length: " + photos.getPhotos().size());
		}

		Storage.getInstance().writeObject("SavedPhoto", photos);

		// --------------------------------------------------------------------------------------------------------------------

		// // IN CASE WE USE FILESTORAGESYSTEM
		// FileSystemStorage fs = FileSystemStorage.getInstance();
		// String root = fs.getAppHomePath(); //getRoots()[0];
		//
		// if(!root.endsWith("/")) {
		// root += "/";
		// }
		// String fileName = root + "";
		// System.out.println("filename: " + fileName);
		// findInfoLb().setText("NAME: " + fileName);
		// findInfo2Lb().setText("IMAGE: " + currentImage2.getImageName());
		//
		// findInfoLb().repaint();
		// findInfo2Lb().repaint();
		//
		// OutputStream o1, o2, o3 = null;
		//
		// try {
		// FileSystemStorage.getInstance().mkdir("/Photos2");
		// // o1 = FileSystemStorage.getInstance().openOutputStream("file:///C:/Photos/file.txt");
		// o2 = FileSystemStorage.getInstance().openOutputStream("file:///C:/Photos/file.jpeg");
		// // o3 = FileSystemStorage.getInstance().openOutputStream(currentImage2.getImageName());
		//
		// findArea().setText("NULL??????? :" + ImageIO.getImageIO());
		// findArea().getParent().revalidate();
		//
		// ImageIO.getImageIO().save(isGlobal, o2, ImageIO.FORMAT_JPEG, 100, 100, 1); //"file:///C:/Images.1.jpeg",
		//
		// } catch (IOException ex) {
		// ex.printStackTrace();
		// findArea().setText("1: " + ex.getMessage());
		// findArea().getParent().revalidate();
		//
		// // Dialog.show("Backup error", "CSV Backup file creation has failed. "+ex.getMessage(), "OK", null);
		// // return;
		// }

		// try {
		//
		// findInfoLb().setText("Photo Path: " + photoPath);
		// String step = "1";
		// findInfo2Lb().setText(step + photoPath);
		//
		// ImageIO imageio = ImageIO.getImageIO();
		// ByteArrayOutputStream out = new ByteArrayOutputStream();
		// step = "2";
		// findInfo2Lb().setText(step+ " - " + ImageIO.getImageIO());
		// System.out.println(step+ " - " + ImageIO.getImageIO());
		//
		//
		//
		// String q = ImageIO.getImageIO().toString();
		// step = "3.0";
		// findInfo2Lb().setText(step + " - " + q);
		// System.out.println(step + " - " + q);
		//
		// InputStream a = FileSystemStorage.getInstance().openInputStream(photoPath);
		// step = "3.1";
		// findInfo2Lb().setText(step);
		// System.out.println(step + " - " + a);
		//
		// byte[] buf = new byte[1024000];
		// int len;
		// while ((len = a.read(buf)) > 0){
		// out.write(buf, 0, len);
		// }
		//
		// ImageIO.getImageIO().save(a, //"file:///C:/Images.1.jpeg" --> NOT IMPLEMENTED FOR J2ME
		// out,
		// ImageIO.FORMAT_JPEG,
		// 100, 100, 1);
		// step = "3";
		// findInfo2Lb().setText(step);
		// System.out.println("a. " + step);
		//
		// Image im = Image.createImage(out.toByteArray(), 0, out.toByteArray().length);
		// step = "4";
		// findInfo2Lb().setText(step);
		// System.out.println("b. " + step);
		//
		// findInfo2Lb().setIcon(im);
		// step = "5";
		// findInfo2Lb().setText(step);
		// System.out.println("c. " + step);
		//
		// findInfo2Lb().getParent().revalidate();
		// step = "6";
		// findInfo2Lb().setText(step);
		// System.out.println("d. " + step);
		//
		// } catch (Exception ex) {
		// findArea().setText("Exception: " + ex.getMessage() + "-" + ex.toString());
		// findArea().getParent().revalidate();
		// findInfoLb().getParent().revalidate();
		// ex.printStackTrace();
		// }

		// OutputStream os = fss.openOutputStream("/i.png");

		return val;
	}

	private String getDateTime() {
		Calendar now = Calendar.getInstance();

		System.out.println("Today: " + Calendar.getInstance().toString());
		System.out.println("Today3: " + now.get(Calendar.DATE));
		System.out.println("Today3: " + now.get(Calendar.MONTH));
		System.out.println("Today3: " + now.get(Calendar.YEAR));
		System.out.println("Today4: " + now.get(Calendar.HOUR_OF_DAY));
		System.out.println("Today5: " + now.get(Calendar.MINUTE));
		System.out.println("Today6: " + now.get(Calendar.SECOND));

		String timeDate = "" + now.get(Calendar.DATE) + now.get(Calendar.MONTH) + now.get(Calendar.YEAR) + now.get(Calendar.HOUR_OF_DAY) + now.get(Calendar.MINUTE)
				+ now.get(Calendar.SECOND);
		return timeDate;
	}

	protected void postStorageForm(final Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.postStorageForm(f);

		final Label l = findStorageLb(f);// new Label();
		l.getStyle().setAlignment(Component.CENTER);

		Image im = l.getStyle().getBgImage();
		if (im != null) {
			im.dispose();
		}
		l.getStyle().setBgImage(null);

		Capture.capturePhoto(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				InputStream is = null;
				try {
					photoPath = (String) evt.getSource();
					System.out.println("path " + photoPath);

					is = com.codename1.io.FileSystemStorage.getInstance().openInputStream(photoPath);
					isGlobal = is;
					i = EncodedImage.create(is);
					i.setImageName(getDateTime());
					currentImage2 = (EncodedImage) i;
					i = i.scaledWidth(360);
					l.setIcon(i);
					f.revalidate();
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					try {
						is.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		f.show();
	}

	protected boolean onStorageFormOpen() {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		boolean val = super.onStorageFormOpen();

		// Form f = Display.getInstance().getCurrent();
		// System.out.println("FORM NAME 8: " + f.getName());
		//
		// Image img = null;
		// String path;
		// ImageActionListener aiActionListener = new ImageActionListener();
		//
		// Display.getInstance().openImageGallery(aiActionListener);
		// path = aiActionListener.GetPath();
		// System.out.println("PATH: " + path);
		//
		// try {
		// img = Image.createImage(path);
		// findStorageLb().setIcon(img);
		// findStorageLb().setText("");
		// f.revalidate();
		// System.out.println("FORM NAME 9: " + f.getName());
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		final Form f = Display.getInstance().getCurrent();// (Form)findByName("Storage Form", null);
		System.out.println("FORM NAME: " + f.getName());

		final Label l = findStorageLb(f);// new Label();
		// final Label l =
		l.getStyle().setAlignment(Component.CENTER);

		Image im = l.getStyle().getBgImage();
		if (im != null) {
			im.dispose();
		}
		l.getStyle().setBgImage(null);

		Capture.capturePhoto(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				InputStream is = null;
				try {
					photoPath = (String) evt.getSource();
					System.out.println("path " + photoPath);

					is = com.codename1.io.FileSystemStorage.getInstance().openInputStream(photoPath);
					isGlobal = is;
					i = EncodedImage.create(is);
					i.setImageName(getDateTime());
					currentImage2 = (EncodedImage) i;
					i = i.scaledWidth(360);
					l.setIcon(i);
					f.revalidate();
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					try {
						is.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		f.show();

		return val;
	}

	// protected void postMyContactsForm(final Form f) {
	// // If the resource file changes the names of components this call will break notifying you that you should fix the code
	// super.postMyContactsForm(f);
	//
	// // ME AYTO TON KWDIKA LEITOURGOUN TA CONTACTS OK STO "As List" ALLA PARAMENOUN Loading... OTAN TO GYRNAW SE "As Grid"
	// Contacts contactsKitchen = new Contacts();
	// Container contactsContainer = contactsKitchen.createDemo(r);
	//
	// f.addComponent(BorderLayout.CENTER, contactsContainer);
	//
	//
	//
	//
	//
	// // System.out.println(" ----> selectedFont: " + selectedFont);
	// //
	// // final ContactSelector contactSelector = new ContactSelector(r, this, selectedTheme, selectedLanguage, selectedFont); // new ContactSelector(this); TAX
	// // f.addComponent(BorderLayout.CENTER, contactSelector);
	// //
	// //// /*
	// //// * After creating the contactselector object and add it to your form, you will then add a ContactListener to your object which will listen for the done and cancel
	// //// * event(done event occurs after a user has finished selecting the contact(s)). The doneContactSelection method in the ContactListener is the corresponding done function
	// //// * which is called after a user clicks the done button in the ContactSelector container. It gives you an hashtable of selected contacts which you can loop through to get
	// //// * each contact. Sample code is available below.
	// //// *
	// //// * The cancelContactSelection method is called when a user press the cancel button - meaning no contact was selected.
	// //// */
	// //// contactSelector.addListener(new ContactListener() {
	// //// public void doneContactSelection(Hashtable h) {
	// //// final Enumeration outter = h.keys();
	// ////
	// //// // Loops through the hashtable and gives you the selected Contact object.
	// //// while (outter.hasMoreElements()) {
	// //// // Here is one of the selected contact, do whatever you want with it.
	// //// final Contact con = (Contact) h.get(outter.nextElement());
	// //// // System.out.println(con.getBirthday()+ "," + con.getDisplayName() + con.getFamilyName() + con.getFirstName() + con.getId() + con.getId() + con.getNote() +
	// //// // con.getPrimaryEmail() + con.getPrimaryPhoneNumber() + con.getAddresses() + con.getEmails() + con.getPhoneNumbers() +con.getPhoto() + con.getUrls());
	// //// }
	// //// }
	// ////
	// //// public void cancelContactSelection() {
	// //// // Perform any action you wish to. Eg switch to another screen.
	// //// }
	// //// });
	// ////
	// //// // Display.getInstance().callSerially(new Runnable() {
	// //// // public void run() {
	// //// // // TODO Auto-generated method stub
	// //// // contactSelector.revalidate();
	// //// // contactSelector.getComponentForm().revalidate();
	// //// // f.revalidate();
	// //// // }
	// //// // });
	// // contactSelector.revalidate();
	// // contactSelector.getComponentForm().revalidate();
	// // f.revalidate();
	// }

	protected void onContacts_AllContactsMultiListAction(Component c, ActionEvent event) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.onContacts_AllContactsMultiListAction(c, event);

		// int selectedIndex = findAllContactsMultiList().getSelectedIndex();
		// int testingIndex = selectedIndex + 1;
		// System.out.println("index: " + selectedIndex);
		// // Contact selectedContact = Display.getInstance().getContactById(""+selectedIndex);
		//
		// Contact selectedContact = Display.getInstance().getContactById(""+testingIndex); // Phdaei ton Bradd Pitt ????

		Object selectedItem = findAllContactsMultiList().getSelectedItem();
		Hashtable seleConta = (Hashtable) selectedItem;
		Contact selectedContact = (Contact) seleConta.get("contact");
		// String selectedId = selectedContact.getId();

		// Contact selectedContact = Display.getInstance().getContactById(""+testingIndex); // Phdaei ton Bradd Pitt ????

		// if(selectedContact != null)
		if (selectedContact != null) {
			Display.getInstance().vibrate(vibrationTime);
			
			String homeNo = "-";
			String mobileNo = "-";
			String workNo = "-";
			String faxNo = "-";
			String otherNo = "-";
			;
			
			// selectedContact.getPrimaryPhoneNumber(); null (NOKIA 701 with SIM card)
			// selectedContact.getFamilyName(); OK (NOKIA 701 with SIM card)
			Hashtable phoneNumbers = selectedContact.getPhoneNumbers();

			if (phoneNumbers.get("home") != null) // null
				homeNo = phoneNumbers.get("home").toString();

			if (phoneNumbers.get("mobile") != null) // OK
				mobileNo = phoneNumbers.get("mobile").toString();

			if (phoneNumbers.get("work") != null) // OK
				workNo = phoneNumbers.get("work").toString();

			if (phoneNumbers.get("fax") != null) // null
				faxNo = phoneNumbers.get("fax").toString();

			if (phoneNumbers.get("other") != null) // OK
				otherNo = phoneNumbers.get("other").toString();

			// String info = selectedContact.getId() + ":" + homeNo + ":" + mobileNo + ":" + workNo + ":" + faxNo + ":" + otherNo;
			 c.getComponentForm().setTitle(selectedContact.getFamilyName() + "-" + mobileNo);
			 c.getComponentForm().revalidate();

			Display.getInstance().dial(mobileNo);
		}

		// Display.getInstance().dial();

		// displayContact = Display.getInstance().getContactById(phoneContacts[selectedIndex]);
		// if (selectedIndex == 0) {
		// showForm("Contact Form", null);
		// }
	}

	public void setDisplayContact(Contact theContact) {
		displayContact = theContact;
		System.out.println(displayContact);
	}

	protected void postContactForm(Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.postContactForm(f);

		// DIKO MOU
		// System.out.println("!!!!!! selectedFont: " + selectedFont);
		//
		// UIManager.getInstance().setThemeProps(r.getTheme(selectedTheme));
		// getFontFromName(selectedFont);
		//
		// Display.getInstance().getCurrent().refreshTheme();
		//
		// f.setTitle(displayContact.getDisplayName());
		//
		// // Label photoLabel = (Label)findByName("photoLb", null);
		// findPhotoLb().getStyle().setAlignment(Component.CENTER);
		// findPhotoLb().getStyle().setMargin(0, 80, 0, 0);
		// findNameField().setText(displayContact.getDisplayName());
		// findSurnameField().setText(displayContact.getFamilyName());
		// findPhoneField().setText(displayContact.getId());
		//
		// f.revalidate();
	}

	protected void beforeFileBrowser(Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.beforeFileBrowser(f);

		System.out.println("______________________________________________");
		System.out.println(f);
		System.out.println(findBrowserCon());
		System.out.println(findFolderBrowserList());
		System.out.println(currFile);
		System.out.println("______________________________________________");

		browser = new MyFolderBrowser(f, findBrowserCon(), findFolderBrowserList());
	}

	protected void postProximityForm(Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.postProximityForm(f);

		ProximityService ps = new ProximityService();
		// InfiniteProgress inf = new InfiniteProgress();
		// Dialog progress = inf.showInifiniteBlocking();

		// startGps();
		// 24.7.13 2:56
		// System.out.println(lastLocation.getLongitude() + "-" + lastLocation.getLatitude());

		final Dialog dlg = new Dialog();
		InfiniteProgress prog = new InfiniteProgress();
		prog.setAnimation(r.getImage("waiting_4.png"));

		dlg.setLayout(new BorderLayout());
		dlg.addComponent(BorderLayout.CENTER, prog);
		dlg.showPacked(BorderLayout.CENTER, false);		
		
		Vector poiVec = ps.parseXMLResponseWithCodenameOneParser(ps.requestProximityService(lastLocation.getLongitude(), lastLocation.getLatitude()));
		ps.showResturantsOnMap(f, findProximityMc(), poiVec, lastLocation.getLatitude(), lastLocation.getLongitude());
		
		dlg.dispose();
	}

	protected void onFileBrowser_FolderBrowserListAction(Component c, ActionEvent event) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.onFileBrowser_FolderBrowserListAction(c, event);

		currFile = findFolderBrowserList().getSelectedItem().toString();

		if (!currFile.endsWith(MyFolderBrowser.DIRECTORY_SEPARATOR)) {
			currFile = currFile + MyFolderBrowser.DIRECTORY_SEPARATOR;
		}
		if (currFile.endsWith(MyFolderBrowser.DIRECTORY_SEPARATOR) || currFile.equals(MyFolderBrowser.UP_DIRECTORY)) {

			Container con2 = (Container) findBrowserCg().getParent();
			Container formFileBrowser = (Container) con2.getParent();
			Form form2 = (Form) con2.getParent();
			String path2 = browser.currentDirectory + currFile;

			if (path2.indexOf(".jpg") != -1) {
				InputStream is = null;
				// Image currentImage = null;
				String path = null;

				try {
					path = path2; // browser.currentDirectory; //(String) evt.getSource();
					System.out.println("path " + path);
					is = com.codename1.io.FileSystemStorage.getInstance().openInputStream(path);
					Image i = Image.createImage(is);
					fileBrowserViewImage = i.scaledWidth(360);

				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					try {
						is.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}

				showForm("File Browser View Form", null);
				// PhotoView view = new PhotoView(currentImage, path);
				// view.show();
			} else {
				browser.browseDirectory((Form) formFileBrowser, findBrowserCon(), findFolderBrowserList(), currFile);
			}
		}

	}

	protected void postFileBrowserViewForm(Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.postFileBrowserViewForm(f);

		findPhotoLb().setIcon(fileBrowserViewImage);
		f.revalidate();
	}

	protected void postLoadPhotoForm(final Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.postLoadPhotoForm(f);

		final MyPhotos photos = (MyPhotos) Storage.getInstance().readObject("SavedPhoto");

		if (photos != null) {
			// // Form f = Display.getInstance().getCurrent();
			// System.out.println("FORM NAME: " + f.getName());
			//
			// f.refreshTheme();
			// f.revalidate();
			//
			// try {
			// System.out.println("2000-1");
			// Thread.sleep(2000);
			// System.out.println("2000-2");
			// } catch (InterruptedException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			//
			// findStorageLb().setIcon(null);
			// findStorageLb().setIcon(photos.getPhoto(0));
			// findStorageLb().getParent().revalidate();
			//
			// try {
			// System.out.println("5000-1");
			// Thread.sleep(5000);
			// System.out.println("5000-2");
			// } catch (InterruptedException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			//
			// findStorageLb().setIcon(photos.getPhoto(1));
			// findStorageLb().getParent().revalidate();
			//
			// f.revalidate();
			// // f.show();

			System.out.println("FORM NAME2: " + Display.getInstance().getCurrent().getName());
		}
	}

	protected void beforeLoadPhotoForm(Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.beforeLoadPhotoForm(f);

		// AYTO LEITOURGEI KAI KANEI TO PRESENTATION APO TIW PHOTOS POU TRAVIXAME
		// final MyPhotos photos = (MyPhotos) Storage.getInstance().readObject("SavedPhoto");
		//
		// if (photos != null) {
		// // Display.getInstance().callSerially(new SwitchPhotos(photos.getPhotos(), findPhotoLb()));
		// new SwitchPhotos(photos.getPhotos(), findPhotoLb());
		// System.out.println("FORM NAME2: " + Display.getInstance().getCurrent().getName());
		// }

		// AYTO LEITOURGEI KAI DIALEGOUME EMEIS APO TON FILE BROWSER POIA PHOTO THELOUME NA ANOIXOUME (thelei tropopoihsh to size - koita commented out lines)
		Display.getInstance().openImageGallery(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				try {
					if (evt == null) {
						System.out.println("user cancelled");
						return;
					}

					String path = (String) evt.getSource();
					// we are opening the image with the file handle since the image
					// is large this method can scale it down dynamically to a manageable
					// size that doesn't exceed the heap
					Image i = Image.createImage(path);
					
					findPhotoLb().setIcon(i.scaledWidth(Display.getInstance().getDisplayWidth())); // TODO: This should be in place of 360
					
//					Label image = new Label(i.scaledWidth(Display.getInstance().getDisplayWidth() / 2));
//					final ComponentGroup cnt = new ComponentGroup();
//					if (cnt.getComponentCount() > 2) {
//						cnt.removeComponent(cnt.getComponentAt(2));
//					}
//					cnt.addComponent(image);
//					cnt.getComponentForm().revalidate();

//					findPhotoLb().setIcon(i);

					findPhotoLb().getComponentForm().revalidate();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

	}

	protected void postMediaPlayerForm(Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.postMediaPlayerForm(f);

		System.out.println("UIManager.getInstance().getThemeName(): " + UIManager.getInstance().getThemeName());
		InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/cowboy.mp4");

		try {
			media = MediaManager.createMedia(is, "video/mpeg4", null);
			media.setVolume(volume);
			media.play();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// protected void postMyAudioForm(Form f) {
	// // If the resource file changes the names of components this call will break notifying you that you should fix the code
	// super.postMyAudioForm(f);
	//
	// // f.addComponent(AudioBrowser.createDemo());
	// // f.revalidate();
	//
	// // Form hi = new Form("Hi World");
	// f.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
	// Button b = new Button("Open");
	// b.addActionListener(new ActionListener() {
	//
	// public void actionPerformed(ActionEvent evt) {
	// final Dialog d = new Dialog("Select a mp3");
	// d.setLayout(new BorderLayout());
	// FileTreeModel model = new FileTreeModel(true);
	// model.addExtensionFilter("mp3");
	// model.addExtensionFilter("wav");
	// // model.addExtensionFilter("mp4");
	//
	// FileTree t = new FileTree(model) {
	//
	// protected Button createNodeComponent(final Object node, int depth) {
	// if (node == null || !getModel().isLeaf(node)) {
	// return super.createNodeComponent(node, depth);
	// }
	// final Button b = super.createNodeComponent(node, depth);
	// b.addActionListener(new ActionListener() {
	//
	// public void actionPerformed(ActionEvent evt) {
	// d.dispose();
	// try {
	//
	// Media m = MediaManager.createMedia((String) node, true); // false (music)
	//
	// mp = new MediaPlayer(m);
	//
	// m.play();
	//
	// } catch (IOException ex) {
	// ex.printStackTrace();
	// }
	// }
	// });
	// return b;
	// }
	// };
	//
	// // d.addComponent(BorderLayout.NORTH, mp);
	// d.addComponent(BorderLayout.CENTER, t);
	//
	// d.placeButtonCommands(new Command[] { new Command("Cancel") });
	// d.showAtPosition(2, 2, 2, 2, true);
	//
	// }
	// });
	// f.addComponent(b);
	// f.addComponent(mp); // dika mou ayta!
	// f.revalidate();
	//
	// f.show();
	// }

	protected void onMyMultimediaForm_MultimediaListAction(Component c, ActionEvent event) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.onMyMultimediaForm_MultimediaListAction(c, event);

		Display.getInstance().vibrate(vibrationTime);
		
		System.out.println("findMultimediaList().getSelectedItem(): " + findMultimediaList().getSelectedItem());
		System.out.println("findMultimediaList().getSelectedIndex():" + findMultimediaList().getSelectedIndex());
		int selectedIndex = findMultimediaList().getSelectedIndex();

		if (selectedIndex == 0) {
//			Display.getInstance().vibrate(vibrationTime);
			showForm("My Images Form", null);
		} else if (selectedIndex == 1) {
//			Display.getInstance().vibrate(vibrationTime);
			showForm("My Audio Form", null);
		} else if (selectedIndex == 2) {
//			Display.getInstance().vibrate(vibrationTime);
			showForm("My Video Form", null);
		}
	}

	protected void onMyImagesForm_MyImagesMultiListAction(Component c, ActionEvent event) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.onMyImagesForm_MyImagesMultiListAction(c, event);

		System.out.println("findMyImagesMultiList().getSelectedItem(): " + findMyImagesMultiList().getSelectedItem());
		System.out.println("findMyImagesMultiList().getSelectedIndex():" + findMyImagesMultiList().getSelectedIndex());
		int selectedIndex = findMyImagesMultiList().getSelectedIndex();

		if (selectedIndex == 0) {
			Display.getInstance().vibrate(vibrationTime);
			showForm("Load Photo Form", null);
		} else if (selectedIndex == 1) {
			Display.getInstance().vibrate(vibrationTime);
			showForm("Image Capture", null);
		}
	}

	protected void onMyAudioForm_MyAudioMultiListAction(final Component c, ActionEvent event) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.onMyAudioForm_MyAudioMultiListAction(c, event);
		
//		String type, type2 = "";
//		
//		type = Display.getInstance().getMediaRecorderingMimeType();
//		System.out.println("type: " + type);
//		
//		String[] array = Display.getInstance().getAvailableRecordingMimeTypes();
//		for(int i=0; i<array.length; i++) {
//			type2 = type2 + "-" + array[i];
//			System.out.println("type2: " + type2);
//		}
//		
//		c.getComponentForm().setTitle(type + "----" + type2);
//		c.getComponentForm().revalidate();

		System.out.println("findMyAudioMultiList().getSelectedItem(): " + findMyAudioMultiList().getSelectedItem());
		System.out.println("findMyAudioMultiList().getSelectedIndex():" + findMyAudioMultiList().getSelectedIndex());
		int selectedIndex = findMyAudioMultiList().getSelectedIndex();

		if (selectedIndex == 0) {
			Display.getInstance().vibrate(vibrationTime);
			// showForm("Audio Listen", null);

			final Dialog d = new Dialog("select an audio file");
			d.setLayout(new BorderLayout());
			FileTreeModel model = new FileTreeModel(true);
//			model.addExtensionFilter("mp3"); // DEN PAIZOUN TA MP3
			model.addExtensionFilter("wav");
			// model.addExtensionFilter("mp4");

			FileTree t = new FileTree(model) {

				protected Button createNodeComponent(final Object node, int depth) {
					if (node == null || !getModel().isLeaf(node)) {
						return super.createNodeComponent(node, depth);
					}
					final Button b = super.createNodeComponent(node, depth);
					b.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent evt) {
							d.dispose();
							try {
								System.out.println("node: " + node);
								Display.getInstance().vibrate(vibrationTime);
								// audioMedia = MediaManager.createMedia((String) node, false); // true (video)

								audioMedia = MediaManager.createMedia((String) node, false, new Runnable() {
									public void run() {
										// findAudioPlayButton().setText("");
										// findAudioPlayButton().setEnabled(false);
										// findAudioLabel().setIcon(r.getImage("cassetteSteady.png"));
										// findAudioLabel().getComponentForm().revalidate();
										// findAudioPlayButton().getComponentForm().revalidate();
										showForm("My Audio Form", null);
									}
								});

								audioMedia.setVolume(volume);

								// audioMedia.play();
								showForm("Audio Listen", null);

							} catch (IOException ex) {
								Dialog.show("A_" + ex.toString(), ex.getMessage(), "ok", "cancel");
							}
						}
					});
					return b;
				}
			};
			d.addComponent(BorderLayout.CENTER, t);

			d.placeButtonCommands(new Command[] { new Command("cancel") });
			d.showAtPosition(2, 2, 2, 2, true);

		} else if (selectedIndex == 1) {
			Display.getInstance().vibrate(vibrationTime);
			// showForm("Audio Record", null);

			String debugTest = "1";
			c.getComponentForm().setTitle(debugTest);
			c.getComponentForm().revalidate();

//			final String value = Capture.captureAudio();
			
			
			
			
			
			
			
			// TODO: Testing with ActionListener
			
			Capture.captureAudio(new ActionListener() {

				public void actionPerformed(ActionEvent evt) {
					if (evt != null) {
						InputStream is = null;
						OutputStream o = null;
						try {
							audioPath = (String) evt.getSource();
							System.out.println("path " + audioPath);

							is = com.codename1.io.FileSystemStorage.getInstance().openInputStream(audioPath); // Me ayto leitourgouse
							// is = com.codename1.io.Storage.getInstance().createInputStream(photoPath);
							
//							System.out.println("type: " + Display.getInstance().getMediaRecorderingMimeType());
//							
//							String[] array = Display.getInstance().getAvailableRecordingMimeTypes();
//							for(int i=0; i<array.length; i++) {
//								System.out.println("type2: " + array[i]);
//							}
							
							
//							Media audio = MediaManager.createMedia(, Display.getInstance().getMediaRecorderingMimeType());
//							isGlobal = is;
//							i = EncodedImage.create(is);
//							i.setImageName(getDateTime());
//							currentImage2 = (EncodedImage) i;
//							i = i.scaledWidth(360);
//							l.setIcon(i);
							
//							Display

							// Afte loading the photo I clean the InputStream and recreate it in order to copy it
//							Util.cleanup(is);
//							stepDebug = 10;
//							is = com.codename1.io.FileSystemStorage.getInstance().openInputStream(audioPath);

							FileSystemStorage fs = FileSystemStorage.getInstance();

							String[] roots = fs.getRoots();
							stepDebug = 1;

							String allRoots = "";

							for (int i = 0; i < roots.length; i++) {
								allRoots = allRoots + "-" + roots[i];
							}
							stepDebug = 2;

							// is = com.codename1.io.Storage.getInstance().createInputStream(photoPath);
							fs.mkdir(roots[0] + "cloud4all"); // file:///E:/
							stepDebug = 30;
							char sep = fs.getFileSystemSeparator();
							stepDebug = 31;
							audioNameDebug = roots[0] + "cloud4all" + sep + getDateTime() + ".wav";
							stepDebug = 32;
							o = FileSystemStorage.getInstance().openOutputStream(audioNameDebug);
							stepDebug = 4;
							// DataOutputStream dos = new DataOutputStream(o);
							// Util.writeObject(i, dos);
							Util.copy(is, o);
							stepDebug = 5;

							// OutputStream o = Storage.getInstance().createOutputStream(name);
							// Util.copy(input, o);
							// Util.close(o);

							// // Saving the photo in a directory in order to have them permanently
							// stepDebug = 11;
							// Storage storage = Storage.getInstance();
							// stepDebug = 12;
							// final String homePath = storage.getAppHomePath();
							// stepDebug = 13;
							// homePathDebug = homePath;
							// System.out.println("homepath: " + homePathDebug);
							// stepDebug = 1;
							//
							// final char sep = fileSystemStorage.getFileSystemSeparator();
							// sepDebug = sep;
							// stepDebug = 2;
							// System.out.println("sepDebug: " + sepDebug);
							//
							// fileSystemStorage.mkdir(homePath + "cloud4all");
							// stepDebug = 3;
							// // System.out.println("homepath: " + homePathDebug);
							//
							// photoNameDebug = homePath + "cloud4all" + sep + currentImage2.getImageName()+".jpg";
							// OutputStream o = FileSystemStorage.getInstance().openOutputStream(photoNameDebug);
							// stepDebug = 4;
							// System.out.println("photoNameDebug: " + photoNameDebug);
							//
							// Util.copy(is, o);
							// stepDebug = 5;
							// // System.out.println("homepath: " + homePathDebug);
							//
							// o.close();
							// stepDebug = 6;
							// // System.out.println("homepath: " + homePathDebug);

							// FileSystemStorage fs = FileSystemStorage.getInstance();
							//
							// String[] roots = fs.getRoots();
							//
							// // if(!root.endsWith("/")) {
							// // root += "/";
							// // }
							// for(int i=0; i<roots.length; i++) {
							// System.out.println(roots[i]);
							// }
							//
							// // String fileName = root + "myFileName";
							//
							// // f.setTitle(photoPath);

							// // Saving the photo in a directory in order to have them permanently
							// stepDebug = 11;
							// FileSystemStorage fileSystemStorage = FileSystemStorage.getInstance();
							// stepDebug = 12;
							// final String homePath = fileSystemStorage.getAppHomePath();
							// stepDebug = 13;
							// homePathDebug = homePath;
							// System.out.println("homepath: " + homePathDebug);
							// stepDebug = 1;
							//
							// final char sep = fileSystemStorage.getFileSystemSeparator();
							// sepDebug = sep;
							// stepDebug = 2;
							// System.out.println("sepDebug: " + sepDebug);
							//
							// fileSystemStorage.mkdir(homePath + "cloud4all");
							// stepDebug = 3;
							// // System.out.println("homepath: " + homePathDebug);
							//
							// photoNameDebug = homePath + "cloud4all" + sep + currentImage2.getImageName()+".jpg";
							// OutputStream o = FileSystemStorage.getInstance().openOutputStream(photoNameDebug);
							// stepDebug = 4;
							// System.out.println("photoNameDebug: " + photoNameDebug);
							//
							// Util.copy(is, o);
							// stepDebug = 5;
							// // System.out.println("homepath: " + homePathDebug);
							//
							// o.close();
							// stepDebug = 6;
							// // System.out.println("homepath: " + homePathDebug);

						} catch (Exception ex) {
							Dialog.show("Error", "" + ex.getMessage() + "-step: " + stepDebug, "OK", null);
							ex.printStackTrace();
						} finally {
							Util.cleanup(is);
							Util.cleanup(o);
						}
					} else {
						showForm("My Audio Form", null);
					}
				}
			});
			
			
			
			
			
			
			
			
			
			
			

//			debugTest = "2";
//			c.getComponentForm().setTitle(debugTest);
//			c.getComponentForm().revalidate();

//			if (value != null) {
//
//				debugTest = "3";
//				c.getComponentForm().setTitle(debugTest);
//				c.getComponentForm().revalidate();
//
//				final Button playCapturedAudio = new Button("listen to recorded audio");
//
//				debugTest = "4";
//				c.getComponentForm().setTitle(debugTest);
//				c.getComponentForm().revalidate();
//
//				c.getComponentForm().addComponent(playCapturedAudio);
//
//				debugTest = "5";
//				c.getComponentForm().setTitle(debugTest);
//				c.getComponentForm().revalidate();
//
//				c.getComponentForm().revalidate();
//
//				debugTest = "6";
//				c.getComponentForm().setTitle(debugTest);
//				c.getComponentForm().revalidate();
//
//				playCapturedAudio.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent evt) {
//						try {
//
//							Media m = MediaManager.createMedia(value, false);
//							m.setVolume(volume);
//							m.play();
//
//							c.getComponentForm().revalidate();
//							c.getComponentForm().show();
//
//						} catch (IOException ex) {
//							// Log.e(ex);
//							Dialog.show("Error", "" + ex.getMessage(), "OK", null);
//						}
//					}
//				});
//			}
		}
	}

	protected void onMyVideoForm_MyVideoMultiListAction(Component c, ActionEvent event) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.onMyVideoForm_MyVideoMultiListAction(c, event);

		System.out.println("findMyVideoMultiList().getSelectedItem(): " + findMyVideoMultiList().getSelectedItem());
		System.out.println("findMyVideoMultiList().getSelectedIndex():" + findMyVideoMultiList().getSelectedIndex());
		int selectedIndex = findMyVideoMultiList().getSelectedIndex();

		if (selectedIndex == 0) {
			// Display.getInstance().vibrate(vibrationTime);
			// showForm("Video View", null);

			Display.getInstance().vibrate(vibrationTime);
			// showForm("Audio Listen", null);

			final Dialog d = new Dialog("Select a video file");
			d.setLayout(new BorderLayout());
			FileTreeModel model = new FileTreeModel(true);
			model.addExtensionFilter("mp4");
			model.addExtensionFilter("3gp");

			FileTree t = new FileTree(model) {

				protected Button createNodeComponent(final Object node, int depth) {
					if (node == null || !getModel().isLeaf(node)) {
						return super.createNodeComponent(node, depth);
					}
					final Button b = super.createNodeComponent(node, depth);
					b.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent evt) {
							d.dispose();
							try {
								// videoMedia = MediaManager.createMedia((String) node, true); // false (audio)
								Display.getInstance().vibrate(vibrationTime);
								
								videoMedia = MediaManager.createMedia((String) node, true, new Runnable() {
									public void run() {
										// findAudioPlayButton().setText("");
										// findAudioPlayButton().setEnabled(false);
										// findAudioLabel().setIcon(r.getImage("cassetteSteady.png"));
										// findAudioLabel().getComponentForm().revalidate();
										// findAudioPlayButton().getComponentForm().revalidate();
										showForm("My Video Form", null);
									}
								});

								videoMedia.setVolume(volume);

								showForm("Video View", null);

							} catch (Exception ex) {// (IOException ex) {
								Dialog.show(
										"A_" + ex.toString(),
										"form:" + showForm("Video View", null) + "videoMedia:" + videoMedia + "_OLE_" + "node:" + node + "_OLE_" + ex.getMessage() + "_OLE_"
												+ ex.toString(), "ok", "cancel");
							}
						}
					});
					return b;
				}
			};
			d.revalidate();

			d.addComponent(BorderLayout.CENTER, t);

			d.placeButtonCommands(new Command[] { new Command("cancel") });
			d.showAtPosition(2, 2, 2, 2, true);

		} else if (selectedIndex == 1) { // TODO: TO BE COMMENTED OUT WHEN THE VIDEO COMPONENT WILL BE WORKING PROPERLY
			// Display.getInstance().vibrate(vibrationTime);
			// showForm("Video Capture", null);
		}
	}

	protected void postImageCapture(final Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.postImageCapture(f);

		// final Label l = findCaptureLb(f);
		// l.getStyle().setAlignment(Component.CENTER);
		//
		// Image im = l.getStyle().getBgImage();
		// if (im != null) {
		// im.dispose();
		// }
		// l.getStyle().setBgImage(null);
		//
		// Capture.capturePhoto(new ActionListener() {
		//
		// public void actionPerformed(ActionEvent evt) {
		// InputStream is = null;
		// try {
		// photoPath = (String) evt.getSource();
		// System.out.println("path " + photoPath);
		//
		// is = com.codename1.io.FileSystemStorage.getInstance().openInputStream(photoPath);
		//
		// //DEBUG
		// f.setTitle(photoPath);
		//
		// isGlobal = is;
		// i = EncodedImage.create(is);
		// i.setImageName(getDateTime());
		// currentImage2 = (EncodedImage) i;
		// i = i.scaledWidth(360);
		// l.setIcon(i);
		// f.revalidate();
		// } catch (Exception ex) {
		// ex.printStackTrace();
		// } finally {
		// try {
		// is.close();
		// } catch (IOException ex) {
		// ex.printStackTrace();
		// }
		// }
		// }
		// });
		//
		// // Saving the captured photo
		// if (currentImage2 instanceof EncodedImage) {
		// photos.addPhoto((EncodedImage) currentImage2);
		// System.out.println("photos.getPhotos().length: " + photos.getPhotos().size());
		// }
		//
		// Storage.getInstance().writeObject("SavedPhoto", photos);
		// f.show();

		// DOKIMH GIA FILESYSTEM
		final Label l = findCaptureLb(f);
		l.getStyle().setAlignment(Component.CENTER);

		Image im = l.getStyle().getBgImage();
		if (im != null) {
			im.dispose();
		}
		l.getStyle().setBgImage(null);

		Capture.capturePhoto(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				if (evt != null) {
					InputStream is = null;
					OutputStream o = null;
					try {
						photoPath = (String) evt.getSource();
						System.out.println("path " + photoPath);

						is = com.codename1.io.FileSystemStorage.getInstance().openInputStream(photoPath); // Me ayto leitourgouse
						// is = com.codename1.io.Storage.getInstance().createInputStream(photoPath);

						isGlobal = is;
						i = EncodedImage.create(is);
						i.setImageName(getDateTime());
						currentImage2 = (EncodedImage) i;
						i = i.scaledWidth(360);
						l.setIcon(i);

						// Afte loading the photo I clean the InputStream and recreate it in order to copy it
						Util.cleanup(is);
						is = com.codename1.io.FileSystemStorage.getInstance().openInputStream(photoPath);

						FileSystemStorage fs = FileSystemStorage.getInstance();

						String[] roots = fs.getRoots();
						stepDebug = 1;

						String allRoots = "";

						for (int i = 0; i < roots.length; i++) {
							allRoots = allRoots + "-" + roots[i];
						}
						stepDebug = 2;

						// is = com.codename1.io.Storage.getInstance().createInputStream(photoPath);
						fs.mkdir(roots[0] + "cloud4all"); // file:///E:/
						stepDebug = 30;
						char sep = fs.getFileSystemSeparator();
						stepDebug = 31;
						photoNameDebug = roots[0] + "cloud4all" + sep + currentImage2.getImageName() + ".jpg";
						stepDebug = 32;
						o = FileSystemStorage.getInstance().openOutputStream(photoNameDebug);
						stepDebug = 4;
						// DataOutputStream dos = new DataOutputStream(o);
						// Util.writeObject(i, dos);
						Util.copy(is, o);
						stepDebug = 5;

						// OutputStream o = Storage.getInstance().createOutputStream(name);
						// Util.copy(input, o);
						// Util.close(o);

						// // Saving the photo in a directory in order to have them permanently
						// stepDebug = 11;
						// Storage storage = Storage.getInstance();
						// stepDebug = 12;
						// final String homePath = storage.getAppHomePath();
						// stepDebug = 13;
						// homePathDebug = homePath;
						// System.out.println("homepath: " + homePathDebug);
						// stepDebug = 1;
						//
						// final char sep = fileSystemStorage.getFileSystemSeparator();
						// sepDebug = sep;
						// stepDebug = 2;
						// System.out.println("sepDebug: " + sepDebug);
						//
						// fileSystemStorage.mkdir(homePath + "cloud4all");
						// stepDebug = 3;
						// // System.out.println("homepath: " + homePathDebug);
						//
						// photoNameDebug = homePath + "cloud4all" + sep + currentImage2.getImageName()+".jpg";
						// OutputStream o = FileSystemStorage.getInstance().openOutputStream(photoNameDebug);
						// stepDebug = 4;
						// System.out.println("photoNameDebug: " + photoNameDebug);
						//
						// Util.copy(is, o);
						// stepDebug = 5;
						// // System.out.println("homepath: " + homePathDebug);
						//
						// o.close();
						// stepDebug = 6;
						// // System.out.println("homepath: " + homePathDebug);

						// FileSystemStorage fs = FileSystemStorage.getInstance();
						//
						// String[] roots = fs.getRoots();
						//
						// // if(!root.endsWith("/")) {
						// // root += "/";
						// // }
						// for(int i=0; i<roots.length; i++) {
						// System.out.println(roots[i]);
						// }
						//
						// // String fileName = root + "myFileName";
						//
						// // f.setTitle(photoPath);

						// // Saving the photo in a directory in order to have them permanently
						// stepDebug = 11;
						// FileSystemStorage fileSystemStorage = FileSystemStorage.getInstance();
						// stepDebug = 12;
						// final String homePath = fileSystemStorage.getAppHomePath();
						// stepDebug = 13;
						// homePathDebug = homePath;
						// System.out.println("homepath: " + homePathDebug);
						// stepDebug = 1;
						//
						// final char sep = fileSystemStorage.getFileSystemSeparator();
						// sepDebug = sep;
						// stepDebug = 2;
						// System.out.println("sepDebug: " + sepDebug);
						//
						// fileSystemStorage.mkdir(homePath + "cloud4all");
						// stepDebug = 3;
						// // System.out.println("homepath: " + homePathDebug);
						//
						// photoNameDebug = homePath + "cloud4all" + sep + currentImage2.getImageName()+".jpg";
						// OutputStream o = FileSystemStorage.getInstance().openOutputStream(photoNameDebug);
						// stepDebug = 4;
						// System.out.println("photoNameDebug: " + photoNameDebug);
						//
						// Util.copy(is, o);
						// stepDebug = 5;
						// // System.out.println("homepath: " + homePathDebug);
						//
						// o.close();
						// stepDebug = 6;
						// // System.out.println("homepath: " + homePathDebug);

					} catch (Exception ex) {
						Dialog.show("Error", "" + ex.getMessage() + "-step: " + stepDebug, "OK", null);
						ex.printStackTrace();
					} finally {
						Util.cleanup(is);
						Util.cleanup(o);
					}
				} else {
					showForm("My Images Form", null);
				}
			}
		});

		// EPAIZE AYTO
		// // Saving the captured photo
		// if (currentImage2 instanceof EncodedImage) {
		// photos.addPhoto((EncodedImage) currentImage2);
		// System.out.println("photos.getPhotos().length: " + photos.getPhotos().size());
		// }
		//
		// Storage.getInstance().writeObject("SavedPhoto", photos);

		f.revalidate();
	}

	protected boolean onImageCaptureCapture() {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		boolean val = super.onImageCaptureCapture();
		
		Display.getInstance().vibrate(vibrationTime);
		
		final Form f = Display.getInstance().getCurrent();// (Form)findByName("Storage Form", null);
		System.out.println("FORM NAME: " + f.getName());

		final Label l = findCaptureLb(f);
		l.getStyle().setAlignment(Component.CENTER);

		Image im = l.getStyle().getBgImage();
		if (im != null) {
			im.dispose();
		}
		l.getStyle().setBgImage(null);

		Capture.capturePhoto(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				if (evt != null) {
					InputStream is = null;
					try {
						photoPath = (String) evt.getSource();
						System.out.println("path " + photoPath);

						is = com.codename1.io.FileSystemStorage.getInstance().openInputStream(photoPath);
						isGlobal = is;
						i = EncodedImage.create(is);
						i.setImageName(getDateTime());
						currentImage2 = (EncodedImage) i;
						i = i.scaledWidth(360);
						l.setIcon(i);
						f.revalidate();

						// // Saving the photo in a directory in order to have them permanently
						// FileSystemStorage fileSystemStorage = FileSystemStorage.getInstance();
						// final String homePath = fileSystemStorage.getAppHomePath();
						// homePathDebug = homePath;
						// System.out.println("homepath: " + homePathDebug);
						// stepDebug = 1;
						//
						// final char sep = fileSystemStorage.getFileSystemSeparator();
						// sepDebug = sep;
						// stepDebug = 2;
						// System.out.println("sepDebug: " + sepDebug);
						//
						// fileSystemStorage.mkdir(homePath + "cloud4all");
						// stepDebug = 3;
						// // System.out.println("homepath: " + homePathDebug);
						//
						// photoNameDebug = homePath + "cloud4all" + sep + currentImage2.getImageName()+".jpg";
						// OutputStream o = FileSystemStorage.getInstance().openOutputStream(photoNameDebug);
						// stepDebug = 4;
						// System.out.println("photoNameDebug: " + photoNameDebug);
						//
						// Util.copy(is, o);
						// stepDebug = 5;
						// // System.out.println("homepath: " + homePathDebug);
						//
						// o.close();
						// stepDebug = 6;
						// // System.out.println("homepath: " + homePathDebug);

					} catch (Exception ex) {
						ex.printStackTrace();
						Dialog.show("Error", "" + ex.getMessage(), "OK", null);
					} finally {
						try {
							is.close();
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
				} else {
					showForm("My Images Form", null);
				}
			}
		});

		// YPHRXE
		// // Saving the captured photo
		// if (currentImage2 instanceof EncodedImage) {
		// photos.addPhoto((EncodedImage) currentImage2);
		// System.out.println("photos.getPhotos().length: " + photos.getPhotos().size());
		// }
		//
		// Storage.getInstance().writeObject("SavedPhoto", photos);
		//
		// f.show();

		return val;
	}

	protected void exitImageCapture(Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.exitImageCapture(f);
		
		// TODO: DEN XERV AN XREIAZETAI!
		// Saving the captured photo
//		if (currentImage2 instanceof EncodedImage) {
//			photos.addPhoto((EncodedImage) currentImage2);
//			System.out.println("photos.getPhotos().length: " + photos.getPhotos().size());
//		}
//		Storage.getInstance().writeObject("SavedPhoto", photos);

		// // MHPWS DEN SWZEI THN TELEYTAIA PHOTO?
		// showForm("My Images Form", null);
	}

	protected void postVideoView(Form f) {
		super.postVideoView(f);

		f.addComponent(BorderLayout.CENTER, videoMedia.getVideoComponent());
		// Button b = new Button(new Command("play") {
		// public void actionPerformed(ActionEvent evt) {
		// videoMedia.play();
		// videoMedia.getVideoComponent().getComponentForm().revalidate();
		// videoMedia.getVideoComponent().getComponentForm().show();
		// }
		// });

		f.revalidate();
		f.show();
	}

	protected void postVideoCapture(final Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.postVideoCapture(f);

		// Xalia poiothta Video kai otan pataw gia na stamathsei na katagrafei to video
		// MediaPlayer exception (An internal application error occurred:
		// java.lang.IllegalStateException: Player is CLOSED.)
		// final String value = Capture.captureVideo();
		// if (value != null) {
		// Button playCapturedVideo = new Button("View captured video");
		// f.addComponent(playCapturedVideo);
		// f.revalidate();
		// playCapturedVideo.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent evt) {
		// try {
		// Media m = MediaManager.createMedia(value, true);
		// m.play();
		// } catch (IOException ex) {
		// // Log.e(ex);
		// Dialog.show("Error", "" + ex, "OK", null);
		// }
		// }
		// });
		// }

		Capture.captureVideo(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					String videoPath = (String) evt.getSource();
					System.out.println("videoPath: " + videoPath);

					findVideoLb().setText(videoPath);

					f.revalidate();

					// Media m = MediaManager.createMedia(videoPath, true);
					// m.play();
					// f.show();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

	}

	protected boolean onVideoViewBack() {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		boolean val = super.onVideoViewBack();

		Display.getInstance().vibrate(vibrationTime);
		videoMedia.cleanup();

		return val;
	}

	protected boolean onAudioListenBack() {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		boolean val = super.onAudioListenBack();

		Display.getInstance().vibrate(vibrationTime);
		audioMedia.cleanup();

		return val;
	}

	protected void postAudioListen(final Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.postAudioListen(f);

		audioMedia.setVolume(volume);

		// Display.getInstance().callSerially(new Runnable()
		// {
		// public void run(){
		// while (true) {
		// if(!audioMedia.isPlaying()){
		// findAudioLabel().setIcon(r.getImage("cassetteSteady.png"));
		// findAudioPlayButton().setText("");
		// return;
		// }
		// f.revalidate();
		// }
		// }
		// });

		// audioMedia.play();
	}

	protected boolean onContactFormBack() {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		boolean val = super.onContactFormBack();

		// Set selectedFont
		setSelectedFont(selectedFont);

		// Set selectedLanguage
		System.out.println("AAAAAAAAA: " + selectedLanguage);
		setSelectedLanguage(selectedLanguage);

		// Set selectedTheme
		setSelectedTheme(selectedTheme);

		UIManager.getInstance().setThemeProps(r.getTheme(selectedTheme));
		getFontFromName(selectedFont);

		Display.getInstance().getCurrent().refreshTheme();

		return val;
	}

	protected void beforeContactForm(Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.beforeContactForm(f);

		System.out.println("!!!!!! selectedFont: " + selectedFont);

		UIManager.getInstance().setThemeProps(r.getTheme(selectedTheme));
		getFontFromName(selectedFont);

		Display.getInstance().getCurrent().refreshTheme();

		f.setTitle(displayContact.getFamilyName());

		// Label photoLabel = (Label)findByName("photoLb", null);
		findPhotoLb().getStyle().setAlignment(Component.CENTER);
		findPhotoLb().getStyle().setMargin(0, 80, 0, 0);
		findNameField().setText(displayContact.getDisplayName());
		findSurnameField().setText(displayContact.getFamilyName());
		findPhoneField().setText(displayContact.getId());

		f.revalidate();
	}

	protected void postMain(Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.postMain(f);

		UIManager.getInstance().setThemeProps(r.getTheme(selectedTheme));
		getFontFromName(selectedFont);
		Display.getInstance().getCurrent().refreshTheme();
	}

	protected void onSettings_SoundYesRadioButtonAction(Component c, ActionEvent event) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.onSettings_SoundYesRadioButtonAction(c, event);

		Display.getInstance().vibrate(vibrationTime);
		if (((RadioButton) c).isSelected()) {
			findVolumeSlider().setProgress(volume);
			findVolumeSlider().setVisible(true);
		} else {
			findVolumeSlider().setProgress(volume);
			findVolumeSlider().setVisible(false);
		}
		c.getComponentForm().revalidate();
	}

	protected void onSettings_SoundNoRadioButtonAction(Component c, ActionEvent event) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.onSettings_SoundNoRadioButtonAction(c, event);

		Display.getInstance().vibrate(vibrationTime);
		if (((RadioButton) c).isSelected()) {
			volume = 0;
			findVolumeSlider().setProgress(volume);
			findVolumeSlider().setVisible(false);
		} else {
			findVolumeSlider().setProgress(volume);
			findVolumeSlider().setVisible(true);
		}
		c.getComponentForm().revalidate();
	}

	// protected void onSettings_TestButtonAction(Component c, ActionEvent event) {
	// // If the resource file changes the names of components this call will break notifying you that you should fix the code
	// super.onSettings_TestButtonAction(c, event);
	//
	// // c.getComponentForm().getStyle().setBgColor(0xFFFFFF, true);
	// // c.getComponentForm().getStyle().setBgImage(null, true);
	// // c.getComponentForm().revalidate();
	//
	// InfiniteProgress ip = new InfiniteProgress();
	//
	// Dialog d = new Dialog();
	// d.setDialogUIID("Container");
	// d.setLayout(new BorderLayout());
	// Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
	// cnt.addComponent(new Label("Loading..."));
	// cnt.addComponent(ip);
	// d.addComponent(BorderLayout.CENTER, cnt);
	// d.setTransitionInAnimator(CommonTransitions.createEmpty());
	// d.setTransitionOutAnimator(CommonTransitions.createEmpty());
	// d.showPacked(BorderLayout.CENTER, false);
	//
	// // request.setDisposeOnCompletion(d);
	// }

	protected void postWebBrowserForm(Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.postWebBrowserForm(f);

		System.out.println("URL: " + url);

		if (url.equals("")) {
			// System.out.println("1");
			findWebBrowser().setURL("http://www.yahoo.com");
			// System.out.println(url);
		} else {
			// System.out.println("2");
			findWebBrowser().setURL(url);
			// System.out.println(url);
		}

		// f.removeComponent(findWebBrowser());

		f.revalidate();
	}

	protected boolean onURLDialogVisit() {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		boolean val = super.onURLDialogVisit();

		Display.getInstance().vibrate(vibrationTime);
		
		String manualUrl = findUrlTextField().getText();
		System.out.println("a: " + manualUrl);
		if (manualUrl.equals("")) {
			// System.out.println("3");
			// findWebBrowser().setURL(url);
		} else {
			// System.out.println("4");
			// findWebBrowser().setURL(manualUrl);
			url = manualUrl;
		}

		// findWebBrowser().getComponentForm().revalidate();

		return val;
	}

	protected void onVideoView_ButtonAction(Component c, ActionEvent event) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.onVideoView_ButtonAction(c, event);

		if (videoMedia != null) {
			try {
				if (videoMedia.isPlaying()) { // EDV NA ALLAZV KAI ONOMA STO KOUMPI KATHE FORA
					((Button) c).setText("play");
					videoMedia.pause();
				} else {
					((Button) c).setText("pause");
					videoMedia.setVolume(volume);
					videoMedia.play();
				}

				videoMedia.getVideoComponent().getComponentForm().revalidate();
				videoMedia.getVideoComponent().getComponentForm().show();

			} catch (Exception e) {
				Dialog.show("B_" + e.toString(), "videoMedia:" + videoMedia + "videoMedia.getVideoComponent:" + videoMedia.getVideoComponent() + "_OLE_" + e.getMessage() + "_OLE_"
						+ "videoMedia.getVideoComponent().getComponentForm(): " + videoMedia.getVideoComponent().getComponentForm() + "_OLE_" + e.toString(), "ok", "cancel");
			}
		}
	}

	protected void postURLDialog(Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.postURLDialog(f);
		//
		// String url1 = "http://www.google.co.uk";
		// String url2 = "http://www.wikipedia.org";
		// String url3 = "http://www.linkedin.com";
		// String url4 = "http://www.facebook.com";
		//
		// url = url1;
		//
		// findUrlComboBox().addItem(url1);
		// findUrlComboBox().addItem(url2);
		// findUrlComboBox().addItem(url3);
		// findUrlComboBox().addItem(url4);
		//
		// f.revalidate();
		// System.out.println("b: " + findUrlComboBox());
		// System.out.println("a: "+ findUrlComboBox().getModel());

	}

	protected void onURLDialog_UrlComboBoxAction(Component c, ActionEvent event) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.onURLDialog_UrlComboBoxAction(c, event);

		Display.getInstance().vibrate(vibrationTime);
		// url = (((ComboBox)c).getSelectedItem().toString());
		url = (String) ((Hashtable) (((ComboBox) c).getSelectedItem())).get("url");
		// System.out.println("obj: " + obj);
	}

	protected void beforeURLDialog(Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.beforeURLDialog(f);

		System.out.println("b: " + findUrlComboBox());
		System.out.println("a: " + findUrlComboBox().getModel());

		m = ((DefaultListModel) findUrlComboBox().getModel());

		Hashtable content1 = new Hashtable();
		Image i1 = UIManager.getInstance().getThemeImageConstant("searchYahooImage");
		content1.put("name", i1);
		content1.put("url", "http://www.yahoo.co.uk");
		// content1.put("name", "yahoo");
		Hashtable content2 = new Hashtable();
		Image i2 = UIManager.getInstance().getThemeImageConstant("searchGoogleImage");
		content2.put("name", i2);
		content2.put("url", "http://www.google.co.uk");
		// content2.put("name", "google");

		Hashtable content3 = new Hashtable();
		Image i3 = UIManager.getInstance().getThemeImageConstant("searchBingImage");
		content3.put("name", i3);
		content3.put("url", "http://www.bing.com");

		Hashtable content4 = new Hashtable();
		Image i4 = UIManager.getInstance().getThemeImageConstant("searchDuckduckgoImage");
		content4.put("name", i4);
		content4.put("url", "https://duckduckgo.com");

		Hashtable content5 = new Hashtable();
		Image i5 = UIManager.getInstance().getThemeImageConstant("searchBlekkoImage");
		content5.put("name", i5);
		content5.put("url", "http://www.blekko.com");

		m.addItem(content1);
		m.addItem(content2);
		m.addItem(content3);
		m.addItem(content4);
		m.addItem(content5);

		findUrlComboBox().setModel(m);
	}

	// protected boolean onMyAudioFormBack() {
	// // If the resource file changes the names of components this call will break notifying you that you should fix the code
	// boolean val = super.onMyAudioFormBack();
	//
	// if (audioMedia != null) {
	// if (audioMedia.isPlaying()) {
	// audioMedia.cleanup();
	// }
	// }
	// return val;
	// }

	// protected void postMyPositionOnMapForm(Form f) {
	// // If the resource file changes the names of components this call will break notifying you that you should fix the code
	// super.postMyPositionOnMapForm(f);
	//
	// // Form map = new Form("Map");
	// // map.setLayout(new BorderLayout());
	// // map.setScrollable(false);
	// final MapComponent mc = new MapComponent(new GoogleMapsProvider("AIzaSyA0R1Bq4_ldMQQVKcBv3fq1dakPfmpUcVU"));
	//
	// // putMeOnMap(mc); // LEITOURGEI
	// mc.zoomToLayers();
	//
	// f.addComponent(BorderLayout.CENTER, mc);
	// // f.addCommand(new BackCommand());
	// // f.setBackCommand(new BackCommand());
	// f.show();
	// }

	protected void onMapsProviders_ProvidersMultiListAction(Component c, ActionEvent event) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.onMapsProviders_ProvidersMultiListAction(c, event);

		int selectedIndex = findProvidersMultiList().getSelectedIndex();

		// Google
		if (selectedIndex == 0) {
			Display.getInstance().vibrate(vibrationTime);
			showForm("My Google Position Form", null);
		} else if (selectedIndex == 1) {
			Display.getInstance().vibrate(vibrationTime);
			showForm("My Position Form", null);
		}
	}

	private void startGps() {

		l = LocationManager.getLocationManager();

		l.setLocationListener(new LocationListener() {
			public void locationUpdated(final Location location) {
				Display.getInstance().invokeAndBlock(new Runnable() {
					public void run() {
						lastLocation = new Coord(location.getLatitude(), location.getLongitude());
						System.out.println("location.getLatitude(): " + location.getLatitude());
						System.out.println("location.getLongitude(): " + location.getLongitude());
						longitudeStr = "" + location.getLongitude();
						latitudeStr = "" + location.getLatitude();
					}
				});
				stopGps();
			}

			public void providerStateChanged(int newState) {
				System.out.println("providerStateChanged");
			}

			public void stopGps() {
				l.setLocationListener(null);
			}
		});
	}

	private void startGpsAndRefreshUI(final MapComponent map) {

		l = LocationManager.getLocationManager();

		l.setLocationListener(new LocationListener() {
			public void locationUpdated(final Location location) {
				Display.getInstance().invokeAndBlock(new Runnable() {
					public void run() {
						lastLocation = new Coord(location.getLatitude(), location.getLongitude());
						System.out.println("location.getLatitude(): " + location.getLatitude());
						System.out.println("location.getLongitude(): " + location.getLongitude());
						longitudeStr = "" + location.getLongitude();
						latitudeStr = "" + location.getLatitude();
					}
				});

				Display.getInstance().callSerially(new Runnable() {
					public void run() {

						// if(dlg.isVisible()) {
						// dlg.dispose();
						// }

						Image i = null;
						try {
							i = Image.createImage("/blue_pin.png");
						} catch (IOException e) {
							e.printStackTrace();
							Dialog.show("1_" + e.toString(), e.getMessage(), "ok", "cancel");
						}

						PointsLayer pl = new PointsLayer();
						pl.setPointIcon(i);
						PointLayer p = new PointLayer(lastLocation, "You Are Here", i);

						p.setDisplayName(true);
						pl.addPoint(p);

						map.addLayer(pl);
						map.zoomToLayers();
						map.revalidate();

						longitudeLb.setText("X2: " + longitudeStr);
						latitudeLb.setText("Y2: " + latitudeStr);
						longitudeLb.getComponentForm().revalidate();
					}
				});

				longitudeLb.setText("X2: " + longitudeStr);
				latitudeLb.setText("Y2: " + latitudeStr);
				longitudeLb.getComponentForm().revalidate();
				stopGps();
			}

			public void providerStateChanged(int newState) {
				// if(Display.getInstance().getCurrent() == dlg) {
				// dlg.dispose();
				// }
				System.out.println("providerStateChanged");
			}

			public void stopGps() {
				l.setLocationListener(null);
			}
		});
	}

	protected void onFindYourPositionForm_PositionMultiListAction(Component c, ActionEvent event) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.onFindYourPositionForm_PositionMultiListAction(c, event);

		int selectedIndex = findPositionMultiList().getSelectedIndex();

		if (selectedIndex == 0) {
			Display.getInstance().vibrate(vibrationTime);
			showForm("Gps Form", null);
		} else if (selectedIndex == 1) {
			Display.getInstance().vibrate(vibrationTime);
			showForm("Insert Position Manually Form", null);
		} else if (selectedIndex == 2) {
			Display.getInstance().vibrate(vibrationTime);
			showForm("Insert Address Form", null);
		}
	}

	protected void postGpsForm(final Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.postGpsForm(f);
		f.revalidate();

		System.out.println("1.Display.getInstance().isEdt():" + Display.getInstance().isEdt());

		// final InfiniteProgress ip = new InfiniteProgress();
		// ip.setAnimation(r.getImage("Arrow.png"));
		// final Dialog dlg = ip.showInifiniteBlocking();

		final Dialog dlg = new Dialog();
		InfiniteProgress prog = new InfiniteProgress();
		prog.setAnimation(r.getImage("waiting_4.png"));
		// Dimension dim = new Dimension(60, 60);

		dlg.setLayout(new BorderLayout());
		dlg.addComponent(BorderLayout.CENTER, prog);
		// dlg.setPreferredSize(dim);
		dlg.showPacked(BorderLayout.CENTER, false);
		// dlg.addComponent(BorderLayout.CENTER, new Label("Loading.."));
		// dlg.showModeless();

		l = LocationManager.getLocationManager();

		l.setLocationListener(new LocationListener() {
			public void locationUpdated(final Location location) {
				System.out.println("3.Display.getInstance().isEdt():" + Display.getInstance().isEdt());
				lastLocation = new Coord(location.getLatitude(), location.getLongitude());
				System.out.println("location.getLatitude(): " + location.getLatitude());
				System.out.println("location.getLongitude(): " + location.getLongitude());
				longitudeStr = "" + location.getLongitude();
				latitudeStr = "" + location.getLatitude();

				stopGps();
				dlg.dispose();
				System.out.println("2.Display.getInstance().isEdt():" + Display.getInstance().isEdt());

				Display.getInstance().callSerially(new Runnable() {
					public void run() {
						findGpsLongitudeLabel().setText("X: " + longitudeStr);
						findGpsLatitudeLabel().setText("Y: " + latitudeStr);
						f.revalidate();
					}
				});
			}

			public void providerStateChanged(int newState) {
				System.out.println("providerStateChanged");
			}

			public void stopGps() {
				l.setLocationListener(null);
			}
		});
		// }

	}

	protected void postMyGooglePositionForm(Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.postMyGooglePositionForm(f);

		MapComponent map = new MapComponent(new GoogleMapsProvider("AIzaSyA0R1Bq4_ldMQQVKcBv3fq1dakPfmpUcVU"));
		f.addComponent(BorderLayout.CENTER, map);

		Image i = null;
		try {
			i = Image.createImage("/blue_pin.png");
		} catch (IOException e) {
			e.printStackTrace();
			Dialog.show("1_" + e.toString(), e.getMessage(), "ok", "cancel");
		}

		PointsLayer pl = new PointsLayer();
		pl.setPointIcon(i);
		PointLayer p = new PointLayer(lastLocation, "You Are Here", i);

		p.setDisplayName(true);
		pl.addPoint(p);

		map.addLayer(pl);
		map.zoomToLayers();
		map.revalidate();

		f.revalidate();
	}

	protected void postMyPositionForm(Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.postMyPositionForm(f);

		MapComponent map = new MapComponent();
		f.addComponent(BorderLayout.CENTER, map);

		Image i = null;
		try {
			i = Image.createImage("/blue_pin.png");
		} catch (IOException e) {
			e.printStackTrace();
			Dialog.show("1_" + e.toString(), e.getMessage(), "ok", "cancel");
		}

		PointsLayer pl = new PointsLayer();
		pl.setPointIcon(i);
		PointLayer p = new PointLayer(lastLocation, "You Are Here", i);

		p.setDisplayName(true);
		pl.addPoint(p);

		map.addLayer(pl);
		map.zoomToLayers();
		map.revalidate();

		f.revalidate();

		// latitudeLb = new Label("Y1:");
		// longitudeLb = new Label("X1:");
		// Container coordsCon = new Container();
		// coordsCon.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		// coordsCon.addComponent(longitudeLb);
		// coordsCon.addComponent(latitudeLb);
		// f.addComponent(BorderLayout.NORTH, coordsCon);
		// f.revalidate();
		//
		// startGpsAndRefreshUI(map);
		// f.revalidate();
	}

	protected void postInsertPositionManuallyForm(Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.postInsertPositionManuallyForm(f);

	}

	protected void exitSettings(Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.exitSettings(f);

		if (findVolumeSlider().isVisible()) {
			volume = findVolumeSlider().getProgress();
			System.out.println("volume: " + volume);
		}
	}

	protected boolean initListModelMultiList(List cmp) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.initListModelMultiList(cmp);

		Vector<Hashtable> items = new Vector<Hashtable>();

		Hashtable item1 = new Hashtable();
		item1.put("Line1", "contacts");
		item1.put("icon", UIManager.getInstance().getThemeImageConstant("contactsImage"));
		item1.put("emblem", r.getImage("Arrow.png"));

		Hashtable item2 = new Hashtable();
		item2.put("Line1", "share");
		item2.put("icon", UIManager.getInstance().getThemeImageConstant("shareImage"));
		item2.put("emblem", r.getImage("Arrow.png"));

		Hashtable item3 = new Hashtable();
		item3.put("Line1", "multimedia");
		item3.put("icon", UIManager.getInstance().getThemeImageConstant("multimediaImage"));
		item3.put("emblem", r.getImage("Arrow.png"));

		Hashtable item4 = new Hashtable();
		item4.put("Line1", "maps");
		item4.put("icon", UIManager.getInstance().getThemeImageConstant("mapsImage"));
		item4.put("emblem", r.getImage("Arrow.png"));

		Hashtable item5 = new Hashtable();
		item5.put("Line1", "web browser");
		item5.put("icon", UIManager.getInstance().getThemeImageConstant("webBrowserImage"));
		item5.put("emblem", r.getImage("Arrow.png"));

		items.add(item1);
		items.add(item2);
		items.add(item3);
		items.add(item4);
		items.add(item5);

		ListModel model = new DefaultListModel(items);
		cmp.setModel(model);

		return true;
	}

	protected void onProximityProvidersForm_ProximityProvidersMultiListAction(Component c, ActionEvent event) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.onProximityProvidersForm_ProximityProvidersMultiListAction(c, event);

		int selectedIndex = findProximityProvidersMultiList().getSelectedIndex();

		// Google
		if (selectedIndex == 0) {
			Display.getInstance().vibrate(vibrationTime);
			showForm("Proximity Google Form", null);
		} // Open Street Maps
		else if (selectedIndex == 1) {
			Display.getInstance().vibrate(vibrationTime);
			showForm("Proximity Form", null);
		}
	}

	protected void postProximityGoogleForm(Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.postProximityGoogleForm(f);

		MapComponent googleMap = new MapComponent(new GoogleMapsProvider("AIzaSyA0R1Bq4_ldMQQVKcBv3fq1dakPfmpUcVU"));
		f.addComponent(BorderLayout.CENTER, googleMap);

		final Dialog dlg = new Dialog();
		InfiniteProgress prog = new InfiniteProgress();
		prog.setAnimation(r.getImage("waiting_4.png"));

		dlg.setLayout(new BorderLayout());
		dlg.addComponent(BorderLayout.CENTER, prog);
		dlg.showPacked(BorderLayout.CENTER, false);
		
		ProximityService ps = new ProximityService();
		Vector poiVec = ps.parseXMLResponseWithCodenameOneParser(ps.requestProximityService(lastLocation.getLongitude(), lastLocation.getLatitude()));
		ps.showRestaurantsOnGoogleMap(f, googleMap, poiVec, lastLocation.getLatitude(), lastLocation.getLongitude());
		
		dlg.dispose();
	}

	protected void exitInsertPositionManuallyForm(Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.exitInsertPositionManuallyForm(f);

		longitudeStr = findLongitudeField().getText();
		latitudeStr = findLatitudeField().getText();

		lastLocation = new Coord(Double.parseDouble(latitudeStr), Double.parseDouble(longitudeStr));
	}

	protected boolean initListModelMultimediaList(List cmp) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.initListModelMultimediaList(cmp);

		Vector<Hashtable> items = new Vector<Hashtable>();

		Hashtable item1 = new Hashtable();
		item1.put("Line1", "images");
		item1.put("icon", UIManager.getInstance().getThemeImageConstant("photosImage"));
		item1.put("emblem", r.getImage("Arrow.png"));

		Hashtable item2 = new Hashtable();
		item2.put("Line1", "audio");
		item2.put("icon", UIManager.getInstance().getThemeImageConstant("audioImage"));
		item2.put("emblem", r.getImage("Arrow.png"));

		Hashtable item3 = new Hashtable();
		item3.put("Line1", "video");
		item3.put("icon", UIManager.getInstance().getThemeImageConstant("videoImage"));
		item3.put("emblem", r.getImage("Arrow.png"));

		items.add(item1);
		items.add(item2);
		items.add(item3);

		ListModel model = new DefaultListModel(items);
		cmp.setModel(model);

		return true;
	}

	protected boolean initListModelMyImagesMultiList(List cmp) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.initListModelMyImagesMultiList(cmp);

		Vector<Hashtable> items = new Vector<Hashtable>();

		Hashtable item1 = new Hashtable();
		item1.put("Line1", "pick from folder");
		item1.put("icon", UIManager.getInstance().getThemeImageConstant("viewPhotoImage"));
		item1.put("emblem", r.getImage("Arrow.png"));

		Hashtable item2 = new Hashtable();
		item2.put("Line1", "capture");
		item2.put("icon", UIManager.getInstance().getThemeImageConstant("capturePhotoImage"));
		item2.put("emblem", r.getImage("Arrow.png"));

		items.add(item1);
		items.add(item2);
		;

		ListModel model = new DefaultListModel(items);
		cmp.setModel(model);

		return true;
	}

	protected boolean initListModelMyAudioMultiList(List cmp) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.initListModelMyAudioMultiList(cmp);

		Vector<Hashtable> items = new Vector<Hashtable>();

		Hashtable item1 = new Hashtable();
		item1.put("Line1", "pick from folder");
		item1.put("icon", UIManager.getInstance().getThemeImageConstant("pickFromFolderAudioImage"));
		item1.put("emblem", r.getImage("Arrow.png"));

		Hashtable item2 = new Hashtable();
		item2.put("Line1", "record");
		item2.put("icon", UIManager.getInstance().getThemeImageConstant("recordAudioImage"));
		item2.put("emblem", r.getImage("Arrow.png"));

		items.add(item1);
		items.add(item2);
		;

		ListModel model = new DefaultListModel(items);
		cmp.setModel(model);

		return true;
	}

	protected boolean initListModelMyVideoMultiList(List cmp) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.initListModelMyVideoMultiList(cmp);

		Vector<Hashtable> items = new Vector<Hashtable>();

		Hashtable item1 = new Hashtable();
		item1.put("Line1", "pick from folder");
		item1.put("icon", UIManager.getInstance().getThemeImageConstant("viewVideoImage"));
		item1.put("emblem", r.getImage("Arrow.png"));

		Hashtable item2 = new Hashtable();
		item2.put("Line1", "capture");
		item2.put("icon", UIManager.getInstance().getThemeImageConstant("captureVideoImage"));
		item2.put("emblem", r.getImage("Arrow.png"));

		items.add(item1);
		items.add(item2);

		ListModel model = new DefaultListModel(items);
		cmp.setModel(model);

		return true;
	}

	protected boolean initListModelPositionMultiList(List cmp) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.initListModelPositionMultiList(cmp);

		Vector<Hashtable> items = new Vector<Hashtable>();

		Hashtable item1 = new Hashtable();
		item1.put("Line1", "find your position");
		item1.put("icon", UIManager.getInstance().getThemeImageConstant("gpsImage"));
		item1.put("emblem", r.getImage("Arrow.png"));

		Hashtable item2 = new Hashtable();
		item2.put("Line1", "insert position manually");
		item2.put("icon", UIManager.getInstance().getThemeImageConstant("positionManualImage"));
		item2.put("emblem", r.getImage("Arrow.png"));

		Hashtable item3 = new Hashtable();
		item3.put("Line1", "insert address");
		item3.put("icon", UIManager.getInstance().getThemeImageConstant("positionAddressImage"));
		item3.put("emblem", r.getImage("Arrow.png"));

		items.add(item1);
		items.add(item2);
		items.add(item3);

		ListModel model = new DefaultListModel(items);
		cmp.setModel(model);

		return true;
	}

	protected void onAudioListen_AudioPlayButtonAction(Component c, ActionEvent event) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.onAudioListen_AudioPlayButtonAction(c, event);

		Display.getInstance().vibrate(vibrationTime);
		
		if (audioMedia != null) {
			if (audioMedia.isPlaying()) { // EDV NA ALLAZV KAI ONOMA STO KOUMPI KATHE FORA
				audioMedia.pause();
				((Button) c).setText("play");
				findAudioLabel().setIcon(r.getImage("cassetteSteady.png"));
			} else {
				audioMedia.setVolume(volume);
				audioMedia.play();
				((Button) c).setText("pause");
				findAudioLabel().setIcon(r.getImage("cassette.gif"));
			}
		}
		c.getComponentForm().revalidate();
		// c.getComponentForm().show();
	}

	protected void postLogin(Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.postLogin(f);

		findLoginIconContainer().getStyle().setMargin(Container.TOP, 20);
		findLoginIconContainer().getSelectedStyle().setMargin(Container.TOP, 20);
		findLoginIconContainer().getUnselectedStyle().setMargin(Container.TOP, 20);
		findLoginIconContainer().getPressedStyle().setMargin(Container.TOP, 20);

		Dimension dim = new Dimension(200, 70);
		findLoginBtn().setPreferredSize(dim);
		findLoginBtn().getStyle().setMargin(Button.TOP, 50);
		findLoginBtn().getSelectedStyle().setMargin(Button.TOP, 50);
		findLoginBtn().getUnselectedStyle().setMargin(Button.TOP, 50);
		findLoginBtn().getPressedStyle().setMargin(Button.TOP, 50);
		f.revalidate();

		selectedTheme = "Initial";
		UIManager.getInstance().setThemeProps(r.getTheme("Initial"));
		Display.getInstance().getCurrent().refreshTheme();
	}

	protected void postShare(Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.postShare(f);

		findShareButton().setIcon(UIManager.getInstance().getThemeImageConstant("shareSearchImage"));
		f.revalidate();
	}

	// protected void onShare_MitsosButtonAction(Component c, ActionEvent event) {
	// // If the resource file changes the names of components this call will break notifying you that you should fix the code
	// super.onShare_MitsosButtonAction(c, event);
	//
	// try {
	// Display.getInstance().sendSMS("6972529998", "mitsosButton!");
	// c.getComponentForm().setTitle("SMS sent!");
	// c.getComponentForm().revalidate();
	// } catch (Exception e) {
	// Dialog.show("Error", "" + e, "OK", null);
	// }
	// }

	// protected void onShare_GeocodingButtonAction(Component c, ActionEvent event) {
	// // If the resource file changes the names of components this call will break notifying you that you should fix the code
	// super.onShare_GeocodingButtonAction(c, event);
	//
	// GeocodingService gs = new GeocodingService();
	// InputStream responseStream = gs.requestGeocodingService("12 Pipinou");
	// Vector responseVec = gs.parseXMLResponseWithCodenameOneParser(responseStream);
	// System.out.println("responseVec: " + responseVec);
	// // ps.showResturantsOnMap(f, findProximityMc(), poiVec, lastLocation.getLatitude(), lastLocation.getLongitude());
	// }

	protected void postSelectAddressForm(Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.postSelectAddressForm(f);

		GeocodingService gs = new GeocodingService(r);

		InputStream responseStream = gs.requestGeocodingService(manualPostalAddress); // 12 Pipinou - Thessaloniki Greece
		Vector responseVec = gs.parseXMLResponseWithCodenameOneParser(responseStream);

		for (int i = 0; i < responseVec.size(); i++) {
			final GeocodingPoi poi = (GeocodingPoi) responseVec.get(i);
			String formattedAddress = poi.getFormattedAddress();

			Button poiBtn = new Button(poi.getFormattedAddress());

			poiBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {

					latitudeStr = poi.getLatitude();
					longitudeStr = poi.getLongitude();
					lastLocation = new Coord(Double.parseDouble(latitudeStr), Double.parseDouble(longitudeStr));

					showForm("Maps Form", null);
				}
			});
			f.addComponent(poiBtn);
		}
		f.revalidate();
	}

	protected boolean initListModelProvidersMultiList(List cmp) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.initListModelProvidersMultiList(cmp);

		Vector<Hashtable> items = new Vector<Hashtable>();

		Hashtable item1 = new Hashtable();
		item1.put("name", "Google");
		item1.put("icon", UIManager.getInstance().getThemeImageConstant("googleImage"));
		item1.put("emblem", r.getImage("Arrow.png"));

		Hashtable item2 = new Hashtable();
		item2.put("name", "Open Street");
		item2.put("icon", UIManager.getInstance().getThemeImageConstant("openStreetImage"));
		item2.put("emblem", r.getImage("Arrow.png"));

		items.add(item1);
		items.add(item2);

		ListModel model = new DefaultListModel(items);
		cmp.setModel(model);

		return true;
	}

	protected boolean initListModelProximityProvidersMultiList(List cmp) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.initListModelProximityProvidersMultiList(cmp);

		Vector<Hashtable> items = new Vector<Hashtable>();

		Hashtable item1 = new Hashtable();
		item1.put("Line1", "Google");
		item1.put("icon", UIManager.getInstance().getThemeImageConstant("googleImage"));
		item1.put("emblem", r.getImage("Arrow.png"));

		Hashtable item2 = new Hashtable();
		item2.put("Line1", "Open Street");
		item2.put("icon", UIManager.getInstance().getThemeImageConstant("openStreetImage"));
		item2.put("emblem", r.getImage("Arrow.png"));

		items.add(item1);
		items.add(item2);

		ListModel model = new DefaultListModel(items);
		cmp.setModel(model);

		return true;
	}

	protected void beforeMyContactsForm(Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.beforeMyContactsForm(f);

		Contacts contactsKitchen = new Contacts();
		Container contactsContainer = contactsKitchen.createDemo(r);

		// int selectedContactIndex = contactsKitchen.getSelectedIndex();

		f.addComponent(BorderLayout.CENTER, contactsContainer);

		// // Xwris ayta kanontas scroll fainontai ta contacts (prin to srcoll kolane sto Loading..)
		// // Den leitourgei gia includeNativeBool = true
		// // Me ayta h symperifora einai idia alla mou fainetai kathysterei ligo perissotero
		// Episis gia kapoio logo den pairnw donhsh
		// contactsContainer.revalidate();
		// contactsContainer.getComponentForm().revalidate();
		// f.revalidate();
	}

	protected void exitInsertAddressForm(Form f) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.exitInsertAddressForm(f);

		manualPostalAddress = findStreetField().getText();
		if (manualPostalAddress.equalsIgnoreCase("")) {
			manualPostalAddress = "Thessaloniki Greece";
		}
	}

	protected boolean initListModelContactsTestMultiList(final List cmp) {
		// If the resource file changes the names of components this call will break notifying you that you should fix the code
		super.initListModelContactsTestMultiList(cmp);

		// Vector<Hashtable> items = new Vector<Hashtable>();
		//
		// Hashtable item1 = new Hashtable();
		// item1.put("Line1", "Google");
		// item1.put("icon", UIManager.getInstance().getThemeImageConstant("googleImage"));
		// item1.put("emblem", r.getImage("Arrow.png"));
		//
		// Hashtable item2 = new Hashtable();
		// item2.put("Line1", "Open Street");
		// item2.put("icon", UIManager.getInstance().getThemeImageConstant("openStreetImage"));
		// item2.put("emblem", r.getImage("Arrow.png"));
		//
		// items.add(item1);
		// items.add(item2);

		final String[] allContacts = Display.getInstance().getAllContacts(true);

		Display.getInstance().callSerially(new Runnable() {
			public void run() {
				ListModel model = new ContactsModel(allContacts);// DefaultListModel(items);
				cmp.setModel(model);
			}
		});

		return true;
	}

//	protected void postMyImagesForm(Form f) {
//		// If the resource file changes the names of components this call will break notifying you that you should fix the code
//		super.postMyImagesForm(f);
//
//		f.setTitle("" + stepDebug);
//		f.revalidate();
//	}


    protected void onSettings_OffVibrationButtonAction(Component c, ActionEvent event) {
        // If the resource file changes the names of components this call will break notifying you that you should fix the code
        super.onSettings_OffVibrationButtonAction(c, event);
    
        Display.getInstance().vibrate(vibrationTime);
        offVibration = true;
        shortVibration = false;
        longVibration = false;
        
        vibrationTime = 0;
    }

    protected void onSettings_ShortVibrationButtonAction(Component c, ActionEvent event) {
        // If the resource file changes the names of components this call will break notifying you that you should fix the code
        super.onSettings_ShortVibrationButtonAction(c, event);
    
        Display.getInstance().vibrate(vibrationTime);
        offVibration = false;
        shortVibration = true;
        longVibration = false;
        
        vibrationTime = 1000;
    }

    protected void onSettings_LongVibrationButtonAction(Component c, ActionEvent event) {
        // If the resource file changes the names of components this call will break notifying you that you should fix the code
        super.onSettings_LongVibrationButtonAction(c, event);
    
        Display.getInstance().vibrate(vibrationTime);
        offVibration = false;
        shortVibration = false;
        longVibration = true;
        
        vibrationTime = 2000;
    }


    protected boolean onSettingsBack() {
        // If the resource file changes the names of components this call will break notifying you that you should fix the code
        boolean val = super.onSettingsBack();
        Display.getInstance().vibrate(vibrationTime);
        return val;
    }


    protected boolean onShareBack() {
        // If the resource file changes the names of components this call will break notifying you that you should fix the code
        boolean val = super.onShareBack();
        Display.getInstance().vibrate(vibrationTime);
        return val;
    }

    protected void onShare_ShareButtonAction(Component c, ActionEvent event) {
        // If the resource file changes the names of components this call will break notifying you that you should fix the code
        super.onShare_ShareButtonAction(c, event);
        Display.getInstance().vibrate(vibrationTime);
    }

    protected boolean onMyMultimediaFormBack() {
        // If the resource file changes the names of components this call will break notifying you that you should fix the code
        boolean val = super.onMyMultimediaFormBack();
        Display.getInstance().vibrate(vibrationTime);
        return val;
    }

    protected boolean onMyVideoFormBack() {
        // If the resource file changes the names of components this call will break notifying you that you should fix the code
        boolean val = super.onMyVideoFormBack();
        Display.getInstance().vibrate(vibrationTime);
        return val;
    }

    protected boolean onMyImagesFormBack() {
        // If the resource file changes the names of components this call will break notifying you that you should fix the code
        boolean val = super.onMyImagesFormBack();
        Display.getInstance().vibrate(vibrationTime);
        return val;
    }

    protected boolean onLoadPhotoFormBack() {
        // If the resource file changes the names of components this call will break notifying you that you should fix the code
        boolean val = super.onLoadPhotoFormBack();
        Display.getInstance().vibrate(vibrationTime);
        return val;
    }

    protected boolean onImageCaptureBack() {
        // If the resource file changes the names of components this call will break notifying you that you should fix the code
        boolean val = super.onImageCaptureBack();
        Display.getInstance().vibrate(vibrationTime);
        return val;
    }

    protected boolean onFindYourPositionFormBack() {
        // If the resource file changes the names of components this call will break notifying you that you should fix the code
        boolean val = super.onFindYourPositionFormBack();
        Display.getInstance().vibrate(vibrationTime);
        return val;
    }

    protected boolean onGpsFormNext() {
        // If the resource file changes the names of components this call will break notifying you that you should fix the code
        boolean val = super.onGpsFormNext();
        Display.getInstance().vibrate(vibrationTime);
        return val;
    }

    protected boolean onGpsFormBack() {
        // If the resource file changes the names of components this call will break notifying you that you should fix the code
        boolean val = super.onGpsFormBack();
        Display.getInstance().vibrate(vibrationTime);
        return val;
    }

    protected boolean onInsertPositionManuallyFormNext() {
        // If the resource file changes the names of components this call will break notifying you that you should fix the code
        boolean val = super.onInsertPositionManuallyFormNext();
        Display.getInstance().vibrate(vibrationTime);
        return val;
    }

    protected boolean onInsertPositionManuallyFormBack() {
        // If the resource file changes the names of components this call will break notifying you that you should fix the code
        boolean val = super.onInsertPositionManuallyFormBack();
        Display.getInstance().vibrate(vibrationTime);
        return val;
    }

    protected boolean onMapsFormBack() {
        // If the resource file changes the names of components this call will break notifying you that you should fix the code
        boolean val = super.onMapsFormBack();
        Display.getInstance().vibrate(vibrationTime);
        return val;
    }

    protected boolean onMapsProvidersBack() {
        // If the resource file changes the names of components this call will break notifying you that you should fix the code
        boolean val = super.onMapsProvidersBack();
        Display.getInstance().vibrate(vibrationTime);
        return val;
    }

    protected boolean onMyGooglePositionFormBack() {
        // If the resource file changes the names of components this call will break notifying you that you should fix the code
        boolean val = super.onMyGooglePositionFormBack();
        Display.getInstance().vibrate(vibrationTime);
        return val;
    }

    protected boolean onMyPositionFormBack() {
        // If the resource file changes the names of components this call will break notifying you that you should fix the code
        boolean val = super.onMyPositionFormBack();
        Display.getInstance().vibrate(vibrationTime);
        return val;
    }

    protected boolean onProximityProvidersFormBack() {
        // If the resource file changes the names of components this call will break notifying you that you should fix the code
        boolean val = super.onProximityProvidersFormBack();
        Display.getInstance().vibrate(vibrationTime);
        return val;
    }

    protected boolean onProximityGoogleFormBack() {
        // If the resource file changes the names of components this call will break notifying you that you should fix the code
        boolean val = super.onProximityGoogleFormBack();
        Display.getInstance().vibrate(vibrationTime);
        return val;
    }

    protected boolean onProximityFormBack() {
        // If the resource file changes the names of components this call will break notifying you that you should fix the code
        boolean val = super.onProximityFormBack();
        Display.getInstance().vibrate(vibrationTime);
        return val;
    }

    protected boolean onWebBrowserFormBack() {
        // If the resource file changes the names of components this call will break notifying you that you should fix the code
        boolean val = super.onWebBrowserFormBack();
        Display.getInstance().vibrate(vibrationTime);
        return val;
    }

    protected boolean onWebBrowserFormSetUrl() {
        // If the resource file changes the names of components this call will break notifying you that you should fix the code
        boolean val = super.onWebBrowserFormSetUrl();
        Display.getInstance().vibrate(vibrationTime);
        return val;
    }

    protected boolean onURLDialogBack() {
        // If the resource file changes the names of components this call will break notifying you that you should fix the code
        boolean val = super.onURLDialogBack();
        Display.getInstance().vibrate(vibrationTime);
        return val;
    }


    protected boolean initListModelMapsMultiList(List cmp) {
        // If the resource file changes the names of components this call will break notifying you that you should fix the code
        super.initListModelMapsMultiList(cmp);
        
		Vector<Hashtable> items = new Vector<Hashtable>();

		Hashtable item1 = new Hashtable();
		item1.put("Line1", "show me on map");
		item1.put("icon", UIManager.getInstance().getThemeImageConstant("myPositionImage"));
		item1.put("emblem", r.getImage("Arrow.png"));

		Hashtable item2 = new Hashtable();
		item2.put("Line1", "show nearby hotels");
		item2.put("icon", UIManager.getInstance().getThemeImageConstant("hotelsImage"));
		item2.put("emblem", r.getImage("Arrow.png"));

		items.add(item1);
		items.add(item2);

		ListModel model = new DefaultListModel(items);
		cmp.setModel(model);
        
        return true;
    }
    
    public void applyServerSettings(String themeName, String fontSize, String language, int volumeLevel, int vibrationLevel) {
    	// ALWAYS set all the selectedXXXXXXX values so that theSettings form continues to be functionable and 
    	// usable by the user in case he/she wants to apply manual settings
    	
    	// THEME
    	// Black-White, White-Black, Yellow-Black, Leather
    	if(themeName.equalsIgnoreCase("Black-White")) {
        	selectedTheme = "mitsosWhite";
    	} else if(themeName.equalsIgnoreCase("White-Black")) {
        	selectedTheme = "mitsosBlack";
    	} else if(themeName.equalsIgnoreCase("Yellow-Black")) {
        	selectedTheme = "mitsosYellow";
    	} else if(themeName.equalsIgnoreCase("Leather")) {
        	selectedTheme = "Leather";
    	}
    	System.out.println("Selected Theme: " + selectedTheme);
    	UIManager.getInstance().setThemeProps(r.getTheme(selectedTheme)); //"mitsosWhite"
		Display.getInstance().getCurrent().refreshTheme();
    	
    	//Also set the thumbnailImage of the volumeSlider 
    	
    	// FONT
		if (fontSize.equals("huge")) {
			UIManager.getInstance().addThemeProps(r.getTheme("0"));
			System.out.println("--> 00");
			selectedFont = "huge";
		} else if (fontSize.equals("veryLarge")) {
			UIManager.getInstance().addThemeProps(r.getTheme("1"));
			System.out.println("--> 10");
			selectedFont = "veryLarge";
		} else if (fontSize.equals("large")) {
			UIManager.getInstance().addThemeProps(r.getTheme("2"));
			System.out.println("--> 20");
			selectedFont = "large";
		} else if (fontSize.equals("medium")) {
			UIManager.getInstance().addThemeProps(r.getTheme("3"));
			System.out.println("--> 30");
			selectedFont = "medium";
		} else if (fontSize.equals("small")) {
			UIManager.getInstance().addThemeProps(r.getTheme("4"));
			System.out.println("--> 40");
			selectedFont = "small";
		}
    	System.out.println("Selected Font: " + selectedFont);
		Display.getInstance().getCurrent().refreshTheme();
		
		// LANGUAGE - Currently ONLY Greek and English are supported
		if(language.equalsIgnoreCase("English")) {
			UIManager.getInstance().setResourceBundle(r.getL10N("cloud4AllThemes", "en"));
			selectedLanguage = "en";
		} else if(language.equalsIgnoreCase("German")) {
			UIManager.getInstance().setResourceBundle(r.getL10N("cloud4AllThemes", "en"));
			selectedLanguage = "en";
		} else if(language.equalsIgnoreCase("Greek")) {
			UIManager.getInstance().setResourceBundle(r.getL10N("cloud4AllThemes", "gr"));
			selectedLanguage = "gr";
		} else if(language.equalsIgnoreCase("Spanish")) {
			UIManager.getInstance().setResourceBundle(r.getL10N("cloud4AllThemes", "en"));
			selectedLanguage = "en";
		}
    	System.out.println("Selected Language: " + selectedLanguage);
		
		// VOLUME
		volume = volumeLevel;
		System.out.println("Volume: " + volume);
//		if(volume == 0) {
//			findVolumeSlider().setProgress(volume);
//			findVolumeSlider().setVisible(false);
//		} else {
//			findVolumeSlider().setProgress(volume);
//			findVolumeSlider().setVisible(true);
//		}
		
		// VIBRATION
		vibrationTime = vibrationLevel;
		if(vibrationLevel == 0) {
	        offVibration = true;
	        shortVibration = false;
	        longVibration = false;
	        
	        vibrationTime = 0;
		} else if(vibrationLevel == 1) {
	        offVibration = false;
	        shortVibration = true;
	        longVibration = false;
	        
	        vibrationTime = 1000;
		} else if(vibrationLevel == 2) {
	        offVibration = false;
	        shortVibration = false;
	        longVibration = true;
	        
	        vibrationTime = 2000;
		}
        Display.getInstance().vibrate(vibrationTime);
        System.out.println("Vibration Time: " + vibrationTime);

    }
    
    
    public void applyServerSettings(Hashtable response) {
    	// ALWAYS set all the selectedXXXXXXX values so that theSettings form continues to be functionable and 
    	// usable by the user in case he/she wants to apply manual settings		
    	
    	Hashtable responseJME;
    	String responseTheme = "";
    	String responseFontSize = "";
    	String responseLanguage = "";
    	int responseVolume = -1;
    	int responseVibrationMode = -1;
    	
    	if(response.containsKey("info.cloud4all.JME")) {
    		isError = false;
			responseJME = (Hashtable)response.get("info.cloud4all.JME");
			
	    	if(responseJME.containsKey("theme")) {
				responseTheme = responseJME.get("theme").toString();
				
		    	// THEME
		    	// Black-White, White-Black, Yellow-Black, Leather
		    	if(responseTheme.equalsIgnoreCase("Black-White")) {
		        	selectedTheme = "mitsosWhite";
		    	} else if(responseTheme.equalsIgnoreCase("White-Black")) {
		        	selectedTheme = "mitsosBlack";
		    	} else if(responseTheme.equalsIgnoreCase("Yellow-Black")) {
		        	selectedTheme = "mitsosYellow";
		    	} else if(responseTheme.equalsIgnoreCase("Leather")) {
		        	selectedTheme = "Leather";
		    	} 
		    	System.out.println("Selected Theme: " + selectedTheme);
		    	UIManager.getInstance().setThemeProps(r.getTheme(selectedTheme)); //"mitsosWhite"
				Display.getInstance().getCurrent().refreshTheme();
			}
			if(responseJME.containsKey("fontSize")) {
				responseFontSize = responseJME.get("fontSize").toString();
				
		    	// FONT
				if (responseFontSize.equals("huge")) {
					UIManager.getInstance().addThemeProps(r.getTheme("0"));
					System.out.println("--> 00");
					selectedFont = "huge";
				} else if (responseFontSize.equals("veryLarge")) {
					UIManager.getInstance().addThemeProps(r.getTheme("1"));
					System.out.println("--> 10");
					selectedFont = "veryLarge";
				} else if (responseFontSize.equals("large")) {
					UIManager.getInstance().addThemeProps(r.getTheme("2"));
					System.out.println("--> 20");
					selectedFont = "large";
				} else if (responseFontSize.equals("medium")) {
					UIManager.getInstance().addThemeProps(r.getTheme("3"));
					System.out.println("--> 30");
					selectedFont = "medium";
				} else if (responseFontSize.equals("small")) {
					UIManager.getInstance().addThemeProps(r.getTheme("4"));
					System.out.println("--> 40");
					selectedFont = "small";
				} 
		    	System.out.println("Selected Font: " + selectedFont);
				Display.getInstance().getCurrent().refreshTheme();
			}
			if(responseJME.containsKey("language")) {
				responseLanguage = responseJME.get("language").toString();
				
				// LANGUAGE - Currently ONLY Greek and English are supported
				if(responseLanguage.equalsIgnoreCase("English")) {
					UIManager.getInstance().setResourceBundle(r.getL10N("cloud4AllThemes", "en"));
					selectedLanguage = "en";
				} else if(responseLanguage.equalsIgnoreCase("German")) {
					UIManager.getInstance().setResourceBundle(r.getL10N("cloud4AllThemes", "en"));
					selectedLanguage = "en";
				} else if(responseLanguage.equalsIgnoreCase("Greek")) {
					UIManager.getInstance().setResourceBundle(r.getL10N("cloud4AllThemes", "gr"));
					selectedLanguage = "gr";
				} else if(responseLanguage.equalsIgnoreCase("Spanish")) {
					UIManager.getInstance().setResourceBundle(r.getL10N("cloud4AllThemes", "es"));
					selectedLanguage = "es";
				} 
		    	System.out.println("Selected Language: " + selectedLanguage);
			}
			if(responseJME.containsKey("volume")) {
				responseVolume = ((Double)responseJME.get("volume")).intValue();
				
				// VOLUME
//				volume = Integer.parseInt(responseVolume);
				volume = responseVolume;
				System.out.println("Volume: " + volume);
			}
			if(responseJME.containsKey("vibrationMode")) {
				responseVibrationMode = ((Double)responseJME.get("vibrationMode")).intValue();
				
				// VIBRATION
				vibrationMode = responseVibrationMode;
				if(vibrationMode == 0) {
			        offVibration = true;
			        shortVibration = false;
			        longVibration = false;
			        
			        vibrationTime = 0;
				} else if(vibrationMode == 1) {
			        offVibration = false;
			        shortVibration = true;
			        longVibration = false;
			        
			        vibrationTime = 1000;
				} else if(vibrationMode == 2) {
			        offVibration = false;
			        shortVibration = false;
			        longVibration = true;
			        
			        vibrationTime = 2000;
				}
		        Display.getInstance().vibrate(vibrationTime);
		        System.out.println("Vibration Time: " + vibrationTime);
			}
		} else if(response.containsKey("isError")) {
			Dialog.show("Error in Login", "Username does not exist!", "Ok", null);
			isError = true;
		}
    	
    	//Also set the thumbnailImage of the volumeSlider 
		
    }



}
