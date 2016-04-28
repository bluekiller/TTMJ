package com.pfwu.ttmj.core.services;

import com.pfwu.ttmj.core.datatypes.DownloadEntry;
import com.pfwu.ttmj.core.datatypes.DramaDetail;
import com.pfwu.ttmj.core.datatypes.Link;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PageParser {

    public static DramaDetail getDramaDetail(Document doc) throws ParseException {
        DramaDetail dramaDetail = new DramaDetail();
        Elements title = doc.select("div.hd>h3");
        String name = title.get(0).text();
        if (name.contains("-")) {
            name = name.split("-")[0];
        }
        Elements detail = doc.select("div.seedlink>table.seedtable");
        Elements rows = detail.get(0).getElementsByTag("tr");
        dramaDetail.setShowDay(rows.get(0).getElementsByTag("td").get(1).text());
        dramaDetail.setStatus(rows.get(1).getElementsByTag("td").get(1).text());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm");
        dramaDetail.setPublishTime(sdf.parse(rows.get(2).getElementsByTag("td").get(1).text()));
        dramaDetail.setLastUpdateTime(sdf.parse(rows.get(3).getElementsByTag("td").get(1).text()));
        dramaDetail.setName(name);
        Elements image = doc.select("div.seedpic>img");
        dramaDetail.setCoverImageURL(image.get(0).attr("src"));
        return dramaDetail;
    }

    public static List<DownloadEntry> getDownloadEntries(URL url) {
        Document doc;
        List<DownloadEntry> entries = new ArrayList<>();
        try {
            doc = Jsoup.parse(url, 2000);
            Elements tables = doc.select(".seedtable");
            Element downloadEntries = tables.last();
            Iterator<Element> rowIterator = downloadEntries.select("tr").iterator();
            while (rowIterator.hasNext()) {
                Element row = rowIterator.next();
                Elements columns = row.getElementsByTag("td");
                if (columns.size() < 5) {
                    continue;
                }
                String name = columns.get(1).text();
                List<Link> links = new ArrayList<Link>();
                Elements downloadLinks = columns.get(2).getElementsByTag("a");
                if (downloadLinks != null) {
                    Iterator<Element> linkIterator = downloadLinks.iterator();
                    while (linkIterator.hasNext()) {
                        Element linkElement = linkIterator.next();
                        Link link = new Link();
                        link.setLink(linkElement.attr("href"));
                        links.add(link);
                    }
                }
                int size = parseSize(columns.get(3).text());
                String format = columns.get(4).text();
                String subtitle = columns.get(5).text();

                DownloadEntry entry = new DownloadEntry();
                entry.setDownloadLinks(links);
                entry.setName(name);
                entry.setSizeInMegaBytes(size);
                entry.setSubtitle(subtitle);
                entry.setFormat(format);
                entries.add(entry);
                System.out.println(entry);
                System.out.println(PageParser.getDramaDetail(doc));
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return entries;
    }

    private static int parseSize(String text) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static void main(String[] args) throws MalformedURLException {
        getDownloadEntries(new URL("http://www.ttmeiju.com/meiju/How.I.Met.Your.Mother.html"));
    }
}
