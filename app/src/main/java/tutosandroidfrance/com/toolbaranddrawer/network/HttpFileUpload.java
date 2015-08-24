package tutosandroidfrance.com.toolbaranddrawer.network;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import tutosandroidfrance.com.toolbaranddrawer.constants.ApiConstants;

public class HttpFileUpload{
	int serverResponseCode = 0;
	String serverResponseMessage=null;
    public String uploadFile(String sourceFileUri) {
          String upLoadServerUri = ApiConstants.BASE_URL;
          String fileName = sourceFileUri;
 
          HttpURLConnection conn = null;
          DataOutputStream dos = null;  
          String lineEnd = "\r\n";
          String twoHyphens = "--";
          String boundary = "*****";
          int bytesRead, bytesAvailable, bufferSize;
          byte[] buffer;
          int maxBufferSize = 1 * 1024 * 1024; 
          File sourceFile = new File("/mnt/sdcard/DCIM/100MEDIA/VIDEO0008.mp4"); 
          if (!sourceFile.isFile()) {
           Log.e("uploadFile", "Source File Does not exist");
           return null;
          }
              try { // <span id="IL_AD8" class="IL_AD">open</span> a URL connection to the Servlet
               FileInputStream fileInputStream = new FileInputStream(sourceFile);
               URL url = new URL(upLoadServerUri);
               conn = (HttpURLConnection) url.openConnection(); // Open a HTTP  connection to  the URL
               conn.setDoInput(true); // Allow Inputs
               conn.setDoOutput(true); // Allow Outputs
               conn.setUseCaches(false); // Don't use a Cached Copy
               conn.setRequestMethod("POST");
               conn.setRequestProperty("Connection", "Keep-Alive");
               conn.setRequestProperty("ENCTYPE", "multipart/form-data");
               conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
               conn.setRequestProperty("uploaded_file", fileName); 
               dos = new DataOutputStream(conn.getOutputStream());
     
               dos.writeBytes(twoHyphens + boundary + lineEnd); 
               dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""+ fileName + "\"" + lineEnd);
               dos.writeBytes(lineEnd);
     
               bytesAvailable = fileInputStream.available(); // create a buffer of  maximum size
     
               bufferSize = Math.min(bytesAvailable, maxBufferSize);
               buffer = new byte[bufferSize];
     
               // read file and write it into form...
               bytesRead = fileInputStream.read(buffer, 0, bufferSize);  
                 
               while (bytesRead > 0) {
                 dos.write(buffer, 0, bufferSize);
                 bytesAvailable = fileInputStream.available();
                 bufferSize = Math.min(bytesAvailable, maxBufferSize);
                 bytesRead = fileInputStream.read(buffer, 0, bufferSize);               
                }
     
               // send multipart form data necesssary after <span id="IL_AD6" class="IL_AD">file data</span>...
               dos.writeBytes(lineEnd);
               dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
     
               // Responses from the server (code and message)
               serverResponseCode = conn.getResponseCode();
                serverResponseMessage = conn.getResponseMessage();
                
               Log.i("uploadFile", "HTTP Response is : " + serverResponseMessage + ": " + serverResponseCode);
               if(serverResponseCode == 200){

//success            
               }    
               
               //close the streams //
               fileInputStream.close();
               dos.flush();
               dos.close();
                
          } catch (MalformedURLException ex) {  
             
              ex.printStackTrace();
            
              Log.e("Upload file to server", "error: " + ex.getMessage(), ex);  
          } catch (Exception e) {
             
              e.printStackTrace();
             
              Log.e("Upload file to seption", "Exception : " + e.getMessage(), e);
          }
              
          return serverResponseMessage;  
         } 
}