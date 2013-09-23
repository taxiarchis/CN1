package gr.certh.hit.app;

import java.util.Vector;

import com.codename1.ui.EncodedImage;
import com.codename1.ui.Label;

public class SwitchPhotos implements Runnable {

	Thread t;
	Vector myPhotos;
	Label label;

	public SwitchPhotos(Vector photos, Label photoLb) {
		myPhotos = photos;
		label = photoLb;
		t = new Thread(this, "sotiri");
		System.out.println("Sotiri: " + t);
		t.start(); // Start the thread
	}

	public void run() {
		try {
			for (int i = 0; i < myPhotos.size(); i++) {
				System.out.println("Child Thread: " + i);
				Thread.sleep(2000);
				label.setIcon(getCurrentPhoto(i));
				System.out.println("***************** label.getComponentForm().getName(): " + label.getComponentForm().getName());
				label.getComponentForm().revalidate();
			}
		} catch (InterruptedException e) {
			System.out.println("Child interrupted.");
		}
		System.out.println("Exiting child thread.");
	}
	
	public EncodedImage getCurrentPhoto(int index) {
		System.out.println(myPhotos);
		return (EncodedImage)myPhotos.get(index);
	}

}
