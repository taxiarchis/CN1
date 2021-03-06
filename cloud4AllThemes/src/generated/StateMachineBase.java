/**
 * This class contains generated code from the Codename One Designer, DO NOT MODIFY!
 * This class is designed for subclassing that way the code generator can overwrite it
 * anytime without erasing your changes which should exist in a subclass!
 * For details about this file and how it works please read this blog post:
 * http://codenameone.blogspot.com/2010/10/ui-builder-class-how-to-actually-use.html
*/
package generated;

import com.codename1.ui.*;
import com.codename1.ui.util.*;
import com.codename1.ui.plaf.*;
import java.util.Hashtable;
import com.codename1.ui.events.*;

public abstract class StateMachineBase extends UIBuilder {
    private Container aboutToShowThisContainer;
    /**
     * this method should be used to initialize variables instead of
     * the constructor/class scope to avoid race conditions
     */
    /**
    * @deprecated use the version that accepts a resource as an argument instead
    
**/
    protected void initVars() {}

    protected void initVars(Resources res) {}

    public StateMachineBase(Resources res, String resPath, boolean loadTheme) {
        startApp(res, resPath, loadTheme);
    }

    public Container startApp(Resources res, String resPath, boolean loadTheme) {
        initVars();
        UIBuilder.registerCustomComponent("MapComponent", com.codename1.maps.MapComponent.class);
        UIBuilder.registerCustomComponent("RadioButton", com.codename1.ui.RadioButton.class);
        UIBuilder.registerCustomComponent("ShareButton", com.codename1.components.ShareButton.class);
        UIBuilder.registerCustomComponent("MediaPlayer", com.codename1.components.MediaPlayer.class);
        UIBuilder.registerCustomComponent("Dialog", com.codename1.ui.Dialog.class);
        UIBuilder.registerCustomComponent("TextArea", com.codename1.ui.TextArea.class);
        UIBuilder.registerCustomComponent("TextField", com.codename1.ui.TextField.class);
        UIBuilder.registerCustomComponent("Slider", com.codename1.ui.Slider.class);
        UIBuilder.registerCustomComponent("WebBrowser", com.codename1.components.WebBrowser.class);
        UIBuilder.registerCustomComponent("ComponentGroup", com.codename1.ui.ComponentGroup.class);
        UIBuilder.registerCustomComponent("Button", com.codename1.ui.Button.class);
        UIBuilder.registerCustomComponent("ComboBox", com.codename1.ui.ComboBox.class);
        UIBuilder.registerCustomComponent("Form", com.codename1.ui.Form.class);
        UIBuilder.registerCustomComponent("MultiList", com.codename1.ui.list.MultiList.class);
        UIBuilder.registerCustomComponent("Label", com.codename1.ui.Label.class);
        UIBuilder.registerCustomComponent("List", com.codename1.ui.List.class);
        UIBuilder.registerCustomComponent("Container", com.codename1.ui.Container.class);
        if(loadTheme) {
            if(res == null) {
                try {
                    if(resPath.endsWith(".res")) {
                        res = Resources.open(resPath);
                        System.out.println("Warning: you should construct the state machine without the .res extension to allow theme overlays");
                    } else {
                        res = Resources.openLayered(resPath);
                    }
                } catch(java.io.IOException err) { err.printStackTrace(); }
            }
            initTheme(res);
        }
        if(res != null) {
            setResourceFilePath(resPath);
            setResourceFile(res);
            initVars(res);
            return showForm(getFirstFormName(), null);
        } else {
            Form f = (Form)createContainer(resPath, getFirstFormName());
            initVars(fetchResourceFile());
            beforeShow(f);
            f.show();
            postShow(f);
            return f;
        }
    }

    protected String getFirstFormName() {
        return "Splash";
    }

    public Container createWidget(Resources res, String resPath, boolean loadTheme) {
        initVars();
        UIBuilder.registerCustomComponent("MapComponent", com.codename1.maps.MapComponent.class);
        UIBuilder.registerCustomComponent("RadioButton", com.codename1.ui.RadioButton.class);
        UIBuilder.registerCustomComponent("ShareButton", com.codename1.components.ShareButton.class);
        UIBuilder.registerCustomComponent("MediaPlayer", com.codename1.components.MediaPlayer.class);
        UIBuilder.registerCustomComponent("Dialog", com.codename1.ui.Dialog.class);
        UIBuilder.registerCustomComponent("TextArea", com.codename1.ui.TextArea.class);
        UIBuilder.registerCustomComponent("TextField", com.codename1.ui.TextField.class);
        UIBuilder.registerCustomComponent("Slider", com.codename1.ui.Slider.class);
        UIBuilder.registerCustomComponent("WebBrowser", com.codename1.components.WebBrowser.class);
        UIBuilder.registerCustomComponent("ComponentGroup", com.codename1.ui.ComponentGroup.class);
        UIBuilder.registerCustomComponent("Button", com.codename1.ui.Button.class);
        UIBuilder.registerCustomComponent("ComboBox", com.codename1.ui.ComboBox.class);
        UIBuilder.registerCustomComponent("Form", com.codename1.ui.Form.class);
        UIBuilder.registerCustomComponent("MultiList", com.codename1.ui.list.MultiList.class);
        UIBuilder.registerCustomComponent("Label", com.codename1.ui.Label.class);
        UIBuilder.registerCustomComponent("List", com.codename1.ui.List.class);
        UIBuilder.registerCustomComponent("Container", com.codename1.ui.Container.class);
        if(loadTheme) {
            if(res == null) {
                try {
                    res = Resources.openLayered(resPath);
                } catch(java.io.IOException err) { err.printStackTrace(); }
            }
            initTheme(res);
        }
        return createContainer(resPath, "Splash");
    }

    protected void initTheme(Resources res) {
            String[] themes = res.getThemeResourceNames();
            if(themes != null && themes.length > 0) {
                UIManager.getInstance().setThemeProps(res.getTheme(themes[0]));
            }
    }

    public StateMachineBase() {
    }

    public StateMachineBase(String resPath) {
        this(null, resPath, true);
    }

    public StateMachineBase(Resources res) {
        this(res, null, true);
    }

    public StateMachineBase(String resPath, boolean loadTheme) {
        this(null, resPath, loadTheme);
    }

    public StateMachineBase(Resources res, boolean loadTheme) {
        this(res, null, loadTheme);
    }

    public com.codename1.ui.RadioButton findLongVibrationButton(Component root) {
        return (com.codename1.ui.RadioButton)findByName("longVibrationButton", root);
    }

