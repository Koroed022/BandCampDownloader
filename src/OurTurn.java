import com.sun.deploy.util.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

class OurTurns extends Solution {


    public static String Turn(String syte) throws IOException, InterruptedException {

        Document allINF = Jsoup.connect(syte).get();
        String outSyte = allINF.toString();//.replaceAll("<.*>", "").trim();
        String outName;
        String outMisc;
        String outNameMisc;
        int nomTrack = 0;
        outName = outSyte.split("artist: \"")[1].split("\",\n" + "    swf_base_url:")[0];
        int count = outSyte.split("\"track_num\":",-1).length-1;
        while (true) {
            nomTrack++;
            outMisc = outSyte.split("128\":\"")[1].split("\"},\"video")[0];
            outNameMisc = outSyte.split("title_link\":\"/track/")[1].split("\",\"is_capped")[0];
            if(nomTrack  <= count)
            outSyte = allINF.toString().split("\"track_num\":" + nomTrack)[1].split("\"track_num\":" + nomTrack+1)[0];
            else{
                outSyte = allINF.toString().split("\"track_num\":" + count)[1].split("playing_from:")[0];
                count++;
            }

           // if(nomTrack + 1 == count)



            String fileName = "C:\\Users\\Vlad\\Desktop\\atrs for GLEDB\\progMisc\\" + outName + " - " + outNameMisc + ".mp3";
            URL link = new URL(outMisc);

            InputStream in = new BufferedInputStream(link.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n;
            while (-1 != (n = in.read(buf))) {
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
            byte[] response = out.toByteArray();

            FileOutputStream fos = new FileOutputStream(fileName);
            fos.write(response);
            fos.close();


            System.out.println(outName + "\n" + outMisc);

        }

    }
}


