package com.example.rss;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    // Khai báo các thành phần giao diện
    ImageView imgWebLogo;
    TextView tvTieuDeWeb;
    ListView lvBaiBao;

    // Danh sách bài báo và adapter
    ArrayList<BaiBao> listBaiBao;
    BaiBaoAdapter baiBaoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các thành phần giao diện từ file XML
        imgWebLogo = findViewById(R.id.imgWebLogo);
        tvTieuDeWeb = findViewById(R.id.tvTieuDeWeb);
        lvBaiBao = findViewById(R.id.lvBaiBao);
        listBaiBao = new ArrayList<>();

        // Sử dụng Handler để làm việc với luồng chính
        Handler handler = new Handler(Looper.getMainLooper());
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Thực hiện công việc trong luồng nền
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // Kết nối đến URL của RSS feed
                    URL fileURL = new URL("https://vnexpress.net/rss/the-thao.rss");
                    InputStream inputStream = fileURL.openStream();

                    // Tạo đối tượng Document từ XML
                    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                    Document docXML = documentBuilder.parse(inputStream);

                    // Lấy thông tin về logo và tiêu đề của trang web từ RSS feed
                    NodeList imageNodeList = docXML.getElementsByTagName("image");
                    Element imageElement = (Element) imageNodeList.item(0);
                    Node webTitleNode = imageElement.getElementsByTagName("title").item(0);
                    Node webLogoNode = imageElement.getElementsByTagName("url").item(0);
                    String webTitle = webTitleNode.getTextContent();
                    String webLogo = webLogoNode.getTextContent();

                    // Tải ảnh từ URL của logo trang web
                    Bitmap bitmap = null;
                    URL webLogoURL = new URL(webLogo);
                    InputStream webLogoInputStream = webLogoURL.openStream();
                    bitmap = BitmapFactory.decodeStream(webLogoInputStream);

                    // Tạo danh sách bài báo từ RSS feed
                    ArrayList<BaiBao> listPost = new ArrayList<>();
                    NodeList itemNodeList = docXML.getElementsByTagName("item");

                    String postTitle, postLink, postImage, postPubDate, postDescrip;
                    for (int i = 0; i < itemNodeList.getLength(); i++) {
                        BaiBao post = new BaiBao();
                        Element itemelement = (Element) itemNodeList.item(i);

                        // Lấy thông tin từng bài báo
                        Node titleNode = itemelement.getElementsByTagName("title").item(0);
                        postTitle = titleNode.getTextContent();
                        post.setTenBaiBao(postTitle);

                        Node pubDateNode = itemelement.getElementsByTagName("pubDate").item(0);
                        postPubDate = pubDateNode.getTextContent();
                        post.setNgayDangBai(postPubDate);

                        Node descripNode = itemelement.getElementsByTagName("description").item(0);
                        org.jsoup.nodes.Document jsoupDoc = Jsoup.parse(descripNode.getTextContent());

                        postLink = jsoupDoc.select("a").attr("href");
                        post.setLinkBaiBao(postLink);

                        postImage = jsoupDoc.select("img").attr("src");
                        post.setLinkAnhBaiBao(postImage);

                        postDescrip = jsoupDoc.body().text();
                        post.setTomTatBaiBao(postDescrip);

                        listPost.add(post);
                    }

                    // Cập nhật giao diện trên luồng chính
                    final Bitmap finalBitmap = bitmap;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            imgWebLogo.setImageBitmap(finalBitmap);
                            tvTieuDeWeb.setText(webTitle);
                            listBaiBao = listPost;
                            baiBaoAdapter = new BaiBaoAdapter(MainActivity.this, R.layout.lv_item_bai_bao, listBaiBao);
                            lvBaiBao.setAdapter(baiBaoAdapter);
                            baiBaoAdapter.notifyDataSetChanged();
                        }
                    });
                } catch (Exception e) {
                    // Xử lý lỗi và hiển thị thông báo trên giao diện
                    final String errorMessage = "Lỗi đọc file XML" + e.toString();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println(errorMessage);
                            Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