    public com.codename1.ui.RadioButton findLongVibrationButton() {
        com.codename1.ui.RadioButton cmp = (com.codename1.ui.RadioButton)findByName("longVibrationButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.RadioButton)findByName("longVibrationButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.ComboBox findUrlComboBox(Component root) {
        return (com.codename1.ui.ComboBox)findByName("urlComboBox", root);
    }

    public com.codename1.ui.ComboBox findUrlComboBox() {
        com.codename1.ui.ComboBox cmp = (com.codename1.ui.ComboBox)findByName("urlComboBox", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.ComboBox)findByName("urlComboBox", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.ComboBox findServerCombo(Component root) {
        return (com.codename1.ui.ComboBox)findByName("serverCombo", root);
    }

    public com.codename1.ui.ComboBox findServerCombo() {
        com.codename1.ui.ComboBox cmp = (com.codename1.ui.ComboBox)findByName("serverCombo", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.ComboBox)findByName("serverCombo", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.RadioButton findEnglishRadioButton(Component root) {
        return (com.codename1.ui.RadioButton)findByName("englishRadioButton", root);
    }

    public com.codename1.ui.RadioButton findEnglishRadioButton() {
        com.codename1.ui.RadioButton cmp = (com.codename1.ui.RadioButton)findByName("englishRadioButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.RadioButton)findByName("englishRadioButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.RadioButton findYellowRadioButton(Component root) {
        return (com.codename1.ui.RadioButton)findByName("yellowRadioButton", root);
    }

    public com.codename1.ui.RadioButton findYellowRadioButton() {
        com.codename1.ui.RadioButton cmp = (com.codename1.ui.RadioButton)findByName("yellowRadioButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.RadioButton)findByName("yellowRadioButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findLoginIconContainer(Component root) {
        return (com.codename1.ui.Container)findByName("loginIconContainer", root);
    }

    public com.codename1.ui.Container findLoginIconContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("loginIconContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("loginIconContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.List findFolderBrowserList(Component root) {
        return (com.codename1.ui.List)findByName("folderBrowserList", root);
    }

    public com.codename1.ui.List findFolderBrowserList() {
        com.codename1.ui.List cmp = (com.codename1.ui.List)findByName("folderBrowserList", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.List)findByName("folderBrowserList", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.ComponentGroup findSoundRadioGroup(Component root) {
        return (com.codename1.ui.ComponentGroup)findByName("soundRadioGroup", root);
    }

    public com.codename1.ui.ComponentGroup findSoundRadioGroup() {
        com.codename1.ui.ComponentGroup cmp = (com.codename1.ui.ComponentGroup)findByName("soundRadioGroup", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.ComponentGroup)findByName("soundRadioGroup", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.list.MultiList findMultimediaList(Component root) {
        return (com.codename1.ui.list.MultiList)findByName("Multimedia List", root);
    }

    public com.codename1.ui.list.MultiList findMultimediaList() {
        com.codename1.ui.list.MultiList cmp = (com.codename1.ui.list.MultiList)findByName("Multimedia List", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.list.MultiList)findByName("Multimedia List", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.RadioButton findOffVibrationButton(Component root) {
        return (com.codename1.ui.RadioButton)findByName("offVibrationButton", root);
    }

    public com.codename1.ui.RadioButton findOffVibrationButton() {
        com.codename1.ui.RadioButton cmp = (com.codename1.ui.RadioButton)findByName("offVibrationButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.RadioButton)findByName("offVibrationButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findPhoneIcon(Component root) {
        return (com.codename1.ui.Label)findByName("phoneIcon", root);
    }

    public com.codename1.ui.Label findPhoneIcon() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("phoneIcon", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("phoneIcon", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLatitudeLabel(Component root) {
        return (com.codename1.ui.Label)findByName("latitudeLabel", root);
    }

    public com.codename1.ui.Label findLatitudeLabel() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("latitudeLabel", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("latitudeLabel", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.RadioButton findVeryLargeRadioButton(Component root) {
        return (com.codename1.ui.RadioButton)findByName("veryLargeRadioButton", root);
    }

    public com.codename1.ui.RadioButton findVeryLargeRadioButton() {
        com.codename1.ui.RadioButton cmp = (com.codename1.ui.RadioButton)findByName("veryLargeRadioButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.RadioButton)findByName("veryLargeRadioButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.list.MultiList findProximityProvidersMultiList(Component root) {
        return (com.codename1.ui.list.MultiList)findByName("proximityProvidersMultiList", root);
    }

    public com.codename1.ui.list.MultiList findProximityProvidersMultiList() {
        com.codename1.ui.list.MultiList cmp = (com.codename1.ui.list.MultiList)findByName("proximityProvidersMultiList", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.list.MultiList)findByName("proximityProvidersMultiList", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findPhotoLb(Component root) {
        return (com.codename1.ui.Label)findByName("photoLb", root);
    }

    public com.codename1.ui.Label findPhotoLb() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("photoLb", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("photoLb", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.list.MultiList findMapsMultiList(Component root) {
        return (com.codename1.ui.list.MultiList)findByName("mapsMultiList", root);
    }

    public com.codename1.ui.list.MultiList findMapsMultiList() {
        com.codename1.ui.list.MultiList cmp = (com.codename1.ui.list.MultiList)findByName("mapsMultiList", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.list.MultiList)findByName("mapsMultiList", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findLatitudeField(Component root) {
        return (com.codename1.ui.TextField)findByName("latitudeField", root);
    }

    public com.codename1.ui.TextField findLatitudeField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("latitudeField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("latitudeField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.RadioButton findSpanishRadioButton(Component root) {
        return (com.codename1.ui.RadioButton)findByName("spanishRadioButton", root);
    }

    public com.codename1.ui.RadioButton findSpanishRadioButton() {
        com.codename1.ui.RadioButton cmp = (com.codename1.ui.RadioButton)findByName("spanishRadioButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.RadioButton)findByName("spanishRadioButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findSurnameField(Component root) {
        return (com.codename1.ui.TextField)findByName("surnameField", root);
    }

    public com.codename1.ui.TextField findSurnameField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("surnameField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("surnameField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findFontsLabel(Component root) {
        return (com.codename1.ui.Label)findByName("fontsLabel", root);
    }

    public com.codename1.ui.Label findFontsLabel() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("fontsLabel", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("fontsLabel", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.RadioButton findSoundNoRadioButton(Component root) {
        return (com.codename1.ui.RadioButton)findByName("soundNoRadioButton", root);
    }

    public com.codename1.ui.RadioButton findSoundNoRadioButton() {
        com.codename1.ui.RadioButton cmp = (com.codename1.ui.RadioButton)findByName("soundNoRadioButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.RadioButton)findByName("soundNoRadioButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.RadioButton findBlackRadioButton(Component root) {
        return (com.codename1.ui.RadioButton)findByName("blackRadioButton", root);
    }

    public com.codename1.ui.RadioButton findBlackRadioButton() {
        com.codename1.ui.RadioButton cmp = (com.codename1.ui.RadioButton)findByName("blackRadioButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.RadioButton)findByName("blackRadioButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.list.MultiList findMultiList(Component root) {
        return (com.codename1.ui.list.MultiList)findByName("MultiList", root);
    }

    public com.codename1.ui.list.MultiList findMultiList() {
        com.codename1.ui.list.MultiList cmp = (com.codename1.ui.list.MultiList)findByName("MultiList", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.list.MultiList)findByName("MultiList", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findUrlTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("urlTextField", root);
    }

    public com.codename1.ui.TextField findUrlTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("urlTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("urlTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findGpsLatitudeLabel(Component root) {
        return (com.codename1.ui.Label)findByName("gpsLatitudeLabel", root);
    }

    public com.codename1.ui.Label findGpsLatitudeLabel() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("gpsLatitudeLabel", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("gpsLatitudeLabel", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findUrlLabel(Component root) {
        return (com.codename1.ui.Label)findByName("urlLabel", root);
    }

    public com.codename1.ui.Label findUrlLabel() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("urlLabel", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("urlLabel", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.list.MultiList findMyAudioMultiList(Component root) {
        return (com.codename1.ui.list.MultiList)findByName("My Audio MultiList", root);
    }

    public com.codename1.ui.list.MultiList findMyAudioMultiList() {
        com.codename1.ui.list.MultiList cmp = (com.codename1.ui.list.MultiList)findByName("My Audio MultiList", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.list.MultiList)findByName("My Audio MultiList", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findInfo2Lb(Component root) {
        return (com.codename1.ui.Label)findByName("info2Lb", root);
    }

    public com.codename1.ui.Label findInfo2Lb() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("info2Lb", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("info2Lb", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findSurnameContainer(Component root) {
        return (com.codename1.ui.Container)findByName("surnameContainer", root);
    }

    public com.codename1.ui.Container findSurnameContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("surnameContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("surnameContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findSurnameIcon(Component root) {
        return (com.codename1.ui.Label)findByName("surnameIcon", root);
    }

    public com.codename1.ui.Label findSurnameIcon() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("surnameIcon", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("surnameIcon", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findInfoLb(Component root) {
        return (com.codename1.ui.Label)findByName("infoLb", root);
    }

    public com.codename1.ui.Label findInfoLb() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("infoLb", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("infoLb", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findLongitudeField(Component root) {
        return (com.codename1.ui.TextField)findByName("longitudeField", root);
    }

    public com.codename1.ui.TextField findLongitudeField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("longitudeField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("longitudeField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findAudioLabelContainer(Component root) {
        return (com.codename1.ui.Container)findByName("audioLabelContainer", root);
    }

    public com.codename1.ui.Container findAudioLabelContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("audioLabelContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("audioLabelContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findProvidersMultiListContainer(Component root) {
        return (com.codename1.ui.Container)findByName("providersMultiListContainer", root);
    }

    public com.codename1.ui.Container findProvidersMultiListContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("providersMultiListContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("providersMultiListContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.RadioButton findShortVibrationButton(Component root) {
        return (com.codename1.ui.RadioButton)findByName("shortVibrationButton", root);
    }

    public com.codename1.ui.RadioButton findShortVibrationButton() {
        com.codename1.ui.RadioButton cmp = (com.codename1.ui.RadioButton)findByName("shortVibrationButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.RadioButton)findByName("shortVibrationButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.list.MultiList findAllContactsMultiList(Component root) {
        return (com.codename1.ui.list.MultiList)findByName("allContactsMultiList", root);
    }

    public com.codename1.ui.list.MultiList findAllContactsMultiList() {
        com.codename1.ui.list.MultiList cmp = (com.codename1.ui.list.MultiList)findByName("allContactsMultiList", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.list.MultiList)findByName("allContactsMultiList", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.ComponentGroup findLanguagesRadioButtonGroup(Component root) {
        return (com.codename1.ui.ComponentGroup)findByName("languagesRadioButtonGroup", root);
    }

    public com.codename1.ui.ComponentGroup findLanguagesRadioButtonGroup() {
        com.codename1.ui.ComponentGroup cmp = (com.codename1.ui.ComponentGroup)findByName("languagesRadioButtonGroup", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.ComponentGroup)findByName("languagesRadioButtonGroup", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.list.MultiList findContactsTestMultiList(Component root) {
        return (com.codename1.ui.list.MultiList)findByName("contactsTestMultiList", root);
    }

    public com.codename1.ui.list.MultiList findContactsTestMultiList() {
        com.codename1.ui.list.MultiList cmp = (com.codename1.ui.list.MultiList)findByName("contactsTestMultiList", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.list.MultiList)findByName("contactsTestMultiList", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findAudioLabel(Component root) {
        return (com.codename1.ui.Label)findByName("audioLabel", root);
    }

    public com.codename1.ui.Label findAudioLabel() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("audioLabel", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("audioLabel", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findAudioPlayButton(Component root) {
        return (com.codename1.ui.Button)findByName("audioPlayButton", root);
    }

    public com.codename1.ui.Button findAudioPlayButton() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("audioPlayButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("audioPlayButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.RadioButton findSoundYesRadioButton(Component root) {
        return (com.codename1.ui.RadioButton)findByName("soundYesRadioButton", root);
    }

    public com.codename1.ui.RadioButton findSoundYesRadioButton() {
        com.codename1.ui.RadioButton cmp = (com.codename1.ui.RadioButton)findByName("soundYesRadioButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.RadioButton)findByName("soundYesRadioButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.RadioButton findSmallRadioButton(Component root) {
        return (com.codename1.ui.RadioButton)findByName("smallRadioButton", root);
    }

    public com.codename1.ui.RadioButton findSmallRadioButton() {
        com.codename1.ui.RadioButton cmp = (com.codename1.ui.RadioButton)findByName("smallRadioButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.RadioButton)findByName("smallRadioButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.RadioButton findGermanRadioButton(Component root) {
        return (com.codename1.ui.RadioButton)findByName("germanRadioButton", root);
    }

    public com.codename1.ui.RadioButton findGermanRadioButton() {
        com.codename1.ui.RadioButton cmp = (com.codename1.ui.RadioButton)findByName("germanRadioButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.RadioButton)findByName("germanRadioButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextArea findServerTA(Component root) {
        return (com.codename1.ui.TextArea)findByName("serverTA", root);
    }

    public com.codename1.ui.TextArea findServerTA() {
        com.codename1.ui.TextArea cmp = (com.codename1.ui.TextArea)findByName("serverTA", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextArea)findByName("serverTA", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findStorageLb(Component root) {
        return (com.codename1.ui.Label)findByName("storageLb", root);
    }

    public com.codename1.ui.Label findStorageLb() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("storageLb", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("storageLb", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findLanguagesContainer(Component root) {
        return (com.codename1.ui.Container)findByName("languagesContainer", root);
    }

    public com.codename1.ui.Container findLanguagesContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("languagesContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("languagesContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findSoundContainer(Component root) {
        return (com.codename1.ui.Container)findByName("soundContainer", root);
    }

    public com.codename1.ui.Container findSoundContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("soundContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("soundContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.RadioButton findHugeRadioButton(Component root) {
        return (com.codename1.ui.RadioButton)findByName("hugeRadioButton", root);
    }

    public com.codename1.ui.RadioButton findHugeRadioButton() {
        com.codename1.ui.RadioButton cmp = (com.codename1.ui.RadioButton)findByName("hugeRadioButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.RadioButton)findByName("hugeRadioButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findPhoneContainer(Component root) {
        return (com.codename1.ui.Container)findByName("phoneContainer", root);
    }

    public com.codename1.ui.Container findPhoneContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("phoneContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("phoneContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findChooseUrlLabel(Component root) {
        return (com.codename1.ui.Label)findByName("chooseUrlLabel", root);
    }

    public com.codename1.ui.Label findChooseUrlLabel() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("chooseUrlLabel", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("chooseUrlLabel", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLanguagesLabel(Component root) {
        return (com.codename1.ui.Label)findByName("languagesLabel", root);
    }

    public com.codename1.ui.Label findLanguagesLabel() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("languagesLabel", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("languagesLabel", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.list.MultiList findShareMultiList(Component root) {
        return (com.codename1.ui.list.MultiList)findByName("shareMultiList", root);
    }

    public com.codename1.ui.list.MultiList findShareMultiList() {
        com.codename1.ui.list.MultiList cmp = (com.codename1.ui.list.MultiList)findByName("shareMultiList", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.list.MultiList)findByName("shareMultiList", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findIpLb(Component root) {
        return (com.codename1.ui.Label)findByName("ipLb", root);
    }

    public com.codename1.ui.Label findIpLb() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("ipLb", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("ipLb", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findStreetLabel(Component root) {
        return (com.codename1.ui.Label)findByName("streetLabel", root);
    }

    public com.codename1.ui.Label findStreetLabel() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("streetLabel", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("streetLabel", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findFindYourPositionContainer(Component root) {
        return (com.codename1.ui.Container)findByName("findYourPositionContainer", root);
    }

    public com.codename1.ui.Container findFindYourPositionContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("findYourPositionContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("findYourPositionContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer2(Component root) {
        return (com.codename1.ui.Container)findByName("Container2", root);
    }

    public com.codename1.ui.Container findContainer2() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container2", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container2", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer1(Component root) {
        return (com.codename1.ui.Container)findByName("Container1", root);
    }

    public com.codename1.ui.Container findContainer1() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container1", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container1", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.MediaPlayer findPlayer(Component root) {
        return (com.codename1.components.MediaPlayer)findByName("player", root);
    }

    public com.codename1.components.MediaPlayer findPlayer() {
        com.codename1.components.MediaPlayer cmp = (com.codename1.components.MediaPlayer)findByName("player", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.MediaPlayer)findByName("player", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findPhoneField(Component root) {
        return (com.codename1.ui.TextField)findByName("phoneField", root);
    }

    public com.codename1.ui.TextField findPhoneField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("phoneField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("phoneField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findServerLb(Component root) {
        return (com.codename1.ui.Label)findByName("serverLb", root);
    }

    public com.codename1.ui.Label findServerLb() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("serverLb", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("serverLb", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.RadioButton findMediumRadioButton(Component root) {
        return (com.codename1.ui.RadioButton)findByName("mediumRadioButton", root);
    }

    public com.codename1.ui.RadioButton findMediumRadioButton() {
        com.codename1.ui.RadioButton cmp = (com.codename1.ui.RadioButton)findByName("mediumRadioButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.RadioButton)findByName("mediumRadioButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findLoginBtn(Component root) {
        return (com.codename1.ui.Button)findByName("loginBtn", root);
    }

    public com.codename1.ui.Button findLoginBtn() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("loginBtn", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("loginBtn", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findVibrationContainer(Component root) {
        return (com.codename1.ui.Container)findByName("vibrationContainer", root);
    }

    public com.codename1.ui.Container findVibrationContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("vibrationContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("vibrationContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findButton(Component root) {
        return (com.codename1.ui.Button)findByName("Button", root);
    }

    public com.codename1.ui.Button findButton() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("Button", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("Button", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLabel1(Component root) {
        return (com.codename1.ui.Label)findByName("Label1", root);
    }

    public com.codename1.ui.Label findLabel1() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("Label1", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("Label1", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findCameraLabel(Component root) {
        return (com.codename1.ui.Label)findByName("cameraLabel", root);
    }

    public com.codename1.ui.Label findCameraLabel() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("cameraLabel", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("cameraLabel", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLabel2(Component root) {
        return (com.codename1.ui.Label)findByName("Label2", root);
    }

    public com.codename1.ui.Label findLabel2() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("Label2", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("Label2", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Slider findVolumeSlider(Component root) {
        return (com.codename1.ui.Slider)findByName("volumeSlider", root);
    }

    public com.codename1.ui.Slider findVolumeSlider() {
        com.codename1.ui.Slider cmp = (com.codename1.ui.Slider)findByName("volumeSlider", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Slider)findByName("volumeSlider", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.list.MultiList findMyImagesMultiList(Component root) {
        return (com.codename1.ui.list.MultiList)findByName("My Images MultiList", root);
    }

    public com.codename1.ui.list.MultiList findMyImagesMultiList() {
        com.codename1.ui.list.MultiList cmp = (com.codename1.ui.list.MultiList)findByName("My Images MultiList", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.list.MultiList)findByName("My Images MultiList", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.RadioButton findLargeRadioButton(Component root) {
        return (com.codename1.ui.RadioButton)findByName("largeRadioButton", root);
    }

    public com.codename1.ui.RadioButton findLargeRadioButton() {
        com.codename1.ui.RadioButton cmp = (com.codename1.ui.RadioButton)findByName("largeRadioButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.RadioButton)findByName("largeRadioButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.ComponentGroup findThemesRadioButtonGroup(Component root) {
        return (com.codename1.ui.ComponentGroup)findByName("themesRadioButtonGroup", root);
    }

    public com.codename1.ui.ComponentGroup findThemesRadioButtonGroup() {
        com.codename1.ui.ComponentGroup cmp = (com.codename1.ui.ComponentGroup)findByName("themesRadioButtonGroup", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.ComponentGroup)findByName("themesRadioButtonGroup", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.RadioButton findWhiteRadioButton(Component root) {
        return (com.codename1.ui.RadioButton)findByName("whiteRadioButton", root);
    }

    public com.codename1.ui.RadioButton findWhiteRadioButton() {
        com.codename1.ui.RadioButton cmp = (com.codename1.ui.RadioButton)findByName("whiteRadioButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.RadioButton)findByName("whiteRadioButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findStreetField(Component root) {
        return (com.codename1.ui.TextField)findByName("streetField", root);
    }

    public com.codename1.ui.TextField findStreetField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("streetField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("streetField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.maps.MapComponent findProximityMc(Component root) {
        return (com.codename1.maps.MapComponent)findByName("ProximityMc", root);
    }

    public com.codename1.maps.MapComponent findProximityMc() {
        com.codename1.maps.MapComponent cmp = (com.codename1.maps.MapComponent)findByName("ProximityMc", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.maps.MapComponent)findByName("ProximityMc", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findBrowserCon(Component root) {
        return (com.codename1.ui.Container)findByName("browserCon", root);
    }

    public com.codename1.ui.Container findBrowserCon() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("browserCon", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("browserCon", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.WebBrowser findWebBrowser(Component root) {
        return (com.codename1.components.WebBrowser)findByName("webBrowser", root);
    }

    public com.codename1.components.WebBrowser findWebBrowser() {
        com.codename1.components.WebBrowser cmp = (com.codename1.components.WebBrowser)findByName("webBrowser", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.WebBrowser)findByName("webBrowser", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLongitudeLabel(Component root) {
        return (com.codename1.ui.Label)findByName("longitudeLabel", root);
    }

    public com.codename1.ui.Label findLongitudeLabel() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("longitudeLabel", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("longitudeLabel", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findNameContainer(Component root) {
        return (com.codename1.ui.Container)findByName("nameContainer", root);
    }

    public com.codename1.ui.Container findNameContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("nameContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("nameContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findGpsLongitudeLabel(Component root) {
        return (com.codename1.ui.Label)findByName("gpsLongitudeLabel", root);
    }

    public com.codename1.ui.Label findGpsLongitudeLabel() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("gpsLongitudeLabel", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("gpsLongitudeLabel", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findVibrationLabel(Component root) {
        return (com.codename1.ui.Label)findByName("vibrationLabel", root);
    }

    public com.codename1.ui.Label findVibrationLabel() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("vibrationLabel", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("vibrationLabel", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findFontsContainer(Component root) {
        return (com.codename1.ui.Container)findByName("fontsContainer", root);
    }

    public com.codename1.ui.Container findFontsContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("fontsContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("fontsContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.RadioButton findLeatherRadioButton(Component root) {
        return (com.codename1.ui.RadioButton)findByName("leatherRadioButton", root);
    }

    public com.codename1.ui.RadioButton findLeatherRadioButton() {
        com.codename1.ui.RadioButton cmp = (com.codename1.ui.RadioButton)findByName("leatherRadioButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.RadioButton)findByName("leatherRadioButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextArea findArea(Component root) {
        return (com.codename1.ui.TextArea)findByName("area", root);
    }

    public com.codename1.ui.TextArea findArea() {
        com.codename1.ui.TextArea cmp = (com.codename1.ui.TextArea)findByName("area", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextArea)findByName("area", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findUsernameLb(Component root) {
        return (com.codename1.ui.Label)findByName("usernameLb", root);
    }

    public com.codename1.ui.Label findUsernameLb() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("usernameLb", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("usernameLb", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findSoundLabel(Component root) {
        return (com.codename1.ui.Label)findByName("soundLabel", root);
    }

    public com.codename1.ui.Label findSoundLabel() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("soundLabel", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("soundLabel", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLoginLb(Component root) {
        return (com.codename1.ui.Label)findByName("loginLb", root);
    }

    public com.codename1.ui.Label findLoginLb() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("loginLb", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("loginLb", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findName(Component root) {
        return (com.codename1.ui.Label)findByName("name", root);
    }

    public com.codename1.ui.Label findName() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("name", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("name", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findNameField(Component root) {
        return (com.codename1.ui.TextField)findByName("nameField", root);
    }

    public com.codename1.ui.TextField findNameField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("nameField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("nameField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.RadioButton findGreekRadioButton(Component root) {
        return (com.codename1.ui.RadioButton)findByName("greekRadioButton", root);
    }

    public com.codename1.ui.RadioButton findGreekRadioButton() {
        com.codename1.ui.RadioButton cmp = (com.codename1.ui.RadioButton)findByName("greekRadioButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.RadioButton)findByName("greekRadioButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer(Component root) {
        return (com.codename1.ui.Container)findByName("Container", root);
    }

    public com.codename1.ui.Container findContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findCaptureLb(Component root) {
        return (com.codename1.ui.Label)findByName("captureLb", root);
    }

    public com.codename1.ui.Label findCaptureLb() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("captureLb", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("captureLb", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findUrlComboRenderer(Component root) {
        return (com.codename1.ui.Container)findByName("urlComboRenderer", root);
    }

    public com.codename1.ui.Container findUrlComboRenderer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("urlComboRenderer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("urlComboRenderer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.ComponentGroup findVibrationGroup(Component root) {
        return (com.codename1.ui.ComponentGroup)findByName("vibrationGroup", root);
    }

    public com.codename1.ui.ComponentGroup findVibrationGroup() {
        com.codename1.ui.ComponentGroup cmp = (com.codename1.ui.ComponentGroup)findByName("vibrationGroup", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.ComponentGroup)findByName("vibrationGroup", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.list.MultiList findProvidersMultiList(Component root) {
        return (com.codename1.ui.list.MultiList)findByName("providersMultiList", root);
    }

    public com.codename1.ui.list.MultiList findProvidersMultiList() {
        com.codename1.ui.list.MultiList cmp = (com.codename1.ui.list.MultiList)findByName("providersMultiList", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.list.MultiList)findByName("providersMultiList", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findVideoLb(Component root) {
        return (com.codename1.ui.Label)findByName("videoLb", root);
    }

    public com.codename1.ui.Label findVideoLb() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("videoLb", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("videoLb", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.ComponentGroup findBrowserCg(Component root) {
        return (com.codename1.ui.ComponentGroup)findByName("browserCg", root);
    }

    public com.codename1.ui.ComponentGroup findBrowserCg() {
        com.codename1.ui.ComponentGroup cmp = (com.codename1.ui.ComponentGroup)findByName("browserCg", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.ComponentGroup)findByName("browserCg", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findThemesLabel(Component root) {
        return (com.codename1.ui.Label)findByName("themesLabel", root);
    }

    public com.codename1.ui.Label findThemesLabel() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("themesLabel", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("themesLabel", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextArea findUsernameTA(Component root) {
        return (com.codename1.ui.TextArea)findByName("usernameTA", root);
    }

    public com.codename1.ui.TextArea findUsernameTA() {
        com.codename1.ui.TextArea cmp = (com.codename1.ui.TextArea)findByName("usernameTA", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextArea)findByName("usernameTA", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.ComponentGroup findFontsRadioButtonGroup(Component root) {
        return (com.codename1.ui.ComponentGroup)findByName("fontsRadioButtonGroup", root);
    }

    public com.codename1.ui.ComponentGroup findFontsRadioButtonGroup() {
        com.codename1.ui.ComponentGroup cmp = (com.codename1.ui.ComponentGroup)findByName("fontsRadioButtonGroup", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.ComponentGroup)findByName("fontsRadioButtonGroup", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.ShareButton findShareButton(Component root) {
        return (com.codename1.components.ShareButton)findByName("shareButton", root);
    }

    public com.codename1.components.ShareButton findShareButton() {
        com.codename1.components.ShareButton cmp = (com.codename1.components.ShareButton)findByName("shareButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.ShareButton)findByName("shareButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.list.MultiList findPositionMultiList(Component root) {
        return (com.codename1.ui.list.MultiList)findByName("positionMultiList", root);
    }

    public com.codename1.ui.list.MultiList findPositionMultiList() {
        com.codename1.ui.list.MultiList cmp = (com.codename1.ui.list.MultiList)findByName("positionMultiList", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.list.MultiList)findByName("positionMultiList", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.list.MultiList findMyVideoMultiList(Component root) {
        return (com.codename1.ui.list.MultiList)findByName("My Video MultiList", root);
    }

    public com.codename1.ui.list.MultiList findMyVideoMultiList() {
        com.codename1.ui.list.MultiList cmp = (com.codename1.ui.list.MultiList)findByName("My Video MultiList", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.list.MultiList)findByName("My Video MultiList", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findThemesContainer(Component root) {
        return (com.codename1.ui.Container)findByName("themesContainer", root);
    }

    public com.codename1.ui.Container findThemesContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("themesContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("themesContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findShareContainer(Component root) {
        return (com.codename1.ui.Container)findByName("shareContainer", root);
    }

    public com.codename1.ui.Container findShareContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("shareContainer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("shareContainer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findNameIcon(Component root) {
        return (com.codename1.ui.Label)findByName("nameIcon", root);
    }

    public com.codename1.ui.Label findNameIcon() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("nameIcon", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("nameIcon", aboutToShowThisContainer);
        }
        return cmp;
    }

    public static final int COMMAND_MyContactsFormBack = 30;
    public static final int COMMAND_InsertPositionManuallyFormNext = 67;
    public static final int COMMAND_InsertAddressFormBack = 65;
    public static final int COMMAND_QRLoginBack = 15;
    public static final int COMMAND_FileBrowserExit = 41;
    public static final int COMMAND_LoadPhotoFormBack = 43;
    public static final int COMMAND_StorageFormLoad = 28;
    public static final int COMMAND_VideoViewBack = 50;
    public static final int COMMAND_SelectAddressFormBack = 76;
    public static final int COMMAND_MyImagesFormBack = 45;
    public static final int COMMAND_MediaPlayerFormBack = 21;
    public static final int COMMAND_SettingsBack = 9;
    public static final int COMMAND_MainSettings = 2;
    public static final int COMMAND_GpsFormBack = 63;
    public static final int COMMAND_FindYourPositionFormBack = 62;
    public static final int COMMAND_ServerDialogCancel = 82;
    public static final int COMMAND_ServerDialogOk = 81;
    public static final int COMMAND_MyMultimediaFormBack = 44;
    public static final int COMMAND_LoginExit = 61;
    public static final int COMMAND_MyPositionFormBack = 13;
    public static final int COMMAND_ProximityProvidersFormBack = 73;
    public static final int COMMAND_ContactsTestBack = 80;
    public static final int COMMAND_URLDialogBack = 56;
    public static final int COMMAND_MapsFormBack = 79;
    public static final int COMMAND_ProximityGoogleFormBack = 69;
    public static final int COMMAND_AudioListenBack = 53;
    public static final int COMMAND_MainLogout = 1;
    public static final int COMMAND_URLDialogVisit = 57;
    public static final int COMMAND_ContactFormBack = 33;
    public static final int COMMAND_FileBrowserViewFormBack = 39;
    public static final int COMMAND_InsertPositionManuallyFormBack = 64;
    public static final int COMMAND_GpsFormNext = 66;
    public static final int COMMAND_ImageCaptureBack = 46;
    public static final int COMMAND_ProximityFormBack = 14;
    public static final int COMMAND_ImageCaptureCapture = 47;
    public static final int COMMAND_StorageFormBack = 35;
    public static final int COMMAND_ContactsBack = 10;
    public static final int COMMAND_LoginLogin = 7;
    public static final int COMMAND_MyAudioFormBack = 71;
    public static final int COMMAND_WebBrowserFormBack = 54;
    public static final int COMMAND_CameraFormBack = 22;
    public static final int COMMAND_InsertAddressFormNext = 68;
    public static final int COMMAND_CameraFormSave = 24;
    public static final int COMMAND_VideoCaptureBack = 51;
    public static final int COMMAND_MyVideoFormBack = 52;
    public static final int COMMAND_WebBrowserFormSetUrl = 58;
    public static final int COMMAND_MapsProvidersBack = 59;
    public static final int COMMAND_MyGooglePositionFormBack = 60;
    public static final int COMMAND_StorageFormOpen = 29;
    public static final int COMMAND_StorageFormSave = 27;
    public static final int COMMAND_AyoolaContactsBack = 34;
    public static final int COMMAND_ShareBack = 16;
    public static final int COMMAND_LoginQRLogin = 8;

    protected boolean onMyContactsFormBack() {
        return false;
    }

    protected boolean onInsertPositionManuallyFormNext() {
        return false;
    }

    protected boolean onInsertAddressFormBack() {
        return false;
    }

    protected boolean onQRLoginBack() {
        return false;
    }

    protected boolean onFileBrowserExit() {
        return false;
    }

    protected boolean onLoadPhotoFormBack() {
        return false;
    }

    protected boolean onStorageFormLoad() {
        return false;
    }

    protected boolean onVideoViewBack() {
        return false;
    }

    protected boolean onSelectAddressFormBack() {
        return false;
    }

    protected boolean onMyImagesFormBack() {
        return false;
    }

    protected boolean onMediaPlayerFormBack() {
        return false;
    }

    protected boolean onSettingsBack() {
        return false;
    }

    protected boolean onMainSettings() {
        return false;
    }

    protected boolean onGpsFormBack() {
        return false;
    }

    protected boolean onFindYourPositionFormBack() {
        return false;
    }

    protected boolean onServerDialogCancel() {
        return false;
    }

    protected boolean onServerDialogOk() {
        return false;
    }

    protected boolean onMyMultimediaFormBack() {
        return false;
    }

    protected boolean onLoginExit() {
        return false;
    }

    protected boolean onMyPositionFormBack() {
        return false;
    }

    protected boolean onProximityProvidersFormBack() {
        return false;
    }

    protected boolean onContactsTestBack() {
        return false;
    }

    protected boolean onURLDialogBack() {
        return false;
    }

    protected boolean onMapsFormBack() {
        return false;
    }

    protected boolean onProximityGoogleFormBack() {
        return false;
    }

    protected boolean onAudioListenBack() {
        return false;
    }

    protected boolean onMainLogout() {
        return false;
    }

    protected boolean onURLDialogVisit() {
        return false;
    }

    protected boolean onContactFormBack() {
        return false;
    }

    protected boolean onFileBrowserViewFormBack() {
        return false;
    }

    protected boolean onInsertPositionManuallyFormBack() {
        return false;
    }

    protected boolean onGpsFormNext() {
        return false;
    }

    protected boolean onImageCaptureBack() {
        return false;
    }

    protected boolean onProximityFormBack() {
        return false;
    }

    protected boolean onImageCaptureCapture() {
        return false;
    }

    protected boolean onStorageFormBack() {
        return false;
    }

    protected boolean onContactsBack() {
        return false;
    }

    protected boolean onLoginLogin() {
        return false;
    }

    protected boolean onMyAudioFormBack() {
        return false;
    }

    protected boolean onWebBrowserFormBack() {
        return false;
    }

    protected boolean onCameraFormBack() {
        return false;
    }

    protected boolean onInsertAddressFormNext() {
        return false;
    }

    protected boolean onCameraFormSave() {
        return false;
    }

    protected boolean onVideoCaptureBack() {
        return false;
    }

    protected boolean onMyVideoFormBack() {
        return false;
    }

    protected boolean onWebBrowserFormSetUrl() {
        return false;
    }

    protected boolean onMapsProvidersBack() {
        return false;
    }

    protected boolean onMyGooglePositionFormBack() {
        return false;
    }

    protected boolean onStorageFormOpen() {
        return false;
    }

    protected boolean onStorageFormSave() {
        return false;
    }

    protected boolean onAyoolaContactsBack() {
        return false;
    }

    protected boolean onShareBack() {
        return false;
    }

    protected boolean onLoginQRLogin() {
        return false;
    }

    protected void processCommand(ActionEvent ev, Command cmd) {
        switch(cmd.getId()) {
            case COMMAND_MyContactsFormBack:
                if(onMyContactsFormBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_InsertPositionManuallyFormNext:
                if(onInsertPositionManuallyFormNext()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_InsertAddressFormBack:
                if(onInsertAddressFormBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_QRLoginBack:
                if(onQRLoginBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_FileBrowserExit:
                if(onFileBrowserExit()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_LoadPhotoFormBack:
                if(onLoadPhotoFormBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_StorageFormLoad:
                if(onStorageFormLoad()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_VideoViewBack:
                if(onVideoViewBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_SelectAddressFormBack:
                if(onSelectAddressFormBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_MyImagesFormBack:
                if(onMyImagesFormBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_MediaPlayerFormBack:
                if(onMediaPlayerFormBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_SettingsBack:
                if(onSettingsBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_MainSettings:
                if(onMainSettings()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_GpsFormBack:
                if(onGpsFormBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_FindYourPositionFormBack:
                if(onFindYourPositionFormBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_ServerDialogCancel:
                if(onServerDialogCancel()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_ServerDialogOk:
                if(onServerDialogOk()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_MyMultimediaFormBack:
                if(onMyMultimediaFormBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_LoginExit:
                if(onLoginExit()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_MyPositionFormBack:
                if(onMyPositionFormBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_ProximityProvidersFormBack:
                if(onProximityProvidersFormBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_ContactsTestBack:
                if(onContactsTestBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_URLDialogBack:
                if(onURLDialogBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_MapsFormBack:
                if(onMapsFormBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_ProximityGoogleFormBack:
                if(onProximityGoogleFormBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_AudioListenBack:
                if(onAudioListenBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_MainLogout:
                if(onMainLogout()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_URLDialogVisit:
                if(onURLDialogVisit()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_ContactFormBack:
                if(onContactFormBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_FileBrowserViewFormBack:
                if(onFileBrowserViewFormBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_InsertPositionManuallyFormBack:
                if(onInsertPositionManuallyFormBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_GpsFormNext:
                if(onGpsFormNext()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_ImageCaptureBack:
                if(onImageCaptureBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_ProximityFormBack:
                if(onProximityFormBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_ImageCaptureCapture:
                if(onImageCaptureCapture()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_StorageFormBack:
                if(onStorageFormBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_ContactsBack:
                if(onContactsBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_LoginLogin:
                if(onLoginLogin()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_MyAudioFormBack:
                if(onMyAudioFormBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_WebBrowserFormBack:
                if(onWebBrowserFormBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_CameraFormBack:
                if(onCameraFormBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_InsertAddressFormNext:
                if(onInsertAddressFormNext()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_CameraFormSave:
                if(onCameraFormSave()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_VideoCaptureBack:
                if(onVideoCaptureBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_MyVideoFormBack:
                if(onMyVideoFormBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_WebBrowserFormSetUrl:
                if(onWebBrowserFormSetUrl()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_MapsProvidersBack:
                if(onMapsProvidersBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_MyGooglePositionFormBack:
                if(onMyGooglePositionFormBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_StorageFormOpen:
                if(onStorageFormOpen()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_StorageFormSave:
                if(onStorageFormSave()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_AyoolaContactsBack:
                if(onAyoolaContactsBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_ShareBack:
                if(onShareBack()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_LoginQRLogin:
                if(onLoginQRLogin()) {
                    ev.consume();
                    return;
                }
                break;

        }
        if(ev.getComponent() != null) {
            handleComponentAction(ev.getComponent(), ev);
        }
    }

    protected void exitForm(Form f) {
        if("My Video Form".equals(f.getName())) {
            exitMyVideoForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("QR Login".equals(f.getName())) {
            exitQRLogin(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Nfc".equals(f.getName())) {
            exitNfc(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Web Browser Form".equals(f.getName())) {
            exitWebBrowserForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Contact Form".equals(f.getName())) {
            exitContactForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Save Photo Form".equals(f.getName())) {
            exitSavePhotoForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Settings".equals(f.getName())) {
            exitSettings(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("AyoolaContacts".equals(f.getName())) {
            exitAyoolaContacts(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Camera Form".equals(f.getName())) {
            exitCameraForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Maps Form".equals(f.getName())) {
            exitMapsForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("MyContacts Form".equals(f.getName())) {
            exitMyContactsForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Audio Listen".equals(f.getName())) {
            exitAudioListen(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Select Address Form".equals(f.getName())) {
            exitSelectAddressForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("serverDialog".equals(f.getName())) {
            exitServerDialog(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Find Your Position Form".equals(f.getName())) {
            exitFindYourPositionForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Video View".equals(f.getName())) {
            exitVideoView(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Proximity Form".equals(f.getName())) {
            exitProximityForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Gps Form".equals(f.getName())) {
            exitGpsForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Google Position Form".equals(f.getName())) {
            exitMyGooglePositionForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Position On Map Form".equals(f.getName())) {
            exitMyPositionOnMapForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Share Form".equals(f.getName())) {
            exitMyShareForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Proximity Providers Form".equals(f.getName())) {
            exitProximityProvidersForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Video Capture".equals(f.getName())) {
            exitVideoCapture(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("File Browser View Form".equals(f.getName())) {
            exitFileBrowserViewForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Image Capture".equals(f.getName())) {
            exitImageCapture(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Maps Providers".equals(f.getName())) {
            exitMapsProviders(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Load Photo Form".equals(f.getName())) {
            exitLoadPhotoForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Insert Position Manually Form".equals(f.getName())) {
            exitInsertPositionManuallyForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            exitMain(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Multimedia Form".equals(f.getName())) {
            exitMyMultimediaForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("urlComboRenderer".equals(f.getName())) {
            exitUrlComboRenderer(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Images Form".equals(f.getName())) {
            exitMyImagesForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Proximity Google Form".equals(f.getName())) {
            exitProximityGoogleForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Insert Address Form".equals(f.getName())) {
            exitInsertAddressForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Adress Form".equals(f.getName())) {
            exitAdressForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Load Form".equals(f.getName())) {
            exitLoadForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Media Player Form".equals(f.getName())) {
            exitMediaPlayerForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Splash".equals(f.getName())) {
            exitSplash(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Audio Form".equals(f.getName())) {
            exitMyAudioForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Share".equals(f.getName())) {
            exitShare(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Contacts".equals(f.getName())) {
            exitContacts(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Position Form".equals(f.getName())) {
            exitMyPositionForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Login".equals(f.getName())) {
            exitLogin(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Storage Form".equals(f.getName())) {
            exitStorageForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("File Browser".equals(f.getName())) {
            exitFileBrowser(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("ContactsTest".equals(f.getName())) {
            exitContactsTest(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("URL Dialog".equals(f.getName())) {
            exitURLDialog(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void exitMyVideoForm(Form f) {
    }


    protected void exitQRLogin(Form f) {
    }


    protected void exitNfc(Form f) {
    }


    protected void exitWebBrowserForm(Form f) {
    }


    protected void exitContactForm(Form f) {
    }


    protected void exitSavePhotoForm(Form f) {
    }


    protected void exitSettings(Form f) {
    }


    protected void exitAyoolaContacts(Form f) {
    }


    protected void exitCameraForm(Form f) {
    }


    protected void exitMapsForm(Form f) {
    }


    protected void exitMyContactsForm(Form f) {
    }


    protected void exitAudioListen(Form f) {
    }


    protected void exitSelectAddressForm(Form f) {
    }


    protected void exitServerDialog(Form f) {
    }


    protected void exitFindYourPositionForm(Form f) {
    }


    protected void exitVideoView(Form f) {
    }


    protected void exitProximityForm(Form f) {
    }


    protected void exitGpsForm(Form f) {
    }


    protected void exitMyGooglePositionForm(Form f) {
    }


    protected void exitMyPositionOnMapForm(Form f) {
    }


    protected void exitMyShareForm(Form f) {
    }


    protected void exitProximityProvidersForm(Form f) {
    }


    protected void exitVideoCapture(Form f) {
    }


    protected void exitFileBrowserViewForm(Form f) {
    }


    protected void exitImageCapture(Form f) {
    }


    protected void exitMapsProviders(Form f) {
    }


    protected void exitLoadPhotoForm(Form f) {
    }


    protected void exitInsertPositionManuallyForm(Form f) {
    }


    protected void exitMain(Form f) {
    }


    protected void exitMyMultimediaForm(Form f) {
    }


    protected void exitUrlComboRenderer(Form f) {
    }


    protected void exitMyImagesForm(Form f) {
    }


    protected void exitProximityGoogleForm(Form f) {
    }


    protected void exitInsertAddressForm(Form f) {
    }


    protected void exitAdressForm(Form f) {
    }


    protected void exitLoadForm(Form f) {
    }


    protected void exitMediaPlayerForm(Form f) {
    }


    protected void exitSplash(Form f) {
    }


    protected void exitMyAudioForm(Form f) {
    }


    protected void exitShare(Form f) {
    }


    protected void exitContacts(Form f) {
    }


    protected void exitMyPositionForm(Form f) {
    }


    protected void exitLogin(Form f) {
    }


    protected void exitStorageForm(Form f) {
    }


    protected void exitFileBrowser(Form f) {
    }


    protected void exitContactsTest(Form f) {
    }


    protected void exitURLDialog(Form f) {
    }

    protected void beforeShow(Form f) {
    aboutToShowThisContainer = f;
        if("My Video Form".equals(f.getName())) {
            beforeMyVideoForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("QR Login".equals(f.getName())) {
            beforeQRLogin(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Nfc".equals(f.getName())) {
            beforeNfc(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Web Browser Form".equals(f.getName())) {
            beforeWebBrowserForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Contact Form".equals(f.getName())) {
            beforeContactForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Save Photo Form".equals(f.getName())) {
            beforeSavePhotoForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Settings".equals(f.getName())) {
            beforeSettings(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("AyoolaContacts".equals(f.getName())) {
            beforeAyoolaContacts(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Camera Form".equals(f.getName())) {
            beforeCameraForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Maps Form".equals(f.getName())) {
            beforeMapsForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("MyContacts Form".equals(f.getName())) {
            beforeMyContactsForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Audio Listen".equals(f.getName())) {
            beforeAudioListen(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Select Address Form".equals(f.getName())) {
            beforeSelectAddressForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("serverDialog".equals(f.getName())) {
            beforeServerDialog(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Find Your Position Form".equals(f.getName())) {
            beforeFindYourPositionForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Video View".equals(f.getName())) {
            beforeVideoView(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Proximity Form".equals(f.getName())) {
            beforeProximityForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Gps Form".equals(f.getName())) {
            beforeGpsForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Google Position Form".equals(f.getName())) {
            beforeMyGooglePositionForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Position On Map Form".equals(f.getName())) {
            beforeMyPositionOnMapForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Share Form".equals(f.getName())) {
            beforeMyShareForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Proximity Providers Form".equals(f.getName())) {
            beforeProximityProvidersForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Video Capture".equals(f.getName())) {
            beforeVideoCapture(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("File Browser View Form".equals(f.getName())) {
            beforeFileBrowserViewForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Image Capture".equals(f.getName())) {
            beforeImageCapture(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Maps Providers".equals(f.getName())) {
            beforeMapsProviders(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Load Photo Form".equals(f.getName())) {
            beforeLoadPhotoForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Insert Position Manually Form".equals(f.getName())) {
            beforeInsertPositionManuallyForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            beforeMain(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Multimedia Form".equals(f.getName())) {
            beforeMyMultimediaForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("urlComboRenderer".equals(f.getName())) {
            beforeUrlComboRenderer(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Images Form".equals(f.getName())) {
            beforeMyImagesForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Proximity Google Form".equals(f.getName())) {
            beforeProximityGoogleForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Insert Address Form".equals(f.getName())) {
            beforeInsertAddressForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Adress Form".equals(f.getName())) {
            beforeAdressForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Load Form".equals(f.getName())) {
            beforeLoadForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Media Player Form".equals(f.getName())) {
            beforeMediaPlayerForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Splash".equals(f.getName())) {
            beforeSplash(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Audio Form".equals(f.getName())) {
            beforeMyAudioForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Share".equals(f.getName())) {
            beforeShare(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Contacts".equals(f.getName())) {
            beforeContacts(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Position Form".equals(f.getName())) {
            beforeMyPositionForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Login".equals(f.getName())) {
            beforeLogin(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Storage Form".equals(f.getName())) {
            beforeStorageForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("File Browser".equals(f.getName())) {
            beforeFileBrowser(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("ContactsTest".equals(f.getName())) {
            beforeContactsTest(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("URL Dialog".equals(f.getName())) {
            beforeURLDialog(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void beforeMyVideoForm(Form f) {
    }


    protected void beforeQRLogin(Form f) {
    }


    protected void beforeNfc(Form f) {
    }


    protected void beforeWebBrowserForm(Form f) {
    }


    protected void beforeContactForm(Form f) {
    }


    protected void beforeSavePhotoForm(Form f) {
    }


    protected void beforeSettings(Form f) {
    }


    protected void beforeAyoolaContacts(Form f) {
    }


    protected void beforeCameraForm(Form f) {
    }


    protected void beforeMapsForm(Form f) {
    }


    protected void beforeMyContactsForm(Form f) {
    }


    protected void beforeAudioListen(Form f) {
    }


    protected void beforeSelectAddressForm(Form f) {
    }


    protected void beforeServerDialog(Form f) {
    }


    protected void beforeFindYourPositionForm(Form f) {
    }


    protected void beforeVideoView(Form f) {
    }


    protected void beforeProximityForm(Form f) {
    }


    protected void beforeGpsForm(Form f) {
    }


    protected void beforeMyGooglePositionForm(Form f) {
    }


    protected void beforeMyPositionOnMapForm(Form f) {
    }


    protected void beforeMyShareForm(Form f) {
    }


    protected void beforeProximityProvidersForm(Form f) {
    }


    protected void beforeVideoCapture(Form f) {
    }


    protected void beforeFileBrowserViewForm(Form f) {
    }


    protected void beforeImageCapture(Form f) {
    }


    protected void beforeMapsProviders(Form f) {
    }


    protected void beforeLoadPhotoForm(Form f) {
    }


    protected void beforeInsertPositionManuallyForm(Form f) {
    }


    protected void beforeMain(Form f) {
    }


    protected void beforeMyMultimediaForm(Form f) {
    }


    protected void beforeUrlComboRenderer(Form f) {
    }


    protected void beforeMyImagesForm(Form f) {
    }


    protected void beforeProximityGoogleForm(Form f) {
    }


    protected void beforeInsertAddressForm(Form f) {
    }


    protected void beforeAdressForm(Form f) {
    }


    protected void beforeLoadForm(Form f) {
    }


    protected void beforeMediaPlayerForm(Form f) {
    }


    protected void beforeSplash(Form f) {
    }


    protected void beforeMyAudioForm(Form f) {
    }


    protected void beforeShare(Form f) {
    }


    protected void beforeContacts(Form f) {
    }


    protected void beforeMyPositionForm(Form f) {
    }


    protected void beforeLogin(Form f) {
    }


    protected void beforeStorageForm(Form f) {
    }


    protected void beforeFileBrowser(Form f) {
    }


    protected void beforeContactsTest(Form f) {
    }


    protected void beforeURLDialog(Form f) {
    }

    protected void beforeShowContainer(Container c) {
        aboutToShowThisContainer = c;
        if("My Video Form".equals(c.getName())) {
            beforeContainerMyVideoForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("QR Login".equals(c.getName())) {
            beforeContainerQRLogin(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Nfc".equals(c.getName())) {
            beforeContainerNfc(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Web Browser Form".equals(c.getName())) {
            beforeContainerWebBrowserForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Contact Form".equals(c.getName())) {
            beforeContainerContactForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Save Photo Form".equals(c.getName())) {
            beforeContainerSavePhotoForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Settings".equals(c.getName())) {
            beforeContainerSettings(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("AyoolaContacts".equals(c.getName())) {
            beforeContainerAyoolaContacts(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Camera Form".equals(c.getName())) {
            beforeContainerCameraForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Maps Form".equals(c.getName())) {
            beforeContainerMapsForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("MyContacts Form".equals(c.getName())) {
            beforeContainerMyContactsForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Audio Listen".equals(c.getName())) {
            beforeContainerAudioListen(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Select Address Form".equals(c.getName())) {
            beforeContainerSelectAddressForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("serverDialog".equals(c.getName())) {
            beforeContainerServerDialog(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Find Your Position Form".equals(c.getName())) {
            beforeContainerFindYourPositionForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Video View".equals(c.getName())) {
            beforeContainerVideoView(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Proximity Form".equals(c.getName())) {
            beforeContainerProximityForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Gps Form".equals(c.getName())) {
            beforeContainerGpsForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Google Position Form".equals(c.getName())) {
            beforeContainerMyGooglePositionForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Position On Map Form".equals(c.getName())) {
            beforeContainerMyPositionOnMapForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Share Form".equals(c.getName())) {
            beforeContainerMyShareForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Proximity Providers Form".equals(c.getName())) {
            beforeContainerProximityProvidersForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Video Capture".equals(c.getName())) {
            beforeContainerVideoCapture(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("File Browser View Form".equals(c.getName())) {
            beforeContainerFileBrowserViewForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Image Capture".equals(c.getName())) {
            beforeContainerImageCapture(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Maps Providers".equals(c.getName())) {
            beforeContainerMapsProviders(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Load Photo Form".equals(c.getName())) {
            beforeContainerLoadPhotoForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Insert Position Manually Form".equals(c.getName())) {
            beforeContainerInsertPositionManuallyForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(c.getName())) {
            beforeContainerMain(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Multimedia Form".equals(c.getName())) {
            beforeContainerMyMultimediaForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("urlComboRenderer".equals(c.getName())) {
            beforeContainerUrlComboRenderer(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Images Form".equals(c.getName())) {
            beforeContainerMyImagesForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Proximity Google Form".equals(c.getName())) {
            beforeContainerProximityGoogleForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Insert Address Form".equals(c.getName())) {
            beforeContainerInsertAddressForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Adress Form".equals(c.getName())) {
            beforeContainerAdressForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Load Form".equals(c.getName())) {
            beforeContainerLoadForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Media Player Form".equals(c.getName())) {
            beforeContainerMediaPlayerForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Splash".equals(c.getName())) {
            beforeContainerSplash(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Audio Form".equals(c.getName())) {
            beforeContainerMyAudioForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Share".equals(c.getName())) {
            beforeContainerShare(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Contacts".equals(c.getName())) {
            beforeContainerContacts(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Position Form".equals(c.getName())) {
            beforeContainerMyPositionForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Login".equals(c.getName())) {
            beforeContainerLogin(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Storage Form".equals(c.getName())) {
            beforeContainerStorageForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("File Browser".equals(c.getName())) {
            beforeContainerFileBrowser(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("ContactsTest".equals(c.getName())) {
            beforeContainerContactsTest(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("URL Dialog".equals(c.getName())) {
            beforeContainerURLDialog(c);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void beforeContainerMyVideoForm(Container c) {
    }


    protected void beforeContainerQRLogin(Container c) {
    }


    protected void beforeContainerNfc(Container c) {
    }


    protected void beforeContainerWebBrowserForm(Container c) {
    }


    protected void beforeContainerContactForm(Container c) {
    }


    protected void beforeContainerSavePhotoForm(Container c) {
    }


    protected void beforeContainerSettings(Container c) {
    }


    protected void beforeContainerAyoolaContacts(Container c) {
    }


    protected void beforeContainerCameraForm(Container c) {
    }


    protected void beforeContainerMapsForm(Container c) {
    }


    protected void beforeContainerMyContactsForm(Container c) {
    }


    protected void beforeContainerAudioListen(Container c) {
    }


    protected void beforeContainerSelectAddressForm(Container c) {
    }


    protected void beforeContainerServerDialog(Container c) {
    }


    protected void beforeContainerFindYourPositionForm(Container c) {
    }


    protected void beforeContainerVideoView(Container c) {
    }


    protected void beforeContainerProximityForm(Container c) {
    }


    protected void beforeContainerGpsForm(Container c) {
    }


    protected void beforeContainerMyGooglePositionForm(Container c) {
    }


    protected void beforeContainerMyPositionOnMapForm(Container c) {
    }


    protected void beforeContainerMyShareForm(Container c) {
    }


    protected void beforeContainerProximityProvidersForm(Container c) {
    }


    protected void beforeContainerVideoCapture(Container c) {
    }


    protected void beforeContainerFileBrowserViewForm(Container c) {
    }


    protected void beforeContainerImageCapture(Container c) {
    }


    protected void beforeContainerMapsProviders(Container c) {
    }


    protected void beforeContainerLoadPhotoForm(Container c) {
    }


    protected void beforeContainerInsertPositionManuallyForm(Container c) {
    }


    protected void beforeContainerMain(Container c) {
    }


    protected void beforeContainerMyMultimediaForm(Container c) {
    }


    protected void beforeContainerUrlComboRenderer(Container c) {
    }


    protected void beforeContainerMyImagesForm(Container c) {
    }


    protected void beforeContainerProximityGoogleForm(Container c) {
    }


    protected void beforeContainerInsertAddressForm(Container c) {
    }


    protected void beforeContainerAdressForm(Container c) {
    }


    protected void beforeContainerLoadForm(Container c) {
    }


    protected void beforeContainerMediaPlayerForm(Container c) {
    }


    protected void beforeContainerSplash(Container c) {
    }


    protected void beforeContainerMyAudioForm(Container c) {
    }


    protected void beforeContainerShare(Container c) {
    }


    protected void beforeContainerContacts(Container c) {
    }


    protected void beforeContainerMyPositionForm(Container c) {
    }


    protected void beforeContainerLogin(Container c) {
    }


    protected void beforeContainerStorageForm(Container c) {
    }


    protected void beforeContainerFileBrowser(Container c) {
    }


    protected void beforeContainerContactsTest(Container c) {
    }


    protected void beforeContainerURLDialog(Container c) {
    }

    protected void postShow(Form f) {
        if("My Video Form".equals(f.getName())) {
            postMyVideoForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("QR Login".equals(f.getName())) {
            postQRLogin(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Nfc".equals(f.getName())) {
            postNfc(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Web Browser Form".equals(f.getName())) {
            postWebBrowserForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Contact Form".equals(f.getName())) {
            postContactForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Save Photo Form".equals(f.getName())) {
            postSavePhotoForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Settings".equals(f.getName())) {
            postSettings(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("AyoolaContacts".equals(f.getName())) {
            postAyoolaContacts(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Camera Form".equals(f.getName())) {
            postCameraForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Maps Form".equals(f.getName())) {
            postMapsForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("MyContacts Form".equals(f.getName())) {
            postMyContactsForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Audio Listen".equals(f.getName())) {
            postAudioListen(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Select Address Form".equals(f.getName())) {
            postSelectAddressForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("serverDialog".equals(f.getName())) {
            postServerDialog(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Find Your Position Form".equals(f.getName())) {
            postFindYourPositionForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Video View".equals(f.getName())) {
            postVideoView(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Proximity Form".equals(f.getName())) {
            postProximityForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Gps Form".equals(f.getName())) {
            postGpsForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Google Position Form".equals(f.getName())) {
            postMyGooglePositionForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Position On Map Form".equals(f.getName())) {
            postMyPositionOnMapForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Share Form".equals(f.getName())) {
            postMyShareForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Proximity Providers Form".equals(f.getName())) {
            postProximityProvidersForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Video Capture".equals(f.getName())) {
            postVideoCapture(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("File Browser View Form".equals(f.getName())) {
            postFileBrowserViewForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Image Capture".equals(f.getName())) {
            postImageCapture(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Maps Providers".equals(f.getName())) {
            postMapsProviders(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Load Photo Form".equals(f.getName())) {
            postLoadPhotoForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Insert Position Manually Form".equals(f.getName())) {
            postInsertPositionManuallyForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            postMain(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Multimedia Form".equals(f.getName())) {
            postMyMultimediaForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("urlComboRenderer".equals(f.getName())) {
            postUrlComboRenderer(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Images Form".equals(f.getName())) {
            postMyImagesForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Proximity Google Form".equals(f.getName())) {
            postProximityGoogleForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Insert Address Form".equals(f.getName())) {
            postInsertAddressForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Adress Form".equals(f.getName())) {
            postAdressForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Load Form".equals(f.getName())) {
            postLoadForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Media Player Form".equals(f.getName())) {
            postMediaPlayerForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Splash".equals(f.getName())) {
            postSplash(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Audio Form".equals(f.getName())) {
            postMyAudioForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Share".equals(f.getName())) {
            postShare(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Contacts".equals(f.getName())) {
            postContacts(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Position Form".equals(f.getName())) {
            postMyPositionForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Login".equals(f.getName())) {
            postLogin(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Storage Form".equals(f.getName())) {
            postStorageForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("File Browser".equals(f.getName())) {
            postFileBrowser(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("ContactsTest".equals(f.getName())) {
            postContactsTest(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("URL Dialog".equals(f.getName())) {
            postURLDialog(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void postMyVideoForm(Form f) {
    }


    protected void postQRLogin(Form f) {
    }


    protected void postNfc(Form f) {
    }


    protected void postWebBrowserForm(Form f) {
    }


    protected void postContactForm(Form f) {
    }


    protected void postSavePhotoForm(Form f) {
    }


    protected void postSettings(Form f) {
    }


    protected void postAyoolaContacts(Form f) {
    }


    protected void postCameraForm(Form f) {
    }


    protected void postMapsForm(Form f) {
    }


    protected void postMyContactsForm(Form f) {
    }


    protected void postAudioListen(Form f) {
    }


    protected void postSelectAddressForm(Form f) {
    }


    protected void postServerDialog(Form f) {
    }


    protected void postFindYourPositionForm(Form f) {
    }


    protected void postVideoView(Form f) {
    }


    protected void postProximityForm(Form f) {
    }


    protected void postGpsForm(Form f) {
    }


    protected void postMyGooglePositionForm(Form f) {
    }


    protected void postMyPositionOnMapForm(Form f) {
    }


    protected void postMyShareForm(Form f) {
    }


    protected void postProximityProvidersForm(Form f) {
    }


    protected void postVideoCapture(Form f) {
    }


    protected void postFileBrowserViewForm(Form f) {
    }


    protected void postImageCapture(Form f) {
    }


    protected void postMapsProviders(Form f) {
    }


    protected void postLoadPhotoForm(Form f) {
    }


    protected void postInsertPositionManuallyForm(Form f) {
    }


    protected void postMain(Form f) {
    }


    protected void postMyMultimediaForm(Form f) {
    }


    protected void postUrlComboRenderer(Form f) {
    }


    protected void postMyImagesForm(Form f) {
    }


    protected void postProximityGoogleForm(Form f) {
    }


    protected void postInsertAddressForm(Form f) {
    }


    protected void postAdressForm(Form f) {
    }


    protected void postLoadForm(Form f) {
    }


    protected void postMediaPlayerForm(Form f) {
    }


    protected void postSplash(Form f) {
    }


    protected void postMyAudioForm(Form f) {
    }


    protected void postShare(Form f) {
    }


    protected void postContacts(Form f) {
    }


    protected void postMyPositionForm(Form f) {
    }


    protected void postLogin(Form f) {
    }


    protected void postStorageForm(Form f) {
    }


    protected void postFileBrowser(Form f) {
    }


    protected void postContactsTest(Form f) {
    }


    protected void postURLDialog(Form f) {
    }

    protected void postShowContainer(Container c) {
        if("My Video Form".equals(c.getName())) {
            postContainerMyVideoForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("QR Login".equals(c.getName())) {
            postContainerQRLogin(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Nfc".equals(c.getName())) {
            postContainerNfc(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Web Browser Form".equals(c.getName())) {
            postContainerWebBrowserForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Contact Form".equals(c.getName())) {
            postContainerContactForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Save Photo Form".equals(c.getName())) {
            postContainerSavePhotoForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Settings".equals(c.getName())) {
            postContainerSettings(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("AyoolaContacts".equals(c.getName())) {
            postContainerAyoolaContacts(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Camera Form".equals(c.getName())) {
            postContainerCameraForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Maps Form".equals(c.getName())) {
            postContainerMapsForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("MyContacts Form".equals(c.getName())) {
            postContainerMyContactsForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Audio Listen".equals(c.getName())) {
            postContainerAudioListen(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Select Address Form".equals(c.getName())) {
            postContainerSelectAddressForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("serverDialog".equals(c.getName())) {
            postContainerServerDialog(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Find Your Position Form".equals(c.getName())) {
            postContainerFindYourPositionForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Video View".equals(c.getName())) {
            postContainerVideoView(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Proximity Form".equals(c.getName())) {
            postContainerProximityForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Gps Form".equals(c.getName())) {
            postContainerGpsForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Google Position Form".equals(c.getName())) {
            postContainerMyGooglePositionForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Position On Map Form".equals(c.getName())) {
            postContainerMyPositionOnMapForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Share Form".equals(c.getName())) {
            postContainerMyShareForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Proximity Providers Form".equals(c.getName())) {
            postContainerProximityProvidersForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Video Capture".equals(c.getName())) {
            postContainerVideoCapture(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("File Browser View Form".equals(c.getName())) {
            postContainerFileBrowserViewForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Image Capture".equals(c.getName())) {
            postContainerImageCapture(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Maps Providers".equals(c.getName())) {
            postContainerMapsProviders(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Load Photo Form".equals(c.getName())) {
            postContainerLoadPhotoForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Insert Position Manually Form".equals(c.getName())) {
            postContainerInsertPositionManuallyForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(c.getName())) {
            postContainerMain(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Multimedia Form".equals(c.getName())) {
            postContainerMyMultimediaForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("urlComboRenderer".equals(c.getName())) {
            postContainerUrlComboRenderer(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Images Form".equals(c.getName())) {
            postContainerMyImagesForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Proximity Google Form".equals(c.getName())) {
            postContainerProximityGoogleForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Insert Address Form".equals(c.getName())) {
            postContainerInsertAddressForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Adress Form".equals(c.getName())) {
            postContainerAdressForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Load Form".equals(c.getName())) {
            postContainerLoadForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Media Player Form".equals(c.getName())) {
            postContainerMediaPlayerForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Splash".equals(c.getName())) {
            postContainerSplash(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Audio Form".equals(c.getName())) {
            postContainerMyAudioForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Share".equals(c.getName())) {
            postContainerShare(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Contacts".equals(c.getName())) {
            postContainerContacts(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Position Form".equals(c.getName())) {
            postContainerMyPositionForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Login".equals(c.getName())) {
            postContainerLogin(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Storage Form".equals(c.getName())) {
            postContainerStorageForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("File Browser".equals(c.getName())) {
            postContainerFileBrowser(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("ContactsTest".equals(c.getName())) {
            postContainerContactsTest(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("URL Dialog".equals(c.getName())) {
            postContainerURLDialog(c);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void postContainerMyVideoForm(Container c) {
    }


    protected void postContainerQRLogin(Container c) {
    }


    protected void postContainerNfc(Container c) {
    }


    protected void postContainerWebBrowserForm(Container c) {
    }


    protected void postContainerContactForm(Container c) {
    }


    protected void postContainerSavePhotoForm(Container c) {
    }


    protected void postContainerSettings(Container c) {
    }


    protected void postContainerAyoolaContacts(Container c) {
    }


    protected void postContainerCameraForm(Container c) {
    }


    protected void postContainerMapsForm(Container c) {
    }


    protected void postContainerMyContactsForm(Container c) {
    }


    protected void postContainerAudioListen(Container c) {
    }


    protected void postContainerSelectAddressForm(Container c) {
    }


    protected void postContainerServerDialog(Container c) {
    }


    protected void postContainerFindYourPositionForm(Container c) {
    }


    protected void postContainerVideoView(Container c) {
    }


    protected void postContainerProximityForm(Container c) {
    }


    protected void postContainerGpsForm(Container c) {
    }


    protected void postContainerMyGooglePositionForm(Container c) {
    }


    protected void postContainerMyPositionOnMapForm(Container c) {
    }


    protected void postContainerMyShareForm(Container c) {
    }


    protected void postContainerProximityProvidersForm(Container c) {
    }


    protected void postContainerVideoCapture(Container c) {
    }


    protected void postContainerFileBrowserViewForm(Container c) {
    }


    protected void postContainerImageCapture(Container c) {
    }


    protected void postContainerMapsProviders(Container c) {
    }


    protected void postContainerLoadPhotoForm(Container c) {
    }


    protected void postContainerInsertPositionManuallyForm(Container c) {
    }


    protected void postContainerMain(Container c) {
    }


    protected void postContainerMyMultimediaForm(Container c) {
    }


    protected void postContainerUrlComboRenderer(Container c) {
    }


    protected void postContainerMyImagesForm(Container c) {
    }


    protected void postContainerProximityGoogleForm(Container c) {
    }


    protected void postContainerInsertAddressForm(Container c) {
    }


    protected void postContainerAdressForm(Container c) {
    }


    protected void postContainerLoadForm(Container c) {
    }


    protected void postContainerMediaPlayerForm(Container c) {
    }


    protected void postContainerSplash(Container c) {
    }


    protected void postContainerMyAudioForm(Container c) {
    }


    protected void postContainerShare(Container c) {
    }


    protected void postContainerContacts(Container c) {
    }


    protected void postContainerMyPositionForm(Container c) {
    }


    protected void postContainerLogin(Container c) {
    }


    protected void postContainerStorageForm(Container c) {
    }


    protected void postContainerFileBrowser(Container c) {
    }


    protected void postContainerContactsTest(Container c) {
    }


    protected void postContainerURLDialog(Container c) {
    }

    protected void onCreateRoot(String rootName) {
        if("My Video Form".equals(rootName)) {
            onCreateMyVideoForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("QR Login".equals(rootName)) {
            onCreateQRLogin();
            aboutToShowThisContainer = null;
            return;
        }

        if("Nfc".equals(rootName)) {
            onCreateNfc();
            aboutToShowThisContainer = null;
            return;
        }

        if("Web Browser Form".equals(rootName)) {
            onCreateWebBrowserForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Contact Form".equals(rootName)) {
            onCreateContactForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Save Photo Form".equals(rootName)) {
            onCreateSavePhotoForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Settings".equals(rootName)) {
            onCreateSettings();
            aboutToShowThisContainer = null;
            return;
        }

        if("AyoolaContacts".equals(rootName)) {
            onCreateAyoolaContacts();
            aboutToShowThisContainer = null;
            return;
        }

        if("Camera Form".equals(rootName)) {
            onCreateCameraForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Maps Form".equals(rootName)) {
            onCreateMapsForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("MyContacts Form".equals(rootName)) {
            onCreateMyContactsForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Audio Listen".equals(rootName)) {
            onCreateAudioListen();
            aboutToShowThisContainer = null;
            return;
        }

        if("Select Address Form".equals(rootName)) {
            onCreateSelectAddressForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("serverDialog".equals(rootName)) {
            onCreateServerDialog();
            aboutToShowThisContainer = null;
            return;
        }

        if("Find Your Position Form".equals(rootName)) {
            onCreateFindYourPositionForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Video View".equals(rootName)) {
            onCreateVideoView();
            aboutToShowThisContainer = null;
            return;
        }

        if("Proximity Form".equals(rootName)) {
            onCreateProximityForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Gps Form".equals(rootName)) {
            onCreateGpsForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("My Google Position Form".equals(rootName)) {
            onCreateMyGooglePositionForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("My Position On Map Form".equals(rootName)) {
            onCreateMyPositionOnMapForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("My Share Form".equals(rootName)) {
            onCreateMyShareForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Proximity Providers Form".equals(rootName)) {
            onCreateProximityProvidersForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Video Capture".equals(rootName)) {
            onCreateVideoCapture();
            aboutToShowThisContainer = null;
            return;
        }

        if("File Browser View Form".equals(rootName)) {
            onCreateFileBrowserViewForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Image Capture".equals(rootName)) {
            onCreateImageCapture();
            aboutToShowThisContainer = null;
            return;
        }

        if("Maps Providers".equals(rootName)) {
            onCreateMapsProviders();
            aboutToShowThisContainer = null;
            return;
        }

        if("Load Photo Form".equals(rootName)) {
            onCreateLoadPhotoForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Insert Position Manually Form".equals(rootName)) {
            onCreateInsertPositionManuallyForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(rootName)) {
            onCreateMain();
            aboutToShowThisContainer = null;
            return;
        }

        if("My Multimedia Form".equals(rootName)) {
            onCreateMyMultimediaForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("urlComboRenderer".equals(rootName)) {
            onCreateUrlComboRenderer();
            aboutToShowThisContainer = null;
            return;
        }

        if("My Images Form".equals(rootName)) {
            onCreateMyImagesForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Proximity Google Form".equals(rootName)) {
            onCreateProximityGoogleForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Insert Address Form".equals(rootName)) {
            onCreateInsertAddressForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Adress Form".equals(rootName)) {
            onCreateAdressForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Load Form".equals(rootName)) {
            onCreateLoadForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Media Player Form".equals(rootName)) {
            onCreateMediaPlayerForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Splash".equals(rootName)) {
            onCreateSplash();
            aboutToShowThisContainer = null;
            return;
        }

        if("My Audio Form".equals(rootName)) {
            onCreateMyAudioForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Share".equals(rootName)) {
            onCreateShare();
            aboutToShowThisContainer = null;
            return;
        }

        if("Contacts".equals(rootName)) {
            onCreateContacts();
            aboutToShowThisContainer = null;
            return;
        }

        if("My Position Form".equals(rootName)) {
            onCreateMyPositionForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("Login".equals(rootName)) {
            onCreateLogin();
            aboutToShowThisContainer = null;
            return;
        }

        if("Storage Form".equals(rootName)) {
            onCreateStorageForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("File Browser".equals(rootName)) {
            onCreateFileBrowser();
            aboutToShowThisContainer = null;
            return;
        }

        if("ContactsTest".equals(rootName)) {
            onCreateContactsTest();
            aboutToShowThisContainer = null;
            return;
        }

        if("URL Dialog".equals(rootName)) {
            onCreateURLDialog();
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void onCreateMyVideoForm() {
    }


    protected void onCreateQRLogin() {
    }


    protected void onCreateNfc() {
    }


    protected void onCreateWebBrowserForm() {
    }


    protected void onCreateContactForm() {
    }


    protected void onCreateSavePhotoForm() {
    }


    protected void onCreateSettings() {
    }


    protected void onCreateAyoolaContacts() {
    }


    protected void onCreateCameraForm() {
    }


    protected void onCreateMapsForm() {
    }


    protected void onCreateMyContactsForm() {
    }


    protected void onCreateAudioListen() {
    }


    protected void onCreateSelectAddressForm() {
    }


    protected void onCreateServerDialog() {
    }


    protected void onCreateFindYourPositionForm() {
    }


    protected void onCreateVideoView() {
    }


    protected void onCreateProximityForm() {
    }


    protected void onCreateGpsForm() {
    }


    protected void onCreateMyGooglePositionForm() {
    }


    protected void onCreateMyPositionOnMapForm() {
    }


    protected void onCreateMyShareForm() {
    }


    protected void onCreateProximityProvidersForm() {
    }


    protected void onCreateVideoCapture() {
    }


    protected void onCreateFileBrowserViewForm() {
    }


    protected void onCreateImageCapture() {
    }


    protected void onCreateMapsProviders() {
    }


    protected void onCreateLoadPhotoForm() {
    }


    protected void onCreateInsertPositionManuallyForm() {
    }


    protected void onCreateMain() {
    }


    protected void onCreateMyMultimediaForm() {
    }


    protected void onCreateUrlComboRenderer() {
    }


    protected void onCreateMyImagesForm() {
    }


    protected void onCreateProximityGoogleForm() {
    }


    protected void onCreateInsertAddressForm() {
    }


    protected void onCreateAdressForm() {
    }


    protected void onCreateLoadForm() {
    }


    protected void onCreateMediaPlayerForm() {
    }


    protected void onCreateSplash() {
    }


    protected void onCreateMyAudioForm() {
    }


    protected void onCreateShare() {
    }


    protected void onCreateContacts() {
    }


    protected void onCreateMyPositionForm() {
    }


    protected void onCreateLogin() {
    }


    protected void onCreateStorageForm() {
    }


    protected void onCreateFileBrowser() {
    }


    protected void onCreateContactsTest() {
    }


    protected void onCreateURLDialog() {
    }

    protected Hashtable getFormState(Form f) {
        Hashtable h = super.getFormState(f);
        if("My Video Form".equals(f.getName())) {
            getStateMyVideoForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("QR Login".equals(f.getName())) {
            getStateQRLogin(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Nfc".equals(f.getName())) {
            getStateNfc(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Web Browser Form".equals(f.getName())) {
            getStateWebBrowserForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Contact Form".equals(f.getName())) {
            getStateContactForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Save Photo Form".equals(f.getName())) {
            getStateSavePhotoForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Settings".equals(f.getName())) {
            getStateSettings(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("AyoolaContacts".equals(f.getName())) {
            getStateAyoolaContacts(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Camera Form".equals(f.getName())) {
            getStateCameraForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Maps Form".equals(f.getName())) {
            getStateMapsForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("MyContacts Form".equals(f.getName())) {
            getStateMyContactsForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Audio Listen".equals(f.getName())) {
            getStateAudioListen(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Select Address Form".equals(f.getName())) {
            getStateSelectAddressForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("serverDialog".equals(f.getName())) {
            getStateServerDialog(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Find Your Position Form".equals(f.getName())) {
            getStateFindYourPositionForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Video View".equals(f.getName())) {
            getStateVideoView(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Proximity Form".equals(f.getName())) {
            getStateProximityForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Gps Form".equals(f.getName())) {
            getStateGpsForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("My Google Position Form".equals(f.getName())) {
            getStateMyGooglePositionForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("My Position On Map Form".equals(f.getName())) {
            getStateMyPositionOnMapForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("My Share Form".equals(f.getName())) {
            getStateMyShareForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Proximity Providers Form".equals(f.getName())) {
            getStateProximityProvidersForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Video Capture".equals(f.getName())) {
            getStateVideoCapture(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("File Browser View Form".equals(f.getName())) {
            getStateFileBrowserViewForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Image Capture".equals(f.getName())) {
            getStateImageCapture(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Maps Providers".equals(f.getName())) {
            getStateMapsProviders(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Load Photo Form".equals(f.getName())) {
            getStateLoadPhotoForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Insert Position Manually Form".equals(f.getName())) {
            getStateInsertPositionManuallyForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Main".equals(f.getName())) {
            getStateMain(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("My Multimedia Form".equals(f.getName())) {
            getStateMyMultimediaForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("urlComboRenderer".equals(f.getName())) {
            getStateUrlComboRenderer(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("My Images Form".equals(f.getName())) {
            getStateMyImagesForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Proximity Google Form".equals(f.getName())) {
            getStateProximityGoogleForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Insert Address Form".equals(f.getName())) {
            getStateInsertAddressForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Adress Form".equals(f.getName())) {
            getStateAdressForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Load Form".equals(f.getName())) {
            getStateLoadForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Media Player Form".equals(f.getName())) {
            getStateMediaPlayerForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Splash".equals(f.getName())) {
            getStateSplash(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("My Audio Form".equals(f.getName())) {
            getStateMyAudioForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Share".equals(f.getName())) {
            getStateShare(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Contacts".equals(f.getName())) {
            getStateContacts(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("My Position Form".equals(f.getName())) {
            getStateMyPositionForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Login".equals(f.getName())) {
            getStateLogin(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Storage Form".equals(f.getName())) {
            getStateStorageForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("File Browser".equals(f.getName())) {
            getStateFileBrowser(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("ContactsTest".equals(f.getName())) {
            getStateContactsTest(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("URL Dialog".equals(f.getName())) {
            getStateURLDialog(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

            return h;
    }


    protected void getStateMyVideoForm(Form f, Hashtable h) {
    }


    protected void getStateQRLogin(Form f, Hashtable h) {
    }


    protected void getStateNfc(Form f, Hashtable h) {
    }


    protected void getStateWebBrowserForm(Form f, Hashtable h) {
    }


    protected void getStateContactForm(Form f, Hashtable h) {
    }


    protected void getStateSavePhotoForm(Form f, Hashtable h) {
    }


    protected void getStateSettings(Form f, Hashtable h) {
    }


    protected void getStateAyoolaContacts(Form f, Hashtable h) {
    }


    protected void getStateCameraForm(Form f, Hashtable h) {
    }


    protected void getStateMapsForm(Form f, Hashtable h) {
    }


    protected void getStateMyContactsForm(Form f, Hashtable h) {
    }


    protected void getStateAudioListen(Form f, Hashtable h) {
    }


    protected void getStateSelectAddressForm(Form f, Hashtable h) {
    }


    protected void getStateServerDialog(Form f, Hashtable h) {
    }


    protected void getStateFindYourPositionForm(Form f, Hashtable h) {
    }


    protected void getStateVideoView(Form f, Hashtable h) {
    }


    protected void getStateProximityForm(Form f, Hashtable h) {
    }


    protected void getStateGpsForm(Form f, Hashtable h) {
    }


    protected void getStateMyGooglePositionForm(Form f, Hashtable h) {
    }


    protected void getStateMyPositionOnMapForm(Form f, Hashtable h) {
    }


    protected void getStateMyShareForm(Form f, Hashtable h) {
    }


    protected void getStateProximityProvidersForm(Form f, Hashtable h) {
    }


    protected void getStateVideoCapture(Form f, Hashtable h) {
    }


    protected void getStateFileBrowserViewForm(Form f, Hashtable h) {
    }


    protected void getStateImageCapture(Form f, Hashtable h) {
    }


    protected void getStateMapsProviders(Form f, Hashtable h) {
    }


    protected void getStateLoadPhotoForm(Form f, Hashtable h) {
    }


    protected void getStateInsertPositionManuallyForm(Form f, Hashtable h) {
    }


    protected void getStateMain(Form f, Hashtable h) {
    }


    protected void getStateMyMultimediaForm(Form f, Hashtable h) {
    }


    protected void getStateUrlComboRenderer(Form f, Hashtable h) {
    }


    protected void getStateMyImagesForm(Form f, Hashtable h) {
    }


    protected void getStateProximityGoogleForm(Form f, Hashtable h) {
    }


    protected void getStateInsertAddressForm(Form f, Hashtable h) {
    }


    protected void getStateAdressForm(Form f, Hashtable h) {
    }


    protected void getStateLoadForm(Form f, Hashtable h) {
    }


    protected void getStateMediaPlayerForm(Form f, Hashtable h) {
    }


    protected void getStateSplash(Form f, Hashtable h) {
    }


    protected void getStateMyAudioForm(Form f, Hashtable h) {
    }


    protected void getStateShare(Form f, Hashtable h) {
    }


    protected void getStateContacts(Form f, Hashtable h) {
    }


    protected void getStateMyPositionForm(Form f, Hashtable h) {
    }


    protected void getStateLogin(Form f, Hashtable h) {
    }


    protected void getStateStorageForm(Form f, Hashtable h) {
    }


    protected void getStateFileBrowser(Form f, Hashtable h) {
    }


    protected void getStateContactsTest(Form f, Hashtable h) {
    }


    protected void getStateURLDialog(Form f, Hashtable h) {
    }

    protected void setFormState(Form f, Hashtable state) {
        super.setFormState(f, state);
        if("My Video Form".equals(f.getName())) {
            setStateMyVideoForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("QR Login".equals(f.getName())) {
            setStateQRLogin(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Nfc".equals(f.getName())) {
            setStateNfc(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Web Browser Form".equals(f.getName())) {
            setStateWebBrowserForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Contact Form".equals(f.getName())) {
            setStateContactForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Save Photo Form".equals(f.getName())) {
            setStateSavePhotoForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Settings".equals(f.getName())) {
            setStateSettings(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("AyoolaContacts".equals(f.getName())) {
            setStateAyoolaContacts(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Camera Form".equals(f.getName())) {
            setStateCameraForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Maps Form".equals(f.getName())) {
            setStateMapsForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("MyContacts Form".equals(f.getName())) {
            setStateMyContactsForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Audio Listen".equals(f.getName())) {
            setStateAudioListen(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Select Address Form".equals(f.getName())) {
            setStateSelectAddressForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("serverDialog".equals(f.getName())) {
            setStateServerDialog(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Find Your Position Form".equals(f.getName())) {
            setStateFindYourPositionForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Video View".equals(f.getName())) {
            setStateVideoView(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Proximity Form".equals(f.getName())) {
            setStateProximityForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Gps Form".equals(f.getName())) {
            setStateGpsForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Google Position Form".equals(f.getName())) {
            setStateMyGooglePositionForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Position On Map Form".equals(f.getName())) {
            setStateMyPositionOnMapForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Share Form".equals(f.getName())) {
            setStateMyShareForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Proximity Providers Form".equals(f.getName())) {
            setStateProximityProvidersForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Video Capture".equals(f.getName())) {
            setStateVideoCapture(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("File Browser View Form".equals(f.getName())) {
            setStateFileBrowserViewForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Image Capture".equals(f.getName())) {
            setStateImageCapture(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Maps Providers".equals(f.getName())) {
            setStateMapsProviders(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Load Photo Form".equals(f.getName())) {
            setStateLoadPhotoForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Insert Position Manually Form".equals(f.getName())) {
            setStateInsertPositionManuallyForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            setStateMain(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Multimedia Form".equals(f.getName())) {
            setStateMyMultimediaForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("urlComboRenderer".equals(f.getName())) {
            setStateUrlComboRenderer(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Images Form".equals(f.getName())) {
            setStateMyImagesForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Proximity Google Form".equals(f.getName())) {
            setStateProximityGoogleForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Insert Address Form".equals(f.getName())) {
            setStateInsertAddressForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Adress Form".equals(f.getName())) {
            setStateAdressForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Load Form".equals(f.getName())) {
            setStateLoadForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Media Player Form".equals(f.getName())) {
            setStateMediaPlayerForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Splash".equals(f.getName())) {
            setStateSplash(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Audio Form".equals(f.getName())) {
            setStateMyAudioForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Share".equals(f.getName())) {
            setStateShare(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Contacts".equals(f.getName())) {
            setStateContacts(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("My Position Form".equals(f.getName())) {
            setStateMyPositionForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Login".equals(f.getName())) {
            setStateLogin(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Storage Form".equals(f.getName())) {
            setStateStorageForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("File Browser".equals(f.getName())) {
            setStateFileBrowser(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("ContactsTest".equals(f.getName())) {
            setStateContactsTest(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("URL Dialog".equals(f.getName())) {
            setStateURLDialog(f, state);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void setStateMyVideoForm(Form f, Hashtable state) {
    }


    protected void setStateQRLogin(Form f, Hashtable state) {
    }


    protected void setStateNfc(Form f, Hashtable state) {
    }


    protected void setStateWebBrowserForm(Form f, Hashtable state) {
    }


    protected void setStateContactForm(Form f, Hashtable state) {
    }


    protected void setStateSavePhotoForm(Form f, Hashtable state) {
    }


    protected void setStateSettings(Form f, Hashtable state) {
    }


    protected void setStateAyoolaContacts(Form f, Hashtable state) {
    }


    protected void setStateCameraForm(Form f, Hashtable state) {
    }


    protected void setStateMapsForm(Form f, Hashtable state) {
    }


    protected void setStateMyContactsForm(Form f, Hashtable state) {
    }


    protected void setStateAudioListen(Form f, Hashtable state) {
    }


    protected void setStateSelectAddressForm(Form f, Hashtable state) {
    }


    protected void setStateServerDialog(Form f, Hashtable state) {
    }


    protected void setStateFindYourPositionForm(Form f, Hashtable state) {
    }


    protected void setStateVideoView(Form f, Hashtable state) {
    }


    protected void setStateProximityForm(Form f, Hashtable state) {
    }


    protected void setStateGpsForm(Form f, Hashtable state) {
    }


    protected void setStateMyGooglePositionForm(Form f, Hashtable state) {
    }


    protected void setStateMyPositionOnMapForm(Form f, Hashtable state) {
    }


    protected void setStateMyShareForm(Form f, Hashtable state) {
    }


    protected void setStateProximityProvidersForm(Form f, Hashtable state) {
    }


    protected void setStateVideoCapture(Form f, Hashtable state) {
    }


    protected void setStateFileBrowserViewForm(Form f, Hashtable state) {
    }


    protected void setStateImageCapture(Form f, Hashtable state) {
    }


    protected void setStateMapsProviders(Form f, Hashtable state) {
    }


    protected void setStateLoadPhotoForm(Form f, Hashtable state) {
    }


    protected void setStateInsertPositionManuallyForm(Form f, Hashtable state) {
    }


    protected void setStateMain(Form f, Hashtable state) {
    }


    protected void setStateMyMultimediaForm(Form f, Hashtable state) {
    }


    protected void setStateUrlComboRenderer(Form f, Hashtable state) {
    }


    protected void setStateMyImagesForm(Form f, Hashtable state) {
    }


    protected void setStateProximityGoogleForm(Form f, Hashtable state) {
    }


    protected void setStateInsertAddressForm(Form f, Hashtable state) {
    }


    protected void setStateAdressForm(Form f, Hashtable state) {
    }


    protected void setStateLoadForm(Form f, Hashtable state) {
    }


    protected void setStateMediaPlayerForm(Form f, Hashtable state) {
    }


    protected void setStateSplash(Form f, Hashtable state) {
    }


    protected void setStateMyAudioForm(Form f, Hashtable state) {
    }


    protected void setStateShare(Form f, Hashtable state) {
    }


    protected void setStateContacts(Form f, Hashtable state) {
    }


    protected void setStateMyPositionForm(Form f, Hashtable state) {
    }


    protected void setStateLogin(Form f, Hashtable state) {
    }


    protected void setStateStorageForm(Form f, Hashtable state) {
    }


    protected void setStateFileBrowser(Form f, Hashtable state) {
    }


    protected void setStateContactsTest(Form f, Hashtable state) {
    }


    protected void setStateURLDialog(Form f, Hashtable state) {
    }

    protected boolean setListModel(List cmp) {
        String listName = cmp.getName();
        if("urlComboBox".equals(listName)) {
            return initListModelUrlComboBox(cmp);
        }
        if("serverCombo".equals(listName)) {
            return initListModelServerCombo(cmp);
        }
        if("folderBrowserList".equals(listName)) {
            return initListModelFolderBrowserList(cmp);
        }
        if("Multimedia List".equals(listName)) {
            return initListModelMultimediaList(cmp);
        }
        if("proximityProvidersMultiList".equals(listName)) {
            return initListModelProximityProvidersMultiList(cmp);
        }
        if("mapsMultiList".equals(listName)) {
            return initListModelMapsMultiList(cmp);
        }
        if("MultiList".equals(listName)) {
            return initListModelMultiList(cmp);
        }
        if("My Audio MultiList".equals(listName)) {
            return initListModelMyAudioMultiList(cmp);
        }
        if("allContactsMultiList".equals(listName)) {
            return initListModelAllContactsMultiList(cmp);
        }
        if("contactsTestMultiList".equals(listName)) {
            return initListModelContactsTestMultiList(cmp);
        }
        if("shareMultiList".equals(listName)) {
            return initListModelShareMultiList(cmp);
        }
        if("My Images MultiList".equals(listName)) {
            return initListModelMyImagesMultiList(cmp);
        }
        if("providersMultiList".equals(listName)) {
            return initListModelProvidersMultiList(cmp);
        }
        if("positionMultiList".equals(listName)) {
            return initListModelPositionMultiList(cmp);
        }
        if("My Video MultiList".equals(listName)) {
            return initListModelMyVideoMultiList(cmp);
        }
        return super.setListModel(cmp);
    }

    protected boolean initListModelUrlComboBox(List cmp) {
        return false;
    }

    protected boolean initListModelServerCombo(List cmp) {
        return false;
    }

    protected boolean initListModelFolderBrowserList(List cmp) {
        return false;
    }

    protected boolean initListModelMultimediaList(List cmp) {
        return false;
    }

    protected boolean initListModelProximityProvidersMultiList(List cmp) {
        return false;
    }

    protected boolean initListModelMapsMultiList(List cmp) {
        return false;
    }

    protected boolean initListModelMultiList(List cmp) {
        return false;
    }

    protected boolean initListModelMyAudioMultiList(List cmp) {
        return false;
    }

    protected boolean initListModelAllContactsMultiList(List cmp) {
        return false;
    }

    protected boolean initListModelContactsTestMultiList(List cmp) {
        return false;
    }

    protected boolean initListModelShareMultiList(List cmp) {
        return false;
    }

    protected boolean initListModelMyImagesMultiList(List cmp) {
        return false;
    }

    protected boolean initListModelProvidersMultiList(List cmp) {
        return false;
    }

    protected boolean initListModelPositionMultiList(List cmp) {
        return false;
    }

    protected boolean initListModelMyVideoMultiList(List cmp) {
        return false;
    }

    protected void handleComponentAction(Component c, ActionEvent event) {
        Container rootContainerAncestor = getRootAncestor(c);
        if(rootContainerAncestor == null) return;
        String rootContainerName = rootContainerAncestor.getName();
        Container leadParentContainer = c.getParent().getLeadParent();
        if(leadParentContainer != null && leadParentContainer.getClass() != Container.class) {
            c = c.getParent().getLeadParent();
        }
        if(rootContainerName == null) return;
        if(rootContainerName.equals("My Video Form")) {
            if("My Video MultiList".equals(c.getName())) {
                onMyVideoForm_MyVideoMultiListAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Contact Form")) {
            if("nameField".equals(c.getName())) {
                onContactForm_NameFieldAction(c, event);
                return;
            }
            if("surnameField".equals(c.getName())) {
                onContactForm_SurnameFieldAction(c, event);
                return;
            }
            if("phoneField".equals(c.getName())) {
                onContactForm_PhoneFieldAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Settings")) {
            if("blackRadioButton".equals(c.getName())) {
                onSettings_BlackRadioButtonAction(c, event);
                return;
            }
            if("whiteRadioButton".equals(c.getName())) {
                onSettings_WhiteRadioButtonAction(c, event);
                return;
            }
            if("yellowRadioButton".equals(c.getName())) {
                onSettings_YellowRadioButtonAction(c, event);
                return;
            }
            if("leatherRadioButton".equals(c.getName())) {
                onSettings_LeatherRadioButtonAction(c, event);
                return;
            }
            if("smallRadioButton".equals(c.getName())) {
                onSettings_SmallRadioButtonAction(c, event);
                return;
            }
            if("mediumRadioButton".equals(c.getName())) {
                onSettings_MediumRadioButtonAction(c, event);
                return;
            }
            if("largeRadioButton".equals(c.getName())) {
                onSettings_LargeRadioButtonAction(c, event);
                return;
            }
            if("veryLargeRadioButton".equals(c.getName())) {
                onSettings_VeryLargeRadioButtonAction(c, event);
                return;
            }
            if("hugeRadioButton".equals(c.getName())) {
                onSettings_HugeRadioButtonAction(c, event);
                return;
            }
            if("englishRadioButton".equals(c.getName())) {
                onSettings_EnglishRadioButtonAction(c, event);
                return;
            }
            if("greekRadioButton".equals(c.getName())) {
                onSettings_GreekRadioButtonAction(c, event);
                return;
            }
            if("spanishRadioButton".equals(c.getName())) {
                onSettings_SpanishRadioButtonAction(c, event);
                return;
            }
            if("germanRadioButton".equals(c.getName())) {
                onSettings_GermanRadioButtonAction(c, event);
                return;
            }
            if("soundYesRadioButton".equals(c.getName())) {
                onSettings_SoundYesRadioButtonAction(c, event);
                return;
            }
            if("soundNoRadioButton".equals(c.getName())) {
                onSettings_SoundNoRadioButtonAction(c, event);
                return;
            }
            if("offVibrationButton".equals(c.getName())) {
                onSettings_OffVibrationButtonAction(c, event);
                return;
            }
            if("shortVibrationButton".equals(c.getName())) {
                onSettings_ShortVibrationButtonAction(c, event);
                return;
            }
            if("longVibrationButton".equals(c.getName())) {
                onSettings_LongVibrationButtonAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Maps Form")) {
            if("mapsMultiList".equals(c.getName())) {
                onMapsForm_MapsMultiListAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Audio Listen")) {
            if("audioPlayButton".equals(c.getName())) {
                onAudioListen_AudioPlayButtonAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("serverDialog")) {
            if("serverTA".equals(c.getName())) {
                onServerDialog_ServerTAAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Find Your Position Form")) {
            if("positionMultiList".equals(c.getName())) {
                onFindYourPositionForm_PositionMultiListAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Video View")) {
            if("Button".equals(c.getName())) {
                onVideoView_ButtonAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("My Share Form")) {
            if("shareMultiList".equals(c.getName())) {
                onMyShareForm_ShareMultiListAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Proximity Providers Form")) {
            if("proximityProvidersMultiList".equals(c.getName())) {
                onProximityProvidersForm_ProximityProvidersMultiListAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Maps Providers")) {
            if("providersMultiList".equals(c.getName())) {
                onMapsProviders_ProvidersMultiListAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Insert Position Manually Form")) {
            if("longitudeField".equals(c.getName())) {
                onInsertPositionManuallyForm_LongitudeFieldAction(c, event);
                return;
            }
            if("latitudeField".equals(c.getName())) {
                onInsertPositionManuallyForm_LatitudeFieldAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Main")) {
            if("MultiList".equals(c.getName())) {
                onMain_MultiListAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("My Multimedia Form")) {
            if("Multimedia List".equals(c.getName())) {
                onMyMultimediaForm_MultimediaListAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("My Images Form")) {
            if("My Images MultiList".equals(c.getName())) {
                onMyImagesForm_MyImagesMultiListAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Insert Address Form")) {
            if("streetField".equals(c.getName())) {
                onInsertAddressForm_StreetFieldAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("My Audio Form")) {
            if("My Audio MultiList".equals(c.getName())) {
                onMyAudioForm_MyAudioMultiListAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Share")) {
            if("shareButton".equals(c.getName())) {
                onShare_ShareButtonAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Contacts")) {
            if("allContactsMultiList".equals(c.getName())) {
                onContacts_AllContactsMultiListAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Login")) {
            if("usernameTA".equals(c.getName())) {
                onLogin_UsernameTAAction(c, event);
                return;
            }
            if("serverCombo".equals(c.getName())) {
                onLogin_ServerComboAction(c, event);
                return;
            }
            if("loginBtn".equals(c.getName())) {
                onLogin_LoginBtnAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Storage Form")) {
            if("area".equals(c.getName())) {
                onStorageForm_AreaAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("File Browser")) {
            if("folderBrowserList".equals(c.getName())) {
                onFileBrowser_FolderBrowserListAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("ContactsTest")) {
            if("contactsTestMultiList".equals(c.getName())) {
                onContactsTest_ContactsTestMultiListAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("URL Dialog")) {
            if("urlTextField".equals(c.getName())) {
                onURLDialog_UrlTextFieldAction(c, event);
                return;
            }
            if("urlComboBox".equals(c.getName())) {
                onURLDialog_UrlComboBoxAction(c, event);
                return;
            }
        }
    }

      protected void onMyVideoForm_MyVideoMultiListAction(Component c, ActionEvent event) {
      }

      protected void onContactForm_NameFieldAction(Component c, ActionEvent event) {
      }

      protected void onContactForm_SurnameFieldAction(Component c, ActionEvent event) {
      }

      protected void onContactForm_PhoneFieldAction(Component c, ActionEvent event) {
      }

      protected void onSettings_BlackRadioButtonAction(Component c, ActionEvent event) {
      }

      protected void onSettings_WhiteRadioButtonAction(Component c, ActionEvent event) {
      }

      protected void onSettings_YellowRadioButtonAction(Component c, ActionEvent event) {
      }

      protected void onSettings_LeatherRadioButtonAction(Component c, ActionEvent event) {
      }

      protected void onSettings_SmallRadioButtonAction(Component c, ActionEvent event) {
      }

      protected void onSettings_MediumRadioButtonAction(Component c, ActionEvent event) {
      }

      protected void onSettings_LargeRadioButtonAction(Component c, ActionEvent event) {
      }

      protected void onSettings_VeryLargeRadioButtonAction(Component c, ActionEvent event) {
      }

      protected void onSettings_HugeRadioButtonAction(Component c, ActionEvent event) {
      }

      protected void onSettings_EnglishRadioButtonAction(Component c, ActionEvent event) {
      }

      protected void onSettings_GreekRadioButtonAction(Component c, ActionEvent event) {
      }

      protected void onSettings_SpanishRadioButtonAction(Component c, ActionEvent event) {
      }

      protected void onSettings_GermanRadioButtonAction(Component c, ActionEvent event) {
      }

      protected void onSettings_SoundYesRadioButtonAction(Component c, ActionEvent event) {
      }

      protected void onSettings_SoundNoRadioButtonAction(Component c, ActionEvent event) {
      }

      protected void onSettings_OffVibrationButtonAction(Component c, ActionEvent event) {
      }

      protected void onSettings_ShortVibrationButtonAction(Component c, ActionEvent event) {
      }

      protected void onSettings_LongVibrationButtonAction(Component c, ActionEvent event) {
      }

      protected void onMapsForm_MapsMultiListAction(Component c, ActionEvent event) {
      }

      protected void onAudioListen_AudioPlayButtonAction(Component c, ActionEvent event) {
      }

      protected void onServerDialog_ServerTAAction(Component c, ActionEvent event) {
      }

      protected void onFindYourPositionForm_PositionMultiListAction(Component c, ActionEvent event) {
      }

      protected void onVideoView_ButtonAction(Component c, ActionEvent event) {
      }

      protected void onMyShareForm_ShareMultiListAction(Component c, ActionEvent event) {
      }

      protected void onProximityProvidersForm_ProximityProvidersMultiListAction(Component c, ActionEvent event) {
      }

      protected void onMapsProviders_ProvidersMultiListAction(Component c, ActionEvent event) {
      }

      protected void onInsertPositionManuallyForm_LongitudeFieldAction(Component c, ActionEvent event) {
      }

      protected void onInsertPositionManuallyForm_LatitudeFieldAction(Component c, ActionEvent event) {
      }

      protected void onMain_MultiListAction(Component c, ActionEvent event) {
      }

      protected void onMyMultimediaForm_MultimediaListAction(Component c, ActionEvent event) {
      }

      protected void onMyImagesForm_MyImagesMultiListAction(Component c, ActionEvent event) {
      }

      protected void onInsertAddressForm_StreetFieldAction(Component c, ActionEvent event) {
      }

      protected void onMyAudioForm_MyAudioMultiListAction(Component c, ActionEvent event) {
      }

      protected void onShare_ShareButtonAction(Component c, ActionEvent event) {
      }

      protected void onContacts_AllContactsMultiListAction(Component c, ActionEvent event) {
      }

      protected void onLogin_UsernameTAAction(Component c, ActionEvent event) {
      }

      protected void onLogin_ServerComboAction(Component c, ActionEvent event) {
      }

      protected void onLogin_LoginBtnAction(Component c, ActionEvent event) {
      }

      protected void onStorageForm_AreaAction(Component c, ActionEvent event) {
      }

      protected void onFileBrowser_FolderBrowserListAction(Component c, ActionEvent event) {
      }

      protected void onContactsTest_ContactsTestMultiListAction(Component c, ActionEvent event) {
      }

      protected void onURLDialog_UrlTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onURLDialog_UrlComboBoxAction(Component c, ActionEvent event) {
      }

}
