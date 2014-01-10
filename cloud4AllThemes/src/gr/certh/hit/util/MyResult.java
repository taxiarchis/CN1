package gr.certh.hit.util;

import userclasses.StateMachine;

import com.codename1.codescan.ScanResult;

public class MyResult implements Runnable, ScanResult {
    private boolean finished;
    private String flag;
    public String text = "I";
    private StateMachine myMachine;
    
    public MyResult(){
    }

     public void scanCompleted(String contents, String formatName, byte[] rawBytes) {
		if (contents.equals("1")) {
			flag = "1";
		} else if (contents.equals("2")) {
			flag = "2";
		}

         finished = true;
     }
     
     public String getFlag() {
    	 return flag;
     }

     public void scanCanceled() {
			if (flag != null) {
			if (flag.equals("1")) {
				 text = text + "1";
//				// qrCode.setText(text);
//
//				selectedLanguage = "en";
//				selectedFont = "huge";
//				selectedTheme = "mitsosYellow";
//
//				// Set Greek
//				Hashtable table = r.getL10N("cloud4AllThemes", "en");
//				UIManager.getInstance().setResourceBundle(table);
//
//				// Set huge
//				UIManager.getInstance().addThemeProps(r.getTheme("0"));
//
//				// Set mitsosYellow
//				UIManager.getInstance().setThemeProps(r.getTheme("mitsosYellow"));
//				selectedTheme = "mitsosYellow";
//				Display.getInstance().getCurrent().refreshTheme();
//
//				String currentFormName = Display.getInstance().getCurrent().getName();
//
//				text = text + currentFormName;
//
//				showForm("Main", null);
			} else if (flag.equals("2")) {
				 text = text + "2";
//				// qrCode.setText(text);
//
//				selectedLanguage = "en";
//				selectedFont = "huge";
//				selectedTheme = "mitsosWhite";
//
//				// Set English
//				Hashtable table = r.getL10N("cloud4AllThemes", "en");
//				UIManager.getInstance().setResourceBundle(table);
//
//				// Set small
//				UIManager.getInstance().addThemeProps(r.getTheme("0"));
//
//				// Set mitsosWhite
//				UIManager.getInstance().setThemeProps(r.getTheme("mitsosWhite"));
//				selectedTheme = "mitsosWhite";
//				Display.getInstance().getCurrent().refreshTheme();
//
//				showForm("Main", null);
			}
		}
    	 
          finished = true;
    }

     public void scanError(int errorCode, String message) {
    	// text = text + "Error";
			// qrCode.setText(text);

			if (flag != null) {
				if (flag.equals("1")) {
					 text = text + "a";
//					// qrCode.setText(text);
//
//					selectedLanguage = "en";
//					selectedFont = "huge";
//					selectedTheme = "mitsosYellow";
//
//					// Set Greek
//					Hashtable table = r.getL10N("cloud4AllThemes", "en");
//					UIManager.getInstance().setResourceBundle(table);
//
//					// Set huge
//					UIManager.getInstance().addThemeProps(r.getTheme("0"));
//
//					// Set mitsosWhite
//					UIManager.getInstance().setThemeProps(r.getTheme("mitsosYellow"));
//					selectedTheme = "mitsosYellow";
//					Display.getInstance().getCurrent().refreshTheme();
//
//					String currentFormName = Display.getInstance().getCurrent().getName();
//
//					text = text + currentFormName;
//
//					showForm("Main", null);
				} else if (flag.equals("2")) {
					 text = text + "b";
//					// qrCode.setText(text);
//
//					selectedLanguage = "en";
//					selectedFont = "huge";
//					selectedTheme = "mitsosWhite";
//
//					// Set English
//					Hashtable table = r.getL10N("cloud4AllThemes", "en");
//					UIManager.getInstance().setResourceBundle(table);
//
//					// Set small
//					UIManager.getInstance().addThemeProps(r.getTheme("0"));
//
//					// Set mitsosWhite
//					UIManager.getInstance().setThemeProps(r.getTheme("mitsosWhite"));
//					selectedTheme = "mitsosWhite";
//					Display.getInstance().getCurrent().refreshTheme();
//
//					showForm("Main", null);
				}
			}
			
         finished = true;
     }

     public void run() {
         while(!finished) {
              try {
                   Thread.sleep(50);
              } catch(InterruptedException e) {}
         }
     }
}
