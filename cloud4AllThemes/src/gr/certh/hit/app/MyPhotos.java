package gr.certh.hit.app;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;

import com.codename1.io.Externalizable;
import com.codename1.io.Util;
import com.codename1.ui.EncodedImage;

public class MyPhotos implements Externalizable{

	Vector photos;
	int numberOfStoredPhotos;
	
	public MyPhotos(){
		photos = new Vector();
		numberOfStoredPhotos = 0;
	}

	public Vector getPhotos() {
		return photos;
	}

	public void setPhotos(Vector photos) {
		this.photos = photos;
		numberOfStoredPhotos = photos.size();
	}
	
	public EncodedImage getPhoto(int index) { // EncodedImage
		return (EncodedImage)photos.get(index);
	}
	
	public void addPhoto(EncodedImage photo) {
		int length = photos.size();
//		if(numberOfStoredPhotos < length)
//		{
			photos.add(photo);
			numberOfStoredPhotos++;
//		}
	}

	public int getVersion() {
		return 1;
	}

	public void externalize(DataOutputStream out) throws IOException {
		Util.writeObject(photos, out);
	}

	public void internalize(int version, DataInputStream in) throws IOException {
		photos = (Vector)Util.readObject(in);
	}

	public String getObjectId() {
		return "MyPhotos";
	}
}
