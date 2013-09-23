package gr.certh.hit.app.map;

import java.util.Vector;

import com.codename1.maps.layers.PointLayer;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public  class MyActionListener implements ActionListener {
	   
    private int localInt;
    private Vector pois;
    
    public MyActionListener(int actionNumber, Vector poisVector){
        localInt = actionNumber;
        pois = poisVector;
    };
   
    public void actionPerformed(ActionEvent evt) {
        if (localInt==0) {
            System.out.println("no action event because it is null");
        } else {
            switch (localInt) {
            case 1:
            {
                PointLayer p = (PointLayer) evt.getSource();
                System.out.println("pressed " + p);
                
                for(int i=0; i<pois.size(); i++) {
                	Poi poi = (Poi)pois.get(i);
                    if(((String)p.getName()).equalsIgnoreCase(poi.getName())){
                    	String email = poi.getEmail();
                    	String accessibilityStatus = poi.getAccStatus();
                    	String accessibilityDescription = poi.getAccDescription();
                    	String postcode = poi.getPostcode();
                    	String street = poi.getStreet();
                    	String streetNo = poi.getStreetNo();
                    	String telephone = poi.getTelephone();
                    	String url = poi.getUrl();
                    	String city = poi.getCity();
                    	String name = poi.getName();
                    	
                    	String details = name + "\n" + "Address: " + "\n" + street + " " + streetNo + "\n" + city + "\n" + "Tel: " + telephone + "\n" + "Url: " + url + "\n" + "Accessibility: " + "\n" + accessibilityDescription;
                    	System.out.println(details);
                    	
                    	Dialog.show("Details", "" + details, "Ok", null);
                    } 
                }
            }
            case 2:
            {
            	System.out.println("Tatoudou!");
            }
                break;
            default:
                break;
            }
        }
    }
   
    public static MyActionListener getInstance(int avg, Vector vec){
        MyActionListener abc = new MyActionListener(avg, vec);
        return abc;
    }


}

