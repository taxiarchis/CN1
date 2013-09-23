package gr.certh.hit.app.media;

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

import java.io.IOException;
import java.io.InputStream;

import com.codename1.capture.Capture;
import com.codename1.codescan.CodeScanner;
import com.codename1.codescan.ScanResult;
import com.codename1.io.Log;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 * Demo of the camera related functionality including QR code etc.
 *
 * @author Shai Almog
 */
public class AudioBrowser {
	
	static Media media;
	static int volume;

//    public String getDisplayName() {
//        return "Camera";
//    }

    public static Container createDemo() {
        final ComponentGroup cnt = new ComponentGroup();
        final Button gallery = new Button("Pick From Gallery");
        cnt.addComponent(gallery);
        gallery.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                
                Display.getInstance().openImageGallery(new ActionListener() {

                    public void actionPerformed(ActionEvent evt) {
                        try {
                            if(evt == null){
                                System.out.println("user cancelled");
                                return;
                            }
                            
                            String path = (String) evt.getSource();                  
                            // FOR AUDIO!
//                    		InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/siren.wav");
                    		
                    		media = MediaManager.createMedia("file:////E:/Music/siren.wav", false);
                    		
//                    		try {
//                    			media = MediaManager.createMedia(is, "audio/wav", null);
                    			volume = media.getVolume();
                    			// f2.addComponent(BorderLayout.CENTER, audioLb);
                    			media.play();
                    			// f2.show();
//                    		} catch (IOException e) {
//                    			e.printStackTrace();
//                    		}
                    		
                    		// FOR IMAGE! (Original ver.)
                            // we are opening the image with the file handle since the image
                            // is large this method can scale it down dynamically to a manageable
                            // size that doesn't exceed the heap
                            
//                            Image i = Image.createImage(path);
//                            Label image = new Label(i.scaledWidth(Display.getInstance().getDisplayWidth() / 2));
//                            if(cnt.getComponentCount() > 2) {
//                                cnt.removeComponent(cnt.getComponentAt(2));
//                            }
//                            cnt.addComponent(image);
//                            cnt.getComponentForm().revalidate();
                            
                            
                        } catch (Exception ex) {
                        	Dialog.show("1_" + ex.toString(),ex.getMessage(), "ok", "cancel");
                        }                        
                    }
                });                
            }
        });
        
        
        final Button capture = new Button("Capture");
        cnt.addComponent(capture);
        capture.addActionListener(new ActionListener() {
        	
            public void actionPerformed(ActionEvent evt) {
//                try {
//                    String value = Capture.capturePhoto(Display.getInstance().getDisplayWidth() / 2, -1);
//                    if(value != null) {
//                        Label image = new Label(Image.createImage(value));
//                        if(cnt.getComponentCount() > 2) {
//                            cnt.removeComponent(cnt.getComponentAt(2));
//                        }
//                        cnt.addComponent(image);
//                        cnt.getComponentForm().revalidate();
//
//                    }
//                } catch (Exception ex) {
//                    Log.e(ex);
//                    Dialog.show("Error", "" + ex, "OK", null);
//                }                        
            	
            	
            	try {
            		
					media = MediaManager.createMedia("file:///E:/Music/Bach.mp3", false);
					
					
				} catch (IOException e) {
					Dialog.show("2_" + e.toString(),e.getMessage(), "ok", "cancel");
				}
            	volume = media.getVolume();
            	media.play();
            	
            }
        });

        final Button captureAudio = new Button("Capture Audio");
        cnt.addComponent(captureAudio);
        captureAudio.addActionListener(new ActionListener() {
        	
            public void actionPerformed(ActionEvent evt) {
//                final String value = Capture.captureAudio();
//                if(value != null) {
//                    Button playCapturedAudio = new Button("Play Captured Audio");
//                    cnt.addComponent(playCapturedAudio);
//                    cnt.getComponentForm().revalidate();
//                    playCapturedAudio.addActionListener(new ActionListener() {
//                        public void actionPerformed(ActionEvent evt) {
//                            try {
//                                Media m = MediaManager.createMedia(value, false);
//                                m.play();
//                            } catch (IOException ex) {
//                                Log.e(ex);
//                                Dialog.show("Error", "" + ex, "OK", null);
//                            }
//                        }
//                    });
//                    
//                }

            	
            	try {
					media = MediaManager.createMedia("file://E:/Music/Bach.mp3", false);
					
					 if (media.isNativePlayerMode()) {
                         media.setNativePlayerMode(true);
                     }
					
				} catch (IOException e) {
					Dialog.show("3_" + e.toString(),e.getMessage(), "ok", "cancel");
				}
            	volume = media.getVolume();
            	media.play();
            }
            
        });
        
        
//        if(CodeScanner.getInstance() != null) {
//            final Button qrCode = new Button("Scan QR");
//            cnt.addComponent(qrCode);
//            qrCode.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent evt) {
//                    CodeScanner.getInstance().scanQRCode(new ScanResult() {
//                        public void scanCompleted(String contents, String formatName, byte[] rawBytes) {
//                            qrCode.setText("QR: " + contents);
//                        }
//
//                        public void scanCanceled() {
//                        }
//
//                        public void scanError(int errorCode, String message) {
//                        }
//                    });
//                }
//            });
//            final Button barCode = new Button("Scan Barcode");
//            cnt.addComponent(barCode);
//            barCode.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent evt) {
//                    CodeScanner.getInstance().scanBarCode(new ScanResult() {
//                        public void scanCompleted(String contents, String formatName, byte[] rawBytes) {
//                            barCode.setText("Bar: " + contents);
//                        }
//
//                        public void scanCanceled() {
//                        }
//
//                        public void scanError(int errorCode, String message) {
//                        }
//                    });
//                }
//            });
//        }
        
        return cnt;
    }
    
}

