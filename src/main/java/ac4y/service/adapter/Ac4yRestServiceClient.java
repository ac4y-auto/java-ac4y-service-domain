package ac4y.service.adapter;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Ac4yRestServiceClient {

    public String getStackTraceAsString(Exception exc)
    {
        String stackTrace = "*** Error in getStackTraceAsString()";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream( baos );
        exc.printStackTrace(ps);
        try {
            stackTrace = baos.toString( "UTF8" ); // charsetName e.g. ISO-8859-1
        }
        catch( UnsupportedEncodingException ex )
        {
            ///Logger.getLogger(sss.class.getName()).log(Level.SEVERE, null, ex);
        }
        ps.close();
        try {
            baos.close();
        }
        catch( IOException ex )
        {
            ///Logger.getLogger(sss.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stackTrace;
    }

    public String request(String host, String method, String request) throws IOException {

        String response = null;

        HttpURLConnection urlConnection = null;
/*
        try {
*/
        URL url = new URL(host);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod(method);

        //urlConnection.setChunkedStreamingMode(0);

        if (request!=null) {

            urlConnection.setRequestProperty("Content-Type","application/json");
            urlConnection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
            wr.writeBytes(request);
            wr.flush();
            wr.close();

        }

        int responseCode = urlConnection.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String inputLine;
        StringBuffer result = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            result.append(inputLine);
        }
        in.close();

        response = result.toString();
/*
        } catch (Exception e) {
            e.printStackTrace();
            response = getStackTraceAsString(e);
        } finally {
            urlConnection.disconnect();
        }
*/
        return response;

    } // request

}